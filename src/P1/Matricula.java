package P1;
/**
 * Representa la inscripcion de un alumno en un curso concreto.
 * 
 * @author oscafer
 * @author albguti
 * @author serorca
 * 
 */
public class Matricula {
	private String idMatricula;
	private boolean estado;
	
	// Constructor
	public Matricula(String idMatricula){
		this.idMatricula= idMatricula;
		this.estado=false;
	}

	/**
	 * Devuelve el identificador de la matricula.
	 * @return idMatricula
	 */
	public String getIdMatricula() {
		return idMatricula;
	}
	
	/**
	 * Devuelve el estado en que se encuentra la matricula
	 * @return true/false (pagada/pendiente)
	 */
	public boolean getEstado() {
		return estado;
	}
	
	/**
	 * Cambia el estado de la matricula.
	 * @param estado true/false
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}
