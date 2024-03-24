package com.example.yolarkadasim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.sql.DataSource;

@SpringBootApplication
public class YolarkadasimApplication {

	public static void main(String[] args) {
		SpringApplication.run(YolarkadasimApplication.class, args);
	}

}
