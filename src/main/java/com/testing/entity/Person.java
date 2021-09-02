package com.testing.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String firtName;
	
	private String lastName;
	
	private String gender;
	
	private String country;
	
	private String age;
	
	private String date;
	
	private String sampleId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirtName() {
		return firtName;
	}

	public void setFirtName(String firtName) {
		this.firtName = firtName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSampleId() {
		return sampleId;
	}

	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firtName=" + firtName + ", lastName=" + lastName + ", gender=" + gender
				+ ", country=" + country + ", age=" + age + ", date=" + date + ", sampleId=" + sampleId + "]";
	}

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person(long id, String firtName, String lastName, String gender, String country, String age, String date,
			String sampleId) {
		super();
		this.id = id;
		this.firtName = firtName;
		this.lastName = lastName;
		this.gender = gender;
		this.country = country;
		this.age = age;
		this.date = date;
		this.sampleId = sampleId;
	}
	
	

}
