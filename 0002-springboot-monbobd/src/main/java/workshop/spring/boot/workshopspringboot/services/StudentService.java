package workshop.spring.boot.workshopspringboot.services;

import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import workshop.spring.boot.workshopspringboot.models.Student;

public interface StudentService {

	public Flux<Student> findAll();

	public Student findBy(Integer idStudent);

	public Student create(Student course);

	public Student update(Integer id, Student student);

	Mono<Void> delete(Integer id);
	
	public List<Student> findByCourse(Integer idCourse);

}
