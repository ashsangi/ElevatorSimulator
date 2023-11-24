module com.main.elevatorsimulator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.main.elevatorsimulator to javafx.fxml;
    exports com.main.elevatorsimulator.structure;
    opens com.main.elevatorsimulator.structure to javafx.fxml;
    exports com.main.elevatorsimulator.main;
    opens com.main.elevatorsimulator.main to javafx.fxml;
    exports com.main.elevatorsimulator;
}
