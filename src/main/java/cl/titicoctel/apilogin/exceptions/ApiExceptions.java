package cl.titicoctel.apilogin.exceptions;

import cl.titicoctel.apilogin.Dto.ErroresNegocioDto;

public class ApiExceptions extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private final String codigo;

    public ApiExceptions(String codigo, String mensaje){
        super(mensaje);
        this.codigo = codigo;
    }

    public ApiExceptions(ErroresNegocioDto error) {
        super(error.getMensaje());
        this.codigo = error.getCodigo();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getMensaje() {
        return getMessage();
    }
}
