package messenger.data;

import messenger.domain.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by flogiston on 03.06.19.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User getByUserName(String userName);
    User save(User user);
}
