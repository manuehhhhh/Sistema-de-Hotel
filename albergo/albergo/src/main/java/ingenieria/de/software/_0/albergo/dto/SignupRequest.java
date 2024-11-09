package ingenieria.de.software._0.albergo.dto;

import lombok.Data;

@Data

public class SignupRequest {
    private String email;
    private String password;
    private String name;
}
