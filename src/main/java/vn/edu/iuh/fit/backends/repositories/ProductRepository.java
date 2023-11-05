package vn.edu.iuh.fit.backends.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.backends.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
