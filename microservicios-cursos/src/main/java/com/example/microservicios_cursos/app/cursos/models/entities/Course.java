package com.example.microservicios_cursos.app.cursos.models.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.example.commons_alumnos.alumnos.models.entity.Student;
import com.example.commons_exams.exams.models.entity.Exam;


@Entity
@Table(name="course")
public class Course {

	@Id
	@Column(name="course_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long courseId;
	
	@NotBlank
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY)
	private Set<Student> students;

	@Column(name="create_at")
	private Date createAt;

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Exam> exams;

	@PrePersist
	public void prePersist() {
	    this.createAt = new Date();
	}
	
	public Course() {
		this.students = new HashSet<Student>();
		this.exams = new HashSet<Exam>();
	}

	public Long getCurseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public Set<Student> getStudents() {
		return students;
	}
	
	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<Exam> getExams() {
		return exams;
	}

	public void setExams(Set<Exam> exams) {
		this.exams = exams;
	}

	public void addExam(Exam exam) {
		this.exams.add(exam);	
	}

	public void removeExam(Exam exam) {
		this.exams.removeIf(e -> e.getExamId() != null && e.getExamId().equals(exam.getExamId()));
	}
	
	public void addStudent(Student student) {
		this.students.add(student);
	}
	
	public void removeStudent(Student student) {
		this.students.remove(student);
	}
}
