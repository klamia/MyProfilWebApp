/*******************************************************************************
 * Copyright 2006 - 2012 Vienna University of Technology,
 * Department of Software Technology and Interactive Systems, IFS
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package dz.com.cerist.myprofil.web.jsf;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;

import dz.com.cerist.myprofil.entite.Compte;
import dz.com.cerist.myprofil.service.ICompteService;
import dz.com.cerist.myprofil.exception.CannotSendMailException;
import dz.com.cerist.myprofil.exception.UserNotFoundException;



/**
 * Viewbean to add a new user.
 */
@ManagedBean(name = "forgotPasswordView")
@ViewScoped
public class ForgotPasswordView implements Serializable {

    private static final long serialVersionUID = -6437185754610418284L;

   

    @ManagedProperty(name="iCompteService", value="#{iCompteService}")
	private ICompteService iCompteService;

    private String userIdentifier;

    /**
     * Resets the password of the user identified by userIdentifier.
     */
    public void resetPassword() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
            .getRequest();
        String serverString = request.getServerName() + ":" + request.getServerPort();
         System.out.println(serverString);
        
        try {
            Compte compte = iCompteService.getUserByIdentifier(userIdentifier);
            iCompteService.initiateResetPassword(compte);
            iCompteService.sendPasswordResetMail(compte, serverString);
            FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("A mail with password recovery information has been sent to the email address provided when you created the account"));
            
            
        } catch (UserNotFoundException e) {
        	FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("No user with this username or email address found"));
        	
        } catch (CannotSendMailException e) {
           
        	FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Error sending the password reset mail"));
              	
        }
    }

    // ---------- getter/setter ----------
    public String getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

	public ICompteService getiCompteService() {
		return iCompteService;
	}

	public void setiCompteService(ICompteService iCompteService) {
		this.iCompteService = iCompteService;
	}
}
