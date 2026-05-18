package cpe223.karlvince.lab7;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class InstructorTabManager {

    private final TableView<Instructor> table;
    private final TableColumn<Instructor, String> colId;
    private final TableColumn<Instructor, String> colFirst;
    private final TableColumn<Instructor, String> colLast;
    private final TableColumn<Instructor, String> colEmail;
    private final TableColumn<Instructor, String> colDept;
    private final TableColumn<Instructor, String> colRank;
    private final TableColumn<Instructor, Boolean> colFT;
    private final TableColumn<Instructor, Void> colAction;

    private final TextField tfId;
    private final TextField tfFirst;
    private final TextField tfLast;
    private final TextField tfEmail;
    private final ComboBox<String> cbDept;
    private final ComboBox<String> cbRank;
    private final CheckBox chkFT;
    private final Button btnAdd;

    private ObservableList<Instructor> dataList;
    private Instructor editing = null;

    public InstructorTabManager(TableView<Instructor> table,
                                TableColumn<Instructor, String> colId,
                                TableColumn<Instructor, String> colFirst,
                                TableColumn<Instructor, String> colLast,
                                TableColumn<Instructor, String> colEmail,
                                TableColumn<Instructor, String> colDept,
                                TableColumn<Instructor, String> colRank,
                                TableColumn<Instructor, Boolean> colFT,
                                TableColumn<Instructor, Void> colAction,
                                TextField tfId, TextField tfFirst, TextField tfLast,
                                TextField tfEmail, ComboBox<String> cbDept,
                                ComboBox<String> cbRank, CheckBox chkFT,
                                Button btnAdd) {
        this.table = table;
        this.colId = colId;
        this.colFirst = colFirst;
        this.colLast = colLast;
        this.colEmail = colEmail;
        this.colDept = colDept;
        this.colRank = colRank;
        this.colFT = colFT;
        this.colAction = colAction;
        this.tfId = tfId;
        this.tfFirst = tfFirst;
        this.tfLast = tfLast;
        this.tfEmail = tfEmail;
        this.cbDept = cbDept;
        this.cbRank = cbRank;
        this.chkFT = chkFT;
        this.btnAdd = btnAdd;
    }

    public void init() {
        colId.setCellValueFactory(new PropertyValueFactory<>("instructorId"));
        colFirst.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLast.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDept.setCellValueFactory(new PropertyValueFactory<>("department"));
        colRank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        colFT.setCellValueFactory(new PropertyValueFactory<>("fullTime"));

        colFT.setCellFactory(col -> new TableCell<Instructor, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : (item ? "Full-Time" : "Part-Time"));
            }
        });

        ActionColumnFactory.install(colAction, this::loadForEdit, this::delete);

        cbDept.setItems(FXCollections.observableArrayList(
                "CPE", "ECE", "EE", "CS", "IT", "GE"));
        cbRank.setItems(FXCollections.observableArrayList(
                "Professor", "Associate Professor", "Assistant Professor", "Lecturer"));

        dataList = SampleData.getInstructors();
        table.setItems(dataList);

        btnAdd.setOnAction(e -> handleAddOrUpdate());
    }

    // helper

    private void loadForEdit(Instructor i) {
        editing = i;
        tfId.setText(i.getInstructorId());
        tfFirst.setText(i.getFirstName());
        tfLast.setText(i.getLastName());
        tfEmail.setText(i.getEmail());
        cbDept.setValue(i.getDepartment());
        cbRank.setValue(i.getRank());
        chkFT.setSelected(i.isFullTime());
        btnAdd.setText("Update");
    }

    private void delete(Instructor i) {
        dataList.remove(i);
        clearForm();
    }

    private void handleAddOrUpdate() {
        String id = tfId.getText().trim();
        String first = tfFirst.getText().trim();
        String last = tfLast.getText().trim();
        String email = tfEmail.getText().trim();
        String dept = cbDept.getValue();
        String rank = cbRank.getValue();
        boolean ft = chkFT.isSelected();

        if (id.isEmpty() || first.isEmpty() || last.isEmpty()) return;

        if (editing != null) {
            editing.setInstructorId(id);
            editing.setFirstName(first);
            editing.setLastName(last);
            editing.setEmail(email);
            editing.setDepartment(dept);
            editing.setRank(rank);
            editing.setFullTime(ft);
            table.refresh();
        } else {
            dataList.add(new Instructor(id, first, last, email, dept, rank, ft));
        }
        clearForm();
    }

    private void clearForm() {
        editing = null;
        tfId.clear();
        tfFirst.clear();
        tfLast.clear();
        tfEmail.clear();
        cbDept.getSelectionModel().clearSelection();
        cbDept.setValue(null);
        cbRank.getSelectionModel().clearSelection();
        cbRank.setValue(null);
        chkFT.setSelected(false);
        btnAdd.setText("Add");
        table.getSelectionModel().clearSelection();
    }
}
