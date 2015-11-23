package P2;

public class MatriculaJunior extends Matricula{

	private int numTel;
	
	public MatriculaJunior(String idMatricula, int numTel) {
		super(idMatricula);
		this.setNumTel(numTel);
	}

	public int getNumTel() {
		return numTel;
	}

	public void setNumTel(int numTel) {
		this.numTel = numTel;
	}

}
