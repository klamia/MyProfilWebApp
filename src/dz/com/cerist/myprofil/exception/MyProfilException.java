package dz.com.cerist.myprofil.exception;

@SuppressWarnings("serial")
public class MyProfilException extends RuntimeException {

	// code d'erreur
	private int code;

	public MyProfilException(int code) {
	super();
	this.code = code;
	 }
	
	public MyProfilException(String message, int code) {
	super(message);
	this.code = code;
	 }

	public MyProfilException(Throwable cause, int code) {
	super(cause);
	this.code = code;
	 }
	
	 public MyProfilException(String message, Throwable cause, int code) {
	 super(message, cause);
	 this.code = code;
	 }
	
      // getter et setter
	
	 public int getCode() {
	 return code;
	 }
	
	 public void setCode(int code) {
	 this.code = code;
	 }




}
