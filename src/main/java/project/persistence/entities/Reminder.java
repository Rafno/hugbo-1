package project.persistence.entities;
import javax.persistence.*;
import java.sql.Time;

/**
 * Table for which hours a user should be reminded
 * TODO: Please Helgi refactor this into java.sql.Time rather than string
 */
@Entity
@Table(name = "reminders") // If you want to specify a table name, you can do so here
public class Reminder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long medicineId;
	private Long usersId;
	private String hour1;
	private String hour2;
	private String hour3;
	private String hour4;

	public Reminder(){
	}
	public Reminder(Long medicineId, Long usersId, String hour1, String hour2, String hour3, String hour4){
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


	public String getHour1()
	{
		return hour1;
	}

	public void setHour1(String hour1)
	{
		this.hour1 = hour1;
	}

	public String getHour2()
	{
		return hour2;
	}

	public void setHour2(String hour2)
	{
		this.hour2 = hour2;
	}

	public String getHour3()
	{
		return hour3;
	}

	public void setHour3(String hour3)
	{
		this.hour3 = hour3;
	}

	public String getHour4()
	{
		return hour4;
	}

	public void setHour4(String hour4)
	{
		this.hour4 = hour4;
	}
}
