package cl.titicoctel.apilogin.service;

import cl.titicoctel.apilogin.Dto.RegistroRequest;
import cl.titicoctel.apilogin.Dto.RegistroResponse;
import cl.titicoctel.apilogin.model.Usuarios;
import cl.titicoctel.apilogin.repository.RegistroUsuariosRepository;
import io.jsonwebtoken.lang.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.Date;

public class RegistroUsuariosServiceImplTest {

    @InjectMocks
    private RegistroUsuariosServiceImpl registroUsuariosService;

    @Mock
    private RegistroUsuariosRepository registroUsuariosRepository;
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void guardarRegistroUsuariosTest(){
        RegistroRequest request = RegistroRequest.builder()
                .correo("f@mail.com")
                .password("Abcd1234")
                .build();
        Date fecha = new Date();
        Usuarios usuarios = new Usuarios();
        usuarios.setCorreo(request.getCorreo());
        usuarios.setPassword(request.getPassword());
        usuarios.setFechaCreacion(new Timestamp(fecha.getTime()));
        Usuarios responseUsuarios = new Usuarios();
        responseUsuarios.setFechaCreacion(new Timestamp(fecha.getTime()));
        Mockito.doReturn(usuarios).when(registroUsuariosRepository).save(Mockito.any());
        ResponseEntity<RegistroResponse> response = registroUsuariosService.guardarRegistro(request);
        Assert.notNull(response);
    }
}
