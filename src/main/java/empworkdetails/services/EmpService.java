package empworkdetails.services;

import java.util.List;

import empworkdetails.entities.Employee;

public interface EmpService {

	public Employee saveEmp(Employee emp);

	public List<Employee> getAllEmp();

	public Employee getEmpById(int id);

	public boolean deleteEmp(int id);

}
