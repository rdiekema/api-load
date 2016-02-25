package org.diekema.skunk.components.async;

/**
 * Created by rdiekema on 2/24/2016.
 */
public class Batch
{
	Long batchId;
	String payload;
	Long time;

	public Batch(Long batchId, String payload)
	{
		this.batchId = batchId;
		this.payload = payload;
	}

	public Long getBatchId()
	{
		return batchId;
	}

	public String getPayload()
	{
		return payload;
	}

	public Long getTime()
	{
		return time;
	}

	public void setTime(Long time)
	{
		this.time = time;
	}
}
