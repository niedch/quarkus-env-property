package test.home;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Iterator;
import java.util.Map;

@Path("/hello")
public class ExampleResource {
    Logger logger = LoggerFactory.getLogger(ExampleResource.class);

    @ConfigProperty(name = "test.property")
    String testproperty;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        logger.info("test property: " + testproperty);
        printConfigSources("endpoint", logger);

        return "hello: " + testproperty;
    }

    public static void printConfigSources(String where, Logger logger) {
        logger.info(where+":");
        Iterator<ConfigSource> it = ConfigProvider.getConfig().getConfigSources().iterator();
        while(it.hasNext()) {
            ConfigSource source = it.next();
            Map<String, String> map = source.getProperties();
            logger.info(source.getName());
            Iterator<Map.Entry<String, String>> itentry = map.entrySet().iterator();
            while(itentry.hasNext()) {
                Map.Entry<String, String> entry = itentry.next();
                if (entry.getKey().toUpperCase().startsWith("TEST")) {
                    logger.info(String.format("\t%s : %s", entry.getKey(), entry.getValue()));
                }
            }
        }
    }
}