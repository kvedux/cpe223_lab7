package cpe223.karlvince.lab7;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public final class SampleData {

    private SampleData() {}

    public static ObservableList<Student> getStudents() {
        return FXCollections.observableArrayList(
                new Student("647281", "Karl Vince", "Reyes", "karlvince.dev@cpe.edu.ph", "3rd Year", "CPE", true),
                new Student("674291", "Maria", "Santos", "maria.santos.explorer@ece.edu.ph", "2nd Year", "ECE", true),
                new Student("657289", "Juan", "Dela Cruz", "juan.delacruz.coder@cs.edu.ph", "4th Year", "CS", false),
                new Student("643829", "Ana", "Reyes", "ana.reyes.innovator@it.edu.ph", "1st Year", "IT", true),
                new Student("682100", "Miguel", "Garcia", "miguel.garcia.spark@ee.edu.ph", "3rd Year", "EE", false)
        );
    }

    public static ObservableList<Course> getCourses() {
        return FXCollections.observableArrayList(
                new Course("CPE 223", "Data Structures", 3, "CPE", "Lecture", true),
                new Course("CPE 223L", "Data Structures Lab", 1, "CPE", "Laboratory", true),
                new Course("ECE 321", "Electronics II", 3, "ECE", "Lecture", true),
                new Course("GE 101", "Purposive Communication", 3, "GE", "Lecture", true),
                new Course("CS 301", "AI Elective", 3, "CS", "Elective", false)
        );
    }

    public static ObservableList<Instructor> getInstructors() {
        return FXCollections.observableArrayList(
                new Instructor("7529172", "Dr. Jose", "Rizal", "jose.rizal.phd@cpe.edu.ph", "CPE", "Professor", true),
                new Instructor("7428102", "Engr. Andres", "Bonifacio", "andres.bonifacio.dean@ece.edu.ph", "ECE", "Associate Professor", true),
                new Instructor("7404852", "Engr. Emilio", "Aguinaldo", "emilio.aguinaldo.lead@cs.edu.ph", "CS", "Assistant Professor", false),
                new Instructor("7952912", "Ms. Gabriela", "Silang", "gabriela.silang.tech@it.edu.ph", "IT", "Lecturer", true),
                new Instructor("7528192", "Dr. Apolinario", "Mabini", "apolinario.mabini.chair@ee.edu.ph", "EE", "Professor", true)
        );
    }
}
