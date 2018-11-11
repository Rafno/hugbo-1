package project.persistence.entities;

import javax.persistence.*;
import javax.persistence.Entity;

import javax.persistence.Table;

/**
 * Simple schema that holds over which roles which users have, only two available:
 * Doctor users and Users users.
 * This is necessary for Spring Security, as it will not work without both this entity
 * and a much needed role column in users.
 */
@Entity
@Table(name = "user_roles")
public class User_roles
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String username;
	private String role;
	
	
	public User_roles(){
	}
	public User_roles(String username, String role){
		this.username = username;
		this.role = role;
	}
	
	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getrole()
	{
		return role;
	}
	
	public void setrole(String role)
	{
		this.role = role;
	}
}
