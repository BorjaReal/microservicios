package com.example.microservicios_cursos.app.cursos.controllers;
import java.util.Set;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.commons_alumnos.alumnos.models.entity.Student;
import com.example.commons_exams.exams.models.entity.Exam;
import com.example.commons_microservicios.commons.controllers.CommonController;
import com.example.microservicios_cursos.app.cursos.models.entities.Course;
import com.example.microservicios_cursos.app.cursos.services.CursoService;

@RestController
@RequestMapping("/cursos")
public class CursoController extends CommonController<Course, CursoService> {

	@GetMapping("/student/{id}")
	public ResponseEntity<?> getCursoByAlumnoId(@PathVariable Long id) {
		Optional<Course> courseOpt = this.service.findCourseByStudentId(id); //devuelve los cursos del alumno 
		
		if(!courseOpt.isEmpty()) {
			Course course = courseOpt.get();
			List<Long> examsIdAnsweredList = (List<Long>) service.findExamsAnsweredByStudent(id);

			//mapeamos y obtenemos todos los examenes del curso
			Set<Exam> exams = course.getExams().stream().map(exam -> {
				//si el examen está en la lista de examenes respondidos, lo marcamos como booleano contestado
				if(examsIdAnsweredList.contains(exam.getExamId())) {
					exam.setAnswered(true);
				}
				return exam;
			}).collect(Collectors.toSet());
			course.setExams(exams);
			return ResponseEntity.ok(course);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Course curso, BindingResult result, @PathVariable Long id) {
		if(result.hasErrors())
			return validate(result);
		
		Optional<Course> courseDb = this.service.findById(id);
		if(courseDb.isEmpty())
			return ResponseEntity.notFound().build();
		Course confirmedCourse = courseDb.get();
		confirmedCourse.setName(curso.getName());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(confirmedCourse));
	}
	
	 @PutMapping("/{id}/asign-students")
	 public ResponseEntity<?> asignStudents(@RequestBody List<Student> studentsCourse, @PathVariable Long id){
		 Optional<Course> courseDb = this.service.findById(id);
			if(courseDb.isEmpty())
				return ResponseEntity.notFound().build();
		Course confirmedCourse = courseDb.get();
		studentsCourse.forEach(s -> confirmedCourse.addStudent(s));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(confirmedCourse));
	}
	 
	@PutMapping("/{id}/remove-student")
	public ResponseEntity<?> asignStudent(@RequestBody Student studentCourse, @PathVariable Long id){
		Optional<Course> courseDb = this.service.findById(id);
			if(courseDb.isEmpty())
				return ResponseEntity.notFound().build();
		Course confirmedCourse = courseDb.get();
		confirmedCourse.removeStudent(studentCourse);
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(confirmedCourse));
	}


	@PutMapping("/{id}/asign-exams")
	public ResponseEntity<?> asignExams(@RequestBody List<Exam> examsCourse, @PathVariable Long id){
		 Optional<Course> courseDb = this.service.findById(id);
			if(courseDb.isEmpty())
				return ResponseEntity.notFound().build();
		Course confirmedCourse = courseDb.get();
		examsCourse.forEach(e -> confirmedCourse.addExam(e));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(confirmedCourse));
	}
	 
	 @PutMapping("/{id}/remove-exam")
	 public ResponseEntity<?> removeExam(@RequestBody Exam examCourse, @PathVariable Long id){
		Optional<Course> courseDb = this.service.findById(id);
		if(courseDb.isEmpty())
			return ResponseEntity.notFound().build();
		Course confirmedCourse = courseDb.get();
		confirmedCourse.removeExam(examCourse);
		System.out.println(confirmedCourse.getExams().size());
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(confirmedCourse));
	 }
 	 
}
