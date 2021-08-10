package my.task.authservice;

import my.task.authservice.Enum.EnumCharacteristics;
import my.task.authservice.Enum.EnumPurpose;
import my.task.authservice.Enum.EnumRole;
import my.task.authservice.Enum.EnumSex;
import my.task.authservice.model.User;
import my.task.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
@EnableEurekaClient
public class AuthServiceApplication {

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

    @Bean
    public void storeData() {

        User user = new User();
        user.setUsername("admin");
        user.setPassword(new BCryptPasswordEncoder().encode("admin"));
        user.setName("admin");
        user.setSex(EnumSex.MALE);
        user.setAge(29);
        user.setRole(EnumRole.ADMIN);
        user.setPurpose(EnumPurpose.GAIN_MUSCLE_MASS);
        user.setAdditionalСharacteristics(EnumCharacteristics.GLUTEN_FREE_DIET);

        userRepository.save(user);
        System.out.println("user admin saved!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

}
