package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Comparator;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import model.ScheduleDB;
import model.TContents;
import model.Table;


public class HomeController implements Initializable {
	//ui components
	@FXML
    private Pane backArrow_pane;

    @FXML
    private FontAwesomeIconView back_arrow;

    @FXML
    private FontAwesomeIconView close;

    @FXML
    private MFXTableView<TContents> coursetable;

    @FXML
    private AnchorPane coursetable_pane;

    @FXML
    private FontAwesomeIconView currenttab_icon;

    @FXML
    private Label currenttab_label;

    @FXML
    private AnchorPane db_tab;

    @FXML
    private AnchorPane home;

    @FXML
    private MFXTableView<?> scheduledtable;

    @FXML
    private AnchorPane scheduledtable_pane;

    @FXML
    private AnchorPane settings_tab;

    @FXML
    private AnchorPane table_tab;

    @FXML
    private AnchorPane timetable;

    @FXML
    private GridPane timetable_grid;

    @FXML
    private AnchorPane timetable_tab;

    @FXML
    private Label welcome_message;
    
    @FXML
    private Label label;
	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setupTableView();	
	}
    
  
  //table contents
    ObservableList<TContents> list_Controller;
	int index=-1;
	Connection conn=null;
	ResultSet rs=null;
	PreparedStatement pst=null;
	
	
	
	//table(coursetable)
	@SuppressWarnings("unchecked")
	private void setupTableView() {
		MFXTableColumn<TContents> idColumn = new MFXTableColumn<>("ID", true, Comparator.comparing(TContents::getID));
		MFXTableColumn<TContents> coursenameColumn = new MFXTableColumn<>("Course Name", true, Comparator.comparing(TContents::getCourseName));
		MFXTableColumn<TContents> ccColumn = new MFXTableColumn<>("Course Code", true, Comparator.comparing(TContents::getCourseCode));
		MFXTableColumn<TContents> deptColumn = new MFXTableColumn<>("Department", true, Comparator.comparing(TContents::getDepartment));
		MFXTableColumn<TContents> lnameColumn = new MFXTableColumn<>("Lecturer Name", true, Comparator.comparing(TContents::getLecturerName));
		MFXTableColumn<TContents> liColumn = new MFXTableColumn<>("Lecturer Initials", true, Comparator.comparing(TContents::getLecturerInitials));
		MFXTableColumn<TContents> noColumn = new MFXTableColumn<>("Number Of Students", true, Comparator.comparing(TContents::getNumberOfStudents));
		MFXTableColumn<TContents> progColumn = new MFXTableColumn<>("Programme", true, Comparator.comparing(TContents::getProgramme));
		MFXTableColumn<TContents> groupColumn = new MFXTableColumn<>("Groupings", true, Comparator.comparing(TContents::getGroupings));
		

		idColumn.setRowCellFactory(list_Controller -> new MFXTableRowCell<>(TContents::getID));
		coursenameColumn.setRowCellFactory(list_Controller -> new MFXTableRowCell<>(TContents::getCourseName));
		ccColumn.setRowCellFactory(list_Controller -> new MFXTableRowCell<>(TContents::getCourseCode));
		deptColumn.setRowCellFactory(list_Controller -> new MFXTableRowCell<>(TContents::getDepartment));
		lnameColumn.setRowCellFactory(list_Controller -> new MFXTableRowCell<>(TContents::getLecturerName));
		liColumn.setRowCellFactory(list_Controller -> new MFXTableRowCell<>(TContents::getLecturerInitials));
		noColumn.setRowCellFactory(list_Controller -> new MFXTableRowCell<>(TContents::getNumberOfStudents));
		progColumn.setRowCellFactory(list_Controller -> new MFXTableRowCell<>(TContents::getProgramme));
		groupColumn.setRowCellFactory(list_Controller -> new MFXTableRowCell<>(TContents::getGroupings));
		
		

		coursetable.getTableColumns().addAll(idColumn, coursenameColumn, ccColumn, deptColumn, lnameColumn, liColumn, noColumn, progColumn, groupColumn);
		coursetable.getFilters().addAll(
				new StringFilter<>("Course Name", TContents::getCourseName),
				new StringFilter<>("Department", TContents::getDepartment),
				new StringFilter<>("Course Code", TContents::getCourseCode),
				new StringFilter<>("Lecturer Name", TContents::getLecturerName)
		);
		
		list_Controller=Table.getDatabaseTable();
		coursetable.setItems(list_Controller);
	}
	
	//methods

	

	
	//adding functions to ui components
	//clickables(navigation panes)----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@FXML
    void backarrow_onclick(MouseEvent event) {
		//goes back to home page
		home.setVisible(true);
		timetable.setVisible(false);
    }
	
	@FXML
    void timetable_tab_onclick(MouseEvent event) {
		//set the current tab name
		currenttab_label.setText("TimeTables");
		
		//switch panes(stackpane)
		home.setVisible(false);
		timetable.setVisible(true);
		timetable_grid.setVisible(true);
		ScheduleDB.displaySchedule(timetable_grid);
		
	}
	
	@FXML
    void tabletab_onclick(MouseEvent event) {
		//set the current tab name
		currenttab_label.setText("Tables");
		
		//switch panes(stackpane)
		home.setVisible(false);
		timetable.setVisible(true);
		timetable_grid.setVisible(false);
		coursetable_pane.setVisible(true);
    }
	
	



}
