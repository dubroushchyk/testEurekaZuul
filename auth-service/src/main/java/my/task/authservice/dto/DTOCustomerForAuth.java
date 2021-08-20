package my.task.authservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DTOCustomerForAuth {

    private String username;

    private String password;

    private String role;
}
