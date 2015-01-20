package workshop.noodles.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import workshop.noodles.model.entities.TestModel;
import workshop.noodles.service.TestService;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

@Path("db")
public class AccessDbController {
	
	@Autowired
	private TestService tService;
	
	@Get("test")
    public String getTest() {
		TestModel t = tService.getTest();
        String s = "Hello the No." + t.getId() + " is " + t.getMsg();
        return "@" + s;
    }
}
