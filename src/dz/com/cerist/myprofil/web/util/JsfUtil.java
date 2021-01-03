package dz.com.cerist.myprofil.web.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.security.core.GrantedAuthority;

public class JsfUtil {

	public static SelectItem[] getSelectItems(List<?> entities,
			boolean selectOne) {
		int size = selectOne ? entities.size() + 1 : entities.size();
		SelectItem[] items = new SelectItem[size];
		int i = 0;
		if (selectOne) {
			items[0] = new SelectItem("", "---");
			i++;
		}
		for (Object x : entities) {
			items[i++] = new SelectItem(x, x.toString());
		}
		return items;
	}

	/**
	 * @author Abdelghani.zait
	 * @param entities
	 * @param selectOne
	 * @return List<SelectItem>
	 */

	public static List<SelectItem> getSelectItemsList(List<?> entities,
			boolean selectOne) {

		List<SelectItem> items = new LinkedList<SelectItem>();
		if (selectOne) {
			items.add(new SelectItem("", "---"));
		}

		for (Object x : entities) {
			items.add(new SelectItem(x, x.toString()));
		}
		return items;
	}

	public static void ensureAddErrorMessage(Exception ex, String defaultMsg) {
		String msg = ex.getLocalizedMessage();
		if (msg != null && msg.length() > 0) {
			addErrorMessage(msg);
		} else {
			addErrorMessage(defaultMsg);
		}
	}

	public static void addErrorMessages(List<String> messages) {
		for (String message : messages) {
			addErrorMessage(message);
		}
	}

	public static void addErrorMessage(String msg) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				msg, msg);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}

	public static void addAvertissementMessage(String msg) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN,
				msg, msg);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}

	public static void addSuccessMessage(String msg) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				msg, msg);
		FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
	}

	public static void addMessageWithDetail(String id, String summary,
			String detail, FacesMessage.Severity severity) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = new FacesMessage();
		facesMessage.setSeverity(severity);
		facesMessage.setSummary(summary);
		facesMessage.setDetail(detail);
		facesContext.addMessage(id, facesMessage);

	}

	public static String getRequestParameter(String key) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get(key);
	}

	public static Object getObjectFromRequestParameter(
			String requestParameterName, Converter converter,
			UIComponent component) {
		String theId = JsfUtil.getRequestParameter(requestParameterName);
		return converter.getAsObject(FacesContext.getCurrentInstance(),
				component, theId);
	}

	public static <T> List<T> arrayToList(T[] arr) {
		if (arr == null) {
			return new ArrayList<T>();
		}
		return Arrays.asList(arr);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> Set<T> arrayToSet(T[] arr) {
		if (arr == null) {
			return new HashSet<T>();
		}
		return new HashSet(Arrays.asList(arr));
	}

	public static Object[] collectionToArray(Collection<?> c) {
		if (c == null) {
			return new Object[0];
		}
		return c.toArray();
	}

	public static <T> List<T> setToList(Set<T> set) {
		return new ArrayList<T>(set);
	}

	public static String getAsConvertedString(Object object, Converter converter) {
		return converter.getAsString(FacesContext.getCurrentInstance(), null,
				object);
	}

	public static String getCollectionAsString(Collection<?> collection) {
		if (collection == null || collection.size() == 0) {
			return "(No Items)";
		}
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for (Object item : collection) {
			if (i > 0) {
				sb.append("<br />");
			}
			sb.append(item);
			i++;
		}
		return sb.toString();
	}

	public static String getMessageString(String keyMessage) {
		FacesContext context = FacesContext.getCurrentInstance();
		String bndl = context.getApplication().getMessageBundle();
		Locale locale = context.getViewRoot().getLocale();
		ResourceBundle msg = ResourceBundle.getBundle(bndl, locale);
		return msg.getString(keyMessage);
	}

	/**
	 * Supprimer un managed bean du conntext
	 * 
	 * @param beanName
	 */
	public static void removeManagedBeanInstance(String beanName) {
		if (beanName != null) {
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put(beanName, null);

			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().remove(beanName);
		}
	}

	// Getters --
	public static Object getApplicationMapValue(String key) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getApplicationMap().get(key);
	}

	// Setters --

	public static void setApplicationMapValue(String key, Object value) {
		FacesContext.getCurrentInstance().getExternalContext()
				.getApplicationMap().put(key, value);
	}

	// Getters --

	public static Object getSessionMapValue(String key) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get(key);
	}

	public static Object getRequestParameterMap(String key) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get(key);
	}

	// Setters --

	public static void setSessionMapValue(String key, Object value) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(key, value);
	}

	public static void refresh() {
		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		ViewHandler viewHandler = application.getViewHandler();
		UIViewRoot viewRoot = viewHandler.createView(context, context
				.getViewRoot().getViewId());
		context.setViewRoot(viewRoot);
		context.renderResponse(); // Optional
	}

	public static void createDirectory(String codeMission) {
		FacesContext fc = FacesContext.getCurrentInstance();

		ServletContext servletContext = (ServletContext) fc
				.getExternalContext().getContext();

		String FILE_DIRECTORY = servletContext.getRealPath("/dossier-mission/");

		File f = new File(FILE_DIRECTORY, codeMission);

		if (!f.exists()) {

			f.mkdir();

		}

	}
	
	/**
	 * Attacher document
	 * 
	 * @param event
	 * @param realPath
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void attacherDocument(UploadedFile uploadedFile, String realPath,
			String fileName) throws FileNotFoundException, IOException {

		FacesContext fc = FacesContext.getCurrentInstance();

		ServletContext servletContext = (ServletContext) fc
				.getExternalContext().getContext();

		String FILE_DIRECTORY = servletContext.getRealPath(realPath);

		File f = new File(FILE_DIRECTORY, fileName);

		FileOutputStream fos = null;

		InputStream is = null;

		fos = new FileOutputStream(f);

		is = uploadedFile.getInputstream();

		byte[] buffer = new byte[4096];

		for (int n; (n = is.read(buffer)) != -1;) {
			fos.write(buffer, 0, n);

		}

		fos.close();

		is.close();

	}
	public static void attacherDocument(FileUploadEvent event, String realPath,
			String fileName) throws FileNotFoundException, IOException {

		FacesContext fc = FacesContext.getCurrentInstance();

		ServletContext servletContext = (ServletContext) fc
				.getExternalContext().getContext();

		String FILE_DIRECTORY = servletContext.getRealPath(realPath);

		File f = new File(FILE_DIRECTORY, fileName);

		FileOutputStream fos = null;

		InputStream is = null;

		fos = new FileOutputStream(f);

		is = event.getFile().getInputstream();

		byte[] buffer = new byte[4096];

		for (int n; (n = is.read(buffer)) != -1;) {
			fos.write(buffer, 0, n);

		}

		fos.close();

		is.close();

	}
	public static Boolean isAdministrateur(
			Collection<? extends GrantedAuthority> authorities) {
		for (GrantedAuthority ga : authorities)
			if (ga.getAuthority().trim().contains("ROLE_ADMIN"))
				return new Boolean(true);
		return new Boolean(false);
	}
	
	/**
	 * 
	 * extraire le premier element d'une formule
	 */

	private static String getFirstArg(String formule) {
		String result = "";
		String emptyString = "";
		boolean wantMore = true;
		if(formule.equals(emptyString)) return null;
		while (wantMore) {

			result = result.concat(String.valueOf(formule.charAt(0)));
			formule = formule.substring(1);
			if (result.matches("[0-9]")) {
				if(formule.equals(emptyString))
					return result;
				while (String.valueOf(formule.charAt(0)).matches("[0-9]|\\.")) {
					result = result.concat(String.valueOf(formule.charAt(0)));
					formule = formule.substring(1);
					if (formule.equals(emptyString))
						break;
				}

				try {
					Float.parseFloat(result);
					return result;
				} catch (Exception e) {
					return null;
				}
			}
			switch (result) {
			case "Q":
				return result;
			case "V":
				return result;
			case "D":
				return result;
			case "/":
				return result;
			case "*":
				return result;
			case "-":
				return result;
			case "+":
				return result;
			case "M": {
				if(!formule.equals(emptyString)){
				result = result.concat(String.valueOf(formule.charAt(0)));
				formule = formule.substring(1);}
				switch (result) {
				case "ML":
					return result;
				case "MO":
					return result;
				default:
					return null;
				}
			}
			case "K": {
				if(!formule.equals(emptyString)){
				result = result.concat(String.valueOf(formule.charAt(0)));
				formule = formule.substring(1);}
				switch (result) {
				case "Km":
					return result;

				default:
					return null;
				}
			}
			case "P": {
				if(!formule.equals(emptyString)){
				result = result.concat(String.valueOf(formule.charAt(0)));
				formule = formule.substring(1);}
				switch (result) {
				case "PA":
					return result;

				default:
					return null;
				}
			}
			case "[": {
				while (!String.valueOf(formule.charAt(0)).equals("]")) {
					if(!formule.equals(emptyString)){
					result = result.concat(String.valueOf(formule.charAt(0)));
					formule = formule.substring(1);}
					if (formule.equals(emptyString))
						break;
				}
				if(!formule.equals(emptyString)){
					result = result.concat(String.valueOf(formule.charAt(0)));
					formule = formule.substring(1);}
				if (String.valueOf(result.charAt(result.length()-1)).equals("]"))
					return result;
				else
					return null;
			}
			default:
				break;
			}

			wantMore = false;

		}
		return null;
	}
	
	/**
	 * 
	 * Verification de la formule de calcule de la taxe
	 */
	public static boolean isValideFormule(String formule) {
		int acc = 0;
		String emptyString = "";
		String temp = "";
		System.out.println("formule before :"+formule);
		char c;
		while (!formule.equals(emptyString)) {
			c = formule.charAt(0);
			if (c == '(') {
				acc++;
			} else {
				if (c == ')') {
					if (acc > 0)
						acc--;
					else
						return false;
				} else {
					temp = temp.concat(String.valueOf(c));
				}
			}
			formule = formule.substring(1);
		}
		if (acc != 0) {
			return false;
		} else {
			formule = "".concat(temp);
			System.out.println("formule after :"+formule);
			while (!formule.equals(emptyString)) {
				String variable = getFirstArg(formule);
				if (variable == null)
					return false;
				
				int i= formule.length();
				formule=formule.substring(variable.length(), i);
				String operateur = "";
				if (!formule.equals(emptyString)) {
					operateur = getFirstArg(formule);
					if (operateur == null) {
						return false;
					} else {
						if (!(operateur.equals("+") || operateur.equals("-")
								|| operateur.equals("*") || operateur
									.equals("/")))
							return false;
						else {
							formule = formule
									.replaceFirst("\\" + operateur, "");
							if (formule.equals(emptyString))
								return false;
						}
					}
				} else {
					return true;
				}
			}

		}
		System.out.println(temp);
		return true;

	}
}
