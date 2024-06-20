package kr.damda.dcm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DcmApplication {

	public static void main(String[] args) {
		SpringApplication.run(DcmApplication.class, args);
	}

}
