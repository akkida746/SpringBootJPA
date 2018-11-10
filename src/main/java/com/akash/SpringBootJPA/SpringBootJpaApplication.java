package com.akash.SpringBootJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootJpaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaApplication.class, args);
	}

	@Autowired
	private PersonDAO personDao;

	@Override
	public void run(String... args) throws Exception {
		Person person = new Person();
		person.setName("Akash");
		person.setCity("MBD");
		personDao.savePerson(person);
	}
}
