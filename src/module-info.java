module Scheduler {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires MaterialFX;
	requires java.sql;
	requires de.jensd.fx.glyphs.fontawesome;
	requires javafx.base;
	requires commons.configuration;
	requires java.desktop;
	requires org.apache.poi.ooxml;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controller to javafx.graphics, javafx.fxml;
	opens model to javafx.base;
}
