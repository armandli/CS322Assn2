package scheduler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * This class stores all the information needed to describe an instance of our simple version of exam scheduling.
 * 
 * @author Frank Hutter
 * 
 */

public class SchedulingInstance {
	public int numRooms;
	public int numCourses;
	public int numStudents;
	public int numTimeslots;
	public Vector<Vector<Integer>> studentsCourses;
	
	
	/**
	 * Read an exam scheduling instance from the file given in scheduleFile.
	 * The first line lists 4 integers, separated by a whitespace: the number of rooms, the number of courses (numCourses),
	 * the number of students (numStudents), and the number of time slots.
	 * This is followed by numStudents lines (one for each student). The nth such line contains a list 
	 * of the IDs of the courses the student is taking (any of 0, ..., numCourses-1), separated by whitespaces.
	 * 
	 * @param scheduleFile The full file name and location for the scheduling instance;
	 * 
	 * @throws IOException
	 */
	
	public SchedulingInstance(String scheduleFile) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(scheduleFile));
		String str = in.readLine();
		StringTokenizer st = new StringTokenizer(str," ");
		
		numRooms = new Integer( st.nextToken() ).intValue();
		numCourses = new Integer( st.nextToken() ).intValue();
		numStudents = new Integer( st.nextToken() ).intValue();
		numTimeslots = new Integer( st.nextToken() ).intValue();
		
		studentsCourses = new Vector<Vector<Integer>>();
		for (int i = 0; i < numStudents; i++) {
			str = in.readLine();
			Vector<Integer> coursesThisStudent = new Vector<Integer>();

			st = new StringTokenizer(str," ");
			while(st.hasMoreTokens()){
				coursesThisStudent.addElement( new Integer(st.nextToken()) );
			}
			studentsCourses.addElement(coursesThisStudent);
		}
	}
}
