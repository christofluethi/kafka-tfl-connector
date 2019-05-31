
package ch.shaped.kafka.tfl.model;

import org.codehaus.jackson.annotate.*;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "$type",
        "bayType",
        "bayCount",
        "free",
        "occupied"
})
public class Bay {

    @JsonProperty("$type")
    private String $type;
    @JsonProperty("bayType")
    private String bayType;
    @JsonProperty("bayCount")
    private Integer bayCount;
    @JsonProperty("free")
    private Integer free;
    @JsonProperty("occupied")
    private Integer occupied;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("$type")
    public String getType() {
        return $type;
    }

    @JsonProperty("$type")
    public void setType(String type) {
        this.$type = $type;
    }

    @JsonProperty("bayType")
    public String getBayType() {
        return bayType;
    }

    @JsonProperty("bayType")
    public void setBayType(String bayType) {
        this.bayType = bayType;
    }

    @JsonProperty("bayCount")
    public Integer getBayCount() {
        return bayCount;
    }

    @JsonProperty("bayCount")
    public void setBayCount(Integer bayCount) {
        this.bayCount = bayCount;
    }

    @JsonProperty("free")
    public Integer getFree() {
        return free;
    }

    @JsonProperty("free")
    public void setFree(Integer free) {
        this.free = free;
    }

    @JsonProperty("occupied")
    public Integer getOccupied() {
        return occupied;
    }

    @JsonProperty("occupied")
    public void setOccupied(Integer occupied) {
        this.occupied = occupied;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
