package workshop.noodles.controllers;


import org.apache.log4j.Logger;

import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

@Path("/hello/")
public class HelloController {

	final static Logger log = Logger.getLogger(HelloController.class);
    @Get("world")
    public String index() {
    	log.debug("test");
        return "@hello world";
    }
}