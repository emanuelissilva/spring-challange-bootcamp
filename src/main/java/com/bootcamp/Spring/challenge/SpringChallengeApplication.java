package com.bootcamp.Spring.challenge;

import com.bootcamp.Spring.challenge.model.Product;
import com.bootcamp.Spring.challenge.model.Seller;
import com.bootcamp.Spring.challenge.model.User;
import com.bootcamp.Spring.challenge.repositories.ProductRepository;
import com.bootcamp.Spring.challenge.repositories.SellerRepository;
import com.bootcamp.Spring.challenge.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
public class SpringChallengeApplication {

	public static void main(String[] args){
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication
				.run(SpringChallengeApplication.class, args);
		SellerRepository sellerRepository = configurableApplicationContext
				.getBean(SellerRepository.class);
		UserRepository userRepository = configurableApplicationContext
				.getBean(UserRepository.class);
		ProductRepository productRepository = configurableApplicationContext
				.getBean(ProductRepository.class);

		User user1 = new User("Amanda Soares");
		User user2 = new User("Carlos Reis");
		User user3 = new User("Ana Becker");
		User user4 = new User("Daniela Ernandes");
		List<User> users = Arrays.asList(user1, user2, user3, user4);

		Seller seller1 = new Seller("Joaquina Z");
		Seller seller2 = new Seller("Joana Silva");
		Seller seller3 = new Seller("Maria Marques");
		Seller seller4 = new Seller("Jose Marquez");
		List<Seller> sellers = Arrays.asList(seller1, seller2, seller3, seller4);

		user1.followSeller(seller4);
		user1.followSeller(seller2);
		user1.followSeller(seller3);
		user2.followSeller(seller4);
		user2.followSeller(seller3);
		user3.followSeller(seller1);

		seller1.followSeller(seller2);
		seller2.followSeller(seller4);
		seller3.followSeller(seller1);
		seller1.followSeller(seller3);

		userRepository.saveAll(users);
		sellerRepository.saveAll(sellers);

		seller1.setFollowers_count(seller1.getFollowers_count());
		seller2.setFollowers_count(seller2.getFollowers_count());
		seller3.setFollowers_count(seller3.getFollowers_count());
		seller4.setFollowers_count(seller4.getFollowers_count());

		userRepository.saveAll(users);
		sellerRepository.saveAll(sellers);

		LocalDate date = LocalDate.now().minusWeeks(1);
		LocalDate date2 = LocalDate.now().minusDays(5);
		LocalDate date3 = LocalDate.now().minusDays(3);
		Product product1 = new Product(1,date,2,100.04,2,"mouse","gamer", "race", "red", "special",true,0.34, seller2);
		Product product4 = new Product(2,date,2,450.04,5,"monitor","gamer", "race", "red", "special",true,0.10, seller2);
		Product product2 = new Product(3,date2,2,100.04,7,"chair","gamer", "race", "blue", "special",false,0.14, seller2);
		Product product3 = new Product(4,date3,2,100.04,9,"chair","gamer", "race", "blue", "special",null,0.14, seller2);
		productRepository.save(product1);
		productRepository.save(product4);
		productRepository.save(product2);
		productRepository.save(product3);
		List<Product> list = new ArrayList<>();
		list.add(product1);
		list.add(product4);
		list.add(product2);
		list.add(product3);

		seller2.setProducts(list);
		sellerRepository.save(seller2);
		seller2.setCountPromos(seller2.getCountPromos());
		sellerRepository.save(seller2);
	}

}
