package cpe223.karlvince.lab7;

import javafx.beans.property.*;
import javafx.beans.property.SimpleIntegerProperty;

public class Student {

    private final StringProperty studentId;
    private final StringProperty firstName;
    private final StringProperty middleName;
    private final StringProperty lastName;
    private final IntegerProperty age;
    private final StringProperty email;
    private final StringProperty yearLevel;
    private final StringProperty program;
    private final BooleanProperty regular;

    public Student(String studentId, String firstName, String middleName, String lastName,
                   int age, String email, String yearLevel, String program, boolean regular) {
        this.studentId = new SimpleStringProperty(studentId);
        this.firstName = new SimpleStringProperty(firstName);
        this.middleName = new SimpleStringProperty(middleName);
        this.lastName = new SimpleStringProperty(lastName);
        this.age = new SimpleIntegerProperty(age);
        this.email = new SimpleStringProperty(email);
        this.yearLevel = new SimpleStringProperty(yearLevel);
        this.program = new SimpleStringProperty(program);
        this.regular = new SimpleBooleanProperty(regular);
    }

    // studentId
    public String getStudentId() { return studentId.get(); }
    public void setStudentId(String value) { studentId.set(value); }
    public StringProperty studentIdProperty() { return studentId; }

    // firstName
    public String getFirstName() { return firstName.get(); }
    public void setFirstName(String value) { firstName.set(value); }
    public StringProperty firstNameProperty() { return firstName; }

    // middleName
    public String getMiddleName() { return middleName.get(); }
    public void setMiddleName(String value) { middleName.set(value); }
    public StringProperty middleNameProperty() { return middleName; }

    // lastName
    public String getLastName() { return lastName.get(); }
    public void setLastName(String value) { lastName.set(value); }
    public StringProperty lastNameProperty() { return lastName; }

    // age
    public int getAge() { return age.get(); }
    public void setAge(int value) { age.set(value); }
    public IntegerProperty ageProperty() { return age; }

    // email
    public String getEmail() { return email.get(); }
    public void setEmail(String value) { email.set(value); }
    public StringProperty emailProperty() { return email; }

    // yearLevel
    public String getYearLevel() { return yearLevel.get(); }
    public void setYearLevel(String value) { yearLevel.set(value); }
    public StringProperty yearLevelProperty() { return yearLevel; }

    // program
    public String getProgram() { return program.get(); }
    public void setProgram(String value) { program.set(value); }
    public StringProperty programProperty() { return program; }

    // status
    public boolean isRegular() { return regular.get(); }
    public void setRegular(boolean value) { regular.set(value); }
    public BooleanProperty regularProperty() { return regular; }
}
