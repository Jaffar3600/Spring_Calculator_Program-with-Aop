package com.cg.app.calculator;

import org.springframework.stereotype.Service;

@Service
public class Calculator {

	public Integer add(int number1, int number2) {
		System.out.println(number1 + number2);
		return number1 + number2;
	}

}
