package empworkdetails.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import empworkdetails.entities.EmpTaskDetails;
import empworkdetails.erpository.TaskRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepo;

	@Override
	public EmpTaskDetails saveTask(EmpTaskDetails emp) {
		// TODO Auto-generated method stub
		EmpTaskDetails newTask = taskRepo.save(emp);
		return newTask;
	}

	@Override
	public List<EmpTaskDetails> getAllTask() {
		// TODO Auto-generated method stub				
		return taskRepo.findAll();
	}

	@Override
	public EmpTaskDetails getTaskById(int id) {
		// TODO Auto-generated method stub		
		return taskRepo.findById(id).get();
	}	

	public void removeSessionMessage() {
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();

		session.removeAttribute("msg");

	}

}
