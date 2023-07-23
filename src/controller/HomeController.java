package controller;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import application.Login;
import application.NewCourse;
import application.Room;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import model.SecondSemesterTableDB;
import model.RoomTable;
import model.RoomTableContents;
import model.ScheduleDB;
import model.ScheduleTable;
import model.ScheduleTableContents;
import model.SecondSemesterTableContents;
import model.Temp;
import model.SecondSemesterTable;


public class HomeController implements Initializable {
	//ui components
	@FXML
    private MFXButton add_btn;

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
    private MFXButton load_tt_btn;

    @FXML
    private MFXButton loadtable_btn;

    @FXML
    private MFXTableView<RoomTableContents> roomtable;

    @FXML
    private AnchorPane roomtable_pane;

    @FXML
    private MFXTableView<ScheduleTableContents> scheduledtable;

    @FXML
    private AnchorPane scheduledtable_pane;
    
    @FXML
    private MFXComboBox<Integer> sel_studentLevel_cbox;	
    
    @FXML
    private MFXComboBox<String> sel_dept_cbox;

    @FXML
    private MFXComboBox<String> selectTable_cbox;

    @FXML
    private MFXButton swap_btn;
    
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
    
    @FXML
    private HBox ttoptionbox;

    @FXML
    private MFXButton update_btn;
    
    @FXML
    private MFXTextField current_table_name;
    
    @FXML
    private MFXComboBox<String> connectionType_cbox;
    
    @FXML
    private MFXTextField connectionUrl_field;
    
    @FXML
    private MFXScrollPane database_pane;
	
    
    
    
 
    //combo_box items
    ObservableList<String> table_list = FXCollections.observableArrayList("Course Table","Scheduled Table","Room Table");
    ObservableList<Integer> level_list = FXCollections.observableArrayList(100,200,300,400);
    ObservableList<String> dept_list = FXCollections.observableArrayList(SecondSemesterTableDB.getDepartment());
        
    
    
    
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	//initialize
    		welcome_message.setText("Welcome, "+Login.Username());
    		//tableviews
			
			
			
			//combo-box
			selectTable_cbox.setItems(table_list);
			sel_dept_cbox.setItems(dept_list);
			sel_studentLevel_cbox.setItems(level_list);
		
//			timetable_grid.add(add_btn, 1, 1, 2, 1);
//			timetable_grid.add(currenttab_label, 1, 2, 3, 1);
////			timetable_grid.add(welcome_message, 0, 2, 1, 1);
////			timetable_grid.add(currenttab_icon, 1, 1, 2, 1);
////			ColumnConstraints col1 = new ColumnConstraints();
////			col1.setPercentWidth(40);
////			ColumnConstraints col2 = new ColumnConstraints();
////			col1.setPercentWidth(10);
////			ColumnConstraints col3 = new ColumnConstraints();
////			col1.setPercentWidth(50);
////			timetable_grid.getColumnConstraints().addAll(col3);
	}
    
    
  
    	//methods

	

	
	//adding functions to ui components
	//clickables(navigation panes)----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@FXML
    void back(ActionEvent event) {
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
		database_pane.setVisible(false);
		//switch option boxes
		ttoptionbox.setVisible(true);
		tableoption_hbox.setVisible(false);
		
		
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
		database_pane.setVisible(false);
		//switch option boxes
		tableoption_hbox.setVisible(true);
		ttoptionbox.setVisible(false);
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
		database_pane.setVisible(false);
		//switch option boxes
		tableoption_hbox.setVisible(false);
		ttoptionbox.setVisible(false);
    }
	
	@FXML
    void database_tab_onclick(MouseEvent event) {
		//set the current tab name
		currenttab_label.setText("Database");
		
		//switch panes
		home.setVisible(false);
		timetable.setVisible(true);
		database_pane.setVisible(true);
		
		
    }
	
	//buttons
	@FXML
    void loadnewtable(ActionEvent event) {
		String currentItem = selectTable_cbox.getSelectedItem();
		switch(currentItem) {
			case "Course Table":
				coursetable.getTableColumns().clear();
				setupTableView_courseTable();	
				coursetable_pane.setVisible(true);
				scheduledtable_pane.setVisible(false);
				roomtable_pane.setVisible(false);
				current_table_name.setText("Course Table");
				break;
			case "Scheduled Table":
				scheduledtable.getTableColumns().clear();
				setupTableView_scheduleTable();
				coursetable_pane.setVisible(false);
				scheduledtable_pane.setVisible(true);
				roomtable_pane.setVisible(false);
				current_table_name.setText("Scheduled Table");
				break;
			case "Room Table":
				roomtable.getTableColumns().clear();
				setupTableView_roomTable();
				coursetable_pane.setVisible(false);
				scheduledtable_pane.setVisible(false);
				roomtable_pane.setVisible(true);
				current_table_name.setText("Room Table");
				break;
		}
    }
	
	@FXML
    void update_db(ActionEvent event) {
		String $table_name =  current_table_name.getText();
		switch($table_name){
			case "Course Table":
				new NewCourse();
				break;
			case "Schedule Table":
				break;
			case "Room Table":
				new Room();
				break;
			default:
				break;
		}
    }
	
	@FXML
    void swap(ActionEvent event) {

    }
	
	@FXML
    void add_db(ActionEvent event) {
		String $table_name =  current_table_name.getText();
		switch($table_name){
			case "Course Table":
				new NewCourse();
				break;
			case "Schedule Table":
				break;
			case "Room Table":
				new Room();
				break;
			default:
				break;
		}
    }
	 
	@FXML
    void loadtt(ActionEvent event) {
		ScheduleDB.displaySchedule(timetable_grid,sel_dept_cbox.getSelectedItem(),sel_studentLevel_cbox.getSelectedItem());
    }
	
	@FXML
    void schedule(ActionEvent event) {

    }
	 
//	boolean doPrint(Node n) {
//		PrinterJob job = PrinterJob.createPrinterJob();
//		if(job == null)return false;
//		if(!job.showPrintDialog(null))return false;
//		if(!job.printPage(n))return false;
//		return job.endJob();
//	}
	
//	private void printImage(Node node) {
//	    Printer printer = Printer.getDefaultPrinter();
//	    PageLayout pageLayout = printer.getDefaultPageLayout();
//
//	    // Printable area
//	    double pWidth = pageLayout.getPrintableWidth();
//	    double pHeight = pageLayout.getPrintableHeight();
//
//	    // Node's (Image) dimensions
//	    double nWidth = node.getBoundsInParent().getWidth();
//	    double nHeight = node.getBoundsInParent().getHeight();
//
//	    // How much space is left? Or is the image to big?
//	    double widthLeft = pWidth - nWidth;
//	    double heightLeft = pHeight - nHeight;
//
//	    // scale the image to fit the page in width, height or both
//	    double scale;
//
//	    if (widthLeft < heightLeft) scale = pWidth / nWidth;
//	    else scale = pHeight / nHeight;
//
//	    // preserve ratio (both values are the same)
//	    node.getTransforms().add(new Scale(scale, scale));
//
//	    PrinterJob job = PrinterJob.createPrinterJob();
//	    if (job != null) {
//	        boolean success = job.printPage(node);
//	        if (success) {
//	            System.out.println("PRINTING FINISHED");
//	            job.endJob();
//	        }
//	    }
//	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//table contents
    ObservableList<SecondSemesterTableContents> list_Controller;
    ObservableList<ScheduleTableContents> list_Controller_scheduleTable;
    ObservableList<RoomTableContents> list_Controller_roomTable;
    
    
  //table(coursetable)
  	@SuppressWarnings("unchecked")
  	private void setupTableView_roomTable() {
  		MFXTableColumn<RoomTableContents> idColumn = new MFXTableColumn<>("ID", true, Comparator.comparing(RoomTableContents::getID));
  		MFXTableColumn<RoomTableContents> capacityColumn = new MFXTableColumn<>("Capacity", true, Comparator.comparing(RoomTableContents::getSize));
  		MFXTableColumn<RoomTableContents> bnameColumn = new MFXTableColumn<>("Building Name", true, Comparator.comparing(RoomTableContents::getBuilding_Name));
  		MFXTableColumn<RoomTableContents> rnameColumn = new MFXTableColumn<>("Room Name", true, Comparator.comparing(RoomTableContents::getRoom_Name));

  		

  		idColumn.setRowCellFactory(list_Controller_roomTable -> new MFXTableRowCell<>(RoomTableContents::getID));
  		capacityColumn.setRowCellFactory(list_Controller_roomTable -> new MFXTableRowCell<>(RoomTableContents::getSize));
  		bnameColumn.setRowCellFactory(list_Controller_roomTable -> new MFXTableRowCell<>(RoomTableContents::getBuilding_Name));
  		rnameColumn.setRowCellFactory(list_Controller_roomTable -> new MFXTableRowCell<>(RoomTableContents::getRoom_Name));
  		
  		

  		roomtable.getTableColumns().addAll(idColumn, bnameColumn, rnameColumn, capacityColumn);
  		roomtable.getFilters().addAll(
  				new StringFilter<>("Room Name", RoomTableContents::getRoom_Name),
  				new StringFilter<>("Building Name", RoomTableContents::getBuilding_Name)
  		);
  		
  		list_Controller_roomTable=RoomTable.getDatabaseTable();
  		roomtable.setItems(list_Controller_roomTable);
  	}
    
	//table(coursetable)
	@SuppressWarnings("unchecked")
	private void setupTableView_courseTable() {
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
	private void setupTableView_scheduleTable() {
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


}
