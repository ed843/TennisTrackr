package com.ericduncandev.TennisTrackr;

import com.ericduncandev.TennisTrackr.TennisGame.Records.Players;
import com.ericduncandev.TennisTrackr.TennisGame.Records.Sets;
import com.ericduncandev.TennisTrackr.TennisGame.Records.TennisMatches;
import com.ericduncandev.TennisTrackr.TennisGame.Records.User.User;
import com.ericduncandev.TennisTrackr.TennisGame.Records.User.UserHttpClient;
import com.ericduncandev.TennisTrackr.TennisGame.Records.User.UserRestClient;
import com.ericduncandev.TennisTrackr.TennisGame.TennisMatchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.sql.Date;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TennisTrackrApplication {

	private static final Logger log = LoggerFactory.getLogger(TennisTrackrApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TennisTrackrApplication.class, args);
	}

	@Bean
	UserHttpClient userHttpClient() {
		RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com/");
		 HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
		 return factory.createClient(UserHttpClient.class);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserHttpClient client) {
		return args -> {
			List<User> users = client.findAll();
			System.out.println(users);
		};
	}

}
