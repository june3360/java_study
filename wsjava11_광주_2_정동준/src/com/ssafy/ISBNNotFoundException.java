package com.ssafy;

public class ISBNNotFoundException extends Exception{
	public ISBNNotFoundException(String isbn) {
		super(isbn+"을 발견하지 못함");
	}
}
