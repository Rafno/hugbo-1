package project.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.persistence.entities.Medicine;

import java.util.List;

/**
 * By extending the {@link JpaRepository} we have access to powerful methods.
 * For detailed information, see:
 * http://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
 * http://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
 *
 */
@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    Medicine save(Medicine medicine);

    void delete(Medicine medicine);

    List<Medicine> findAll();
    @Query("Select p from Medicine p where Lower(p.name) LIKE concat('%', concat(?1, '%'))")
	List<Medicine> findPlaceContainingKeywordAnywhere(String name);
    // If we need a custom query that maybe doesn't fit the naming convention used by the JPA repository,
    // then we can write it quite easily with the @Query notation, like you see below.
    // This method returns all Medicines where the length of the name is equal or greater than 3 characters.
    @Query(value = "SELECT p FROM Medicine p where length(p.name) >= 3 ")
    List<Medicine> findAllWithNameLongerThan3Chars();

    // Instead of the method findAllReverseOrder() in MedicineService.java,
    // We could have used this method by adding the words
    // ByOrderByIdDesc, which mean: Order By Id in a Descending order
    //
    List<Medicine> findAllByOrderByIdDesc();

    @Query(value = "SELECT p FROM Medicine p WHERE p.id = ?1")
    Medicine findOne(Long id);

    @Query(value = "select p.id from Medicine p where p.name like ?1" +
											" and p.strength like ?2" +
											" and p.pharmaceutical_form like ?3" +
											" and p.ma_issued like ?4")
	Long getMedId(String name, String strength, String form, String ma_issued);

    List<Medicine> findByName(String name);
}
