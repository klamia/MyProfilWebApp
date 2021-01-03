/**
 * Classe d'aide pour les messages 
 * @author Mounir MESSAOUDI
 */
package dz.com.cerist.myprofil.web.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

/**
 *
 */
public class MessageHelper {

	protected static ClassLoader getCurrentClassLoader(Object defaultObject) {

		ClassLoader loader = Thread.currentThread().getContextClassLoader();

		if (loader == null) {
			loader = defaultObject.getClass().getClassLoader();
		}

		return loader;
	}

	/**
	 * Renvoi le message definit dans le fichier resource dont la cle de message
	 * est key
	 * 
	 * @param key
	 * @return
	 */
	public static String getMessageResourceString(String key) {

		String text = null;
		FacesContext context = FacesContext.getCurrentInstance();
		Locale localeCourant = context.getViewRoot().getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle("bundles.application-resources", localeCourant,
				getCurrentClassLoader(null));

		try {
			text = bundle.getString(key);
		} catch (MissingResourceException e) {
			text = "?? key " + key + " introuvable ??";
		}
		return text;
	}
	/**
	 * Renvoi le message definit dans le fichier resource dont la cle de message
	 * est key
	 * 
	 * @param key
	 * @return
	 */
	public static String getMessageResourceString(String bundleName,String key) {

		String text = null;
		FacesContext context = FacesContext.getCurrentInstance();
		Locale localeCourant = context.getViewRoot().getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle(bundleName, localeCourant,
				getCurrentClassLoader(null));

		try {
			text = bundle.getString(key);
		} catch (MissingResourceException e) {
			e.printStackTrace();
			text = "?? key " + key + " introuvable ??";
		}
		return text;
	}
	/**
	 * Renvoi le message definit dans le fichier resource dont la cle de message
	 * est key
	 * 
	 * @param key
	 * @param params
	 * @return String
	 */
	public static String getMessageResourceStrings(String key,  Object params[]) {

		String text = null;
		FacesContext context = FacesContext.getCurrentInstance();
		Locale localeCourant = context.getViewRoot().getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle("bundles.application-resources", localeCourant,
				getCurrentClassLoader(params));

		try {
			text = bundle.getString(key);
		} catch (MissingResourceException e) {
			text = "?? key " + key + " introuvable ??";
		}
		
		if (params != null) {
			MessageFormat mf = new MessageFormat(text, localeCourant);
			text = mf.format(params, new StringBuffer(), null).toString();
		}

		return text;
	}
	/**
	 * Renvoi le message definit dans le fichier resource dont la cle de message
	 * est key
	 * 
	 * @param key
	 * @param params
	 * @return String
	 */
	public static String getMessageResourceString(String key, Object... params) {

		String text = null;
		FacesContext context = FacesContext.getCurrentInstance();
		Locale localeCourant = context.getViewRoot().getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle("bundles.application-resources", localeCourant,
				getCurrentClassLoader(params));

		try {
			text = bundle.getString(key);
		} catch (MissingResourceException e) {
			text = "?? key " + key + " introuvable ??";
		}
		
		if (params != null) {
			MessageFormat mf = new MessageFormat(text, localeCourant);
			text = mf.format(params, new StringBuffer(), null).toString();
		}

		return text;
	}
	
	/**
	 * Renvoi le message definit dans le fichier resource dont la cle de message
	 * est key
	 * 
	 * @param bundleName
	 * @param key
	 * @param params
	 * @param locale
	 * @return
	 */
	public static String getMessageResourceString(String bundleName,
			String key, Object params[], Locale locale) {

		String text = null;

		ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale,
				getCurrentClassLoader(params));

		try {
			text = bundle.getString(key);
		} catch (MissingResourceException e) {
			text = "?? key " + key + " introuvable ??";
		}
		
		if (params != null) {
			MessageFormat mf = new MessageFormat(text, locale);
			text = mf.format(params, new StringBuffer(), null).toString();
		}

		return text;
	}
}
