package com.ssafy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author itsme
 * @Date : 2019. 1. 13.
 */
public class BookManager implements IBookManager {
	private List<Book> books;
	int index;
	private static BookManager manager = new BookManager();

	public static BookManager getManager() {
		return manager;
	}

	private BookManager() {
		books = new ArrayList<>();
	}

	public boolean add(Book book) {
		boolean result = books.add(book);
		return result;
	}

	public List<Book> search() {
		return books;
	}

	public Book search(String isbn) {
		Book book = null;
		for (Book b : books) {
			if (b == null) {
				break;
			} else if (b.getIsbn().equals(isbn)) {
				book = b;
				break;
			}
		}
		return book;
	}

	public List<Book> searchByTitle(String title) {
		List<Book> temp = new ArrayList<>();
		for (Book book : books) {
			if (book == null) {
				break;
			} else if (book.getTitle().contains(title)) {
				temp.add(book);
			}
		}
		return temp;
	}

	public List<Book> searchOnlyMagazine() {
		List<Book> temp = new ArrayList<>();
		for (Book book : books) {
			if (book == null) {
				break;
			} else if (book instanceof Magazine) {
				temp.add(book);
			}
		}
		return temp;
	}

	public List<Book> searchOnlyBook() {
		List<Book> temp = new ArrayList<>();
		for (Book book : books) {
			if (book == null) {
				break;
			} else if (!(book instanceof Magazine)) {
				temp.add(book);
			}
		}
		return temp;
	}

	public List<Book> searchNewArrival() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		List<Book> temp = new ArrayList<>();
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i) instanceof Magazine) {
				Magazine m = (Magazine) books.get(i);
				if (m.getYear() == year) {
					temp.add(m);
				}
			}
		}
		return temp;
	}

	public List<Book> searchByPublisher(String publisher) {
		List<Book> temp = new ArrayList<>();
		for (Book book : books) {
			if (book == null) {
				break;
			} else if (book.getPublisher().equals(publisher)) {
				temp.add(book);
			}
		}
		return temp;
	}

	public List<Book> search(int price) {
		List<Book> temp = new ArrayList<>();
		for (Book book : books) {
			if (book == null) {
				break;
			} else if (book.getPrice() < price) {
				temp.add(book);
			}
		}
		return temp;
	}

	public int getTotalPrice() {
		int sum = 0;
		for (int i = 0; i < index; i++) {
			sum += books.get(i).getPrice();
		}
		return sum;
	}

	public double getAverage() {
		return 1.0 * getTotalPrice() / index;
	}

	@Override
	public void sell(String isbn, int quantity) throws ISBNNotFoundException, QuantityException {
		Book book = search(isbn);

		if (book == null) {
			throw new ISBNNotFoundException(isbn);
		} else {
			int temp = book.getQuantity();
			if (temp < quantity) {
				throw new QuantityException(temp);
			} else {
				book.setQuantity(temp - quantity);
			}
		}
	}

	@Override
	public void buy(String isbn, int quantity) throws ISBNNotFoundException {
		Book book = search(isbn);
		if (book == null) {
			throw new ISBNNotFoundException(isbn);
		} else {
			int temp = book.getQuantity();
			book.setQuantity(temp + quantity);
		}
	}

	@Override
	public int getTotalAmount() {
		int sum = 0;
		for(Book b: books) {
			sum+=b.getPrice() * b.getQuantity();
		}
		return sum;
	}

	@Override
	public void open() {
		File file = new File("c:\\Temp\\books.dat");
		// input, byte, file --> FileInputStream
		// 객체 저장: Object
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
			Object obj = ois.readObject();
			books = (List)obj;
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		File file = new File("c:\\Temp\\books.dat");
		// output, byte, file --> FileOutputStream
		// 객체 저장: Object
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
			oos.writeObject(books);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
