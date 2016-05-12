package org.cyberpwn.langapi;

import java.util.Locale;

import org.bukkit.plugin.Plugin;

public class LangCluster
{
	private LangPlugin pl;
	private GMap<Locale, LangFile> languages;
	
	public LangCluster(LangPlugin pl)
	{
		this.pl = pl;
		this.languages = new GMap<Locale, LangFile>();
	}
	
	public String get(Locale locale, Plugin plugin, String key)
	{
		if(!languages.containsKey(locale))
		{
			load(locale);
		}
		
		return languages.get(locale).get(plugin, key);
	}
	
	public void set(Locale locale, Plugin plugin, String key, String value)
	{
		if(!languages.containsKey(locale))
		{
			load(locale);
		}
		
		languages.get(locale).set(plugin, key, value);
	}
	
	public void load(Locale locale)
	{
		languages.put(locale, new LangFile(pl, locale.getDisplayCountry(Locale.ENGLISH), locale.getLanguage()));
	}
	
	public void save(Locale locale)
	{
		languages.get(locale).save();
	}
}
