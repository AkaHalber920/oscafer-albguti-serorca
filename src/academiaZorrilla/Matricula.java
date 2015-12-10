package academiaZorrilla;

import academiaZorrilla.Alumno;
import academiaZorrilla.Curso;

/**
 * Representa la inscripción de un alumno en un curso de la academia.
 * 
 * @author oscafer
 * @author alberto
 * @author sergio
 */
public class Matricula {
// Atributos
	/**
	 * Número único de matrícula.
	 */
	protected int idMatricula;
	/**
	 * Estado: pagada o pendiente de pago(Inicialmente pendiente de pago).
	 */
	protected boolean estado;
	/**
	 * Alumno
	 */
	protected Alumno alumno;
	/**
	 * Curso
	 */
	protected Curso curso;
// Constructor
	public Matricula(int idMatricula, Alumno alumno, Curso curso){
		this.idMatricula=idMatricula;
		this.alumno=alumno;
		this.curso=curso;
		this.estado=false;
	}
// Metodos
	// IdMatricula
	/**
	 * Devuelve el identificador de la matricula.
	 * @return idMatricula
	 */
	public int getIdMatricula() {
		return idMatricula;
	}
	// Alumno
	/**
	 * Devuelve el alumno de esta matricula
	 * @return alumno
	 */
	public Alumno getAlumno(){
		return alumno;
	}
	/** 
	 * Cambia el alumno de esta matricula
	 * @param alumno
	 */
	public void setAlumno(Alumno alumno){
			this.alumno=alumno;
	}
	// Curso
	/**
	 * Devuelve el curso de esta matricula
	 * @return curso
	 */
	public Curso getCurso(){
		return curso;
	}
	/**
	 * Cambia el curso de esta matricula
	 * @param curso
	 */
	public void setCurso(Curso curso){
		this.curso=curso;
	}
	// Estado
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
