package project.service.Implementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.repositories.reminderRepository;
import project.service.CabinetService;
import project.persistence.entities.reminder;
import project.service.reminderService;

import java.util.List;

public class reminderServiceImplementation implements reminderService {
	// Instance Variables
	reminderRepository repository;

	// Dependency Injection
	@Autowired
	public reminderServiceImplementation(reminderRepository repository) {
		this.repository = repository;
	}

	@Override
	public void save(reminder reminder){}

	@Override
	public void delete(reminder reminder) {
		repository.delete(reminder);
	}

	@Override
	public List<reminder> findAll() {
		return repository.findAll();
	}
}
