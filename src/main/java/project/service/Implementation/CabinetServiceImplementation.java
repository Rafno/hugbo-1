package project.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.repositories.CabinetRepository;
import project.service.CabinetService;
import project.persistence.entities.Cabinet;

import java.util.List;

@Service
public class CabinetServiceImplementation implements CabinetService {

	// Instance Variables
	CabinetRepository repository;

	// Dependency Injection
	@Autowired
	public CabinetServiceImplementation(CabinetRepository repository) {
		this.repository = repository;
	}

	@Override
	public Cabinet save(Cabinet cabinet) {
		return repository.save(cabinet);
	}

	@Override
	public void delete(Cabinet cabinet) {
		repository.delete(cabinet);
	}

	@Override
	public List<Cabinet> findAll() {
		return repository.findAll();
	}

	@Override
	public List<Cabinet> getMedsByUser(Long userId){
		return repository.getMedsByUser(userId);
	}
}