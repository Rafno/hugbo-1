package project.service;

import org.springframework.stereotype.Service;
import project.persistence.entities.Reminder;

import java.util.List;
import java.sql.Time;
public interface ReminderService {

	void save(Reminder reminder);

	void delete(Reminder reminder);

	List<Reminder> findAll();
}
