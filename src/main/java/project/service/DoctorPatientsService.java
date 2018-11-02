package project.service;

import project.persistence.entities.DoctorPatients;

import java.util.List;

public interface DoctorPatientsService {

	DoctorPatients save(DoctorPatients doctorPatients);

	void delete(DoctorPatients doctorPatients);

	List<DoctorPatients> findAll();
}
