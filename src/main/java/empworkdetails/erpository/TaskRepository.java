package empworkdetails.erpository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import empworkdetails.entities.EmpTaskDetails;

public interface TaskRepository extends JpaRepository<EmpTaskDetails, Integer> {
	
}
