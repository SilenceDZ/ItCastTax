package cn.itcasttax.entity;

import java.io.Serializable;

import javax.persistence.Entity;

import org.springframework.stereotype.Repository;
@Entity
@Repository
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	
	public Person() {
	}
	
	public Person(String name) {
		this.name = name;
	}
	
	public Person(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}
	
	
}
