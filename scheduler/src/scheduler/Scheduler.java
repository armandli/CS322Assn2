package scheduler;

import java.util.Random;

public abstract class Scheduler {
	private float allowedTimeInSeconds;
	protected Evaluator evaluator;
	protected long startTime;
	protected Random r;
	
	/**
	 * @return names of the authors and their student IDs (1 per line).
	 */
	public abstract String authorsAndStudentIDs();

	/**
	 * This is the interface that your local search schedulers should implement.
	 * The function should keep track of the best schedule found so far, and return
	 * it if either time is up, or the schedule is perfect (no violated constraints).
	 * 
	 * @param pProblem a scheduling problem from the generator
	 * @return an array of SchedulingChoices. Its length should be the the number of classes, as each class needs an exam.
	 * @throws Exception 
	 */
	public abstract ScheduleChoice[] solve(SchedulingInstance pInstance) throws Exception;
	
	
	/**
	 * This is the interface called by SchedulerTester. It initializes the random number generator and
	 * sets the start time to enable the boolean function timeIsUp(). Then it calls your solve() function. 
	 * 
	 * @param pEvaluator a evaluator for schedules
	 * @param pInstance a scheduling problem from the generator
	 * @param pSeed a seed for the random number generator
	 * @param pSeed the time allowed for scheduling
	 * 
	 * @return an array of SchedulingChoices. Its length should be the the number of classes, as each class needs an exam.
	 * @throws Exception 
	 */
	public ScheduleChoice[] schedule(Evaluator pEvaluator, SchedulingInstance pInstance, long pSeed, float pAllowedTime) throws Exception{
		evaluator = pEvaluator;
		allowedTimeInSeconds = pAllowedTime;
		startTime = System.currentTimeMillis();
		r = new Random();
		r.setSeed(pSeed);
		return solve(pInstance);
	}
	
	public boolean timeIsUp(){
		return ((System.currentTimeMillis() - startTime) / 1000.0) > allowedTimeInSeconds;
	}
}
