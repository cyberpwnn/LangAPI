package org.cyberpwn.langapi;

import org.cyberpwn.langapi.ClusterConfig.ClusterDataType;

public class Cluster
{
	protected final ClusterDataType type;
	protected final String key;
	protected Double value;
	
	public Cluster(ClusterDataType type, String key, Double value)
	{
		this.type = type;
		this.key = key;
		this.value = value;
	}

	public ClusterDataType getType()
	{
		return type;
	}

	public String getKey()
	{
		return key;
	}
}
