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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name= "STUDENT")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name= "STUDENT_NAME")
	private String studentName;
	@Column(name = "STUDENT_ID_NUMBER")
	private String studentId;
	@Column(name = "STUDENT_INFO")
	private String studentInfo;
	
    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "students",
            targetEntity = com.neu.myapp.pojo.Course.class
        )
	private Set<Course> courseList = new HashSet<Course>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ACCOUNT_fk")
	private Account studentAccount;
	
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "advisor_fk")
    private Advisor advisor;
    
	public Advisor getAdvisor() {
		return advisor;
	}
	public void setAdvisor(Advisor advisor) {
		this.advisor = advisor;
	}
	public Student() {
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentInfo() {
		return studentInfo;
	}
	public void setStudentInfo(String studentInfo) {
		this.studentInfo = studentInfo;
	}
	public Set<Course> getCourseList() {
		return courseList;
	}
	public void setCourseList(Set<Course> courseList) {
		this.courseList = courseList;
	}
	public Account getStudentAccount() {
		return studentAccount;
	}
	public void setStudentAccount(Account studentAccount) {
		this.studentAccount = studentAccount;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public void addCourse(Course course) {
		this.courseList.add(course);
		course.getStudents().add(this);
	}
	
}
