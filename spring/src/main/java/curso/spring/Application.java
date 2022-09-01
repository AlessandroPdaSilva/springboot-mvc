package curso.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "curso.spring.model")
// @ComponentScan(basePackages = {"curso.*"})
// @EnableJpaRepositories(basePackages= {"curso.spring.repository"})
// @EnableTransactionManagement
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
