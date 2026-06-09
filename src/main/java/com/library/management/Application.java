package com.library.management;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.library.management.entity.Author;
import com.library.management.entity.Book;
import com.library.management.entity.Category;
import com.library.management.entity.Publisher;
import com.library.management.entity.Role;
import com.library.management.entity.User;
import com.library.management.repository.UserRepository;
import com.library.management.service.BookService;

@SpringBootApplication
public class Application {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private BookService bookService;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner initialCreate() {
		return (args) -> {

			var book = new Book("AP1287", "Spring in Action", "CXEF12389", "A guide to Spring Framework basics and advanced concepts.");
			book.addAuthors(new Author("Craig Walls", "Author and software developer specializing in Spring and Java."));
			book.addCategories(new Category("Programming"));
			book.addPublishers(new Publisher("Manning Publications"));
			bookService.createBook(book);

			var book1 = new Book("BP567#R", "Spring Microservices in Action", "KCXEF12389", "Build scalable microservices with Spring Cloud and Spring Boot.");
			book1.addAuthors(new Author("John Carnell", "Senior cloud architect and author of microservices articles."));
			book1.addCategories(new Category("Cloud Computing"));
			book1.addPublishers(new Publisher("Packt Publishing"));
			bookService.createBook(book1);

			var book2 = new Book("GH67F#", "Expert One-on-One J2EE Design", "UV#JH", "A seminal book introducing lightweight architectures and Spring ancestors.");
			book2.addAuthors(new Author("Rod Johnson", "Creator of the Spring framework and Java authority."));
			book2.addCategories(new Category("Software Architecture"));
			book2.addPublishers(new Publisher("Wrox Press"));
			bookService.createBook(book2);

			var user = new User("admin", "admin", "admin@library.com", passwordEncoder.encode("Temp123"),
					Arrays.asList(new Role("ROLE_ADMIN")));
			userRepository.save(user);

		};
	}
}

