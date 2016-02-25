package org.diekema.skunk.components;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Created by rdiekema on 2/24/2016.
 */

public class Entity
{
	@JacksonXmlProperty(localName = "Uid")
	String Uid;

	@JacksonXmlProperty(localName = "Name")
	String Name;

	@JacksonXmlProperty(localName = "Address")
	String Address;

	@JacksonXmlProperty(localName = "City")
	String City;

	@JacksonXmlProperty(localName = "StateOrProvince")
	String StateOrProvince;

	@JacksonXmlProperty(localName = "Country")
	String Country;

	public Entity(CsvEntity csvEntity)
	{
		this.Uid = "";
		this.Name = csvEntity.getFirstname() + csvEntity.getLastName() + csvEntity.getCompanyName();
		this.Address = csvEntity.getAddress();
		this.City = csvEntity.getCity();
		this.StateOrProvince = csvEntity.getStateOrProvince();
		this.Country = csvEntity.getCountry();
	}

	public String getUid()
	{
		return Uid;
	}

	public void setUid(String uid)
	{
		Uid = uid;
	}

	public String getName()
	{
		return Name;
	}

	public void setName(String name)
	{
		Name = name;
	}

	public String getAddress()
	{
		return Address;
	}

	public void setAddress(String address)
	{
		Address = address;
	}

	public String getCity()
	{
		return City;
	}

	public void setCity(String city)
	{
		City = city;
	}

	public String getStateOrProvince()
	{
		return StateOrProvince;
	}

	public void setStateOrProvince(String stateOrProvince)
	{
		StateOrProvince = stateOrProvince;
	}

	public String getCountry()
	{
		return Country;
	}

	public void setCountry(String country)
	{
		Country = country;
	}
}
