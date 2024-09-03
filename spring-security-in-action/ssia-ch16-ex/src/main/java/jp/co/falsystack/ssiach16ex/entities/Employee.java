package jp.co.falsystack.ssiach16ex.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Employee {

    private String name;
    private List<String> books;
    private List<String> roles;

}
