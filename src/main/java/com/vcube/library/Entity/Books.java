package com.vcube.library.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Books {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String bookname;
	private String author;
	private String price;
	private String publisheddate;
	private String booklink;
	private String status="Active";
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPublisheddate() {
		return publisheddate;
	}
	public void setPublisheddate(String publisheddate) {
		this.publisheddate = publisheddate;
	}
	public String getBooklink() {
		return booklink;
	}
	public void setBooklink(String booklink) {
		this.booklink = booklink;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Books(int id, String bookname, String author, String price, String publisheddate, String booklink,
			String status) {
		super();
		this.id = id;
		this.bookname = bookname;
		this.author = author;
		this.price = price;
		this.publisheddate = publisheddate;
		this.booklink = booklink;
		this.status = status;
	}
	public Books() {}
}