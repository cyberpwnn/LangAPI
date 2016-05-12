package org.cyberpwn.langapi;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class LangFile
{
	private ClusterConfig cc;
	private String languageName;
	private String languageCode;
	private File dataFolder;
	
	public LangFile(LangPlugin pl, String languageName, String languageCode)
	{
		cc = new ClusterConfig();
		this.languageName = languageName;
		this.languageCode = languageCode;
		this.dataFolder = new File(pl.getDataFolder(), "lang");
		
		load();
	}
	
	public String get(Plugin plugin, String key)
	{
		return cc.getString(plugin.getName() + "." + key);
	}
	
	public void register(Plugin plugin, String key, String value)
	{
		cc.set(plugin.getName() + "." + key, value);
	}
	
	private void onStructure()
	{
		cc.set("lang.name", languageName);
		cc.set("lang.code", languageCode);
	}
	
	private void onRead()
	{
		//Dynamic
	}
	
	public void save()
	{
		try
		{
			File file = new File(dataFolder, languageCode + ".yml");
			
			if(!file.exists() && file.isDirectory())
			{
				file.delete();
			}
			
			if(!file.exists())
			{
				onStructure();
				verifyFile(file);
			}
			
			saveFileConfig(file, cc.toYaml());
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void load()
	{
		try
		{
			File file = new File(dataFolder, languageCode + ".yml");
			
			if(!file.exists() && file.isDirectory())
			{
				file.delete();
			}
			
			if(!file.exists())
			{
				onStructure();
				verifyFile(file);
				saveFileConfig(file, cc.toYaml());
			}
			
			loadConfigurableSettings(file);
			onRead();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void loadConfigurableSettings(File file)
	{
		onStructure();
		FileConfiguration fc = loadFileConfig(file);
		
		for(String i : fc.getKeys(true))
		{
			if(fc.isBoolean(i))
			{
				cc.set(i, fc.getBoolean(i));
			}
			
			if(fc.isDouble(i))
			{
				cc.set(i, fc.getDouble(i));
			}
			
			if(fc.isInt(i))
			{
				cc.set(i, fc.getInt(i));
			}
			
			if(fc.isString(i))
			{
				cc.set(i, fc.getString(i));
			}
			
			if(fc.isList(i))
			{
				cc.set(i, new GList<String>(fc.getStringList(i)));
			}
		}
		
		for(String i : cc.getData().k())
		{
			fc.set(i, cc.getAbstract(i));
		}
		
		saveFileConfig(file, fc);
	}
	
	private FileConfiguration loadFileConfig(File file)
	{
		FileConfiguration fc = new YamlConfiguration();
		
		try
		{
			fc.load(file);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return fc;
	}
	
	private void saveFileConfig(File file, FileConfiguration fc)
	{
		try
		{
			fc.save(file);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void verifyFile(File file)
	{
		if(!file.exists())
		{
			file.getParentFile().mkdirs();
			
			try
			{
				file.createNewFile();
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public String getLanguageName()
	{
		return languageName;
	}
	
	public String getLanguageCode()
	{
		return languageCode;
	}
}
