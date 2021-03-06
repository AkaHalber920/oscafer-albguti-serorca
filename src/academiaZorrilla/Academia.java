package academiaZorrilla;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import com.sun.xml.internal.ws.policy.AssertionSet;

/**
 * Crea y mantiene la lista de cursos, alumnos y matr��culas. 
 * Comprueba si se dan las condiciones previas para las matr��culas y si es posible crearlas 
 * Debe probar del mismo modo el resto de m��todos p��blicos de las otras tres clases.
 *
 */
public class Academia {
// Atributos
	 /**
	 * Lista de los cursos de la academia.
	 */
	private ArrayList<Curso> cursosAcademia;
	/**
	 * Lista de los alumnos de la academia.
	 */
	private ArrayList<Alumno> alumnosAcademia;
	/**
	 * Lista de las matriculas de la academia.
	 */
	private ArrayList<Matricula> matriculasAcademia;
	private int idMatricula; // Identificador que asignamos a las matriculas, auto incremental
// Constructor
	/**
	 * Constructor de una academia
	 */
	public Academia(){
		this.cursosAcademia= new ArrayList<Curso>();
		this.alumnosAcademia=  new ArrayList<Alumno>();
		this.matriculasAcademia= new ArrayList<Matricula>();
	}
// Metodos
	
	// Alumnos
	/**
	 * Crea un alumno.
	 * @param DNI	del  alumno responsable
	 * @param nombre del alumno
	 * @param apellidos del alumno
	 * @param dia	de nacimiento 
	 * @param mes	de nacimiento 
	 * @param ano	de nacimiento 
	 */
	public void crearAlumno(String DNI,String nombre,String apellidos, int dia, int mes ,int ano,String DNIResponsable) throws InterruptedException{
		// Comprobaciones
		//if(DNI!=null && DNIResponsable != null){
		// Fin comprobaciones
			if(DNIResponsable==null){		// ADULTO porque no tiene adulto responsable
				Alumno adulto=checkAlumno(DNI);
				try{
				assert adulto == null;
				//if(adulto!=null){
				//	System.out.println("**AVISO** Ya existe un alumno con DNI "+DNI+".");
				//}else{
					Alumno alumno= new Adulto(DNI, nombre, apellidos);
					alumnosAcademia.add(alumno);
					System.out.println("** - Alumno "+((Adulto)alumno).getNombre()+" "+((Adulto)alumno).getApellidos()+" creado correctamente.");	
				//}
				}catch (AssertionError ex) {
					System.err.println("**AVISO** Ya existe un alumno con DNI "+DNI+".");
				}
			}else{						// JUNIOR
				Alumno adultoResponsable=checkAlumno(DNIResponsable);
				if(adultoResponsable!=null){
					GregorianCalendar fechaNacimiento=new GregorianCalendar(ano, mes-1, dia);
					java.sql.Date milfechaNacimiento = new java.sql.Date(fechaNacimiento.getTimeInMillis());	// Almacenamos la conversion en milisegundos de la fecha de nacimiento
					GregorianCalendar fechaActual = new GregorianCalendar();
					java.sql.Date milfechaActual = new java.sql.Date(fechaActual.getTimeInMillis());	// Almacenamos la conversion en milisegundos de la fecha actual
					long diferencia = this.getDiferenciaFechas(milfechaNacimiento, milfechaActual, 0);
					try {
					    assert diferencia > 0 & diferencia<17;
					//if(diferencia > 0 && diferencia<17){ // Comprobamos que la diferencia sea mayor que 0 y menor de 17
						Alumno alumno= new Junior(nombre,apellidos, fechaNacimiento, (Adulto)adultoResponsable);
						alumnosAcademia.add(alumno);
						System.out.println("** - Alumno "+alumno.getNombre()+" "+alumno.getApellidos()+" creado correctamente.");
					//}else System.out.println("**AVISO** La edad del alumno debe ser mayor de 0 y menor de 17.(Tiene "+diferencia+" a��os) **");
					}catch(AssertionError ex) {
						System.err.println("La edad del niño esta mallll");
					}
				}else System.out.println("**AVISO** No existe el alumno adulto con DNI "+DNIResponsable+". **");
			}
		// Mensajes de error
	
	
	
	
	}
	/**
	 * Imprime la lista de alumnos.
	 */
	public void imprimirAlumnos(){
		if(!alumnosAcademia.isEmpty()){
		System.out.println("____________________________________________________________________");
		System.out.println("_____________________________ Alumnos ______________________________\n");
		Iterator<Alumno> it = alumnosAcademia.iterator();
		while(it.hasNext()){
			Alumno alumno=it.next();
			if(alumno instanceof Adulto){
				System.out.println("- DNI: "+((Adulto) alumno).getDNI()+"\n\tNombre: "+alumno.getNombre()+"\tApellidos: "+alumno.getApellidos()+"\tDeuda: "+alumno.getDeuda());
				System.out.println("  -----------------------------------------------------------------");
			}else{
				String mes;
				// Si el mes es 12 en fecha de nacimiento
				if (((Junior)alumno).getFechaNacimiento().get(Calendar.MONTH) ==11) {
					mes =  "12";
				} else {
					mes = Integer.toString(((((Junior)alumno).getFechaNacimiento().get(Calendar.MONTH))+1));
				}
				System.out.println("- Nombre: "+alumno.getNombre()+"\tApellidos: "+alumno.getApellidos()+"\tDeuda: "+alumno.getDeuda()+
				"\n\tFecha nacimiento: "+((Junior)alumno).getFechaNacimiento().get(Calendar.DATE) + "/"+ mes +
				"/" + ((Junior)alumno).getFechaNacimiento().get(Calendar.YEAR)+"\tDNI responsable: "+((Junior)alumno).getAdultoResponsable().getDNI());
				System.out.println("  -----------------------------------------------------------------");
			}
		}System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''");
		}else System.out.println("**OK** - No existen alumnos en la academia.");
		
	}
	/**
	 * Imprime alumnos junior que son responsablidad de un adulto.
	 * @param DNI del adulto
	 */
	public void imprimirListaJuniorsDeAdulto(String DNI){
		Alumno aux = checkAlumno(DNI);
		if(aux!=null){
		if(!alumnosAcademia.isEmpty()){
			System.out.println("____________________________________________________________________");
			System.out.println("______ Alumnos junior del alumno adulto responsable: "+DNI+" _____\n");
			Iterator<Alumno> it = alumnosAcademia.iterator();
			while(it.hasNext()){
				Alumno alumno=it.next();
				if(alumno instanceof Junior && ((Junior)alumno).getAdultoResponsable().getDNI()==DNI){
					System.out.println("\t- Nombre: "+alumno.getNombre()+"\tApellidos: "+alumno.getApellidos());
					System.out.println("  -----------------------------------------------------------------");
				}
			}System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''");
			}else System.out.println("**OK** - No existen alumnos en la academia.");
		}else System.out.println("**AVISO** No existe el alumno con DNI "+DNI+".");
		
	}
	/**
	 * Cambia de nivel a un alumno en un curso normal, teniendo en cuenta que solo se puede subir o bajar un nivel, 
	 * dentro del mismo idioma. Adem��s no se puede superar el m��ximo numero de alumnos del curso al que cambia.
	 * @param idCurso	del curso del que queremos cambiar de nivel al alumno
	 * @param DNI		del alumno adulto que cambiamos de nivel
	 * @param nombre 	del alumno junior que queremos cambiar de nivel
	 * @param apellidos del alumno junior que queremos cambiar de nivel
	 * @param x			(true=subir de nivel / false= bajar de nivel)
	 */
	public void cambiarNivel(String idCurso,String DNI, String nombre, String apellidos, boolean x){
		Curso curso=checkCurso(idCurso);				// Almacenamos el curso ,
		if(curso!=null){								// si existe
			if(curso instanceof CursoNormal){
				if(DNI!=null){		// Adulto
					Alumno alumno = checkAlumno(DNI);					// Almacenamos el alumno
					if(alumno!=null){									// si existe
						Matricula matricula = checkMatricula(alumno, curso);
						if(matricula!=null){
							String idioma = curso.getIdioma();			// Almacenamos el idioma del curso de procedencia
							int nivel = ((CursoNormal)curso).getNivel();				// Almacenamos el nivel del curso de procedencia
							Iterator<Curso> it = cursosAcademia.iterator();
							boolean okMatriculado=false;	// Bandera para registrar que se ha realizado el cambio (true=cambio realizado)
							boolean encontradoCurso=false;	// Bandera para registrar que se ha encontrado el curso inf/sup (tre=curso inf/sup encontrado)
							boolean noMatriculado=false;	// Bandera para registrar que el alumno no esta matriculado (true=alumno no matriculado)
							while (it.hasNext() && (!okMatriculado || !noMatriculado)) { // Seguimos en el bucle si quedan m��s cursos por recorrer y el alumno sigue sin matricularse o no ha intentado matricularse y no se premite por la politica adoptada
								Curso cursoAux = it.next();
								if(x){						// Comprobamos si vamos a subir de curso
									if (cursoAux.getIdioma() == idioma && ((CursoNormal)cursoAux).getNivel() == nivel + 1){ // Comprobamos que existe el curso superior
										encontradoCurso=true;
										if(checkMatricula(alumno,cursoAux)==null){					// Comprobamos que no exite la matricula
											noMatriculado=true;
											if(nAlumnosMatriculados(cursoAux)<cursoAux.getMaxNalumnos()){	// Comprobamos que el curso no esta completo
												okMatriculado=true;
												matricula.setCurso(cursoAux);				// Cambiamos la matricula al idioma nuevo
												alumno.setDeuda(alumno.getDeuda()-curso.getPrecio());	// Restamos el precio del curso del que procede
												alumno.setDeuda(alumno.getDeuda()+cursoAux.getPrecio());// A��adimos el precio del curso de nivel superior
											}
										}
									}
								}else{						// Si bajamos de curso
									if (cursoAux.getIdioma() == idioma && ((CursoNormal)cursoAux).getNivel() == nivel -1){ // Comprobamos que existe el curso superior
										encontradoCurso=true;
										if(checkMatricula(alumno,cursoAux)==null){					// Comprobamos que no exite la matricula
											noMatriculado=true;
											if(nAlumnosMatriculados(cursoAux)<cursoAux.getMaxNalumnos()){	// Comprobamos que el curso no esta completo
												okMatriculado=true;
												matricula.setCurso(cursoAux);				// Cambiamos la matricula al idioma nuevo
												alumno.setDeuda(alumno.getDeuda()-curso.getPrecio());	// Restamos el precio del curso del que procede
												alumno.setDeuda(alumno.getDeuda()+cursoAux.getPrecio());// A��adimos el precio del curso de nivel inferior
											}
										}
									}
								}
							}
							// Mensajes dependiendo del caso
							if(x) {		// Si subimos nivel
								if(encontradoCurso){		// y encuentra el curso
									if(noMatriculado){		// y no esta matriculado
										if(okMatriculado){	// y ha conseguido matricularse
											System.out.println("**OK** - Cambio del nivel "+((CursoNormal)curso).getNivel()+" al "+(((CursoNormal)curso).getNivel()+1)+" en el idioma de "+curso.getIdioma()+" al alumno con DNI "+DNI+" realizado correctamente.");
										}else System.out.println("**AVISO** No se ha podido realizar el cambio de nivel del alumno con DNI "+DNI+"\n\t en el curso de "+curso.getIdioma()+", en el nivel "+(((CursoNormal)curso).getNivel()+1)+" porque esta lleno.");
									}else System.out.println("**AVISO** No se ha podido realizar el cambio de nivel del alumno con DNI "+DNI+"\n\t porque ya esta matriculado en el curso de "+curso.getIdioma()+", en el nivel "+(((CursoNormal)curso).getNivel()+1)+". **");
								}else System.out.println("**AVISO** No se ha podido realizar el cambio de nivel del alumno con DNI "+DNI+"\n\t porque no existe el curso superior de "+curso.getIdioma()+", en nivel "+(((CursoNormal)curso).getNivel()+1)+". **");
							}else {		// Si bajamos nivel
								if(encontradoCurso){		// y encuentra el curso
									if(noMatriculado){		// y no esta matriculado
										if(okMatriculado){	// y ha conseguido matricularse
											System.out.println("**OK** - Cambio del nivel "+((CursoNormal)curso).getNivel()+" al "+(((CursoNormal)curso).getNivel()-1)+" en el idioma de "+curso.getIdioma()+" al alumno con DNI "+DNI+" realizado correctamente.");
										}else System.out.println("**AVISO** No se ha podido realizar el cambio de nivel del alumno con DNI "+DNI+"\n\t en el curso de "+curso.getIdioma()+", en el nivel "+(((CursoNormal)curso).getNivel()-1)+" porque esta lleno.");
									}else System.out.println("**AVISO** No se ha podido realizar el cambio de nivel del alumno con DNI "+DNI+"\n\t porque ya esta matriculado  en el curso de "+curso.getIdioma()+", en el nivel "+(((CursoNormal)curso).getNivel()-1)+". **");
								}else System.out.println("**AVISO** No se ha podido realizar el cambio de nivel del alumno con DNI "+DNI+"\n\t porque no existe el curso inferior de "+curso.getIdioma()+", en nivel "+((CursoNormal)curso).getNivel()+". **");
							}// Fin de mensajes
						}else System.out.println("**AVISO** No existe la matricula en el curso "+idCurso+" del alumno con DNI "+DNI+" para poder subirle de nivel. **");
					}else System.out.println("**AVISO** No existe el alumno con DNI "+DNI+".");
				}else{		// Junior
					Alumno alumno = checkAlumno(nombre,apellidos);					// Almacenamos el alumno
					if(alumno!=null){									// si existe
						Matricula matricula = checkMatricula(alumno, curso);
						if(matricula!=null){
							String idioma = curso.getIdioma();			// Almacenamos el idioma del curso de procedencia
							int nivel = ((CursoNormal)curso).getNivel();				// Almacenamos el nivel del curso de procedencia
							Iterator<Curso> it = cursosAcademia.iterator();
							boolean okMatriculado=false;	// Bandera para registrar que se ha realizado el cambio (true=cambio realizado)
							boolean encontradoCurso=false;	// Bandera para registrar que se ha encontrado el curso inf/sup (tre=curso inf/sup encontrado)
							boolean noMatriculado=false;	// Bandera para registrar que el alumno no esta matriculado (true=alumno no matriculado)
							while (it.hasNext() && (!okMatriculado || !noMatriculado)) { // Seguimos en el bucle si quedan m��s cursos por recorrer y el alumno sigue sin matricularse o no ha intentado matricularse y no se premite por la politica adoptada
								Curso cursoAux = it.next();
								if(x){						// Comprobamos si vamos a subir de curso
									if (cursoAux.getIdioma() == idioma && ((CursoNormal)cursoAux).getNivel() == nivel + 1){ // Comprobamos que existe el curso superior
										encontradoCurso=true;
										if(checkMatricula(alumno,cursoAux)==null){					// Comprobamos que no exite la matricula
											noMatriculado=true;
											if(nAlumnosMatriculados(cursoAux)<cursoAux.getMaxNalumnos()){	// Comprobamos que el curso no esta completo
												okMatriculado=true;
												matricula.setCurso(cursoAux);				// Cambiamos la matricula al idioma nuevo
												((Junior)alumno).getAdultoResponsable().setDeuda(((Junior)alumno).getAdultoResponsable().getDeuda()-curso.getPrecio());	// Restamos el precio del curso del que procede
												((Junior)alumno).getAdultoResponsable().setDeuda(((Junior)alumno).getAdultoResponsable().getDeuda()+cursoAux.getPrecio());// A��adimos el precio del curso de nivel superior
											}
										}
									}
								}else{						// Si bajamos de curso
									if (cursoAux.getIdioma() == idioma && ((CursoNormal)cursoAux).getNivel() == nivel -1){ // Comprobamos que existe el curso superior
										encontradoCurso=true;
										if(checkMatricula(alumno,cursoAux)==null){					// Comprobamos que no exite la matricula
											noMatriculado=true;
											if(nAlumnosMatriculados(cursoAux)<cursoAux.getMaxNalumnos()){	// Comprobamos que el curso no esta completo
												okMatriculado=true;
												matricula.setCurso(cursoAux);				// Cambiamos la matricula al idioma nuevo
												((Junior)alumno).getAdultoResponsable().setDeuda(((Junior)alumno).getAdultoResponsable().getDeuda()-curso.getPrecio());	// Restamos el precio del curso del que procede
												((Junior)alumno).getAdultoResponsable().setDeuda(((Junior)alumno).getAdultoResponsable().getDeuda()+cursoAux.getPrecio());// A��adimos el precio del curso de nivel superior
											}
										}
									}
								}
							}
							// Mensajes dependiendo del caso
							if(x) {		// Si subimos nivel
								if(encontradoCurso){		// y encuentra el curso
									if(noMatriculado){		// y no esta matriculado
										if(okMatriculado){	// y ha conseguido matricularse
											System.out.println("**OK** - Cambio del nivel "+((CursoNormal)curso).getNivel()+" al "+(((CursoNormal)curso).getNivel()+1)+" en el idioma de "+curso.getIdioma()+" al alumno "+nombre+" "+apellidos+" realizado correctamente.");
										}else System.out.println("**AVISO** No se ha podido realizar el cambio de nivel del alumno "+nombre+" "+apellidos+"\n\t en el curso de "+curso.getIdioma()+", en el nivel "+(((CursoNormal)curso).getNivel()+1)+" porque esta lleno.");
									}else System.out.println("**AVISO** No se ha podido realizar el cambio de nivel del alumno "+nombre+" "+apellidos+"\n\t porque ya esta matriculado en el curso de "+curso.getIdioma()+", en el nivel "+(((CursoNormal)curso).getNivel()+1)+". **");
								}else System.out.println("**AVISO** No se ha podido realizar el cambio de nivel del alumno "+nombre+" "+apellidos+"\n\t porque no existe el curso superior de "+curso.getIdioma()+", en nivel "+(((CursoNormal)curso).getNivel()+1)+". **");
							}else {		// Si bajamos nivel
								if(encontradoCurso){		// y encuentra el curso
									if(noMatriculado){		// y no esta matriculado
										if(okMatriculado){	// y ha conseguido matricularse
											System.out.println("**OK** - Cambio del nivel "+((CursoNormal)curso).getNivel()+" al "+(((CursoNormal)curso).getNivel()-1)+" en el idioma de "+curso.getIdioma()+" al alumno "+nombre+" "+apellidos+" realizado correctamente.");
										}else System.out.println("**AVISO** No se ha podido realizar el cambio de nivel del alumno "+nombre+" "+apellidos+"\n\t en el curso de "+curso.getIdioma()+", en el nivel "+(((CursoNormal)curso).getNivel()-1)+" porque esta lleno.");
									}else System.out.println("**AVISO** No se ha podido realizar el cambio de nivel del alumno "+nombre+" "+apellidos+"\n\t porque ya esta matriculado  en el curso de "+curso.getIdioma()+", en el nivel "+(((CursoNormal)curso).getNivel()-1)+". **");
								}else System.out.println("**AVISO** No se ha podido realizar el cambio de nivel del alumno "+nombre+" "+apellidos+"\n\t porque no existe el curso inferior de "+curso.getIdioma()+", en nivel "+((CursoNormal)curso).getNivel()+". **");
							}// Fin de mensajes
						}else System.out.println("**AVISO** No existe la matricula en el curso "+idCurso+" del alumno "+nombre+" "+apellidos+" para poder subirle de nivel. **");
					}else System.out.println("**AVISO** El curso "+idCurso+" es Junior y no se puede cambiar de nivel.");
				}
			}else System.out.println("**AVISO** No existe el curso con id "+idCurso+".");
		}else System.out.println("**AVISO** No existe el alumno "+nombre+" "+apellidos+".");
	
		
	}
	/**
	 * Imprime la lista de cursos de un alumno
	 * @param DNI	del alumno adulto
	 * @param nombre del alumno junior
	 * @param apellidos	del alumno junior
	 */
	public void imprimirListaCursosAlumno(String DNI,String nombre,String apellidos){
		if(DNI!=null){	// Adulto
			Alumno alumno =checkAlumno(DNI);	// Comprobamos que exista el alumno con DNI
			if ( alumno!= null) {
				boolean bandMatriculado=false; // Racogemos si no esta matriculado
				boolean alumnoSinMatriculas=true;		// Recogemos que no tiene ninguna matricula
				boolean cabeceraPuesta = false;		// Recogemos si se ha puesto la cabecera
					Iterator<Curso>it=cursosAcademia.iterator();	
					while(it.hasNext()){					// Recorremos los cursos
						Curso curso=it.next();					// Almacenamos el curso para mostrar sus atributos en caso de estar matriculado
						bandMatriculado=false;				// Establecemos en un principio como que no esta matriculado
						if(checkMatricula(alumno, curso)!=null){	// Comprobamos si exite la matricula
							bandMatriculado=true;			// Almacenamos que si esta matriculado		
							alumnoSinMatriculas=false;		// Almacenamos que el alumno tiene alguna matricula 
						}
						else bandMatriculado=false;			// Almacenamos que no esta matriculado
						if(!alumnoSinMatriculas && !cabeceraPuesta ){	// Si el alumno tiene alguna matricula y no esta ya puesta la cabecera
							cabeceraPuesta=true;		// Almacenamos que ponemos la cabecera
							System.out.println("\t________________________________________________________");
							System.out.println("\t  <<<     Cursos matriculados del Alumno: " + DNI + " >>>");
							System.out.println("\t  ��������������������������������������������������������������������������������������������������������");
						}
						if(bandMatriculado){	// Si el alumno esta matriculado del curso lo imprimimos
							System.out.println("\t    Id Curso: " + curso.getIdCurso() + "\tIdioma: " + curso.getIdioma()
							+ "\tNivel: " + ((CursoNormal)curso).getNivel());		// Imprimimos el curso 
							System.out.println("\t  ��������������������������������������������������������������������������������������������������������");
						}
					}
					if(alumnoSinMatriculas){ 		// Si el alumno no esta matriculado en ningun curso
						System.out.println("\t**AVISO** No esta matriculado en ningun curso **");
					}
			}else System.out.println("**AVISO** No exite el alumno con DNI "+DNI+".");
		}else{			// Junior
			Alumno alumno =checkAlumno(nombre,apellidos);	// Comprobamos que exista el alumno con DNI
			if ( alumno!= null) {
				boolean bandMatriculado=false; // Racogemos si no esta matriculado
				boolean alumnoSinMatriculas=true;		// Recogemos que no tiene ninguna matricula
				boolean cabeceraPuesta = false;		// Recogemos si se ha puesto la cabecera
					Iterator<Curso>it=cursosAcademia.iterator();	
					while(it.hasNext()){					// Recorremos los cursos
						Curso curso=it.next();					// Almacenamos el curso para mostrar sus atributos en caso de estar matriculado
						bandMatriculado=false;				// Establecemos en un principio como que no esta matriculado
						if(checkMatricula(alumno, curso)!=null){	// Comprobamos si exite la matricula
							bandMatriculado=true;			// Almacenamos que si esta matriculado		
							alumnoSinMatriculas=false;		// Almacenamos que el alumno tiene alguna matricula 
						}
						else bandMatriculado=false;			// Almacenamos que no esta matriculado
						if(!alumnoSinMatriculas && !cabeceraPuesta ){	// Si el alumno tiene alguna matricula y no esta ya puesta la cabecera
							cabeceraPuesta=true;		// Almacenamos que ponemos la cabecera
							System.out.println("\t________________________________________________________");
							System.out.println("\t  <<<     Cursos matriculados del Alumno: " + nombre+" "+apellidos + " >>>");
							System.out.println("\t  ��������������������������������������������������������������������������������������������������������");
						}
						if(bandMatriculado){	// Si el alumno esta matriculado en el curso lo imprimimos
							if(curso instanceof CursoNormal)
							System.out.println("\t    Id Curso: " + curso.getIdCurso() + "\tIdioma: " + curso.getIdioma()
							+ "\tNivel: " + ((CursoNormal)curso).getNivel());		// Imprimimos el curso 
							else
							System.out.println("\t    Id Curso: " + curso.getIdCurso() + "\tIdioma: " + curso.getIdioma());		// Imprimimos el curso 
							System.out.println("\t  ��������������������������������������������������������������������������������������������������������");	
						}
					}
					if(alumnoSinMatriculas){ 		// Si el alumno no esta matriculado en ningun curso
						System.out.println("\t**AVISO** No esta matriculado en ningun curso **");
					}
			}else System.out.println("**AVISO** No exite el alumno "+nombre+" "+apellidos+".");
		
		}
	}
		
	//Cursos
	/**
	 * Crea un curso.
	 * @param idCurso del nuevo curso
	 * @param idioma del nuevo curso
	 * @param nivel del nuevo curso
	 * @param dia de inicio del nuevo curso
	 * @param mes de inicio del nuevo curso
	 * @param ano de inicio del nuevo curso
	 * @param diaf de fin del nuevo curso
	 * @param mesf de fin del nuevo curso
	 * @param anof de fin del nuevo curso
	 * @param hora en que e imparte el nuevo curso
	 * @param min en que e imparte el nuevo curso
	 * @param maxNalumnos del nuevo curso
	 * @param precio del nuevo curso
	 */
	public void crearCurso(String idCurso, String idioma, String nivel, int dia, int mes, int ano, int diaf, int mesf,
			int anof, int hora, int min, int maxNalumnos, double precio, int edadMinima,int edadMaxima){
		if(checkCurso(idCurso)==null){		// Comprobamos que no exista un curso con idCurso
				if(hora>=8 && hora<=21){	// Comprobamos que la hora es entre las 8 y las 21
					GregorianCalendar fechaIni = new GregorianCalendar(ano, mes-1, dia);
					java.sql.Date milfechaIni = new java.sql.Date(fechaIni.getTimeInMillis());	// Almacenamos la conversion a milisegundos de la fecha de inicio
					GregorianCalendar fechaFin = new GregorianCalendar(anof, mesf-1, diaf);
					java.sql.Date milfechaFin = new java.sql.Date(fechaFin.getTimeInMillis());	// Almacenamos la conversion a milisegundos de la fecha de fin
					long diferencia = (milfechaFin.getTime() - milfechaIni.getTime()); // Almacenamos la diferencia de fin - inicio
					if(diferencia > 0){		// Comprobamos que la diferencia sea mayor que 0, asi nos aseguramos que la fechaIni < fechaFin
						if(maxNalumnos>0){	// Comprobamos que el numero maximo de alumnos sea mayor que 0
							if(precio>=0){	// Comprobamos que el precio sea mayor o igual a 0
							
								if(nivel!=null){	// NORMAL porque introducidos nivel distinto de null creamos un curso normal
									int niv = Integer.parseInt(nivel);
									if(niv>=0){		// Comprobamos que el nivel sea igual o mayor que 0
										GregorianCalendar horario =new GregorianCalendar(0,0,0,hora,min);
										Curso curso= new CursoNormal(idCurso,  idioma,  niv,  fechaIni, fechaFin, horario, maxNalumnos,precio);
										cursosAcademia.add(curso);
										System.out.println("**OK** - Curso "+curso.getIdCurso()+" creado correctamente.");	
									}else System.out.println("**AVISO** El nivel ha de ser mayor que 0. **");
								}else{						// JUNIOR
									if(edadMinima>0){	// Comprobamos que la edad minima es mayor que 0
										if(edadMaxima<17){	// Comprobamos que la edad maxima es menor que 17.
											GregorianCalendar horario =new GregorianCalendar(0,0,0,hora,min);
											Curso curso= new CursoJunior(idCurso, idioma, fechaIni, fechaFin, horario, maxNalumnos, precio, edadMinima, edadMaxima);
											cursosAcademia.add(curso);
											System.out.println("**OK** - Curso "+curso.getIdCurso()+" creado correctamente.");
										}else System.out.println("**AVISO** La edad maxima debe ser menor o igual que 18. **");
									}else System.out.println("**AVISO** La edad minima debe ser mayor que 0. **");
								}
							}else System.out.println("**AVISO** El precio ha de ser igual o mayor que 0. **");
						}else System.out.println("**AVISO** El numero maximo de alumnos debe ser mayor que 0. **");
					}else System.out.println("**AVISO** La fecha de inicio del curso debe ser anterior a la fecha de fin. **");
				}else System.out.println("**AVISO** La hora debe ser entre las 8 y las 21. **");
			}else System.out.println("**AVISO** Ya existe un curso con identificador "+idCurso+". **");
	}
	/**
	 * Imprime la lista de cursos.
	 */
	public void imprimirCursos(){
		if(!cursosAcademia.isEmpty()){
		System.out.println("____________________________________________________________________");
		System.out.println("_____________________________ Cursos _______________________________\n");
		Curso curso;
		Iterator<Curso> it = cursosAcademia.iterator();
		while(it.hasNext()){
			curso=it.next();
			String horario,mes,mesf;
			// Si minutos < 10 
			if (curso.getHorario().get(Calendar.MINUTE) < 10) 
				horario =  Integer.toString(curso.getHorario().get(Calendar.HOUR))+":0" + Integer.toString(curso.getHorario().get(Calendar.MINUTE));
			else
				horario = Integer.toString(curso.getHorario().get(Calendar.HOUR))+":"+Integer.toString(curso.getHorario().get(Calendar.MINUTE));
			// Si el mes es 12 en fecha inicio
			if (curso.getFechaIni().get(Calendar.MONTH) ==11) mes =  "12";
			else mes = Integer.toString(((curso.getFechaIni().get(Calendar.MONTH))+1));
			// Si el mes es 12 en fecha fin
			if (curso.getFechaFin().get(Calendar.MONTH) ==11) mesf =  "12";
			else mesf = Integer.toString(((curso.getFechaFin().get(Calendar.MONTH))+1));
			if(curso instanceof CursoNormal){
				System.out.println("- Id curso: "+curso.getIdCurso()+"\tIdioma: "+curso.getIdioma()+"\t\tNivel:"+((CursoNormal)curso).getNivel()+
						"\n\tFecha inicio: "+ curso.getFechaIni().get(Calendar.DATE) + "/"+ mes +
						"/" + curso.getFechaIni().get(Calendar.YEAR)+"\t Fecha de finalizacion: "+curso.getFechaFin().get(Calendar.DATE)+"/"+
						mesf+"/"+curso.getFechaFin().get(Calendar.YEAR)+"\n\tMax N�� de Alumnos: "+
						curso.getMaxNalumnos()+"\t Horario: "+horario+"\tPrecio: "+curso.getPrecio());
				System.out.println("  ----------------------------------------------------------------");
			}else{
				System.out.println("- Id curso: "+curso.getIdCurso()+"\tIdioma: "+curso.getIdioma()+"\n\tFecha inicio: "+
			curso.getFechaIni().get(Calendar.DATE) + "/"+ mes +"/" + curso.getFechaIni().get(Calendar.YEAR)+
			"\t Fecha de finalizacion: "+curso.getFechaFin().get(Calendar.DATE)+"/"+mesf+"/"+
			curso.getFechaFin().get(Calendar.YEAR)+"\n\tMax N�� de Alumnos: "+curso.getMaxNalumnos()+"\t Horario: "+
			horario+"\tPrecio: "+curso.getPrecio()+"\n\tEdad Minima: "+((CursoJunior)curso).getEdadMinima()+
			"\t\t Edad Maxima: "+((CursoJunior)curso).getEdadMaxima());
				System.out.println("  ----------------------------------------------------------------");
			}
			
			
		}System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''");
		}else System.out.println("**OK** - No existen cursos.");
		
	}
	/**
	 * Comprueba el numero de matriculas en un curso
	 * @param idCurso	del curso 
	 * @return nAlumnosMatriculados Numero de alumno matriculados
	 */
	public int nAlumnosMatriculados(Curso idCurso){
		int nAlumnosMatriculados=0;						// Observamos el numero de matriculas que hay en el curso
		Iterator<Matricula>itm=matriculasAcademia.iterator();
		while(itm.hasNext()){
			Matricula matricula=itm.next();
			if(matricula.getCurso()==idCurso){		// 
				nAlumnosMatriculados=+1;
			}
		}
		return nAlumnosMatriculados;
	}
	/**
	 * Lista de alumnos de un curso
	 * @param idCurso  del curso
	 */
	public void imprimirListaAlumnosCurso(String idCurso){
		Curso curso =checkCurso(idCurso);		// Comprobamos que exista el curso con id
		if ( curso!= null) {
			boolean bandAlumnoMatriculado=false; 	// Racogemos si no hay matriculas
			boolean cursoConMatriculas=false;		// Recogemos que el curso tiene alguna matricula
			boolean cabeceraPuesta = false;			// Recogemos si se ha puesto la cabecera
				Iterator<Alumno>it=alumnosAcademia.iterator();	
				while(it.hasNext()){					// Recorremos los alumnos
					Alumno alumno=it.next();					// Almacenamos el curso para mostrar sus atributos en caso de estar matriculado
					bandAlumnoMatriculado=false;		// Establecemos en un principio como que el alumno no esta matriculado
					Matricula matricula =checkMatricula(alumno, curso);
					if(matricula!=null){			// Comprobamos si exite la matricula
						bandAlumnoMatriculado=true;		// Almacenamos que si tiene matricula del alumno		
						cursoConMatriculas=true;		// Almacenamos que el curso tiene alguna matricula 
					}
					else bandAlumnoMatriculado=false;	// Almacenamos que no tiene matricula del alumno
					if(cursoConMatriculas && !cabeceraPuesta ){	// Si el curso tiene alguna matricula y no esta ya puesta la cabecera
						cabeceraPuesta=true;			// Almacenamos que ponemos la cabecera
						System.out.println("\t________________________________________________________");
						if(curso instanceof CursoNormal)
						System.out.println("\t  <<< Alumnos matriculados del Curso Normal: " + idCurso + " >>>\n"
								+ "\t\t  Idioma: "+curso.getIdioma()+"\t\tNivel:"+((CursoNormal)curso).getNivel());
						else System.out.println("\t  <<< Alumnos matriculados del Curso Junior: " + idCurso + " >>>\n"
								+ "\t\tIdioma: "+curso.getIdioma());
						
						System.out.println("\t  ��������������������������������������������������������������������������������������������������������");
					}
					if(bandAlumnoMatriculado){			// Si el alumno esta matriculado del curso lo imprimimos
						if(alumno instanceof Adulto)
							System.out.println("\t  - DNI: " + ((Adulto)alumno).getDNI()+"\tEstado de la matricula: "+matricula.getEstado() + "\n\t\tNombre: " +alumno.getNombre()
							+ "\tApellidos: " + alumno.getApellidos());		// Imprimimos el curso 
						else System.out.println("\t  - Nombre: " +alumno.getNombre()+ "\tApellidos: " + alumno.getApellidos()+"\tEstado de la matricula: "+matricula.getEstado());		// Imprimimos el curso 
						System.out.println("\t  ----------------------------------------------------");
					}
				}
				if(!cursoConMatriculas){ 				// Si el curso no tiene ningun alumno matriculado
					System.out.println("\t**AVISO** El curso "+idCurso+" no tiene ningun alumno matriculado **");
				}
		}else System.out.println("**AVISO** No exite el curso con id "+idCurso+".");
	}
	/**
	 * Lista las matriculas pendientes de un curso.
	 * @param idCurso	del curso
	 */
	public void imprimirListaMatriculasPendientesCurso(String idCurso){
		Curso curso =checkCurso(idCurso);		// Comprobamos que exista el curso con id
		if ( curso!= null) {
			boolean bandAlumnoMatriculado=false; 	// Racogemos si no hay matriculas
			boolean cursoConMatriculas=false;		// Recogemos que el curso tiene alguna matricula
			boolean cabeceraPuesta = false;			// Recogemos si se ha puesto la cabecera
				Iterator<Alumno>it=alumnosAcademia.iterator();	
				while(it.hasNext()){					// Recorremos los alumnos
					Alumno alumno=it.next();					// Almacenamos el curso para mostrar sus atributos en caso de estar matriculado
					bandAlumnoMatriculado=false;		// Establecemos en un principio como que el alumno no esta matriculado
					Matricula matricula =checkMatricula(alumno, curso);
					if(matricula!=null){			// Comprobamos si exite la matricula
						bandAlumnoMatriculado=true;		// Almacenamos que si tiene matricula del alumno		
						cursoConMatriculas=true;		// Almacenamos que el curso tiene alguna matricula 
					}
					else bandAlumnoMatriculado=false;	// Almacenamos que no tiene matricula del alumno
					if(cursoConMatriculas && !cabeceraPuesta ){	// Si el curso tiene alguna matricula y no esta ya puesta la cabecera
						cabeceraPuesta=true;			// Almacenamos que ponemos la cabecera
						System.out.println("\t________________________________________________________");
						if(curso instanceof CursoNormal)
						System.out.println("\t  <<< Matriculas pendientes del Curso Normal: " + idCurso + " >>>\n"
								+ "\t\t  Idioma: "+curso.getIdioma()+"\t\tNivel:"+((CursoNormal)curso).getNivel());
						else System.out.println("\t  <<< Matriculas pendientes del Curso Junior: " + idCurso + " >>>\n"
								+ "\t\t\t\tIdioma: "+curso.getIdioma());
						
						System.out.println("\t  ��������������������������������������������������������������������������������������������������������");
					}
					if(bandAlumnoMatriculado && matricula.getEstado()==false){			// Si el alumno esta matriculado del curso y no ha pagado la matricula lo imprimimos
						if(curso instanceof CursoNormal){
						if(alumno instanceof Adulto)
							System.out.println("\t  - IdMatricula "+((MatriculaNormal)matricula).getIdMatricula()+"\n\t\tDNI: " + ((Adulto)alumno).getDNI()+"\tEstado de la matricula: "+((MatriculaNormal)matricula).getEstado() + "\n\t\tNombre: " +alumno.getNombre()
							+ "\tApellidos: " + alumno.getApellidos());		// Imprimimos el alumno y matricula
						else System.out.println("\t  - IdMatricula "+((MatriculaNormal)matricula).getIdMatricula()+"\tEstado de la matricula: "+((MatriculaNormal)matricula).getEstado()+"\n\t\tNombre: " +alumno.getNombre()+ "\tApellidos: " + alumno.getApellidos());		// Imprimimos el curso 
						}else System.out.println("\t  - IdMatricula "+((MatriculaJunior)matricula).getIdMatricula()+"\tEstado de la matricula: "+((MatriculaJunior)matricula).getEstado()+"\n\t\tNombre: " +alumno.getNombre()+ "\tApellidos: " + alumno.getApellidos()+"\tTelefono: "+ ((MatriculaJunior)matricula).getTelefono());		// Imprimimos el curso
						System.out.println("\t  ----------------------------------------------------");
					}
				}
				if(!cursoConMatriculas){ 				// Si el curso no tiene ningun alumno matriculado
					System.out.println("\t**AVISO** El curso "+idCurso+" no tiene ningun alumno matriculado **");
				}
		}else System.out.println("**AVISO** No exite el curso con id "+idCurso+".");
	}
	
	// Matriculas
	/**
	 * Cear una matricula
	 * @param idCurso
	 * @param DNI
	 * @param nombre
	 * @param apellidos
	 * @param telefono
	 */
	public void crearMatricula(String idCurso, String DNI, String nombre, String apellidos, int telefono){
		Curso curso = checkCurso(idCurso);						// Almacenamos el curso ,
		if(curso!=null){										// si existe
			if(nAlumnosMatriculados(curso)<curso.getMaxNalumnos()){	// Comprobamos que el curso no esta completo
				if(DNI!= null){	//Adultos
					Alumno alumno=checkAlumno(DNI);
			    	if(alumno!=null){						// si existe y
				    	if(curso instanceof CursoNormal){
				    		if(checkMatricula(alumno, curso)==null){			// Comprobamos que no exista una matricula con id (el alumno no esta matriculado)
								Matricula matricula= new MatriculaNormal(idMatricula,alumno,curso);	// Creamos la matricula
								this.idMatricula=this.idMatricula+1;	// Incrementamos el identificador de la matricula
								matriculasAcademia.add(matricula);		// Almacenamos la matricula en la academia
								alumno.setDeuda(alumno.getDeuda()+curso.getPrecio());		// A��adimos el precio a la deuda del alumno si es adulto
								System.out.println("**OK** - Matricula con id "+matricula.getIdMatricula()+" creada correctamente.");
							}else System.out.println("**AVISO** Ya existe una matricula del alumno con DNI "+DNI+" en el curso "+idCurso+".");
						}else System.out.println("**AVISO** Este curso es solo para alumnos junior.");
			    	}else System.out.println("**AVISO** No existe el alumno con DNI "+DNI+".");
			    
				}else{			//Junior
					Alumno alumno=checkAlumno(nombre,apellidos);	// Almacenamos el alumno
			    	if(alumno!=null){							// si existe y
				 		if(curso instanceof CursoNormal){		// Curso Normal
						  		if(checkMatricula(alumno, curso)==null){			// Comprobamos que no exista una matricula con id (el alumno no esta matriculado)
									Matricula matricula= new MatriculaNormal(idMatricula,alumno,curso);	// Creamos la matricula
									this.idMatricula=this.idMatricula+1;	// Incrementamos el identificador de la matricula
									matriculasAcademia.add(matricula);		// Almacenamos la matricula en la academia
									((Junior)alumno).getAdultoResponsable().setDeuda(((Junior)alumno).getAdultoResponsable().getDeuda()+curso.getPrecio());		// A��adimos el precio a la deuda del alumno adulto responsable
									System.out.println("**OK** - Matricula con id "+matricula.getIdMatricula()+" creada correctamente.");
								}else System.out.println("**AVISO** Ya existe una matricula del alumno "+nombre+" "+apellidos+" en el curso "+idCurso+".");
							//}else System.out.println("**AVISO** Este curso es solo para alumnos junior y precisa de telefono de contacto.");
					    
						}else{			// Curso Junior
							java.sql.Date milfechaNacimiento = new java.sql.Date(((Junior)alumno).getFechaNacimiento().getTimeInMillis());	// Almacenamos la conversion en milisegundos de la fecha de nacimiento del alumno
							java.sql.Date milfechaInicioCurso = new java.sql.Date(curso.getFechaIni().getTimeInMillis());	// Almacenamos la conversion en milisegundos de la fecha de inicio de curso
							long diferencia = this.getDiferenciaFechas(milfechaInicioCurso,milfechaNacimiento, 0);
							if (diferencia < 0)diferencia = this.getDiferenciaFechas(milfechaNacimiento,milfechaInicioCurso, 0);
							if(diferencia >= ((CursoJunior)curso).getEdadMinima() && diferencia <=((CursoJunior)curso).getEdadMaxima()){ // Y esta en el rango de edad del curso
								if(checkMatricula(alumno, curso)==null){			// Comprobamos que no exista una matricula con id (el alumno no esta matriculado)
									//TODO NECESARIO TELEFONO
									Matricula matricula= new MatriculaJunior(idMatricula,alumno,curso,telefono);	// Creamos la matricula
									this.idMatricula=this.idMatricula+1;	// Incrementamos el identificador de la matricula
									matriculasAcademia.add(matricula);		// Almacenamos la matricula en la academia
									((Junior)alumno).getAdultoResponsable().setDeuda(((Junior)alumno).getAdultoResponsable().getDeuda()+curso.getPrecio());		// A��adimos el precio a la deuda del alumno adulto responsable
									System.out.println("**OK** - Matricula con id "+matricula.getIdMatricula()+" creada correctamente.");
								
								}else System.out.println("**AVISO** Ya existe una matricula del alumno "+alumno+" en el curso "+curso+".");
							}else System.out.println("**AVISO** El curso es para edades entre "+((CursoJunior)curso).getEdadMinima()+" y "+((CursoJunior)curso).getEdadMaxima()+".(Tendra "+diferencia+" a��os).");
					
						}
			    	}else System.out.println("**AVISO** No existe el alumno "+nombre+" "+apellidos+".");
			   		
				}
		
			}else System.out.println("**AVISO** El curso esta lleno, el alumno no ha podido matricularse.");
		}else System.out.println("**AVISO** No existe el curso con id "+idCurso+".");	
	}
	/**
	 * Imprime la lista de matriculas.
	 */
	public void imprimirMatriculas(){
		if(!matriculasAcademia.isEmpty()){
			System.out.println("____________________________________________________________________");
			System.out.println("____________________________ Matriculas ____________________________\n");
			Iterator<Matricula> it = matriculasAcademia.iterator();
			while(it.hasNext()){
				Matricula matricula=it.next();
				if(matricula.getCurso() instanceof CursoNormal)
					if(matricula.getAlumno() instanceof Adulto)
						System.out.println("- Id: "+matricula.getIdMatricula()+"\t\tEstado: "+matricula.getEstado()+"\n\tAlumno: "+((Adulto)matricula.getAlumno()).getDNI()+"\t\tCurso: "+matricula.getCurso().getIdCurso());
					else
						System.out.println("- Id: "+matricula.getIdMatricula()+"\t\tEstado: "+matricula.getEstado()+"\n\tAlumno: "+((Junior)matricula.getAlumno()).getNombre()+" "+((Junior)matricula.getAlumno()).getApellidos()+"\t\t\tCurso: "+matricula.getCurso().getIdCurso());
				else
					System.out.println("- Id: "+matricula.getIdMatricula()+"\t\tEstado: "+matricula.getEstado()+"\t\tAlumno Responsable: "+((Junior)matricula.getAlumno()).getAdultoResponsable().getDNI()+"\n\tAlumno: "+matricula.getAlumno().getNombre()+" "+matricula.getAlumno().getApellidos()+"\tCurso: "+matricula.getCurso().getIdCurso()+"\tTlfn: "+((MatriculaJunior)matricula).getTelefono());
				System.out.println("  ----------------------------------------------------------------");
			}System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''");
		}else System.out.println("**OK** - No existen matriculas.");
		
	}
	/**
	 * Imprime la lista de matriculas pendientes de pago.
	 */
	public void imprimirMatriculasPendientes(){
		if(!matriculasAcademia.isEmpty()){
			System.out.println("____________________________________________________________________");
			System.out.println("_______________________ Matriculas Pendientes ______________________\n");
			Iterator<Matricula> itm = matriculasAcademia.iterator();
			boolean bandSinMatriculasPendientes = false;	// Recogemos si no hay matriculas pendientes 
			while(itm.hasNext()){
				Matricula matricula=itm.next();
				if(matricula.getEstado()==false){
					bandSinMatriculasPendientes=true;
					System.out.println("- Id: "+matricula.getIdMatricula());
					System.out.println("  ----------------------------------------------------------------");
				}
			}
			if(bandSinMatriculasPendientes==false){
				System.out.println("**OK** - No existen matriculas pendientes.");
			}
			System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''");
		}else System.out.println("**OK** - No existen matriculas.");
		
	}
	/**
	 * Realiza el pago de una matricula.
	 * @param idMatricula 
	 */
	public void pagarMatricula(int idMatricula){	
		Matricula matricula = checkMatricula(idMatricula);		// Almacenamos la matricula
		if(matricula!=null){									// si existe y 
			if(matricula.getEstado()==false){					// no esta pagada,
				if(matricula.getCurso() instanceof CursoNormal && matricula.getAlumno() instanceof Adulto){	// Comprobamos si es un curso Normal y un alumno adulto
					matricula.setEstado(true);						// cambiamos el estado de la matricula ha pagada y
					matricula.getAlumno().setDeuda(matricula.getAlumno().getDeuda()-matricula.getCurso().getPrecio());// restamos a la deuda del alumno el precio del curso
					System.out.println("**OK** - Matricula con id "+matricula.getCurso().getIdCurso()+" pagada correctamente.");
				}else{					// Para alumnos junior, tanto en cursos Normales como Junior
					matricula.setEstado(true);						// cambiamos el estado de la matricula ha pagada y
					((Junior)matricula.getAlumno()).getAdultoResponsable().setDeuda(((Junior)matricula.getAlumno()).getAdultoResponsable().getDeuda()-matricula.getCurso().getPrecio());// restamos a la deuda del alumno responsable el precio del curso
					System.out.println("**OK** - Matricula con id "+matricula.getIdMatricula()+" pagada correctamente.");
				}
			}else System.out.println("**AVISO** - La matricula ya esta pagada.");
		}else System.out.println("**AVISO** No existe la matricula con id "+idMatricula+".");
	}
	
	// Rutinas
		// Alumno
	/**
	 * Devuele el alumno adulto si existe mediante su DNI, si no existe devuelve null.
	 * @param DNI	del alumno a comprobar
	 * @return a/null	(alumno si existe/null si no existe)
	 */
	public Alumno checkAlumno(String DNI){
		Adulto alumno=null;
		Iterator<Alumno> it = alumnosAcademia.iterator();
		while (it.hasNext()) {
			Alumno alumnoAux = it.next();
			if (alumnoAux instanceof Adulto && ((Adulto) alumnoAux).getDNI().equals(DNI)) {
				alumno=(Adulto)alumnoAux;
			}
		}
		if (alumno == null) {
			return null;
		} else
			return alumno;	
	}
	/**
	 * Devuele el alumno junior si existe mediante su nombre y apellidos, si no existe devuelve null.
	 * @param nombre
	 * @param apellidos
	 * @return
	 */
	public Alumno checkAlumno(String nombre, String apellidos){
		Junior alumno=null;	
		Iterator<Alumno> it = alumnosAcademia.iterator();
		while (it.hasNext()) {
			Alumno alumnoAux = it.next();
			if (alumnoAux instanceof Junior && ((Junior)alumnoAux).getNombre()==nombre && ((Junior)alumnoAux)
					.getApellidos()==apellidos) {
				alumno=(Junior)alumnoAux;
			}
		}
		if (alumno == null) {
			return null;
		} else
			return alumno;	
	}
		// Curso
	/**
	 * Devuele el curso si existe mediante su identificador, si no existe devuelve null.
	 * @param idCurso	del curso ha comprobar
	 * @return c/null	(curso si existe/null si no existe)
	 */
	public Curso checkCurso(String idCurso){
		Curso c=null;	// Si el curso existe aqu�� ser��a almacenado
		Iterator<Curso> it = cursosAcademia.iterator();
		while (it.hasNext()) {
			Curso caux = it.next();
			if (caux.getIdCurso().equals(idCurso)) {
				c=caux;
			}
		}
		if (c == null) {
			return null;
		} else
			return c;	
	}
		// Matricula
	/**
	 * Devuele la matricula si existe en la academia mediante un curso y un alumno, si no existe devuelve null.
	 * @param idMatricula de la matricula ha comprobar
	 * @return (m/null) (matricula si existe/null si no existe)
	 */
	public Matricula checkMatricula(Alumno alumno, Curso curso){
		Matricula matricula=null;	// Si la matricula existe, aqu�� ser��a almacenado
		Iterator<Matricula> it = matriculasAcademia.iterator();
		while (it.hasNext()) {
			Matricula maux = it.next();
			if (maux.getAlumno()==alumno && maux.getCurso()==curso) {
				matricula=maux;
			}
		}
		if (matricula == null) {
			return null;
		} else
			return matricula;	
	}
	/**
	 * Devuele la matricula si existe en la academia mediante su identificador, si no existe devuelve null.
	 * @param idMatricula de la matricula ha comprobar
	 * @return (m/null) (matricula si existe/null si no existe)
	 */
	public Matricula checkMatricula(int idMatricula){
		Matricula matricula=null;	// Si la matricula existe, aqu�� ser��a almacenado
		Iterator<Matricula> it = matriculasAcademia.iterator();
		while (it.hasNext()) {
			Matricula maux = it.next();
			if (maux.getIdMatricula()==idMatricula){
				matricula=maux;
			}
		}
		if (matricula == null) {
			return null;
		} else
			return matricula;	
	}
		// Fecha
	/**
	 * Calcula la diferencia entre dos fechas. Devuelve el resultado en d��as, meses o a��os seg��n sea el valor del par��metro 'tipo'
	 * @param fechaInicio Fecha inicial
	 * @param fechaFin Fecha final
	 * @param tipo 0=TotalA��os; 1=TotalMeses; 2=TotalD��as; 3=MesesDelAnio; 4=DiasDelMes
	 * @return numero de d��as, meses o a��os de diferencia
	 */
	private long getDiferenciaFechas(Date fechaInicio, Date fechaFin, int tipo) {
		// Fecha inicio
		Calendar calendarInicio = Calendar.getInstance();
		calendarInicio.setTime(fechaInicio);
		int diaInicio = calendarInicio.get(Calendar.DAY_OF_MONTH);
		int mesInicio = calendarInicio.get(Calendar.MONTH) + 1; // 0 Enero, 11 Diciembre
		int anioInicio = calendarInicio.get(Calendar.YEAR);

		// Fecha fin
		Calendar calendarFin = Calendar.getInstance();
		calendarFin.setTime(fechaFin);
		int diaFin = calendarFin.get(Calendar.DAY_OF_MONTH);
		int mesFin = calendarFin.get(Calendar.MONTH) + 1; // 0 Enero, 11 Diciembre
		int anioFin = calendarFin.get(Calendar.YEAR);
		int anios = 0;
		int mesesPorAnio = 0;
		int diasPorMes = 0;
		int diasTipoMes = 0;
		//
		// Calculo de d��as del mes
		//
		if (mesInicio == 2) {
			// Febrero
			if ((anioFin % 4 == 0) && ((anioFin % 100 != 0) || (anioFin % 400 == 0))) {
				// Bisiesto
				diasTipoMes = 29;
			} else {
				// No bisiesto
				diasTipoMes = 28;
			}
		} else if (mesInicio <= 7) {
			// De Enero a Julio los meses pares tienen 30 y los impares 31
			if (mesInicio % 2 == 0) {
				diasTipoMes = 30;
			} else {
				diasTipoMes = 31;
			}
		} else if (mesInicio > 7) {
			// De Julio a Diciembre los meses pares tienen 31 y los impares 30
			if (mesInicio % 2 == 0) {
				diasTipoMes = 31;
			} else {
				diasTipoMes = 30;
			}
		}
		//
		// Calculo de diferencia de a��o, mes y dia
		//
		if ((anioInicio > anioFin) || (anioInicio == anioFin && mesInicio > mesFin)
				|| (anioInicio == anioFin && mesInicio == mesFin && diaInicio > diaFin)) {
			// La fecha de inicio es posterior a la fecha fin
			// System.out.println("La fecha de inicio ha de ser anterior a la fecha fin");
			return -1;
		} else {
			if (mesInicio <= mesFin) {
				anios = anioFin - anioInicio;
				if (diaInicio <= diaFin) {
					mesesPorAnio = mesFin - mesInicio;
					diasPorMes = diaFin - diaInicio;
				} else {
					if (mesFin == mesInicio) {
						anios = anios - 1;
					}
					mesesPorAnio = (mesFin - mesInicio - 1 + 12) % 12;
					diasPorMes = diasTipoMes - (diaInicio - diaFin);
				}
			} else {
				anios = anioFin - anioInicio - 1;
				//System.out.println(anios);
				if (diaInicio > diaFin) {
					mesesPorAnio = mesFin - mesInicio - 1 + 12;
					diasPorMes = diasTipoMes - (diaInicio - diaFin);
				} else {
					mesesPorAnio = mesFin - mesInicio + 12;
					diasPorMes = diaFin - diaInicio;
				}
			}
		}
		//System.out.println("Han transcurrido " + anios + " A��os, " + mesesPorAnio + " Meses y " + diasPorMes + " D��as.");		
		//
		// Totales
		//
		long returnValue = -1;
		switch (tipo) {
			case 0:
				// Total A��os
				returnValue = anios;
				// System.out.println("Total a��os: " + returnValue + " A��os.");
				break;
	 
			case 1:
				// Total Meses
				returnValue = anios * 12 + mesesPorAnio;
				// System.out.println("Total meses: " + returnValue + " Meses.");
				break;
	 
			case 2:
				// Total Dias (se calcula a partir de los milisegundos por d��a)
				long millsecsPerDay = 86400000; // Milisegundos al d��a
				returnValue = (fechaFin.getTime() - fechaInicio.getTime()) / millsecsPerDay;
				// System.out.println("Total d��as: " + returnValue + " D��as.");
				break;
	 
			case 3:
				// Meses del a��o
				returnValue = mesesPorAnio;
				// System.out.println("Meses del a��o: " + returnValue);
				break;
	 
			case 4:
				// Dias del mes
				returnValue = diasPorMes;
				// System.out.println("Dias del mes: " + returnValue);
				break;
	 
			default:
				break;
		}
	 
		return returnValue;
	}

	//************************* Inicio de la aplicacion principal ********************************
	/**
	 * Inicio de metodo principal
	 * @param arg
	 * @throws InterruptedException 
	 */
	public static void main(String[] arg) throws InterruptedException{
	// Creacion de academias
		Academia AcademiaZorrilla=new Academia();
		
	// Creacion de alumnos
		//(String DNI,String nombre,String apellidos, int dia, int mes ,int ano, String DNIResponsable))
		AcademiaZorrilla.crearAlumno("11111111a", "Adulto", "1",0,0,0,null);	// Adultos 	//OK
		AcademiaZorrilla.crearAlumno("22222222b", "Adulto", "2",0,0,0,null);	// Adultos 	//OK
		AcademiaZorrilla.crearAlumno("33333333c", "Adulto", "3",0,0,0,null);	// Adultos 	//OK
		AcademiaZorrilla.crearAlumno(null, "Junior", "1",1,1,2011,"11111111a");	// Junior	//OK
		AcademiaZorrilla.crearAlumno(null, "Junior", "2",1,1,2000,"11111111a");	// Junior	//OK
		// Errores
		AcademiaZorrilla.crearAlumno("11111111a", "Adulto10", "1",0,0,0,null);	//Error mismo DNI
		AcademiaZorrilla.crearAlumno(null, "Junior", "1",1,1,2011,"00000000a");	//Error no existe el adulto asignado
		AcademiaZorrilla.crearAlumno(null, "Junior", "1",1,1,2017,"11111111a");	//Error edad minima
		AcademiaZorrilla.crearAlumno(null, "Junior", "1",1,1,1998,"11111111a");	//Error edad maxima
		// Comprobacion
		AcademiaZorrilla.imprimirAlumnos();	// Vemos que se crean los alumnos
		
	// Creacion de cursos
		// (String idCurso, String idioma, int nivel, int dia, int mes, int ano, int diaf, int mesf,
		//  int anof, int hora, int min, int maxNalumnos, double precio, int edadMinima,int edadMaxima)
		// Normales
		AcademiaZorrilla.crearCurso("I.0a", "Ingles", "0", 1, 1, 2016, 1, 1, 2017, 8, 0, 2, 10,0,0);
		AcademiaZorrilla.crearCurso("I.1a", "Ingles", "1", 1, 1, 2016, 1, 1, 2017, 8, 0, 2, 15,0,0);
		// Junior
		AcademiaZorrilla.crearCurso("I.ja", "Ingles",null,1, 1, 2016, 1, 1, 2017, 8, 0, 2, 5,4,12);
		// Errores
		AcademiaZorrilla.crearCurso("I.0a", "Ingles", "0", 1, 1, 2016, 1, 1, 2017, 8, 0, 2, 5,0,0);	//Error mismo id cn
		AcademiaZorrilla.crearCurso("I.ja", "Ingles",null,1, 1, 2016, 1, 1, 2017, 8, 0, 2, 5,4,12);	//Error mismo id cj
		// Comprobacion
		AcademiaZorrilla.imprimirCursos();	// Vemos que se crean los cursos
		
	// Creacion de matriculas
		//(String idCurso, String DNI, String nombre, String apellidos, int telefono)
		// Normales
		AcademiaZorrilla.crearMatricula("I.0a", "11111111a",null,null,0);	//Adulto	//OK
		AcademiaZorrilla.crearMatricula("I.0a",null, "Junior","1",0);		//Junior	//OK
		// Junior (solo para cursos junior)
		AcademiaZorrilla.crearMatricula("I.ja",null, "Junior", "1", 611111111);		//OK
		// Errores
		
		// Comprobacion
		AcademiaZorrilla.imprimirMatriculas();	// Vemos que se crean las matriculas
		AcademiaZorrilla.imprimirAlumnos();		// Vemos el incremento de deuda en los alumnos
		//TODO
		
		AcademiaZorrilla.imprimirListaCursosAlumno("11111111a",null,null);
		AcademiaZorrilla.imprimirListaCursosAlumno(null,"Junior", "1");
		AcademiaZorrilla.imprimirListaJuniorsDeAdulto("11111111a");
		AcademiaZorrilla.imprimirListaAlumnosCurso("I.0a");
		AcademiaZorrilla.pagarMatricula(1);
		AcademiaZorrilla.imprimirListaMatriculasPendientesCurso("I.0a");
		AcademiaZorrilla.imprimirAlumnos();		// Vemos el incremento de deuda en los alumnos
		AcademiaZorrilla.cambiarNivel("I.0a", "11111111a", null, null, true);
		AcademiaZorrilla.imprimirAlumnos();		// Vemos el incremento de deuda en los alumnos
		AcademiaZorrilla.cambiarNivel("I.1a", "11111111a", null, null, false);
		AcademiaZorrilla.imprimirAlumnos();		// Vemos el decenso de la deuda en el alumno
		
		/*
		AcademiaZorrilla.CrearCurso("I.0a", "Ingles", 0,1,1,2000,1,12,2000,8,0, 1,(double) 5);
		AcademiaZorrilla.CrearCurso("I.1a", "Ingles", 1,1,1,2000,1,12,2000,8,0, 2,(double) 10);
		AcademiaZorrilla.CrearCurso("I.2a", "Ingles", 2,1,1,2000,1,12,2000,8,0, 2,(double) 15);
		AcademiaZorrilla.CrearCurso("I.3a", "Ingles", 3,1,1,2000,1,12,2000,8,0, 1,(double) 20);
		AcademiaZorrilla.CrearCurso("F.0a", "Frances", 0,1,1,2000,1,12,2000,8,0, 1,(double) 4);
		AcademiaZorrilla.imprimirCursos();
		// Creacion de matriculas
		//(String idCurso, String DNI)
		AcademiaZorrilla.crearMatricula("I.0a", "72885727s");
		AcademiaZorrilla.crearMatricula("I.1a", "72885727s");
		AcademiaZorrilla.crearMatricula("I.2a", "72885727s");
		AcademiaZorrilla.crearMatricula("F.0a", "72885727s");
		AcademiaZorrilla.crearMatricula("I.1a", "12345667r");	// Error curso lleno
		//AcademiaZorrilla.imprimirMatriculas();
		//AcademiaZorrilla.imprimirAlumnos();
		//AcademiaZorrilla.imprimirMatriculasPendientes();
		//AcademiaZorrilla.pagarMatricula("I.0a", "72885727s");
		//AcademiaZorrilla.imprimirAlumnos();
		//AcademiaZorrilla.imprimirMatriculasPendientes();
		AcademiaZorrilla.cambiarNivel("I.1a", "72885727s", true);
		AcademiaZorrilla.cambiarNivel("I.1a", "12345667r", true);
		AcademiaZorrilla.imprimirMatriculas();
		
		AcademiaZorrilla.imprimirAlumnos();
		AcademiaZorrilla.imprimirListaCursosAlumno("72885727s");
		AcademiaZorrilla.imprimirListaAlumnosCurso("I.1a");
		AcademiaZorrilla.imprimirListaMatriculasPendientesCurso("I.2a");
	*/
	}
} // Fin de Academia