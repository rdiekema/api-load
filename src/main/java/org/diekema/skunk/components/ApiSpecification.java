package org.diekema.skunk.components;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
		"versions"
})
public class ApiSpecification
{

	@JsonProperty("versions")
	private List<Version> versions = new ArrayList<Version>();

	/**
	 * @return The versions
	 */
	@JsonProperty("versions")
	public List<Version> getVersions()
	{
		return versions;
	}

	/**
	 * @param versions The versions
	 */
	@JsonProperty("versions")
	public void setVersions(List<Version> versions)
	{
		this.versions = versions;
	}

}
