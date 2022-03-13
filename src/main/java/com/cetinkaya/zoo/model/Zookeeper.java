package com.cetinkaya.zoo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "t_zookeeper")
public class Zookeeper extends BaseEntity{
	
	@NotEmpty
	@Column(name = "first_name")
	private String firstName;
	
	@NotEmpty
	@Column(name = "last_name")
	private String lastName;
	
	@OneToMany(mappedBy = "zookeeper")
	private Set<Animal> animals = new HashSet<>();

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public Set<Animal> getAnimals() {
		return animals;
	}

	@JsonIgnore
	public void setAnimals(Set<Animal> animals) {
		this.animals = animals;
	}

	@Override
	public String toString() {
		return "Zookeeper [id=" + getId() + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
}
