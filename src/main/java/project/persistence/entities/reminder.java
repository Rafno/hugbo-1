package project.persistence.entities;
import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "reminders") // If you want to specify a table name, you can do so here
public class reminder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true)
	private Long id;
	private Long medicineId;
	private Long usersId;
	private Time hour1;
	private Time hour2;
	private Time hour3;
	private Time hour4;

	public reminder(){
	}
	public reminder(Long medicineId, Long usersId, Time hour1, Time hour2, Time hour3, Time hour4){
		this.medicineId = medicineId;
		this.usersId = usersId;
		this.hour1 = hour1;
		this.hour2 = hour2;
		this.hour3 = hour3;
		this.hour4 = hour4;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
	}

	public Long getUsersId() {
		return usersId;
	}

	public void setUsersId(Long usersId) {
		this.usersId = usersId;
	}
	
	
	public Time getHour1()
	{
		return hour1;
	}
	
	public void setHour1(Time hour1)
	{
		this.hour1 = hour1;
	}
	
	public Time getHour2()
	{
		return hour2;
	}
	
	public void setHour2(Time hour2)
	{
		this.hour2 = hour2;
	}
	
	public Time getHour3()
	{
		return hour3;
	}
	
	public void setHour3(Time hour3)
	{
		this.hour3 = hour3;
	}
	
	public Time getHour4()
	{
		return hour4;
	}
	
	public void setHour4(Time hour4)
	{
		this.hour4 = hour4;
	}
}
