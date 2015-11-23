package P2;

import java.util.GregorianCalendar;

public class CursoNormal extends Curso {
	
	private int nivel;
	
	public CursoNormal(String idCurso, String idioma, int nivel,
			GregorianCalendar fechaIni, GregorianCalendar fechaFin,
			GregorianCalendar horario, int maxNalumnos, double precio) {
		super(idCurso, idioma, fechaIni, fechaFin, horario, maxNalumnos, precio);
		this.nivel = nivel;
	}

	/**
	 * Devuelve el nivel del curso.
	 * 
	 * @return nivel del curso
	 */
	public int getNivel() {
		return nivel;
	}
	
	/**
	 * Cambia el nivel del curso
	 * 
	 * @param nivel
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	

}
