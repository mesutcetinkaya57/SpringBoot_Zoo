package com.cetinkaya.zoo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "t_animal")
public class Animal extends BaseEntity {

	@Column(name = "name")
	private String name;
	
	@Column(name = "birth_date")
	private Date birthOfDate;
	
	@ManyToOne
	@JoinColumn(name = "zookeeper_id")
	private Zookeeper zookeeper;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthOfDate() {
		return birthOfDate;
	}
	public void setBirthOfDate(Date birthOfDate) {
		this.birthOfDate = birthOfDate;
	}
	public Zookeeper getZookeeper() {
		return zookeeper;
	}
	public void setZookeeper(Zookeeper zookeeper) {
		this.zookeeper = zookeeper;
	}
	@Override
	public String toString() {
		return "Animal [id=" + getId() + ", name=" + name + ", birthOfDate=" + birthOfDate + ", zookeeper=" + zookeeper + "]";
	}
	
	
}
