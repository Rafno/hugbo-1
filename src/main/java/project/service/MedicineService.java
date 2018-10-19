package project.service;

import project.persistence.entities.Medicine;

import java.util.List;

public interface MedicineService {

    /**
     * Save a {@link Medicine
	 * }
     * @param medicine {@link Medicine} to be saved
     * @return {@link Medicine} that was saved
     */
    Medicine save(Medicine medicine);

    /**
     * Delete {@link Medicine}
     * @param medicine {@link Medicine} to be deleted
     */
    void delete(Medicine medicine);

    /**
     * Get all {@link Medicine}s
     * @return A list of {@link Medicine}s
     */
    List<Medicine> findAll();

    /**
     * Get all {@link Medicine}s in a reverse order
     * @return A reversed list of {@link Medicine}s
     */
    List<Medicine> findAllReverseOrder();

    /**
     * Find a {@link Medicine} based on {@link Long id}
     * @param id {@link Long}
     * @return A {@link Medicine} with {@link Long id}
     */
    Medicine findOne(Long id);

    /**
     * Find all {@link Medicine}s with {@link String name}
     * @param name {@link String}
     * @return All {@link Medicine}s with the {@link String name} passed
     */
    List<Medicine> findByName(String name);
}
