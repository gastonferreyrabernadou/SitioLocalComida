package localcomida.pikda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:valores.properties")
public class PikdaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PikdaApplication.class, args);
	}

}
