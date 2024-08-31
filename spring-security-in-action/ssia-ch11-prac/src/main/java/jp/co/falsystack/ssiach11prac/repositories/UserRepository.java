package jp.co.falsystack.ssiach11prac.repositories;

import jp.co.falsystack.ssiach11prac.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    /**
     * select *
     * from user u
     * where u.username = ?
     */
    Optional<User> findUserByUsername(String username);
}
