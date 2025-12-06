package com.example.commons_alumnos.alumnos.models.entity;


import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.PrePersist;

@Entity
@Table(name = "alumnos")
public class Alumno {

    @Id
    @Column(name = "alumno_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alumnoId;

    private String name;

    private String lastname;

    private String email;

    @Column(name = "create_at")
    private Date createAt;

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

    public Long getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(Long alumnoId) {
        this.alumnoId = alumnoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

	@Override
	public int hashCode() {
		return Objects.hash(alumnoId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if(!(obj instanceof Alumno))
			return false;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Alumno objectToStudent = (Alumno) obj;
		return objectToStudent.getAlumnoId() != null && objectToStudent.getAlumnoId().equals(this.getAlumnoId());
	}
    
    
}
