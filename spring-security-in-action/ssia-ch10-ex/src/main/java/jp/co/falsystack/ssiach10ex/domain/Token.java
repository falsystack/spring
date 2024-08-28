package jp.co.falsystack.ssiach10ex.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String identifier;

    @Getter
    private String token;

    @Builder
    private Token(String identifier, String token) {
        this.identifier = identifier;
        this.token = token;
    }

    public static Token from(String identifier, String csrfToken) {
        return Token.builder()
                .identifier(identifier)
                .token(csrfToken)
                .build();
    }

    public void updateToken(String token) {
        this.token = token;
    }
}
