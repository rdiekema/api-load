package org.diekema.skunk.components;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
		"major",
		"minor",
		"patch",
		"build"
})
public class Version
{

	@JsonProperty("major")
	private String major;
	@JsonProperty("minor")
	private String minor;
	@JsonProperty("patch")
	private String patch;
	@JsonProperty("build")
	private String build;

	/**
	 * @return The major
	 */
	@JsonProperty("major")
	public String getMajor()
	{
		return major;
	}

	/**
	 * @param major The major
	 */
	@JsonProperty("major")
	public void setMajor(String major)
	{
		this.major = major;
	}

	/**
	 * @return The minor
	 */
	@JsonProperty("minor")
	public String getMinor()
	{
		return minor;
	}

	/**
	 * @param minor The minor
	 */
	@JsonProperty("minor")
	public void setMinor(String minor)
	{
		this.minor = minor;
	}

	/**
	 * @return The patch
	 */
	@JsonProperty("patch")
	public String getPatch()
	{
		return patch;
	}

	/**
	 * @param patch The patch
	 */
	@JsonProperty("patch")
	public void setPatch(String patch)
	{
		this.patch = patch;
	}

	/**
	 * @return The build
	 */
	@JsonProperty("build")
	public String getBuild()
	{
		return build;
	}

	/**
	 * @param build The build
	 */
	@JsonProperty("build")
	public void setBuild(String build)
	{
		this.build = build;
	}

}
