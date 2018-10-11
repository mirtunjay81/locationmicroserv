package business.services;

import db.LocationDAO;
import model.Location;

import java.util.List;

public class locServiceService {
    private LocationDAO locationDAO;

    public locServiceService(final LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    public List<Location> getLocations() {
        return this.locationDAO.getLocations();
    }
}
