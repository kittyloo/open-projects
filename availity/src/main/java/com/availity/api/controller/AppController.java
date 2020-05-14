/**
 * 
 */
package com.availity.api.controller;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author mangeles
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.availity.api.*")
public class AppController {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(AppController.class, args);
	}

}
