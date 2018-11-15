package project.service;

import org.springframework.stereotype.Service;
import project.persistence.entities.Reminder;

import java.util.List;
import java.sql.Time;
public interface ReminderService {

	void save(Reminder reminder);

	void delete(Reminder reminder);

	List<Reminder> findAll();

	List<Reminder> getMedIdByUserId(Long userId);

	Long getIdOfRelation(Long userId, Long medicineId);

	void updateReminder(Long id,
						String time1,
						String time2,
						String time3,
						String time4,
						Boolean enable1,
						Boolean enable2,
						Boolean enable3,
						Boolean enable4
	);
}
