package mx.gob.stps.portal.core.oferta.busqueda.dao;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_IDIOMAS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_TIPO_EMPLEO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONTACTO_CORREO;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONTACTO_TELEFONO;
import mx.gob.stps.portal.core.infra.utils.Constantes.EDAD_REQUISITO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PERSONA;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 18/03/2011
 **/
//TODO ELIMINAR CLASE YA NO SE UTILIZA
public class OfertasPorPerfilDAO extends TemplateDAO {

	private OfertasPorPerfilDAO(){}
	
	private OfertasPorPerfilDAO(Connection globalConnection){
		super(globalConnection);
	}
	
	//private ArrayList<ExpectativaLugarVO> expectativasLugar;
    
	/**
	 * Obtiene las ofertas relacionadas con el perfil de una candidato
	 * numRegistros es el numero de registros maximos que se mostraran 
	 * paginados, debe ser mayor que 1
	 * 
	 * @author Mario Alberto Vázquez Flores
	 * @since 18/03/2011
	 * @param int idCandidato
	 * @throws SQLException
	 * @return List<OfertaPorPerfilVO>
	 **/
	/*public List<OfertaPorPerfilVO> obtenerOfertasPorPerfil(long idCandidato, int numRegistros, List<ExpectativaLugarVO> expectativasLugar) throws SQLException {
		setExpectativasLugar(expectativasLugar);
		
		Object[] parametros = { idCandidato, numRegistros };
		CachedRowSet cachedRowSet = executeQuery(parametros);		
		
		List<OfertaPorPerfilVO> rows = new ArrayList<OfertaPorPerfilVO>();

		while (cachedRowSet.next()) {

			OfertaPorPerfilVO row = new OfertaPorPerfilVO();

			row.setIdCandidato(cachedRowSet.getLong(1));
			row.setIdOfertaEmpleo(cachedRowSet.getLong(2));
			row.setTituloOferta(cachedRowSet.getString(3));
			row.setUbicacion(cachedRowSet.getString(4));
			row.setEmpresa(cachedRowSet.getString(5));
			row.setSalario(cachedRowSet.getDouble(6));
			row.setGradoEstudio(cachedRowSet.getString(7));
			row.setCarrera(cachedRowSet.getString(8));
			row.setFunciones(cachedRowSet.getString(9));
			row.setEdad(cachedRowSet.getString(10));
			row.setIdiomas(cachedRowSet.getString(11));
			row.setHorario(cachedRowSet.getString(12));
			row.setNumeroPlazas(cachedRowSet.getInt(13));
			row.setMedioContacto(cachedRowSet.getString(14));
			rows.add(row);
		}

		return rows;
	}*/

	public List<OfertaPorPerfilVO> obtenerOfertasPorPerfil(long idCandidato, int numRegistros) throws SQLException {

		//System.out.println("Numero de Registros: " + numRegistros);		
		
		Object[] parametros = { idCandidato, numRegistros };
		CachedRowSet cachedRowSet = executeQuery(parametros);		
		
		List<OfertaPorPerfilVO> rows = new ArrayList<OfertaPorPerfilVO>();
		
		while (cachedRowSet.next()) {
		
			OfertaPorPerfilVO row = new OfertaPorPerfilVO();
		
			row.setIdCandidato(cachedRowSet.getLong(1));
			row.setIdOfertaEmpleo(cachedRowSet.getLong(2));
			row.setTituloOferta(cachedRowSet.getString(3));
			row.setUbicacion(cachedRowSet.getString(4));
			row.setEmpresa(cachedRowSet.getString(5));
			row.setSalario(cachedRowSet.getDouble(6));
			row.setGradoEstudio(cachedRowSet.getString(7));
			row.setCarrera(cachedRowSet.getString(8));
			row.setFunciones(cachedRowSet.getString(9));
			row.setEdad(cachedRowSet.getString(10));
			row.setIdiomas(cachedRowSet.getString(11));
			row.setHorario(cachedRowSet.getString(12));
			row.setNumeroPlazas(cachedRowSet.getInt(13));
			row.setMedioContacto(cachedRowSet.getString(14));
			rows.add(row);
		}
		
		return rows;
	}
	
	
	@Override
	protected String getQuery() {
		return this.execQueryBusquedaPorPerfil();
	}

	private String execQueryBusquedaPorPerfil() {

		StringBuffer sqlString = new StringBuffer();
		
		sqlString.append(" SELECT * ");
		sqlString.append(" FROM ("); 
		
		sqlString.append("SELECT DISTINCT  A.ID_CANDIDATO, H.ID_OFERTA_EMPLEO, H.TITULO_OFERTA, G.OPCION || ', ' || F.MUNICIPIO AS UBICACION");
		
		sqlString.append(", CASE");
		sqlString.append(" WHEN I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() + " THEN I.NOMBRE || ' ' || I.APELLIDO1 || ' ' || I.APELLIDO2");
		sqlString.append(" WHEN I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() + " THEN I.RAZON_SOCIAL");
		//sqlString.append(" WHEN ((H.ID_TERCERA_EMPRESA IS NOT NULL AND H.ID_TERCERA_EMPRESA > 0) AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() + ")) THEN J.NOMBRE || ' ' || J.APELLIDO1 || ' ' || J.APELLIDO2");
		//sqlString.append(" WHEN ((H.ID_TERCERA_EMPRESA IS NOT NULL AND H.ID_TERCERA_EMPRESA > 0) AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() + ")) THEN J.RAZON_SOCIAL");
		sqlString.append(" ELSE ' ' END AS EMPRESA");
		
		sqlString.append(" ,H.SALARIO");

		sqlString.append(" ,F_DESC_CATALOGO(" + CATALOGO_OPCION_GRADO_ESTUDIOS +  ", H.ID_NIVEL_ESTUDIO) AS GRADO_ESTUDIO ");

		sqlString.append(" ,CASE");
		sqlString.append(" WHEN ((K.ID_OFERTA_EMPLEO IS NULL) OR (K.ID_CARRERA_ESPECIALIDAD <= 0)) THEN ' '");
		sqlString.append(" ELSE F_DESC_CATALOGO(F_CAT_ASOCIADO(" + CATALOGO_OPCION_GRADO_ESTUDIOS + ", H.ID_NIVEL_ESTUDIO), K.ID_CARRERA_ESPECIALIDAD)");
		sqlString.append(" END AS CARRERA");

		sqlString.append(" ,H.FUNCIONES ");

		sqlString.append(" ,CASE ");
		sqlString.append(" WHEN (H.EDAD_REQUISITO = "+ EDAD_REQUISITO.SI.getIdOpcion()+ ") THEN EDAD_MINIMA || ' - ' || EDAD_MAXIMA");
		sqlString.append("   ELSE '' END AS EDAD");

		sqlString.append(" ,CASE");
		sqlString.append(" WHEN ((L.ID_OFERTA_EMPLEO IS NULL) OR (L.ID_IDIOMA <= 0)) THEN ' '");
		sqlString.append(" ELSE F_DESC_CATALOGO(" + CATALOGO_OPCION_IDIOMAS + ", L.ID_IDIOMA)");
		sqlString.append(" END AS IDIOMA");
				
		sqlString.append(" ,F_DESC_CATALOGO(" + CATALOGO_OPCION_TIPO_EMPLEO + ", H.ID_TIPO_EMPLEO) AS HORARIO ");
		
		sqlString.append(" ,H.NUMERO_PLAZAS ");
		
		sqlString.append(" ,CASE");
		sqlString.append("   WHEN ((H.CONTACTO_TEL = " + CONTACTO_TELEFONO.SI.getIdContactoTelefono() + ")  AND (H.CONTACTO_CORREO = " + CONTACTO_CORREO.SI.getIdContactoCorreo() + ")) THEN 'Teléfono y Correo Electrónico '");
		sqlString.append("   WHEN ((H.CONTACTO_TEL = " + CONTACTO_TELEFONO.SI.getIdContactoTelefono() + ")  AND (H.CONTACTO_CORREO = " + CONTACTO_CORREO.NO.getIdContactoCorreo() + ")) THEN 'Teléfono'");
		sqlString.append("   WHEN ((H.CONTACTO_TEL = " + CONTACTO_TELEFONO.NO.getIdContactoTelefono() + ")  AND (H.CONTACTO_CORREO = " + CONTACTO_CORREO.SI.getIdContactoCorreo() + ")) THEN 'Correo Electrónico'");
		sqlString.append("   ELSE ' '");
	    sqlString.append(" END AS CONTACTO");

		sqlString.append(" FROM");
		sqlString.append(" PERFIL_LABORAL A");
		sqlString.append(" ,EXPECTATIVA_LABORAL B");
		sqlString.append(" ,EXPECTATIVA_LUGAR C");
		sqlString.append(" ,DOMICILIO D");
		sqlString.append(" ,OFERTA_SECTOR E");
		sqlString.append(" ,MUNICIPIO F");
		sqlString.append(" ,CATALOGO_OPCION G");
		sqlString.append(" ,OFERTA_EMPLEO H");
		sqlString.append(" ,EMPRESA I");
		//sqlString.append(" ,TERCERA_EMPRESA J");
		sqlString.append(" ,OFERTA_CARRERA_ESPEC K");
		sqlString.append(" ,OFERTA_IDIOMA L");
		
		sqlString.append(" WHERE");
		sqlString.append(" A.ID_CANDIDATO = ?");
		sqlString.append(" AND A.ID_CANDIDATO = B.ID_CANDIDATO");
		sqlString.append(" AND A.ID_CANDIDATO = C.ID_CANDIDATO");

		//sqlString.append(" AND C.ID_ENTIDAD_DESEADA = D.ID_ENTIDAD");
		//sqlString.append(" AND C.ID_MUNICIPIO_DESEADO = D.ID_MUNICIPIO");
		/*if(expectativasLugar.size()>0){
			sqlString.append(" AND(");
			for(int i=0; i<expectativasLugar.size(); i++){				
				ExpectativaLugarVO vo = (ExpectativaLugarVO)expectativasLugar.get(i);
								
				sqlString.append("    (D.ID_ENTIDAD = " + String.valueOf(vo.getIdEntidadDeseada()));
				sqlString.append("    AND D.ID_MUNICIPIO = " + String.valueOf(vo.getIdMunicipioDeseado()) + ") " );						
				if(i<expectativasLugar.size()-1){
					sqlString.append(" OR ");
				} 
			}
			sqlString.append(" ) ");			
		}*/
		
		sqlString.append(" AND D.ID_TIPO_PROPIETARIO = " + TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());

		
		sqlString.append(" AND B.ID_SECTOR_DESEADO = E.ID_SECTOR");
		sqlString.append(" AND D.ID_PROPIETARIO = E.ID_OFERTA_EMPLEO");

		sqlString.append(" AND D.ID_ENTIDAD = F.ID_ENTIDAD");
		sqlString.append(" AND D.ID_MUNICIPIO = F.ID_MUNICIPIO");

		sqlString.append(" AND G.ID_CATALOGO = " + CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
		sqlString.append(" AND D.ID_ENTIDAD = G.ID_CATALOGO_OPCION");

		sqlString.append(" AND E.ID_OFERTA_EMPLEO = H.ID_OFERTA_EMPLEO");
		sqlString.append(" AND B.ID_AREA_LABORAL_DESEADA = H.ID_AREA_LABORAL");
		sqlString.append(" AND B.SALARIO_PRETENDIDO <= H.SALARIO");

		sqlString.append(" AND B.ID_OCUPACION_DESEADA = H.ID_OCUPACION");
		sqlString.append(" AND B.ID_TIPO_EMPLEO_DESEADO = H.ID_TIPO_EMPLEO");
		sqlString.append(" AND H.ESTATUS = " + ESTATUS.ACTIVO.getIdOpcion());

		sqlString.append(" AND A.DISPONIBILIDAD_VIAJAR = H.DISPONIBILIDAD_VIAJAR");
		sqlString.append(" AND A.DISPONIBILIDAD_RADICAR = H.DISPONIBILIDAD_RADICAR");

		sqlString.append(" AND H.ID_EMPRESA = I.ID_EMPRESA");
		//sqlString.append(" AND H.ID_EMPRESA = J.ID_EMPRESA(+)");
		//sqlString.append(" AND H.ID_TERCERA_EMPRESA = J.ID_TERCERA_EMPRESA(+)");
		
		sqlString.append(" AND H.ID_OFERTA_EMPLEO = K.ID_OFERTA_EMPLEO(+)");
		sqlString.append(" AND H.ID_OFERTA_EMPLEO = L.ID_OFERTA_EMPLEO(+)");
		sqlString.append(" AND NVL(L.PRINCIPAL, "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +" ) = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +" ");

		sqlString.append(" ) WHERE ROWNUM < ?");
				
		return sqlString.toString();
	}
	
    /*private void setExpectativasLugar(List<ExpectativaLugarVO> expectativasLugar){
    	this.expectativasLugar = (ArrayList<ExpectativaLugarVO>) expectativasLugar;
    }*/

}
