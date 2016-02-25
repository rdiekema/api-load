package org.diekema.skunk.components;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.List;

/**
 * Created by rdiekema on 2/24/2016.
 */

public class Entities
{
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<Entity> Entity;

	public Entities()
	{
	}

}
