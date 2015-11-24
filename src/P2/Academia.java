package P2;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;


/**
 * Crea y mantiene la lista de cursos, alumnos y matriculas. 
 * Comprueba si se dan las condiciones previas para las matriculas y si es posible crearlas 
 * Debe probar del mismo modo el resto de mrtodos publicos de las otras tres clases.
 * 
 * @author oscafer
 * @author albguti
 * @author serorca
 * 
 */
public class Academia {
	//Probando GIIT GIIT
	private ArrayList<Curso> cursosAcademia;
	private ArrayList<Alumno>alumnosAcademia;
	private ArrayList<Matricula> matriculasAcademia;	
	
	// Constructor
	/**
	 * Constructor de una academia
	 */
	public Academia(){
		this.cursosAcademia= new ArrayList<Curso>();
		this.alumnosAcademia=  new ArrayList<Alumno>();
		this.matriculasAcademia= new ArrayList<Matricula>();
		
		//PRUEBA DE COMMIT 1
		//Probando GIT v4
		//Te huele el culo orkajo
		//commit push
	}

	//Alumnos
	/**
	 * Devuele el alumno si existe mediante su DNI, si no existe devuelve null.
	 * @param DNI	del alumno a comprobar
	 * @return a/null	(alumno si existe/null si no existe)
	 */
	/*
	public Alumno checkAlumno(Alumno a){
				
		if (alumnosAcademia.contains(a)) {   //Si el alumno existe en la academia
			return a;						// lo devolvemos
		} else {
			return null;					
		}
	}*/
	
	/**
	 * Devuele el alumno si existe mediante su DNI, si no existe devuelve null.
	 * @param DNI	del alumno a comprobar
	 * @return a/null	(alumno si existe/null si no existe)
	 */
	public Alumno checkAlumno(Alumno a){
		Alumno alumno = null;
		if (a instanceof Adulto){
			Iterator<Alumno> it = alumnosAcademia.iterator();
			while (it.hasNext()) {
				Adulto aux = (Adulto) it.next();
				if (aux.getDNI().equals(((Adulto) a).getDNI())) { //Check: Es el alumno deseado
					alumno=aux; //lo guardamos en a
				}
			}
			
					
		}else{
			Iterator<Alumno> it = alumnosAcademia.iterator();
			while (it.hasNext()) {
				Junior aux = (Junior) it.next();
				if (aux.equals(a)) { //Check: Es el alumno deseado
					alumno=aux; //lo guardamos en a
				}
			}
			
			
		}
			if (alumno == null) { //Si no est� el curso
				return null;
			} else {
				return alumno;
			}
			
			
		}
		
	/**
	 * Devuele el alumno si existe mediante su DNI, si no existe devuelve null.
	 * @param DNI	del alumno a comprobar
	 * @return a/null	(alumno si existe/null si no existe)
	 */
	public Adulto obtenerAdulto(String DNI){
		Adulto a=null;	// Si el curso existe se almacenara en a
		Iterator<Alumno> it = alumnosAcademia.iterator();
		while (it.hasNext()) {
			Adulto aux = (Adulto) it.next();
			if (aux.getDNI().equals(DNI)) { //Check: Es el alumno deseado
				a=aux; //le guardamos en a
			}
		}
		
		if (a == null) { //Si no est� el curso
			return null;
		} else {
			return a;
		}
	}
	
	
	/**
	 * Crea un alumno.
	 * 
	 * @param DNI	del nuevo alumno
	 * @param nombre del nuevo alumno
	 * @param apellidos del nuevo alumno
	 */
	public void CrearAlumno(String DNI, String nombre, String apellidos, GregorianCalendar fechaNac){
		//Alumno al = null;
		if (fechaNac==null){
			Adulto al = new Adulto(DNI,nombre, apellidos);
			if (checkAlumno(al)==null) {	// Comprobamos que NO exista un alumno con ese DNI ya
				alumnosAcademia.add(al); //Lo anadimos al ArrayList de alumnos de la academia.
				System.out.println("** - Alumno Adulto "+ al.getDNI() +" creado correctamente.");	
			}else System.out.println("**AVISO** Ya existe un alumno Adulto con DNI "+DNI+". **");
		}else{			
			Adulto resp = obtenerAdulto(DNI);
			if (resp!=null){
				Junior j = new Junior(  resp, nombre,  apellidos, fechaNac);
				if (checkAlumno(j)==null) {					
					alumnosAcademia.add(j); //Lo anadimos al ArrayList de alumnos de la academia.
					System.out.println("** - Alumno Junior "+ j.getNombre() +" creado correctamente.");	
				}else System.out.println("**AVISO** Ya existe ese alumno Junior **");
								
			}
							
		}
			
			
	}
	
	
	/**
	 * Imprime la lista de todos los alumnos de la academia.
	 */
	public void imprimirAlumnos(){
		if(!alumnosAcademia.isEmpty()){ //Si no est� vac�a
		System.out.println("____________________________________________________________________");
		System.out.println("_______________________________ALUMNOS______________________________");
		System.out.println("____________________________________________________________________");
		Iterator<Alumno> it = alumnosAcademia.iterator();
		while(it.hasNext()){
			Alumno a=it.next();
			if (a instanceof Adulto){
				System.out.println("  DNI: "+((Adulto) a).getDNI()+"\n\tNombre: "+a.getNombre()+"\tApellidos: "
			+a.getApellidos()+"\tDeuda: "+a.getDeuda());
				System.out.println("  -----------------------------------------------------------------");
			}else{
				System.out.println("  Nombre: "+a.getNombre()+"\tApellidos: "+a.getApellidos()+"\tDeuda: "
			+a.getDeuda()+"Fecha Nacimiento: "+((Junior) a).getFechaNac()+"Responsable: "+((Junior) a).getAdultoResponsable().getDNI());
				System.out.println("  -----------------------------------------------------------------");
			}
		}System.out.println("____________________________________________________________________\n");
		}else System.out.println("**OK** - No existen alumnos.");
		
	}
	/**
	 * Cambia de nivel a un alumno, teniendo en cuenta que solo se puede subir o bajar un nivel, 
	 * dentro del mismo idioma. Ademas no se puede superar el maximo numero de alumnos del curso al que cambia.
	 * 
	 * @param idCurso	identificador del curso que cambiamos de nivel
	 * @param DNI		DNI del alumno que cambiamos de nivel
	 * @param x			boolean: true = subir de nivel / false = bajar de nivel
	 */
	public void cambiarNivel(String idCurso, String DNI, boolean x){
		Alumno a = checkAlumno(DNI);					// Almacenamos el alumno
		if(a!=null){									// si el alumno si existe
			Curso c=checkCurso(idCurso);				// Almacenamos el curso ,
			if(c!=null){								// si el curso si existe
				String idioma = c.getIdioma();			// Almacenamos el idioma del curso de procedencia
				int nivel = c.getNivel();				// Almacenamos el nivel del curso de procedencia
				Iterator<Curso> it = cursosAcademia.iterator();
				boolean okMatriculado=false;	// Bandera para registrar que se ha realizado el cambio (true=cambio realizado)
				boolean encontradoCurso=false;	// Bandera para registrar que se ha encontrado el curso inf/sup (tre=curso inf/sup encontrado)
				boolean noMatriculado=false;	// Bandera para registrar que el alumno no esta matriculado (true=alumno no matriculado)
				while (it.hasNext() && (!okMatriculado || !noMatriculado)) { // Seguimos en el bucle si quedan mas cursos por recorrer y el alumno sigue sin matricularse o no ha intentado matricularse 
					Curso caux = it.next();
					if(x){						// x=true -> Vamos a subir de nivel
						if (caux.getIdioma() == idioma && caux.getNivel() == nivel + 1){ // Comprobamos que existe el curso superior
							encontradoCurso=true;
							String idMatricula=caux.getIdCurso()+DNI;
							if(checkMatricula(idMatricula)==null){					// Comprobamos que no exite la matricula
								noMatriculado=true;
								if(nAlumnosMatriculados(caux.getIdCurso())<c.getMaxNalumnos()){	// Comprobamos que el curso no esta completo
									okMatriculado=true;
									this.crearMatricula(caux.getIdCurso(), DNI);	// Matriculamos al alumno en el curso de nivel superior
									this.pagarMatricula(c.getIdCurso(), DNI);		// Dejamos como pagada la matricula en el curso del que procede
									eliminarMatricula(idCurso, DNI);
									
								}
							}
						}
					}else{						// x=false -> Vamos a bajar de nivel
						if (caux.getIdioma() == idioma && caux.getNivel() == nivel -1){ // Comprobamos que existe el curso inferior
							encontradoCurso=true;
							String idMatricula=caux.getIdCurso()+DNI;
							if(checkMatricula(idMatricula)==null){					// Comprobamos que no exite la matricula
								noMatriculado=true;
								if(nAlumnosMatriculados(caux.getIdCurso())<c.getMaxNalumnos()){	// Comprobamos que el curso no esta completo
									okMatriculado=true;
									this.crearMatricula(caux.getIdCurso(), DNI);	// Matriculamos al alumno en el curso de nivel inferior
									this.pagarMatricula(c.getIdCurso(), DNI);		// Dejamos como pagada la matricula en el curso del que procede
									eliminarMatricula(idCurso, DNI);
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
								System.out.println("**OK** - Cambio del nivel "+c.getNivel()+" al "+(c.getNivel()+1)+" en el idioma de "+c.getIdioma()+" al alumno con DNI "+DNI+" realizado correctamente.");
							}else System.out.println("**AVISO** No se ha podido realizar el cambio de nivel del alumno con DNI "+DNI+"\n\t en el curso de "+c.getIdioma()+", en el nivel "+(c.getNivel()+1)+" porque esta lleno.");
						}else System.out.println("**AVISO** No se ha podido realizar el cambio de nivel del alumno con DNI "+DNI+"\n\t porque ya esta matriculado en el curso de "+c.getIdioma()+", en el nivel "+(c.getNivel()+1)+". **");
					}else System.out.println("**AVISO** No se ha podido realizar el cambio de nivel del alumno con DNI "+DNI+"\n\t porque no existe el curso superior de "+c.getIdioma()+", en nivel "+(c.getNivel()+1)+". **");
				}else {		// Si bajamos nivel
					if(encontradoCurso){		// y encuentra el curso
						if(noMatriculado){		// y no esta matriculado
							if(okMatriculado){	// y ha conseguido matricularse
								System.out.println("**OK** - Cambio del nivel "+c.getNivel()+" al "+(c.getNivel()+1)+" en el idioma de "+c.getIdioma()+" al alumno con DNI "+DNI+" realizado correctamente.");
							}else System.out.println("**AVISO** No se ha podido realizar el cambio de nivel del alumno con DNI "+DNI+"\n\t en el curso de "+c.getIdioma()+", en el nivel "+(c.getNivel()-1)+" porque esta lleno.");
						}else System.out.println("**AVISO** No se ha podido realizar el cambio de nivel del alumno con DNI "+DNI+"\n\t porque ya esta matriculado  en el curso de "+c.getIdioma()+", en el nivel "+(c.getNivel()-1)+". **");
					}else System.out.println("**AVISO** No se ha podido realizar el cambio de nivel del alumno con DNI "+DNI+"\n\t porque no existe el curso inferior de "+c.getIdioma()+", en nivel "+c.getNivel()+". **");
				}// Fin de mensajes
			}else System.out.println("**AVISO** No existe el curso con id "+idCurso+".");
		}else System.out.println("**AVISO** No existe el alumno con DNI "+DNI+".");
	}
	
	/**
	 * Lista de cursos de un alumno
	 * @param DNI	
	 */
	public void imprimirListaCusosAlumno(String DNI){
		Alumno a =checkAlumno(DNI);	// Comprobamos que exista el alumno con DNI
		if ( a!= null) {
			boolean bandMatriculado=false; // Racogemos si no esta matriculado
			boolean alumnoSinMatriculas=true;		// Recogemos que no tiene ninguna matricula
			boolean cabeceraPuesta = false;		// Recogemos si se ha puesto la cabecera
				Iterator<Curso>it=cursosAcademia.iterator();	
				while(it.hasNext()){					// Recorremos los cursos
					Curso c=it.next();					// Almacenamos el curso para mostrar sus atributos en caso de estar matriculado
					bandMatriculado=false;				// Establecemos en un principio como que no esta matriculado
					String idMatricula=c.getIdCurso()+DNI;	// Formamos la matricula a comprobar con el curso que estamos recorriendo
					if(checkMatricula(idMatricula)!=null){	// Comprobamos si exite la matricula
						bandMatriculado=true;			// Almacenamos que si esta matriculado		
						alumnoSinMatriculas=false;		// Almacenamos que el alumno tiene alguna matricula 
					}
					else bandMatriculado=false;			// Almacenamos que no esta matriculado
					if(!alumnoSinMatriculas && !cabeceraPuesta ){	// Si el alumno tiene alguna matricula y no esta ya puesta la cabecera
						cabeceraPuesta=true;		// Almacenamos que ponemos la cabecera
						System.out.println("\t________________________________________________________");
						System.out.println("\t  <<<     Cursos matriculados del Alumno: " + DNI + " >>>");
						System.out.println("\t________________________________________________________\n");
					}
					if(bandMatriculado){	// Si el alumno esta matriculado del curso lo imprimimos
						System.out.println("\t    Id Curso: " + c.getIdCurso() + "\tIdioma: " + c.getIdioma()
						+ "\tNivel: " + c.getNivel());		// Imprimimos el curso 
					}
				}System.out.println("\t________________________________________________________\n");
				if(alumnoSinMatriculas){ 		// Si el alumno no esta matriculado en ningun curso
					System.out.println("\tNo esta matriculado en ningun curso **");
				}
		}else System.out.println("**AVISO** No exite el alumno con DNI "+DNI+".");
	}
	
	//Cursos
	/**
	 * Devuele el curso si existe mediante su identificador, si no existe devuelve null.
	 * @param idCurso	del curso ha comprobar
	 * @return c/null	(curso si existe/null si no existe)
	 */
	public Curso checkCurso(String idCurso){
		Curso c=null;	// Si el curso existe sera almacenado en c
		Iterator<Curso> it = cursosAcademia.iterator();
		while (it.hasNext()) {
			Curso caux = it.next();
			if (caux.getIdCurso().equals(idCurso)) {
				c=caux; // Almacenamos el curso en c
			}
		}
		if (c == null) {
			return null;
		} else
			return c;	
	}
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
	public void CrearCurso(String idCurso, String idioma, int nivel, int dia, int mes, int ano, int diaf, int mesf,
							int anof, int hora, int min, int maxNalumnos, double precio){
		if(checkCurso(idCurso)==null){		// Comprobamos que no exista un curso con el mismo idCurso
			if(nivel>=0){					// Comprobamos que el nivel sea mayor o igual a 0
				if(hora>=8 && hora<21){	// Comprobamos que la hora es entre las 8 y las 21
					GregorianCalendar fechaIni = new GregorianCalendar(ano, mes-1, dia);
					//java.sql.Date milfechaIni = new java.sql.Date(fechaIni.getTimeInMillis());	// Almacenamos la conversion a milisegundos de la fecha de inicio
					GregorianCalendar fechaFin = new GregorianCalendar(anof, mesf-1, diaf);
					//java.sql.Date milfechaFin = new java.sql.Date(fechaFin.getTimeInMillis());	// Almacenamos la conversion a milisegundos de la fecha de fin
					GregorianCalendar actual = (GregorianCalendar) GregorianCalendar.getInstance();
					if (actual.before(fechaIni)) { //Si la fecha de inicio del curso es posterior a la actual
						if (fechaIni.before(fechaFin)) { //Si la fecha de finalizacion es posterior a la de inicio del curso
						//long diferencia = (milfechaFin.getTime() - milfechaIni.getTime()); // Almacenamos la diferencia de fin - inicio
						//if(diferencia > 0){		// Comprobamos que la diferencia sea mayor que 0
							if(maxNalumnos>0){	// Comprobamos que el numero maximo de alumnos sea mayor que 0
								if(precio>=0){	// Comprobamos que el precio sea mayor o igual a 0
									GregorianCalendar horario =new GregorianCalendar(0,0,0,hora,min);
									Curso c= new Curso(idCurso,  idioma,  nivel,  fechaIni, fechaFin, horario, maxNalumnos,precio);
									cursosAcademia.add(c);
									System.out.println("Curso "+c.getIdCurso()+" creado correctamente.");	
								}else System.out.println("**AVISO** El precio ha de ser igual o mayor que 0�. **");
							}else System.out.println("**AVISO** El numero maximo de alumnos debe ser mayor que 0. **");
						}else System.out.println("**AVISO** La fecha de finalizacion del curso debe ser anterior a la fecha de inicio. **");
					}else System.out.println("**AVISO** La fecha de inicio del curso debe ser posterior a la actual. **");
				}else System.out.println("**AVISO** La hora debe ser entre las 8 y las 21. **");
			}else System.out.println("**AVISO** El nivel ha de ser mayor que 0. **");
		}else System.out.println("**AVISO** Ya existe un curso con identificador "+idCurso+". **");
	
	}
	/**
	 * Imprime la lista de cursos de la academia.
	 */
	public void imprimirCursos(){
		if(!cursosAcademia.isEmpty()){
		System.out.println("____________________________________________________________________");
		System.out.println("_____________________________ CURSOS _______________________________");
		System.out.println("____________________________________________________________________\n");
		Curso c;
		Iterator<Curso> it = cursosAcademia.iterator();
		while(it.hasNext()){
			c=it.next();
			String horario,mes,mesf;
			// Si minutos < 10 
			if (c.getHorario().get(Calendar.MINUTE) < 10) {
				horario =  Integer.toString(c.getHorario().get(Calendar.HOUR))+":0" + Integer.toString(c.getHorario().get(Calendar.MINUTE));
			} else {
				horario = Integer.toString(c.getHorario().get(Calendar.HOUR))+":"+Integer.toString(c.getHorario().get(Calendar.MINUTE));
			}
			// Si el mes es 12 en fecha inicio
			if (c.getFechaIni().get(Calendar.MONTH) ==11) {
				mes =  "12";
			} else {
				mes = Integer.toString(((c.getFechaIni().get(Calendar.MONTH))+1));
			}
			// Si el mes es 12 en fecha fin
						if (c.getFechaFin().get(Calendar.MONTH) ==11) {
							mesf =  "12";
						} else {
							mesf = Integer.toString(((c.getFechaFin().get(Calendar.MONTH))+1));
						}
			System.out.println("- Id curso: "+c.getIdCurso()+"\tIdioma: "+c.getIdioma()+"\t\tNivel:"+c.getNivel()+
					"\n\tFecha inicio: "+ c.getFechaIni().get(Calendar.DATE) + "/"+ mes +
					"/" + c.getFechaIni().get(Calendar.YEAR)+"\t Fecha de finalizacion: "+c.getFechaFin().get(Calendar.DATE)+"/"+
					mesf+"/"+c.getFechaFin().get(Calendar.YEAR)+"\n\tMax Num de Alumnos: "+
					c.getMaxNalumnos()+"\t Horario: "+horario+"\tPrecio: "+c.getPrecio());
			System.out.println("  ----------------------------------------------------------------");
			
		}System.out.println("____________________________________________________________________\n");
		}else System.out.println(" No hay cursos en la academia.");
	}
	
	/**
	 * Comprueba el numero de alumnos que estan matriculados en un curso
	 * 
	 * @param idCurso Identificador del curso 
	 * @return el numero de alumnos matriculados
	 */
	public int nAlumnosMatriculados(String idCurso){
		int nAlumnosMatriculados=0;						// Observamos el numero de matriculas que hay en el curso
		Iterator<Matricula>itm=matriculasAcademia.iterator();
		while(itm.hasNext()){
			Matricula m=itm.next();
			if(m.getIdMatricula().contains(idCurso)){		// si la matricula m es en el curso al que va a inscribirse
				nAlumnosMatriculados++;
			}
		}
		return nAlumnosMatriculados;
	}
	
	/**
	 * Lista de alumnos de un curso
	 * 
	 * @param idCurso identificador unico del curso
	 */
	public void imprimirListaAlumnosCurso(String idCurso){
		Curso c = checkCurso(idCurso);		// Comprobamos que exista el curso con ese identificador
		if (c != null) {
			boolean bandAlumnoMatriculado=false; 	// Recogemos si no hay matriculas
			boolean cursoSinMatriculas=true;		// Recogemos que el curso no tiene ninguna matricula
			boolean cabeceraPuesta = false;			// Recogemos si se ha puesto la cabecera
				Iterator<Alumno>it=alumnosAcademia.iterator();	
				while(it.hasNext()){					// Recorremos los alumnos
					Alumno a=it.next();					// Almacenamos el alumno para mostrar sus atributos en caso de estar matriculado
					bandAlumnoMatriculado=false;		// Establecemos en un principio como que el alumno no esta matriculado
					String idMatricula=idCurso+a.getDNI();	// Formamos la matricula a comprobar con el alumno que estamos recorriendo
					Matricula m =checkMatricula(idMatricula);
					if(m!=null){						// Comprobamos que si exite la matricula
						bandAlumnoMatriculado=true;		// Almacenamos que si tiene matricula del alumno		
						cursoSinMatriculas=false;		// Almacenamos que el curso tiene alguna matricula 
					}else {
						bandAlumnoMatriculado=false;	// Almacenamos que no tiene matricula del alumno
					}
					if(!cursoSinMatriculas && !cabeceraPuesta ){	// Si el curso tiene alguna matricula y no esta ya puesta la cabecera
						cabeceraPuesta=true;			// Almacenamos que ponemos la cabecera
						System.out.println("\t________________________________________________________");
						System.out.println("\t  <<<     Alumnos matriculados del Curso: " + idCurso + " >>>\n"
								+ "\t\t  Idioma: "+c.getIdioma()+"\t\tNivel:"+c.getNivel());
						System.out.println("\t________________________________________________________\n");
					}
					if(bandAlumnoMatriculado){			// Si el alumno esta matriculado del curso lo imprimimos
						System.out.println("\t  - DNI: " + a.getDNI()+"\tEstado de la matricula: "+m.getEstado() + "\n\t\tNombre: " +a.getNombre()
						+ "\tApellidos: " + a.getApellidos());		// Imprimimos el curso 
						System.out.println("\t  ----------------------------------------------------");
					}
				}
				if(cursoSinMatriculas){ 				// Si el curso no tiene ningun alumno matriculado
					System.out.println("\tEl curso "+idCurso+" no tiene ningun alumno matriculado. ");
				}
		}else System.out.println("**AVISO** No exite el curso con id "+idCurso+".");
	}
	
	/**
	 * Imprime un listado con las matriculas pendientes de un curso.
	 * 
	 * @param idCurso identificador del curso
	 */
	public void imprimirListaMatriculasPendientesCurso(String idCurso){
		Curso c =checkCurso(idCurso);	// Comprobamos que exista el curso con ese identificador
		if ( c!= null) {
			boolean bandAlumnoMatriculado=false; 	// Recogemos si no hay matriculas
			boolean cursoSinMatriculas=true;		// Recogemos que el curso no tiene ninguna matricula
			boolean cabeceraPuesta = false;			// Recogemos si se ha puesto la cabecera
				Iterator<Alumno>it=alumnosAcademia.iterator();	
				while(it.hasNext()){					// Recorremos los alumnos
					Alumno a=it.next();					// Almacenamos el curso para mostrar sus atributos en caso de tener alguna matricula pendiente
					bandAlumnoMatriculado=false;				// Establecemos en un principio como que el alumno no esta matriculado
					String idMatricula=idCurso+a.getDNI();	 	// Formamos la matricula a comprobar con el alumno que estamos recorriendo
					Matricula m =checkMatricula(idMatricula);
					if(m!=null){	// Comprobamos si exite la matricula
						bandAlumnoMatriculado=true;		// Almacenamos que si tiene matricula del alumno		
						cursoSinMatriculas=false;		// Almacenamos que el curso tiene alguna matricula 
					}
					else bandAlumnoMatriculado=false;	// Almacenamos que no tiene matricula del alumno
					if(!cursoSinMatriculas && !cabeceraPuesta ){	// Si el curso tiene alguna matricula y no esta ya puesta la cabecera
						cabeceraPuesta=true;		// Almacenamos que ponemos la cabecera
						System.out.println("\t________________________________________________________");
						System.out.println("\t  <<<     Mariculas pendientes del Curso: " + idCurso + " >>>\n"
								+ "\t\tIdioma: "+c.getIdioma()+"\t\tNivel:"+c.getNivel());
						System.out.println("\t________________________________________________________\n");
					}
					if(bandAlumnoMatriculado && !m.getEstado()){	// Si el alumno esta matriculado del curso y su matricula esta pendiente de pago 
						System.out.println("\t  - IdMatricula: " + m.getIdMatricula()+"\t    DNI: "+a.getDNI());		// Imprimimos el curso 
						System.out.println("\t  ----------------------------------------------------");
					}
				}
				if(cursoSinMatriculas){ 		// Si el curso no tiene matriculas pendientes
					System.out.println("\tEl curso no tiene ninguna matricula pendiente de pago. ");
				}
		}else System.out.println("**AVISO** No exite el curso con id "+idCurso+".");
	}
	
	// Matriculas
	/**
	 * Devuele la matricula si existe en la academia mediante su identificador, si no existe devuelve null.
	 * 
	 * @param idMatricula de la matricula ha comprobar
	 * @return m/null -> la matricula si existe / null si no existe
	 */
	public Matricula checkMatricula(String idMatricula){
		Matricula m=null;	// Si la matricula existe, se almacenara aqui
		Iterator<Matricula> it = matriculasAcademia.iterator();
		while (it.hasNext()) {
			Matricula maux = it.next();
			if (maux.getIdMatricula().equals(idMatricula)) {
				m=maux;
			}
		}
		if (m == null) {
			return null;
		} else
			return m;	
	}
	
	/**
	 * Crea una matricula
	 * @param idCurso  del curso en el que matriculamos 
	 * @param DNI  del alumno que se matricula
	 */
	public void crearMatricula(String idCurso, String DNI){
		Curso c=checkCurso(idCurso);				// Almacenamos el curso en c
		if(c!=null){								// si existe el curso en la academia
			if(nAlumnosMatriculados(idCurso)<c.getMaxNalumnos()){	// Comprobamos que el curso no esta ya completo
				Alumno a=checkAlumno(DNI);			// Almacenamos al alumno en a
				if(a!=null){						// si existe el alumno 
					String idMatricula=idCurso+DNI;					//creamos el identificador unico de la matricula
					if(checkMatricula(idMatricula)==null){			// Comprobamos que no exista una matricula con el mismo id (el alumno no esta matriculado)
						Matricula m = new Matricula(idMatricula);	// Creamos la matricula
						matriculasAcademia.add(m);
						a.setDeuda(a.getDeuda()+c.getPrecio());		// Anadimos el precio a la deuda del alumno
						System.out.println(" Matricula con id "+m.getIdMatricula()+" creada correctamente.");
					}else System.out.println("**AVISO** Ya existe una matricula con id "+idMatricula+". **");
				}else System.out.println("**AVISO** No existe el alumno con DNI "+DNI+".");
			}else System.out.println("**AVISO** El curso " + idCurso + " esta lleno, el alumno con DNI "+DNI+" no ha podido matricularse.");
		}else System.out.println("**AVISO** No existe el curso con id "+idCurso+".");
	}
	
	/**
	 * Elimina una matricula
	 * @param idCurso  del curso que queremos eliminar la matricula 
	 * @param DNI  del alumno que queremos eliminar la matricula
	 */
	public void eliminarMatricula(String idCurso, String DNI){
		Curso c=checkCurso(idCurso);				// Almacenamos el curso en c
		if(c!=null){								// si existe el curso en la academia
				Alumno a=checkAlumno(DNI);			// Almacenamos al alumno en a
				if(a!=null){						// si existe el alumno 
					String idMatricula=idCurso+DNI;					//creamos el identificador unico de la matricula
					Iterator<Matricula> it = matriculasAcademia.iterator();
					while(it.hasNext()){
						Matricula m=it.next(); // Seleccionamos la matricula
						if (m.getIdMatricula().equals(idMatricula)) {
							//TODO Estoy AQUI
							it.remove();
							System.out.println(" Matricula con id "+m.getIdMatricula()+" eliminada correctamente.");
						} 
					}
				}else System.out.println("**AVISO** No existe el alumno con DNI "+DNI+".");
	}else System.out.println("**AVISO** No existe el curso con id "+idCurso+".");
	}
	
	/**
	 * Imprime la lista de matriculas de la academia.
	 */
	public void imprimirMatriculas(){
		if(!matriculasAcademia.isEmpty()){
			System.out.println("____________________________________________________________________");
			System.out.println("____________________________ MATRICULAS ____________________________");
			System.out.println("____________________________________________________________________\n");
			Iterator<Matricula> it = matriculasAcademia.iterator();
			while(it.hasNext()){
				Matricula m=it.next();
				System.out.println("- Id: "+m.getIdMatricula()+"\t\tEstado: "+m.getEstado());
				System.out.println("  ----------------------------------------------------------------");
			}System.out.println("____________________________________________________________________\n");
		}else System.out.println(" No existen matriculas en la academia.");
		
	}
	/**
	 * Imprime la lista de matriculas pendientes de pago de la academia.
	 */
	public void imprimirMatriculasPendientes(){
		if(!matriculasAcademia.isEmpty()){
			System.out.println("____________________________________________________________________");
			System.out.println("_______________________ MATRICULAS PENDIENTES ______________________");
			System.out.println("____________________________________________________________________\n");
			Iterator<Matricula> it = matriculasAcademia.iterator();
			boolean band = false;
			while(it.hasNext()){
				Matricula m=it.next();
				if(m.getEstado()==false){
					band=true;
					System.out.println("- Id: "+m.getIdMatricula());
					System.out.println("  ----------------------------------------------------------------");
				}
			}
			if(band==false){
				System.out.println(" No existen matriculas pendientes de pagar.");
			}
			System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''");
		}else System.out.println("**OK** - No existen matriculas en la academia.");
	}
	
	/**
	 * Realiza el pago de una matricula
	 * @param idCurso del curso en que se efectua el pago
	 * @param DNI del alumno que efectua el pago
	 */
	public void pagarMatricula(String idCurso, String DNI){			
		Matricula m = checkMatricula(idCurso+DNI);		// Almacenamos la matricula a pagar en m
			if(m!=null){		// si existe la matricula y 
				if(m.getEstado()==false){	// no esta pagada ya,
					m.setEstado(true);						// cambiamos el estado de la matricula y
					//Alumno a= checkAlumno(m.getIdMatricula().substring(3));
					//Curso c= checkCurso(m.getIdMatricula().substring(0,3));
					Alumno a = checkAlumno(DNI);
					Curso c = checkCurso(idCurso);
					a.setDeuda(a.getDeuda()-c.getPrecio());// restamos a la deuda del alumno el precio del curso que paga
					System.out.println("**OK** - Matricula con id "+idCurso+DNI+" pagada correctamente.");
				}else System.out.println("**AVISO** - La matricula ya esta pagada.");
			}else System.out.println("**AVISO** No existe la matricula en el curso con id "+idCurso+" del alumno con DNI "+DNI+".");
		}
	

	//************************* Inicio de la aplicacion principal ********************************
		/**
		 * Inicio de metodo principal
		 * @param arg
		 */
	public static void main(String[] arg){
		
		// Creacion de academias
		Academia AcademiaZorrilla=new Academia();
		// Creacion de alumnos
		//(String DNI,String nombre,String apellidos)
		
		AcademiaZorrilla.CrearAlumno("72885727S", "Oscar", "Fernandez Aranda", null);
		AcademiaZorrilla.imprimirAlumnos();
		AcademiaZorrilla.CrearAlumno("72885727S", "Oscar", "Fernandez Aranda", null); //Error: alumno con DNI repetido
		AcademiaZorrilla.imprimirAlumnos();
		AcademiaZorrilla.CrearAlumno("72885727S", "Sergio", "Orcajo Fernandez", new GregorianCalendar (2000, 12, 12) );
		AcademiaZorrilla.CrearAlumno("70817416W", "Alberto", "Gutierrez del Campo ", new GregorianCalendar (2000, 13, 12));
		AcademiaZorrilla.imprimirAlumnos();
		
		// Creacion de cursos
		//(String idCurso, String idioma, int nivel, int dia, int mes, int ano, int diaf, int mesf, int anof, int hora, int min, int maxNalumnos, double precio)
		
	}
} // Fin de Academia