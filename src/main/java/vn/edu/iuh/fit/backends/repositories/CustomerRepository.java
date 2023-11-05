package vn.edu.iuh.fit.backends.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.backends.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
