package db;

import io.dropwizard.hibernate.AbstractDAO;
import model.Location;
import org.hibernate.SessionFactory;

import java.util.List;

public class LocationDAO extends AbstractDAO<Location> {
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public LocationDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Location> getLocations() {
        return list(namedQuery("model.Location.findAll"));
        //return (List<Location>) currentSession().createCriteria(Location.class).list();
    }
}
