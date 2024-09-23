package jp.co.falsystack.tacocloud.data;

import jp.co.falsystack.tacocloud.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
