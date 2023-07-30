package controller;

import java.net.URL;



import java.util.ResourceBundle;

import org.apache.commons.configuration.ConfigurationException;

import application.Login;
import application.NewCourse;
import application.Room;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.SecondSemesterTableDB;
import model.Temp;
import model.RoomTableContents;
import model.RoomTableDB;
import model.ScheduleTableDB;
import model.ScheduleTableContents;
import model.SecondSemesterTableContents;




public class HomeController implements Initializable {
	//ui components
	@FXML
    private MFXButton add_btn;

    @FXML
    private FontAwesomeIconView close;

    @FXML
    private TableView<SecondSemesterTableContents> coursetable;

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
    private TableView<RoomTableContents> roomtable;

    @FXML
    private AnchorPane roomtable_pane;

    @FXML
    private TableView<ScheduleTableContents> scheduledtable;

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
    
    @FXML
    private TableColumn<RoomTableContents, Integer> roomTable_columnID;
    
    @FXML
    private TableColumn<RoomTableContents, String> roomTable_columnbname;
    
    @FXML
    private TableColumn<RoomTableContents, String> roomTable_columnrname;
    
    @FXML
    private TableColumn<RoomTableContents, Integer> roomTable_columncapacity;
    
    @FXML
    private TableColumn<SecondSemesterTableContents, Integer> courseTable_columnID;
    
    @FXML
    private TableColumn<SecondSemesterTableContents, String> courseTable_columncourseName;
    
    @FXML
    private TableColumn<SecondSemesterTableContents, String> courseTable_columncourseCode;
    
    @FXML
    private TableColumn<SecondSemesterTableContents, String> courseTable_columnlname;
    
    @FXML
    private TableColumn<SecondSemesterTableContents, String> courseTable_columnlinitials;
    
    @FXML
    private TableColumn<SecondSemesterTableContents, String> courseTable_columndept;
    
    @FXML
    private TableColumn<SecondSemesterTableContents, Integer> courseTable_columnnoStudents;
    
    @FXML
    private TableColumn<SecondSemesterTableContents, Integer> courseTable_columnstudentLevel;

    @FXML
    private TableColumn<SecondSemesterTableContents, String> courseTable_columnprogramme;
    
    @FXML
    private TableColumn<SecondSemesterTableContents, String> courseTable_columngroup;

    @FXML
    private TableColumn<ScheduleTableContents, Integer> scheduledtable_columnID;
    
    @FXML
    private TableColumn<ScheduleTableContents, String> scheduledtable_columncourseName;
    
    @FXML
    private TableColumn<ScheduleTableContents, String> scheduledtable_columncourseCode;
    
    @FXML
    private TableColumn<ScheduleTableContents, String> scheduledtable_columnlname;
    
    @FXML
    private TableColumn<ScheduleTableContents, String> scheduledtable_columnlinitials;
    
    @FXML
    private TableColumn<ScheduleTableContents, String> scheduledtable_columnrname;
    
    @FXML
    private TableColumn<ScheduleTableContents, Integer> scheduledtable_columntAllocated;
    
    @FXML
    private TableColumn<ScheduleTableContents, String> scheduledtable_columndept;
    
    @FXML
    private TableColumn<ScheduleTableContents, Integer> scheduledtable_columnlevel;
    

    //combo_box items
    ObservableList<String> table_list = FXCollections.observableArrayList("Course Table","Scheduled Table","Room Table");
    ObservableList<Integer> level_list = FXCollections.observableArrayList(100,200,300,400);
    ObservableList<String> dept_list = FXCollections.observableArrayList(SecondSemesterTableDB.getDepartment());
    ObservableList<String> type_list =FXCollections.observableArrayList("MSSQL","MySQL");
        
    
    
    
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	//initialize
    		welcome_message.setText("Welcome, "+Login.Username());
			//combo-box
			selectTable_cbox.setItems(table_list);
			sel_dept_cbox.setItems(dept_list);
			sel_studentLevel_cbox.setItems(level_list);
			
			
			connectionUrl_field.setText(Temp.configuration().getProperty("jdbcUrl").toString());
			connectionType_cbox.setItems(type_list);
			
		
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
				setupTableView_courseTable();	
				coursetable_pane.setVisible(true);
				scheduledtable_pane.setVisible(false);
				roomtable_pane.setVisible(false);
				current_table_name.setText("Course Table");
				break;
			case "Scheduled Table":
				coursetable.getItems().clear();
				roomtable.getItems().clear();
				setupTableView_scheduleTable();
				coursetable_pane.setVisible(false);
				scheduledtable_pane.setVisible(true);
				roomtable_pane.setVisible(false);
				current_table_name.setText("Scheduled Table");
				break;
			case "Room Table":
				coursetable.getItems().clear();
				scheduledtable.getItems().clear();
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
		Temp.setProperty("ButtonStatus", "Update");
		String ID;
		switch($table_name){
			case "Course Table":
				ID = String.valueOf(coursetable.getSelectionModel().getSelectedItem().getID());
				Temp.setProperty("ID", ID);
				new NewCourse();
			case "Schedule Table":
				break;
			case "Room Table":
				ID = String.valueOf(roomtable.getSelectionModel().getSelectedItem().getID());
				Temp.setProperty("ID", ID);
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
		Temp.setProperty("ButtonStatus", "Add");
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
		ScheduleTableDB.displaySchedule(timetable_grid,sel_dept_cbox.getSelectedItem(),sel_studentLevel_cbox.getSelectedItem());
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
  	private void setupTableView_roomTable() {
  		roomTable_columnID.setCellValueFactory(new PropertyValueFactory<RoomTableContents,Integer>("ID"));
  		roomTable_columnbname.setCellValueFactory(new PropertyValueFactory<RoomTableContents,String>("Building_Name"));
  		roomTable_columnrname.setCellValueFactory(new PropertyValueFactory<RoomTableContents,String>("Room_Name"));
  		roomTable_columncapacity.setCellValueFactory(new PropertyValueFactory<RoomTableContents,Integer>("Size"));

  		list_Controller_roomTable=RoomTableDB.getDatabaseTable();
  		roomtable.setItems(list_Controller_roomTable);
  	}   
	//table(coursetable)
	private void setupTableView_courseTable() {
		courseTable_columnID.setCellValueFactory(new PropertyValueFactory<SecondSemesterTableContents,Integer>("ID"));
		courseTable_columncourseName.setCellValueFactory(new PropertyValueFactory<SecondSemesterTableContents,String>("CourseName"));
		courseTable_columncourseCode.setCellValueFactory(new PropertyValueFactory<SecondSemesterTableContents,String>("CourseCode"));
		courseTable_columnlname.setCellValueFactory(new PropertyValueFactory<SecondSemesterTableContents,String>("LecturerName"));
		courseTable_columnlinitials.setCellValueFactory(new PropertyValueFactory<SecondSemesterTableContents,String>("LecturerInitials"));
		courseTable_columndept.setCellValueFactory(new PropertyValueFactory<SecondSemesterTableContents,String>("Department"));
		courseTable_columnnoStudents.setCellValueFactory(new PropertyValueFactory<SecondSemesterTableContents,Integer>("numberOfStudents"));
		courseTable_columnprogramme.setCellValueFactory(new PropertyValueFactory<SecondSemesterTableContents,String>("Programme"));
		courseTable_columngroup.setCellValueFactory(new PropertyValueFactory<SecondSemesterTableContents,String>("Groupings"));
		courseTable_columnstudentLevel.setCellValueFactory(new PropertyValueFactory<SecondSemesterTableContents,Integer>("level"));
		
		list_Controller=SecondSemesterTableDB.getDatabaseTable();
		coursetable.setItems(list_Controller);
	}	
	//table(Schedule)
	private void setupTableView_scheduleTable() {
		scheduledtable_columnID.setCellValueFactory(new PropertyValueFactory<ScheduleTableContents,Integer>("ID"));
		scheduledtable_columncourseName.setCellValueFactory(new PropertyValueFactory<ScheduleTableContents,String>("courseName"));
		scheduledtable_columncourseCode.setCellValueFactory(new PropertyValueFactory<ScheduleTableContents,String>("courseCode"));
		scheduledtable_columnlname.setCellValueFactory(new PropertyValueFactory<ScheduleTableContents,String>("lecturerName"));
		scheduledtable_columnlinitials.setCellValueFactory(new PropertyValueFactory<ScheduleTableContents,String>("lecturerInitials"));
		scheduledtable_columnrname.setCellValueFactory(new PropertyValueFactory<ScheduleTableContents,String>("roomName"));
		scheduledtable_columntAllocated.setCellValueFactory(new PropertyValueFactory<ScheduleTableContents,Integer>("timeAllocated"));
		scheduledtable_columndept.setCellValueFactory(new PropertyValueFactory<ScheduleTableContents,String>("dept"));
		scheduledtable_columnlevel.setCellValueFactory(new PropertyValueFactory<ScheduleTableContents,Integer>("studentLevel"));
		
		list_Controller_scheduleTable=ScheduleTableDB.getScheduleTable();
		scheduledtable.setItems(list_Controller_scheduleTable);
	}
}
