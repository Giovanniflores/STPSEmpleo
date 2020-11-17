package mx.gob.stps.portal.core.ws.dao;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CARRERA_ESPECIALIDAD_PRINCIPAL;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_AREA_LABORAL;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_DOMINIO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_EXPERIENCIA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_IDIOMAS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_OCUPACION;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_SITUACION_ACADEMICA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_TIPO_EMPLEO;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.SimpleTimeZone;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO;
import mx.gob.stps.portal.core.infra.utils.Constantes.EDAD_REQUISITO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS_ACTIVAS;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PERSONA;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoOutVO;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoSFPVO;

public class OfertasSFPDAO extends TemplateDAO {
	private final static int QUERY_EXISTE_OFERTA = 1;
	private final static int QUERY_INSERTA_OFERTA_EXTERNA = 2;
	private final static int QUERY_BUSCA_OFERTASRSS = 3;
	private final static int QUERY_BUSCA_OFERTA_SFP = 4;
	private final static int QUERY_BUSCA_CARRERA = 5;
	private final static int QUERY_BUSCA_OFERTASRSS_SIN_PARAMETROS = 6;
	private final static int QUERY_BUSCA_TODAS_OFERTASSFP_SIN_PARAMETROS = 7;
	private int QUERY = 0;
	private int ID_CATALOGO = 0;
	private String plus ="";
	
	
	public OfertasSFPDAO(){}
	
	public OfertasSFPDAO(Connection globalConnection){
		super(globalConnection);
	}
	
	public int existeVacante(OfertaEmpleoSFPVO oferta) throws SQLException {
		QUERY = QUERY_EXISTE_OFERTA;
		int estadoVacante = 3;
	    try {
	    	String [] params = {String.valueOf(oferta.getIdOfertaBolsaSFP())};
			CachedRowSet rs = this.executeQuery(params); 
			if (rs.next()) {
				Date fecModBD = rs.getDate("OFERTA_FECMOD");
				if (fecModBD == null) {
					fecModBD = rs.getDate("OFERTA_FECALTA");
				}
				if (oferta.getFechaAlta() != null) {
		           SimpleTimeZone tz = new SimpleTimeZone(-21600000, "America/Mexico_City", 3, 1, -1, 7200000, 9, -1, 1, 7200000, 3600000);
		           GregorianCalendar modificacion = new GregorianCalendar(tz);
		           GregorianCalendar modificacionBD = new GregorianCalendar(tz);
		           modificacionBD.setTime(fecModBD);
		           modificacionBD.set(Calendar.HOUR_OF_DAY, 0);
		           modificacionBD.set(Calendar.MINUTE, 0);
		           modificacionBD.set(Calendar.SECOND, 0);
		           modificacionBD.set(Calendar.MILLISECOND, 0);
		           
		           modificacion.setTime(oferta.getFechaAlta());
		           modificacion.set(Calendar.HOUR_OF_DAY, 0);
		           modificacion.set(Calendar.MINUTE, 0);
		           modificacion.set(Calendar.SECOND, 0);
		           modificacion.set(Calendar.MILLISECOND, 0);
		
		           estadoVacante = modificacion.after(modificacionBD) ? 2 : 3;
		           if (estadoVacante == 2)
		             oferta.setIdOfertaEmpleo(rs.getLong("ID_OFERTA_EMPLEO"));
		         }
	       } else {
	         estadoVacante = 1;
	       }
		   if(oferta.getCarreras().size() ==0){
			   estadoVacante = 3;
		   }
			
	     } catch (SQLException sqle) {
	       estadoVacante = 3;
	     } catch (Exception e) {
	       estadoVacante = 3;
	     }
	    return estadoVacante;
	  }
	

	  public short crearVacante(OfertaEmpleoVO oferta) {
	     short operacion = 0;
	       SimpleTimeZone tz = new SimpleTimeZone(-21600000, "America/Mexico_City", 3, 1, -1, 7200000, 9, -1, 1, 7200000, 3600000);
	       GregorianCalendar fecAlta = new GregorianCalendar(tz);
	       fecAlta.setTime(oferta.getFechaAlta()); 
	       fecAlta.set(Calendar.HOUR_OF_DAY, 0);
	       fecAlta.set(Calendar.MINUTE, 0);
	       fecAlta.set(Calendar.SECOND, 0);
	       fecAlta.set(Calendar.MILLISECOND, 0);
	       
	       GregorianCalendar fecMod = new GregorianCalendar(tz);
	       fecMod.setTime(oferta.getFechaAlta());
	       fecMod.set(Calendar.HOUR_OF_DAY, 0);
	       fecMod.set(Calendar.MINUTE, 0);
	       fecMod.set(Calendar.SECOND, 0);
	       fecMod.set(Calendar.MILLISECOND, 0);

	       return operacion;
   }
	  
	  
	  
	  
	public List<OfertaEmpleoOutVO> buscaOfertasSFP() throws SQLException{
		QUERY = QUERY_BUSCA_OFERTASRSS_SIN_PARAMETROS;
		CachedRowSet rs = this.executeQuery(); 
		List<OfertaEmpleoOutVO> listaOfertas = new ArrayList<OfertaEmpleoOutVO>();
		while (rs.next()){
			OfertaEmpleoOutVO oferta = new OfertaEmpleoOutVO();
			oferta.setFecha(rs.getString("fecha_alta"));
			oferta.setPuesto(rs.getString("titulo_oferta"));
			oferta.setEmpresa(rs.getString("empresa"));
			oferta.setEstado(rs.getString("opcion"));
			oferta.setIdOferta((long)rs.getLong("id_oferta_empleo"));	
			oferta.setUrl("detalleoferta.do?method=init&id_oferta_empleo="+ rs.getString("id_oferta_empleo"));
			listaOfertas.add(oferta);
		}
		return listaOfertas;
	}
	
	public List<OfertaPorCanalVO> buscaTodasOfertasSFP() throws SQLException{		
		QUERY = QUERY_BUSCA_TODAS_OFERTASSFP_SIN_PARAMETROS;		
		CachedRowSet rs = this.executeQuery(); 
		List<OfertaPorCanalVO> listaOfertas = new ArrayList<OfertaPorCanalVO>();
		while (rs.next()){
			OfertaPorCanalVO oferta = new OfertaPorCanalVO();
			//FIX 
			//oferta.setFechaInicio(rs.getDate("fecha_alta"));
			oferta.setFechaInicio(Utils.toDate(rs.getObject("fecha_alta")));
			oferta.setFechaInicioString(rs.getString("fecha_alta"));
			oferta.setTituloOferta(rs.getString("titulo_oferta"));
			oferta.setEmpresa(rs.getString("empresa"));
			oferta.setEntidad(rs.getString("entidad"));
			oferta.setIdOfertaEmpleo((long)rs.getLong("id_oferta_empleo"));		
			oferta.setMunicipio(rs.getString("ciudad"));
			oferta.setSalario(rs.getDouble("salario"));			
			//FIX oferta.setsSalario(sSalario) ?
			oferta.setHabilidadGeneral(rs.getString("habilidad_general"));
			oferta.setExperiencia(rs.getString("experiencia_anios_descripcion"));
			oferta.setEdad(rs.getString("edad"));
			oferta.setIdiomas(rs.getString("idiomas"));
			listaOfertas.add(oferta);
		}
		return listaOfertas;
	}		  
	
	public List<OfertaEmpleoOutVO> buscaOfertasSFP(int idEntidad, Long idOcupacion) throws SQLException{
		QUERY = QUERY_BUSCA_OFERTASRSS;
		String [] params = {String.valueOf(idEntidad), String.valueOf(idOcupacion)};
		CachedRowSet rs = this.executeQuery(params); 
		List<OfertaEmpleoOutVO> listaOfertas = new ArrayList<OfertaEmpleoOutVO>();
		while (rs.next()){
			OfertaEmpleoOutVO oferta = new OfertaEmpleoOutVO();
			oferta.setFecha(rs.getString("fecha_alta"));
			oferta.setPuesto(rs.getString("titulo_oferta"));
			oferta.setEmpresa(rs.getString("empresa"));
			oferta.setEstado(rs.getString("opcion"));
			oferta.setIdOferta((long)rs.getLong("id_oferta_empleo"));
			listaOfertas.add(oferta);
		}
		return listaOfertas;
	}
	
	
	public OfertaEmpleoSFPVO buscaOfertaSFP(Long idOferta) throws SQLException, ParseException{
		QUERY = QUERY_BUSCA_CARRERA;
		long idCarrera = -1;
		int idPrincipal = -1;
		boolean bCarreraEncontrada = false;
		int tam = 0;
		String [] params = {String.valueOf(idOferta)};
		CachedRowSet rs = this.executeQuery(params);
		while(rs.next()){
			idCarrera= rs.getLong("ID_CARRERA_ESPECIALIDAD");
			idPrincipal = rs.getInt("PRINCIPAL");
			if(idPrincipal==1){			
				bCarreraEncontrada = true;
				break;
			} else if(idPrincipal==2 && bCarreraEncontrada==false){
				bCarreraEncontrada = true;
				break;
			} else if(idPrincipal==4 && bCarreraEncontrada==false){
				idPrincipal = 0;
				bCarreraEncontrada = true;
				break;	
			}	
		}
		tam = String.valueOf(idCarrera).length();
		if( tam < 7 && idCarrera >= 0){
			ID_CATALOGO = CATALOGO.TECNICA_COMERCIAL_BASICA.getIdCatalogo();
		}else if  (idCarrera == 9999999){
			ID_CATALOGO = CATALOGO.SIN_ESPECIALIDAD.getIdCatalogo();
		}else {
			idCarrera = Long.parseLong(String.valueOf(idCarrera).substring(0,3));
		}
		
		if (idCarrera > 100 && idCarrera < 300) {
			ID_CATALOGO = CATALOGO.TECNICA_COMERCIAL_SUPERIOR.getIdCatalogo();
		}else if (idCarrera > 300 && idCarrera < 500) {
			ID_CATALOGO = CATALOGO.PROFESIONAL.getIdCatalogo();
		}else if (idCarrera > 500 && idCarrera < 700) {
			ID_CATALOGO = CATALOGO.MAESTRIA_DOCTORADO.getIdCatalogo();
		}else {
			plus = "(+)";
		}
				 
		QUERY = QUERY_BUSCA_OFERTA_SFP;
		String [] parametros = {String.valueOf(idPrincipal), String.valueOf(ID_CATALOGO), String.valueOf(idOferta)};
		rs = this.executeQuery(parametros);
		OfertaEmpleoSFPVO ofertaVO= new OfertaEmpleoSFPVO();
		DomicilioVO domicilio = new DomicilioVO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		while(rs.next()){
			ofertaVO.setFechaAlta(sdf.parse(rs.getString("fecha_alta")));
			ofertaVO.setAreaLaboralDescripcion(rs.getString("area_lab"));
			ofertaVO.setOcupacionDescripcion(rs.getString("ocupacion"));
			ofertaVO.setTituloOferta(rs.getString("titulo_oferta"));
			ofertaVO.setFunciones(rs.getString("funciones"));
			ofertaVO.setTipoEmpleoDescripcion(rs.getString("tipo_empleo"));
			ofertaVO.setHorario(rs.getString("horario"));
			ofertaVO.setNumeroPlazas(rs.getLong("numero_plazas"));
			ofertaVO.setSalario(rs.getDouble("salario"));
			domicilio.setEntidad(rs.getString("entidad"));
			ofertaVO.setEscolaridad(rs.getString("escolaridad"));
			ofertaVO.setSituacionAcademicaDescrip(rs.getString("sit_academ"));
			ofertaVO.setExperienciaAniosDescrip(rs.getString("experiencia"));
			ofertaVO.setDispViajarDescripcion(rs.getString("disp_viajar"));
			ofertaVO.setDispRadicarDescripcion(rs.getString("disp_radicar"));
			ofertaVO.setEdadPreferente(rs.getString("edad_pref"));
			ofertaVO.setEmpresaDescripcion(rs.getString("empresa"));
			ofertaVO.setHabilidadGeneral(rs.getString("habilidad_general"));
			ofertaVO.setCarreraDescripcion(rs.getString("carrera"));
			domicilio.setMunicipio(rs.getString("municipio"));
			ofertaVO.setDomicilio(domicilio);
		}
		return ofertaVO;
	}
	
	@Override
	protected String getQuery() {
		StringBuffer query = new StringBuffer();
		if (QUERY == QUERY_EXISTE_OFERTA) {
			query.append(" SELECT DISTINCT TO_DATE(OC.FECHA_MODIFICACION, 'dd/MM/YYYY') AS OFERTA_FECMOD, ");
			query.append(" 	TO_DATE(OC.FECHA_ALTA, 'dd/MM/YYYY') AS OFERTA_FECALTA, OC.ID_OFERTA_EMPLEO");
			query.append("	FROM OFERTA_EMPLEO OC, OFERTA_EXTERNA OE ");
			query.append(" 	WHERE  OC.ID_OFERTA_EMPLEO = OE.ID_OFERTA_EMPLEO ");
			query.append(" 	AND OE.ID_OFERTA_BOLSA = ? ");
		}else if (QUERY == QUERY_INSERTA_OFERTA_EXTERNA) {
			query.append(" INSERT INTO OFERTA_EXTERNA VALUES(?,?)");
		}else if (QUERY == QUERY_BUSCA_OFERTASRSS){
			query.append("	SELECT TO_CHAR(OE.FECHA_ALTA, 'dd/MM/yyyy') AS FECHA_ALTA, OE.TITULO_OFERTA, ");
			query.append("		CASE WHEN E.ID_TIPO_PERSONA = " + TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona());
			query.append("		THEN  E.NOMBRE||' '||E.APELLIDO1 WHEN E.ID_TIPO_PERSONA = " + TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona());
			query.append("		THEN E.RAZON_SOCIAL END EMPRESA, ENTIDAD.OPCION, OE.ID_OFERTA_EMPLEO");
			query.append("	FROM OFERTA_EMPLEO OE, OFERTA_EXTERNA OEX, ");
			query.append("		EMPRESA E, DOMICILIO DOM, CATALOGO_OPCION ENTIDAD");
			query.append("	WHERE OE.ID_OFERTA_EMPLEO= OEX.ID_OFERTA_EMPLEO ");
			query.append("		AND OE.ESTATUS = " + ESTATUS.ACTIVO.getIdOpcion());
			query.append("		AND OE.ID_EMPRESA = E.ID_EMPRESA");
			query.append("		AND DOM.ID_TIPO_PROPIETARIO        = " + TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
			query.append("		AND DOM.ID_PROPIETARIO = OE.ID_OFERTA_EMPLEO");
			query.append("		AND ENTIDAD.ID_CATALOGO = "+ CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
			query.append("		AND DOM.ID_ENTIDAD = ENTIDAD.ID_CATALOGO_OPCION");
			query.append("		AND DOM.ID_ENTIDAD = ?  AND OE.ID_OCUPACION = ?");			
		}else if(QUERY == QUERY_BUSCA_OFERTASRSS_SIN_PARAMETROS){
			query.append("	SELECT TO_CHAR(OE.FECHA_ALTA, 'dd/MM/yyyy') AS FECHA_ALTA, OE.TITULO_OFERTA, ");
			query.append("		CASE WHEN E.ID_TIPO_PERSONA = " + TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona());
			query.append("		THEN  E.NOMBRE||' '||E.APELLIDO1 WHEN E.ID_TIPO_PERSONA = " + TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona());
			query.append("		THEN E.RAZON_SOCIAL END EMPRESA, DESCCATALOGO(1, " + CATALOGO_OPCION_ENTIDAD_FEDERATIVA + ", dom.id_entidad) AS OPCION, ");
			query.append("		OE.ID_OFERTA_EMPLEO");
			query.append("	FROM OFERTA_EMPLEO OE left join empresa e on oe.id_empresa = e.id_empresa ");
			query.append("		left join domicilio dom on oe.id_oferta_empleo = dom.id_propietario and dom.id_tipo_propietario  = " + TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
			query.append("	WHERE OE.fuente =" + Constantes.ID_FUENTE_SFP);
			query.append("		AND OE.ESTATUS = " + ESTATUS.ACTIVO.getIdOpcion());
			query.append("		ORDER BY OE.FECHA_ALTA DESC ");
		}else if(QUERY == QUERY_BUSCA_TODAS_OFERTASSFP_SIN_PARAMETROS){
			query.append("	SELECT TO_CHAR(OE.FECHA_ALTA, 'dd/MM/yyyy') AS FECHA_ALTA, OE.TITULO_OFERTA, ");
			query.append("		CASE WHEN E.ID_TIPO_PERSONA = " + TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona());
			query.append("		THEN  E.NOMBRE||' '||E.APELLIDO1 WHEN E.ID_TIPO_PERSONA = " + TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona());
			query.append("		THEN E.RAZON_SOCIAL END EMPRESA, DESCCATALOGO(1, " + CATALOGO_OPCION_ENTIDAD_FEDERATIVA + ", dom.id_entidad) AS ENTIDAD, "); 
			query.append("		OE.ID_OFERTA_EMPLEO, OE.SALARIO AS SALARIO, MUN.MUNICIPIO AS CIUDAD, OE.HABILIDAD_GENERAL, ");
			query.append(" CASE");
			query.append(" WHEN (OE.EXPERIENCIA_ANIOS=0)  THEN 'No es requisito' ");	    
			query.append(" ELSE DESCCATALOGO(1, " + CATALOGO_OPCION_EXPERIENCIA + ", OE.EXPERIENCIA_ANIOS) END AS EXPERIENCIA_ANIOS_DESCRIPCION, ");			
			query.append(" CASE");
			query.append(" WHEN (OE.EDAD_REQUISITO = "+EDAD_REQUISITO.SI.getIdOpcion()+") THEN EDAD_MINIMA || ' - ' || EDAD_MAXIMA ");
		    query.append(" ELSE 'No es requisito' END AS EDAD, ");
			query.append(" CASE");
			query.append(" WHEN ((L.ID_OFERTA_EMPLEO IS NULL) OR (L.ID_IDIOMA <= 0)) THEN 'N/A' ");	    
			query.append(" ELSE DESCCATALOGO(1, "+CATALOGO_OPCION_IDIOMAS+" ,L.ID_IDIOMA) || ' ' || DESCCATALOGO(1, "+CATALOGO_OPCION_DOMINIO+", L.ID_DOMINIO) END AS IDIOMAS ");			
			query.append("	FROM OFERTA_EMPLEO OE  ");
			query.append("		left join empresa e on oe.id_empresa = e.id_empresa ");
			query.append("		left join domicilio dom on oe.id_oferta_empleo = dom.id_propietario and dom.id_tipo_propietario  =" + TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
			query.append("		left join municipio mun on dom.id_municipio = mun.id_municipio and dom.id_entidad = mun.id_entidad ");
			query.append("		left join oferta_idioma l on oe.id_oferta_empleo = l.id_oferta_empleo ");	
			query.append("	WHERE OE.fuente =" + Constantes.ID_FUENTE_SFP);
			query.append("		AND OE.ESTATUS = " + ESTATUS.ACTIVO.getIdOpcion());
			query.append("		ORDER BY OE.FECHA_ALTA DESC ");							
		}else if(QUERY == QUERY_BUSCA_OFERTA_SFP){
			query.append("	SELECT TO_CHAR(OE.FECHA_ALTA,'DD/MM/YYYY') FECHA_ALTA, 'Sector Gobierno Federal Central' as AREA_LAB,");
			query.append("		OCUPACION.OPCION OCUPACION, OE.TITULO_OFERTA, OE.FUNCIONES, TIPO_EMPLEO.OPCION TIPO_EMPLEO,");
			query.append("		OE.HORA_ENTRADA ||':00 - ' ||OE.HORA_SALIDA ||':00' HORARIO, OE.NUMERO_PLAZAS,");
			query.append("		OE.SALARIO, ENTIDAD.OPCION ENTIDAD, ");
			query.append("		CASE");
			query.append("		WHEN ((OE.ID_NIVEL_ESTUDIO IS NULL) OR (OE.ID_NIVEL_ESTUDIO <= 0)) THEN 'N/A'");
			query.append("		ELSE F_DESC_CATALOGO(" + CATALOGO_OPCION_GRADO_ESTUDIOS + ", OE.ID_NIVEL_ESTUDIO) "); //8
			query.append("		END AS ESCOLARIDAD,");
			query.append("		ESPEC.ID_CARRERA_ESPECIALIDAD, SIT_ACADEM.OPCION SIT_ACADEM,");
			query.append("		CASE");
			query.append("		WHEN ((OE.EXPERIENCIA_ANIOS IS NULL) OR (OE.EXPERIENCIA_ANIOS <= 0)) THEN 'N/A'");
			query.append("		ELSE F_DESC_CATALOGO(" + CATALOGO_OPCION_EXPERIENCIA + ", OE.EXPERIENCIA_ANIOS) "); //14
			query.append("		END AS EXPERIENCIA,");	
			query.append("		DECODE(OE.DISPONIBILIDAD_VIAJAR, 1, 'SI', 0, 'NO') DISP_VIAJAR,");
			query.append("		DECODE(OE.DISPONIBILIDAD_RADICAR, 1, 'SI', 0, 'NO') DISP_RADICAR,");
			query.append("		OE.EDAD_MINIMA ||' A ' ||OE.EDAD_MAXIMA ||' AÑOS' EDAD_PREF,");			
			query.append("		CASE WHEN E.ID_TIPO_PERSONA =" + TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona());
			query.append("		THEN E.NOMBRE ||' ' ||E.APELLIDO1 ||' ' ||E.APELLIDO2");
			query.append("		WHEN E.ID_TIPO_PERSONA =" + TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona());
			query.append("		THEN E.RAZON_SOCIAL END EMPRESA, OE.HABILIDAD_GENERAL, MUN.MUNICIPIO, CARRERA.OPCION CARRERA");			
			query.append("	FROM OFERTA_EMPLEO OE, OFERTA_EXTERNA OEX, ");
			query.append("		CATALOGO_OPCION OCUPACION, CATALOGO_OPCION TIPO_EMPLEO, DOMICILIO DOM,");
			query.append("		MUNICIPIO MUN, CATALOGO_OPCION ENTIDAD, ");
			query.append("		OFERTA_CARRERA_ESPEC ESPEC, CATALOGO_OPCION SIT_ACADEM,");
			query.append("		EMPRESA E, CATALOGO_OPCION CARRERA");
			query.append("	WHERE OE.ID_OFERTA_EMPLEO          	   = OEX.ID_OFERTA_EMPLEO");
			query.append("		AND OE.ESTATUS                     = " + ESTATUS_ACTIVAS.ALTA_NORMAL.getIdOpcion());
			query.append("		AND OE.ID_OCUPACION                = OCUPACION.ID_CATALOGO_OPCION");
			query.append("		AND OCUPACION.ID_CATALOGO          = " + CATALOGO_OPCION_OCUPACION);
			query.append("		AND OE.ID_TIPO_EMPLEO              = TIPO_EMPLEO.ID_CATALOGO_OPCION");
			query.append("		AND TIPO_EMPLEO.ID_CATALOGO        = " + CATALOGO_OPCION_TIPO_EMPLEO);  //15
			query.append("		AND DOM.ID_TIPO_PROPIETARIO        = " + TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
			query.append("		AND DOM.ID_PROPIETARIO             = OE.ID_OFERTA_EMPLEO");			
			query.append("		AND DOM.ID_ENTIDAD                 = ENTIDAD.ID_CATALOGO_OPCION");
			query.append("		AND ENTIDAD.ID_CATALOGO            = " + CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
			query.append("		AND DOM.ID_ENTIDAD                 = MUN.ID_ENTIDAD ");
			query.append("		AND DOM.ID_MUNICIPIO               = MUN.ID_MUNICIPIO");
			query.append("		AND ESPEC.ID_OFERTA_EMPLEO         = OE.ID_OFERTA_EMPLEO");
			query.append("		AND ESPEC.PRINCIPAL                = ?");
			query.append("		AND SIT_ACADEM.ID_CATALOGO_OPCION  = OE.ID_SITUACION_ACADEMICA");
			query.append("		AND SIT_ACADEM.ID_CATALOGO         = " + CATALOGO_OPCION_SITUACION_ACADEMICA); //10
			query.append("		AND OE.ID_EMPRESA                  = E.ID_EMPRESA");
			query.append("		AND CARRERA.ID_CATALOGO"+ plus +"			   = ? ");
			query.append("		AND ESPEC.ID_CARRERA_ESPECIALIDAD  = CARRERA.ID_CATALOGO_OPCION" + plus);
			query.append("		AND OE.ID_OFERTA_EMPLEO = ?");

		}else if(QUERY == QUERY_BUSCA_CARRERA){
			query.append(" SELECT ID_CARRERA_ESPECIALIDAD,PRINCIPAL FROM (");
			query.append(" SELECT ID_CARRERA_ESPECIALIDAD, DECODE(OCE.PRINCIPAL, 0, 4,OCE.PRINCIPAL) AS PRINCIPAL");
			query.append(" FROM OFERTA_CARRERA_ESPEC OCE WHERE OCE.ID_OFERTA_EMPLEO =?) ORDER BY PRINCIPAL");
		}
		return query.toString();
	}
}