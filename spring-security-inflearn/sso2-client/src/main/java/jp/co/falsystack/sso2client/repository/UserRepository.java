package jp.co.falsystack.sso2client.repository;

import jp.co.falsystack.sso2client.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {

    private Map<String, Object> users = new HashMap<>();

    public Optional<User> findByUsername(String username) {
        if (users.containsKey(username)) {
            return Optional.of((User) users.get(username));
        }
        return Optional.empty();
    }

    public void register(User user) {
        if (users.containsKey(user.getUsername())) {
            return;
        }

        users.put(user.getUsername(), user);
    }

}
