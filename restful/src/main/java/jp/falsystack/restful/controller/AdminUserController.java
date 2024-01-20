package jp.falsystack.restful.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import jp.falsystack.restful.bean.AdminUser;
import jp.falsystack.restful.bean.User;
import jp.falsystack.restful.dao.UserDaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminUserController {

    private final UserDaoService service;

    @GetMapping("/users/{id}")
    public MappingJacksonValue retrieveUser4Admin(@PathVariable Long id) {
        User findUser = service.findById(id);

        AdminUser adminUser = findUser.toAdminUser();
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue value = new MappingJacksonValue(adminUser);
        value.setFilters(filters);

        return value;
    }

    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsers4Admin() {
        List<User> allUsers = service.findAll();

        List<AdminUser> adminUsers = allUsers.stream().map(User::toAdminUser).toList();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue value = new MappingJacksonValue(adminUsers);
        value.setFilters(filters);

        return value;
    }

}
