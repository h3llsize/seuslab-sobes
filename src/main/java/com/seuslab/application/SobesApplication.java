package com.seuslab.application;

import com.seuslab.application.service.pet.BaseReaderService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SobesApplication {

	private final BaseReaderService baseReaderService;

	public SobesApplication(BaseReaderService baseReaderService) {
		this.baseReaderService = baseReaderService;
	}

	@PostConstruct
	private void init() throws InterruptedException {
		baseReaderService.startReadingFromBase();
	}


	public static void main(String[] args) {
		SpringApplication.run(SobesApplication.class, args);
	}

}
