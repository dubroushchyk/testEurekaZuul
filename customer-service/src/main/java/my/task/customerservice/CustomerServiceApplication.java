package my.task.customerservice;

import my.task.customerservice.model.Admin;
import my.task.customerservice.model.User;
import my.task.customerservice.repository.AdminRepository;
import my.task.customerservice.repository.UserRepository;
import my.task.customerservice.util.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableEurekaClient
public class CustomerServiceApplication {

    private UserRepository userRepository;

    private AdminRepository adminRepository;

    public CustomerServiceApplication(UserRepository userRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public void storeData() {

        User user = new User();
        user.setUsername("user");
        user.setPassword(new BCryptPasswordEncoder().encode("user"));
        user.setName("user");
        user.setSex("Male");
        user.setAge(29);
        user.setRole("USER");
        user.setPurpose("GAIN_MUSCLE_MASS");
        user.setAdditionalСharacteristics("GLUTEN_FREE_DIET");
        userRepository.save(user);
        System.out.println("user saved!!!!!!!!!!!!!!!!!!!!!!!!!!");

        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
        admin.setRole("ADMIN");
        adminRepository.save(admin);

        System.out.println("admin saved!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}
