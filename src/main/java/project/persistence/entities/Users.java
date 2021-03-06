package project.persistence.entities;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

/**
 * The class for the Users itself.
 * The system generates a table schema based on this class for this entity.
 * Be sure to annotate any entities you have with the @Entity annotation.
 * Every information about a user,
 * TODO: Maybe change so that not every information about a user is available.
 */
@Entity
@Table(name = "users") // If you want to specify a table name, you can do so here
public class Users
{
	//TODO, þegar table er droppað þá resettast ekki ID.
	// Declare that this attribute is the id
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, columnDefinition = "serial")
	private Long id;
	private String name;
	private String password;
	private String username;
	private String imagePublicId;
	private String role;
	private String homeAddress;
	private String homeTown;
	private String zipCode;
	private String email;
	private String confirmationNumber;
	private String date;
	private Long epochTime;
	private boolean confirmed;
	private boolean enabled;
	// Notice the empty constructor, because we need to be able to create an empty PostitNote to add
	// to our model so we can use it with our form
	
	public Users(){}
	public Users(String name,
				 String username,
				 String password,
				 String hlutverk,
				 String img ,
				 String homeAddress,
				 String homeTown,
				 String zipCode,
				 String email ,
				 String confirmationNumber,
				 String date,
				 Long epochTime,
				 boolean confirmed,
				 boolean enabled
				)
	{
		this.name = name;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.imagePublicId = img;
		this.role = hlutverk;
		this.homeAddress = homeAddress;
		this.homeTown = homeTown;
		this.zipCode = zipCode;
		this.email = email;
		this.confirmationNumber = confirmationNumber;
		this.date = date;
		this.epochTime = epochTime;
		this.confirmed = confirmed;

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

	public String getImagePublicId(){
		return this.imagePublicId;
	}
	public void setImagePublicId(String imagePublicId){
		this.imagePublicId = imagePublicId;
	}


	public String getRole() {
		return this.role;
	}

	public void setRole(String hlutverk) {
		this.role = hlutverk;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getHomeTown() {
		return homeTown;
	}

	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return this.email;
	}

	public String getConfirmationNumber(){
		return this.confirmationNumber;
	}

	public void setConfirmationNumber(String confirmationNumber){
		this.confirmationNumber = confirmationNumber;
	}
	public boolean isEnabled()
	{
		return enabled;
	}
	
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}

	public String getDate(){
		return this.date;
	}
	public void setDate(String date){
		this.date = date;
	}

	public long getEpochTime(){
		return this.epochTime;
	}

	public void setEpochTime(Long epochTime){
		this.epochTime = epochTime;
	}

	public boolean getConfirmed(){
		return this.confirmed;
	}

	public void setConfirmed(boolean confirmed){
		this.confirmed = confirmed;
	}
}