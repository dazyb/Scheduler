package controller;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import application.Login;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;

import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.ScheduleDB;
import model.ScheduleTable;
import model.ScheduleTableContents;
import model.SecondSemesterTableContents;
import model.SecondSemesterTable;


public class HomeController implements Initializable {
	//ui components
	@FXML
    private Pane backArrow_pane;

    @FXML
    private FontAwesomeIconView back_arrow;

    @FXML
    private FontAwesomeIconView back_arrow1;

    @FXML
    private FontAwesomeIconView close;

    @FXML
    private MFXTableView<SecondSemesterTableContents> coursetable;

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
    private MFXButton loadtable_btn;

    @FXML
    private MFXTableView<?> roomtable;

    @FXML
    private AnchorPane roomtable_pane;

    @FXML
    private MFXTableView<ScheduleTableContents> scheduledtable;

    @FXML
    private AnchorPane scheduledtable_pane;

    @FXML
    private MFXComboBox<String> selectTable_cbox;

    @FXML
    private AnchorPane settings_tab;

    @FXML
    private GridPane structure_gridPane;

    @FXML
    private AnchorPane structure_tab;

    @FXML
    private AnchorPane table_tab;

    @FXML
    private HBox tableoption_hbox;

    @FXML
    private AnchorPane timetable;

    @FXML
    private GridPane timetable_grid;

    @FXML
    private AnchorPane timetable_tab;

    @FXML
    private Label welcome_message;
	
    
    
    
    
    
    //combo_box items
    ObservableList<String> table_list = FXCollections.observableArrayList("Course Table","Scheduled Table","Room Table");
        
    
    
    
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	//initialize
    	welcome_message.setText("Welcome, "+Login.Username());
		setupTableView();	
		setupTableView_Schedule();
		selectTable_cbox.setItems(table_list);
	}
    
  
  //table contents
    ObservableList<SecondSemesterTableContents> list_Controller;
    ObservableList<ScheduleTableContents> list_Controller_scheduleTable;
    
//	int index=-1;
//	Connection conn=null;
//	ResultSet rs=null;
//	PreparedStatement pst=null;
	
	
	
	//table(coursetable)
	@SuppressWarnings("unchecked")
	private void setupTableView() {
		MFXTableColumn<SecondSemesterTableContents> idColumn = new MFXTableColumn<>("ID", true, Comparator.comparing(SecondSemesterTableContents::getID));
		MFXTableColumn<SecondSemesterTableContents> coursenameColumn = new MFXTableColumn<>("Course Name", true, Comparator.comparing(SecondSemesterTableContents::getCourseName));
		MFXTableColumn<SecondSemesterTableContents> ccColumn = new MFXTableColumn<>("Course Code", true, Comparator.comparing(SecondSemesterTableContents::getCourseCode));
		MFXTableColumn<SecondSemesterTableContents> deptColumn = new MFXTableColumn<>("Department", true, Comparator.comparing(SecondSemesterTableContents::getDepartment));
		MFXTableColumn<SecondSemesterTableContents> lnameColumn = new MFXTableColumn<>("Lecturer Name", true, Comparator.comparing(SecondSemesterTableContents::getLecturerName));
		MFXTableColumn<SecondSemesterTableContents> liColumn = new MFXTableColumn<>("Lecturer Initials", true, Comparator.comparing(SecondSemesterTableContents::getLecturerInitials));
		MFXTableColumn<SecondSemesterTableContents> noColumn = new MFXTableColumn<>("Number Of Students", true, Comparator.comparing(SecondSemesterTableContents::getNumberOfStudents));
		MFXTableColumn<SecondSemesterTableContents> progColumn = new MFXTableColumn<>("Programme", true, Comparator.comparing(SecondSemesterTableContents::getProgramme));
		MFXTableColumn<SecondSemesterTableContents> groupColumn = new MFXTableColumn<>("Groupings", true, Comparator.comparing(SecondSemesterTableContents::getGroupings));
		

		idColumn.setRowCellFactory(list_Controller -> new MFXTableRowCell<>(SecondSemesterTableContents::getID));
		coursenameColumn.setRowCellFactory(list_Controller -> new MFXTableRowCell<>(SecondSemesterTableContents::getCourseName));
		ccColumn.setRowCellFactory(list_Controller -> new MFXTableRowCell<>(SecondSemesterTableContents::getCourseCode));
		deptColumn.setRowCellFactory(list_Controller -> new MFXTableRowCell<>(SecondSemesterTableContents::getDepartment));
		lnameColumn.setRowCellFactory(list_Controller -> new MFXTableRowCell<>(SecondSemesterTableContents::getLecturerName));
		liColumn.setRowCellFactory(list_Controller -> new MFXTableRowCell<>(SecondSemesterTableContents::getLecturerInitials));
		noColumn.setRowCellFactory(list_Controller -> new MFXTableRowCell<>(SecondSemesterTableContents::getNumberOfStudents));
		progColumn.setRowCellFactory(list_Controller -> new MFXTableRowCell<>(SecondSemesterTableContents::getProgramme));
		groupColumn.setRowCellFactory(list_Controller -> new MFXTableRowCell<>(SecondSemesterTableContents::getGroupings));
		
		

		coursetable.getTableColumns().addAll(idColumn, coursenameColumn, ccColumn, deptColumn, lnameColumn, liColumn, noColumn, progColumn, groupColumn);
		coursetable.getFilters().addAll(
				new StringFilter<>("Course Name", SecondSemesterTableContents::getCourseName),
				new StringFilter<>("Department", SecondSemesterTableContents::getDepartment),
				new StringFilter<>("Course Code", SecondSemesterTableContents::getCourseCode),
				new StringFilter<>("Lecturer Name", SecondSemesterTableContents::getLecturerName)
		);
		
		list_Controller=SecondSemesterTable.getDatabaseTable();
		coursetable.setItems(list_Controller);
	}
	
	
	//table(Schedule)
	
	@SuppressWarnings("unchecked")
	private void setupTableView_Schedule() {
		MFXTableColumn<ScheduleTableContents> idColumn = new MFXTableColumn<>("ID", true, Comparator.comparing(ScheduleTableContents::getID));
		MFXTableColumn<ScheduleTableContents> coursenameColumn = new MFXTableColumn<>("Course Name", true, Comparator.comparing(ScheduleTableContents::getCourseName));
		MFXTableColumn<ScheduleTableContents> ccColumn = new MFXTableColumn<>("Course Code", true, Comparator.comparing(ScheduleTableContents::getCourseCode));
		MFXTableColumn<ScheduleTableContents> deptColumn = new MFXTableColumn<>("Department", true, Comparator.comparing(ScheduleTableContents::getDept));
		MFXTableColumn<ScheduleTableContents> lnameColumn = new MFXTableColumn<>("Lecturer Name", true, Comparator.comparing(ScheduleTableContents::getLecturerName));
		MFXTableColumn<ScheduleTableContents> liColumn = new MFXTableColumn<>("Lecturer Initials", true, Comparator.comparing(ScheduleTableContents::getLecturerInitials));
		MFXTableColumn<ScheduleTableContents> timeColumn = new MFXTableColumn<>("Time Allocated", true, Comparator.comparing(ScheduleTableContents::getTimeAllocated));
		MFXTableColumn<ScheduleTableContents> roomNameColumn = new MFXTableColumn<>("Programme", true, Comparator.comparing(ScheduleTableContents::getRoomName));
		

		idColumn.setRowCellFactory(list_Controller_scheduleTable -> new MFXTableRowCell<>(ScheduleTableContents::getID));
		coursenameColumn.setRowCellFactory(list_Controller_scheduleTable -> new MFXTableRowCell<>(ScheduleTableContents::getCourseName));
		ccColumn.setRowCellFactory(list_Controller_scheduleTable -> new MFXTableRowCell<>(ScheduleTableContents::getCourseCode));
		deptColumn.setRowCellFactory(list_Controller_scheduleTable -> new MFXTableRowCell<>(ScheduleTableContents::getDept));
		lnameColumn.setRowCellFactory(list_Controller_scheduleTable -> new MFXTableRowCell<>(ScheduleTableContents::getLecturerName));
		liColumn.setRowCellFactory(list_Controller_scheduleTable -> new MFXTableRowCell<>(ScheduleTableContents::getLecturerInitials));
		timeColumn.setRowCellFactory(list_Controller_scheduleTable -> new MFXTableRowCell<>(ScheduleTableContents::getTimeAllocated));
		roomNameColumn.setRowCellFactory(list_Controller_scheduleTable -> new MFXTableRowCell<>(ScheduleTableContents::getRoomName));
		
		

		scheduledtable.getTableColumns().addAll(idColumn, coursenameColumn, ccColumn, deptColumn, lnameColumn, liColumn, timeColumn, roomNameColumn);
		scheduledtable.getFilters().addAll(
				new StringFilter<>("Course Name", ScheduleTableContents::getCourseName),
				new StringFilter<>("Department", ScheduleTableContents::getDept),
				new StringFilter<>("Course Code", ScheduleTableContents::getCourseCode),
				new StringFilter<>("Lecturer Name", ScheduleTableContents::getLecturerName),
				new StringFilter<>("Time Allocated", ScheduleTableContents::getTimeAllocated )
		);
		
		list_Controller_scheduleTable=ScheduleTable.getScheduleTable();
		scheduledtable.setItems(list_Controller_scheduleTable);
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
		currenttab_label.setText("TimeTable");
		//switch panes(stackpane)
		home.setVisible(false);
		timetable.setVisible(true);
		timetable_grid.setVisible(true);
		tableoption_hbox.setVisible(false);
		
		//display contents in gridPane
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
		scheduledtable_pane.setVisible(false);
		
		//top option
		tableoption_hbox.setVisible(true);
    }
	
	@FXML
    void structuretab_onclick(MouseEvent event) {
		//set the current tab name
		currenttab_label.setText("Structure");
		//switch panes(stackpane)
		home.setVisible(false);
		timetable.setVisible(true);
		timetable_grid.setVisible(false);
		coursetable_pane.setVisible(false);
		scheduledtable_pane.setVisible(false);
		structure_gridPane.setVisible(true);
		
		//top option
		tableoption_hbox.setVisible(false);
    }
	
	
	//buttons
	@FXML
    void loadnewtable(ActionEvent event) {
		String currentItem = selectTable_cbox.getSelectedItem();
		switch(currentItem) {
			case "Course Table":
				coursetable_pane.setVisible(true);
				scheduledtable.setVisible(false);
				roomtable_pane.setVisible(false);
				break;
			case "Scheduled Table":
				coursetable_pane.setVisible(false);
				scheduledtable_pane.setVisible(true);
				roomtable_pane.setVisible(false);
				break;
			case "Room Table":
				coursetable_pane.setVisible(false);
				scheduledtable_pane.setVisible(false);
				roomtable_pane.setVisible(true);
				break;
		}
    }
	
	



}
