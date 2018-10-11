import api.resources.locServiceResource;
import auth.AppBasicAuthenticator;
import auth.User;
import business.services.locServiceService;
import ch.qos.logback.classic.Logger;
import db.LocationDAO;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import model.Location;
import org.slf4j.LoggerFactory;

public class locServiceApplication extends Application<locServiceConfiguration> {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(locServiceApplication.class);

    public static void main(String[] args) throws Exception {
        new locServiceApplication().run(args);
    }


    private final HibernateBundle<locServiceConfiguration> hibernateBundle = new HibernateBundle<locServiceConfiguration>(Location.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(locServiceConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(final Bootstrap<locServiceConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(locServiceConfiguration configuration, Environment environment) throws Exception {
        LOGGER.info("hello welcome to run method");
        final LocationDAO locationDAO = new LocationDAO(hibernateBundle.getSessionFactory());
        final locServiceService locServiceService = new locServiceService(locationDAO);
        environment.jersey().register(new locServiceResource(locServiceService));
        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
        .setAuthenticator(new AppBasicAuthenticator(configuration.getLogin(), configuration.getPassword()))
        .setRealm("BASIC-AUTH-REALM")
                .buildAuthFilter()));

        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    }
}
