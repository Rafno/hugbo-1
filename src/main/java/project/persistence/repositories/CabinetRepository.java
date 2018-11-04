package project.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.persistence.entities.Cabinet;

import java.util.List;

/**
 * By extending the {@link JpaRepository} we have access to powerful methods.
 * For detailed information, see:
 * http://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
 * http://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
 *
 */
@Repository
public interface CabinetRepository extends JpaRepository<Cabinet, Long> {

	Cabinet save(Cabinet cabinet);

	void delete(Cabinet cabinet);

	List<Cabinet> findAll();

	@Query(value = "select c from Cabinet c where c.usersId = ?1" )
	List<Cabinet> getMedsByUser(Long userId);

}