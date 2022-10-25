package recipes.persistancelayer;

import org.springframework.data.repository.CrudRepository;
import recipes.businesslayer.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
