package my.task.customerservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOUser {

    private Long id;

    private String username;

    private String password;

    private String role;

    private String name;

    private String sex;

    private Integer age;

    private String purpose;

    private String additionalСharacteristics;
}
