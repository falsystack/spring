package jp.co.falsystack.ssiacommon.entities;

import jakarta.persistence.*;
import jp.co.falsystack.ssiacommon.entities.enums.EncryptionAlgorithm;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private EncryptionAlgorithm algorithm;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Authority> authorities;
}
