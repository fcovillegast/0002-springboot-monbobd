package workshop.spring.boot.workshopspringboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import workshop.spring.boot.workshopspringboot.models.Course;
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

	public Mono<Student> findBy(Integer idStudent) {
		Mono<Student> studentSaved = studentRepository.findById(idStudent);

		checkExistStudent(idStudent, studentSaved);

		return studentSaved;
	}

	public Mono<Student> create(Student course) {
		Mono<Student> mono = studentRepository.save(course);
		return mono;
	}

	public Mono<Student> update(Integer id, Student student) {

		Mono<Student> studentMono = this.findBy(id);

		Student studentSaved = studentMono.block();
		
		studentSaved.setRut(student.getRut());
		studentSaved.setName(student.getName());

		Mono<Student> mono = studentRepository.save(studentSaved);
		return mono;
	}

	public Mono<Void> delete(Integer id) {
		//Mono<Student> studentSaved = this.findBy(id);

		Mono<Void> monoVoid = studentRepository.deleteById(id);
		return monoVoid;
	}

	private void checkExistStudent(Integer id, Mono<Student> studentSaved) {
		if (studentSaved == null) {
			throw new CourseNotFoundException("Doesnt not exist the Student with id:" + id);
		}
	}

	@Override
	public Flux<Student> findByCourse(Integer idCourse) {
		
		Course course = courseService.findBy(idCourse);
		
		Flux<Student> students = studentRepository.findAllByCourse(course);
		
		return students;
	}

	@Override
	public Mono<Student> addCourse(Integer idCourse, Integer idStudent) {
		
		Course courseSaved = courseService.findBy(idCourse);
		Mono<Student> studentSaved = findBy(idStudent);
		
		studentSaved.doOnSuccess(findPlayer -> {
			findPlayer.setCourse(courseSaved);
			studentRepository.save(findPlayer).subscribe();
		});
		
//		studentSaved.setCourse(courseSaved);
//		
//		Mono<Student> mono = studentRepository.save(studentSaved);
		
		return studentSaved;
	}

}
