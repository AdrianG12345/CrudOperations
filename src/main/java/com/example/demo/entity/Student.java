package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private int student_id;
	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	@ManyToOne
	@JoinColumn(name = "gr_fk", nullable = false)
	private Grupuri grup;
	
	public Student() {
		
	}
	public Student(int rollNo, String name, String address, Grupuri grupuri) {
		super();
		this.grup = grupuri;
		this.student_id = rollNo;
		this.name = name;
		this.address = address;
	}
//	public List<Grupuri> getGrupuri()
//	{
//		return grupuri;
//	}
//	public void setGrupuri(List<Grupuri> grupuri)
//	{
//		this.grupuri = grupuri;
//	}
	public Grupuri getGrup() {
		return grup;}
	public void setGrup(Grupuri id) {
		grup = id;
	}
	
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int rollNo) {
		this.student_id = rollNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {///generated automaticaly
		return "Student [rollNo=" + student_id + ", name=" + name + ", Address=" + address + "]";
	}
	
}
