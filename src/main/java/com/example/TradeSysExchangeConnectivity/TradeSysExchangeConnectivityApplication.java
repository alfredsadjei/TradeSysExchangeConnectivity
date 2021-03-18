package com.example.TradeSysExchangeConnectivity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping(path = "api/v1/exchange")
public class TradeSysExchangeConnectivityApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeSysExchangeConnectivityApplication.class, args);
	}

	@GetMapping
	public List<String> testExchange(){
		return List.of("Hello" , "Exchange");
	}

	//////////
}
