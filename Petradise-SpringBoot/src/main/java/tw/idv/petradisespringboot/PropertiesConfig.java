package tw.idv.petradisespringboot;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:sensitives.properties")
public class PropertiesConfig {
}
