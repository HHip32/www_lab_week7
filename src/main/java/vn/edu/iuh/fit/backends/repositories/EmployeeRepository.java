package vn.edu.iuh.fit.backends.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.backends.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
