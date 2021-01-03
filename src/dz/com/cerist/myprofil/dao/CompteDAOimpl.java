package dz.com.cerist.myprofil.dao;





import java.io.Serializable;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

//import org.apache.commons.configuration.Configuration;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import dz.com.cerist.myprofil.entite.Compte;
import dz.com.cerist.myprofil.entite.Profil;
import dz.com.cerist.myprofil.exception.CannotSendMailException;
import dz.com.cerist.myprofil.exception.MyProfilException;
import dz.com.cerist.myprofil.exception.UserNotFoundException;

@Repository("iCompteDao")
@Transactional
public class CompteDAOimpl implements ICompteDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7098655403992689400L;
	@PersistenceContext(unitName = "MyProfilePU")
	private EntityManager em;
	
	
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

//	private Configuration config;
	
	
	@Override
	public void ajouterCompte(Compte compte) {
		
		try{
		   // compte.setdCreationCompte(null);
			// Create a user actionToken which is needed for activation
	        compte.setActionToken(UUID.randomUUID().toString());
			em.persist(compte); 
		
		}
		catch (Throwable th){
			 throw new MyProfilException(th,11);
			 }
	
        
	/*
		if(!em.contains(compte))
			System.out.println("Entity is not persisted yet, will persist now!");
		    em.persist(compte); 
		    if(em.contains(compte))
				System.out.println("persisted entity is - " + compte);
			else
				System.out.println("Entitiy is still not persisted after call to persist()");
	*/
	}

	@Override
	public Compte getComptebyId(Integer codeCompte) {
		try{
		return em.find(Compte.class, codeCompte);
		}
		catch (Throwable th){
			 throw new MyProfilException(th,12);
			 }
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Compte> getAllCompte() {
		try {
		return em.createNamedQuery("Compte.findAll").setHint("org.hibernate.cacheable", true).getResultList();
		}
		catch (Throwable th){
			 throw new MyProfilException(th,13);
			 }
	}

	@Override
	public Compte editerCompte(Compte compte) {
		// TODO Auto-generated method stub
		try{
		return em.merge(compte);
		}
		catch (Throwable th){
			 throw new MyProfilException(th,14);
			 }
	}
	
	@Override
	public Compte getCompteByLogin(String login) {
		// TODO Auto-generated method stub
		 try{
		
			 Query query = em.createNamedQuery("Compte.findByLogin");
				query.setParameter("login", login);
				return (Compte) query.getSingleResult();
			
			 
			 //return (Compte) em.createNamedQuery("Compte.findByLogin").getSingleResult();
		 
		 }
		 catch (Throwable th){
			 throw new MyProfilException(th,15);
			 }
	}

	@Override
	public Profil voirProfil(Compte compte) {
		// TODO Auto-generated method stub
		try{
		return (Profil) em.createNamedQuery("Compte.voirProfil").setParameter("idCompte", compte.getCodeCompte()).getSingleResult();
		//return (Profil) em.createNamedQuery("Compte.voirProfil").setParameter("compte", compte).getSingleResult();
		}
		catch (Throwable th){
			 throw new MyProfilException(th,16);
			 }
		}

	@Override
	public void activerCompte(Compte compte) {
       try{
		
    	 compte.setEnabled(true);
		 em.merge(compte);
       }
       catch (Throwable th){
			 throw new MyProfilException(th,17);
			 }
	}

	@Override
	public void desactiverCompte(Compte compte) {
		// TODO Auto-generated method stub
		try{
		compte.setEnabled(false);
		 em.merge(compte);
		}
		catch (Throwable th){
			 throw new MyProfilException(th,18);
			 }
		}

	 /**
     * Initiates password reset for the user.
     * 
     * @param user
     *            the user
     */
	@Override
	public void initiateResetPassword(Compte compte) {
		compte.setActionToken(UUID.randomUUID().toString());
        em.persist(em.merge(compte));
		
	}

	/**
     * Sends the user a link to reset the password.
     * 
    */ 
	
	@Override
	public void sendPasswordResetMail(Compte compte, String serverString) throws CannotSendMailException {
	    
		final String username = "usersmtp";
        final String password = "Smtp@2016";
		
		Properties props = new Properties();
		props.put("mail.debug", "true");
		props.put("mail.transport.protocol", "smtp");
		//props.put("mail.smtp.auth", "true");
      //  props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "dsism.cerist.dz");
        props.put("mail.smtp.port", "25");
        //props.put("mail.smtp.auth.plain.disable", "true");
       // props.put("mail.smtp.auth.ntlm.disable", "true");
       // props.put("mail.smtp.auth.ntlm.domain", "dsism");
       // props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });
        
		try {
            
			
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("root@dsism.cerist.dz"));
            message.setRecipient(RecipientType.TO, new InternetAddress(compte.getEmailProf()));
            message.setSubject("MyProfilWebApp password recovery");

            StringBuilder builder = new StringBuilder();
            builder.append("Dear " + compte.getLogin() + ", \n\n");
            builder.append("You have requested help recovering the password for the MyProfilWebApp user ");
            builder.append(compte.getLogin()).append(".\n\n");
            builder.append("Please use the following link to reset your MyProfilWebApp password: \n");
            builder.append("http://" + serverString + "/MyProfileWebApp/resetPassword.jsf?uid=" + compte.getActionToken());
            builder.append("\n\n--\n");
            builder.append("Your MyProfilWebApp team");

            message.setText(builder.toString());
            message.saveChanges();

            Transport.send(message);
            

        } catch (Exception e) {
            
            throw new CannotSendMailException("Error at sending password reset mail to " + compte.getEmailProf());
        }
		
		
	}
	
	
	/* JBOSS CASES 
	 * * @param user
     *            the user
     * @param serverString
     *            host and port of the server
     * @throws CannotSendMailException
     *             if the password reset mail could not be sent
     */
	 /*
	@Override
	public void sendPasswordResetMail(Compte compte, String serverString) throws CannotSendMailException {
	    
		try {
            Properties props = System.getProperties();

            props.put("mail.smtp.host", config.getString("mail.smtp.host"));
            Session session = Session.getDefaultInstance(props, null);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(config.getString("mail.from")));
            message.setRecipient(RecipientType.TO, new InternetAddress(compte.getEmailProf()));
            message.setSubject("MyProfilWebApp password recovery");

            StringBuilder builder = new StringBuilder();
            builder.append("Dear " + compte.getLogin() + ", \n\n");
            builder.append("You have requested help recovering the password for the MyProfilWebApp user ");
            builder.append(compte.getLogin()).append(".\n\n");
            builder.append("Please use the following link to reset your MyProfilWebApp password: \n");
            builder.append("http://" + serverString + "/MyProfileWebApp/resetPassword.jsf?uid=" + compte.getActionToken());
            builder.append("\n\n--\n");
            builder.append("Your MyProfilWebApp team");

            message.setText(builder.toString());
            message.saveChanges();

            Transport.send(message);
            

        } catch (Exception e) {
            
            throw new CannotSendMailException("Error at sending password reset mail to " + compte.getEmailProf());
        }
		
		
	}
	*/

	/**
     * Resets the password of the user identified by the actionToken.
     * 
     * @param user
     *            the user
     * @throws UserNotFoundException
     *             if the user could not be found
     */
    public void resetPassword(Compte compte) throws UserNotFoundException {

        // We have to find the user because if we use em.merge(user)
        // user.plainPassword will be deleted (because it is transient).
    	Compte foundUser = em.find(Compte.class, compte.getCodeCompte());
        if (foundUser == null) {
            System.out.println("Error resetting password. User not found {}."+ compte.getLogin());
            throw new UserNotFoundException("Error resetting password. User not found " + compte.getLogin());
        }
        foundUser.setPassword(compte.getPassword());
        foundUser.setActionToken("");
        em.persist(foundUser);
        System.out.println("Reset password for user "+ compte.getLogin());
        
       
    
    }

    /**
     * Reads the user identified by the provided action token.
     * 
     * @param actionToken
     *            the action token identifying the user
     * @return the user
     * @throws UserNotFoundException
     *             if no user could be found
     */
    
    @Override
	public Compte getUserByActionToken(String actionToken) throws UserNotFoundException {
    	List<Compte> matchingUsers = em.createQuery("SELECT cpt FROM Compte cpt WHERE cpt.actionToken = :actionToken", Compte.class)
                .setParameter("actionToken", actionToken).getResultList();

            if (matchingUsers.size() != 1) {
            	System.out.println("{} users matching given actionToken {}" + matchingUsers.size() + "" + actionToken);
                throw new UserNotFoundException(matchingUsers.size() + " users matching given actionToken " + actionToken);
            }

            return matchingUsers.get(0);
	}

    /**
     * Reads the user identified by the provided identifier.
     * 
     * @param userIdentifier
     *            the identifier identifying the user
     * @return the user
     * @throws UserNotFoundException
     *             if no user could be found
     */
    @Override
	public Compte getUserByIdentifier(String userIdentifier) throws UserNotFoundException {
    	List<Compte> matchingUsers = em.createQuery("SELECT cpt FROM Compte cpt WHERE cpt.login = :userIdentifier OR cpt.emailProf = :userIdentifier", Compte.class)
    			.setParameter("userIdentifier", userIdentifier).getResultList();

            if (matchingUsers.size() != 1) {
                
                System.out.println("{} users matching given identifier {}" + matchingUsers.size() + "" + userIdentifier);
                throw new UserNotFoundException(matchingUsers.size() + " users matching given identifier " + userIdentifier);
            }

            return matchingUsers.get(0);
	
    
    }
	

}
