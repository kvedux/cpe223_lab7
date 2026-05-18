module cpe223.karlvince.lab7 {
    
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;

    opens cpe223.karlvince.lab7 to javafx.fxml;
    exports cpe223.karlvince.lab7;
}

