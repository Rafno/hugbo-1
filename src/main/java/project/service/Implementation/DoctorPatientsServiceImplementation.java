package project.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.repositories.DoctorPatientsRepository;

import project.persistence.entities.DoctorPatients;
import project.service.DoctorPatientsService;

import java.util.List;

@Service
public class DoctorPatientsServiceImplementation implements DoctorPatientsService{

	// Instance Variables
	DoctorPatientsRepository repository;

	// Dependency Injection
	@Autowired
	public DoctorPatientsServiceImplementation(DoctorPatientsRepository repository) {
		this.repository = repository;
	}

	@Override
	public DoctorPatients save(DoctorPatients doctorPatients) {
		return repository.save(doctorPatients);
	}

	@Override
	public void delete(DoctorPatients doctorPatients) {
		repository.delete(doctorPatients);
	}

	@Override
	public List<DoctorPatients> findAll() {
		return repository.findAll();
	}
}