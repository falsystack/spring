package jp.co.falsystack.ssiacommon.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @JoinColumn(name = "user")
    @ManyToOne
    private User user;

}
