package model;

public class Timeslot {
	private final int timeslotId;
    private final String timeslot;

    /**
     * Initalize new Timeslot
     * 
     * @param timeslotId The ID for this timeslot
     * @param timeslot The timeslot being initalized
     */
    public Timeslot(int timeslotId, String timeslot){
        this.timeslotId = timeslotId;
        this.timeslot = timeslot;
    }
    
    /**
     * Returns the timeslotId
     * 
     * @return timeslotId
     */
    public int getTimeslotId(){
        return this.timeslotId;
    }
    
    /**
     * Returns the timeslot
     * 
     * @return timeslot
     */
    public String getTimeslot(){
        return this.timeslot;
    }
}
