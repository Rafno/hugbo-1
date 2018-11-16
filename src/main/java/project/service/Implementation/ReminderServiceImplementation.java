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

	@Override
	public List<Reminder> getMedIdByUserId(Long userId){
		return repository.getMedIdByUserId(userId);
	}
	@Override
	public Reminder getRelation(Long userId, Long medicineId){
		return repository.getRelation(userId,medicineId);
	}

	@Override
	public void updateReminder(Long id,
							   String time1,
							   String time2,
							   String time3,
							   String time4,
							   Boolean enable1,
							   Boolean enable2,
							   Boolean enable3,
							   Boolean enable4
	){
		repository.updateReminder(id,time1,time2,time3,time4,enable1,enable2,enable3,enable4);
	}
}