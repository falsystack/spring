package jp.co.falsystack.ssiacommon.repositories;

import jp.co.falsystack.ssiacommon.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * expect
     *
     * select *
     * from user u
     * where u.username = ?
     */
    Optional<User> findUserByUsername(String username);
}
