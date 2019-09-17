package workshop.spring.boot.workshopspringboot.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

 
public class Student {

	@Id
	private int id;

	@Pattern(regexp = "^(\\d{1,3}(?:\\.\\d{1,3}){2}-[\\dkK])$")
	private String rut;

	@NotNull
	private String name;

	@JsonProperty(access = Access.WRITE_ONLY)
	private Course course;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
