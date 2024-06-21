package academy.devdojo.springboot2;

import academy.devdojo.springboot2.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class DevdojoApplication {


	public static void main(String[] args) {
		SpringApplication.run(DevdojoApplication.class, args);
	}

}
