package com.bootcamp.Spring.challenge;

import com.bootcamp.Spring.challenge.model.Seller;
import com.bootcamp.Spring.challenge.model.User;
import com.bootcamp.Spring.challenge.repositories.SellerRepository;
import com.bootcamp.Spring.challenge.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringChallengeApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication
				.run(SpringChallengeApplication.class, args);
		SellerRepository streamRepository = configurableApplicationContext
				.getBean(SellerRepository.class);
		UserRepository viewerRepository = configurableApplicationContext
				.getBean(UserRepository.class);

		User user1 = new User("user1");
		User user2 = new User("user2");
		List<User> viewers = Arrays.asList(user1, user2);

		Seller seller1 = new Seller("seller1");
		Seller seller2 = new Seller("seller2");
		List<Seller> streams = Arrays.asList(seller1, seller2);

		user1.followSeller(seller1);
		user1.followSeller(seller2);
		user2.followSeller(seller2);


		viewerRepository.saveAll(viewers);
		streamRepository.saveAll(streams);
		seller1.getCountFollowers();
		seller2.getCountFollowers();
	}

}
