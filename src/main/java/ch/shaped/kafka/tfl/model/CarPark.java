
package ch.shaped.kafka.tfl.model;

import org.codehaus.jackson.annotate.*;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "$type",
        "id",
        "bays",
        "name",
        "carParkDetailsUrl"
})
public class CarPark {

    @JsonProperty("$type")
    private String $type;
    @JsonProperty("id")
    private String id;
    @JsonProperty("bays")
    private List<Bay> bays = null;
    @JsonProperty("name")
    private String name;
    @JsonProperty("carParkDetailsUrl")
    private String carParkDetailsUrl;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("$type")
    public String getType() {
        return $type;
    }

    @JsonProperty("$type")
    public void setType(String type) {
        this.$type = $type;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("bays")
    public List<Bay> getBays() {
        return bays;
    }

    @JsonProperty("bays")
    public void setBays(List<Bay> bays) {
        this.bays = bays;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("carParkDetailsUrl")
    public String getCarParkDetailsUrl() {
        return carParkDetailsUrl;
    }

    @JsonProperty("carParkDetailsUrl")
    public void setCarParkDetailsUrl(String carParkDetailsUrl) {
        this.carParkDetailsUrl = carParkDetailsUrl;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Integer getBayCount() {
        return getBays().stream().map(Bay::getBayCount).mapToInt(Integer::intValue).sum();
    }

    public Integer getFree() {
        return getBays().stream().map(Bay::getFree).mapToInt(Integer::intValue).sum();
    }

    public Integer getOccupied() {
        return getBays().stream().map(Bay::getOccupied).mapToInt(Integer::intValue).sum();
    }

    public String getState() {
        return getBayCount()+" "+getFree()+" "+getOccupied();
    }
}
