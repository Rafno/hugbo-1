package project.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.persistence.entities.DoctorPatients;

import java.util.List;

/**
 * By extending the {@link JpaRepository} we have access to powerful methods.
 * For detailed information, see:
 * http://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
 * http://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
 *
 */
@Repository
public interface DoctorPatientsRepository extends JpaRepository<DoctorPatients, Long> {

	DoctorPatients save(DoctorPatients doctorPatients);

	void delete(DoctorPatients doctorPatients);

	List<DoctorPatients> findAll();
	
}