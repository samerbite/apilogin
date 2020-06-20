package cl.titicoctel.apilogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("cl.titicoctel.apilogin.model")
public class ApiLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiLoginApplication.class, args);
	}

}
