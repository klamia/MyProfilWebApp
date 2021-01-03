package dz.com.cerist.myprofil.service;

import java.io.IOException;

import javax.faces.bean.ManagedProperty;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ImageServlet
 */

/*@WebServlet(name = "ImageServlet", urlPatterns = {"/ImageServlet"})*/
public class ImageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	@ManagedProperty(name="iProfilService", value="#{iProfilService}")
	private IProfilService iProfilService;
	
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        Integer codeProfil =Integer.parseInt(request.getParameter("codeProfil"));
        byte[] image = iProfilService.loadImage(codeProfil);

        response.setContentType("image/png");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(image);
        outputStream.close();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	 @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        processRequest(request, response);
	    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        processRequest(request, response);
	    }
	 
	 @Override
	    public String getServletInfo() {
	        return "Short description";
	    }// </editor-fold>

	public IProfilService getiProfilService() {
		return iProfilService;
	}

	public void setiProfilService(IProfilService iProfilService) {
		this.iProfilService = iProfilService;
	}


    

}
