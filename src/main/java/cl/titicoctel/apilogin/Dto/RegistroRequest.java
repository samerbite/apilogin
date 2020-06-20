package cl.titicoctel.apilogin.Dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RegistroRequest {
    private String correo;
    private String password;
}
