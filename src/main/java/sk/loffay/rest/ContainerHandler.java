package sk.loffay.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import sk.loffay.cache.StringCacheContainer;
import sk.loffay.cache.StringWrapper;

/**
 * @author Pavol Loffay
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public class ContainerHandler {

    private static final Logger log = Logger.getLogger(ContainerHandler.class);

    @Inject
    private StringCacheContainer stringCacheContainer;


    @GET
    @Path("/stringContainer")
    public Response getContainer(@QueryParam("id") String id, @QueryParam("q") String query) {
        log.infof("Get string container = id[%s], query[%s]", id, query);


        Response.ResponseBuilder responseBuilder = Response.ok();

        if (query == null) {
            responseBuilder.entity(stringCacheContainer.get(id));
        } else {
            responseBuilder.entity(stringCacheContainer.query(query));
        }

        return responseBuilder.build();
    }

    @POST
    @Path("/stringContainer")
    public Response storeContainer(StringWrapper stringWrapper) {
        log.infof("Store to container cache = %s", stringWrapper);
        stringCacheContainer.store(stringWrapper);

        return Response.ok().entity(stringWrapper).build();
    }
}
