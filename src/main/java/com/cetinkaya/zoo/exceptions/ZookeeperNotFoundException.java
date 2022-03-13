package com.cetinkaya.zoo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ZookeeperNotFoundException extends RuntimeException {

	public ZookeeperNotFoundException(String message) {
		super(message);
	}
	

}
