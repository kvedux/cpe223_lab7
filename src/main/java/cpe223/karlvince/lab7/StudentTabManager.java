package cpe223.karlvince.lab7;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentTabManager {

    private final TableView<Student> table;
    private final TableColumn<Student, String> colId;
    private final TableColumn<Student, String> colFirst;
    private final TableColumn<Student, String> colMiddle;
    private final TableColumn<Student, String> colLast;
    private final TableColumn<Student, Integer> colAge;
    private final TableColumn<Student, String> colEmail;
    private final TableColumn<Student, String> colYear;
    private final TableColumn<Student, String> colProgram;
    private final TableColumn<Student, Boolean> colRegular;
    private final TableColumn<Student, Void> colAction;

    private final TextField tfId;
    private final TextField tfFirst;
    private final TextField tfMiddle;
    private final TextField tfLast;
    private final TextField tfAge;
    private final TextField tfEmail;
    private final ComboBox<String> cbYear;
    private final ComboBox<String> cbProgram;
    private final CheckBox chkRegular;
    private final Button btnAdd;

    private ObservableList<Student> dataList;
    private Student editing = null;

    public StudentTabManager(TableView<Student> table,
                             TableColumn<Student, String> colId,
                             TableColumn<Student, String> colFirst,
                             TableColumn<Student, String> colMiddle,
                             TableColumn<Student, String> colLast,
                             TableColumn<Student, Integer> colAge,
                             TableColumn<Student, String> colEmail,
                             TableColumn<Student, String> colYear,
                             TableColumn<Student, String> colProgram,
                             TableColumn<Student, Boolean> colRegular,
                             TableColumn<Student, Void> colAction,
                             TextField tfId, TextField tfFirst, TextField tfMiddle,
                             TextField tfLast, TextField tfAge, TextField tfEmail,
                             ComboBox<String> cbYear,
                             ComboBox<String> cbProgram, CheckBox chkRegular,
                             Button btnAdd) {
        this.table = table;
        this.colId = colId;
        this.colFirst = colFirst;
        this.colMiddle = colMiddle;
        this.colLast = colLast;
        this.colAge = colAge;
        this.colEmail = colEmail;
        this.colYear = colYear;
        this.colProgram = colProgram;
        this.colRegular = colRegular;
        this.colAction = colAction;
        this.tfId = tfId;
        this.tfFirst = tfFirst;
        this.tfMiddle = tfMiddle;
        this.tfLast = tfLast;
        this.tfAge = tfAge;
        this.tfEmail = tfEmail;
        this.cbYear = cbYear;
        this.cbProgram = cbProgram;
        this.chkRegular = chkRegular;
        this.btnAdd = btnAdd;
    }

    public void init() {
        // Bind columns
        colId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colFirst.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colMiddle.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        colLast.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("yearLevel"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("program"));
        colRegular.setCellValueFactory(new PropertyValueFactory<>("regular"));

        colRegular.setCellFactory(col -> new TableCell<Student, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : (item ? "Yes" : "No"));
            }
        });

        ActionColumnFactory.install(colAction, this::loadForEdit, this::delete);

        cbYear.setItems(FXCollections.observableArrayList(
                "1st Year", "2nd Year", "3rd Year", "4th Year"));
        cbProgram.setItems(FXCollections.observableArrayList(
                "CPE", "ECE", "EE", "CS", "IT"));

        dataList = SampleData.getStudents();
        table.setItems(dataList);

        btnAdd.setOnAction(e -> handleAddOrUpdate());
    }

    // helper

    private void loadForEdit(Student s) {
        editing = s;
        tfId.setText(s.getStudentId());
        tfFirst.setText(s.getFirstName());
        tfMiddle.setText(s.getMiddleName());
        tfLast.setText(s.getLastName());
        tfAge.setText(String.valueOf(s.getAge()));
        tfEmail.setText(s.getEmail());
        cbYear.setValue(s.getYearLevel());
        cbProgram.setValue(s.getProgram());
        chkRegular.setSelected(s.isRegular());
        btnAdd.setText("Update");
    }

    private void delete(Student s) {
        dataList.remove(s);
        clearForm();
    }

    private void handleAddOrUpdate() {
        String id = tfId.getText().trim();
        String first = tfFirst.getText().trim();
        String middle = tfMiddle.getText().trim();
        String last = tfLast.getText().trim();
        String ageText = tfAge.getText().trim();
        String email = tfEmail.getText().trim();
        String year = cbYear.getValue();
        String program = cbProgram.getValue();
        boolean regular = chkRegular.isSelected();

        if (id.isEmpty() || first.isEmpty() || last.isEmpty()) return;
        int age = 0;
        try { age = Integer.parseInt(ageText); } catch (NumberFormatException ignored) {}

        if (editing != null) {
            editing.setStudentId(id);
            editing.setFirstName(first);
            editing.setMiddleName(middle);
            editing.setLastName(last);
            editing.setAge(age);
            editing.setEmail(email);
            editing.setYearLevel(year);
            editing.setProgram(program);
            editing.setRegular(regular);
            table.refresh();
        } else {
            dataList.add(new Student(id, first, middle, last, age, email, year, program, regular));
        }
        clearForm();
    }

    private void clearForm() {
        editing = null;
        tfId.clear();
        tfFirst.clear();
        tfMiddle.clear();
        tfLast.clear();
        tfAge.clear();
        tfEmail.clear();
        cbYear.getSelectionModel().clearSelection();
        cbYear.setValue(null);
        cbProgram.getSelectionModel().clearSelection();
        cbProgram.setValue(null);
        chkRegular.setSelected(false);
        btnAdd.setText("Add");
        table.getSelectionModel().clearSelection();
    }
}
