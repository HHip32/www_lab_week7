package vn.edu.iuh.fit.backends.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.iuh.fit.backends.enums.ProductStatus;
import vn.edu.iuh.fit.backends.models.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //    public List<Object[]> findBy
    @Query("SELECT p, pi, pp FROM Product p LEFT JOIN FETCH p.productImageList pi LEFT JOIN p.productPrices pp WHERE p.status = ?1")
    List<Object[]> findAllProductsWithDetails(ProductStatus status);

}
