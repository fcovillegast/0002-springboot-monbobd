package workshop.spring.boot.workshopspringboot.models;

import javax.validation.constraints.NotNull;


public class Course {

	private int id;
	
	@NotNull
	private String name;

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
	
	
	
}
