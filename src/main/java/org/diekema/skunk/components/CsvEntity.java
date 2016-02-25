package org.diekema.skunk.components;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * Created by rdiekema on 2/24/2016.
 */

@Record
public class CsvEntity
{
	@Field(at = 0)
	String firstname;
	@Field(at = 1)
	String lastName;
	@Field(at = 2)
	String companyName;
	@Field(at = 3)
	String address;
	@Field(at = 4)
	String city;
	@Field(at = 5)
	String stateOrProvince;
	@Field(at = 6)
	String country;

	public String getFirstname()
	{
		return firstname;
	}

	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getCompanyName()
	{
		return companyName;
	}

	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getStateOrProvince()
	{
		return stateOrProvince;
	}

	public void setStateOrProvince(String stateOrProvince)
	{
		this.stateOrProvince = stateOrProvince;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}
}
