package academiaZorrilla;

import java.util.GregorianCalendar;

import academiaZorrilla.Adulto;
import academiaZorrilla.Alumno;
/**
 * 
 * @author oscafer
 * @author alberto
 * @author sergio
 *
 */
public class Junior extends Alumno{
	/**
	 * Fecha de nacimiento
	 */
	private GregorianCalendar fechaNacimiento;
	/**
	 * Alumno adulto responsable de este alumno (junior)
	 */
	private Adulto adultoResponsable;
	
	public Junior(String nombre, String apellidos, GregorianCalendar fechaNacimiento, Adulto adultoResponsable) {
		super(nombre, apellidos);
		this.fechaNacimiento=fechaNacimiento;
		this.adultoResponsable=adultoResponsable;
	}
	// Fecha nacimiento
	/**
	 * Devuelve la fecha de nacimiento
	 * @return fechaNacimiento
	 */
	public GregorianCalendar getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * Cambia la fecha de nacimiento
	 * @param fechaNacimiento
	 */
	public void setFechaNacimiento(GregorianCalendar fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	// Aduto responsable
	/**
	 * Devuelve el alumno (adulto) responsable 
	 * @return
	 */
	public Adulto getAdultoResponsable(){
		return adultoResponsable;
	}
	/**
	 * Cambia el alumno (adulto) responsable
	 * @param alumnoAdulto
	 */
	public void setAdultoResponsable(Adulto alumnoAdulto){
		this.adultoResponsable=alumnoAdulto;
	}

}
