package my.task.customerservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@RequiredArgsConstructor
public class DTOUser {

    private Long id;

    private String username;

    private String password;

    private String name;

    private String sex;

    private Integer age;

    private String purpose;

    private String additionalСharacteristics;
}
