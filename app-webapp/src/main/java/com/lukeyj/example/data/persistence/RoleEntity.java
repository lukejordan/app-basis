package com.lukeyj.example.data.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RoleEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String role;
    
    private String company;
    

    protected RoleEntity() {}

    public RoleEntity(String role, String company) {
        this.role = role;
        this.company = company;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, role='%s', company='%s']",
                id, role, company);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}