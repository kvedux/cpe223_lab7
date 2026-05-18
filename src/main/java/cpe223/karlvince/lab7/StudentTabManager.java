package cpe223.karlvince.lab7;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentTabManager {

    private final TableView<Student> table;
    private final TableColumn<Student, String> colId;
    private final TableColumn<Student, String> colFirst;
    private final TableColumn<Student, String> colLast;
    private final TableColumn<Student, String> colEmail;
    private final TableColumn<Student, String> colYear;
    private final TableColumn<Student, String> colProgram;
    private final TableColumn<Student, Boolean> colRegular;
    private final TableColumn<Student, Void> colAction;

    private final TextField tfId;
    private final TextField tfFirst;
    private final TextField tfLast;
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
                             TableColumn<Student, String> colLast,
                             TableColumn<Student, String> colEmail,
                             TableColumn<Student, String> colYear,
                             TableColumn<Student, String> colProgram,
                             TableColumn<Student, Boolean> colRegular,
                             TableColumn<Student, Void> colAction,
                             TextField tfId, TextField tfFirst, TextField tfLast,
                             TextField tfEmail, ComboBox<String> cbYear,
                             ComboBox<String> cbProgram, CheckBox chkRegular,
                             Button btnAdd) {
        this.table = table;
        this.colId = colId;
        this.colFirst = colFirst;
        this.colLast = colLast;
        this.colEmail = colEmail;
        this.colYear = colYear;
        this.colProgram = colProgram;
        this.colRegular = colRegular;
        this.colAction = colAction;
        this.tfId = tfId;
        this.tfFirst = tfFirst;
        this.tfLast = tfLast;
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
        colLast.setCellValueFactory(new PropertyValueFactory<>("lastName"));
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
        tfLast.setText(s.getLastName());
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
        String last = tfLast.getText().trim();
        String email = tfEmail.getText().trim();
        String year = cbYear.getValue();
        String program = cbProgram.getValue();
        boolean regular = chkRegular.isSelected();

        if (id.isEmpty() || first.isEmpty() || last.isEmpty()) return;

        if (editing != null) {
            editing.setStudentId(id);
            editing.setFirstName(first);
            editing.setLastName(last);
            editing.setEmail(email);
            editing.setYearLevel(year);
            editing.setProgram(program);
            editing.setRegular(regular);
            table.refresh();
        } else {
            dataList.add(new Student(id, first, last, email, year, program, regular));
        }
        clearForm();
    }

    private void clearForm() {
        editing = null;
        tfId.clear();
        tfFirst.clear();
        tfLast.clear();
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
