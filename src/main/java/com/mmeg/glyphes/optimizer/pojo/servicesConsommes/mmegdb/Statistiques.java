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
		"hp",
		"defense",
		"attack",
		"criticalChance",
		"criticalDamage",
		"accuracy",
		"resistance",
		"speed"
})
@Data
public class Statistiques  extends AdditionalProperties {
	@JsonProperty("hp")
	private int hp;
	@JsonProperty("defense")
	private int defense;
	@JsonProperty("attack")
	private int attack;
	@JsonProperty("criticalChance")
	private int criticalChance;
	@JsonProperty("criticalDamage")
	private int criticalDamage;
	@JsonProperty("accuracy")
	private int accuracy;
	@JsonProperty("resistance")
	private int resistance;
	@JsonProperty("speed")
	private int speed;
}
