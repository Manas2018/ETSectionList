
package com.til.et.testLocal.model.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ssname",
    "msid",
    "subsec3"
})
public class Subsec2 {

    @JsonProperty("ssname")
    private String ssname;
    @JsonProperty("msid")
    private String msid;
    @JsonProperty("subsec3")
    private List<Subsec3> subsec3 = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ssname")
    public String getSsname() {
        return ssname;
    }

    @JsonProperty("ssname")
    public void setSsname(String ssname) {
        this.ssname = ssname;
    }

    @JsonProperty("msid")
    public String getMsid() {
        return msid;
    }

    @JsonProperty("msid")
    public void setMsid(String msid) {
        this.msid = msid;
    }

    @JsonProperty("subsec3")
    public List<Subsec3> getSubsec3() {
        return subsec3;
    }

    @JsonProperty("subsec3")
    public void setSubsec3(List<Subsec3> subsec3) {
        this.subsec3 = subsec3;
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
