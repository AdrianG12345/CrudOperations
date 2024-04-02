package com.example.demo.entity;



import jakarta.persistence.*;

@Entity
@Table(name = "grupuri")
public class Grupuri {
	@Id
	@Column(name = "group_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int group_id;
	@Column(name = "groupNr")
	private int groupNr;

	public Grupuri() {
		
	}
	public Grupuri(int groupId, int groupNr) {
		super();
//		this.students = students;
		this.group_id = groupId;
		this.groupNr = groupNr;
	}

//	public List<Student> getStudents() {
//		return students;
//	}
//
//	public void setStudents(List<Student> students) {
//		this.students = students;
//	}

	public int getId() {
		return group_id;
	}
	public void setId(int groupId) {
		this.group_id = groupId;
	}
	public int getGroupNr() {
		return groupNr;
	}
	public void setGroupNr(int groupNr) {
		this.groupNr = groupNr;
	}
	@Override
	public String toString() {
		return "Group [id=" + group_id + ", groupNr=" + groupNr + "]";
	}
	
	
}
