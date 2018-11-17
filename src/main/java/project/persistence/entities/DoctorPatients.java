package project.persistence.entities;

import javax.persistence.*;


/**
 * The class for the DoctorPatients itself.
 * The system generates a table schema based on this class for this entity.
 * Be sure to annotate any entities you have with the @Entity annotation.
 * A many to many schema that connects doctors to patients.
 * Used to show what doctor has which patients and opposite.
 */
@Entity
@Table(name = "DoctorPatients") // If you want to specify a table name, you can do so here
public class DoctorPatients
{

	// Declare that this attribute is the id
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, columnDefinition = "serial")
	private Long id;
	private Long doctorId;
	private Long patientId;


	public DoctorPatients(Long doctorId, Long patientId) {
		this.doctorId = doctorId;
		this.patientId = patientId;
	}

	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId){
		this.doctorId = doctorId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}