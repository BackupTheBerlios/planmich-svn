package org.schorpp.planmich.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "turnus")
public class Turnus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Basic(optional = false)
	@Column(name = "name", unique=true)
	private String name;

	
	public Turnus() {}
	
	public Turnus(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
