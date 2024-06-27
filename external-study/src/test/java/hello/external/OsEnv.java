package hello.external;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class OsEnv {

    public static void main(String[] args) {
        Map<String, String> envMap = System.getenv();
        envMap.forEach((k, v) -> {
            System.out.println(k + "=" + System.getenv(k));
            System.out.println(k + "=" + v);
        });


    }
}
