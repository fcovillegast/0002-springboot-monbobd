package workshop.spring.boot.workshopspringboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import workshop.spring.boot.workshopspringboot.models.Student;
import workshop.spring.boot.workshopspringboot.repositories.StudentsRepository;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentsRepository studentRepository;
	private CourseService courseService;

	@Autowired
	public StudentServiceImpl(StudentsRepository studentRepository, CourseService courseService) {
		super();
		this.studentRepository = studentRepository;
		this.courseService = courseService;
	}

	public Flux<Student> findAll() {
		Flux<Student> courses = studentRepository.findAll();
		return courses;
	}

	public Student findBy(Integer idStudent) {
		Mono<Student> studentSaved = studentRepository.findById(idStudent);

		checkExistStudent(idStudent, studentSaved);

		return studentSaved.block();
	}

	public Student create(Student course) {
		Mono<Student> mono = studentRepository.save(course);
		return mono.block();
	}

	public Student update(Integer id, Student student) {

		Student studentSaved = this.findBy(id);

		studentSaved.setRut(student.getRut());
		studentSaved.setName(student.getName());

		Mono<Student> mono = studentRepository.save(studentSaved);
		return mono.block();
	}

	public Mono<Void> delete(Integer id) {
		Student studentSaved = this.findBy(id);

		Mono<Void> monoVoid = studentRepository.delete(studentSaved);
		return monoVoid;
	}

	private void checkExistStudent(Integer id, Mono<Student> studentSaved) {
		if (studentSaved == null) {
			throw new CourseNotFoundException("Doesnt not exist the Student with id:" + id);
		}
	}

	@Override
	public List<Student> findByCourse(Integer idCourse) {
		// TODO Auto-generated method stub
		return null;
	}

}
