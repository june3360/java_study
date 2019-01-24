package com.ssafy;

public class QuantityException extends Exception {
	
	public QuantityException(int quantity) {
		System.out.println("수량이 부족합니다. 현 재고(" + quantity + ")");
	}
}
