package empworkdetails.services;

import java.util.List;

import empworkdetails.entities.EmpTaskDetails;


public interface TaskService {

	public EmpTaskDetails saveTask(EmpTaskDetails emp);

	public List<EmpTaskDetails> getAllTask();

	public EmpTaskDetails getTaskById(int id);

}
