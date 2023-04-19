package com.coen6731.topic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TopicApplication {

	public static void main(String[] args) {
		SpringApplication.run(TopicApplication.class, args);
		JsonConfigMaker configMaker=new JsonConfigMaker();
		System.out.println(configMaker.setConfigFile());
	}

}
