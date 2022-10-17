package br.com.agls.pizzariafuturodev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PizzariaFuturodevApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzariaFuturodevApplication.class, args);
	}

	@GetMapping("/")
	public  String index(){
		return "Olá mundo";
	}
}
