package scheduler;

public class ScheduleChoice {
    public int timeslot; // integer in 0, ..., numTimeslots-1 (variable across scheduling instances)
    public int room; // integer in 0, ...,  numRooms-1 (variable across scheduling instances)
	
	public String toString() {
		return "Timeslot " + timeslot + ", \t room " + room;
	}
	
	public ScheduleChoice clone(){
		ScheduleChoice theClone = new ScheduleChoice();
		theClone.timeslot = timeslot;
		theClone.room = room;
		return theClone;
	}
}
