package cl.titicoctel.apilogin.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import cl.titicoctel.apilogin.Dto.ErroresNegocioDto;
import cl.titicoctel.apilogin.exceptions.ApiExceptions;

@ConfigurationProperties(prefix = "errores")
@Component
@Getter
@Setter
public class ErroresConfiguration {
    private ErroresNegocioDto camposVacios;
    private ErroresNegocioDto camposNulos;
    private ErroresNegocioDto registroNoExiste;
    private ErroresNegocioDto correoExiste;
    private ErroresNegocioDto correoNoCumpleFormato;
    private ErroresNegocioDto passwordNoCumpleRequisitos;

    public ApiExceptions obtenerErrorDeNegocio(ErroresNegocioDto erroresNegocioDto){
        return new ApiExceptions(erroresNegocioDto.getCodigo(), erroresNegocioDto.getMensaje());
    }
}
