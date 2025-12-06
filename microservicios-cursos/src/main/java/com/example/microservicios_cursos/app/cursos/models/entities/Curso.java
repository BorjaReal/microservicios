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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.example.commons_alumnos.alumnos.models.entity.Alumno;

@Entity(name="cursos")
public class Curso {

	@Id
	@Column(name="curso_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cursoId;
	
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY)
	private Set<Alumno> students;

	@Column(name="create_at")
	private Date createAt;
	
	@PrePersist
	public void prePersist() {
	    this.createAt = new Date();
	}
	
	public Curso() {
		this.students = new HashSet<Alumno>();
	}

	public Long getCursoId() {
		return cursoId;
	}

	public void setCursoId(Long cursoId) {
		this.cursoId = cursoId;
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
	
	
	public Set<Alumno> getStudents() {
		return students;
	}

	public void setStudents(Set<Alumno> students) {
		this.students = students;
	}
	
	public void addStudent(Alumno student) {
		this.students.add(student);
	}
	
	public void removeStudent(Alumno student) {
		this.students.remove(student);
	}
}
