package dz.com.cerist.myprofil.web.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JExcelApiExporter;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class JasperUtil {

	


	/**
	 * Method generer le rapport jasper report
	 * 
	 * @param params
	 * @param reportName
	 * 
	 * @author Abdelghani.zait
	 * 
	 */
	
	
	private static String getUrl() {

		return MessageHelper.getMessageResourceString("oracle-dao",
				"database.connection.url");
	}

	private static String getLongin() {
		return MessageHelper.getMessageResourceString("oracle-dao",
				"database.connection.username");
	}

	private static String getPassword() {
		return MessageHelper.getMessageResourceString("oracle-dao",
				"database.connection.password");
	}
	
	

	
	@SuppressWarnings({ "unused", "rawtypes" })
	public static void generateFile(Map params, String reportName,
			String raportGenererName,String realPath) {

		

		
		
		
		
		
		

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext
				.getResponse();
		String path = externalContext.getRequestContextPath();
		ServletContext servletContext = (ServletContext) facesContext
				.getExternalContext().getContext();

		// Set class path for compiling XML templates
		System.setProperty(
				"jasper.reports.compile.class.path",
				servletContext
						.getRealPath("/WEB-INF/lib/jasperreports-5.0.4.jar")
						+ System.getProperty("path.separator")
						+ servletContext.getRealPath("/WEB-INF/classes/"));
		// Specify a default folder for storing
		// compiled XML templates
		System.setProperty("jasper.reports.compile.temp",
				servletContext.getRealPath(realPath));
		System.setProperty("net.sf.jasperreports.extension.registry.factory.htmlcomponent", "net.sf.jasperreports.components.html.HtmlComponentExtensionsRegistryFactory");
		

		try {
			// - Connexion la base
			Driver monDriver = new oracle.jdbc.OracleDriver();
			
			DriverManager.registerDriver(monDriver);
			
			Connection connection = DriverManager.getConnection(getUrl(),
					getLongin(), getPassword());
			
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					servletContext.getRealPath("/report/" + reportName
							+ ".jasper"), params, connection);
			
			// - Creation du rapport au format PDF
			JasperExportManager.exportReportToPdfFile(jasperPrint,
					servletContext.getRealPath(raportGenererName));

	    } catch (JRException e) {
	    	System.err.println("Exception JR : \n"+e.getLocalizedMessage());
		    e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			// connection.close();

		}
	}

	
	
	public static StreamedContent generateExcelFile(@SuppressWarnings("rawtypes") Map params, String reportName,
			String raportGenererName,String realPath) {

		

		 FileInputStream stream;
		 StreamedContent file = null ;


		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		@SuppressWarnings("unused")
		HttpServletResponse response = (HttpServletResponse) externalContext
				.getResponse();
		@SuppressWarnings("unused")
		String path = externalContext.getRequestContextPath();
		ServletContext servletContext = (ServletContext) facesContext
				.getExternalContext().getContext();

		// Set class path for compiling XML templates
		System.setProperty(
				"jasper.reports.compile.class.path",
				servletContext
						.getRealPath("/WEB-INF/lib/jasperreports-4.0.1.jar")
						+ System.getProperty("path.separator")
						+ servletContext.getRealPath("/WEB-INF/classes/"));
		// Specify a default folder for storing
		// compiled XML templates
		System.setProperty("jasper.reports.compile.temp",
				servletContext.getRealPath(realPath));

		try {
			// - Connexion la base
			Driver monDriver = new oracle.jdbc.OracleDriver();
			
			DriverManager.registerDriver(monDriver);
			
			Connection connection = DriverManager.getConnection(getUrl(),
					getLongin(), getPassword());
			
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					servletContext.getRealPath("/report/" + reportName
							+ ".jasper"), params, connection);
			
			// - Creation du rapport au format excel
			
			
			JExcelApiExporter xlsExporter = new JExcelApiExporter();
			xlsExporter.setParameter(JRExporterParameter.
			JASPER_PRINT, jasperPrint);
			xlsExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
					servletContext.getRealPath(raportGenererName));
			
			System.out.println("Exporting report...");
			xlsExporter.exportReport();
			System.out.println("Done!");
			
			System.out.println("*********"+servletContext.getRealPath(raportGenererName));
			
			
			
			stream = new FileInputStream(servletContext.getRealPath(raportGenererName));
			 file = new DefaultStreamedContent (stream ,"application/vnd.ms-excel", reportName+".xls" );
			
			 
			
			
				
	
			
	    } catch (JRException e) {
	    	System.err.println("Exception JR : \n"+e.getLocalizedMessage());
		    e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		
		}
	 catch (@SuppressWarnings("hiding") IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		return file;
		 
		
		
				

	}

	
	
	
}