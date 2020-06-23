package cl.titicoctel.apilogin.cucumber.base;

import cl.titicoctel.apilogin.cucumber.PruebasAceptacionCucumber;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration
public class AcceptanceBase {
    private final String SERVER_URL = "http://localhost/titicoctel";
    private final String THINGS_ENDPOINT = "/registro";

    @LocalServerPort
    private int port;
    protected RestTemplate restTemplate = new RestTemplate();

    private String thingsEndpoint() {
        return SERVER_URL + ":" + port + THINGS_ENDPOINT;
    }

    public int put(final String something) {
        return restTemplate.postForEntity(thingsEndpoint(), something, Void.class).getStatusCodeValue();
    }

    public PruebasAceptacionCucumber getContents() {
        return restTemplate.getForEntity(thingsEndpoint(), PruebasAceptacionCucumber.class).getBody();
    }

    public void clean() {
        restTemplate.delete(thingsEndpoint());
    }

}
