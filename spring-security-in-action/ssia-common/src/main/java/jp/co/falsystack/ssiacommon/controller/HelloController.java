package jp.co.falsystack.ssiacommon.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

@RestController
public class HelloController {

    // @GetMapping("/hello")
    public String hello1() {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        return "Hello " + auth.getName();
    }

    @GetMapping("/hello")
    public String hello2(Authentication auth) {

        return "Hello " + auth.getName();
    }

    @GetMapping("/bye")
    @Async // 메서드가 별도의 스레드에서 실행된다.
    public void goodbye() {
        // 별도의 스레드이기 때문에 SecurityContextHolder 의 전략에 의해 Authentication 이 새로 생성된 비동기 스레드에는 없다
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var username = auth.getName();
        System.out.println("username = " + username);
    }

    @GetMapping("/ciao")
    public String ciao() throws ExecutionException, InterruptedException {
        Callable<String> task = () -> {
            var auth = SecurityContextHolder.getContext().getAuthentication();
            return auth.getName();
        };

        var e = Executors.newCachedThreadPool();
        try {
            var contextTask = new DelegatingSecurityContextCallable<String>(task);
            return "Ciao, " + e.submit(contextTask).get() + "!";
        } finally {
            e.shutdown();
        }
    }

    @GetMapping("/hola")
    public String hola() throws ExecutionException, InterruptedException {
        Callable<String> task = () -> {
            var auth = SecurityContextHolder.getContext().getAuthentication();
            return auth.getName();
        };

        var e = new DelegatingSecurityContextExecutorService(Executors.newCachedThreadPool());
        try {
            return "Hola, " + e.submit(task).get() + "!";
        } finally {
            e.shutdown();
        }
    }

}
