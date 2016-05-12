package org.cyberpwn.langapi;

import org.cyberpwn.langapi.ClusterConfig.ClusterDataType;

public class ClusterStringList extends Cluster
{
	private GList<String> strings;
	
	public ClusterStringList(String key, GList<String> value)
	{
		super(ClusterDataType.STRING_LIST, key, 0.0);
		this.strings = value;
	}
	
	public GList<String> get()
	{
		return strings;
	}
	
	public void set(GList<String> s)
	{
		value = 0.0;
		strings = s;
	}
}
