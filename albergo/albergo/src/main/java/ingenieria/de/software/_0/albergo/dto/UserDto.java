package ingenieria.de.software._0.albergo.dto;

import ingenieria.de.software._0.albergo.enums.UserRole;

import lombok.Data;

@Data

public class UserDto {
    private Long id;
    private String email;
    private String name;
    private UserRole userRole;
}
