package academy.devdojo.springboot2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "academy.devdojo.springboot2")
public class DevdojoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevdojoApplication.class, args);
	}

}
