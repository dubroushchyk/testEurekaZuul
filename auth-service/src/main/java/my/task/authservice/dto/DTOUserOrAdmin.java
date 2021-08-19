package my.task.authservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOUserOrAdmin {

    private String username;

    private String password;

    private String role;
}
