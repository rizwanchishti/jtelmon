package org.coenraets.directory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

//http://n2.nabble.com/Jsonp-issue:jaxb-can%27t-convert-to-json-object-to-user-client.-td2414324.html


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "person")
public class Person {
	private int id;
	private String name;
	private int age;


	public Person() {
	}
	
	public Person(int id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}