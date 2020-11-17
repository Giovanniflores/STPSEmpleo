package mx.gob.stps.portal.core.oferta.detalle.helper;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mx.gob.stps.portal.core.infra.utils.Constantes.CATEGORIA_SUGERENCIA;
import mx.gob.stps.portal.core.infra.utils.Constantes.CAUSA_ORIGINA_OFERTA;
import mx.gob.stps.portal.core.infra.utils.Constantes.DISPONIBILIDAD_VIAJAR;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.EXPERIENCIA;
import mx.gob.stps.portal.core.infra.utils.Constantes.GRADO_ESTUDIOS;
import mx.gob.stps.portal.core.infra.utils.Constantes.HORARIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.JERARQUIA;
import mx.gob.stps.portal.core.infra.utils.Constantes.RAZON_BUSQUEDA;
import mx.gob.stps.portal.core.infra.utils.Constantes.SITUACION_ACADEMICA;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_CONTRATO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_DISCAPACIDAD;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_EMPLEO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_TELEFONO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TRABAJA;
import mx.gob.stps.portal.utils.Catalogos.IDIOMAS;
import mx.gob.stps.portal.utils.Catalogos.TRABAJA_ACTUALMENTE;

public class Utils {

	public static String getGradoEstudios(int degree) {
		String gradoEstudios = "";
		switch (degree) {
			case 1: gradoEstudios = GRADO_ESTUDIOS.SIN_INSTRUCCION.getOpcion(); break;
			case 2: gradoEstudios = GRADO_ESTUDIOS.LEER_ESCRIBIR.getOpcion(); break;
			case 3: gradoEstudios = GRADO_ESTUDIOS.PRIMARIA.getOpcion(); break;
			case 4: gradoEstudios = GRADO_ESTUDIOS.SECUNDARIA.getOpcion(); break;
			case 5: gradoEstudios = GRADO_ESTUDIOS.CARRERA_COMERCIAL.getOpcion(); break;
			case 6: gradoEstudios = GRADO_ESTUDIOS.CARRERA_TECNICA.getOpcion(); break;
			case 7: gradoEstudios = GRADO_ESTUDIOS.PROFESIONAL_TECNICO.getOpcion(); break;
			case 8: gradoEstudios = GRADO_ESTUDIOS.PREPA_VOCACIONAL.getOpcion(); break;
			case 9: gradoEstudios = GRADO_ESTUDIOS.SUPERIOR_UNIVERSITARIO.getOpcion(); break;
			case 10: gradoEstudios = GRADO_ESTUDIOS.LICENCIATURA.getOpcion(); break;
			case 11: gradoEstudios = GRADO_ESTUDIOS.MAESTRIA.getOpcion(); break;
			case 12: gradoEstudios = GRADO_ESTUDIOS.DOCTORADO.getOpcion(); break;
			default: gradoEstudios = ""; break;
		}
		return gradoEstudios;
	}
	
	public static String getDominio(int id_dominio) {
		String dominio = "";
		if (id_dominio > -1) {
			switch(id_dominio) {
				case 1: dominio ="Básico"; break;
				case 2: dominio ="Intermedio"; break;
				case 3: dominio ="Avanzado"; break;
				/**case 4: dominio ="40%"; break;
				case 5: dominio ="50%"; break;
				case 6: dominio ="60%"; break;
				case 7: dominio ="70%"; break;
				case 8: dominio ="80%"; break;
				case 9: dominio ="90%"; break;
				case 10: dominio ="100%"; break;
				*/default : dominio = ""; break;
			}
		}
		return dominio;
	}
	
	public static long getIdCatalog(int param) {
		long id_catalog = 0;
		if (param > -1) {
			switch(param) {
				case 1: id_catalog = 45; break;
				case 2: id_catalog = 45; break;
				case 3: id_catalog = 45; break;
				case 4: id_catalog = 45; break;
				case 5: id_catalog = 40; break;
				case 6: id_catalog = 40; break;
				case 7: id_catalog = 42; break;
				case 8: id_catalog = 42; break;
				case 9: id_catalog = 43; break;
				case 10: id_catalog = 43; break;
				case 11: id_catalog = 44; break;
				case 12: id_catalog = 44; break;
				default : id_catalog = 45; break;
			}
		}
		return id_catalog;
	}
	
	public static String setEstatusLbl(int status) {
		String estatus = null;
		switch(status) {
			case 1: estatus = ESTATUS.ACTIVO.getOpcion(); break;
			case 2: estatus = ESTATUS.INACTIVO.getOpcion(); break;
			case 3: estatus = ESTATUS.MODIFICADA.getOpcion(); break;
			case 4: estatus = ESTATUS.SELECCIONADA.getOpcion(); break;
			case 5: estatus = ESTATUS.POSTULADO.getOpcion(); break;
			case 6: estatus = ESTATUS.DESPOSTULADO.getOpcion(); break;
			case 7: estatus = ESTATUS.REGISTRADA.getOpcion(); break;
			case 8: estatus = ESTATUS.PREVALIDADA.getOpcion(); break;
			case 9: estatus = ESTATUS.EMP_MODIFICADA.getOpcion(); break;
			case 10: estatus = ESTATUS.CANCELADA.getOpcion(); break;
			case 11: estatus = ESTATUS.EN_EDICION.getOpcion(); break;
			case 12: estatus = ESTATUS.PENDIENTE_PUBLICAR.getOpcion(); break;
			case 13: estatus = ESTATUS.ELIMINADA_EMP.getOpcion(); break;
			case 14: estatus = ESTATUS.ELIMINADA_ADMIN.getOpcion(); break;
			case 15: estatus = ESTATUS.ELIMINADA_VIG.getOpcion(); break;
			case 16: estatus = ESTATUS.VINCULADO.getOpcion(); break;
			case 17: estatus = ESTATUS.NO_INTERESADO.getOpcion(); break;
			case 18: estatus = ESTATUS.DESVNCULADO.getOpcion(); break;
			case 19: estatus = ESTATUS.SELECCIONADO.getOpcion(); break;
			case 20: estatus = ESTATUS.EN_PROCESO.getOpcion(); break;
			case 21: estatus = ESTATUS.CONTRATADO.getOpcion(); break;
			case 22: estatus = ESTATUS.NO_ACEPTADO.getOpcion(); break;
			case 23: estatus = ESTATUS.RECHAZADA.getOpcion(); break;
			case 24: estatus = ESTATUS.REPROGRAMADA.getOpcion(); break;
			case 25: estatus = ESTATUS.ACEPTADA.getOpcion(); break;
			case 26: estatus = ESTATUS.ASIGNADO_PUBLICADOR.getOpcion(); break;
			case 27: estatus = ESTATUS.EN_EDICION_PUBLICADOR.getOpcion(); break;
			case 28: estatus = ESTATUS.ELIMINADA_EMP_FRAUDULENTA.getOpcion(); break;
			case 29: estatus = ESTATUS.NUEVA.getOpcion(); break;			
			case 30: estatus = ESTATUS.INACTIVO_POR_PERFIL.getOpcion(); break;
			case 31: estatus = ESTATUS.ALCANZO_LIMITE_PORTULACION.getOpcion(); break;
			case 32: estatus = ESTATUS.INACTIVO_POR_BAJA_ADMINISTRADOR.getOpcion(); break;
			case 33: estatus = ESTATUS.INACTIVO_POR_BAJA_FUNCIONARIO.getOpcion(); break;			
			case 34: estatus = ESTATUS.INACTIVO_A_PETICION_DEL_CANDIDATO.getOpcion(); break;
			case 35: estatus = ESTATUS.INACTIVO_POR_VIGENCIA.getOpcion(); break;
			case 36: estatus = ESTATUS.SIN_VACANTES.getOpcion(); break;
			case 37: estatus = ESTATUS.ATENDIDA.getOpcion(); break;
			case 38: estatus = ESTATUS.PUBLICADA_PARA_SISNE.getOpcion(); break;
			case 39: estatus = ESTATUS.CUBIERTA.getOpcion(); break;
			case 40: estatus = ESTATUS.PENDIENTE_POR_CAPACITACION.getOpcion(); break;
			case 41: estatus = ESTATUS.ELIMINADA_POR_MODIFICACION.getOpcion(); break;
			case 42: estatus = ESTATUS.FERIA_PENDIENTE_POR_PUBLICAR.getOpcion(); break;
			case 43: estatus = ESTATUS.FERIA_VALIDADO_UR.getOpcion(); break;			
			case 44: estatus = ESTATUS.FERIA_VALIDADO_COOR_VIN_UC.getOpcion(); break;
			case 45: estatus = ESTATUS.FERIA_VALIDADO_CGSNE.getOpcion(); break;
			case 46: estatus = ESTATUS.FERIA_RECHADAZO_UR.getOpcion(); break;
			case 47: estatus = ESTATUS.FERIA_RECHADAZO_COOR_VIN_UC.getOpcion(); break;
			case 48: estatus = ESTATUS.FERIA_RECHADAZO_CGSNE.getOpcion(); break;
			case 49: estatus = ESTATUS.FERIA_SEGUIMIENTO.getOpcion(); break;
			case 50: estatus = ESTATUS.ABRIENDO_ESPACIOS_ACTIVO.getOpcion(); break;
			default : estatus = ""; break;
		}
		return estatus;
	}
	
	public static String getExperiencia(int status) {
		String experiencia = null;
		switch(status) {
			case 1: experiencia = EXPERIENCIA.NINGUNA.getOpcion(); break;
			case 2: experiencia = EXPERIENCIA.MENOR_UNO.getOpcion(); break;
			case 3: experiencia = EXPERIENCIA.MENOR_DOS.getOpcion(); break;
			case 4: experiencia = EXPERIENCIA.MENOR_TRES.getOpcion(); break;
			case 5: experiencia = EXPERIENCIA.MENOR_CUATRO.getOpcion(); break;
			case 6: experiencia = EXPERIENCIA.MENOR_CINCO.getOpcion(); break;
			case 7: experiencia = EXPERIENCIA.MAS_CINCO.getOpcion(); break;
			case 8: experiencia = EXPERIENCIA.MENOR_UNO.getOpcion(); break;
			case 9: experiencia = EXPERIENCIA.NO_REQUISITO.getOpcion(); break;
			default : experiencia = ""; break;
		}
		return experiencia;
	}
	
	public static String getHorario(int status) {
		String horario = "";
		switch(status) {
			case 1: horario = HORARIO.OCHO_AM.getOpcion(); break;
			case 2: horario = HORARIO.OCHO_MEDIA_AM.getOpcion(); break;
			case 3: horario = HORARIO.NUEVE_AM.getOpcion(); break;
			case 4: horario = HORARIO.NUEVE_MEDIA_AM.getOpcion(); break;
			case 5: horario = HORARIO.DIEZ_AM.getOpcion(); break;
			case 6: horario = HORARIO.DIEZ_MEDIA_AM.getOpcion(); break;
			case 7: horario = HORARIO.ONCE_AM.getOpcion(); break;
			case 8: horario = HORARIO.ONCE_MEDIA_AM.getOpcion(); break;
			case 9: horario = HORARIO.DOCE_PM.getOpcion(); break;
			case 10: horario = HORARIO.DOCE_MEDIA_PM.getOpcion(); break;
			case 11: horario = HORARIO.UNA_PM.getOpcion(); break;
			case 12: horario = HORARIO.UNA_MEDIA_PM.getOpcion(); break;
			case 13: horario = HORARIO.DOS_PM.getOpcion(); break;
			case 14: horario = HORARIO.DOS_MEDIA_PM.getOpcion(); break;
			case 15: horario = HORARIO.TRES_PM.getOpcion(); break;
			case 16: horario = HORARIO.TRES_MEDIA_PM.getOpcion(); break;
			case 17: horario = HORARIO.CUATRO_PM.getOpcion(); break;
			case 18: horario = HORARIO.CUATRO_MEDIA_PM.getOpcion(); break;
			case 19: horario = HORARIO.CINCO_PM.getOpcion(); break;
			case 20: horario = HORARIO.CINCO_MEDIA_PM.getOpcion(); break;
			case 21: horario = HORARIO.SEIS_PM.getOpcion(); break;
			case 22: horario = HORARIO.SEIS_MEDIA_PM.getOpcion(); break;
			case 23: horario = HORARIO.SIETE_PM.getOpcion(); break;
			case 24: horario = HORARIO.SIETE_MEDIA_PM.getOpcion(); break;
			case 25: horario = HORARIO.OCHO_PM.getOpcion(); break;
			case 26: horario = HORARIO.OCHO_MEDIA_PM.getOpcion(); break;
			case 27: horario = HORARIO.NUEVE_PM.getOpcion(); break;
			default : horario = ""; break;
		}
		return horario;
	}
	
	public static String getHorario(String status) {
		String horario = "";
		int option = parseInt(status);
		switch(option) {
			case 1: horario = HORARIO.OCHO_AM.getOpcion(); break;
			case 2: horario = HORARIO.OCHO_MEDIA_AM.getOpcion(); break;
			case 3: horario = HORARIO.NUEVE_AM.getOpcion(); break;
			case 4: horario = HORARIO.NUEVE_MEDIA_AM.getOpcion(); break;
			case 5: horario = HORARIO.DIEZ_AM.getOpcion(); break;
			case 6: horario = HORARIO.DIEZ_MEDIA_AM.getOpcion(); break;
			case 7: horario = HORARIO.ONCE_AM.getOpcion(); break;
			case 8: horario = HORARIO.ONCE_MEDIA_AM.getOpcion(); break;
			case 9: horario = HORARIO.DOCE_PM.getOpcion(); break;
			case 10: horario = HORARIO.DOCE_MEDIA_PM.getOpcion(); break;
			case 11: horario = HORARIO.UNA_PM.getOpcion(); break;
			case 12: horario = HORARIO.UNA_MEDIA_PM.getOpcion(); break;
			case 13: horario = HORARIO.DOS_PM.getOpcion(); break;
			case 14: horario = HORARIO.DOS_MEDIA_PM.getOpcion(); break;
			case 15: horario = HORARIO.TRES_PM.getOpcion(); break;
			case 16: horario = HORARIO.TRES_MEDIA_PM.getOpcion(); break;
			case 17: horario = HORARIO.CUATRO_PM.getOpcion(); break;
			case 18: horario = HORARIO.CUATRO_MEDIA_PM.getOpcion(); break;
			case 19: horario = HORARIO.CINCO_PM.getOpcion(); break;
			case 20: horario = HORARIO.CINCO_MEDIA_PM.getOpcion(); break;
			case 21: horario = HORARIO.SEIS_PM.getOpcion(); break;
			case 22: horario = HORARIO.SEIS_MEDIA_PM.getOpcion(); break;
			case 23: horario = HORARIO.SIETE_PM.getOpcion(); break;
			case 24: horario = HORARIO.SIETE_MEDIA_PM.getOpcion(); break;
			case 25: horario = HORARIO.OCHO_PM.getOpcion(); break;
			case 26: horario = HORARIO.OCHO_MEDIA_PM.getOpcion(); break;
			case 27: horario = HORARIO.NUEVE_PM.getOpcion(); break;
			default : horario = ""; break;
		}
		return horario;
	}
	
	//RBM1 TK1000 TK1001 se homologa este catalogo con RU 
//	public static String getJerarquia(int status) {
//		String jerarquia = "";
//		switch(status) {
//			case 1: jerarquia = JERARQUIA.DIRECCION.getOpcion(); break;
//			case 2: jerarquia = JERARQUIA.MANDOS.getOpcion();; break;
//			case 3: jerarquia = JERARQUIA.EMPLEADO.getOpcion();; break;
//			case 4: jerarquia = JERARQUIA.TECNICO.getOpcion();; break;
//			case 5: jerarquia = JERARQUIA.OPERATIVO.getOpcion();; break;
//			case 6: jerarquia = JERARQUIA.PROFESIONISTA.getOpcion();; break;
//			default : jerarquia = ""; break;
//		}
//		return jerarquia;
//	}
	
	public static String getJerarquia(int status) {
		String jerarquia = "";
		switch(status) {
			case 1: jerarquia = JERARQUIA.OPERARIO.getOpcion(); break;
			case 2: jerarquia = JERARQUIA.TECNICO.getOpcion();; break;
			case 3: jerarquia = JERARQUIA.ADMINISTRATIVOS.getOpcion();; break;
			case 4: jerarquia = JERARQUIA.PRODUCCION.getOpcion();; break;
			case 5: jerarquia = JERARQUIA.DIRECTIVOS.getOpcion();; break;
			default : jerarquia = ""; break;
		}
		return jerarquia;
	}
	
	//RBM1 END TK1000 TK1001 se homologa este catalogo con RU 
	
	public static String getSituacionAcademica(int status) {
		String situacionAcademica = "";
		switch (status) {
			case 2: situacionAcademica = SITUACION_ACADEMICA.ESTUDIANTE.getOpcion(); break;
			case 3: situacionAcademica = SITUACION_ACADEMICA.DIPLOMA_CERTIFICADO.getOpcion(); break;
			case 4: situacionAcademica = SITUACION_ACADEMICA.TRUNCA.getOpcion(); break;
			case 5: situacionAcademica = SITUACION_ACADEMICA.PASANTE.getOpcion(); break;
			case 6: situacionAcademica = SITUACION_ACADEMICA.TITULADO.getOpcion(); break;
			default: situacionAcademica = ""; break;
		}
		return situacionAcademica;
	}
	
	public static String getTipoEmpleo(int type) {
		String tipoEmpleo = "";
		switch (type) {
			case 1: tipoEmpleo = TIPO_EMPLEO.TIEMPO_COMPLETO.getOpcion(); break;
			case 2: tipoEmpleo = TIPO_EMPLEO.MEDIO_TIEMPO.getOpcion(); break;
			case 5: tipoEmpleo = TIPO_EMPLEO.FINES_SEMANA.getOpcion(); break;
			case 6: tipoEmpleo = TIPO_EMPLEO.TEMPORAL.getOpcion(); break;
			case 7: tipoEmpleo = TIPO_EMPLEO.ROLAR_TURNOS.getOpcion(); break;
			case 8: tipoEmpleo = TIPO_EMPLEO.NOCTURNO.getOpcion(); break;
			default: tipoEmpleo = ""; break;
		}
		return tipoEmpleo;
	}
	
	//RBM1 TK1000 TK1001
	public static String getTipoContrato(int type) {
		String tipoContrato = "";
		switch (type) {
			case 1: tipoContrato = TIPO_CONTRATO.INDETERMINADO.getOpcion(); break;
			case 2: tipoContrato = TIPO_CONTRATO.DETERMINADO.getOpcion(); break;
			case 3: tipoContrato = TIPO_CONTRATO.OBRA_DETERMINADA.getOpcion(); break;
			case 4: tipoContrato = TIPO_CONTRATO.PERIODO_PRUEBA.getOpcion(); break;
			case 5: tipoContrato = TIPO_CONTRATO.CAPACITACION_INICIAL.getOpcion(); break;
			case 6: tipoContrato = TIPO_CONTRATO.TEMPORADA.getOpcion(); break;
			case 7: tipoContrato = TIPO_CONTRATO.UNIDAD_TIEMPO.getOpcion(); break;						
			default: tipoContrato = ""; break;
		}
		return tipoContrato;
	}
	
	public static String getTipoDiscapacidad(int type) {
		String tipoDiscapacidad = "";
		switch (type) {
			case 1: tipoDiscapacidad = TIPO_DISCAPACIDAD.NINGUNA.getOpcion(); break;
			case 2: tipoDiscapacidad = TIPO_DISCAPACIDAD.AUDITIVA.getOpcion(); break;
			case 3: tipoDiscapacidad = TIPO_DISCAPACIDAD.INTELECTUAL.getOpcion(); break;
			case 4: tipoDiscapacidad = TIPO_DISCAPACIDAD.MENTAL.getOpcion(); break;
			case 5: tipoDiscapacidad = TIPO_DISCAPACIDAD.MOTRIZ.getOpcion(); break;
			case 6: tipoDiscapacidad = TIPO_DISCAPACIDAD.VISUAL.getOpcion(); break;
			default: tipoDiscapacidad = ""; break;
		}
		return tipoDiscapacidad;
	}
	
	public static String getCausaOferta(int type) {
		String causa = "";
		switch(type) {
			case 1: causa = CAUSA_ORIGINA_OFERTA.EMPRESA_NUEVA.getOpcion(); break;
			case 2: causa = CAUSA_ORIGINA_OFERTA.NECESIDADES_TEMPORALES.getOpcion();; break;
			case 3: causa = CAUSA_ORIGINA_OFERTA.PUESTO_NUEVO.getOpcion();; break;
			case 4: causa = CAUSA_ORIGINA_OFERTA.REPOSICION_PERSONAL.getOpcion();; break;
			case 5: causa = CAUSA_ORIGINA_OFERTA.OTRA.getOpcion();; break;
			default : causa = ""; break;
		}
		return causa;
	}
	
	public static String getTipoTelefono(int type) {
		String tipo = "";
		switch(type) {
			case 1: tipo = TIPO_TELEFONO.CELULAR.getOpcion(); break;
			/*case 2: tipo = TIPO_TELEFONO.LOCAL.getOpcion();; break;
			case 3: tipo = TIPO_TELEFONO.NEXTEL.getOpcion();; break;
			case 4: tipo = TIPO_TELEFONO.OTRO.getOpcion();; break;*/
			case 5: tipo = TIPO_TELEFONO.FIJO.getOpcion();; break;
			default : tipo = ""; break;
		}
		return tipo;
	}
	
	public static String getLblDiasLaborales(String diasLaborales) {
		StringBuilder bits = new StringBuilder();
		char[] bytes = diasLaborales.toCharArray();
		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] == '1') {
				switch (i) {
					case 0: bits.append(" D"); break;
					case 1: bits.append(" L"); break;
					case 2: bits.append(" Ma"); break;
					case 3: bits.append(" Mi"); break;
					case 4: bits.append(" J"); break;
					case 5: bits.append(" V"); break;
					case 6: bits.append(" S"); break;
					default : bits.append(""); break;
				}
			}
		}
		return bits.toString().trim();
	}
	
	public static String getLblTrabaja(int idTrabaja) {
		String trabaja = "";
		switch(idTrabaja) {
			case 1: trabaja = TRABAJA_ACTUALMENTE.NO.getOpcion(); break;
			case 2: trabaja = TRABAJA_ACTUALMENTE.SI.getOpcion(); break;
			default : trabaja = ""; break;
		}
		return trabaja;
	}
	
	public static String getLblDisponibilidad(int idDisponibilidad) {
		String disponibilidad = "";
		switch(idDisponibilidad) {
			case 1: disponibilidad = DISPONIBILIDAD_VIAJAR.NO.getOpcion(); break;
			case 2: disponibilidad = DISPONIBILIDAD_VIAJAR.SI.getOpcion(); break;
			default : disponibilidad = ""; break;
		}
		return disponibilidad;
	}
	
	public static String getLblRazonBusqueda(int idRazon) {
		String razon = "";
		switch(idRazon) {
			case 2: razon = RAZON_BUSQUEDA.CAMBIO_TRABAJO.getOpcion(); break;
			case 3: razon = RAZON_BUSQUEDA.MAS_EMPLEO.getOpcion(); break;
			case 11: razon = RAZON_BUSQUEDA.EXTRABAJADOR_LYFC.getOpcion(); break;
			case 12: razon = RAZON_BUSQUEDA.NUNCA_TRABAJO.getOpcion(); break;
			case 13: razon = RAZON_BUSQUEDA.CERRO_FUENTE.getOpcion(); break;
			case 14: razon = RAZON_BUSQUEDA.AJUSTE_PERSONAL.getOpcion(); break;
			case 15: razon = RAZON_BUSQUEDA.DESPIDO.getOpcion(); break;
			case 16: razon = RAZON_BUSQUEDA.TERMINO_CONTRATO.getOpcion(); break;
			case 17: razon = RAZON_BUSQUEDA.RETIRO_VOLUNTARIO.getOpcion(); break;
			case 18: razon = RAZON_BUSQUEDA.OTRO.getOpcion(); break;
			default : razon = ""; break;
		}
		return razon;
	}
	
	public static String getLblCategoriaSugerencia(int idCategoria) {
		String category = "";
		switch(idCategoria) {
			case 1: category = CATEGORIA_SUGERENCIA.QUEJA.getOpcion(); break;
			case 2: category = CATEGORIA_SUGERENCIA.DUDA.getOpcion(); break;
			case 3: category = CATEGORIA_SUGERENCIA.SUGERENCIA.getOpcion(); break;
			case 4: category = CATEGORIA_SUGERENCIA.COMENTARIO.getOpcion(); break;
			default : category = ""; break;
		}
		return category;
	}	
	
	public static String formatDate(Date date2format) {
		Locale local = new Locale("es");
		//Format formatter = new SimpleDateFormat("dd MMM yyyy", local);
		Format formatter = new SimpleDateFormat("d' de 'MMMM' de 'yyyy", local);
		return formatter.format(date2format);
	}
	
	public static int getEdadActual(Date fechaNacimiento) {
		Calendar calendar = Calendar.getInstance();
		int yearCurrent = calendar.get(Calendar.YEAR);
		if (null != fechaNacimiento)
			calendar.setTime(fechaNacimiento);
		int yearBirth = calendar.get(Calendar.YEAR);
		int age = yearCurrent - yearBirth;
		return age;
	}
	
	public static int parseInt(String numero){
	    int valor = -1;
	    if (esEntero(numero))
	        valor = Integer.parseInt(numero);
	    return valor;
	}
	
	public static boolean esEntero(String numero) {
		if (numero != null && numero.length() > 0) {
			Pattern pattern = Pattern.compile("^[0-9]+$");
			Matcher matcher = pattern.matcher(numero);
			return matcher.find();
		} else {
			return false;
		}
	}
	
	public static String getSalario(double salario) {
		String sSalario = "";
		String salary = String.valueOf(salario);
		int index = salary.lastIndexOf(".");
		if (index > 1)
			sSalario = salary.substring(0,index);
		else sSalario = salary;
		return sSalario;
	}

	public static String capitalize(String cadena) {
		if (cadena==null) return cadena;

		cadena = cadena.trim();

		if (cadena.isEmpty() || cadena.length() < 2) return cadena;
		
		cadena = cadena.toLowerCase();
		cadena = Character.toUpperCase(cadena.charAt(0)) + cadena.substring(1);

		return cadena;
	}
	
	public static String normalizeDetail(String detail, int maxLength) {
		if (null == detail)
			return "";
		else if (detail.length() > maxLength)
			return detail.substring(0,maxLength);
		else
			return detail;
	}	

	public static String getIdioma(int status) {
        String idioma = null;
        switch(status) {
              case 1: idioma = IDIOMAS.NINGUNO.getOpcion(); break;
              case 2: idioma = IDIOMAS.ALEMAN.getOpcion(); break;
              case 3: idioma = IDIOMAS.CHINO.getOpcion(); break;
              case 4: idioma = IDIOMAS.FRANCES.getOpcion(); break;
              case 5: idioma = IDIOMAS.INGLES.getOpcion(); break;
              case 6: idioma = IDIOMAS.ITALIANO.getOpcion(); break;
              case 7: idioma = IDIOMAS.JAPONES.getOpcion(); break;
              case 8: idioma = IDIOMAS.PORTUGUES.getOpcion(); break;
              case 9: idioma = IDIOMAS.NO_REQUISITO.getOpcion(); break;
              default : idioma = ""; break;
        }
        return idioma;
   }
   
   public static int getIdCatCertificacion(int status) {
        int certificacion = 1;
        switch(status) {
              case 2: certificacion = 32; break;
              case 3: certificacion = 35; break;
              case 4: certificacion = 31; break;
              case 5: certificacion = 30; break;
              case 6: certificacion = 33; break;
              case 7: certificacion = 36; break;
              case 8: certificacion = 89; break;
              default : certificacion = 1; break;
        }
        return certificacion;
   }

}