package dz.com.cerist.myprofil.entite;

public enum Sexe {

	Masculin("MASCULIN"),
    Féminin("FEMININ");
	
	private final String label;

    private Sexe(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
