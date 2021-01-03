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

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;

import dz.com.cerist.myprofil.entite.Compte;
import dz.com.cerist.myprofil.service.ICompteService;
import dz.com.cerist.myprofil.exception.UserNotFoundException;



/**
 * View bean to add a new user.
 */
@ManagedBean(name = "resetPasswordView")
@ViewScoped
public class ResetPasswordView {

    

	 @ManagedProperty(name="iCompteService", value="#{iCompteService}")
	 private ICompteService iCompteService;

    private boolean passwordResetSuccessful = false;

    private Compte compte;

    /**
     * Reads the action token and processes it.
     */
    public void processActionToken() {
        if (!passwordResetSuccessful) {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
            String actionToken = request.getParameter("uid");

            try {
                compte = iCompteService.getUserByActionToken(actionToken);
            } catch (UserNotFoundException e) {
            	FacesContext context = FacesContext.getCurrentInstance();
    	        context.addMessage(null, new FacesMessage("Action token not valid"));
                compte = null;
            }
        }
    }

    /**
     * Resets the password of the user identified by userIdentifier.
     */
    public void resetPassword() {
        if (compte != null) {
            try {
            	iCompteService.resetPassword(compte);
                passwordResetSuccessful = true;
            } catch (UserNotFoundException e) {
            	FacesContext context = FacesContext.getCurrentInstance();
    	        context.addMessage(null, new FacesMessage("Could not find user"));
               passwordResetSuccessful = false;
            }
        } else {
            passwordResetSuccessful = false;
        }
    }

    // ---------- getter/setter ----------

    public boolean isPasswordResetSuccessful() {
        return passwordResetSuccessful;
    }

    public void setPasswordResetSuccessful(boolean passwordResetSuccessful) {
        this.passwordResetSuccessful = passwordResetSuccessful;
    }

 

	public ICompteService getiCompteService() {
		return iCompteService;
	}

	public void setiCompteService(ICompteService iCompteService) {
		this.iCompteService = iCompteService;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

}
