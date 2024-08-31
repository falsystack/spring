package jp.co.falsystack.ssiach11prac.service;

import jp.co.falsystack.ssiach11prac.domains.Otp;
import jp.co.falsystack.ssiach11prac.domains.User;
import jp.co.falsystack.ssiach11prac.repositories.OtpRepository;
import jp.co.falsystack.ssiach11prac.repositories.UserRepository;
import jp.co.falsystack.ssiach11prac.utils.GenerateCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final OtpRepository otpRepository;

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void auth(User user) {
        var optionalUser = userRepository.findUserByUsername(user.getUsername());

        if (optionalUser.isPresent()) {
            var foundUser = optionalUser.get();
            if (passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
                renewOtp(foundUser);
            } else {
                throw new BadCredentialsException("Wrong credentials");
            }
        } else {
            throw new BadCredentialsException("Wrong credentials");
        }
    }

    private void renewOtp(User user) {
        var code = GenerateCodeUtil.generateCode();

        var optionalOtp = otpRepository.findOtpByUsername(user.getUsername());
        if (optionalOtp.isPresent()) {
            var otp = optionalOtp.get();
            otp.setCode(code);
        } else {
            var otp = new Otp();
            otp.setUsername(user.getUsername());
            otp.setCode(code);
            otpRepository.save(otp);
        }
    }

    public boolean check(Otp otpValidate) {
        var optionalOtp = otpRepository.findOtpByUsername(otpValidate.getUsername());

        if (optionalOtp.isPresent()) {
            var otp = optionalOtp.get();
            if (otpValidate.getCode().equals(otp.getCode())) {
                return true;
            }
        }

        return false;
    }
}
