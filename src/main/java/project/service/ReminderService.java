package project.service;

import project.persistence.entities.Reminder;

import java.util.List;
import java.sql.Time;
public interface ReminderService {
	void save(Reminder reminder);

	void delete(Reminder reminder);

	List<Reminder> findAll();
}
