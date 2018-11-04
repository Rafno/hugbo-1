package project.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.persistence.entities.Reminder;
import java.sql.Time;
import java.util.List;

/**
 * By extending the {@link JpaRepository} we have access to powerful methods.
 * For detailed information, see:
 * http://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
 * http://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
 *
 */
@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {

	Reminder save(Reminder reminder);

	void delete(Reminder reminder);

	List<Reminder> findAll();



}