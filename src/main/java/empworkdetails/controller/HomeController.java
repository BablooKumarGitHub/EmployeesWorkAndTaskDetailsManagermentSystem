package empworkdetails.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import empworkdetails.entities.EmpTaskDetails;
import empworkdetails.entities.Employee;
import empworkdetails.services.EmpService;
import empworkdetails.services.TaskService;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private EmpService empService;
	@Autowired
	private TaskService taskService;

	@GetMapping("/")
	public String index(Model m) {
		List<Employee> list = empService.getAllEmp();
		m.addAttribute("empList", list);
		return "index";
	}

	// Employee Registration form page
		@GetMapping("/addEmp")
		public String addemp() {
			return "emp_save";
		}
	// Employee Registration Save handler
	@PostMapping("/saveEmp")
	public String saveEmp(@ModelAttribute Employee emp, HttpSession session) {
		// System.out.println(emp);

		Employee newEmp = empService.saveEmp(emp);

		if (newEmp != null) {
			// System.out.println("save success");
			System.out.println("Employee Data: "+newEmp.toString());
			session.setAttribute("msg", "Register sucessfully");
		} else {
			// System.out.println("something wrong on server");
			session.setAttribute("msg", "something wrong on server");
		}

		return "redirect:/addEmp";
	}
	
	// Employee Edit form page
	@GetMapping("editEmp/{id}")
	public String editEmp(@PathVariable int id, Model m) {
		// System.out.println(id);
		Employee emp = empService.getEmpById(id);
		m.addAttribute("emp", emp);
		return "edit_emp";
	}
	// Employee Edit Save handler
	@PostMapping("/updateEmpDtls")
	public String updateEmp(@ModelAttribute Employee emp, HttpSession session) {
		 System.out.println(emp.toString());

		Employee updateEmp = empService.saveEmp(emp);

		if (updateEmp != null) {
			// System.out.println("save success");
			session.setAttribute("msg", "Update sucessfully");
		} else {
			// System.out.println("something wrong on server");
			session.setAttribute("msg", "something wrong on server");
		}
		System.out.println(updateEmp.toString());
//		return "index";
		return "redirect:/";
	}

	@GetMapping("deleteEmp/{id}")
	public String loadEmpSave(@PathVariable int id, HttpSession session) {
		boolean f = empService.deleteEmp(id);
		if (f) {
			session.setAttribute("msg", "Delete sucessfully");
		} else {
			session.setAttribute("msg", "something wrong on server");
		}
		return "redirect:/";
	}
//................................................................................	
	// WorkTask Handler  /emp_task
	@GetMapping("/task_saves/{id}")
	public String addTask(@PathVariable int id, Model m) {
		m.addAttribute("empId",id);
		return "task_save";
	}
	// Employee Registration Save handler task_save/task_saves.
	@PostMapping("/saveTask")
	public String saveTask(@ModelAttribute EmpTaskDetails empTaskDetails, HttpSession session) {
		 System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");

		
		EmpTaskDetails newEmpTask = taskService.saveTask(empTaskDetails);
		 System.out.println("******************************;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");

		if (newEmpTask != null) {
			// System.out.println("save success");
			System.out.println("Task Data: "+newEmpTask.toString());
			session.setAttribute("msg", "Task save sucessfully");
			return "redirect:/emp_task/"+newEmpTask.getEmpId();
		} else {
			// System.out.println("something wrong on server");
			session.setAttribute("msg", "something wrong on server");
			return "redirect:/";
		}
//		return "redirect:/task_save/task_saves";
//		return "redirect:/emp_task/"+newEmpTask.getEmpId();
	}
		

		// Particular emp wise show workTask
		@GetMapping("/emp_task/{id}")
		public String EmpTask(@PathVariable int id, Model m) {
			 System.out.println("emp_task:   ;;;;;;;;;;;   "+id);
			
			Employee emp = empService.getEmpById(id);
			 List<EmpTaskDetails> task = taskService.getAllTask();
			 
		     List<EmpTaskDetails> tsk = task.stream().filter(emid ->emid.getEmpId() == id).collect(Collectors.toList());
		     System.out.println(tsk.toString());
			m.addAttribute("emp", emp);
			m.addAttribute("task", tsk);
			return "emp_task";
		}
				

		// Task Edit form page
		@GetMapping("editTask/{id}")
		public String editTask(@PathVariable int id, Model m) {
			// System.out.println(id);
			EmpTaskDetails emp = taskService.getTaskById(id);
			m.addAttribute("emp", emp);
			return "edit_task";
		}
		
		// Employee Edit Save handler emp_task/editTask/13
		@PostMapping("/updateEmpTask")
		public String updateEmpTask(@ModelAttribute EmpTaskDetails task, HttpSession session) {
			 System.out.println(task.toString());

			EmpTaskDetails updateTask = taskService.saveTask(task);

			if (updateTask != null) {
				// System.out.println("save success");
				session.setAttribute("msg", "Update sucessfully");
			} else {
				// System.out.println("something wrong on server");
				session.setAttribute("msg", "something wrong on server");
			}

			return "redirect:/";
		}
		
		// All Task
		@GetMapping("/allTask")
		public String allTask(Model m) {
			List<EmpTaskDetails> list = taskService.getAllTask();
			m.addAttribute("empList", list);
			return "all_task";
		}
		
}
