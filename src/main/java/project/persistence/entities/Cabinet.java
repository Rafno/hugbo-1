package project.persistence.entities;

import javax.persistence.*;

/**
 * The class for the Medicine itself.
 * The system generates a table schema based on this class for this entity.
 * Be sure to annotate any entities you have with the @Entity annotation.
 */
@Entity
@Table(name = "cabinet") // If you want to specify a table name, you can do so here
public class Cabinet {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true)
	private Long id;
	private Long medicineId;
	private Long usersId;
	
	public Cabinet(Long medicineId, Long usersId){
		this.medicineId = medicineId;
		this.usersId = usersId;
	}
	
	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public Long getMedicineId()
	{
		return medicineId;
	}
	
	public void setMedicineId(Long medicineId)
	{
		this.medicineId = medicineId;
	}
	
	public Long getUsersId()
	{
		return usersId;
	}
	
	public void setUsersId(Long usersId)
	{
		this.usersId = usersId;
	}
	@Override
	public String toString()
	{
		return String.format("Cabinet[usersId=%s, id=%s, medicineId=%s]", usersId, id,medicineId);
	}
}