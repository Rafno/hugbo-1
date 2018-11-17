package project.persistence.POJO;
//Plain Old Java Object
public class ReminderMeds {

	private String name;
	private String pharmaceutical_form;
	private String strength;
	private Long medicineId;
	private String hour1;
	private boolean enable1;
	private String hour2;
	private boolean enable2;
	private String hour3;
	private boolean enable3;
	private String hour4;
	private boolean enable4;

	public ReminderMeds( String name,
						 String pharmaceutical_form,
						 String strength,
						 long medicineId,
						 String hour1,
						 String hour2,
						 String hour3,
						 String hour4,
						 boolean enable1,
						 boolean enable2,
						 boolean enable3,
						 boolean enable4
	){
		this.name = name;
		this.pharmaceutical_form = pharmaceutical_form;
		this.strength = strength;
		this.medicineId = medicineId;
		this.hour1 = hour1;
		this.hour2 = hour2;
		this.hour3 = hour3;
		this.hour4 = hour4;
		this.enable1 = enable1;
		this.enable2 = enable2;
		this.enable3 = enable3;
		this.enable4 = enable4;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPharmaceutical_form() {
		return pharmaceutical_form;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public Long getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
	}

	public String getHour1() {
		return hour1;
	}

	public void setHour1(String hour1) {
		this.hour1 = hour1;
	}

	public boolean isEnable1() {
		return enable1;
	}

	public void setEnable1(boolean enable1) {
		this.enable1 = enable1;
	}

	public String getHour2() {
		return hour2;
	}

	public void setHour2(String hour2) {
		this.hour2 = hour2;
	}

	public boolean isEnable2() {
		return enable2;
	}

	public void setEnable2(boolean enable2) {
		this.enable2 = enable2;
	}

	public String getHour3() {
		return hour3;
	}

	public void setHour3(String hour3) {
		this.hour3 = hour3;
	}

	public boolean isEnable3() {
		return enable3;
	}

	public void setEnable3(boolean enable3) {
		this.enable3 = enable3;
	}

	public String getHour4() {
		return hour4;
	}

	public void setHour4(String hour4) {
		this.hour4 = hour4;
	}

	public boolean isEnable4() {
		return enable4;
	}

	public void setEnable4(boolean enable4) {
		this.enable4 = enable4;
	}
}
