package P2;

import java.util.GregorianCalendar;

public class CursoJunior extends Curso {
	
	private int edadMin;
	private int edadMax;

	public CursoJunior(String idCurso, String idioma, int edadMin, int edadMax,
			GregorianCalendar fechaIni, GregorianCalendar fechaFin,
			GregorianCalendar horario, int maxNalumnos, double precio) {
		super(idCurso, idioma,fechaIni, fechaFin, horario, maxNalumnos, precio);
		this.setEdadMax(edadMax);
		this.setEdadMin(edadMin);
	}

	public int getEdadMax() {
		return edadMax;
	}

	public void setEdadMax(int edadMax) {
		this.edadMax = edadMax;
	}

	public int getEdadMin() {
		return edadMin;
	}

	public void setEdadMin(int edadMin) {
		this.edadMin = edadMin;
	}

}
