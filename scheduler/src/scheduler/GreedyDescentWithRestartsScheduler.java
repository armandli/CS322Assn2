package scheduler;


/**
 * A stub for your Greedy Descent With Restarts scheduler
 */
public class GreedyDescentWithRestartsScheduler extends Scheduler {

	/**
	 * @see scheduler.Scheduler#authorsAndStudentIDs()
	 */
	public String authorsAndStudentIDs() {
		// TODO Your Code Here!
		return null;
	}

	/**
	 * @throws Exception 
	 * @see scheduler.Scheduler#schedule(scheduler.SchedulingInstance)
	 */
	public ScheduleChoice[] solve(SchedulingInstance pInstance) throws Exception {
		ScheduleChoice[] bestScheduleFound = null;
		while( !timeIsUp() && evaluator.violatedConstraints(pInstance, bestScheduleFound)>0 ){
			// TODO Your Code Here!
		}
		return bestScheduleFound;
	}

}
