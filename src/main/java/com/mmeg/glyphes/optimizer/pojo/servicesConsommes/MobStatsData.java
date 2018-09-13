package com.mmeg.glyphes.optimizer.pojo.servicesConsommes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mmeg.glyphes.optimizer.pojo.AdditionalProperties;
import lombok.Data;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
		"name",
		"stats"
})
@Data
public class MobStatsData  extends AdditionalProperties {
	@JsonProperty("name")
	private String name;
	@JsonProperty("stats")
	private StatsGlobales statsGlobales;
}
