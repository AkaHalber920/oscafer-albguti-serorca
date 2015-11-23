package P2;

import java.util.GregorianCalendar;

public class Junior extends Alumno{
	
	private GregorianCalendar fechaNac;
	
	public Junior(GregorianCalendar fechaNac, String nombre, String apellidos) {
		super(nombre, apellidos);
		this.setFechaNac(fechaNac);
	}

	public GregorianCalendar getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(GregorianCalendar fechaNac) {
		this.fechaNac = fechaNac;
	}

	
	
	

}
