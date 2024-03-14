package sr.qualogy;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import sr.qualogy.configuration.CorsFilter;
import sr.qualogy.configuration.JPAConfiguration;
import sr.qualogy.controller.UserController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ApplicationPath("/api")
public class ApplicationConfig extends Application {

    @Override
    public Map<String, Object> getProperties() {
        JPAConfiguration.getEntityManager();
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("jersey.config.server.provider.packages", "sr.qualogy.controller");
        return properties;
    }


    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(CorsFilter.class);
        classes.add(UserController.class);// Add CorsFilter to registered classes
        return classes;
    }
}
