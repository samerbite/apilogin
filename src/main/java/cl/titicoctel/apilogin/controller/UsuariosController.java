package cl.titicoctel.apilogin.controller;

import cl.titicoctel.apilogin.Dto.RegistroRequest;
import cl.titicoctel.apilogin.Dto.RegistroResponse;
import cl.titicoctel.apilogin.service.RegistroUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/titicoctel")
public class UsuariosController {

    @Autowired
    private RegistroUsuariosService registroUsuariosService;

    @PostMapping(value = "/registro")
    public ResponseEntity<RegistroResponse>RegistroUsuario(@RequestBody RegistroRequest registroRequest){
        ResponseEntity<RegistroResponse>responseResponseEntity=
                registroUsuariosService.guardarRegistro(registroRequest);
        return responseResponseEntity;

    }


}
