package P2;

import java.util.GregorianCalendar;

public class Junior extends Alumno{
	
	private GregorianCalendar fechaNac;
	private Adulto adultoResponsable;
	
	public Junior( Adulto adultoResponsable, String nombre, String apellidos, GregorianCalendar fechaNac) {
		super(nombre, apellidos);
		this.fechaNac=fechaNac;
		this.setAdultoResponsable(adultoResponsable);
	}

	public GregorianCalendar getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(GregorianCalendar fechaNac) {
		this.fechaNac = fechaNac;
	}

	public Adulto getAdultoResponsable() {
		return adultoResponsable;
	}

	public void setAdultoResponsable(Adulto adultoResponsable) {
		this.adultoResponsable = adultoResponsable;
	}

	
	
	

}
