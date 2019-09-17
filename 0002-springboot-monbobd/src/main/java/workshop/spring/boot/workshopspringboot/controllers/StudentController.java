package workshop.spring.boot.workshopspringboot.controllers;

 

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import workshop.spring.boot.workshopspringboot.models.Student;
import workshop.spring.boot.workshopspringboot.services.StudentService;


@RestController
@RequestMapping("students")
public class StudentController {
	

	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	
	
	@GetMapping()
	@ResponseBody
	public Flux<Student> findAll() {
		return studentService.findAll();
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Student> create(@Valid  @RequestBody Student student) {
		Student savesStudent = studentService.create(student);
		
		return new ResponseEntity<>(savesStudent, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Student> delete(@PathVariable(value="id") Integer id) {
		studentService.delete(id);;
		
		return new ResponseEntity<>(null, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Student> update(@PathVariable(value="id") Integer id, @Valid @RequestBody Student student) {
		studentService.update(id, student);
		
		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}
	
	@RequestMapping("/inCourse/{id_course}")
	@ResponseBody
	public List<Student> getStudents(@PathVariable(value="id_course") Integer idCourse) {
		return studentService.findByCourse(idCourse);
	}

//	@PutMapping("/{id_course}/{id_student}")
//	@ResponseBody
//	public ResponseEntity<Student> addCourse(@PathVariable(value="id_course") Integer idCourse, @PathVariable(value="id_student") Integer idStudent) {
//		Student student = studentService.addCourse(idCourse, idStudent);
//		
//		return new ResponseEntity<Student>(student, HttpStatus.ACCEPTED); 
//	}
//	
}
