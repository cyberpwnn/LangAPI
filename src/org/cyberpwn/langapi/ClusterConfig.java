package org.cyberpwn.langapi;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ClusterConfig
{
	public enum ClusterDataType
	{
		INTEGER, DOUBLE, BOOLEAN, STRING, STRING_LIST;
	}
	
	private GMap<String, Cluster> data;
	
	public ClusterConfig()
	{
		this.data = new GMap<String, Cluster>();
	}
	
	public FileConfiguration toYaml()
	{
		FileConfiguration fc = new YamlConfiguration();
				
		for(String i : data.keySet())
		{
			fc.set(i, getAbstract(i));
		}
		
		return fc;
	}
	
	public void set(String key, int value)
	{
		data.put(key, new ClusterInteger(key, value));
	}
	
	public void set(String key, double value)
	{
		data.put(key, new ClusterDouble(key, value));
	}
	
	public void set(String key, boolean value)
	{
		data.put(key, new ClusterBoolean(key, value));
	}
	
	public void set(String key, String value)
	{
		data.put(key, new ClusterString(key, value));
	}
	
	public void set(String key, GList<String> value)
	{
		data.put(key, new ClusterStringList(key, value));
	}
	
	public Boolean getBoolean(String key)
	{
		if(contains(key) && getType(key).equals(ClusterDataType.BOOLEAN))
		{
			return ((ClusterBoolean)get(key)).get();
		}
		
		return null;
	}
	
	public Integer getInt(String key)
	{
		if(contains(key) && getType(key).equals(ClusterDataType.INTEGER))
		{
			return ((ClusterInteger)get(key)).get();
		}
		
		return null;
	}
	
	public Double getDouble(String key)
	{
		if(contains(key) && getType(key).equals(ClusterDataType.DOUBLE))
		{
			return ((ClusterDouble)get(key)).get();
		}
		
		return null;
	}
	
	public String getString(String key)
	{
		if(contains(key) && getType(key).equals(ClusterDataType.STRING))
		{
			return ((ClusterString)get(key)).get();
		}
		
		return null;
	}
	
	public GList<String> getStringList(String key)
	{
		if(contains(key) && getType(key).equals(ClusterDataType.STRING_LIST))
		{
			return ((ClusterStringList)get(key)).get();
		}
		
		return null;
	}
	
	public boolean contains(String key)
	{
		return data.containsKey(key) && data.get(key) != null;
	}
	
	public void remove(String key)
	{
		data.remove(key);
	}
	
	public Object getAbstract(String key)
	{
		if(getType(key).equals(ClusterDataType.BOOLEAN))
		{
			return getBoolean(key);
		}
		
		else if(getType(key).equals(ClusterDataType.INTEGER))
		{
			return getInt(key);
		}
		
		else if(getType(key).equals(ClusterDataType.DOUBLE))
		{
			return getDouble(key);
		}
		
		else if(getType(key).equals(ClusterDataType.STRING))
		{
			return getString(key);
		}
		
		else if(getType(key).equals(ClusterDataType.STRING_LIST))
		{
			return getStringList(key);
		}
		
		return null;
	}
	
	public Cluster get(String key)
	{
		return data.get(key);
	}
	
	public ClusterDataType getType(String key)
	{
		return get(key).getType();
	}

	public GMap<String, Cluster> getData()
	{
		return data;
	}

	public void setData(GMap<String, Cluster> data)
	{
		this.data = data;
	}
}
