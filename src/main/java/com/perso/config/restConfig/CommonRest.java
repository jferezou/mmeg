package com.perso.config.restConfig;

import com.perso.annotation.ServiceMethod;
import com.perso.service.OptimizeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(tags = "Commun")
@Consumes(MediaType.APPLICATION_JSON)
@Service
@Path("/mmeg")
public class CommonRest {

    @Resource
    OptimizeService optimizeService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonRest.class);



    @GET
    @Path("/optimize")
    @Produces(MediaType.APPLICATION_JSON)
    @ServiceMethod
    @ApiResponses({@ApiResponse(code = 200, message = "")})
    public Response estimateTime(@QueryParam("multiplicateur") final String multiplicateur, @QueryParam("isPalynologie") final Boolean isPalynologie) {


        return Response.ok().build();

    }


    /**
     * Retourne des données
     *
     * @param object
     * @return
     */
    private Response ok(final Object object) {
        return Response.ok(object).build();
    }

    /**
     * Retourne des données
     *
     * @return
     */
    private Response ok() {
        return Response.ok().build();
    }

}
