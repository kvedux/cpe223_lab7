package cpe223.karlvince.lab7;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class CourseTabManager {

    private final TableView<Course> table;
    private final TableColumn<Course, String> colCode;
    private final TableColumn<Course, String> colName;
    private final TableColumn<Course, Integer> colUnits;
    private final TableColumn<Course, String> colDept;
    private final TableColumn<Course, String> colType;
    private final TableColumn<Course, Boolean> colActive;
    private final TableColumn<Course, Void> colAction;

    private final TextField tfCode;
    private final TextField tfName;
    private final ComboBox<String> cbUnits;
    private final ComboBox<String> cbDept;
    private final RadioButton rbLecture;
    private final RadioButton rbLab;
    private final RadioButton rbElective;
    private final CheckBox chkActive;
    private final Button btnAdd;

    private ObservableList<Course> dataList;
    private Course editing = null;
    private ToggleGroup courseTypeGroup;

    public CourseTabManager(TableView<Course> table,
                            TableColumn<Course, String> colCode,
                            TableColumn<Course, String> colName,
                            TableColumn<Course, Integer> colUnits,
                            TableColumn<Course, String> colDept,
                            TableColumn<Course, String> colType,
                            TableColumn<Course, Boolean> colActive,
                            TableColumn<Course, Void> colAction,
                            TextField tfCode, TextField tfName,
                            ComboBox<String> cbUnits, ComboBox<String> cbDept,
                            RadioButton rbLecture, RadioButton rbLab,
                            RadioButton rbElective, CheckBox chkActive,
                            Button btnAdd) {
        this.table = table;
        this.colCode = colCode;
        this.colName = colName;
        this.colUnits = colUnits;
        this.colDept = colDept;
        this.colType = colType;
        this.colActive = colActive;
        this.colAction = colAction;
        this.tfCode = tfCode;
        this.tfName = tfName;
        this.cbUnits = cbUnits;
        this.cbDept = cbDept;
        this.rbLecture = rbLecture;
        this.rbLab = rbLab;
        this.rbElective = rbElective;
        this.chkActive = chkActive;
        this.btnAdd = btnAdd;
    }

    public void init() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colUnits.setCellValueFactory(new PropertyValueFactory<>("units"));
        colDept.setCellValueFactory(new PropertyValueFactory<>("department"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colActive.setCellValueFactory(new PropertyValueFactory<>("active"));

        colActive.setCellFactory(col -> new TableCell<Course, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : (item ? "Active" : "Inactive"));
            }
        });

        ActionColumnFactory.install(colAction, this::loadForEdit, this::delete);

        cbUnits.setItems(FXCollections.observableArrayList("1", "2", "3", "4", "5", "6"));
        cbDept.setItems(FXCollections.observableArrayList(
                "CPE", "ECE", "EE", "CS", "IT", "GE"));

        courseTypeGroup = new ToggleGroup();
        rbLecture.setToggleGroup(courseTypeGroup);
        rbLab.setToggleGroup(courseTypeGroup);
        rbElective.setToggleGroup(courseTypeGroup);
        rbLecture.setSelected(true);

        dataList = SampleData.getCourses();
        table.setItems(dataList);

        btnAdd.setOnAction(e -> handleAddOrUpdate());
    }

    // helper

    private void loadForEdit(Course c) {
        editing = c;
        tfCode.setText(c.getCourseCode());
        tfName.setText(c.getCourseName());
        cbUnits.setValue(String.valueOf(c.getUnits()));
        cbDept.setValue(c.getDepartment());
        chkActive.setSelected(c.isActive());

        switch (c.getType()) {
            case "Lecture":     rbLecture.setSelected(true);  break;
            case "Laboratory":  rbLab.setSelected(true);      break;
            case "Elective":    rbElective.setSelected(true);  break;
        }
        btnAdd.setText("Update");
    }

    private void delete(Course c) {
        dataList.remove(c);
        clearForm();
    }

    private void handleAddOrUpdate() {
        String code = tfCode.getText().trim();
        String name = tfName.getText().trim();
        String unitsStr = cbUnits.getValue();
        String dept = cbDept.getValue();
        boolean active = chkActive.isSelected();

        if (code.isEmpty() || name.isEmpty() || unitsStr == null) return;

        int units = Integer.parseInt(unitsStr);
        String type = "Lecture";
        if (rbLab.isSelected()) type = "Laboratory";
        else if (rbElective.isSelected()) type = "Elective";

        if (editing != null) {
            editing.setCourseCode(code);
            editing.setCourseName(name);
            editing.setUnits(units);
            editing.setDepartment(dept);
            editing.setType(type);
            editing.setActive(active);
            table.refresh();
        } else {
            dataList.add(new Course(code, name, units, dept, type, active));
        }
        clearForm();
    }

    private void clearForm() {
        editing = null;
        tfCode.clear();
        tfName.clear();
        cbUnits.getSelectionModel().clearSelection();
        cbUnits.setValue(null);
        cbDept.getSelectionModel().clearSelection();
        cbDept.setValue(null);
        rbLecture.setSelected(true);
        chkActive.setSelected(false);
        btnAdd.setText("Add");
        table.getSelectionModel().clearSelection();
    }
}
