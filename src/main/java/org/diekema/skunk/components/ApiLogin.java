package org.diekema.skunk.components;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
		"loginTokens",
		"apiVersion",
		"purpose"
})
public class ApiLogin
{

	@JsonProperty("loginTokens")
	private List<LoginToken> loginTokens = new ArrayList<LoginToken>();
	@JsonProperty("apiVersion")
	private ApiVersion apiVersion;
	@JsonProperty("purpose")
	private String purpose;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * @return The loginTokens
	 */
	@JsonProperty("loginTokens")
	public List<LoginToken> getLoginTokens()
	{
		return loginTokens;
	}

	/**
	 * @param loginTokens The loginTokens
	 */
	@JsonProperty("loginTokens")
	public void setLoginTokens(List<LoginToken> loginTokens)
	{
		this.loginTokens = loginTokens;
	}

	/**
	 * @return The apiVersion
	 */
	@JsonProperty("apiVersion")
	public ApiVersion getApiVersion()
	{
		return apiVersion;
	}

	/**
	 * @param apiVersion The apiVersion
	 */
	@JsonProperty("apiVersion")
	public void setApiVersion(ApiVersion apiVersion)
	{
		this.apiVersion = apiVersion;
	}

	/**
	 * @return The purpose
	 */
	@JsonProperty("purpose")
	public String getPurpose()
	{
		return purpose;
	}

	/**
	 * @param purpose The purpose
	 */
	@JsonProperty("purpose")
	public void setPurpose(String purpose)
	{
		this.purpose = purpose;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties()
	{
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value)
	{
		this.additionalProperties.put(name, value);
	}

}
