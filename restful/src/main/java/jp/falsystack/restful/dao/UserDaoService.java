package jp.falsystack.restful.dao;

import jp.falsystack.restful.bean.User;
import jp.falsystack.restful.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int userCount = 3;

    static {
        users.add(new User(1L, "Kenneth", LocalDate.now(), "password", "1111-1111"));
        users.add(new User(2L, "Beta", LocalDate.now(), "password", "2222-2222"));
        users.add(new User(3L, "Cannon", LocalDate.now(), "password", "3333-3333"));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId((long) ++userCount);
        }

        if (user.getJoinDate() == null) {
            user.setJoinDate(LocalDate.now());
        }

        users.add(user);
        return user;
    }

    public User findById(Long id) {
        return users.stream().filter(user -> Objects.equals(user.getId(), id)).findAny().orElseThrow(() -> new UserNotFoundException(String.format("ID[%s] not found", id)));
    }

    public void delete(Long id) {
        User user = findById(id);
        users.remove(user);
    }
}
