package scheduler;

import java.util.Vector;

public class MyEvaluator implements Evaluator
{
  /*
   *  This function scores a complete assignment of variables. It is an improved
   *  scoring system based on OrigEvaluator.
   *  
   *  It takes as input a scheduling instance and a complete schedule (all
   *  courses have times assigned for their exams).
   *  
   *  If the schedule isn't feasible (i.e. two exams are scheduled in the same
   *  room at the same time), the function returns a cost that is relative to
   *  the total number of students taking exams and the total number of course
   *  conflicts. 
   *  
   *  As in OrigEvaluator, this function treats student constraints as lower
   *  priority, and counts up the number of times that a schedule asks a student
   *  to be in two exams at the same time. It has the ability to count the number
   *  of courses being assigned the same room and time and able to differentiate
   *  that number. This improves the situation when you have multiple courses
   *  having time + location conflicts, removing some of them, but not all of them
   *  are considered to be an improvement instead of considered as no improvement.
   *  
   *  @param  pInstance the problem instance
   *  @param  pCandidateSchedule a candidate schedule (a list of timeslot and
   *          room for each course)
   *  @return either the number of student constraints violated, or
   *          Integer.MAX_VALUE if the schedule is incomplete or infeasible
   *  @throws Exception
   */
  public int violatedConstraints(SchedulingInstance pInstance, ScheduleChoice[] pCandidateSchedule) throws Exception
  {
    if(pCandidateSchedule == null)
    {
      return Integer.MAX_VALUE;
    }
    
    /* Throw error for incomplete schedules */
    if (pCandidateSchedule.length != pInstance.numCourses)
    {
      throw new Exception("Incomplete Exam Schedule. Have to assign a time and room to every exam!");
    }

    /* Throw error for using more rooms or timeslots than available */
    for (int i = 0; i < pCandidateSchedule.length; i++) {
      if (pCandidateSchedule[i].timeslot >= pInstance.numTimeslots || pCandidateSchedule[i].timeslot < 0) {
        throw new Exception("Timeslot must be in {0,...," + (pInstance.numTimeslots-1) + "}, but is " + pCandidateSchedule[i].timeslot );
      }
      if (pCandidateSchedule[i].room >= pInstance.numRooms || pCandidateSchedule[i].room < 0) {
        throw new Exception("Room must be in {0,...," + (pInstance.numRooms-1) + "}, but is " + pCandidateSchedule[i].room );
      }
    }
    
    /* Count student conflicts */
    int conflicts = 0;
    Vector<Vector<Integer>> studentsCourses = pInstance.studentsCourses;
    for (int student = 0; student < pInstance.numStudents; student++) {
      Vector<Integer> coursesOfThisStudent = studentsCourses.elementAt(student);
      for (int i = 0; i < coursesOfThisStudent.size(); i++) {
        for (int j = i+1; j < coursesOfThisStudent.size(); j++) {
          if (pCandidateSchedule[coursesOfThisStudent.elementAt(i)].timeslot == pCandidateSchedule[coursesOfThisStudent.elementAt(j)].timeslot){
            conflicts++;
          }
        }
      }
    }
    
    /* Check for collisions of exams (same time and slot) */
    for (int i = 0; i < pCandidateSchedule.length; i++) {
      for (int j = i+1; j < pCandidateSchedule.length; j++) {
        if (pCandidateSchedule[i].timeslot == pCandidateSchedule[j].timeslot && pCandidateSchedule[i].room == pCandidateSchedule[j].room) {
          // We cannot schedule multiple exams into the same timeslot and
          // room.
          // 
          // For a more elaborate evaluation of exam collisions, return a score
          // that is relative to the student body size and the number of course
        	// conflicts. this guarantees that inherent course conflicts will have
        	// larger consequence than student conflict, but allowing the differentiate
        	// different number of course conflicts.
          conflicts += pInstance.numStudents;
        }
      }
    }
    
    // Return total student conflicts as the score
    return conflicts;
  }
}
