package com.everis.logicserver.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping
public class controllerLogic {

	@GetMapping("/generatedNumberLong/{length}")
	public Mono<String> generatedNumberLong(@PathVariable("length") int length) {
		Random random = new Random();

		try {
			char[] digits = new char[length];
			digits[0] = (char) (random.nextInt(9) + '1');

			for (int i = 1; i < length; i++) {
				digits[i] = (char) (random.nextInt(10) + '0');
			}

			return Mono.just(Long.parseLong(new String(digits)) + "");
		} catch (Exception e) {
			return null;
		}
	}

	@GetMapping("/encriptBySha1/{input}")
	public Mono<String> encriptSha1(@PathVariable("input") String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);

			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}

			return Mono.just(hashtext);
		} catch (Exception e) {
			return null;
		}
	}
}
