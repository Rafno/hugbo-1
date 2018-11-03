package project.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.persistence.entities.User_roles;

public interface UserRolesRepository extends JpaRepository<User_roles, Long>
{

	User_roles save(User_roles user);
	
	void delete(User_roles user);
}
