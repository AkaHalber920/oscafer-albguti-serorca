package academiaZorrilla;

import academiaZorrilla.Alumno;

public class Adulto extends Alumno{
	/**
	 * DNI del alumno.
	 */
	private String DNI;
	/**
	 * 
	 * @param DNI
	 * @param nombre
	 * @param apellidos
	 */
	public Adulto(String DNI, String nombre, String apellidos) {
		super(nombre, apellidos);
		this.DNI=DNI;
	}
	/**  
	 * Devuelve el DNI del alumno
	 * @return DNI del alumno
	 */
	public String getDNI(){
		return DNI;		
	}
}
