package project.service;

import project.persistence.entities.reminder;

import java.util.List;

public interface reminderService {
	void save(reminder reminder);

	void delete(reminder reminder);

	List<reminder> findAll();
}
