package project.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.persistence.entities.Reminder;

import javax.transaction.Transactional;
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

	@Query(value = "SELECT r FROM Reminder r WHERE r.usersId = ?1")
	Reminder findOneByUserID(Long id);

	@Query("select r.medicineId from Reminder r where r.usersId = ?1")
	List<Reminder> getMedIdByUserId(Long userId);

	@Query("select r from Reminder r where r.usersId = ?1 and r.medicineId = ?2")
	Reminder getRelation(Long userId, Long medicineId);

	@Transactional
	@Modifying
	@Query("UPDATE Reminder r SET 	 r.hour1 = :time1 , r.hour2 = :time2, " +
		"r.hour3 = :time3, r.hour4 = :time4, " +
		"r.enable1 = :enable1, r.enable2 = :enable2, " +
		"r.enable3 = :enable3, r.enable4 = :enable4 " +
		"where r.id = :id")
	void updateReminder(@Param("id") Long id,
						@Param("time1") String time1,
						@Param("time2") String time2,
						@Param("time3") String time3,
						@Param("time4") String time4,
						@Param("enable1") boolean enable1,
						@Param("enable2") boolean enable2,
						@Param("enable3") boolean enable3,
						@Param("enable4") boolean enable4
	);
}