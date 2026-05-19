package cpe223.karlvince.lab7;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public final class SampleData {

    private SampleData() {}

    public static ObservableList<Student> getStudents() {
        return FXCollections.observableArrayList(
                new Student("647281", "Karl Vince", "Reyes", "karlvince.dev@cpe.edu.ph", "3rd Year", "CPE", true),
                new Student("674291", "Samus", "Aran", "samus.aran.hunter@ece.edu.ph", "2nd Year", "ECE", true),
                new Student("657289", "Link", "Hyrule", "link.hyrule.hero@cs.edu.ph", "4th Year", "CS", false),
                new Student("643829", "Lara", "Croft", "lara.croft.explorer@it.edu.ph", "1st Year", "IT", true),
                new Student("682100", "Master", "Chief", "master.chief.spartan@ee.edu.ph", "3rd Year", "EE", false)
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
                new Instructor("7529172", "Sonic", "Hedgehog", "sonic.hedgehog.fast@cpe.edu.ph", "CPE", "Professor", true),
                new Instructor("7428102", "Mario", "Jumpman", "mario.jumpman.plumber@ece.edu.ph", "ECE", "Associate Professor", true),
                new Instructor("7404852", "Donkey", "Kong", "donkey.kong.jungle@cs.edu.ph", "CS", "Assistant Professor", false),
                new Instructor("7952912", "Kirby", "Popstar", "kirby.popstar.hero@it.edu.ph", "IT", "Lecturer", true),
                new Instructor("7528192", "Pac", "Man", "pac.man.ghost@ee.edu.ph", "EE", "Professor", true)
        );
    }
}
