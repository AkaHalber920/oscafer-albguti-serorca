package P2;



/**
 * Respresenta cada uno de los alumnos de la academia.
 * 
 * @author oscafer
 * @author albguti
 * @author serorca
 * 
 */


public class Alumno{

	private String nombre;
	private String apellidos;
	private double deuda;
	
	
// Constructor 
	public Alumno(String nombre,String apellidos){
	
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.deuda=0;
		//Probando GIT v3
	}


	/**  
	 * Devuelve el nombre del alumno.
	 * @return Nombre del alumno
	 */
	public String getNombre(){
		return nombre;		
	}
	/**
	 * Cambia el nombre del alumno.
	 * @param nombre
	 */
	public void setNombre(String nombre){
		this.nombre=nombre;
	}
	
	/** 
	 * Devuelve los apellidos del alumno.
	 * @return apellidos del alumno
	 */
	public String getApellidos(){
		return apellidos;		
	}
	/**
	 * Cambia los apellidos del alumno.
	 * @param apellidos
	 */
	public void setApellidos(String apellidos){
		this.apellidos=apellidos;
	}
	
	/**
	 * Devuelve la deuda del alumno.
	 * @return Deuda
	 */
	public double getDeuda() {
		return deuda;
	}
	/**
	 * Anade a la deuda el precio de un curso
	 * @param deuda sera el precio de un curso
	 */
	public void setDeuda(double deuda) {
		this.deuda = deuda;
	}	
}
