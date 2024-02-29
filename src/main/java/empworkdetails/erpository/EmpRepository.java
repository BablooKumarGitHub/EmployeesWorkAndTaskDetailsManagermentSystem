package empworkdetails.erpository;

import org.springframework.data.jpa.repository.JpaRepository;

import empworkdetails.entities.Employee;

public interface EmpRepository extends JpaRepository<Employee, Integer> {

}
