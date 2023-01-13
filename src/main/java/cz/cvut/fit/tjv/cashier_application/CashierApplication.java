package cz.cvut.fit.tjv.cashier_application;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(title = "Cashier Application", version = "1.0.0", description = "Application for saving orders."),
		servers = @Server(description = "local server", url = "http://localhost:8080")
)
@SpringBootApplication
public class CashierApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashierApplication.class, args);
	}

}
