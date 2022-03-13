package com.cetinkaya.zoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableCaching
@EnableJpaAuditing(auditorAwareRef="zooAuditorAware")
@ServletComponentScan
@SpringBootApplication
@EnableConfigurationProperties(value = ZooProperties.class)
public class ZooApplication {

	public static void main(String[] args) throws Exception{

		SpringApplication.run(ZooApplication.class, args);
	}

}
