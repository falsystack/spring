package jp.falsystack.restful.repository;

import jp.falsystack.restful.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
