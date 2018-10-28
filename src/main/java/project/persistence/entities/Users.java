package project.persistence.entities;

import javax.persistence.*;

/**
 * The class for the Users itself.
 * The system generates a table schema based on this class for this entity.
 * Be sure to annotate any entities you have with the @Entity annotation.
 */
@Entity
@Table(name = "users") // If you want to specify a table name, you can do so here
public class Users
{
	
	// Declare that this attribute is the id
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, columnDefinition = "serial")
	private Long id;
	private String name;
	private String password;
	private String username;
	
	// Notice the empty constructor, because we need to be able to create an empty PostitNote to add
	// to our model so we can use it with our form
	
	public Users(Long id, String name, String username, String password)
	{
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		
	}
	
	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
}