package org.achumakin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.achumakin.model.NameModel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/name")
@Slf4j
public class NameResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        var name = NameModel.getRandom();
        log.info("Generated name model: {}", name);

        try {
            var nameJson = new ObjectMapper().writeValueAsString(name);
            return Response.ok(nameJson).build();
        } catch (JsonProcessingException e) {
            return Response.serverError().build();
        }
    }

}
