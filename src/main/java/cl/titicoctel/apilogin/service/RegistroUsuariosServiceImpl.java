package cl.titicoctel.apilogin.service;

import cl.titicoctel.apilogin.configuration.ErroresConfiguration;
import cl.titicoctel.apilogin.Dto.RegistroRequest;
import cl.titicoctel.apilogin.Dto.RegistroResponse;
import cl.titicoctel.apilogin.model.Usuarios;
import cl.titicoctel.apilogin.repository.RegistroUsuariosRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Servicio que permite registrar nuevos usuarios.
 *
 * @author Francisco Venegas Rojas
 *
 * <p>
 * <b>Todos los derechos reservados por Francisco Venegas Rojas</b>
 * <p>
 */

@Service
public class RegistroUsuariosServiceImpl implements RegistroUsuariosService {
    @Autowired
    private RegistroUsuariosRepository registroUsuariosRepository;
    @Autowired
    private ErroresConfiguration erroresConfiguration;

    @Override
    public ResponseEntity<RegistroResponse> guardarRegistro(RegistroRequest registroRequest){
        boolean passValida = validarPass(registroRequest.getPassword());
        boolean mailExiste = existeMail(registroRequest.getCorreo());
        boolean mailCumpleFormato = validarMail(registroRequest.getCorreo());
        if(mailExiste == true){
            return new ResponseEntity(erroresConfiguration.getCorreoExiste(), HttpStatus.BAD_REQUEST);
        }
        if (!passValida){
            return new ResponseEntity(erroresConfiguration.getPasswordNoCumpleRequisitos(), HttpStatus.BAD_REQUEST);
        }
        if(!mailCumpleFormato){
            return new ResponseEntity(erroresConfiguration.getCorreoNoCumpleFormato(), HttpStatus.BAD_REQUEST);
        }
        if (registroRequest.getCorreo().equals("")||registroRequest.getPassword().equals("")){
            return new ResponseEntity(erroresConfiguration.getCamposVacios(), HttpStatus.BAD_REQUEST);
        }
        if (registroRequest.getPassword()==null||registroRequest.getCorreo()==null){
            return new ResponseEntity(erroresConfiguration.getCamposNulos(), HttpStatus.BAD_REQUEST);
        }
        if (passValida && !mailExiste) {
            String passwordEncryptada = Encryptarpassword(registroRequest.getPassword());
            Usuarios usuarios = new Usuarios();
            usuarios.setCorreo(registroRequest.getCorreo());
            usuarios.setPassword(passwordEncryptada);
            Usuarios response = registroUsuariosRepository.save(usuarios);
            RegistroResponse registroResponse = RegistroResponse.builder()
                    .fechaGeneracion(response.getFechaCreacion())
                    .build();
            return new ResponseEntity<>(registroResponse, HttpStatus.CREATED);
        }else{
            return new ResponseEntity("Password no cumple valores", HttpStatus.BAD_REQUEST);
        }
    }

    private boolean validarPass (String clave){
        Pattern pattern = Pattern.compile("^(?=.*[0-9]{2})(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");
        String pass = clave;
        boolean password = false;
        Matcher mather = pattern.matcher(pass);
        if (mather.find() == true){
            password = true;
        }
        return password;
    }

    private String Encryptarpassword (String password){
        String secretKey = password;
        String base64EncryptedString = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainTextBytes = password.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }
    private boolean existeMail (String correo){
        Usuarios usuarios = new Usuarios();
        usuarios.setCorreo(correo);
        Example<Usuarios>correoBuscado=Example.of(usuarios);
        List<Usuarios> correos=registroUsuariosRepository.findAll(correoBuscado);
        boolean siExiste = false;
        if (correos.size()>0){
            siExiste=true;
        }
        return siExiste;
    }

    private boolean validarMail (String mail){
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        String email = mail;
        boolean valido =  false;
        Matcher mather = pattern.matcher(email);
        if (mather.find() == true) {
            valido = true;
        }
        return valido;
    }
}
