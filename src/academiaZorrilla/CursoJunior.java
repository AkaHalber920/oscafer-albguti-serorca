package academiaZorrilla;

import java.util.GregorianCalendar;

/**
 * Curso pensado exclusivamente para alumnos de tipo Junior 
 * @author pcp
 *
 */
public class CursoJunior extends Curso {
// Atributos
	/**
	 * Edad minima para poder matricularse
	 */
	private int edadMinima;
	/**
	 * Edad maxima para poder matricularse
	 */
	private int edadMaxima;
// Constructor
	public CursoJunior(String idCurso, String idioma, GregorianCalendar fechaIni, GregorianCalendar fechaFin, GregorianCalendar horario,  int maxNalumnos, double precio, int edadMinima, int edadMaxima){
		super(idCurso,idioma,fechaIni,fechaFin,horario,maxNalumnos,precio);
		this.edadMinima=edadMinima;
		this.edadMaxima=edadMaxima;
	}
// Metodos
	//Edad minima
	/**
	 * Devuelve la edad minima
	 * @return
	 */
	public int getEdadMinima() {
		return edadMinima;
	}
	/**
	 * Cambia la edad minima
	 * @param edadMinima
	 */
	public void setEdadMinima(int edadMinima) {
		this.edadMinima = edadMinima;
	}
	// Edad maxima
	/**
	 * Devuelve la edad maxima
	 * @return
	 */
	public int getEdadMaxima() {
		return edadMaxima;
	}
	/**
	 * Cambia la edad maxima
	 * @param edadMaxima
	 */
	public void setEdadMaxima(int edadMaxima) {
		this.edadMaxima = edadMaxima;
	}

}