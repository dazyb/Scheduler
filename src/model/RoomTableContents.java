package model;

public class RoomTableContents {
	private int ID,Size;
	String Building_Name,Room_Name;
	public RoomTableContents(int iD, int size, String building_Name, String room_Name) {
		super();
		ID = iD;
		Size = size;
		Building_Name = building_Name;
		Room_Name = room_Name;
	}
	public int getID() {
		return ID;
	}
	public int getSize() {
		return Size;
	}
	public String getBuilding_Name() {
		return Building_Name;
	}
	public String getRoom_Name() {
		return Room_Name;
	}
	
}
