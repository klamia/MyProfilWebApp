package dz.com.cerist.myprofil.web.util;
/*
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.primefaces.model.UploadedFile;
*/
//import dz.com.naftal.audit.business.model.bo.Mail;

public class MailHelper {

	/*
	 //Type texte brut 
	static public final int TEXT = 0;
	// Type text/html 
	static public final int HTML = 1;

	private String smtpAuthUser;
	private String smtpAuthPwd;

	private Properties props;
	private Session session;
	private MimeMessage message;

	private UploadedFile uploadedFile;

	private InputStream inputStream;

	private File fileAttached;

	private String mimetype;
	/**
	 * Si on n'appelle pas setFrom, met automatiquement noreply@societe.fr
	
	private boolean noreply = true;


	 // Créateur d'un mail.
	
	public MailHelper() {
		// Par defaut en texte brut
		mimetype = "text/html";
		props = new Properties();
		// debug
		props.put("mail.debug", MessageHelper.getMessageResourceString(
				"bundles.mail", "mail.debug"));
		// protocol
		props.put("mail.transport.protocol", MessageHelper
				.getMessageResourceString("bundles.mail",
						"mail.transport.protocol"));
		// hostname
		props.put("mail.smtp.host", MessageHelper.getMessageResourceString(
				"bundles.mail", "mail.smtp.host"));
		// port
		props.put("mail.smtp.port", MessageHelper.getMessageResourceString(
				"bundles.mail", "mail.smtp.port"));
		// autantification
		props.put("mail.smtp.auth", MessageHelper.getMessageResourceString(
				"bundles.mail", "mail.smtp.auth"));

		props.put("mail.smtp.auth.plain.disable", MessageHelper
				.getMessageResourceString("bundles.mail",
						"mail.smtp.auth.plain.disable"));

		props.put("mail.smtp.auth.ntlm.disable", MessageHelper
				.getMessageResourceString("bundles.mail",
						"mail.smtp.auth.ntlm.disable"));

		props.put("mail.smtp.auth.ntlm.domain", MessageHelper
				.getMessageResourceString("bundles.mail",
						"mail.smtp.auth.ntlm.domain"));

		// props.put("mail.smtp.starttls.enable",MessageHelper.getMessageResourceString("bundles.mail",
		// mail.smtp.starttls.enable"));
		// Get session
		session = Session.getDefaultInstance(props);
		// Define message
		message = new MimeMessage(session);
	}

	// ############################################################
	// Ajout d'exp�diteur et destinataires
	// ############################################################

	public String getSmtpAuthUser() {
		return smtpAuthUser;
	}

	public void setSmtpAuthUser(String smtpAuthUser) {
		this.smtpAuthUser = smtpAuthUser;
	}

	public String getSmtpAuthPwd() {
		return smtpAuthPwd;
	}

	public void setSmtpAuthPwd(String smtpAuthPwd) {
		this.smtpAuthPwd = smtpAuthPwd;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public File getFileAttached() {
		return fileAttached;
	}

	public void setFileAttached(File fileAttached) {
		this.fileAttached = fileAttached;
	}

	/**
	 * Ajoute un expéditeur
	 * 
	 * @param expediteur
	 * @throws Exception
	 
	public void setFrom(String expediteur) throws Exception {
		if (expediteur.trim().length() > 0) {
			message.setFrom(checkMail(expediteur));
			noreply = false;

			System.out.print(noreply);
		}
	}

	/**
	 * Ajoute un destinataire
	 * 
	 * @param destinataire
	 * @throws Exception
	 
	public void setTo(String destinataire) throws Exception {
		if (destinataire.trim().length() > 0) {
			if (plusieursMails(destinataire))
				setTo(decoupe(destinataire));
			else {
				message.addRecipient(Message.RecipientType.TO,
						checkMail(destinataire));
			}
		}
	}

	/**
	 * Ajoute plusieurs destinataires
	 * 
	 * @param dest
	 * @throws Exception
	 
	public void setTo(String[] dest) throws Exception {
		for (int i = 0; i < dest.length; i++)
			setTo(dest[i]);
	}

	/**
	 * Ajoute un destinataire en copie
	 * 
	 * @param cc
	 * @throws Exception
	 
	public void setCC(String cc) throws Exception {
		if (cc.trim().length() > 0) {
			if (plusieursMails(cc))
				setCC(decoupe(cc));
			else {
				message.addRecipient(Message.RecipientType.CC, checkMail(cc));
			}
		}
	}

	/**
	 * Ajoute plusieurs destinataires en copie
	 * 
	 * @param cc
	 * @throws Exception
	
	public void setCC(String[] cc) throws Exception {
		for (int i = 0; i < cc.length; i++)
			setCC(cc[i]);
	}

	/**
	 * Ajoute un destinataire en copie cachée.
	 * 
	 * @param bcc
	 * @throws Exception
	
	public void setCopieCachee(String bcc) throws Exception {
		if (bcc.trim().length() > 0) {
			if (plusieursMails(bcc))
				setCopieCachee(decoupe(bcc));
			else {
				message.addRecipient(Message.RecipientType.BCC, checkMail(bcc));
			}
		}
	}

	/**
	 * Ajoute plusieurs destinataires en copie cachée.
	 * 
	 * @param bcc
	 * @throws Exception
	
	public void setCopieCachee(String[] bcc) throws Exception {
		for (int i = 0; i < bcc.length; i++)
			setCopieCachee(bcc[i]);
	}

	// ############################################################
	// Champs avec destinataires multiples
	// ############################################################

	/**
	 * Teste si y'a plusieurs mails dans la chaîne, séparés par un espace,
	 * une virgule ou un point-virgule
	 * 
	 * @param mails
	 * @return

	static public boolean plusieursMails(String mails) {
		return ((mails.indexOf(' ') + mails.indexOf(',') + mails.indexOf(';')) > -1);
	}

	/**
	 * Découpe la chaîne contenant plusieurs mails en String[]
	 * 
	 * @return
	
	static public String[] decoupe(String mails) {
		return mails.split(" |,|;");
	}

	// ############################################################
	// Sujet et message
	// ############################################################

	/**
	 * Ajoute le sujet
	 * 
	 * @param sujet
	 * @throws Exception
	
	public void setSujet(String sujet) throws Exception {
		message.setSubject(sujet);
	}

	/**
	 * Choisir entre le type texte brut ({@link Mail#TEXT}) ou HTML (
	 * {@link Mail#HTML}).
	 * 
	 * @param mime
	 * @throws Exception
	
	public void setMimeType(String mime) throws Exception {
		mimetype = mime;
	}

	/**
	 * Ajoute le message
	 * 
	 * @param message
	 * @throws Exception
	
	public void setMessage(String message) throws Exception {
		if (mimetype != null)
			this.message.setContent(message, mimetype);
		else
			this.message.setText(message);

	}

	/**
	 * Retrun SMTP HOST NAME
	 * 
	 * @return
	

	public String getSmtpHostName() {

		// 10.96.5.15
		return MessageHelper.getMessageResourceString("bundles.mail",
				"mail.smtp.host");

	}

	/**
	 * Retrun SMTP HOST NAME
	 * 
	 * @return
	

	public Integer getSmtpHostPort() {

		return Integer.parseInt(MessageHelper.getMessageResourceString(
				"bundles.mail", "mail.smtp.port"));
	}

	// ############################################################
	// Envoi
	// ############################################################

	/**
	 * Envoie le mail
	 * 
	 * @throws Exception
	

	public void send() throws Exception {

		session.setDebug(true);

		Transport transport = session.getTransport();
		
		

		transport.connect(getSmtpHostName(), getSmtpHostPort(),	getSmtpAuthUser(), getSmtpAuthPwd());

		transport.sendMessage(message,
				message.getRecipients(Message.RecipientType.TO));

		transport.close();

	}

	// ############################################################
	// V�rifications de la validit� d'adresse e-mails
	// (fonctions pour l'ext�rieur : jsp ou WebServices...)
	// ############################################################
	/**
	 * V�rifie si l'adresse pass�e en param�tre est valide. Si elle ne contient
	 * pas de @, v�rifie si elle correspond bien à un pseudo (voir nameToMail).
	 * 
	 * @param mail
	 
	static public void isValide(String mail) throws Exception {
		if (plusieursMails(mail))
			areValide(decoupe(mail));
		else
			MailHelper.checkMail(mail);
	}

	/**
	 * V�rifie si les adresses pass�es en param�tres sont valides. Si elles ne
	 * contient pas de @, v�rifie si elles correspondent � des pseudos (voir
	 * nameToMail).
	 * <p>
	 * V�rifie chacune des adresse, et renvoie une exception global, contenant
	 * plusieurs messages d'erreur si plusieurs adresses sont fausses, chaque
	 * message �tant s�par� par un \n.
	 * 
	 * @param mails
	 * @throws Exception
	
	static public void areValide(String[] mails) throws Exception {
		String msg = "";
		for (int i = 0; i < mails.length; i++) {
			try {
				isValide(mails[i]);
			} catch (Exception e) {
				msg += ((msg.length() > 0) ? "\n" : "") + e.getMessage();
			}
		}
		if (msg.length() > 0) {
			throw new Exception(msg);
		}
	}

	// ############################################################
	// Fonctions private,
	// statiques pour être appelés par les public isValide et areValide
	// ############################################################

	/**
	 * V�rifie la validit� d'une adresse e-mail, et renvoie sous forme
	 * d'InternetAdress ou une exception en cas d'erreur.
	 * 
	 * @param mail
	 * @throws Exception
	
	static private InternetAddress checkMail(String mail) throws Exception {
		// Pas de @, c'est peut-etre un pseudonyme ?...
		if (mail.indexOf('@') == -1) {
			return new InternetAddress(nameToMail(mail));
		} else {
			// le parse d'InternetAddress ne renvoie pas d'erreur sur
			// adresse@mail
			// alors on fait un test simple pour savoir si y'a un point, apres
			// l'@
			// adresse@mail.fr est OK, adresse@mail renvoie une exception.
			if (mail.lastIndexOf('.') < mail.indexOf('@')) {
				throw new Exception("Adresse email \"" + mail + "\" invalide");
			} else {
				return new InternetAddress(mail);
			}
		}
	}

	/**
	 * Ici votre fonction perso, qui transforme des noms generiques en adresses
	 * e-mail.
	 * <p>
	 * Par exemple : webmaster -> webmaster@mtp.dz
	 * 
	 * @param name
	 * @throws Exception
	 *             si le pseudo est inconnu.
	 
	static private String nameToMail(String name) throws Exception {
		// L'exemple du mail au webmaster, si on met juste "webmaster" sans
		// mettre
		// @societe.fr, cette fonction transforme.
		if (name.equalsIgnoreCase("webmaster"))
			return "webmaster@naftal.dz";
		// else if....
		// else if....
		// else c'est une erreur
		else
			throw new Exception("Nom \"" + name + "\" inconnu");

	}

	public void sendMailAttchement() throws MessagingException {

		

			session.setDebug(true);
			// create and fill the first message part
			MimeBodyPart mbp1 = new MimeBodyPart();

			mbp1.setText("Send Attachement file");

			// create the second message part
			MimeBodyPart mbp2 = new MimeBodyPart();

			mbp2.setHeader("Content-Type", "application/pdf");

			// attach the file to the messag

			FileDataSource fds = new FileDataSource(createFileFromInputStream());
			// FileDataSource fds = new FileDataSource(getFileAttached());

			mbp2.setDataHandler(new DataHandler(fds));
			// mbp2.setDataHandler(new DataHandler(new
			// ByteArrayDataSource(fds.toString(), "application/pdf")));

			mbp2.setFileName(fds.getName());

			// create the Multipart and add its parts to it
			Multipart mp = new MimeMultipart();

			mp.addBodyPart(mbp1);

			mp.addBodyPart(mbp2);
			// msg.setContent(message, "text/plain");

			// add the Multipart to the message
			message.setContent(mp);

			// set the Date: header
			message.setSentDate(new Date());

			// send the message
			Transport transport = session.getTransport();

			transport.connect(getSmtpHostName(), getSmtpHostPort(),
					getSmtpAuthUser(), getSmtpAuthPwd());

			transport.sendMessage(message,
					message.getRecipients(Message.RecipientType.TO));

			transport.close();

		
	}

	public File createFileFromInputStream() {
		File f = new File("Notification.pdf");

		try {

			// InputStream inputStream = getUploadedFile().getInputStream();
			InputStream inputStream = getInputStream();
			OutputStream out = new FileOutputStream(f);
			byte buf[] = new byte[1024];
			int len;
			while ((len = inputStream.read(buf)) > 0)
				out.write(buf, 0, len);
			out.close();
			inputStream.close();
			System.out
					.println("\nFile is created...................................");

		} catch (FileNotFoundException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Le fichier " + f.getName()
				+ (f.exists() ? "exist" : "n'existe pas"));

		return f;
	}
	
	public void sendMailAttchement(String fileName,String messageText) {

		try {

			session.setDebug(true);
			// create and fill the first message part
			MimeBodyPart mbp1 = new MimeBodyPart();
			
			mbp1.setHeader("Content-Type", "text/html");


			mbp1.setText(messageText);
			
			mbp1.setContent(messageText, "text/html");

			// create the second message part
			MimeBodyPart mbp2 = new MimeBodyPart();

			mbp2.setHeader("Content-Type", "application/pdf");

			// attach the file to the messag

			DataSource fds = new FileDataSource(fileName);
			// FileDataSource fds = new FileDataSource(getFileAttached());

			mbp2.setDataHandler(new DataHandler(fds));
			
			mbp2.setFileName(fds.getName());

			// create the Multipart and add its parts to it
			Multipart mp = new MimeMultipart();

			mp.addBodyPart(mbp1);

			mp.addBodyPart(mbp2);
			// msg.setContent(message, "text/plain");

			// add the Multipart to the message
			message.setContent(mp);

			// set the Date: header
			message.setSentDate(new Date());

			// send the message
			Transport transport = session.getTransport();

			transport.connect(getSmtpHostName(), getSmtpHostPort(),
					getSmtpAuthUser(), getSmtpAuthPwd());

			transport.sendMessage(message,
					message.getRecipients(Message.RecipientType.TO));

			transport.close();

		} catch (MessagingException mex) {
			mex.printStackTrace();
			Exception ex = null;
			if ((ex = mex.getNextException()) != null) {
				ex.printStackTrace();
			}
		}
	}

}

/**
/

public void sendRapportFinalByMail() {

		try {

			
			;
			
			
			
			MailHelper mail = new MailHelper();

			mail.setMimeType("text/html");

			String userName = this.adressMail.substring(0,
					this.adressMail.indexOf("@"));
			
			
			String password = inserteUserAndRoleToAudite(userName);
			//String password = randomString(12);
			

			Object[] missionId = new Object[1];

			missionId[0] = this.mission.getMissionId();

			Object[] login = new Object[2];

			login[0] = userName;

			login[1] = password;
			
			
			
			
			LOGGER.info("user created: "+userName+ " "+password);
			
			

			StringBuffer stringBuffer = new StringBuffer(
					MessageHelper.getMessageResourceString("bundles.mail",
							"message.envoi.rapport.final.lienacces",
							missionId, Locale.FRENCH));
			stringBuffer.append(MessageHelper.getMessageResourceString(
					"bundles.mail", "message.envoi.rapport.final.utilisateur",
					login, Locale.FRENCH));

			this.message = this.message + stringBuffer.toString();

			FacesContext fc = FacesContext.getCurrentInstance();

			ServletContext servletContext = (ServletContext) fc
					.getExternalContext().getContext();

			String fileName = servletContext
					.getRealPath(this.urlDocumentCourant);

			mail.setSmtpAuthUser(MessageHelper.getMessageResourceString(
					"bundles.mail", "smtp.auth.user"));

			mail.setSmtpAuthPwd(MessageHelper.getMessageResourceString(
					"bundles.mail", "smtp.auth.pwd"));
			
			mail.setFrom(MessageHelper.getMessageResourceString(
					"bundles.mail", "smtp.auth.user.from"));

			mail.setTo(this.adressMail);
			
			LOGGER.info("Envoie du mail � "+this.adressMail);
			mail.setSujet(objetMail);

			

			mail.sendMailAttchement(fileName, message);

			/********* MODIF POUR ENREGISTREMENT DES MAILS 
	
			List<Document> documents = new ArrayList<Document>(1);
			documents.add(this.document);
			this.mail.setListDocuments(documents);
			this.mail.setMission(this.mission);
			this.mail.setEmail(this.adressMail);
			this.mail.setRappel(false);
			this.mail.setTextMail(message);
			this.mail.setTypeMail("ENVOI_RAPPORT_FINAL");
			this.mail.setDateEnvoiMail(new Date());
			mailService.persist(this.mail);
			/*******************************************************************
			updateSituationRecommandation(PLAN_ACTION_DEVEROUILLE);
			
			
			JsfUtil.addSuccessMessage(MessageHelper
					.getMessageResourceString("M_I039"));

		} catch (Exception e) {
			e.printStackTrace();

			JsfUtil.addErrorMessage("Une erreur s'est produite.");

		}
*/
	}
	
