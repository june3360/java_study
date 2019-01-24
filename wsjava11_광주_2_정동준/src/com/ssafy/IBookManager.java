package com.ssafy;

import java.util.List;

public interface IBookManager {
	public boolean add(Book book);
	public List<Book> search();
	public Book search(String isbn);
	public List<Book> searchByTitle(String title);
	public List<Book> searchOnlyMagazine();
	public List<Book> searchOnlyBook();
	public List<Book> searchNewArrival();
	public List<Book> searchByPublisher(String publisher);
	public List<Book> search(int price);
	public int getTotalPrice();
	public double getAverage();
	/**
	 * isbn의 책을 찾은 후 없으면 예외 처리, 있다면 수량 차감. 단 수량이 부족하면 예외 처리
	 * @param isbn
	 * @param quantity
	 * @throws ISBNNotFoundException
	 * @throws QuantityException
	 */
	public void sell(String isbn, int quantity) throws ISBNNotFoundException, QuantityException;
	
	/**
	 * isbn의 책을 찾은 후 없으면 예외 처리, 있다면 수량 증가
	 * @param isbn
	 * @param quantity
	 * @throws ISBNNotFoundException
	 */
	public void buy(String isbn, int quantity) throws ISBNNotFoundException;
	
	public int getTotalAmount();
	
	public void open();
	
	public void close();
	public void send();
	
}