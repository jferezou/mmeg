package com.mmeg.glyphes.optimizer.config.restConfig.consommes;

import com.mmeg.glyphes.optimizer.pojo.servicesConsommes.MobStats;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("")
@Produces(MediaType.APPLICATION_JSON + "; Charset=UTF-8")
@Consumes(MediaType.APPLICATION_JSON)
public interface MobStatsService {

		/**
		 * @param mobName
		 * @param mobElement
		 * @return
		 */
		@GET
		@Path("/creatures/{mobName}-{mobElement}/data")
		MobStats getStatistiques(@PathParam("mobName") final String mobName, @PathParam("mobElement") final String mobElement);
	}
