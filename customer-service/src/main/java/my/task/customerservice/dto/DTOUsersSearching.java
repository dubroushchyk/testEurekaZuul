package my.task.customerservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DTOUsersSearching {

    private String username;

    private String name;

    private String sex;

    private Integer age;

    private String purpose;

    private String additionalСharacteristics;
}
