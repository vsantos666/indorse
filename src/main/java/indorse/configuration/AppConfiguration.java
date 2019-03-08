package indorse.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by vsantos on 7/03/2019.
 */
@Configuration
@PropertySource("classpath:/main.properties")
@ComponentScan(basePackages = {"indorse"})
public class AppConfiguration {

    private final Logger logger = LoggerFactory.getLogger(AppConfiguration.class);

    public AppConfiguration() {
        logger.info(">>>>>>>>>>>>>>>>>>>>>>> begining AUTENTICACION ...");
    }
}

