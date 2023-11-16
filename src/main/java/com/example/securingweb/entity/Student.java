package com.example.securingweb.entity;

import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "students",uniqueConstraints=
@UniqueConstraint(columnNames={"email"}))
public class Student {
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	@Column(nullable=false)
    public String name;
	@Column(nullable=false)
    private String email;
	
	
   @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
   @JoinTable(
           name = "students_courses",
           joinColumns = @JoinColumn(name = "student_id"),
           inverseJoinColumns = @JoinColumn(name = "course_id")
           )
   //@ManyToMany(mappedBy="courses")
   private Set<Course> courses = new HashSet<>();


	public Long getId() {
		return id;
	}
	
	
	public String getName() {
		return name;
	}
	
	
	public String getEmail() {
		return email;
	}
	
	
	public Set<Course> getCourses() {
		return courses;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
    
}