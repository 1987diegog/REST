package uy.com.demente.ideas.REST;

import java.io.Serializable;

/**
 * @author DieSil
 */
public class PersonDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String lastName;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
