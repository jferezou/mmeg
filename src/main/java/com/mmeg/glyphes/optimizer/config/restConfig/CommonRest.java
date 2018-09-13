package com.mmeg.glyphes.optimizer.config.restConfig;

import com.mmeg.glyphes.optimizer.annotation.ServiceMethod;
import com.mmeg.glyphes.optimizer.pojo.OptimizeParameters;
import com.mmeg.glyphes.optimizer.service.OptimizeService;
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

@Api(tags = "Optimisation")
@Consumes(MediaType.APPLICATION_JSON)
@Service
@Path("/mmeg")
public class CommonRest {

    @Resource
    OptimizeService optimizeService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonRest.class);



    @POST
    @Path("/optimize")
    @Produces(MediaType.APPLICATION_JSON)
    @ServiceMethod
    @ApiResponses({@ApiResponse(code = 200, message = "")})
    public Response launchOptimizer( final OptimizeParameters optimizeParameters) {

        optimizeService.optimize(optimizeParameters);
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
