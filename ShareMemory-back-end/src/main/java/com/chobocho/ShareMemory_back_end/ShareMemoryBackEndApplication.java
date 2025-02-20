package com.chobocho.ShareMemory_back_end;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ShareMemoryBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShareMemoryBackEndApplication.class, args);
	}
}
