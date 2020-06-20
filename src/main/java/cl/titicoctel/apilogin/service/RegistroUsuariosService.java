package cl.titicoctel.apilogin.service;

import cl.titicoctel.apilogin.Dto.RegistroRequest;
import cl.titicoctel.apilogin.Dto.RegistroResponse;
import org.springframework.http.ResponseEntity;

public interface RegistroUsuariosService {
    ResponseEntity<RegistroResponse>guardarRegistro(RegistroRequest registroRequest);
}
