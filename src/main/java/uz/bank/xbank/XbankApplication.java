package uz.bank.xbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class XbankApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();

		System.setProperty("spring.datasource.url", dotenv.get("DB_URL"));
		System.setProperty("spring.datasource.username", dotenv.get("DB_USERNAME"));
		System.setProperty("spring.datasource.password", dotenv.get("DB_PASSWORD"));
		System.setProperty("server.port", dotenv.get("SERVER_PORT"));
		System.setProperty("xbank.jwt.secret", dotenv.get("JWT_SECRET"));
		System.setProperty("xbank.jwt.expirationMs", dotenv.get("JWT_EXPIRATION_MS"));

		SpringApplication.run(XbankApplication.class, args);
	}

}
