package test.home;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@Provider
public class Inteceptor implements ContainerRequestFilter {
    Logger logger = LoggerFactory.getLogger(Inteceptor.class);

    @ConfigProperty(name = "test.property")
    String testProperty;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        logger.info("test property: " + testProperty);
        ExampleResource.printConfigSources("interceptor", logger);
    }
}
