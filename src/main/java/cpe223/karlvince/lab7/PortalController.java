package cpe223.karlvince.lab7;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;


public class PortalController implements Initializable {

    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, String> colStudentId;
    @FXML private TableColumn<Student, String> colStudentFirst;
    @FXML private TableColumn<Student, String> colStudentMiddle;
    @FXML private TableColumn<Student, String> colStudentLast;
    @FXML private TableColumn<Student, Integer> colStudentAge;
    @FXML private TableColumn<Student, String> colStudentEmail;
    @FXML private TableColumn<Student, String> colStudentYear;
    @FXML private TableColumn<Student, String> colStudentProgram;
    @FXML private TableColumn<Student, Boolean> colStudentRegular;
    @FXML private TableColumn<Student, Void> colStudentAction;

    @FXML private TextField tfStudentId;
    @FXML private TextField tfStudentFirst;
    @FXML private TextField tfStudentMiddle;
    @FXML private TextField tfStudentLast;
    @FXML private TextField tfStudentAge;
    @FXML private TextField tfStudentEmail;
    @FXML private ComboBox<String> cbStudentYear;
    @FXML private ComboBox<String> cbStudentProgram;
    @FXML private CheckBox chkStudentRegular;
    @FXML private Button btnStudentAdd;

    @FXML private TableView<Course> courseTable;
    @FXML private TableColumn<Course, String> colCourseCode;
    @FXML private TableColumn<Course, String> colCourseName;
    @FXML private TableColumn<Course, Integer> colCourseUnits;
    @FXML private TableColumn<Course, String> colCourseDept;
    @FXML private TableColumn<Course, String> colCourseType;
    @FXML private TableColumn<Course, Boolean> colCourseActive;
    @FXML private TableColumn<Course, Void> colCourseAction;

    @FXML private TextField tfCourseCode;
    @FXML private TextField tfCourseName;
    @FXML private ComboBox<String> cbCourseUnits;
    @FXML private ComboBox<String> cbCourseDept;
    @FXML private RadioButton rbLecture;
    @FXML private RadioButton rbLab;
    @FXML private RadioButton rbElective;
    @FXML private CheckBox chkCourseActive;
    @FXML private Button btnCourseAdd;

    @FXML private TableView<Instructor> instructorTable;
    @FXML private TableColumn<Instructor, String> colInstructorId;
    @FXML private TableColumn<Instructor, String> colInstructorFirst;
    @FXML private TableColumn<Instructor, String> colInstructorMiddle;
    @FXML private TableColumn<Instructor, String> colInstructorLast;
    @FXML private TableColumn<Instructor, String> colInstructorEmail;
    @FXML private TableColumn<Instructor, String> colInstructorDept;
    @FXML private TableColumn<Instructor, String> colInstructorRank;
    @FXML private TableColumn<Instructor, Boolean> colInstructorFT;
    @FXML private TableColumn<Instructor, Void> colInstructorAction;

    @FXML private TextField tfInstructorId;
    @FXML private TextField tfInstructorFirst;
    @FXML private TextField tfInstructorMiddle;
    @FXML private TextField tfInstructorLast;
    @FXML private TextField tfInstructorEmail;
    @FXML private ComboBox<String> cbInstructorDept;
    @FXML private ComboBox<String> cbInstructorRank;
    @FXML private CheckBox chkInstructorFT;
    @FXML private Button btnInstructorAdd;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new StudentTabManager(
                studentTable,
                colStudentId, colStudentFirst, colStudentMiddle, colStudentLast,
                colStudentAge, colStudentEmail,
                colStudentYear, colStudentProgram, colStudentRegular, colStudentAction,
                tfStudentId, tfStudentFirst, tfStudentMiddle, tfStudentLast,
                tfStudentAge, tfStudentEmail,
                cbStudentYear, cbStudentProgram, chkStudentRegular, btnStudentAdd
        ).init();

        new CourseTabManager(
                courseTable,
                colCourseCode, colCourseName, colCourseUnits, colCourseDept,
                colCourseType, colCourseActive, colCourseAction,
                tfCourseCode, tfCourseName, cbCourseUnits, cbCourseDept,
                rbLecture, rbLab, rbElective, chkCourseActive, btnCourseAdd
        ).init();

        new InstructorTabManager(
                instructorTable,
                colInstructorId, colInstructorFirst, colInstructorMiddle, colInstructorLast,
                colInstructorEmail, colInstructorDept, colInstructorRank,
                colInstructorFT, colInstructorAction,
                tfInstructorId, tfInstructorFirst, tfInstructorMiddle, tfInstructorLast,
                tfInstructorEmail,
                cbInstructorDept, cbInstructorRank, chkInstructorFT, btnInstructorAdd
        ).init();
    }
}
