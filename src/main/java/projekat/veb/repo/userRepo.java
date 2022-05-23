package projekat.veb.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import projekat.veb.models.User;

public interface userRepo extends JpaRepository<User, String> {

}
