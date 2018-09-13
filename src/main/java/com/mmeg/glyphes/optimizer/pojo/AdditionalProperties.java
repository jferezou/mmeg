package com.mmeg.glyphes.optimizer.pojo;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

public class AdditionalProperties {

    /**
     * Additional Properties
     */
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * Getter
     *
     * @return Additional Properties
     */
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    /**
     * Setter
     *
     * @param name  name of Additional Properties
     * @param value value of Additional Properties
     */
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
