package mx.gob.stps.portal.core.infra.utils;

import java.util.ArrayList; 
import java.util.List;

public final class Constantes {

	private Constantes() {}
	
	/** Dia en Milisegundos OAM **/
	public static final long DIA_MILISEGUNDOS = 24 * 60 * 60 * 1000;

	/**Identificadores de ofertas recientes,destacadas, pet y gendarmeria**/
	public static final int OFERTARECIENTE = 1;
	public static final int OFERTADESTACADA = 2;
	public static final int OFERTAPET = 3;
	public static final int OFERTAGENDARMERIA = 4;
	public static final int GRADO_ESTUDIOS_LICENCIATURA = 10;
	public static final int GRADO_ESTUDIOS_MAESTRIA = 11;
	public static final int GRADO_ESTUDIOS_DOCTORADO = 12;

	/** Nombre de archivo de propiedades **/
	public static final String APPLICATION_PROPERTIES = "portal.properties";

	public static final String APPLICATION_MESSAGES = "portal-messages.properties";	
	
	/** Identificador del parametro para el tiempo de asignacion (lapso de temporizador) **/
	public static final long ID_PARAMETRO_TIEMPO = 1;

	/** Identificador del parametro para el tamaño del bloque de Registros por Asignar a Publicadores **/
	public static final long ID_PARAMETRO_TAM_BLOQUE = 2;

	/** Identificador del parametro para indicar si se carga la plantilla en el sitio **/
	public static final long ID_PARAMETRO_WITH_TEMPLATE = 3;
	
	public static final long ID_PARAMETRO_URL_WS_RENAPO_CONSULTA_POR_CURP = 4;
	
	public static final long ID_PARAMETRO_URL_WS_RENAPO_CONSULTA_POR_DETALLE = 5;
	
	public static final long ID_PARAMETRO_NUM_VACANTES_RSS = 6;
	
	/** Identificador del parametro para total de ofertas de OCC **/
	public static final long ID_PARAMETRO_TOTAL_VACANTES_OCC       = 7;
	/** Identificador del parametro para total de ofertas de Bumeran **/
	public static final long ID_PARAMETRO_TOTAL_VACANTES_BUMERAN   = 8;
	/** Identificador del parametro para total de ofertas de Manpower **/
	public static final long ID_PARAMETRO_TOTAL_VACANTES_MANPOWER  = 9;
	/** Identificador del parametro para total de ofertas de Adecco **/
	public static final long ID_PARAMETRO_TOTAL_VACANTES_ADECCO    = 10;
	/** Identificador del parametro para total de ofertas de El Universal **/
	public static final long ID_PARAMETRO_TOTAL_VACANTES_UNIVERSAL = 11;
	/** Identificador del parametro para total de ofertas de Portal del empleo **/
	public static final long ID_PARAMETRO_TOTAL_VACANTES_STPS      = 12;
	/** Identificador del parametro para el ultimo candidto registrado para el envio de SMS **/
	public static final long ID_PARAMETRO_ID_CANDIDATO_ULTIMO_SMS      = 13;
	/** Identificador del parametro para total de ofertas de Zona Jobs **/
	public static final long ID_PARAMETRO_TOTAL_VACANTES_ZONA_JOBS = 14;
	/** Identificador del parametro para total de ofertas de Zona Jobs **/
	public static final long ID_PARAMETRO_ID_EMPRESA_OFERTAS_CANADA = 15;
	/** Identificador del parametro para total de ofertas de superchamba **/
	public static final long ID_PARAMETRO_TOTAL_OFERTAS_SUPERCHAMBA = 24;
	/** Identificador del parametro para total de ofertas de trabajos mx **/
	public static final long ID_PARAMETRO_TOTAL_OFERTAS_TRABAJOSMX = 25;
	
	/** Identificador del parametro para el nombre de la cookie que se genera desde un sitio publicitario **/
	public static final long ID_PARAMETRO_COOKIE_NAME_PUBLICIDAD = 16;
	
	/** Identificador del parametro para el nombre del parametro de origen publicitario **/
	public static final long ID_PARAMETRO_REGISTRO_PUBLICIDAD = 17;
	
	/** Ruta de la pagina que contiene los terminos y condiciones del Portal del Empleo **/
	public static final String TERMINOS_CONDICIONES = "terminos.html";
	
	/** Ruta de la pagina que contiene los terminos y condiciones video del Portal del Empleo **/
	public static final String TERMINOS_CONDICIONES_CARGA_VIDEO_CV = "terminosCargaCv.html";

	/** Clave de Acceso para telefonos **/
	public static final String CLAVE_TELEFONO_CELULAR = "044";
	public static final String CLAVE_TELEFONO_FIJO = "01";
	
	/** Numero de registros a mostrar en ofertas recientes y destacadas **/
	public static final int NUMERO_REGISTROS = 6;

	/** Número de registros a mostrar cuando se desea ver la totalidad de ofertas recientes y destacadas **/
	public static final int NUMERO_REGISTROS_TODAS = 100;
		
	/** En minutos, intervalo entre consultas para refrescar la lista de ofertas recientes/destacadas**/
	public static final int INTERVALO_REGISTROS_TODAS = 30;

	/** Parametro que es enviado a en la URL validacion que identifica el tipo : EMPRESA o CANDIDATO**/
	public static final String PARAM_TIPO = "tipo=";
		
	/** Parametro que es enviado en la URL de validacion, identificador de la entidad empresa o candidato*/
	public static final String PARAM_ID = "id=";
		
	/** Parametro que es enviado en la URL de validacion , para enviar password**/
	public static final String PARAM_PASSW = "passw=";
	
	/** Caracter separador entre parametros **/
	public static final String SEPARADOR_PARAM = ",";

    public static final Integer TAMANO_CORRECTO           = 3;

    /** Datos Confidenciales **/
	public static final int DATOS_CONFIDENCIALES = 1;
	
	/**Dias asignados para depurar cuentas de usuario*/
	public static final Integer NUMERO_DIAS_CADUCAN_CUENTAS = 800;
	
	
	/** Número de Registros Ofertas Por Perfil **/	
	public static final int OFERTA_PERFIL_ALL = 100;
	public static final int OFERTA_PERFIL_TOP = 5;

	/** Salarios Minimos para Grado de estudio **/
	public static final double SALARIO_MINIMO_DOCTORADO = 25000.00;
	public static final double SALARIO_MINIMO_MAESTRIA = 15000.00;
	public static final double SALARIO_MINIMO_LICENCIATURA = 10000.00;
	
	/** Días restados a la fecha del sistema para la busqueda de candidatos INFONAVIT **/
	public static final int DIAS_REPORTE_INFONAVIT = 6;
	
	/** Número de registros a mostrar en la ventana que muestra más ofertas de trabajo después de una búsqueda desde Ocupate**/
	public static final int NUM_REGISTROS_SIGUIENTES_OFERTAS = 10;
	
	/** Límite superior de compatibilidad **/
	public static final int COMPATIBILITY_LIMIT = 70;
	
	/** Límite de registros adicionales beneficiarios**/
	public static final int REGISTROS_ADICIONALES_PROGRAMAS = 5;
	
	/** Catálogo de Estatus de Multiregistro **/
	public enum MULTIREGISTRO {
		PRINCIPAL(1,"Principal"),
		ADICIONAL(2,"Adicional");
		
		private int idOpcion;
		private String opcion;
	
		private MULTIREGISTRO(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
		
	}
	
	/** Catálogo de Situación académica **/
	public enum SITUACION_ACADEMICA {
		NINGUNO(1,"Ninguno"),
		ESTUDIANTE(2,"Estudiante"),
		DIPLOMA_CERTIFICADO(3,"diploma o certificado"),
		TRUNCA(4,"Trunca"),
		PASANTE(5,"Pasante"),
		TITULADO(6,"Titulado");
		
		private long idOpcion;
		private String opcion;
	
		private SITUACION_ACADEMICA(long idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public long getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
		
		public static String getDescripcion(int idOpcion){
			String descripcion = null;			
			for (SITUACION_ACADEMICA tipo : SITUACION_ACADEMICA.values()){
				if (tipo.getIdOpcion() == idOpcion){
					descripcion = tipo.getOpcion();
					break;
				}
			}
			return descripcion;
		}

	}
	
	/** Catálogo de Tipos de impresion de Kioscos**/
	public enum TIPO_IMPRESION_KIOSCO {
		CARTA(1,"Carta"),
		TICKET(2,"Ticket"),
		CARTA_NORMAL(3,"Carta Normal");
		
		private int idOpcion;
		private String opcion;
	
		private TIPO_IMPRESION_KIOSCO(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
		
	}
	
	/** Catálogo de Grado de estudios **/
	
	public enum RANGO_EDAD{
		
		DE_15_A_19(1,"15-19"),
		DE_20_A_29(2,"20-29"),
		DE_30_A_39(3,"30-39"),
		DE_40_A_49(4,"40-49"),
		DE_50_A_59(5,"50-59"),
		DE_60_0_MAS(6,"60 O MÁS");
		
		private int idRangoEdad;
		private String rangoEdad;
		
		private RANGO_EDAD(int idRangoEdad, String rangoEdad){
			
			this.idRangoEdad = idRangoEdad;
			this.rangoEdad = rangoEdad;
		}
		
		public int getIdRangoEdad() {return idRangoEdad;}
		public String getRangoEdad() {return rangoEdad;}
		
		public static String getDescripcion(int idRangoEdad){
			String descripcion = null;			
			for (RANGO_EDAD tipo : RANGO_EDAD.values()){
				if (tipo.getIdRangoEdad() == idRangoEdad){
					descripcion = tipo.getRangoEdad();
					break;
				}
			}
			return descripcion;
		}
		
	}
	
	/** Catálogo de Grado de estudios **/
	public enum GRADO_ESTUDIOS {
		
		SIN_INSTRUCCION(1,"Sin instrucción"),
		LEER_ESCRIBIR(2,"Saber leer y escribir"),
		PRIMARIA(3,"Primaria"),
		SECUNDARIA(4,"Secundaria/sec. técnica"),
		CARRERA_COMERCIAL(5,"Carrera comercial"),
		CARRERA_TECNICA(6,"Carrera técnica"),
		PROFESIONAL_TECNICO(7,"Profesional técnico"),
		PREPA_VOCACIONAL(8,"Prepa o vocacional"),
		SUPERIOR_UNIVERSITARIO(9,"T. superior universitario"),
		LICENCIATURA(10,"Licenciatura"),
		MAESTRIA(11,"Maestría"),
		DOCTORADO(12,"Doctorado");
		
		private long idOpcion;
		private String opcion;
	
		private GRADO_ESTUDIOS(long idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public long getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
		
		public static String getDescripcion(int idOpcion){
			String descripcion = null;			
			for (GRADO_ESTUDIOS tipo : GRADO_ESTUDIOS.values()){
				if (tipo.getIdOpcion() == idOpcion){
					descripcion = tipo.getOpcion();
					break;
				}
			}
			return descripcion;
		}

	}
	
	/** Catálogo de Tipo de empleo **/
	public enum TIPO_EMPLEO {
		
		TIEMPO_COMPLETO(1,"Tiempo completo"),
		MEDIO_TIEMPO(2,"Medio tiempo"),
		FINES_SEMANA(5,"Fines de semana"),
		TEMPORAL(6,"Temporal"),
		ROLAR_TURNOS(7,"Rolar turnos"),
		NOCTURNO(8,"Nocturno");
		
		private long idOpcion;
		private String opcion;
	
		private TIPO_EMPLEO(long idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public long getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
		
		public static String getDescripcion(int idOpcion){
			String descripcion = null;			
			for (TIPO_EMPLEO tipo : TIPO_EMPLEO.values()){
				if (tipo.getIdOpcion() == idOpcion){
					descripcion = tipo.getOpcion();
					break;
				}
			}
			return descripcion;
		}
	}
	
	/** Catálogo que indica si tiene estudios o no **/
	public enum ESCOLARIDAD {SIN_ESTUDIOS(1), CON_ESTUDIOS(2);
		
		private int idOpcion;
	
		private ESCOLARIDAD(int idOpcion){
			this.idOpcion = idOpcion;
		}

		public int getIdOpcion() {return idOpcion;}
	}
	
	/** Catálogo que indica si tiene estudios o no **/
	public enum EXPERIENCIA_LABORAL {SIN_EXPERIENCIA(1), CON_EXPERIENCIA(2);
		
		private int idOpcion;
	
		private EXPERIENCIA_LABORAL(int idOpcion){
			this.idOpcion = idOpcion;
		}

		public int getIdOpcion() {return idOpcion;}
	}
	
	/** Catálogo que indica si se actualizo o no el correo electronico **/
	public enum CAMBIO_CORREO {
		NO(1,"No"),
		SI(2,"Sí");
		
		private int idOpcion;
		private String opcion;
	
		private CAMBIO_CORREO(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
	}
	
	/** Catálogo de Disponibilidad para viajar **/
	public enum DISPONIBILIDAD_VIAJAR {
		
		NO(1,"No"),
		SI(2,"Sí");
		
		private long idOpcion;
		private String opcion;
	
		private DISPONIBILIDAD_VIAJAR(long idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public long getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
		
		public static String getDescripcion(int idOpcion){
			String descripcion = null;			
			for (DISPONIBILIDAD_VIAJAR tipo : DISPONIBILIDAD_VIAJAR.values()){
				if (tipo.getIdOpcion() == idOpcion){
					descripcion = tipo.getOpcion();
					break;
				}
			}
			return descripcion;
		}

	}
	
	/** Catálogo de Disponibilidad para radicar fuera **/
	public enum DISPONIBILIDAD_RADICAR {
		
		NO(1,"No"),
		SI(2,"Sí");
		
		private long idOpcion;
		private String opcion;
	
		private DISPONIBILIDAD_RADICAR(long idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public long getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
		
		public static String getDescripcion(int idOpcion){
			String descripcion = null;			
			for (DISPONIBILIDAD_RADICAR tipo : DISPONIBILIDAD_RADICAR.values()){
				if (tipo.getIdOpcion() == idOpcion){
					descripcion = tipo.getOpcion();
					break;
				}
			}
			return descripcion;
		}		
	}
	
	/** Catálogo de Residencia **/
	public enum CAMBIO_RESIDENCIA {
		
		SI(2,"Sí");
		
		private long idOpcion;
		private String opcion;
	
		private CAMBIO_RESIDENCIA(long idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public long getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
	}
	
	public enum PERFIL {ADMINISTRADOR(1,"Administrador del portal"),
		                PUBLICADOR   (2,"Publicador"),
		                EMPRESA      (3,"Empresa"),
		                CANDIDATO    (4,"Candidato"),
		                SUPERVISOR   (5,"Supervisor"),
		                ADMIN_TIPO_A (6,"Administrador Tipo A"),
		                ADMINISTRADOR_SNETEL (7,"Administrador SNETEL"),
		                MONITOR_PE        (8,"Monitor PE"),
		                SUPERVISOR_SNETEL (9,"Supervisor SNETEL"),
		                SUPERADMINISTRADOR(10, "Administrador General"),
		                ADMINISTRADOR_ESTATAL_CIL(11, "Administrador de CIL a nivel estatal"),
		                ADMINISTRADOR_CIL(12,"Administrador de CIL"),
		                ADMINISTRADOR_ESTATAL(13, "Administrador Portal del empleo estatal"),
		                ADMINISTRADOR_NACIONAL_KIOSCOS(14, "Administrador de Kioscos a nivel nacional"),
		                ADMINISTRADOR_ESTATAL_KIOSCOS(15, "Administrador de Kioscos a nivel de estado"),
		                ADMINISTRADOR_KIOSCO(16, "Administrador de Kiosco a nivel local"),
		                REPORTE_DE_ATENCIONES(17, "Reporte de atenciones"),
		                //ADMINISTRADOR_ESTATAL_PORTAL(20, "Administrador estatal del Portal del empleo"),
		                CANDIDATO_DISCAPACIDAD(24, "Candidato con discapacidad"),       
		                CANDIDATO_ADULTO_MAYOR(25, "Candidato adulto mayor"),
						PUBLICADOR_ABRIENDO_ESPACIOS(26, "Publicador Abriendo Espacios"),
		        		//FERIAS
		        		FERIAS_REGISTRO_EVENTO(80001,"Registro Evento"),
		        		FERIAS_RESPONSABLE_UR(80002,"Responsable UR"),
		        		FERIAS_RESPONSABLE_UC(80003,"Responsable UC"),
		        		FERIAS_RESPONSABLE_CGSNE(80004,"Responsable CGSNE");
		                
		
		private int idOpcion;
		private String opcion;
	
		private PERFIL(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
		
		public static PERFIL getPerfil(int idPerfil){
			PERFIL perfil = null;
			
			for (PERFIL tipo : PERFIL.values()){
				if (tipo.getIdOpcion() == idPerfil){
					perfil = tipo;
					break;
				}
			}
			
			return perfil;
		}
		
	}
	
	/** Catalogo de Estatus **/
	public enum ESTATUS {
		ACTIVO								(1,"Activa(o)"),
		INACTIVO							(2,"Inactiva"),
		MODIFICADA							(3,"Modificada"),
		SELECCIONADA						(4,"Seleccionada"),
		POSTULADO							(5,"Postulado"),
		DESPOSTULADO						(6,"Despostulado"),
		REGISTRADA							(7,"Registrada"),
		PREVALIDADA							(8,"Pre-validada"),
		EMP_MODIFICADA						(9,"Empresa Modificada"),
		CANCELADA							(10,"Cancelada"),
		EN_EDICION							(11,"En  edición"),
		PENDIENTE_PUBLICAR					(12,"Pendiente por publicar"),
		ELIMINADA_EMP						(13,"Eliminada por empresa"),
		ELIMINADA_ADMIN						(14,"Eliminada por administrador"),
		ELIMINADA_VIG						(15,"Eliminada por vigencia"),
		VINCULADO							(16,"Vinculado"),
		NO_INTERESADO						(17,"No interesado"),
		DESVNCULADO							(18,"Desvinculado"),
		SELECCIONADO						(19,"Seleccionado"),
		EN_PROCESO							(20,"En proceso"),
		CONTRATADO							(21,"Contratado"),
		NO_ACEPTADO							(22,"No aceptado"),
		RECHAZADA							(23,"Rechazada"),
		REPROGRAMADA						(24,"Reprogramada"),
		ACEPTADA							(25,"Aceptada"),
		ASIGNADO_PUBLICADOR					(26,"Asignado a publicador"),
		EN_EDICION_PUBLICADOR				(27,"En edición por publicador"),
		ELIMINADA_EMP_FRAUDULENTA			(28,"Eliminada por empresa fraudulenta"),
		NUEVA								(29,"Nueva Solicitud"),
		INACTIVO_POR_PERFIL					(30,"Inactivo por perfil"),
		ALCANZO_LIMITE_PORTULACION			(31,"Alcanzo el limite de postulaciones"),
		INACTIVO_POR_BAJA_ADMINISTRADOR		(32,"Inactivo por baja de administrador"),		
		INACTIVO_POR_BAJA_FUNCIONARIO		(33,"Inactivo por baja de funcionario"),
		INACTIVO_A_PETICION_DEL_CANDIDATO	(34,"Inactivo por el usuario"),
		INACTIVO_POR_VIGENCIA				(35,"Inactivo por vigencia"),
		SIN_VACANTES						(36,"Empresa sin ofertas publicadas"),
		ATENDIDA							(37,"Atendida"),
		PUBLICADA_PARA_SISNE				(38,"Oferta publicada sólo para SISNE"), 
		CUBIERTA							(39,"Oferta cubierta"), 
		PENDIENTE_POR_CAPACITACION			(40,"Pendiente por capacitación"),
		ELIMINADA_POR_MODIFICACION			(41,"Eliminada por modificación"),
		// ESTATUS PARA FERIAS
		FERIA_PENDIENTE_POR_PUBLICAR		(42,"Pendiente por publicar FERIAS"),
		FERIA_VALIDADO_UR					(43,"Validado por jefe de UR"),
		FERIA_VALIDADO_COOR_VIN_UC			(44,"Validado por coordinador de vinculación de UC"),
		FERIA_VALIDADO_CGSNE				(45,"Validado por ferias de empleo CGSNE"),
		FERIA_RECHADAZO_UR					(46,"Rechazado por jefe de UR"),
		FERIA_RECHADAZO_COOR_VIN_UC			(47,"Rechazado por coordinación de vinculación de UC"),
		FERIA_RECHADAZO_CGSNE				(48,"Rechazado por ferias de empleo CGSNE"),
		FERIA_SEGUIMIENTO					(49,"Seguimiento"),
		ABRIENDO_ESPACIOS_ACTIVO			(50,"Activo solo para Abriendo espacios"),
		BLOQUEADO_POR_FERIAS    			(51,"Bloqueado por Ferias"),		
		ACEPTADA_A_EVENTO           		(52,"Aceptada a evento"),
		CANCELADO                 		    (53,"Cancelado"),
		RECHAZADO                			(54,"Rechazado"),
		FERIA_OFERTAS_PUBLICADAS			(55,"Ofertas publicadas sólo para ferias"),
		OFERTA_ELIMINADA_EVENTO_CONCLUIDO   (56,"Oferta eliminada por evento concluido"),
		ELIMINADA_NO_VALIDACION  			(57,"Eliminada por no validación"),

		CONTRATADO_EN_OTRA_OFERTA			(62,"Contratado en otra oferta"),
		FINALIZADO                		    (63,"Finalizado"),
		MODALIDAD_ACTIVA          	     	(64,"Activa(o) para modalidad"),
		VALIDADA                 			(65,"Validada"),
		MODALIDAD_PROCESO_VALIDACION        (66,"Proceso-validacion-modalidad"),
		MODALIDAD_PENDIENTE_VALIDAR_UR      (67,"Pendiente-validar-UR-modalidad"),
		PENDIENTE_ACCION                    (68,"Pendiente-accion"),
		CANDIDATO_SOLICITA_INSCRIPCION		(69, "Candidato solicita inscripción a programa"),
		CANDIDATO_CANALIZADO				(70, "Candidato canalizado"),
		CANDIDATO_NO_INSCRITO				(71, "Candidato no inscrito a programa"),
		FIN_VIGENCIA_CANALIZACION			(72, "Fin de vigencia de la canalización")
		;		
	
		private int idOpcion;
		private String opcion;
	
		private ESTATUS(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {
			return idOpcion;
		}
		public String getOpcion() {
			return opcion;
		}

		public static String getDescripcion(int idOpcion){
			String descripcion = null;
			for (ESTATUS tipo : ESTATUS.values()){
				if (tipo.getIdOpcion() == idOpcion){
					descripcion = tipo.getOpcion();
					break;
				}
			}
			return descripcion;
		}

		//Start cambio movil
		public static int getInt(String descripcion){
			int estatus = 0;
			for(ESTATUS tipo : ESTATUS.values()){
				if(tipo.getOpcion().equals(descripcion)){
					estatus = tipo.getIdOpcion();
					break;
				}
			}
			return estatus;
		}

		
		//Fin cambio movil
		
	};
			
	/** Catalogo de Estatus **/
	public enum ESTATUS_CV {
		CERO(0,"0%"),
		UNO(1,"25%"),
		DOS(2,"50%"),
		TRES(3,"75%"),
		CUATRO(4,"100%");

	
		private int idOpcion;
		private String porcentaje;
	
		private ESTATUS_CV(int idOpcion, String porcentaje){
			this.idOpcion = idOpcion;
			this.porcentaje = porcentaje;
		}

		public int getIdOpcion() {
			return idOpcion;
		}
		public String getPorcentaje() {
			return porcentaje;
		}
	};
	
	/** Catalogo de Estatus **/
	public enum EVALUA_CV_FLUJO_PANTALLA{
		PERFIL(1,"Información de mi perfil", 25),
		ESCOLARIDAD(2,"Escolaridad y otros estudios", 50),
		EXPERIENCIA(3,"Experiencia laboral", 75),
		EXPECTATIVAS(4,"Expectativas laborales", 100);

	
		private int idOpcion;
		private String opcion;
		private int porcentaje;
		
		private EVALUA_CV_FLUJO_PANTALLA(int idOpcion, String opcion, int porcentaje){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
			this.porcentaje = porcentaje;
			
		}

		public int getIdOpcion() {
			return idOpcion;
		}
		public String getOpcion() {
			return opcion;
		}
		public int getPorcentaje() {
			return porcentaje;
		}
	};
	/** Catalogo de Genero **/
	public enum GENERO {
		MASCULINO(1,"Masculino"),
		FEMENINO(2,"Femenino"),
		NO_ES_REQUISITO(3,"No es requisito");
	
		private int idOpcion;
		private String opcion;
	
		private GENERO(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {
			return idOpcion;
		}
		public String getOpcion() {
			return opcion;
		}
		
		public static GENERO getGenero(int idOpcion){	
			GENERO genero = null;

			for (GENERO generofor : GENERO.values()){
				if (generofor.getIdOpcion() == idOpcion){
					genero = generofor;
					break;
				}
			}

			return genero;
		}
		
		public static String getDescripcion(int idOpcion){
			String descripcion = null;			
			for (GENERO tipo : GENERO.values()){
				if (tipo.getIdOpcion() == idOpcion){
					descripcion = tipo.getOpcion();
					break;
				}
			}
			return descripcion;
		}		
		
	};
	
	/** Catalogo Contactar candidato por correo **/
	public enum CONTACTO_CORREO {NO(1), SI(2);
		private int idContactoCorreo;

		private CONTACTO_CORREO(int idContactoCorreo){
			this.idContactoCorreo = idContactoCorreo;
		}
		public int getIdContactoCorreo() {
			return idContactoCorreo;
		}
	};

	public enum MEDIO_CONTACTO { FAX(1, "Fax"), TELEFONO(2, "Teléfono"), CORREO_ELECTRONICO(3, "Correo electrónico"), DOMICILIO(4, "Domicilio");
	
		private int idOpcion;
		private String opcion;
	
		// Catálodo de medios de contacto, utilizado en el SISNE
		private MEDIO_CONTACTO(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}
	
		public int getIdOpcion() {
			return idOpcion;
		}
		public String getOpcion() {
			return opcion;
		}
		public static String getDescripcion(int idOpcion){
			String descripcion = null;			
			for (MEDIO_CONTACTO tipo : MEDIO_CONTACTO.values()){
				if (tipo.getIdOpcion() == idOpcion){
					descripcion = tipo.getOpcion();
					break;
				}
			}
			return descripcion;
		}
};
	

	
	
	/** Catalogo Confidencialidad del candidato **/
	public enum CONFIDENCIALIDAD {NO(1), SI(2);
		private int idOpcion;

		private CONFIDENCIALIDAD(int idOpcion){
			this.idOpcion = idOpcion;
		}
		public int getIdOpcion() {
			return idOpcion;
		}
	};
	
	/** Catalogo Contactar candidato por telefono **/
	public enum CONTACTO_TELEFONO {NO(1), SI(2);
		private int idContactoTelefono;

		private CONTACTO_TELEFONO(int idContactoTelefono){
			this.idContactoTelefono = idContactoTelefono;
		}
		public int getIdContactoTelefono() {
			return idContactoTelefono;
		}
	};
	
	
	public enum CONTACTO_DOMICILIO {NO(1), SI(2);
	private int idContactoDomicilio;

	private CONTACTO_DOMICILIO(int idContactoDomicilio){
		this.idContactoDomicilio = idContactoDomicilio;
	}
	public int getIdContactoDomicilio() {
		return idContactoDomicilio;
	}
};
	
	/** Catalogo Recibir ofertas de empleo **/
	public enum RECIBE_OFERTA {NO(1), TELEFONO(2), CORREO(3),  AMBOS(4);
		private int idRecibeOferta;

		private RECIBE_OFERTA(int idRecibeOferta){
			this.idRecibeOferta = idRecibeOferta;
		}
		public int getIdRecibeOferta() {
			return idRecibeOferta;
		}
	};
	
	/** Catalogo Trabaja actualmente **/
	public enum TRABAJA { NO(1, "No"), SI(2, "Sí");
	
		private int idOpcion;
		private String opcion;

		private TRABAJA(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {
			return idOpcion;
		}
		public String getOpcion() {
			return opcion;
		}
		public static String getDescripcion(int idOpcion){
			String descripcion = null;			
			for (TRABAJA tipo : TRABAJA.values()){
				if (tipo.getIdOpcion() == idOpcion){
					descripcion = tipo.getOpcion();
					break;
				}
			}
			return descripcion;
		}
	};
	
	/** Indica si el candidato tiene computacion basica**/
	public enum COMPU_BASICA { 
		NO(1, "No"), 
		SI(2, "Sí");
	
		private int idOpcion;
		private String opcion;

		private COMPU_BASICA(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {
			return idOpcion;
		}
		public String getOpcion() {
			return opcion;
		}
	};
	/** Indica si el candidato tiene computacion avanzada**/
	public enum COMPU_AVANZADA { 
		NO(1, "No"), 
		SI(2, "Sí");
	
		private int idOpcion;
		private String opcion;

		private COMPU_AVANZADA(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {
			return idOpcion;
		}
		public String getOpcion() {
			return opcion;
		}
	};
	/** Indica si es el trabajo actual del candidato**/
	public enum TRABAJO_ACTUAL { 
		NO(1, "No"), 
		SI(2, "Sí");
	
		private int idOpcion;
		private String opcion;

		private TRABAJO_ACTUAL(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {
			return idOpcion;
		}
		public String getOpcion() {
			return opcion;
		}
	};
	/**
	 * Indica si se muestra el nombre de la empresa 
	 * en la historia laboral del candidato
	 **/
	public enum MOSTRAR_EMPRESA { 
		NO(1, "No"), 
		SI(2, "Sí");
	
		private int idOpcion;
		private String opcion;

		private MOSTRAR_EMPRESA(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {
			return idOpcion;
		}
		public String getOpcion() {
			return opcion;
		}
	};
	
	/**
	 * Indica si el candidato esta laborando o no 
	 **/
	public enum ESTATUS_LABORAL { 
		NO(1, "No trabajo actualmente"), 
		SI(2, "Trabajo actualmente"),
		NA(0, "Sin definir");
		private int idOpcion;
		private String opcion;

		private ESTATUS_LABORAL(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {
			return idOpcion;
		}
		public String getOpcion() {
			return opcion;
		}
	};
	/** Catalogo Conocimiento o Habilidad **/
	public enum CONOC_HAB { 
		CONOCIMIENTO(1, "Conocimiento"), 
		HABILIDAD(2, "Habilidad");
	
		private int idOpcion;
		private String opcion;

		private CONOC_HAB(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {
			return idOpcion;
		}
		public String getOpcion() {
			return opcion;
		}
	};
	
	/** Catalogo para Tipos de Registro **/
	public enum TIPO_REGISTRO {EMPRESA(1,"Empresa"), OFERTA(2,"Oferta"), TESTIMONIO(3,"Testimonio"), VIDEO_CURRICULO(4,"Vídeo Currículo");
		private int idTipoRegistro;
		private String tipoRegistro;

		private TIPO_REGISTRO(int idTipoRegistro, String tipoRegistro){
			this.idTipoRegistro = idTipoRegistro;
			this.tipoRegistro = tipoRegistro;
		}

		public int getIdTipoRegistro() {return idTipoRegistro;}
		public String getTipoRegistro() {return tipoRegistro;}
	};
	
	/** Constantes para la identificacion interna de subtipos de registros en la Asignacion de Registros por Validar **/
	public enum SUBTIPO_REGISTRO {EMPRESA_POR_AUTORIZAR(1,"Empresa por autorizar"), 
								  EMPRESA              (2,"Empresa"),
		                          OFERTA               (3,"Oferta"), 
		                          TESTIMONIO_EMPRESA   (4,"Testimonio de Empresa"),
		                          TESTIMONIO_CANDIDATO (5,"Testimonio de Candidato"),
		                          VIDEO_CURRICULO      (6,"Vídeo Currículo");
	
		private int idSubTipoRegistro;
		private String subTipoRegistro;

		private SUBTIPO_REGISTRO(int idSubTipoRegistro, String subTipoRegistro){
			this.idSubTipoRegistro = idSubTipoRegistro;
			this.subTipoRegistro = subTipoRegistro;
		}

		public int getIdSubTipoRegistro() {return idSubTipoRegistro;}
		public String getSubTipoRegistro() {return subTipoRegistro;}
		
		@Override
		public String toString() {
			return String.valueOf(idSubTipoRegistro);
		}
		
	};
	
	public enum TIPO_PROPIETARIO {
			EMPRESA(1,"Empresa"),
			CANDIDATO(2,"Candidato"), 
			OFERTA(3,"Oferta"),
			TERCERA_EMPRESA(4, "Tercera Empresa"), 
			CONTACTO(5, "Contacto"),
			EMPRESA_FRAUDULENTA(6, "Empresa Fraudulenta"),
			ADMINISTRADOR(7, "Administrador"),
			PERFIL(8, "Perfil"),
			CIL(9, "Cil"),
			OFICINA(10, "Oficina"),
			KIOSKO(11, "Kiosco"),
			SOLICITANTE(12, "Solicitante"),
			CITA(13, "Cita"),
			SOLICITANTE_CITA(14, "Solicitante Cita"),
			BENEFICIARIO(15, "Beneficiario"),
			REFERENCIA_LABORAL(16, "Referencia laboral"),
			CASETA(18, "Caseta");
			
		private int idTipoPropietario;
		private String tipoPropietario;
		
		private TIPO_PROPIETARIO(int idTipoPropietario, String tipoPropietario){
			this.idTipoPropietario = idTipoPropietario;
			this.tipoPropietario = tipoPropietario;
		}
		public int getIdTipoPropietario() {return idTipoPropietario;}
		public String getTipoPropietario() {return tipoPropietario;}
	};
	

    public enum TIPO_USUARIO {
        ADMINISTRADOR(1, "Administrador"),
        EMPRESA(2,"Empresa"),
        CANDIDATO(3,"Candidato");

        private static int MIN_INVALID_LIMIT = 1;
        private static int MAX_INVALID_LIMIT = 3;

        private int idTipoUsuario;
        private String tipoUsuario;

        private TIPO_USUARIO(int idTipoUsuario, String tipoUsuario){
            this.idTipoUsuario = idTipoUsuario;
            this.tipoUsuario = tipoUsuario;
        }

        public int getIdTipoUsuario() {return idTipoUsuario;}
        public String getTipoUsuario() {return tipoUsuario;}

        public static TIPO_USUARIO forIdTipoUsuario(final long id) {
            if (id < MIN_INVALID_LIMIT || id > MAX_INVALID_LIMIT) {
                throw new IllegalArgumentException(String.format("id [%d] value out of range [%d, %d]", id, MIN_INVALID_LIMIT, MAX_INVALID_LIMIT));
            }
            TIPO_USUARIO tipoUsuario = null;
            for (TIPO_USUARIO item : TIPO_USUARIO.values()) {
                if (item.getIdTipoUsuario() == id) {
                    tipoUsuario = item;
                    break;
                }
            }
            return tipoUsuario;
        }

		public static long getIdTipoUsuario(String tipoUsuarioEsperado) {
			for(TIPO_USUARIO item :TIPO_USUARIO.values()){
				if(item.getTipoUsuario().equals(tipoUsuarioEsperado)){
					return item.getIdTipoUsuario();
				}
			}
			return 0;
		}
        
        
    }

	
	/** Catalogo par Tipo Persona **/
	public enum TIPO_PERSONA {PERSONA_FISICA(1,"Persona Fisica"), PERSONA_MORAL(2, "Persona Moral");
		private int idTipoPersona;
		private String tipoPersona;

		private TIPO_PERSONA(int idTipoPersona, String tipoPersona){
			this.idTipoPersona = idTipoPersona;
			this.tipoPersona = tipoPersona;
		}

		public int getIdTipoPersona() {return idTipoPersona;}
		public String getTipoPersona() {return tipoPersona;}
		
		public static String getTipoPersona(int idTipoPersona){
			String descripcion = null;			

			for (TIPO_PERSONA tipo : TIPO_PERSONA.values()){
				if (tipo.getIdTipoPersona() == idTipoPersona){
					descripcion = tipo.getTipoPersona();
					break;
				}
			}

			return descripcion;
		}
	};
	
	/** Indicadores para verificar si una empresa esta pendiente de autorizar o ya se encuentra registrada **/
	public enum TIPO_EMPRESA {EMPRESA_POR_AUTORIZAR(1, "Empresa por autorizar"), EMPRESA(2, "Empresa");
		private int tipoEmpresa;
		private String tipoEmpresaDesc;
		
		private TIPO_EMPRESA(int tipoEmpresa, String tipoEmpresaDesc){
			this.tipoEmpresa = tipoEmpresa;
			this.tipoEmpresaDesc = tipoEmpresaDesc;
		}

		public int getTipoEmpresa() {return tipoEmpresa;}
		public String getTipoEmpresaDesc(){return tipoEmpresaDesc;}
		
		public static String getTipoEmpresa(int idTipoEmpresa){
			String descripcion = null;			

			for (TIPO_EMPRESA tipo : TIPO_EMPRESA.values()){
				if (tipo.getTipoEmpresa() == idTipoEmpresa){
					descripcion = tipo.getTipoEmpresaDesc();
					break;
				}
			}

			return descripcion;
		}

	};
	
	public enum MOTIVO_DESACTIVACION_CANDIDATO {
		A_PETICION_DEL_USUARIO(1, "A petición del usuario"), 
		POR_MAL_USO_SERVICIOS_SNE(2, "Por mal uso de los servicios del SNE"),
		POR_VIGENCIA(3, "Por vigencia");
	private int idMotivo;
	private String motivoDesc;
	
	private MOTIVO_DESACTIVACION_CANDIDATO(int idMotivo, String motivoDesc){
		this.idMotivo = idMotivo;
		this.motivoDesc = motivoDesc;
	}

	public int getIdMotivo() {return idMotivo;}
	public String getMotivoDesc(){return motivoDesc;}
	
	public static String getMotivo(int idMotivo){
		String descripcion = null;			

		for (MOTIVO_DESACTIVACION_CANDIDATO tipo : MOTIVO_DESACTIVACION_CANDIDATO.values()){
			if (tipo.getIdMotivo() == idMotivo){
				descripcion = tipo.getMotivoDesc();
				break;
			}
		}

		return descripcion;
	}

};
	
	public enum MOTIVO_DESACTIVACION_EMPRESA {
		INCUMPLE_DISPOSICIONES_EN_MATERIA_LABORAL(1, "Incumple las disposiciones en materia laboral"), 
		TRATA_CON_DESCORTESIA(2, "Trata con evidente descortesía a los buscadores enviados"),
		INFORMACION_FALSA(3, "Información falsa sobre la empresa o sobres sus ofertas"),
		MODIFICAN_TERMINOS_ORIGINALES(4, "Reiteradamente modifiquen los términos que originalmente registran"),
		A_SOLICITUD_DE_LA_PROPIA_EMPRESA(5, "A solicitud de la propia empresa"),
		DUPLICIDAD(6, "Duplicidad"),
		DESAPARECIO_EMPRESA(7, "Desapareció la empresa");
		private int idMotivo;
		private String motivoDesc;
		
		private MOTIVO_DESACTIVACION_EMPRESA(int idMotivo, String motivoDesc){
			this.idMotivo = idMotivo;
			this.motivoDesc = motivoDesc;
		}
	
		public int getIdMotivo() {return idMotivo;}
		public String getMotivoDesc(){return motivoDesc;}
		
		public static String getMotivo(int idMotivo){
			String descripcion = null;			
	
			for (MOTIVO_DESACTIVACION_EMPRESA tipo : MOTIVO_DESACTIVACION_EMPRESA.values()){
				if (tipo.getIdMotivo() == idMotivo){
					descripcion = tipo.getMotivoDesc();
					break;
				}
			}
	
			return descripcion;
		}
	};		
	
	public enum ES_EMPRESA_SNE {
		
		NO(1,"No"),
		SI(2,"Sí");
		
		private long idOpcion;
		private String opcion;
	
		private ES_EMPRESA_SNE(long idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public long getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
	}	

	/** Catalogo para los motivos de eliminacion de Registros por validar **/
	public enum VALIDACION_MOTIVO {AUTORIZA_EMPRESA(1,"Empresa Autorizada"),
								   AUTORIZA_OFERTA(2,"Oferta Autorizada"),
								   AUTORIZA_VIDEOC(3,"Vídeo Currículo Autorizada"),
								   AUTORIZA_TESTIMONIO(4,"Testimonio Autorizada"),
		                           RECHAZO_EMPRESA_FRAUDULENTA(5, "Empresa fraudulenta"),
		                           RECHAZO_DATOS_INCONSISTENTES(6, "Datos inconsistentes");
		private int idMotivo;
		private String motivo;
		
		private VALIDACION_MOTIVO (int idMotivo, String motivo){
			this.idMotivo = idMotivo;
			this.motivo = motivo;
		}

		public int getIdMotivo() {return idMotivo;}
		public String getMotivo() {return motivo;}
	};
	
	public enum EVENTO {
		AUTORIZA_REGISTRO(1, "Registro Autorizado"), 
		RECHAZA_REGISTRO(2, "Registro Rechazado"), 
		REGISTRA_EMPRESA_POR_AUTORIZAR(3, "Registra Empresa por Autorizar"),
		REGISTRA_TERCERA_EMPRESA(4, "Registra Tercera Empresa"),
		ACTIVA_OFERTA(5,"Activa Oferta de Empleo"),
	    NOTIFICACION_EMPRESA_POR_AUTORIZAR(6,"Notificación Empresa por autorizar"),
	    NOTIFICACION_EMPRESA(7,"Notificacion Empresa"),
	    NOTIFICACION_CANDIDATO_CAMBIO_EMAIL(8, "Notificacion a Candidato cambio de email"),	
	    NOTIFICACION_EMPRESA_PREVALIDADA(9, "Notificacion a Empresa prevalidada"),
	    NOTIFICACION_EMPRESA_INACTIVA(10, "Notificacion a Empresa inactiva"),
	    MODIFICA_EMPRESA_POR_AUTORIZAR(11, "Modifica Empresa por Autorizar"),
	    MODIFICA_EMPRESA(12, "Modifica Empresa"),
	    NOTIFICACION_EMPRESA_MODIFICADA(13, "Notificacion a Empresa modificada"),
	    REGISTRA_CONTACTO(14, "Registra Contacto"),
	    NOTIFICACION_CANDIDATO_EMPRESA_CAMBIO_EMAIL(15, "Notificacion a candidato, empresa modifico email"),
		REGISTRO_CANDIDATOS(16, "Registro de candidato"),
		ACTIVACION_CANDIDATO(17, "Activacion de cuenta de usuario del Candidato"),
		ACTIVACION_EMPRESA(18, "Activacion de cuenta de usuario de la Empresa"),
		NOTIFICACION_CANDIDATO_ADMON_CAMBIO_EMAIL(19, "Notificacion a candidato, admon modifico email"),
		REGISTRO_PERFIL_LABORAL_CANDIDATO(20, "Registro del perfil laboral del candidato"),
		REGISTRO_ESCOLARIDAD_CANDIDATO(21, "Registro de la escolaridad del candidato"),
		REGISTRO_EXPERIENCIA_CANDIDATO(22, "Registro de experiencia del candidato"),
		REGISTRO_EXPECTATIVA_CANDIDATO(23, "Registro de expectativa laboral del candidato"),
		MODIFICA_TERCERA_EMPRESA(24, "Modifica Tercera Empresa"),
		MODIFICA_CONTACTO(25, "Modifica Contacto"),
		ENTREVISTA_REPROGRAMAR(26, "Reprogramar Entrevista"),
		ENTREVISTA_ACEPTADA(27, "Aceptar Entrevista"),
		ENTREVISTA_CANCELADA(28, "Cancelar Entrevista"),
		ENTREVISTA_RECHAZADA(29, "Rechazar Entrevista"),
		RECUPERA_EMPRESA_ELIMINADA_ADMIN(30, "Recupera Empresa eliminada por el admon"),
		CONFIRMA_CONTRASENA(31, "Confirmacion de contraseña"),
		MODIFICA_OFERTA_EMPLEO(32, "Modifica Oferta Empleo"),
		REGISTRO_EMPRESAS(33, "Registro de Empresa"),
		REGENERACION_CUENTA_EMPRESA(34, "Regeneracion de cuenta de Empresa"),
		REGENERACION_CUENTA_CANDIDATO(35, "Regeneracion de cuenta de Candidato"),
		REGISTRAR_PERFIL(36, "Registrar Perfil"),
		DESACTIVAR_PERFIL(37, "Desactivar Perfil"),
		MODIFICAR_PERFIL(38, "Modificar Perfil"),
		REACTIVAR_PERFIL(39, "Reactivar Perfil"),
		REGISTRAR_USUARIO(40, "Registrar Usuario"),
		DESACTIVAR_USUARIO(41, "Desactivar Usuario"),		
		MODIFICAR_USUARIO(42, "Modificar Usuario"),
		REACTIVAR_USUARIO(43, "Reactivar Usuario"),
		REGISTRAR_CIL(44, "Registrar CIL"),
		MODIFICAR_CIL(45, "Modificar CIL"),
		DESACTIVAR_CIL(46, "Desactivar CIL"),
		REACTIVAR_CIL(47, "Reactivar CIL"),
		REGISTRAR_ADMINISTRADOR_CIL(48, "Registrar Administrador CIL"),
		DESACTIVAR_ADMINISTRADOR_CIL(49, "Desactivar Administrador CIL"),
		REACTIVAR_ADMINISTRADOR_CIL(50, "Reactivar Administrador CIL"),
		REGISTRAR_CODIGO_ACCESO_CIL(51, "Registrar Codigo de Acceso CIL"),
		REGISTRAR_BOLSA_TRABAJO_CIL(52, "Registrar Bolsa de Trabajo CIL"),
		ELIMINAR_BOLSA_TRABAJO_CIL(53, "Eliminar Bolsa de Trabajo CIL"),
		REGISTRAR_OFICINA(54, "Registrar Usuario"),
		DESACTIVAR_OFICINA(55, "Desactivar Usuario"),
		MODIFICAR_OFICINA(56, "Modificar Usuario"),

		DESACTIVAR_CANDIDATO       (57, "Desactivar Candidato a peticion del Candidato"),
		REACTIVAR_CANDIDATO        (58, "Reactivar Candidato a peticion del Candidato"),

		INICIA_SESION              (59, "Inicio de sesión"),
		FINALIZA_SESION            (60, "Cerrar sesión"),
		ACTUALIZA_DATOS_PERSONALES (61, "Actualizar datos"),
		REGISTRA_CUENTA_PERSONAL   (62, "Alta de cuenta personal"),
		BUSQUEDA_ESPECIFICA		   (63, "Buscador especifico"),
		BUSQUEDA_OCUPATE		   (64, "Buscador Ocúpate"),
		BUSQUEDA_POR_PERFIL		   (65, "Buscador por perfil"),
		BUSQUEDA_OTRAS_BOLSAS	   (66, "Buscar en otras bolsas de trabajo"),
		CONSULTA_CURRICULO		   (67, "Ver CV"),
		RECUPERAR_OFERTA		   (68, "Recuperar Oferta"),
		USAR_PLANTILLA_OFERTA	   (69, "Usar Plantilla de Oferta"),
		DETECTAR_EMPRESA_FRAUDULENTA	   (70, "Detectar empresa fraudulenta"),
		
		DESACTIVAR_EMPRESA       (71, "Desactivar Empresa por Administrador"),
		REACTIVAR_EMPRESA        (72, "Reactivar Empresa por Administrador"),
		
		//EVENTOS RECUPERACION USUARIO & CONTRASEÑA OAM
		SOLICITUD_CAMBIO_CONTRASEÑA(79, "Solicitud de Cambio de Contraseña Exitoso"),
		CAMBIO_CONTRASEÑA (80, "Cambio de Contraseña Exitoso"),
		RECUPERA_USUARIO (81, "Recuperación de Usuario Exitoso"),
		
		//CAMBIO MOVIL
		REGISTRO_DE_POSTULACION  (73, "Postulacion por Candidato"),
		ACTIVO_PPC  (73, "Activo para el PPC"),
		INACTIVO_PPC  (73, "Inactivo para el PPC"),
		FUERA_PPC  (73, "Fuera del PPC"),
		ACTUALIZAR_PARAMETRO		(74, "Actualizar parámetro por Administrador")
		;
		
		private long idEvento;
		private String evento;
		
		private EVENTO(long idEvento, String evento){
			this.idEvento = idEvento;
			this.evento = evento;
		}

		public long getIdEvento() {return idEvento;}
		public String getEvento() {return evento;}
	};
	


	public enum Plantilla {
	HTML_REGISTRO_CANDIDATO("mail.plantilla.html.registro.candidato"),
	HTML_REGISTRO_CANDIDATO_CON_PPC("mail.plantilla.html.registro.candidato.conppc"),
	HTML_VALIDA_CUENTA("mail.plantilla.html.empresa.validacuenta"),	
	HTML_NEW_PASSWORD("mail.plantilla.html.password"),
	HTML_RECUPERAR_PASSWORD("mail.plantilla.html.recuperarpassword"),
	HTML_ENTREVISTA("mail.plantilla.html.entrevista"),
	HTML_OFERTA_CANDIDATO("mail.plantilla.html.ofertaCandidato"),
	HTML_NOTIFICA_POSTULACION("mail.plantilla.html.notificaPostulado"),
	HTML_NOTIFICA_POSTULACION_PPC("mail.plantilla.html.notificaPostuladoPPC"),	
	HTML_REPORTE_INFONAVIT("mail.plantilla.html.reporte.semanalInfonavit"),
	HTML_VALIDA_CUENTA_EMPRESA("mail.plantilla.html.empresa.validacuentaempresa"),
	HTML_NOTIFICA_CANDIDATO_DESACTIVADO("mail.plantilla.html.empresa.desactivadoCandidato"),
	HTML_NOTIFICA_CANDIDATO_PROXIMA_DESACTIVACION("mail.plantilla.html.candidato.proximaDesactivacion"),
	HTML_NOTIFICA_CANDIDATO_EMPRESA_DESACTIVADA("mail.plantilla.html.empresa.desactivada.postulantes"),
	HTML_NOTIFICA_RECOMENDACION("mail.plantilla.html.recomendacion"),
	HTML_NOTIFICA_CONTRATACION("mail.plantilla.html.contratacion.candidato"),
	HTML_NOTIFICA_RECHAZO("mail.plantilla.html.candidato.rechazado"),
	HTML_NOTIFICA_VINCULACION("mail.plantilla.html.candidato.vinculado"),
	HTML_NOTIFICA_NEW_PASSWORD_EMPRESA("mail.plantilla.html.new.password.empresa"),
	HTML_NOTIFICA_INSCRIPCIONPPC("mail.plantilla.html.ppc.inscripcion");
		
		Plantilla(String archivoPlantilla){
				this.archivoPlantilla = PropertiesLoader.getInstance().getProperty(archivoPlantilla);
		}

		private String archivoPlantilla;
		public String getArchivoPlantilla(){
			return archivoPlantilla;
		}
	};

	public enum ETIQUETAS {
		
        //NODOSADECCO("Table", "Fecha", "PuestoOfrecido", "Estado", "Empresa", "Url"),
        NODOSMANPOWER("Registro", "Dato1", "Dato2", "Dato4", "Dato3", "Dato5"),
        NODOSUNIVERSAL("ns1:Aviso", "ns1:fechaInicio", "ns1:puesto", "ns1:zonaDescr", "ns1:empresa","ns1:urlAviso"),
        NODOSZONAJOBS("jobid", "startDate", "jobTitle", "nameLocation", "otherCompanyName","link"),
        NODOSRIVIERAMAYA("oferta", "","nombrepuesto","ubicacion","razonsocial","id_oferta","gradoescolaridad",""),
        NODOSTURIJOBS("job", "posted-date", "title", "state", "name","detail-url", "summary", "city"),
        NODOSDISCACIDADEMPLEO("vacante","fecha","idVacante","nombreVacante","idDiscapacidad","nombreDiscapacidad","imagen","idEmpresa","nombreEmpresa","idEstado","nombreEstado","url"),
		NODOS_SUPERCHAMBA(/*tagRegistro:*/"Vacante", /*tagFecha:*/"Fecha", /*tagPuesto:*/"PuestoOfrecido", /*tagEstado:*/"Estado", /*tagEmpresa:*/"Empresa", /*tagUrl:*/"url");

        private String tagRegistro;
        private String tagFecha;
        private String tagPuesto;
        private String tagEstado;
        private String tagEmpresa;
        private String tagUrl;
        private String tagDescripcion;
        private String tagCiudad;
        
        private String tagidPuesto;
        private String tagidDiscapacidad;
        private String tagDiscapacidad;
        private String tagImagen;
        private String tagidEmpresa;
        private String tagidEstado;
         
        
        private ETIQUETAS(String tagRegistro, String tagFecha, String tagPuesto, String tagEstado, String tagEmpresa, String tagUrl){
                 this.tagRegistro = tagRegistro;
                 this.tagFecha = tagFecha;
                 this.tagPuesto = tagPuesto;
                 this.tagEstado = tagEstado;
                 this.tagEmpresa = tagEmpresa;
                 this.tagUrl = tagUrl;
        }        
        private ETIQUETAS(String tagRegistro, String tagFecha, String tagPuesto, String tagEstado, String tagEmpresa, String tagUrl, 
        		String tagDescripcion, String tagCiudad){
            this.tagRegistro = tagRegistro;
            this.tagFecha = tagFecha;
            this.tagPuesto = tagPuesto;
            this.tagEstado = tagEstado;
            this.tagEmpresa = tagEmpresa;
            this.tagUrl = tagUrl;
            this.tagDescripcion = tagDescripcion;
            this.tagCiudad = tagCiudad;
        }        

        private ETIQUETAS(String tagRegistro, String tagFecha,String tagidPuesto, String tagPuesto,String tagidDiscapacidad,String tagDiscapacidad, 
        		String tagImagen,String tagidEmpresa,String tagEmpresa,String tagidEstado, String tagEstado,String tagUrl){
            this.tagRegistro = tagRegistro;
            this.tagFecha = tagFecha;
            this.tagidPuesto = tagidPuesto;
            this.tagPuesto = tagPuesto;
            this.tagidDiscapacidad = tagidDiscapacidad;
            this.tagDiscapacidad = tagDiscapacidad;
            this.tagImagen = tagImagen;
            this.tagidEmpresa = tagidEmpresa;
            this.tagEmpresa = tagEmpresa;
            this.tagidEstado = tagidEstado;
            this.tagEstado = tagEstado;
            this.tagUrl = tagUrl;
            
        }
        
        
		public String getTagRegistro() {
			return tagRegistro;
		}

		public void setTagRegistro(String tagRegistro) {
			this.tagRegistro = tagRegistro;
		}

		public String getTagFecha() {
			return tagFecha;
		}

		public void setTagFecha(String tagFecha) {
			this.tagFecha = tagFecha;
		}

		public String getTagPuesto() {
			return tagPuesto;
		}

		public void setTagPuesto(String tagPuesto) {
			this.tagPuesto = tagPuesto;
		}

		public String getTagEstado() {
			return tagEstado;
		}

		public void setTagEstado(String tagEstado) {
			this.tagEstado = tagEstado;
		}

		public String getTagEmpresa() {
			return tagEmpresa;
		}

		public void setTagEmpresa(String tagEmpresa) {
			this.tagEmpresa = tagEmpresa;
		}

		public String getTagUrl() {
			return tagUrl;
		}

		public void setTagUrl(String tagUrl) {
			this.tagUrl = tagUrl;
		}
		
		public String getTagDescripcion() {
			return tagDescripcion;
		}

		public void setTagDescripcion(String tagDescripcion) {
			this.tagDescripcion = tagDescripcion;
		}	
		
		public String getTagCiudad() {
			return tagCiudad;
		}

		public void setTagCiudad(String tagCiudad) {
			this.tagCiudad = tagCiudad;
		}


		public String getTagidPuesto() {
			return tagidPuesto;
		}


		public void setTagidPuesto(String tagidPuesto) {
			this.tagidPuesto = tagidPuesto;
		}


		public String getTagidDiscapacidad() {
			return tagidDiscapacidad;
		}


		public void setTagidDiscapacidad(String tagidDiscapacidad) {
			this.tagidDiscapacidad = tagidDiscapacidad;
		}


		public String getTagDiscapacidad() {
			return tagDiscapacidad;
		}


		public void setTagDiscapacidad(String tagDiscapacidad) {
			this.tagDiscapacidad = tagDiscapacidad;
		}


		public String getTagImagen() {
			return tagImagen;
		}


		public void setTagImagen(String tagImagen) {
			this.tagImagen = tagImagen;
		}


		public String getTagidEmpresa() {
			return tagidEmpresa;
		}


		public void setTagidEmpresa(String tagidEmpresa) {
			this.tagidEmpresa = tagidEmpresa;
		}


		public String getTagidEstado() {
			return tagidEstado;
		}


		public void setTagidEstado(String tagidEstado) {
			this.tagidEstado = tagidEstado;
		}			
		
	};
	
	public enum TIPO_QUERY_OLA {
		OCUPACION(1), CARRERA(2);
		int tipoQueryOLA;
		TIPO_QUERY_OLA(int opcion) {
			this.tipoQueryOLA = opcion;
		}
		
		public int getTipoQueryOLA() {
			return tipoQueryOLA;
		}
		public void setTipoQueryOLA(int tipoQueryOLA) {
			this.tipoQueryOLA = tipoQueryOLA;
		}
	}	
	
	public enum CATALOGO_FUENTE_QUE_REGISTRA_OFERTA {
		PORTAL(1,"Portal"),
		SFP(2,"SFP"),
		CANADA(3,"Canadá"),
		SISNE(4,"SISNE"),
		//SNETEL(5,"SNETEL"),
		//ADMINISTRADOR(6,"Administrador portal del empleo"),
		//SIISNE(7,"SIISNE"),
		//FERIAS(8,"Ferias"),
		ABRIENDO_ESPACIOS(9,"Abriendo Espacios");		
		
		private int idOpcion;
		private String opcion;
		
		private CATALOGO_FUENTE_QUE_REGISTRA_OFERTA(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}
		
		public int getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
		
		public static String getDescripcion(int idOpcion){
			String descripcion = null;			
			for (CATALOGO_FUENTE_QUE_REGISTRA_OFERTA fuente : CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.values()){
				if (fuente.getIdOpcion() == idOpcion){
					descripcion = fuente.getOpcion();
					break;
				}
			}
			return descripcion;
		}
	}
	
	
	/**
	 * Constantes para el campo estatus de la tabla Telefono
	 */
	public static final int TELEFONO_CELULAR = 1;
	public static final int TELEFONO_FIJO = 5;

	
	/**
	 * Constantes para el campo idCatalogo de la tabla Catalogo Opcion
	 */
	public static final long CATALOGO_OPCION_ESTATUS = 1;
	public static final long CATALOGO_OPCION_ESTADO_CIVIL = 2;
	public static final long CATALOGO_OPCION_TIPO_DISCAPACIDAD = 3;
	public static final long CATALOGO_OPCION_MEDIO_ENTERADO = 4;	//Cómo se entero
	public static final long CATALOGO_OPCION_HORARIO_CONTACTO = 5;
	public static final long CATALOGO_OPCION_SI_TRABAJA = 6;//Si el candidato trabaja actualmente
	public static final long CATALOGO_OPCION_OTROS_MEDIOS = 7; //Otros medios para buscar trabajo
	public static final long CATALOGO_OPCION_GRADO_ESTUDIOS = 8;
	public static final long CATALOGO_OPCION_ESTATUS_GRADO = 10; //Situacion academica
	public static final long CATALOGO_OPCION_IDIOMAS = 11;
	public static final long CATALOGO_OPCION_DOMINIO = 13; //Dominio en el idioma, conocimiento, habilidad o competencia
	public static final long CATALOGO_OPCION_EXPERIENCIA = 14; //Experiencia en el idioma, conocimiento, habilidad o competencia -- Años de experiencia total
	public static final long CATALOGO_OPCION_TIPO_EMPLEO = 15;
	public static final long CATALOGO_OPCION_CAUSA_OFERTA = 16;
	public static final long CATALOGO_OPCION_SUBSECTOR = 17;
	public static final long CATALOGO_OPCION_NO_TRABAJA = 18; //Si el NO candidato trabaja actualmente
	public static final long CATALOGO_OPCION_PRESTACIONES = 19;
	public static final long CATALOGO_OPCION_AREA_LABORAL = 20;
	//RBM1 TK1000 TK1001 se agrega SUB_AREA
	public static final long CATALOGO_OPCION_OCUPACION = 21;
	public static final long CATALOGO_OPCION_SUBAREA= 1000;
	public static final long CATALOGO_OPCION_JERARQUIA_PUESTO = 22;
	public static final long CATALOGO_OPCION_PERSONAS_CARGO = 23;
	public static final long CATALOGO_OPCION_TIPO_CONTRATO = 24;
	public static final long CATALOGO_OPCION_ENTIDAD_FEDERATIVA = 25;
	public static final long CATALOGO_OPCION_MUNICIPIO = 26; 	
	public static final long CATALOGO_OPCION_TIPO_EMPRESA = 27;
	public static final long CATALOGO_OPCION_TIPO_SOCIEDAD = 79; 
	public static final long CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA = 28;	
	public static final long CATALOGO_OPCION_HORARIO_LABORAL = 29;
	public static final long CATALOGO_OPCION_COLONIA = 35;
	public static final long CATALOGO_OPCION_DOMICILIO_CANDIDATO = 2;
	public static final long CATALOGO_OPCION_TIPO_VIALIDAD = 91;
	public static final long CATALOGO_OPCION_TIPO_ASENTAMIENTO = 92;
	/** ESTATUS OFERTAS**/
	public static final long CATALOGO_OPCION_OFERTAS_VINCULADA = 16;
	public static final long CATALOGO_OPCION_OFERTAS_SELECCIONADO = 19;
	public static final long CATALOGO_OPCION_OFERTAS_POSTULADO = 5;
	public static final long CATALOGO_OPCION_OFERTAS_ENPROCESO = 20;	
	public static final long CATALOGO_OPCION_OFERTAS_CONTRATADO= 21;	
	public static final long CATALOGO_OPCION_OFERTAS_RECHAZADO = 22;	
	public static final long CATALOGO_OPCION_OFERTAS_DESVINCULADA = 18;	
	public static final long CATALOGO_OPCION_OFERTAS_INACTIVA = 2;
	public static final long CATALOGO_OPCION_VIGENCIA_OFERTA = 62;
	public static final long CATALOGO_OPCION_PROVINCIAS_CANADA = 63;
	public static final long CATALOGO_OPCION_RANGOS_EDAD 		= 64; // Rangos de edad CIL
	public static final long CATALOGO_OPCION_FUENTES 			= 65;	// Fuentes
	public static final long CATALOGO_OPCION_CANDIDATO_HABILIDAD= 66;
	public static final long CATALOGO_OPCION_SECTOR = 68;
	public static final long CATALOGO_OPCION_MOTIVO_DESACTIVACION_EMPRESA = 69;
	public static final long CATALOGO_OPCION_MOTIVO_DESACTIVACION_CANDIDATO = 67;
	
	public static final long CATALOGO_OPCION_ENTIDAD_FEDERATIVA_NACIDO_EXTRANJERO = 33; 
	
	/** Identificador del catalogo de Entidades **/
	public static final long CATALOGO_OPCION_ENTIDADES = 25;

	/** Identificador del catalogo de Perfiles **/
	public static final long CATALOGO_OPCION_PERFIL = 38;

	/** Identificador del catalogo de Tipos de Usuario **/
	public static final long CATALOGO_OPCION_TIPO_USUARIO = 39;

	/** Identificador del catalogo de Tipos de Telefono **/
	public static final long CATALOGO_OPCION_TIPO_TELEFONO = 41;

	/** Identificador del catalogo de Clave telefónica **/
	public static final long CATALOGO_MOTIVOS_NO_ACEPTACION = 50;

	public static final long CATALOGO_OPCION_CARRERA_TECNICA_COMERCIAL_BASICO=40;
	public static final long CATALOGO_OPCION_CARRERA_TECNICA_COMERCIAL_SUPERIOR=42;
	public static final long CATALOGO_OPCION_CARRERA_PROFESIONAL=43;
	public static final long CATALOGO_OPCION_CARRERA_MAESTRIA_DOCTORADO=44;
	public static final long CATALOGO_OPCION_SIN_ESPECIALIDAD=45;
	
	public static final long CATALOGO_OPCION_MOTIVO_NO_POSTULACION=111;
	
	/** Identificador de catálogos de productos **/
	public static final long CATALOGO_PTAT_TIPO_CULTIVO = 122;
	public static final long CATALOGO_PTAT_VERDURAS_LEGUMBRES_CAMPO=123;
	public static final long CATALOGO_PTAT_FLORES_CAMPO=124;
	public static final long CATALOGO_PTAT_ARBOLES=125;
	public static final long CATALOGO_PTAT_FRUTAS=126;
	public static final long CATALOGO_PTAT_TABACO=127;
	public static final long CATALOGO_PTAT_APICULTURA=128;
	public static final long CATALOGO_PTAT_VERDURAS_LEGUMBRES_VIVERO=129;
	public static final long CATALOGO_PTAT_FLORES_VIVERO=130;
	public static final long CATALOGO_PTAT_ARBOLES_VIVERO=131;
	public static final long CATALOGO_PTAT_GANADO=132;
	public static final long CATALOGO_PTAT_RASTRO=133;
	public static final long CATALOGO_PTAT_OTROS_RUBROS=134;
	public static final long CATALOGO_PTAT_CEREALES=135;
	
	public static final long CATALOGO_OPCION_CURSOS=140;
	public static final long CATALOGO_OPCION_NUMERO_SALARIOS=141;	
	public static final long CATALOGO_TIPO_RECURSO=100;
	public static final long CATALOGO_LENGUAS_INDIGENAS=150;
	public static final long CATALOGO_OPCION_BANCOS=151;
	public static final long CATALOGO_OPCION_PARENTESCO=152;
	public static final long CATALOGO_TIPO_AUTOMOVIL=153;
	public static final long CATALOGO_TIPO_LICENCIA=154;
	public static final long CATALOGO_OPCION_COMPLEXION=155;
	public static final long CATALOGO_SITUACION_LICENCIA=156;
	public static final long CATALOGO_OPCION_DOCUMENTACION=157;
	public static final long CATALOGO_ENFERMEDADES_CONTAGIOSAS=158;
	public static final long CATALOGO_ENFERMEDADES_CRONICAS=159;
	public static final long CATALOGO_LIMITACIONES_FISICAS=160;
	public static final long CATALOGO_ENFERMEDADES_LESIONES=161;
	
	/** Catalogos Nivel de reporte o Lineas de mando funcional*/
	public static final int CATALOGO_NIVEL_REPORTE1=175;
	public static final int CATALOGO_NIVEL_REPORTE2=176;
	
	/** Catalogo Certificacion */
	public static final int CATALOGO_CERTIFICACION=180;
	
	/** Catalogo Sistemas Especializados */
	public static final int CATALOGO_SISTEMAS=173;
	
	/** Catalogo Competencias transversales necesarias para el puesto */
	public static final long CATALOGO_OPCION_COMPETENCIAS_TRANSVERSALES = 433;
	
	/** Identificador del Origel de Registro de Usuarios del Portal **/
	public static final long ID_REGISTRO_PORTAL = 1;
	/** Identificador de Canada para el registro de ofertas **/
	public static final long ID_FUENTE_SFP = 2;	
	/** Identificador de Canada para el registro de ofertas **/
	public static final long ID_FUENTE_CANADA = 3;
	/** Identificador de SISNE para el registro de ofertas **/
	public static final long ID_FUENTE_SISNE = 4;
	/** Identificador del Usuario Anonimo usado para registro en bitacora **/
	public static final long ID_USUARIO_ANONIMO = 99999999;

	/** Valores para la aceptaci&oacute;n de t&eacute;rminos y condiciones del curriculum **/
	public enum ACEPTACION_TERMINOS_CURRICULUM {
		
		NO(0,"No"),
		SI(1,"Sí");
		
		private int idOpcion;
		private String opcion;
	
		private ACEPTACION_TERMINOS_CURRICULUM(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
	}
	
	/** L&iacute;mite para el tama&ntilde;o de la foto de candidato a guardar **/
	public static final int TAMANIO_MAXIMO_FOTO = 30720; 

	/** Tipos para la consulta de Registros por Validar **/
	public enum REGISTROS_TIPO_CONSULTA {ASIGNACION, ACTUALIZACION};
	
	/** Catálogo de Prestaciones **/
	public enum PRESTACIONES {
		
		DE_LEY(1,"Prestaciones de ley"),
		PUNTUALIDAD(2,"Bono por puntualidad"),
		PRODUCTIVIDAD(3,"Bono por productividad"),
		AHORRO(4,"Fondo de ahorro"),
		GASTOS_MEDICOS(5,"Seguro de gastos médicos mayores"),
		COMEDOR(6,"Servicio de comedor"),
		VALES_COMIDA(7,"Vales de comida"),
		VALES_DESPENSA(8,"Vales de despensa"),
		VALES_GASOLINA(9,"Vales de gasolina");
		
		private long idOpcion;
		private String opcion;
	
		private PRESTACIONES(long idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public long getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
		
		public static String getDescripcion(int idOpcion){
			String descripcion = null;			
			for (PRESTACIONES tipo : PRESTACIONES.values()){
				if (tipo.getIdOpcion() == idOpcion){
					descripcion = tipo.getOpcion();
					break;
				}
			}
			return descripcion;
		}
	}
	
	
	/** Catálogo de Jerarquía **/
	public enum JERARQUIA {
		//RBM1 TK1000 TK1001 se homologa con RU 
		/*
		DIRECCION(1,"Dirección"),
		MANDOS(2,"Mandos"),
		EMPLEADO(3,"Empleado"),
		TECNICO(4,"Técnico"),
		OPERATIVO(5,"Operativo"),
		PROFESIONISTA(6,"Profesionista");
		*/
		
		OPERARIO(1L, "Operario"), 
		TECNICO(2L, "Técnico"), 
		ADMINISTRATIVOS(3L,"Mandos medios administrativos"), 
		PRODUCCION(4L,"Mandos medios producción"),
		DIRECTIVOS(5L,"Directivos-Gerentes"),
		EMPLEADO(6,"Empleado");//Este no es parte de RU 
		
		
		private long idOpcion;
		private String opcion;
	
		private JERARQUIA(long idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public long getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
		
		public static String getDescripcion(int idOpcion){
			String descripcion = null;			
			for (JERARQUIA tipo : JERARQUIA.values()){
				if (tipo.getIdOpcion() == idOpcion){
					descripcion = tipo.getOpcion();
					break;
				}
			}
			return descripcion;
		}
	}
	
	/** Catálogo de experiencia **/
	public enum EXPERIENCIA {
		
		NINGUNA(1,"Ninguna"),
		MENOR_UNO(2,"6m - 1 año"),
		MENOR_DOS(3,"1 - 2 años"),
		MENOR_TRES(4,"2 - 3 años"),
		MENOR_CUATRO(5,"3 - 4 años"),
		MENOR_CINCO(6,"4 - 5 años"),
		MAS_CINCO(7,"Más de 5 años"),
		NO_REQUISITO(8,"No es requisito");
		
		private long idOpcion;
		private String opcion;
	
		private EXPERIENCIA(long idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public long getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}


		public static String getDescripcion(int idOpcion){
			String descripcion = null;			
			for (EXPERIENCIA tipo : EXPERIENCIA.values()){
				if (tipo.getIdOpcion() == idOpcion){
					descripcion = tipo.getOpcion();
					break;
				}
			}
			return descripcion;
		}

	}
	
	/** Catálogo de certificación de idiomas **/
	public enum CERTIFICACION {
		NINGUNO(37,"Ninguno", 1),
		INGLES(30,"Certificación en idioma Inglés", 5),
		FRANCES(31,"Certificación en idioma Francés", 4),
		ALEMAN(32,"Certificación en idioma Alemán", 2),
		ITALIANO(33,"Certificación en idioma Italiano", 6),
		CHINO(35,"Certificación en idioma Chino", 3),
		JAPONES(36,"Certificación en idioma Japonés", 7),
		PORTUGUES(89, "Certificación en idioma Portugués", 8);
		
		private long idOpcion;
		private String opcion;
		private long idIdioma;
	
		private CERTIFICACION(long idOpcion, String opcion, long idIdioma){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
			this.idIdioma = idIdioma;
		}

		public long getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
		public long getIdIdioma() {return idIdioma;}
		
		public static String getDescripcion(int idOpcion){
			String descripcion = null;			
			for (CERTIFICACION tipo : CERTIFICACION.values()){
				if (tipo.getIdOpcion() == idOpcion){
					descripcion = tipo.getOpcion();
					break;
				}
			}
			return descripcion;
		}	
		
		public static CERTIFICACION getCertificacion(long idIdioma){
			CERTIFICACION idioma = null;			
			for (CERTIFICACION tipo : CERTIFICACION.values()){
				if (tipo.idIdioma == idIdioma){
					idioma = tipo;
					break;
				}
			}			
			return idioma;
		}		
		
		public static Long getIdCertificacion(long idIdioma){
			Long idCertificacion = 1L;			
			for (CERTIFICACION tipo : CERTIFICACION.values()){
				if (tipo.idIdioma == idIdioma){
					idCertificacion = tipo.idOpcion;
					break;
				}
			}			
			return idCertificacion;
		} 		
	}
	
	/** Catálogo de idiomas **/
	public enum IDIOMAS {
		
		NINGUNO(1,"Ninguno"),
		ALEMAN(2,"Alemán"),
		CHINO(3,"Chino"),
		FRANCES(4,"Francés"),
		INGLES(5,"Inglés"),
		ITALIANO(6,"Italiano"),
		JAPONES(7,"Japonés"),
		PORTUGUES(8,"Portugués"),
		NO_REQUISITO(9,"No es requisito");
		
		private long idOpcion;
		private String opcion;
	
		private IDIOMAS(long idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public long getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
		
		public static String getDescripcion(int idOpcion){
			String descripcion = null;			
			for (IDIOMAS tipo : IDIOMAS.values()){
				if (tipo.getIdOpcion() == idOpcion){
					descripcion = tipo.getOpcion();
					break;
				}
			}
			return descripcion;
		}
		
		public static boolean isMember(long idOpcion){
			IDIOMAS[] idiomas = IDIOMAS.values();
			for(IDIOMAS idioma : idiomas){
				if(idioma.getIdOpcion() == idOpcion){
					return true;
				} 
			}
			return false;
		}

	}
	
	/** Catálogo de Dominio **/
	public enum DOMINIO {
		NINGUNO    (0, "Ninguno"),
		BASICO     (1, "Básico"),
		INTERMEDIO (2, "Intermedio"),
		AVANZADO   (3, "Avanzado");

		private long idOpcion;
		private String opcion;
	
		private DOMINIO(long idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public long getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
		
		public static String getDescripcion(int idOpcion){
			String descripcion = null;			
			for (DOMINIO tipo : DOMINIO.values()){
				if (tipo.getIdOpcion() == idOpcion){
					descripcion = tipo.getOpcion();
					break;
				}
			}
			return descripcion;
		}

	}
	
	/** Opción de rolar turno **/
	public enum ROLAR_TURNO {
		
		NO(1,"No"),
		SI(2,"Sí");
		
		private int idOpcion;
		private String opcion;
	
		private ROLAR_TURNO(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
		
		public static String getDescripcion(int idOpcion){
			String descripcion = null;			
			for (ROLAR_TURNO tipo : ROLAR_TURNO.values()){
				if (tipo.getIdOpcion() == idOpcion){
					descripcion = tipo.getOpcion();
					break;
				}
			}
			return descripcion;
		}				
	}

	/** Catalogo de Horario **/
	public enum HORARIO {
		OCHO_AM(1,"08:00"),
		OCHO_MEDIA_AM(2,"08:30"),
		NUEVE_AM(3,"09:00"),
		NUEVE_MEDIA_AM(4,"09:30"),
		DIEZ_AM(5,"10:00"),
		DIEZ_MEDIA_AM(6,"10:30"),
		ONCE_AM(7,"11:00"),
		ONCE_MEDIA_AM(8,"11:30"),
		DOCE_PM(9,"12:00"),
		DOCE_MEDIA_PM(10,"12:30"),
		UNA_PM(11,"13:00"),
		UNA_MEDIA_PM(12,"13:30"),
		DOS_PM(13,"14:00"),
		DOS_MEDIA_PM(14,"14:30"),
		TRES_PM(15,"15:00"),
		TRES_MEDIA_PM(16,"15:30"),
		CUATRO_PM(17,"16:00"),
		CUATRO_MEDIA_PM(18,"16:30"),
		CINCO_PM(19,"17:00"),
		CINCO_MEDIA_PM(20,"17:30"),
		SEIS_PM(21,"18:00"),
		SEIS_MEDIA_PM(22,"18:30"),
		SIETE_PM(23,"19:00"),
		SIETE_MEDIA_PM(24,"19:30"),
		OCHO_PM(25,"20:00"),
		OCHO_MEDIA_PM(26,"20:30"),
		NUEVE_PM(27,"21:00");
	
		private int idOpcion;
		private String opcion;
	
		private HORARIO(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {
			return idOpcion;
		}
		public String getOpcion() {
			return opcion;
		}
		
		public static String getDescripcion(int idOpcion){
			String descripcion = null;			
			for (HORARIO tipo : HORARIO.values()){
				if (tipo.getIdOpcion() == idOpcion){
					descripcion = tipo.getOpcion();
					break;
				}
			}
			return descripcion;
		}

	};
	
	/** Catalogo Edad requisito **/
	public enum EDAD_REQUISITO {NO(1), SI(2);
		private int idEdadReq;

		private EDAD_REQUISITO(int idEdadReq){
			this.idEdadReq = idEdadReq;
		}
		public int getIdOpcion() {
			return idEdadReq;
		}
	};
	

				
	//RBM1 TK1000 TK1001 
	/** Catálogo de Jerarquía **/
	public enum   TIPO_CONTRATO{
			
		INDETERMINADO(1,"Contrato por tiempo indeterminado"),
		DETERMINADO(2,"Contrato por tiempo determinado"),
		OBRA_DETERMINADA(3,"Contrato por obra determinada"),
		PERIODO_PRUEBA(4, "Contrato por periodo de prueba"),
		CAPACITACION_INICIAL(5, "Contrato por capacitación inicial"),
		TEMPORADA(6, "Contrato por relación de trabajo por temporada"),
		UNIDAD_TIEMPO(7, "Contrato por salario por unidad de tiempo");
			
		/* Catalogo antes RU
		INDETERMINADO(1,"Indeterminado"),
		DETERMINADO(2,"Determinado"),
		OBRA_DETERMINADA(3,"Obra determinada");
		*/		

		
		private long idOpcion;
		private String opcion;
	
		private TIPO_CONTRATO(long idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public long getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
		
		public static String getDescripcion(int idOpcion){
			String descripcion = null;			
			for (TIPO_CONTRATO tipo : TIPO_CONTRATO.values()){
				if (tipo.getIdOpcion() == idOpcion){
					descripcion = tipo.getOpcion();
					break;
				}
			}
			return descripcion;
		}
	}
	
	/** Catálogo de Tipo de empleo **/
	public enum TIPO_DISCAPACIDAD {
		
		NINGUNA(1,"Ninguna"),
		AUDITIVA(2,"Auditiva"),
		INTELECTUAL(3,"Intelectual"),
		MENTAL(4,"Mental"),
		MOTRIZ(5,"Motriz"),
		VISUAL(6,"Visual");
		
		private long idOpcion;
		private String opcion;
	
		private TIPO_DISCAPACIDAD(long idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public long getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
		
		public static String getDescripcion(int idOpcion){
			String descripcion = null;			
			for (TIPO_DISCAPACIDAD tipo : TIPO_DISCAPACIDAD.values()){
				if (tipo.getIdOpcion() == idOpcion){
					descripcion = tipo.getOpcion();
					break;
				}
			}
			return descripcion;
		}
	}
	
	/** Catálogo de Jerarquía **/
	public enum CAUSA_ORIGINA_OFERTA {
		
		EMPRESA_NUEVA(1,"Empresa nueva"),
		NECESIDADES_TEMPORALES(2,"Necesidades temporales de mano de obra"),
		PUESTO_NUEVO(3,"Puesto de nueva creación"),
		REPOSICION_PERSONAL(4,"Reposición de personal"),
		OTRA(5,"Otra");
		
		private long idOpcion;
		private String opcion;
	
		private CAUSA_ORIGINA_OFERTA(long idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public long getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
		
		public static String getDescripcion(int idOpcion){
			String descripcion = null;			
			for (CAUSA_ORIGINA_OFERTA tipo : CAUSA_ORIGINA_OFERTA.values()){
				if (tipo.getIdOpcion() == idOpcion){
					descripcion = tipo.getOpcion();
					break;
				}
			}
			return descripcion;
		}

	}
	
	public enum CATALOGO {
		TECNICA_COMERCIAL_BASICA(40),
		TECNICA_COMERCIAL_SUPERIOR(42),
		PROFESIONAL(43),
		MAESTRIA_DOCTORADO(44),
		SIN_ESPECIALIDAD(45),
		OCUPACION(21);
		private int idCatalogo;
		
		private CATALOGO(int idCatalogo){
			this.idCatalogo = idCatalogo;
		}
		public int getIdCatalogo() {return idCatalogo;}
	}
	
	/** Catalogo Principal **/
	public enum PRINCIPAL {NO(1), SI(2);
		private int idEdadReq;

		private PRINCIPAL(int idEdadReq){
			this.idEdadReq = idEdadReq;
		}
		public int getIdOpcion() {
			return idEdadReq;
		}
	};
	
	public static final String DIA_ACTIVO="1";
	public static final String DIA_INACTIVO="0";
	public static final int CARRERA_ESPECIALIDAD_PRINCIPAL=1;
	
	public static final long CATALOGO_OPCION_CARRERA_ESPECIALIDAD=9;
	public static final long CATALOGO_OPCION_SITUACION_ACADEMICA=10 ;
	public static final long CATALOGO_OPCION_IDIOMA=11;
	public static final int CAT_TIPO_CONOCIMIENTO=1;
	public static final int CAT_TIPO_HABILIDAD=2;
	public static final int CAT_TIPO_COMPETENCIA=3;
	public static final long CATALOGO_OPCION_EMPRESA_OFRECE_EMPLEO=27;
	public static final long CATALOGO_OPCION_NOMBRE_CONTACTO_EMPRESA=24;
	public static final long CATALOGO_OPCION_MEDIO_CONTACTO=24;
	public static final long CATALOGO_OPCION_DURACION_APROXIMADA=12;
	public static final long CATALOGO_OPCION_CARRERA_NINGUNA=1;
	
	public static final long CATALOGO_OPCION_CARRERAS_1=40;
	public static final long CATALOGO_OPCION_CARRERAS_2=42;
	public static final long CATALOGO_OPCION_CARRERAS_3=43;
	public static final long CATALOGO_OPCION_CARRERAS_4=44;

	/** Identificador del catalogo-opcion para NINGUNA **/
	public static final long CATALOGO_OPCION_NINGUNA = 2;
	public static final long CATALOGO_OPCION_OTRO_CERTIFICACION = 1;

	// TODO Eliminar tipo de requisito competencia
	public enum TIPO_REQUISITO {CONOCIMIENTO(1), HABILIDAD(2), COMPETENCIA(3);
		private long idTipoRequisito;
		
		private TIPO_REQUISITO(long idTipoRequisito){
			this.idTipoRequisito = idTipoRequisito;
		}
	
		public long getIdTipoRequisito(){
			return this.idTipoRequisito;
		}
	}
	
	/** Catálogo de Tipo de empleo **/
	public enum TIPO_TELEFONO {
		CELULAR(1,"Celular","044"),
		LOCAL(2,"Local","01"),
		/*NEXTEL(3,"Nextel"),
		OTRO(4,"Otro"),*/
		FIJO(5,"Fijo","01");
		
		private long idOpcion;
		private String opcion;
		private String acceso;
		
	
		private TIPO_TELEFONO(long idOpcion, String opcion, String acceso){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
			this.acceso = acceso;
		}

		public long getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
		public String getAcceso() {return acceso;}
	
		public static String getTipoTelefono(long idTipoTelefono){
			String descripcion = null;			

			for (TIPO_TELEFONO tipo : TIPO_TELEFONO.values()){
				if (tipo.getIdOpcion() == idTipoTelefono){
					descripcion = tipo.getOpcion();
					break;
				}
			}

			return descripcion;
		}
	}
	
	public enum ESTATUS_ACTIVAS {
		
		ALTA_NORMAL(1,"AN"),
		ALTA_CON_HOMONIMIA (2,"AH"),
		CURP_REACTIVADA (3,"CRA"),
		REGISTRO_CAMBIO_NO_AFECTADO (4,"AH"),
		REGISTRO_CAMBIO_AFECTADO (5,"RCN");
	
		private int idOpcion;
		private String opcion;
	
		private ESTATUS_ACTIVAS(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {
			return idOpcion;
		}
		public String getOpcion() {
			return opcion;
		}
	};

	public enum ESTATUS_DESACTIVAS {
		
		BAJA_POR_DOCUMENTO_APOCRIFO(1,"BAP"),
		BAJA_POR_DEFUNCION (2,"BD"),
		BAJA_POR_DUPLICIDAD (3,"BDA"),
		BAJA_POR_CAMBIO_EN_CURP (4,"BCC"),
		BAJA_NO_AFECTADP_A_CURP (5,"BCN");
	
		private int idOpcion;
		private String opcion;
	
		private ESTATUS_DESACTIVAS(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}
	
		public int getIdOpcion() {
			return idOpcion;
		}
		public String getOpcion() {
			return opcion;
		}
	};

	public enum ENTIDADES_FEDERATIVAS {
		
		AGUASCALIENTES	    (1, "Aguascalientes","aguascalientes"),
		BAJA_CALIFORNIA	    (2, "Baja California","baja-california-norte"),
		BAJA_CALIFORNIA_SUR	(3, "Baja California Sur","baja-california-sur"),
		CAMPECHE	        (4, "Campeche","campeche"),
		COAHUILA	        (5, "Coahuila","coahuila"),
		COLIMA	            (6, "Colima","colima"),
		CHIAPAS	            (7, "Chiapas","chiapas"),
		CHIHUAHUA	        (8, "Chihuahua","chihuahua"),
		DISTRITO_FEDERAL	(9, "Distrito Federal","distrito-federal"),
		DURANGO	            (10, "Durango","durango"),
		GUANAJUATO	        (11, "Guanajuato","guanajuato"),
		GUERRERO	        (12, "Guerrero","guerrero"),
		HIDALGO	            (13, "Hidalgo","hidalgo"),
		JALISCO	            (14, "Jalisco","jalisco"),
		MEXICO	            (15, "México","estado-de-mexico"),
		MICHOACAN	        (16, "Michoacán","michoacan"),
		MORELOS	            (17, "Morelos","morelos"),
		NAYARIT	            (18, "Nayarit","nayarit"),
		NUEVO_LEON	        (19, "Nuevo León","nuevo-leon"),
		OAXACA	            (20, "Oaxaca","oaxaca"),
		PUEBLA	            (21, "Puebla","puebla"),
		QUERETARO	        (22, "Querétaro","queretaro"),
		QUINTANA_ROO	    (23, "Quintana Roo","quintana-roo"),
		SAN_LUIS_POTOSI	    (24, "San Luis Potosí","san-luis-potosi"),
		SINALOA	            (25, "Sinaloa","sinaloa"),
		SONORA	            (26, "Sonora","sonora"),
		TABASCO	            (27, "Tabasco","tabasco"),
		TAMAULIPAS	        (28, "Tamaulipas","tamaulipas"),
		TLAXCALA	        (29, "Tlaxcala","tlaxcala"),
		VERACRUZ	        (30, "Veracruz","veracruz"),
		YUCATAN	            (31, "Yucatán","yucatan"),
		ZACATECAS	        (32, "Zacatecas","zacatecas"),
		NACIDO_EN_EL_EXTRANJERO	(33, "Nacido en el extranjero","exterior");

		private int idEntidad;
		private String descripcion;
		private String abreviaturaZonaJobs;
	
		private ENTIDADES_FEDERATIVAS (int idEntidad, String descripcion, String abreviaturaZonaJobs){
			this.idEntidad= idEntidad;
			this.descripcion = descripcion;
			this.abreviaturaZonaJobs = abreviaturaZonaJobs;
		}	

		public int getIdEntidad() {
			return idEntidad;
		}
		public String getDescripcion() {
			return descripcion;
		}
		public String getAbreviaturaZonaJobs() {
			return abreviaturaZonaJobs;
		}
		
		public static ENTIDADES_FEDERATIVAS getEntidad(int idEntidad){	
			ENTIDADES_FEDERATIVAS entidad = null;

			for (ENTIDADES_FEDERATIVAS entidadfor : ENTIDADES_FEDERATIVAS.values()){
				if (entidadfor.getIdEntidad() == idEntidad){
					entidad = entidadfor;
					break;
				}
			}

			return entidad;
		}
	};
	
	public enum ENTIDADES_FEDERATIVAS_RSS{
		
		AGUASCALIENTES 	    (1,"AS","AGUASCALIENTES", "AGS"),
		BAJA_CALIFORNIA	    (2,"BC","BAJA CALIFORNIA", "BC"),
		BAJA_CALIFORNIA_SUR (3,"BS","BAJA CALIFORNIA SUR", "BCS"),
		CAMPECHE	        (4,"CC","CAMPECHE", "CAMP"),
		CHIAPAS	            (5,"CS","CHIAPAS", "CHIS"),
		CHIHUAHUA	        (6,"CH","CHIHUAHUA", "CHIH"),
		COAHUILA			(7,"CL","COAHUILA", "COAH"),
		COLIMA				(8,"CM","COLIMA", "COL"),
		DISTRITO_FEDERAL	(9,"DF","DISTRITO FEDERAL", "DF"),
		DURANGO 			(10,"DG","DURANGO", "DGO"),
		GUANAJUATO			(11,"GT","GUANAJUATO", "GTO"),
		GUERRERO 			(12,"GR","GUERRERO", "GRO"),
		HIDALGO 			(13,"HG","HIDALGO", "HGO"),
		JALISCO 			(14,"JC","JALISCO", "JAL"),
		MEXICO				(15,"MC","MEXICO", "MEX"),
		MICHOACAN			(16,"MN","MICHOACAN", "MICH"),
		MORELOS				(17,"MS","MORELOS", "MOR"),
		NAYARIT 			(18,"NT","NAYARIT", "NAY"),
		NUEVO_LEON			(19,"NL","NUEVO LEON", "NL"),
		OAXACA 				(20,"OC","OAXACA", "OAX"),
		PUEBLA 				(21,"PL","PUEBLA", "PUE"),
		QUERETARO 			(22,"QT","QUERETARO", "QRO"),
		QUINTANA_ROO 		(23,"QR","QUINTA ROO", "QROO"),
		SAN_LUIS_POTOSI 	(24,"SP","SAN LUIS POTOSÍ", "SLP"),
		SINALOA				(25,"SL","SINALOA", "SIN"),
		SONORA				(26,"SR","SONORA", "SON"),
		TABASCO				(27,"TC","TABASCO", "TAB"),
		TLAXCALA			(28,"TL","TLAXCALA", "TLAX"),
		TAMAULIPAS			(29,"TS","TAMAULIPAS", "TAMPS"),
		VERACRUZ			(30,"VZ","VERACRUZ", "VER"),
		YUCATAN				(31,"YN","YUCATAN", "YUC"),
		ZACATECAS			(32,"ZS","ZACATECAS", "ZAC");	

		private int idOpcion;
		private String opcion;
		private String nombre;
		private String abreviatura;
	
		private ENTIDADES_FEDERATIVAS_RSS(int idOpcion, String opcion, String nombre, String abreviatura){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
			this.nombre = nombre;
			this.abreviatura = abreviatura;
		}
	
		public static int getIdOpcion(String abreviatura){
			int id = 0;			
			for (ENTIDADES_FEDERATIVAS_RSS tipo : ENTIDADES_FEDERATIVAS_RSS.values()){
				if (tipo.getAbreviatura().equalsIgnoreCase(abreviatura)){
					id = tipo.getIdOpcion();
					break;
				}
			}
			return id;
		}

		public static ENTIDADES_FEDERATIVAS_RSS getEntidad(int idEntidad){	
			ENTIDADES_FEDERATIVAS_RSS entidad = null;

			for (ENTIDADES_FEDERATIVAS_RSS entidadfor : ENTIDADES_FEDERATIVAS_RSS.values()){
				if (entidadfor.getIdOpcion() == idEntidad){
					entidad = entidadfor;
					break;
				}
			}

			return entidad;
		}

		public int getIdOpcion() {
			return idOpcion;
		}
		public String getOpcion() {
			return opcion;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getNombre() {
			return nombre;
		}
		
		public void setAbreviatura(String abreviatura) {
			this.abreviatura = abreviatura;
		}

		public String getAbreviatura() {
			return abreviatura;
		}
	};
	
	
	public enum ETIQUETAS_SFP {
		   
        XML_SFP ("ns:return", "ax21:idVacante", "ax21:idEmpresa", "ax21:puesto","ax21:idAreaLaboral","ax21:idOcupacion","ax21:funciones",
        "ax21:salario", "ax21:fechaAlta", "ax21:fechaVencimiento", "ax21:idEscolaridad", "ax21:idSituacion", "ax21:observaciones", 
        "ax21:idEspecialidad", "ax21:habilidad", "ax21:idExperiencia", "ax21:idEntidad", "ax21:idMunicipio", "ax21:calle", 
        "ax21:codigoPostal", "ax21:numeroExt");
        //"ns:horarioDe", "ns:horarioA", 
        private String tagRegistro;
        private String tagVacante;
        private String tagEmpresa;
        private String tagPuesto;
        private String tagAreaLaboral;
        private String tagOcupacion;
        private String tagFunciones;
        private String tagSalario;
        private String tagFechaAlta;
        private String tagFechaVencimiento;
        private String tagEscolaridad;
        private String tagSituacion;
        private String tagObservaciones;
        private String tagEspecialidad;
        private String tagHabilidad;
        private String tagExperiencia;
        private String tagEntidad;
		private String tagMunicipio;
        private String tagCalle;
        private String tagCodigoPostal;
        private String tagNumeroExt;

        private ETIQUETAS_SFP(String registro, String vacante, String empresa, String puesto, String areaLaboral, String ocupacion,
        		String funciones, String salario, String fechaAlta, String fechaVencimiento, String escolaridad, String situacion, 
        		String observaciones, String especialidad, String habilidad, String experiencia, String entidad, String municipio,
        		String calle, String codigoPostal, String numeroExt){
                 this.tagRegistro = registro;
                 this.tagVacante = vacante;
                 this.tagEmpresa = empresa;
                 this.tagPuesto = puesto;
                 this.tagAreaLaboral = areaLaboral;
                 this.tagOcupacion = ocupacion;
                 this.tagFunciones = funciones;
                 this.tagSalario = salario;
                 this.tagFechaAlta = fechaAlta;
                 this.tagFechaVencimiento = fechaVencimiento;
                 this.tagEscolaridad = escolaridad;
                 this.tagSituacion = situacion;
                 this.tagObservaciones = observaciones;
                 this.tagEspecialidad = especialidad;
                 this.tagHabilidad = habilidad;
                 this.tagExperiencia = experiencia;
                 this.tagEntidad = entidad;
                 this.tagMunicipio = municipio;
                 this.tagCalle = calle;
                 this.tagCodigoPostal = codigoPostal;
                 this.tagNumeroExt = numeroExt;
        }

		public String getTagRegistro() {
			return tagRegistro;
		}

		public void setTagRegistro(String tagRegistro) {
			this.tagRegistro = tagRegistro;
		}

		public String getTagVacante() {
			return tagVacante;
		}

		public void setTagVacante(String tagVacante) {
			this.tagVacante = tagVacante;
		}

		public String getTagEmpresa() {
			return tagEmpresa;
		}

		public void setTagEmpresa(String tagEmpresa) {
			this.tagEmpresa = tagEmpresa;
		}

		public String getTagPuesto() {
			return tagPuesto;
		}

		public void setTagPuesto(String tagPuesto) {
			this.tagPuesto = tagPuesto;
		}

		public String getTagAreaLaboral() {
			return tagAreaLaboral;
		}

		public void setTagAreaLaboral(String tagAreaLaboral) {
			this.tagAreaLaboral = tagAreaLaboral;
		}

		public String getTagOcupacion() {
			return tagOcupacion;
		}

		public void setTagOcupacion(String tagOcupacion) {
			this.tagOcupacion = tagOcupacion;
		}

		public String getTagFunciones() {
			return tagFunciones;
		}

		public void setTagFunciones(String tagFunciones) {
			this.tagFunciones = tagFunciones;
		}

		public String getTagSalario() {
			return tagSalario;
		}

		public void setTagSalario(String tagSalario) {
			this.tagSalario = tagSalario;
		}

		public String getTagFechaAlta() {
			return tagFechaAlta;
		}

		public void setTagFechaAlta(String tagFechaAlta) {
			this.tagFechaAlta = tagFechaAlta;
		}

		public String getTagFechaVencimiento() {
			return tagFechaVencimiento;
		}

		public void setTagFechaVencimiento(String tagFechaVencimiento) {
			this.tagFechaVencimiento = tagFechaVencimiento;
		}

		public String getTagEscolaridad() {
			return tagEscolaridad;
		}

		public void setTagEscolaridad(String tagEscolaridad) {
			this.tagEscolaridad = tagEscolaridad;
		}

		public String getTagSituacion() {
			return tagSituacion;
		}

		public void setTagSituacion(String tagSituacion) {
			this.tagSituacion = tagSituacion;
		}

		public String getTagObservaciones() {
			return tagObservaciones;
		}

		public void setTagObservaciones(String tagObservaciones) {
			this.tagObservaciones = tagObservaciones;
		}

		public String getTagEspecialidad() {
			return tagEspecialidad;
		}

		public void setTagEspecialidad(String tagEspecialidad) {
			this.tagEspecialidad = tagEspecialidad;
		}

		public String getTagHabilidad() {
			return tagHabilidad;
		}

		public void setTagHabilidad(String tagHabilidad) {
			this.tagHabilidad = tagHabilidad;
		}

		public String getTagExperiencia() {
			return tagExperiencia;
		}

		public void setTagExperiencia(String tagExperiencia) {
			this.tagExperiencia = tagExperiencia;
		}
		public String getTagEntidad() {
			return tagEntidad;
		}

		public void setTagEntidad(String tagEntidad) {
			this.tagEntidad = tagEntidad;
		}

		public String getTagMunicipio() {
			return tagMunicipio;
		}

		public void setTagMunicipio(String tagMunicipio) {
			this.tagMunicipio = tagMunicipio;
		}

		public String getTagCalle() {
			return tagCalle;
		}

		public void setTagCalle(String tagCalle) {
			this.tagCalle = tagCalle;
		}

		public String getTagCodigoPostal() {
			return tagCodigoPostal;
		}

		public void setTagCodigoPostal(String tagCodigoPostal) {
			this.tagCodigoPostal = tagCodigoPostal;
		}

		public String getTagNumeroExt() {
			return tagNumeroExt;
		}

		public void setTagNumeroExt(String tagNumeroExt) {
			this.tagNumeroExt = tagNumeroExt;
		}

}

	/** Tipo oferta por perfil **/
	public enum MOSTRAR_OFERTA_PERFIL {ALL(1), TOP(2);
		private int tipoOfertaPerfil;

		private MOSTRAR_OFERTA_PERFIL(int tipoOfertaPerfil){
			this.tipoOfertaPerfil = tipoOfertaPerfil;
		}
		public int getTipoOfertaPerfil() {
			return tipoOfertaPerfil;
		}
	};


	/**
	 * N&uacute;mero m&aacute;ximo de registros mostrados en el detalle del Candidato.
	 */
	public static final short CONOC_HABIL_MAX_RESULTADOS = 3;

	public enum TIPO_BUSQUEDA {BUSQUEDA_ESPECIFICA, BUSQUEDA_OCUPATE, BUSQUEDA_OCUPATE_WS, BUSQUEDA_POR_PERFIL, BUSQUEDA_OTRAS_BOLSAS}
	
	/** Catálogo de Jerarquía **/
	public enum RAZON_BUSQUEDA {CAMBIO_TRABAJO(2,"Cambio de trabajo"),
	                            MAS_EMPLEO(3,"Tener más de un empleo"),
	                            EXTRABAJADOR_LYFC(11, "Extrabajador de lyfc"),
	                            NUNCA_TRABAJO(12, "Nunca ha trabajado"),
	                            CERRO_FUENTE(13, "Cerró/quebró la fuente de trabajo"),
	                            AJUSTE_PERSONAL(14, "Ajuste de personal"),
	                            DESPIDO(15, "Despido"),
	                            TERMINO_CONTRATO(16, "Término de contrato"),
	                            RETIRO_VOLUNTARIO(17, "Retiro voluntario"),
	                            OTRO(18, "Otro");
		private long idOpcion;
		private String opcion;
	
		private RAZON_BUSQUEDA(long idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}
		public long getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
		
		public static boolean isMember(long idOpcion){
			RAZON_BUSQUEDA[] razones = RAZON_BUSQUEDA.values();
			for(RAZON_BUSQUEDA razon : razones){
				if(razon.getIdOpcion() == idOpcion){
					return true;
				} 
			}
			return false;
		}			
	}
	
	public static final String CANAL_ESTUDIANTES = "ESTUDIANTES";
	public static final String CANAL_EGRESADOS = "EGRESADOS";
	public static final String CANAL_MAYORES = "MAYORES";
	public static final String CANAL_CAPACIDADES = "CAPACIDADES";
	public static final String OCUPATE = "OCUPATE";
	
	public static final short EXPERIENCIA_EGRESADOS = 3;
	public static final short EDAD_MINIMA = 50;

	public static final short NUM_MAX_POSTULACIONES_RECIENTES = 3;
	 
	public static final String URL_LOGO_PORTAL = "http://empleo.gob.mx/images/logo_portal_del_empleo2.gif";

	/**
	 * Cat&aacute;logo de bolsas de trabajo
	 */
	public enum BOLSA_TRABAJO {
		PORTAL_EMPLEO (1, "Portal del Empleo"),
		TRABAJA_EN (2, "Trabaja en"),
		CANADA (3, "Movilidad Laboral México-Canadá"),
		SNE (4, "Bolsa de Trabajo SNE");
		
		private long idOpcion;
		private String opcion;
		
		private BOLSA_TRABAJO(long idOpcion, String opcion) {
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}
		public long getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
	}
	
	public enum VALORES_SFP {
		TIPO_EMPLEO(1),
		DIAS_LABORALES("0111110"),
		HORA_INI("9"),
		HORA_FIN("18"),
		ROLAR_TURNO(1),
		EMPRESA_OFRECE("1"),
		ID_TIPO_CONTRATO(1),
		ID_JERARQUIA(2),
		NUMERO_PLAZAS(1),
		LIMITE_POSTULANTES(1),
		ID_DISCAPACIDAD(1),
		ID_CAUSA_VACANTE(5),
		DISPONIBILIDAD_VIAJAR(0),
		DISPONIBILIDAD_RADICAR(0),
		EDAD_REQUISITO(1),
		GENERO(3),
		MAPA_UBICACION("http://maps.google.com/maps?f=q&source=s_q&hl=es&geocode=&aq=4&sll=19.570142,-99.074707&sspn=3.032633,6.778564&ll=19.359616,-99.183776&spn=0.011337,0.016458&z=16"),
		ID_DURACION_APROX(1),
		FUENTE(2),
		CONTACTO_TEL(1),
		CONTACTO_CORREO(1);

		private int valorNumerico;
		private String valorCadena;
		
		private VALORES_SFP(int numero) {
			this.valorNumerico = numero;
		}
		private VALORES_SFP(String cadena) {
			this.valorCadena = cadena;
		}
		public int getValorNumerico() {
			return valorNumerico;
		}
		public void setValorNumerico(int valorNumerico) {
			this.valorNumerico = valorNumerico;
		}
		public String getValorCadena() {
			return valorCadena;
		}
		public void setValorCadena(String valorCadena) {
			this.valorCadena = valorCadena;
		}
	}
	
	public enum BOLSAS_TRABAJO_EXTERNAS {
		OCC						(1, "OCC"),
		BUMERAN					(2, "Bumeran"),
		MANPOWER				(3, "Manpower"),
		ADECCO					(4, "Adecco"),
		HISPAVISTA				(5, "TrabajosMX"),
		ZONAJOBS				(6, "Turijobs"),
		TURIJOBS				(7, "Zona Jobs"),
		ADMINISTRACION_PUBLIC	(8, "Ofertas en la administración pública"),
		DISCAPACIDAD_EMPLEO		(9, "Discapacidad y empleo"),
		SUPERCHAMBA				(10, "SuperChamba");

		private int valor;
		private String nombre;
		
		private BOLSAS_TRABAJO_EXTERNAS(int valor, String nombre){
			this.valor = valor;
			this.nombre = nombre;
		}

		public static BOLSAS_TRABAJO_EXTERNAS getBolsa(int valor){	
			BOLSAS_TRABAJO_EXTERNAS bolsa = null;

			for (BOLSAS_TRABAJO_EXTERNAS item : BOLSAS_TRABAJO_EXTERNAS.values()){
				if (item.getValor() == valor){
					bolsa = item;
					break;
				}
			}

			return bolsa;
		}

		
		public int getValor() {
			return valor;
		}

		public void setValor(int valor) {
			this.valor = valor;
		}
		public String getNombre(){
			return this.nombre;
		}
	}
	
	/** Catálogo de Tipo de empleo **/
	public enum ESTILO_CV {
		
		SIMPLE(1,"Elegante"),
		CLASICO(2,"Clásico"),
		MODERNO(3,"Moderno");
		
		private int idOpcion;
		private String opcion;
	
		private ESTILO_CV(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
	}
	
	
	/** Catálogo de Tipo de atencion **/
	public enum TIPO_SERVICIO {
		
		ACTUALIZAR_CV(1,"Acceso o captura de curriculum"),
		IMPRESION(2,"Impresión"),
		TELEFONO(3,"Llamada telefónica"),
		FOTOCOPIAS(4,"Fotocopiado"),
		OTRAS_BOLSAS(5,"Consulta a otras bolsas de trabajo"),
		ACTIVIDAD_PORTAL(6,"Acceso al Portal del Empleo"); 
		
		
		private int idOpcion;
		private String opcion;
	
		private TIPO_SERVICIO(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
	}
		
	/** Catálogo de Tipo de atención **/
	public enum CATEGORIA_SUGERENCIA {
		
		QUEJA(1,"Queja"),
		DUDA(2,"Duda"),
		SUGERENCIA(3,"Sugerencia"),
		COMENTARIO(4,"Comentario"),;
		
		private int idOpcion;
		private String opcion;
	
		private CATEGORIA_SUGERENCIA(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
	}	
	
	
	public static final int SESION_ACTIVA = 1;	
	public static final int SESION_INACTIVA = 0;

	public static final int AUTENTICADO_REQUERIDO = 1;	
	public static final int AUTENTICADO_NO_REQUERIDO = 0;
	
	public static final int MOTIVO_DETALLE_NO_REQUERIDO = 1;
	public static final int MOTIVO_DETALLE_REQUERIDO = 2;

	public static final String PROPERTIES_RUTA_DESTINO_OFERTAS_SMS = "app.ofertasms.archivo.ruta";

	public static final String PROPERTIES_ARCHIVO_SMS_NOMBRE = "app.ofertasms.archivo.nombre";
	
	/** Catálogo de el candidato desea ver sus datos de certificaciones en el apartado Mis Datos -> Escolaridad **/
	public enum PUBLICAR_ESTANDARES {
		
		NO(1,"No"),
		SI(2,"Sí");
		
		private long idOpcion;
		private String opcion;
	
		private PUBLICAR_ESTANDARES(long idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public long getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
	}

	public enum DECISION {
		
		NO(1,"No"),
		SI(2,"Sí");
		
		private long idOpcion;
		private String opcion;
	
		private DECISION(long idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public long getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
	}

	/** Catálogo de deseo de publicar estándares **/
	public enum VOLVER_PREGUNTAR {
		
		NO(1,"No"),
		SI(2,"Sí");
		
		private long idOpcion;
		private String opcion;
	
		private VOLVER_PREGUNTAR(long idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public long getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
	}	

	/** Catálogo de deseo que los datos de certificaciones se muestren en el detalle de candidato cuando una empresa los consulta **/
	public enum PUBLICAR_EN_PERFIL {
		
		NO(1,"No"),
		SI(2,"Sí");
		
		private long idOpcion;
		private String opcion;
	
		private PUBLICAR_EN_PERFIL(long idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public long getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
	}		
	
	/** Número de ofertas que se muestran en el cuadro Postulantes a mis ofertas de Mi Espacio Empresa **/
	public static final int NUMERO_OFERTAS_RECIENTES = 5;
	
	/** Número de postulaciones que se muestran en el cuadro Postulaciones Recientes de Mi Espacio Empresa **/
	public static final int NUM_ULTIMAS_POSTULACIONES = 5;

	/** Numero de Ofertas recientes **/
	public static final short NUM_MAX_OFERTAS_RECIENTES = 4;
	
	/** Rangos de salarios **/
	public enum RANGO_SALARIOS {
		
		MINIMUM(1,"Menor a $5,000"),
		MINIMUM_MIDDLE(2,"De $5,001 a $10,000"),
		MIDDLE(3,"De $10,001 a $15,000"),
		MAXIMUM_MIDDLE(4, "De $15,001 a $25,000"),
		MAXIMUM(5, "De $25,000 a $40,000"),
		OVER_MAXIMUM(6, "Más de 40,000");
		
		private int idOpcion;
		private String opcion;
	
		private RANGO_SALARIOS(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
	}
	
	/** Rangos de edades **/
	public enum RANGO_EDADES {
		
		YOUNG(1,"Menores a 21 años"),
		YOUNG_ADULT(2,"De 21 a 29 años"),
		ADULT(3,"De 30 a 39 años"),
		CONTEMPORARY_ADULT(4, "De 40 a 49 años"),
		SENIOR(5, "50 años o más");
		
		private int idOpcion;
		private String opcion;
	
		private RANGO_EDADES(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
	}
	
	
	/** Regiones **/
	public enum REGION {
		
		NOROESTE(1,"Noroeste",
				ENTIDADES_FEDERATIVAS.BAJA_CALIFORNIA,
				ENTIDADES_FEDERATIVAS.BAJA_CALIFORNIA_SUR,
				ENTIDADES_FEDERATIVAS.SONORA,
				ENTIDADES_FEDERATIVAS.SINALOA,
				ENTIDADES_FEDERATIVAS.CHIHUAHUA,
				ENTIDADES_FEDERATIVAS.DURANGO),
				
	NORESTE(2,"Noreste",
			  ENTIDADES_FEDERATIVAS.COAHUILA,
			  ENTIDADES_FEDERATIVAS.NUEVO_LEON,
			  ENTIDADES_FEDERATIVAS.TAMAULIPAS,
			  ENTIDADES_FEDERATIVAS.ZACATECAS,
			  ENTIDADES_FEDERATIVAS.SAN_LUIS_POTOSI),
			  
	CENTRO_OCCIDENTE(3,"Centro-Occidente",
					ENTIDADES_FEDERATIVAS.NAYARIT, 
					ENTIDADES_FEDERATIVAS.AGUASCALIENTES,
					ENTIDADES_FEDERATIVAS.JALISCO, 
					ENTIDADES_FEDERATIVAS.GUANAJUATO,
					ENTIDADES_FEDERATIVAS.COLIMA, 
					ENTIDADES_FEDERATIVAS.MICHOACAN),
					
	CENTRO(4, "Centro",
			ENTIDADES_FEDERATIVAS.GUERRERO,
			ENTIDADES_FEDERATIVAS.QUERETARO,
			ENTIDADES_FEDERATIVAS.HIDALGO, 
			ENTIDADES_FEDERATIVAS.MEXICO,
			ENTIDADES_FEDERATIVAS.DISTRITO_FEDERAL, 
			ENTIDADES_FEDERATIVAS.MORELOS,
			ENTIDADES_FEDERATIVAS.PUEBLA, 
			ENTIDADES_FEDERATIVAS.TLAXCALA,
			ENTIDADES_FEDERATIVAS.VERACRUZ),

	SUR_SURESTE(5, "Sur-Sureste",
				ENTIDADES_FEDERATIVAS.OAXACA, 
				ENTIDADES_FEDERATIVAS.CHIAPAS,
				ENTIDADES_FEDERATIVAS.TABASCO, 
				ENTIDADES_FEDERATIVAS.CAMPECHE,
				ENTIDADES_FEDERATIVAS.YUCATAN, 
				ENTIDADES_FEDERATIVAS.QUINTANA_ROO);
		
		private int idOpcion;
		private String opcion;
		private List<ENTIDADES_FEDERATIVAS> entidades;
		
		private REGION(int idOpcion, String opcion, ENTIDADES_FEDERATIVAS... entidades){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
			
			this.entidades = new ArrayList<ENTIDADES_FEDERATIVAS>();
			
			if (entidades!=null){
				for (ENTIDADES_FEDERATIVAS e : entidades){
					this.entidades.add(e);
				}
			}
		}

		public int getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
		public List<ENTIDADES_FEDERATIVAS> getEntidades(){return this.entidades;}
		
		public static REGION getRegion(int idRegion){	
			REGION region = null;

			for (REGION entidadfor : REGION.values()){
				if (entidadfor.getIdOpcion() == idRegion){
					region = entidadfor;
					break;
				}
			}

			return region;
		}
		
	}	

	/** Catálogo de aplicaciones **/
	public enum SISTEMAS_PORTAL {
		PORTAL(1,"Portal Empleo 2.0"),
		CIL(2,"Centros de Intermediación Laboral");
		
		private int idOpcion;
		private String opcion;
	
		private SISTEMAS_PORTAL(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
		
	}
	
	/** Catálogo de Aplicacion **/
	public enum APLICACION {
		
		PORTAL_DEL_EMPLEO		(1,"Portal del Empleo", 1),
		ADMINISTRACION_PUBLICA	(2,"Administración pública", 1),
		MEXICO_CANADA			(3,"México-Canadá", 1),		
		SISNE					(4,"SISNE", 2),
		SNETEL					(5,"SNETEL", 2),
		ADMINISTRADOR			(6,"Administrador portal del empleo", 2),
		SIISNE					(7,"SIISNE",1),
		FERIAS					(8,"Ferias",1),
		ABRIENDO_ESPACIOS		(9,"Abriendo Espacios",1);

		private int idOpcion;
		private String opcion;
		private int esAdministrable;
		
		private APLICACION(int idOpcion, String opcion, int esAdministrable){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
			this.esAdministrable = esAdministrable;
		}

		public int getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
		public int getEsAdministrable() {return esAdministrable;}
		
		public static APLICACION getAplicacion(int idOpcion){
			APLICACION item = null;			
			for (APLICACION row : APLICACION.values()){
				if (row.getIdOpcion() == idOpcion){
					item = row;
					break;
				}
			}
			return item;
		}
		
		public static List<APLICACION> getAppAdministrables(int esAdministrable) {
			List<APLICACION> lst = new ArrayList<APLICACION>();
			for (APLICACION row : APLICACION.values()) {
				if (row.getEsAdministrable() == esAdministrable) {
					lst.add(row);
				}
			}
			return lst;
		}
	}	
	
	public enum VIGENCIA_OFERTA {
		
		UN_DIA(1,1),
		TRES_DIAS(2,3),
		CINCO_DIAS(3,5),
		SIETE_DIAS(4,7),
		DIEZ_DIAS(5,10),
		QUINCE_DIAS(6,15),
		VEINTIUN_DIAS(7,21),
		UN_MES(8,30),
		TRES_MESES(9,90);
		
		private long idVigencia;
		private int dias;

		private VIGENCIA_OFERTA(long idVigencia, int dias){
			this.idVigencia = idVigencia;
			this.dias = dias;
		}

		public long getIdVigencia() {return idVigencia;}
		public int getDias() {return dias;}
		
		public static int getDias(int idVigencia){
			int dias = 0;			
			for (VIGENCIA_OFERTA tipo : VIGENCIA_OFERTA.values()){
				if (tipo.getIdVigencia() == idVigencia){
					dias = tipo.getDias();
					break;
				}
			}
			return dias;
		}
	}
	
	 /**Filtro situacion academica de acuerdo al grado academico **/
	public static final long[] SIN_INSTRUCCION = {SITUACION_ACADEMICA.ESTUDIANTE.getIdOpcion(),SITUACION_ACADEMICA.DIPLOMA_CERTIFICADO.getIdOpcion(),SITUACION_ACADEMICA.TRUNCA.getIdOpcion(),SITUACION_ACADEMICA.PASANTE.getIdOpcion(),SITUACION_ACADEMICA.TITULADO.getIdOpcion()};
	public static final long[] LEER_Y_ESCRIBIR = {SITUACION_ACADEMICA.ESTUDIANTE.getIdOpcion(),SITUACION_ACADEMICA.DIPLOMA_CERTIFICADO.getIdOpcion(),SITUACION_ACADEMICA.TRUNCA.getIdOpcion(),SITUACION_ACADEMICA.PASANTE.getIdOpcion(),SITUACION_ACADEMICA.TITULADO.getIdOpcion()};
	public static final long[] PRIMARIA = {SITUACION_ACADEMICA.ESTUDIANTE.getIdOpcion(),SITUACION_ACADEMICA.TRUNCA.getIdOpcion(),SITUACION_ACADEMICA.PASANTE.getIdOpcion(),SITUACION_ACADEMICA.TITULADO.getIdOpcion()};
	public static final long[] SECUNDARIA_SEC_TECNICA = {SITUACION_ACADEMICA.ESTUDIANTE.getIdOpcion(),SITUACION_ACADEMICA.TRUNCA.getIdOpcion(),SITUACION_ACADEMICA.PASANTE.getIdOpcion(),SITUACION_ACADEMICA.TITULADO.getIdOpcion()};
	public static final long[] CARRERA_COMERCIAL = {1,SITUACION_ACADEMICA.PASANTE.getIdOpcion(),SITUACION_ACADEMICA.TITULADO.getIdOpcion()};
	public static final long[] CARRERA_TECNICA = {1,SITUACION_ACADEMICA.PASANTE.getIdOpcion(),SITUACION_ACADEMICA.TITULADO.getIdOpcion()};
	public static final long[] PROFESIONAL_TECNICO = {1,SITUACION_ACADEMICA.PASANTE.getIdOpcion()};
	public static final long[] PREPA_VOCACIONAL = {1,SITUACION_ACADEMICA.PASANTE.getIdOpcion(),SITUACION_ACADEMICA.TITULADO.getIdOpcion()};
	public static final long[] T_SUPERIOR_UNIVERSITARIO = {1,SITUACION_ACADEMICA.DIPLOMA_CERTIFICADO.getIdOpcion()};
	public static final long[] LICENCIATURA = {1,SITUACION_ACADEMICA.DIPLOMA_CERTIFICADO.getIdOpcion()};
	public static final long[] MAESTRIA = {1,SITUACION_ACADEMICA.DIPLOMA_CERTIFICADO.getIdOpcion()};
	public static final long[] DOCTORADO = {1,SITUACION_ACADEMICA.DIPLOMA_CERTIFICADO.getIdOpcion()};

	public enum TIPO_INGRESO {
				ALTA(1,"Alta en el portal"),
				ACTUALIZACION(2,"Actualizacion de perfil");
		
		private int idTipoIngreso;
		private String tipoIngreso;
	
		private TIPO_INGRESO (int idTipoIngreso, String tipoIngreso){
			this.idTipoIngreso = idTipoIngreso;
			this.tipoIngreso = tipoIngreso;
		}

		public int getIdTipoIngreso() {return idTipoIngreso;}
		public String getTipoIngreso() {return tipoIngreso;}
		
	}

	// Indicadores para el Indexador en Batch
	public static final int OFFER_INDEX = 0;
	public static final int CANDIDATE_INDEX = 1;
	
	/** Catálogo para identificar a los candidatos que han recibido Apoyo Prospera **/
	public enum APOYO_PROSPERA {
		
		NO(1,"No"),
		SI(2,"Sí");
		
		private int idOpcion;
		private String opcion;
	
		private APOYO_PROSPERA(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
	}		

	public enum OCUPATE_CAMPO_ORDEN {
        TITULO_OFERTA(1), UBICACION(2), EMPRESA(3), SALARIO(4), FECHA(5);

        private final int value;

        private OCUPATE_CAMPO_ORDEN(int value) {
            this.value  = value;
        }

        public int getValue() {
          return value;
        }
    }

    public enum OCUPATE_ORDEN_DIRECCION {
        ASCENDENTE(1), DESCENDENTE(2);

        private final int value;

        private OCUPATE_ORDEN_DIRECCION(int value) {
            this.value = value;
        }

        public int getValue() {
          return value;
        }
    }

	public enum HABILIDADES_ACTITUDES {
		AUTONOMIA(1,"Autonomía"),
		RAZONAMIENTO_LOGICO_MATEMATICO(2,"Razonamiento Lógico-Matemático"),
		NEGOCIACION(3,"Negociación"),
		PENSAMIENTO_CRITICO(4,"Pensamiento crítico"),
		TRABAJO_EN_EQUIPO(5,"Trabajo en equipo"),
		MOTIVACION(6,"Motivación"),
		PROACTIVIDAD(7,"Proactividad"),
		LIDERAZGO(8,"Liderazgo"),
		ORIENTACION_A_RESULTADOS(9,"Orientación a resultados"),
		ORIENTACION_AL_CLIENTE(10,"Orientación al cliente"),
		COMPROMISO(11,"Compromiso"),
		CREATIVIDAD_E_INNOVACION(12,"Creatividad e innovación"),
		CALIDAD_EN_EL_TRABAJO(13,"Calidad en el trabajo"),
		APRENDIZAJE_CONSTANTE(14,"Aprendizaje constante"),
		ADAPTACION_AL_CAMBIO(15,"Adaptación al cambio"),
		TOLERANCIA_A_LA_PRESION(16,"Tolerancia a la presión"),
		MEJORA_CONTINUA(17,"Mejora continua"),
		ANALISIS_Y_SOLUCION_DE_PROBLEMAS(18,"Análisis y solución de problemas"),
		PLANEACION_ESTRATEGICA(19,"Planeación estratégica"),
		COMUNICACION(20,"Comunicación");

        private static int MIN_INVALID_LIMIT = 1;
        private static int MAX_INVALID_LIMIT = 20;
		
		private long idOpcion;
		private String opcion;

		private HABILIDADES_ACTITUDES(long idOpcion, String opcion){
		this.idOpcion = idOpcion;
		this.opcion = opcion;
		}

		public long getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}

		public static HABILIDADES_ACTITUDES forIdOpcion(long id){
            if (id < MIN_INVALID_LIMIT || id > MAX_INVALID_LIMIT) {
                throw new IllegalArgumentException(String.format("id [%d] value out of range [%d, %d]", id, MIN_INVALID_LIMIT, MAX_INVALID_LIMIT));
            }

			HABILIDADES_ACTITUDES habilidadesActitudes = null;

			for (HABILIDADES_ACTITUDES item : HABILIDADES_ACTITUDES.values()){
				if (item.getIdOpcion() == id){
					habilidadesActitudes = item;
					break;
				}
			}

			return habilidadesActitudes;
		}
	}

	public enum PERSONAS_CARGO {
		NINGUNA(1,"Ninguna"),
		UNA_A_CINCO(2, "1 - 5"),
		SEIS_A_DIEZ(3,"6 - 10"),
		ONCE_A_VEINTE(4,"11 - 20"),
		VEINTIUNO_A_TREINTA(5,"21 - 30"),
		TREITAYUNO_A_CUARENTA(6,"31 - 40"),
		CUARENTAYUNO_A_CINCUENTA(7,"41 - 50"),
		CINCUENTAYUNO_O_MAS(8,"51 o más");

        private static int MIN_INVALID_LIMIT = 1;
        private static int MAX_INVALID_LIMIT = 8;

		private long idOpcion;
		private String opcion;

		private PERSONAS_CARGO(long idOpcion, String opcion){
		this.idOpcion = idOpcion;
		this.opcion = opcion;
		}

		public long getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}

		public static PERSONAS_CARGO forIdOpcion(long id){
            if (id < MIN_INVALID_LIMIT || id > MAX_INVALID_LIMIT) {
                throw new IllegalArgumentException(String.format("id [%d] value out of range [%d, %d]", id, MIN_INVALID_LIMIT, MAX_INVALID_LIMIT));
            }

            PERSONAS_CARGO personasCargo = null;
			
			for (PERSONAS_CARGO item : PERSONAS_CARGO.values()){
				if (item.getIdOpcion() == id){
					personasCargo = item;
					break;
				}
			}
			
			return personasCargo;
		}
	}

	public enum RANGO_COMPARA {
		MAYOR(1,"Mayor a"),
		MENOR(2, "Menor a"),
		IGUAL(3, "Igual");

		private long idOpcion;
		private String opcion;

		private RANGO_COMPARA(long idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}
		
		public long getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}
		
	}
	
	public enum RESPUESTA_IMMS_CONSULTA_NSS{
		
		NSS_REGISTRADO(1, "NSS registrado"),
		NSS_NO_REGISTRADO(2, "NSS no registrado"),
		SERVICIO_NO_DISPONIBLE(3, "Servicio no disponible");
		
		private int idOpcion;
		private String opcion;
	
		private RESPUESTA_IMMS_CONSULTA_NSS(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}			
		
		public int getIdOpcion() {return idOpcion;}
		public String getOpcion() {return opcion;}	
	}
	
	/** Catálogo de modalidad **/
	public enum MODALIDAD {
		
		CM(1, 1, "CM", "Capacitación Mixta", "", "cm"),
		CPL(2, 1, "CPL", "Capacitación en la práctica laboral", "", "cpl"),
		VC(3, 1, "VC", "Vales de capacitación", "", "vc"),
		CA(4, 1, "CA", "Capacitación para el autoempleo", "", "ca"),
		FA(5, 2, "FA", "Fomento al Autoempleo", "informacionPrograma.do?method=init&program=fa", "fa"),
		MLISIS(6, 3, "MLI-SIS", "Movilidad Laboral Interna - Sector Industrial y de Servicios", "informacionPrograma.do?method=init&program=misi", "misi"),
		MLISA(7, 21, "MLI-SA", "Movilidad Laboral Interna - Sector Agrícola", "informacionPrograma.do?method=init&program=misa", "misa"),
		PTAT(8, 5, "PTAT", "Programa de Trabajadores Agrícolas Temporales México - Canadá", "informacionPrograma.do?method=init&program=ptat", "ptat"),
		MML(9, 6, "MML", "Mecanismo de Movilidad Laboral", "informacionPrograma.do?method=init&program=mml", "mml"),
		BECATE(16, 1, "Bécate", "Bécate", "informacionPrograma.do?method=init&program=beca", "beca"),
		FE(17, 0, "FE", "Ferias del empleo", "registrarCandidatoEvento.do?method=init", "fe"),
		RC(18, -1, "RC", "Registro de datos complementarios", "perfilComplementario.do?method=complement&program=", "rc"),
		RT(26, 22, "RT", "Repatriados trabajando", "", "rt"),
		AIT(27, 23, "AIT", "Aspirante a apoyo al ingreso de los trabajadores", "", "ait"),
		APE(28, 23, "APE", "Aspirante a apoyo para la empleabilidad", "", "ape"),
		ARUE(29, 23, "ARUE", "Aspirante a apoyo para reactivar unidades económicas", "", "arue"),
		AOT(30, 23, "AOT", "Aspirante a apoyo para la ocupación transitoria", "", "aot"),
		AMLP(31, 23, "AMLP", "Aspirante a apoyo para la movilidad laboral de las personas", "", "ampl");
		
		private int idModalidad;
		private int idSubprograma;
		private String nombreCorto;
		private String nombre;
		private String url;
		private String param;
	
		private MODALIDAD(int idModalidad, int idSubprograma, String nombreCorto, String nombre, String url, String param) {
			this.idModalidad = idModalidad;
			this.idSubprograma = idSubprograma;
			this.nombreCorto = nombreCorto;
			this.nombre = nombre;
			this.url = url;
			this.param = param;
		}

		public int getIdModalidad() {return idModalidad;}
		public int getIdSubprograma() { return idSubprograma; }
		public String getNombre() {return nombre;}
		public String getNombreCorto() {return nombreCorto;}
		public String getUrl() {return url;}
		public String getParam() {return param;}
		
		public static MODALIDAD findByID(int id) {
			MODALIDAD modalidad = null;
			for (MODALIDAD item : MODALIDAD.values()) {
				if (item.getIdModalidad() == id) {
					modalidad = item;
					break;
				}
			}
			return modalidad;
		}
		
		public static MODALIDAD findByParam(String param) {
			MODALIDAD modalidad = null;
			if (null != param) {
				for (MODALIDAD item : MODALIDAD.values()) {
					if (item.getParam().equalsIgnoreCase(param)) {
						modalidad = item;
						break;
					}
				}
			}
			return modalidad;
		}
	}	
	
	/** Catálogo de rubro historía clínica **/
	public enum HISTORIA_CLINICA {
		enfermedadesContagiosas(158, "contagiosas", "Enfermedades contagiosas"),
		enfermedadesCronicas(159, "cronicas", "Enfermedades crónicas"),
		limitacionesFisicas(160, "fisicas", "Limitaciones físicass"),
		enfermedadesLesiones(161, "lesiones", "Enfermedades o lesiones");
		
		private int idOpcion;
		private String opcion;
		private String parameter;
		
		private HISTORIA_CLINICA(int idOpcion, String parameter, String opcion) {
			this.opcion = opcion;
			this.idOpcion = idOpcion;
			this.parameter = parameter;
		}
		
		public String getOpcion() {return opcion;}
		public int getIdOpcion() {return idOpcion;}
		public String  getParameter() {return parameter;}
	}
	
	/** Catálogo de rubro agropecuareio **/
	public enum RUBRO_AGROPECUARIO {
		legumbresCampo(123, "legumbresCampo", "Verduras y legumbres del campo"),
		floresCampo(124, "floresCampo", "Flores del campo"),
		arbolesCampo(125, "arbolesCampo", "Árboles del campo"),
		frutasCampo(126, "frutas", "Frutas del campo"),
		tabaco(127, "tabaco", "Tabaco del campo"),
		apicultura(128, "apicultura", "Apicultura"),
		legumbresViv(129, "legumbresViv", "Verduras y legumbres de vivero e invernadero"),
		floresViv(130, "floresViv", "Flores de vivero e invernadero"),
		arbolesViv(131, "arbolesViv", "Árboles de vivero e invernadero"),
		ganado(132, "ganado", "Cuidado de ganado "),
		rastro(133, "rastro", "Rastro"),
		otrosRubros(134, "otros_rubros", "Otros rubros"),
		cereales(135, "cereales", "Cereales  del campo");
		
		private int idOpcion;
		private String opcion;
		private String parameter;
		
		private RUBRO_AGROPECUARIO(int idOpcion, String parameter, String opcion) {
			this.opcion = opcion;
			this.idOpcion = idOpcion;
			this.parameter = parameter;
		}	
		
		public String getOpcion() {return opcion;}
		public int getIdOpcion() {return idOpcion;}
		public String  getParameter() {return parameter;}
	}
	
	/** Catalogo de Subprogramas **/
	public enum SUBPROGRAMA {
		BECATE								(1,"Bécate"),
		FOMENTO_AL_AUTOEMPLEO				(2,"Fomento al Autoempleo"),
		MOVILIDAD_LABORAL_INTERNA_SIS		(3,"Movilidad Laboral Interna Sector Industrial y de Servicios"),
		MOVILIDAD_LABORAL					(4,"Movilidad Laboral"),
		PROGRAMA_DE_TRABAJADORES_AGRICOLAS_TEMPORALES_MEXICO_CANADA			(5,"Programa de Trabajadores Agrícolas Temporales México - Canadá"),
		MECANISMOS_DE_MOVILIDAD_LABORAL		(6,"Mecanismos de Movilidad Laboral"),
		MOVILIDAD_LABORAL_INTERNA_SA		(21,"Movilidad Laboral Interna Sector Agrícola"),
		REPATRIADOS_TRABAJANDO				(22,"Repatriados Trabajando"),
		ATENCION_CONTINGENCIA				(23,"Programa de atención a situaciones de contingencia laboral"),
		;		
	
		private int idOpcion;
		private String opcion;
	
		private SUBPROGRAMA(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}

		public int getIdOpcion() {
			return idOpcion;
		}
		
		public String getOpcion() {
			return opcion;
		}
		
		public static SUBPROGRAMA findByID(int id) {
			SUBPROGRAMA subprograma = null;
			for (SUBPROGRAMA item : SUBPROGRAMA.values()) {
				if (item.getIdOpcion() == id) {
					subprograma = item;
					break;
				}
			}
			return subprograma;
		}
	}
	
	public enum TIPO_FORMATO_BENEFICIARIO {
		MLI (1,"Movilidad Laboral Interna"),
		MML (2, "Mecanismos de Movilidad Laboral"),
		PTAT (3, "Programa de Trabajadores Agrícolas Temporales");
		
		private int idOpcion;
		private String opcion;
		
		private TIPO_FORMATO_BENEFICIARIO(int idOpcion, String opcion){
			this.idOpcion = idOpcion;
			this.opcion = opcion;
		}
		
		public int getIdOpcion() {
			return idOpcion;
		}
		
		public String getOpcion() {
			return opcion;
		}
	}
}
