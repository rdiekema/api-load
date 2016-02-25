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
		"apiSpecification",
		"loginFields"
})
public class Handshake
{

	@JsonProperty("apiSpecification")
	private ApiSpecification apiSpecification;
	@JsonProperty("loginFields")
	private List<LoginField> loginFields = new ArrayList<LoginField>();

	/**
	 * @return The apiSpecification
	 */
	@JsonProperty("apiSpecification")
	public ApiSpecification getApiSpecification()
	{
		return apiSpecification;
	}

	/**
	 * @param apiSpecification The apiSpecification
	 */
	@JsonProperty("apiSpecification")
	public void setApiSpecification(ApiSpecification apiSpecification)
	{
		this.apiSpecification = apiSpecification;
	}

	/**
	 * @return The loginFields
	 */
	@JsonProperty("loginFields")
	public List<LoginField> getLoginFields()
	{
		return loginFields;
	}

	/**
	 * @param loginFields The loginFields
	 */
	@JsonProperty("loginFields")
	public void setLoginFields(List<LoginField> loginFields)
	{
		this.loginFields = loginFields;
	}

}
