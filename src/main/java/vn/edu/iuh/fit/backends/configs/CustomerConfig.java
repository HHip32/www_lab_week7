package vn.edu.iuh.fit.backends.configs;

import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.edu.iuh.fit.backends.models.Customer;
import vn.edu.iuh.fit.backends.repositories.CustomerRepository;

import java.util.Random;

@Configuration
public class CustomerConfig {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerConfig(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

//    @Bean
    CommandLineRunner getAllCus() {
        return args -> {
            Faker faker = new Faker();
            for (int i = 0; i < 200; i++) {
                int length = 5;
                String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
                Random random = new Random();
                StringBuilder result = new StringBuilder(length);
                for (int j = 0; j < length; j++) {

                    // Lấy một ký tự ngẫu nhiên từ chuỗi characters
                    int index = random.nextInt(characters.length());
                    char randomChar = characters.charAt(index);

                    // Thêm ký tự vào chuỗi kết quả
                    result.append(randomChar);
                }
                Customer customer = new Customer(
                        faker.name().name(),
                        result + String.valueOf(i) + "@gmail.com",
                        "0988433335",
                        faker.address().streetAddress()
                );
                customerRepository.save(customer);
            }
        };
    }
}
