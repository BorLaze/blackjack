package ua.in.lbn.bj;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.info.BuildProperties;

@SpringBootApplication
public class BjClientApp implements ApplicationRunner {

    private final BuildProperties buildProperties;

    public BjClientApp(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    public static void main(String[] args) {
        SpringApplication.run(BjClientApp.class, args);
    }

    @SuppressWarnings("java:S106")
    @Override
    public void run(ApplicationArguments args) {
        if (args.getSourceArgs().length == 0 || args.containsOption("help")) {
            System.out.println(buildProperties.getName());
            System.out.println("  --help        print this help");
            System.out.println("  --version     program version");
            return;
        }

        if (args.getOptionNames().contains("version")) {
            System.out.println(buildProperties.getName());
            System.out.println(buildProperties.getVersion());
        }
    }
}
