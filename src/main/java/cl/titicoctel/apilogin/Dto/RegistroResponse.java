package cl.titicoctel.apilogin.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistroResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern= "yyyy-MM-dd'T'HH:mm:ss.SSSz", timezone= "America/Santiago")
    private Date fechaGeneracion;
}
