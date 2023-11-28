package vn.edu.iuh.fit.backends.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.edu.iuh.fit.backends.models.Product;
import vn.edu.iuh.fit.backends.models.ProductImage;
import vn.edu.iuh.fit.backends.repositories.ProductImageRepository;

@Configuration
public class ProductImageConfig {
    @Autowired
    private ProductImageRepository productImageRepository;


//    @Bean
    CommandLineRunner createProductImage() {
        return args -> {
            for (int i = 0; i < 20; i++) {
                String path = "https://bizweb.dktcdn.net/thumb/large/100/318/659/products/mbp16-silver-select-202110-jpeg-8dd6d65a-32b9-4a60-ac8c-4ff6b8dee8d1-4693bb2a-1f3a-469f-b854-dc1af8c8e570.jpg?v=1683193287500";
                ProductImage productImage = new ProductImage(
                        path,
                        new Product(i+1)
                );
                productImageRepository.save(productImage);
            }
        };
    }


}
