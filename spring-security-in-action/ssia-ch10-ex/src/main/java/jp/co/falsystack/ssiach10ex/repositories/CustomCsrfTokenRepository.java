//package jp.co.falsystack.ssiach10ex.repositories;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jp.co.falsystack.ssiach10ex.domain.Token;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.web.csrf.CsrfToken;
//import org.springframework.security.web.csrf.CsrfTokenRepository;
//import org.springframework.security.web.csrf.DefaultCsrfToken;
//
//import java.util.Optional;
//import java.util.UUID;
//
//@RequiredArgsConstructor
//public class CustomCsrfTokenRepository implements CsrfTokenRepository {
//
//    private final JpaTokenRepository jpaTokenRepository;
//
//    @Override
//    public CsrfToken generateToken(HttpServletRequest request) {
//        var uuid = UUID.randomUUID().toString();
//
//        return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", uuid);
//    }
//
//    @Override
//    public void saveToken(CsrfToken csrfToken, HttpServletRequest request, HttpServletResponse response) {
//        var identifier = request.getHeader("X-IDENTIFIER");
//        var tokenOptional = jpaTokenRepository.findTokenByIdentifier(identifier);
//
//        if (tokenOptional.isPresent()) {
//            var token = tokenOptional.get();
//            token.updateToken(csrfToken.getToken());
//        } else {
//            jpaTokenRepository.save(Token.from(identifier, csrfToken.getToken()));
//        }
//    }
//
//    @Override
//    public CsrfToken loadToken(HttpServletRequest request) {
//        var identifier = request.getHeader("X-IDENTIFIER");
//        var tokenOptional = jpaTokenRepository.findTokenByIdentifier(identifier);
//
//        if (tokenOptional.isPresent()) {
//            var token = tokenOptional.get();
//            return new DefaultCsrfToken(
//                    "X-CSRF-TOKEN",
//                    "_csrf",
//                    token.getToken()
//            );
//        }
//
//        return null;
//    }
//}
