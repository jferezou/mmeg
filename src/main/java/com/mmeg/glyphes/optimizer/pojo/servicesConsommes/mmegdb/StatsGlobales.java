package com.mmeg.glyphes.optimizer.pojo.servicesConsommes.mmegdb;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mmeg.glyphes.optimizer.pojo.AdditionalProperties;
import lombok.Data;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
		"base",
		"max"
})
@Data
public class StatsGlobales  extends AdditionalProperties {
	@JsonProperty("base")
	private Statistiques base;
	@JsonProperty("max")
	private Statistiques max;
}
