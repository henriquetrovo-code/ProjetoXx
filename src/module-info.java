module ProjetoX {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;

    opens view to javafx.fxml;

    exports view;
    exports application;
}
