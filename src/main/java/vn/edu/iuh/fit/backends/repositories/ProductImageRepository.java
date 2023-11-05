package vn.edu.iuh.fit.backends.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.backends.models.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
