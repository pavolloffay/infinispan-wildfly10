package sk.loffay.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.jboss.logging.Logger;

/**
 * @author Pavol Loffay
 */
@ApplicationPath("/")
public class RestApplication extends Application {

    private static final Logger log = Logger.getLogger(RestApplication.class);

    public RestApplication() {
        log.info("Rest application starting");
    }

}
