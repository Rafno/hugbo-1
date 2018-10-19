package project.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.Medicine;
import project.persistence.repositories.MedicineRepository;
import project.service.MedicineService;

import java.util.Collections;
import java.util.List;

@Service
public class MedicineServiceImplementation implements MedicineService {

    // Instance Variables
    MedicineRepository repository;

    // Dependency Injection
    @Autowired
    public MedicineServiceImplementation(MedicineRepository repository) {
        this.repository = repository;
    }

    @Override
    public Medicine save(Medicine medicine) {
        return repository.save(medicine);
    }

    @Override
    public void delete(Medicine medicine) {
        repository.delete(medicine);
    }

    @Override
    public List<Medicine> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Medicine> findAllReverseOrder() {
        // Get all the Medicine notes
        List<Medicine> medicines = repository.findAll();

        // Reverse the list
        Collections.reverse(medicines);

        return medicines;
    }

    @Override
    public Medicine findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Medicine> findByName(String name) {
        return repository.findByName(name);
    }
}
