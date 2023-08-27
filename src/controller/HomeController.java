package controller;

import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import model.SecondSemesterTableDB;
import model.CoursesExport;
import model.DBConfig;
import model.Faculty;
import model.TimetableGA;
import model.RoomTableContents;
import model.RoomTableDB;
import model.RoomsExport;
import model.ScheduleTableDB;
import model.ScheduledExports;
import model.Search;
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
    private MFXButton export_btn;   
    @FXML
    private MFXButton schedule_btn;   
    @FXML
    private AnchorPane settings_tab;
    @FXML
    private MFXScrollPane structure_pane;
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
//    @FXML
//    private TableColumn<SecondSemesterTableContents, String> courseTable_columncourseName;    
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
//    @FXML
//    private TableColumn<ScheduleTableContents, String> scheduledtable_columncourseName;    
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
    @FXML
    private StackPane contents_stackpane;
    @FXML
    private HBox hbox;
    @FXML
    private MFXTextField searchbar;
    @FXML
    private MFXComboBox<String> college_cbox;
    @FXML
    private MFXComboBox<String> faculty_cbox;
    @FXML
    private MFXComboBox<String> dept_cbox;
    @FXML
    private HBox down_hbox;
    @FXML
    private MFXButton excel_path_save;
    @FXML
    private MFXTextField excel_path;



    

    
    //combo_box items
    ObservableList<String> table_list = FXCollections.observableArrayList("Course Table","Scheduled Table","Room Table");
    ObservableList<Integer> level_list = FXCollections.observableArrayList(100,200,300,400);
    ObservableList<String> dept_list = FXCollections.observableArrayList(Faculty.get_Department(DBConfig.configuration().getProperty("faculty").toString()));
    ObservableList<String> col_list = FXCollections.observableArrayList(Faculty.get_College());
    ObservableList<String> fal_list = FXCollections.observableArrayList(Faculty.get_Faculty());
    
//    ObservableList<String> type_list =FXCollections.observableArrayList("MSSQL","MySQL");
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	//initialize
    		welcome_message.setText("Welcome, "+Login.Username());
			//combo-box
			selectTable_cbox.setItems(table_list);
			sel_dept_cbox.setItems(dept_list);
			sel_studentLevel_cbox.setItems(level_list);
			
			college_cbox.setItems(col_list);
			
			DBConfig.setProperty("random", college_cbox.getSelectedItem());
			//excel_path.setText(DBConfig.configuration().getProperty("exportPath").toString());

	}
    

    
  
    //methods

	

	
	//adding functions to ui components
	//clickables(navigation panes)----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@FXML
    void excelPath_save(ActionEvent event) {
		DBConfig.setProperty("exportPath", excel_path.getText());
		JOptionPane.showMessageDialog(null, "Path Saved Succefully");
    }
	
	@FXML
    void export(ActionEvent event) throws SQLException, IOException {
		String getTable = current_table_name.getText();
		String getPath = DBConfig.configuration().getProperty("exportPath").toString();
		switch(getTable) {
			case "Course Table":
				CoursesExport.export_courses(getPath);
				break;
			case "Schedule Table":
				ScheduledExports.export_courses(getPath);
				break;
			case "Rooms Table":
				RoomsExport.export_courses(getPath);
				break;
		}
    }
	
	@FXML
    void search(KeyEvent event) {
		if(event.getCode().equals(KeyCode.ENTER)) {
			String table_name = current_table_name.getText();
			String search_key = searchbar.getText();
			if(searchbar.getLength()!=0) {
				switch(table_name) {
				case "Course Table":
					coursetable.setItems(Search.search_semester("SecondSemester", search_key));
					break;
				case "Scheduled Table":
					scheduledtable.setItems(Search.search_scheduled("Schedule", search_key));
					break;
				case "Room Table":
					roomtable.setItems(Search.search_room("Rooms", search_key));
					break;
				}
			}else {
				JOptionPane.showMessageDialog(null, "Please A Search Key");
			}
		}
    }
	
	
	
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
		contents_stackpane.setVisible(true);
		structure_pane.setVisible(false);
		coursetable_pane.setVisible(false);
		scheduledtable_pane.setVisible(false);
		roomtable_pane.setVisible(false);
		//switch option boxes
		ttoptionbox.setVisible(true);
		tableoption_hbox.setVisible(false);
		down_hbox.setVisible(false);

	}
	
	@FXML
    void tabletab_onclick(MouseEvent event) {
		//set the current tab name
		currenttab_label.setText("Tables");
		//switch panes(stackpane)
		home.setVisible(false);
		timetable.setVisible(true);
		contents_stackpane.setVisible(false);
//		timetable_grid.setVisible(false);
		coursetable_pane.setVisible(true);
		structure_pane.setVisible(false);
		//switch option boxes
		tableoption_hbox.setVisible(true);
		down_hbox.setVisible(true);
		ttoptionbox.setVisible(false);
    }
	
	@FXML
    void structuretab_onclick(MouseEvent event) {
		//set the current tab name
		currenttab_label.setText("Structure");
		//switch panes(stackpane)
		home.setVisible(false);
		timetable.setVisible(true);
		contents_stackpane.setVisible(false);
//		timetable_grid.setVisible(false);
		coursetable_pane.setVisible(false);
		scheduledtable_pane.setVisible(false);
		structure_pane.setVisible(true);
		//switch option boxes
		tableoption_hbox.setVisible(false);
		ttoptionbox.setVisible(false);
		down_hbox.setVisible(false);
    }
	
	//buttons
	@FXML
    void loadnewtable(ActionEvent event) {
		try {
			String currentItem = selectTable_cbox.getSelectedItem();
			switch(currentItem) {
			case "Course Table":
				setupTableView_courseTable();	
				coursetable_pane.setVisible(true);
				scheduledtable_pane.setVisible(false);
				roomtable_pane.setVisible(false);
				current_table_name.setText("Course Table");
				add_btn.setDisable(false);
				update_btn.setDisable(false);
				schedule_btn.setDisable(true);	
				break;
			case "Scheduled Table":
				coursetable.getItems().clear();
				roomtable.getItems().clear();
				setupTableView_scheduleTable();
				coursetable_pane.setVisible(false);
				scheduledtable_pane.setVisible(true);
				roomtable_pane.setVisible(false);
				current_table_name.setText("Scheduled Table");
				add_btn.setDisable(true);
				update_btn.setDisable(true);
				schedule_btn.setDisable(false);
				break;
			case "Room Table":
				coursetable.getItems().clear();
				scheduledtable.getItems().clear();
				setupTableView_roomTable();
				coursetable_pane.setVisible(false);
				scheduledtable_pane.setVisible(false);
				roomtable_pane.setVisible(true);
				current_table_name.setText("Room Table");
				add_btn.setDisable(false);
				update_btn.setDisable(false);
				schedule_btn.setDisable(true);
				break;
		}
		} catch (NullPointerException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Please select a table");
		}


    }
	
	@FXML
    void update_db(ActionEvent event) {
		String $table_name =  current_table_name.getText();
		DBConfig.setProperty("ButtonStatus", "Update");
		String ID;
		switch($table_name){
			case "Room Table":
				try {
					ID = String.valueOf(roomtable.getSelectionModel().getSelectedItem().getID());
					DBConfig.setProperty("ID", ID);
					new Room();
				} catch (NullPointerException e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Select item to update");
				}
				break;
			case "Course Table":
				try {
					ID = String.valueOf(coursetable.getSelectionModel().getSelectedItem().getID());
					DBConfig.setProperty("ID", ID);
					new NewCourse();
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Select item to update");
				}
				break;
			default:
				JOptionPane.showMessageDialog(null, "Not Applicable");
				break;
		}
    }
	
	@FXML
    void swap(ActionEvent event) {

    }
	
	@FXML
    void add_db(ActionEvent event) {
		String $table_name =  current_table_name.getText();
		DBConfig.setProperty("ButtonStatus", "Add");
		switch($table_name){
			case "Course Table":
				new NewCourse();
				break;
			case "Room Table":
				new Room();
				break;
			default:
				JOptionPane.showMessageDialog(null, "Not Applicable");
				break;
		}
    }
	 
	@FXML
    void loadtt(ActionEvent event) {
		contents_stackpane.getChildren().remove(contents_stackpane.lookup("#grid"));
		ScheduleTableDB.createGridPane(contents_stackpane, sel_dept_cbox.getSelectedItem(), sel_studentLevel_cbox.getSelectedItem());
    }
	
	
	@FXML
    void schedule(ActionEvent event) {
		TimetableGA.schedule();
    }
	
	
	@FXML
    void select_college(ActionEvent event) {
		faculty_cbox.setItems(Faculty.get_Faculty(college_cbox.getSelectedItem()));
    }

    @FXML
    void select_department(ActionEvent event) {
    	
    }

    @FXML
    void select_faculty(ActionEvent event) {
    	dept_cbox.setItems(Faculty.get_Department(faculty_cbox.getSelectedItem()));
    }
    

	 
    @FXML
    void save(ActionEvent event) {
    	DBConfig.setProperty("faculty", faculty_cbox.getSelectedItem());
    	JOptionPane.showMessageDialog(null, "Saved Successfully");
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
//		courseTable_columncourseName.setCellValueFactory(new PropertyValueFactory<SecondSemesterTableContents,String>("CourseName"));
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
//		scheduledtable_columncourseName.setCellValueFactory(new PropertyValueFactory<ScheduleTableContents,String>("courseName"));
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
