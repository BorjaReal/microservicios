package com.example.commons_alumnos.alumnos.models.entity;


import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.PrePersist;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @NotBlank
    private String name;

    @NotBlank
    private String lastname;

    @Email
    @NotBlank
    private String email;

    @Column(name = "create_at")
    private Date createAt;

    @Lob
    @JsonIgnore()
    private byte[] photo;

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    } 

    public Integer getPhotoHashCode(){
        return (this.photo != null) ? this.photo.hashCode() : null;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }


	@Override
	public int hashCode() {
		return Objects.hash(studentId);
	}

    

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if(!(obj instanceof Student))
			return false;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Student objectToStudent = (Student) obj;
		return objectToStudent.getStudentId() != null && objectToStudent.getStudentId().equals(this.getStudentId());
	}    
}
