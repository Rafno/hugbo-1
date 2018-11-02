package project.persistence.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class for the Users itself.
 * The system generates a table schema based on this class for this entity.
 * Be sure to annotate any entities you have with the @Entity annotation.
 */
@Entity
@Table(name = "DoctorPatients") // If you want to specify a table name, you can do so here
public class DoctorPatients
{

	// Declare that this attribute is the id
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "doctorId", unique = true, columnDefinition = "serial")

	private Long doctorId;
	private Long patientId;

	// Notice the empty constructor, because we need to be able to create an empty PostitNote to add
	// to our model so we can use it with our form
	public DoctorPatients(){
	}

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
}