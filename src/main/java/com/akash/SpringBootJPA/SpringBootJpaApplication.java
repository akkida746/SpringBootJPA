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
	@Autowired
	private PersonHibernateDAO personHibernateDAO;

	@Override
	public void run(String... args) throws Exception {

		// Saving objects using Spring @Transactional.
		Person person = new Person();
		person.setName("person");
		person.setCity("London");
		personDao.savePerson(person);

		Employee emp = new Employee();
		emp.setName("employee");
		emp.setCity("Noida");
		emp.setDesignation("Architect");
		personDao.savePerson(emp);

		// Saving objects using Hibernate transaction.
		Person person1 = new Person();
		person1.setName("person1");
		person1.setCity("Paris");
		personHibernateDAO.savePerson(person1);

	}
}
