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

import sk.loffay.cache.StringCache;
import sk.loffay.cache.StringWrapper;

/**
 * curl -iv  -H 'Content-Type: application/json' 'http://localhost:8080/string' -d '{"id": "id1", "str":  "hallo"}'
 * curl -ivX GET 'http://localhost:8080/string?q=hallo'
 *
 * @author Pavol Loffay
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public class RestHandler {

    private static final Logger log = Logger.getLogger(RestApplication.class);

    @Inject
    private StringCache stringCache;

    @GET
    @Path("/string")
    public Response get(@QueryParam("id") String id, @QueryParam("q") String query) {
        log.infof("Get string = id[%s], query[%s]", id, query);


        Response.ResponseBuilder responseBuilder = Response.ok();

        if (query == null) {
            responseBuilder.entity(stringCache.get(id));
        } else {
            responseBuilder.entity(stringCache.query(query));
        }

        return responseBuilder.build();
    }

    @POST
    @Path("/string")
    public Response store(StringWrapper stringWrapper) {
        log.infof("Store to cache = %s", stringWrapper);
        stringCache.store(stringWrapper);

        return Response.ok().entity(stringWrapper).build();
    }
}
