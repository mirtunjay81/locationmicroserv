package api.resources;

import auth.User;
import business.services.locServiceService;
import ch.qos.logback.classic.Logger;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/locations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class locServiceResource {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger("locServiceResource.class");
    private locServiceService locServiceService;

    public locServiceResource(final locServiceService locServiceService) {
        this.locServiceService = locServiceService;
    }

    @GET
    @UnitOfWork
    public Response getLocations(@Auth User user) {
        LOGGER.info("hello to my resources!!!");
        Response response = Response.ok().entity(this.locServiceService.getLocations()).build();
        //String response = new locServiceService().getLocations();
        return response;
    }
}
