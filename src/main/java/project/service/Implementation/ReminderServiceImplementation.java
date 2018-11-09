package project.service.Implementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.repositories.ReminderRepository;
import project.persistence.entities.Reminder;
import project.service.ReminderService;
import java.util.List;
@Service
public class ReminderServiceImplementation implements ReminderService {
	// Instance Variables
	ReminderRepository repository;
	// Dependency Injection
	@Autowired
	public ReminderServiceImplementation(ReminderRepository repository) {
		this.repository = repository;
	}
	@Override
	public void save(Reminder reminder){repository.save(reminder);}
	@Override
	public void delete(Reminder reminder) {
		repository.delete(reminder);
	}
	@Override
	public List<Reminder> findAll() {
		return repository.findAll();
	}
}