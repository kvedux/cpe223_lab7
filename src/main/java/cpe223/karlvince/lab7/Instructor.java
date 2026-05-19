package cpe223.karlvince.lab7;

import javafx.beans.property.*;

public class Instructor {

    private final StringProperty instructorId;
    private final StringProperty firstName;
    private final StringProperty middleName;
    private final StringProperty lastName;
    private final StringProperty email;
    private final StringProperty department;
    private final StringProperty rank;
    private final BooleanProperty fullTime;

    public Instructor(String instructorId, String firstName, String middleName, String lastName,
                      String email, String department, String rank, boolean fullTime) {
        this.instructorId = new SimpleStringProperty(instructorId);
        this.firstName = new SimpleStringProperty(firstName);
        this.middleName = new SimpleStringProperty(middleName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.department = new SimpleStringProperty(department);
        this.rank = new SimpleStringProperty(rank);
        this.fullTime = new SimpleBooleanProperty(fullTime);
    }

    // instructorId
    public String getInstructorId() { return instructorId.get(); }
    public void setInstructorId(String value) { instructorId.set(value); }
    public StringProperty instructorIdProperty() { return instructorId; }

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

    // email
    public String getEmail() { return email.get(); }
    public void setEmail(String value) { email.set(value); }
    public StringProperty emailProperty() { return email; }

    // department
    public String getDepartment() { return department.get(); }
    public void setDepartment(String value) { department.set(value); }
    public StringProperty departmentProperty() { return department; }

    // rank
    public String getRank() { return rank.get(); }
    public void setRank(String value) { rank.set(value); }
    public StringProperty rankProperty() { return rank; }

    // fullTime
    public boolean isFullTime() { return fullTime.get(); }
    public void setFullTime(boolean value) { fullTime.set(value); }
    public BooleanProperty fullTimeProperty() { return fullTime; }
}
