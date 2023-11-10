package vn.edu.iuh.fit.backends.configs;

import net.datafaker.Faker;
import net.datafaker.providers.base.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.edu.iuh.fit.backends.enums.ProductStatus;
import vn.edu.iuh.fit.backends.models.Product;
import vn.edu.iuh.fit.backends.repositories.ProductRepository;

@Configuration
public class ProductConfig {
    private final ProductRepository productRepository;

    @Autowired
    public ProductConfig(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//    @Bean
    CommandLineRunner createSampleProducts() {
        return args -> {
            Faker faker = new Faker();
//            Random rnd = new Random();
            Device devices = faker.device();
            for (int i = 0; i < 200; i++) {
                Product product = new Product(
                        devices.modelName(),
                        faker.lorem().paragraph(30),
//                        rnd.nextInt(10)%2==0?"Kg":"piece",
                        "piece",
                        devices.manufacturer(),
                        ProductStatus.ACTIVE
                );
                productRepository.save(product);
            }
        };
    }
}
