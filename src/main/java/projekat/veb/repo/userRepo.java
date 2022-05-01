package projekat.veb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projekat.veb.models.User;

@Repository
public interface userRepo extends JpaRepository<User, String> {

}
