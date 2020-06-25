package cl.titicoctel.apilogin.cucumber.steps;

import cl.titicoctel.apilogin.Dto.ErroresNegocioDto;
import cl.titicoctel.apilogin.Dto.RegistroRequest;
import cl.titicoctel.apilogin.Dto.RegistroResponse;
import cl.titicoctel.apilogin.cucumber.base.AcceptanceBase;
import cl.titicoctel.apilogin.cucumber.util.Utilitarios;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import static org.assertj.core.api.Assertions.assertThat;

public class RegistroUsuarioStep extends AcceptanceBase {

    private ResponseEntity responseEntity;
    private int latestCodeError;
    private String mensajeError;

    @When("^solicito crear cuenta de usuario con correo \"([^\"]*)\" y password \"([^\"]*)\"$")
    public void solicitoCrearCuentaDeUsuarioCorreoPassword(String correo, String password) throws Throwable {
        String URL= "http://localhost:8080/titicoctel/registro";
        RegistroRequest request = RegistroRequest.builder()
                .correo(correo)
                .password(password)
                .build();
        HttpEntity<Object>formEntity=new HttpEntity<>(request);
        try{
            responseEntity = restTemplate.exchange(URL, HttpMethod.POST,formEntity, RegistroResponse.class);
        }catch(HttpStatusCodeException ex){
            latestCodeError = ex.getRawStatusCode();
            mensajeError = ex.getResponseBodyAsString();
            assertThat(latestCodeError).isNotNull();
        }
    }
    @Then("^obtengo un codigo de respuesta (\\d+) y mensaje \"([^\"]*)\"$")
    public void obtengo_un_codigo_de_respuesta(Integer codigo, String mensaje) {
        if (codigo!=201) {
            ErroresNegocioDto error = Utilitarios.obtenerErrorDesdeJson(mensajeError);
            assertThat(error.getCodigo()).isEqualTo(mensaje);
        }else{
            assertThat(responseEntity.getStatusCodeValue()).isEqualTo(codigo);
        }
    }

}
