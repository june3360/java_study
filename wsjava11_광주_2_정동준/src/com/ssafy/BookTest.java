package com.ssafy;

import java.io.File;
import java.util.List;

public class BookTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Book book0 = new Book("21424", "Java Basic", "김하나", "Jaen.kr", 15000, "Java 기본 문법", 10);
		Book book1 = new Book("33455", "JDBC Pro", "김철수", "Jaen.kr", 23000, "JDBC 학습", 10);
		Book book2 = new Book("55355", "Servlet/JSP", "박자바", "Jaen.kr", 41000, "Model2 기반", 10);
		Book book3 = new Book("35332", "Android App", "홍길동", "Jaen.kr", 25000, "Lightweight Framework", 10);
		Book book4 = new Book("35355", "OOAD 분석, 설계", "소나무", "Jaen.kr", 30000, "분석 설계 책", 10);
		Magazine m0 = new Magazine("35535", "Java World", "편집부", "Java.com", 7000, 10, "", 2019, 2);
		Magazine m1 = new Magazine("33434", "Mobile World", "편집부", "Java.com", 8000, 10, "", 2013, 8);
		Magazine m2 = new Magazine("75342", "Next Web", "편집부", "Java.com", 10000, 10, "AJAX 소개", 2012, 10);
		Magazine m3 = new Magazine("76543", "Architecture", "편집부", "Java.com", 5000, 10, "java 시스템", 2010, 3);
		Magazine m4 = new Magazine("76534", "Data Modeling", "편집부", "Java.com", 14000, 10, "", 2019, 12);

		Book[] books = { book0, book1, book2, book3, book4, m0, m1, m2, m3, m4 };
		BookManager bm = BookManager.getManager();

		File file = new File("c:\\Temp\\books.dat");
		if (file.canRead()) {
			bm.open();
		} else {
			// 추가 테스트
			for (int i = 0; i < books.length; i++) {
				boolean result = bm.add(books[i]);
				System.out.println(result);
			}
		}
		// 전체 검색
		System.out.println("[전체 검색---------------------------------]");
		List<Book> searched = bm.search();
		for (Book b : searched) {
			if (b == null) {
				break;
			}
			System.out.println(b);
		}
		// isbn으로 검색
		System.out.println("[isbn으로 검색------------------------------]");
		Book byIsbn = bm.search("21424");
		System.out.println(byIsbn);
		// title이 포함된 책
		System.out.println("[타이틀이 포함된 책 검색--------------------]");
		searched = bm.searchByTitle("a");
		for (Book b : searched) {
			if (b == null) {
				break;
			}
			System.out.println(b);
		}
		System.out.println("[Book 만-------------------------------------]");
		searched = bm.searchOnlyBook();
		for (Book b : searched) {
			if (b == null) {
				break;
			}
			System.out.println(b);
		}

		System.out.println("Magazine 만-----------------------------------]");
		searched = bm.searchOnlyMagazine();
		for (Book b : searched) {
			if (b == null) {
				break;
			}
			System.out.println(b);
		}
		System.out.println("올해 출간되 잡지 만---------------------------]");
		searched = bm.searchNewArrival();
		for (Book b : searched) {
			if (b == null) {
				break;
			}
			System.out.println(b);
		}
		System.out.println("10000원미만의 책 만---------------------------]");
		searched = bm.search(10000);
		for (Book b : searched) {
			if (b == null) {
				break;
			}
			System.out.println(b);
		}
		System.out.println("도서 가격 총 합---------------------------]");
		System.out.println(bm.getTotalPrice());
		System.out.println("도서 가격의 평균");
		System.out.println(bm.getAverage());
		
		bm.close();
	}
}
