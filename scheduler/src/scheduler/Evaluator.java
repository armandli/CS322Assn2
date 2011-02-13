package scheduler;

public interface Evaluator {
	public int violatedConstraints(SchedulingInstance pInstance, ScheduleChoice[] pCandidateSchedule) throws Exception;
}
