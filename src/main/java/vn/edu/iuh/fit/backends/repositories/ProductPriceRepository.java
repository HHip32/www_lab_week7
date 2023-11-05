package vn.edu.iuh.fit.backends.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.backends.models.ProductPrice;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {
}
