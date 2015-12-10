package academiaZorrilla;

/**
 * Alumno de la academia.
 * 
 * @author oscafer
 * @author alberto
 * @author sergio
 */
public class Alumno{
// Atributos
	/**
	 * Nombre del alumno.
	 */
	protected String nombre;
	/**
	 * Apellidos del alumno.
	 */
	protected String apellidos;
	/**
	 * Deuda total del alumno
	 */
	protected double deuda;
// Constructor 
	public Alumno(String nombre,String apellidos){
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.deuda=0;
	}
// Metodos
	// Nombre
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
	// Apellidos
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
	// Deuda
	/**
	 * Devuelve la deuda del alumno.
	 * @return Deuda
	 */
	public double getDeuda() {
		return deuda;
	}
	/**
	 * Añade a la deuda el precio de un curso
	 * @param c curso
	 */
	public void setDeuda(double deuda) {
		this.deuda = deuda;
	}	
}
