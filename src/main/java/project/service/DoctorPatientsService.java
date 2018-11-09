package project.service;

import project.persistence.entities.DoctorPatients;
import project.persistence.entities.Users;

import java.util.List;

public interface DoctorPatientsService
{
	
	DoctorPatients save(DoctorPatients doctorPatients);
	
	void delete(DoctorPatients doctorPatients);
	
	List<DoctorPatients> findAll();
	
	List<Long> getPatientIdByDoctorId(Long doctorId);
	
	List<Users> findAllPatients(Long doctorId);
}