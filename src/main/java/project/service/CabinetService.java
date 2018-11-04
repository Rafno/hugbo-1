package project.service;

import project.persistence.entities.Cabinet;

import java.util.List;

public interface CabinetService {

	Cabinet save(Cabinet cabinet);

	void delete(Cabinet cabinet);

	List<Cabinet> findAll();

	List<Cabinet> getMedsByUser(Long userId);
}
