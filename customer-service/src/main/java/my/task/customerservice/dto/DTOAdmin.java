package my.task.customerservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@RequiredArgsConstructor
public class DTOAdmin {

    private Long id;

    private String username;

    private String password;
}
