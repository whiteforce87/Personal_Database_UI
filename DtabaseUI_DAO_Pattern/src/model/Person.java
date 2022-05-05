package model;

import java.sql.Date;

public class Person extends BaseEntity{
	
	private String name, lastname, workphone, address, homephone, email, city;
	private int category_id;
	private Date birthdate;
	
	public Person(int id, String name, String lastname, String workphone, int category_id ,String address, String homephone,
			String email, String city, Date birthdate) {
		super(id);
		this.name = name;
		this.lastname = lastname;
		this.workphone = workphone;
		this.address = address;
		this.homephone = homephone;
		this.email = email;
		this.city = city;
		this.birthdate = birthdate;
		this.category_id = category_id;
	}
	public Person() {
		super();
	}
	
	
	public Person(int id) {
		super(id);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getWorkphone() {
		return workphone;
	}
	public void setWorkphone(String workphone) {
		this.workphone = workphone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHomephone() {
		return homephone;
	}
	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	@Override
	public String toString() {
		return name + " " + lastname;
	}

	
	
}
