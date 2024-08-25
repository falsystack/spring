package jp.co.falsystack.ssiacommon.entities;

import jakarta.persistence.*;
import jp.co.falsystack.ssiacommon.entities.enums.Currency;
import lombok.Getter;

@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private double price;

    @Enumerated(EnumType.STRING)
    private Currency currency;
}
