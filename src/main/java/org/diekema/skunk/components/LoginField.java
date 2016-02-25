package org.diekema.skunk.components;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
		"name",
		"description",
		"key",
		"length",
		"maskedField"
})
public class LoginField
{

	@JsonProperty("name")
	private String name;
	@JsonProperty("description")
	private String description;
	@JsonProperty("key")
	private String key;
	@JsonProperty("length")
	private int length;
	@JsonProperty("maskedField")
	private boolean maskedField;

	/**
	 * @return The name
	 */
	@JsonProperty("name")
	public String getName()
	{
		return name;
	}

	/**
	 * @param name The name
	 */
	@JsonProperty("name")
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return The description
	 */
	@JsonProperty("description")
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description The description
	 */
	@JsonProperty("description")
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @return The key
	 */
	@JsonProperty("key")
	public String getKey()
	{
		return key;
	}

	/**
	 * @param key The key
	 */
	@JsonProperty("key")
	public void setKey(String key)
	{
		this.key = key;
	}

	/**
	 * @return The length
	 */
	@JsonProperty("length")
	public int getLength()
	{
		return length;
	}

	/**
	 * @param length The length
	 */
	@JsonProperty("length")
	public void setLength(int length)
	{
		this.length = length;
	}

	/**
	 * @return The maskedField
	 */
	@JsonProperty("maskedField")
	public boolean isMaskedField()
	{
		return maskedField;
	}

	/**
	 * @param maskedField The maskedField
	 */
	@JsonProperty("maskedField")
	public void setMaskedField(boolean maskedField)
	{
		this.maskedField = maskedField;
	}

}
