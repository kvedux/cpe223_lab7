package cpe223.karlvince.lab7;

import javafx.beans.property.*;

public class Course {

    private final StringProperty courseCode;
    private final StringProperty courseName;
    private final IntegerProperty units;
    private final StringProperty department;
    private final StringProperty type;       // Lecture, Laboratory, Elective
    private final BooleanProperty active;

    public Course(String courseCode, String courseName, int units,
                  String department, String type, boolean active) {
        this.courseCode = new SimpleStringProperty(courseCode);
        this.courseName = new SimpleStringProperty(courseName);
        this.units = new SimpleIntegerProperty(units);
        this.department = new SimpleStringProperty(department);
        this.type = new SimpleStringProperty(type);
        this.active = new SimpleBooleanProperty(active);
    }

    // courseCode
    public String getCourseCode() { return courseCode.get(); }
    public void setCourseCode(String value) { courseCode.set(value); }
    public StringProperty courseCodeProperty() { return courseCode; }

    // courseName
    public String getCourseName() { return courseName.get(); }
    public void setCourseName(String value) { courseName.set(value); }
    public StringProperty courseNameProperty() { return courseName; }

    // units
    public int getUnits() { return units.get(); }
    public void setUnits(int value) { units.set(value); }
    public IntegerProperty unitsProperty() { return units; }

    // department
    public String getDepartment() { return department.get(); }
    public void setDepartment(String value) { department.set(value); }
    public StringProperty departmentProperty() { return department; }

    // type
    public String getType() { return type.get(); }
    public void setType(String value) { type.set(value); }
    public StringProperty typeProperty() { return type; }

    // active
    public boolean isActive() { return active.get(); }
    public void setActive(boolean value) { active.set(value); }
    public BooleanProperty activeProperty() { return active; }
}
