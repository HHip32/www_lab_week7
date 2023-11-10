package vn.edu.iuh.fit.backends.configs;

import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.edu.iuh.fit.backends.enums.EmployeeStatus;
import vn.edu.iuh.fit.backends.models.Employee;
import vn.edu.iuh.fit.backends.repositories.EmployeeRepository;

import java.time.LocalDate;
import java.util.Random;

@Configuration
public class EmployeeConfig {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeConfig(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

//    @Bean
    CommandLineRunner createSampleEmployee() {
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
                Employee employee = new Employee(
                        faker.artist().name()
                        , LocalDate.now()
                        , result + String.valueOf(i) + "@gmail.com"
                        , "0977066555"
                        , faker.address().fullAddress()
                        , EmployeeStatus.ACTIVE
                );
                employeeRepository.save(employee);
            }
        };
    }
}
