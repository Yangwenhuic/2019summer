package com.neu.myapp.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

@Entity
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "CourseName")
	private String courseName;
	@Column(name = "CRN")
	private String crn;
	@Column(name = "CourseInfo")
	private String courseInfo;
	@Column(name = "Compacity")
	private int capacity;
	@Column(name = "Registerable")
	private boolean registerable;


	@ManyToMany(targetEntity=com.neu.myapp.pojo.Student.class,
    cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "STUDENT_COURSE", 
		joinColumns = {@JoinColumn(name = "COURSE_ID", nullable = false, updatable = false) },
		inverseJoinColumns = {@JoinColumn(name = "STUDENT_ID") })
	private Set<Student> students = new HashSet<Student>();

	
	public Course() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCrn() {
		return crn;
	}

	public void setCrn(String crn) {
		this.crn = crn;
	}

	public String getCourseInfo() {
		return courseInfo;
	}

	public void setCourseInfo(String courseInfo) {
		this.courseInfo = courseInfo;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int compacity) {
		this.capacity = compacity;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public boolean isRegisterable() {
		return registerable;
	}

	public void setRegisterable(boolean registerable) {
		this.registerable = registerable;
	}
	
	
	
	
}
