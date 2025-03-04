package com.vcube.library.Entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String gender;
	private String email;
	private String password;
	private String Address;
	private String status="Active";
	@ManyToMany
	private Set<Books> listofbooks=new HashSet<>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Set<Books> getListofbooks() {
		return listofbooks;
	}
	public void setListofbooks(Set<Books> listofbooks) {
		this.listofbooks = listofbooks;
	}
	public User(int id, String name, String gender, String email, String password, String address, String status,
			Set<Books> listofbooks) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.password = password;
		Address = address;
		this.status = status;
		this.listofbooks = listofbooks;
	}
	
	public User() {}
}