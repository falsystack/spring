package hello.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;

import java.util.List;
import java.util.Set;


@Slf4j
public class CommandLineV2 {
    // --url=devdb --username=dev_user --password=dev_pw mode=on
    public static void main(String[] args) {
        for (String arg : args) {
            log.info("{}", arg);
        }

        ApplicationArguments appArgs = new DefaultApplicationArguments(args);
        log.info("Source Args = {}", List.of(appArgs.getSourceArgs()));
        log.info("NonOptionsArgs = {}", appArgs.getNonOptionArgs());
        log.info("OptionsNames = {}", appArgs.getOptionNames());

        Set<String> optionNames = appArgs.getOptionNames();
        for (String optionName : optionNames) {
            log.info("option arg = {} = {}", optionName, appArgs.getOptionValues(optionName));
        }

        List<String> url = appArgs.getOptionValues("url");
        List<String> username = appArgs.getOptionValues("username");
        List<String> password = appArgs.getOptionValues("password");
        List<String> mode = appArgs.getOptionValues("mode");

        log.info("url = {}, username = {}, password = {}", url, username, password);
    }


}
