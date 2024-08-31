package jp.co.falsystack.ssiach11prac.repositories;

import jp.co.falsystack.ssiach11prac.domains.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, String> {
    /**
     * select *
     * from otp o
     * where o.username = ?
     */
    Optional<Otp> findOtpByUsername(String username);
}
