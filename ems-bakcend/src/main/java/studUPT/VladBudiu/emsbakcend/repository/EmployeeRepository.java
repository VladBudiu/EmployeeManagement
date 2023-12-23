package studUPT.VladBudiu.emsbakcend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import studUPT.VladBudiu.emsbakcend.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
