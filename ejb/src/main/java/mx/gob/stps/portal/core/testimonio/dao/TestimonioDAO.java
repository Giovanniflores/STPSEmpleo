/**
 * 
 */
package mx.gob.stps.portal.core.testimonio.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PERSONA;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.testimonio.vo.TestimonioVO;

/**
 * @author concepcion.aguilar
 *
 */
public class TestimonioDAO extends TemplateDAO {
	private final static int QUERY_DATOS_TESTIMONIO = 1;
	private final static int QUERY_TESTIMONIO_ALEATORIO = 2;
	private final static int QUERY_ULTIMO_TESTIMONIO = 3;
	private long VALOR_TIPO_PROPIETARIO = 0;
	private int QUERY = 0;

	public TestimonioDAO(){}
	
	public TestimonioDAO(Connection globalConnection){
		super(globalConnection);
	}
	
	/**
	 * Consulta el nombre completo y la empresa perteneciente al propietario del testimonio
	 * @param vo Tiene el id del propietario y el id del tipo del propietario
	 * @return un objeto VO con el nombre del candidato y la empresa cuando el tipo de propietario es candidato
	 * o el nombres del contacto de la empresa y la empresa cuando el tipo de propietario es empresa
	 * @throws Exception
	 */
	public TestimonioVO datosTestimonio(TestimonioVO vo) throws SQLException {
		VALOR_TIPO_PROPIETARIO = vo.getIdTipoPropietario();
		QUERY = QUERY_DATOS_TESTIMONIO;
		String [] params = {String.valueOf(vo.getIdPropietario())};
		CachedRowSet rs = this.executeQuery(params); 
		if (rs.next()) {
			vo.setNombre(rs.getString(1));
			vo.setEmpresa(rs.getString(2));
		} else {
			throw new SQLException();
		}
		return vo;
	}
	
	/**
	 * Consulta a la base de datos para que esta genere un id de testimonio valido y aleatorio
	 * @return el tipo de propietario, el id de propietario y la descripcion del testimonio
	 * @throws SQLException
	 */
	public TestimonioVO getTestimonioAleatorio() throws SQLException {
		TestimonioVO vo = new TestimonioVO();
		boolean idObtenido = false;
		QUERY = QUERY_TESTIMONIO_ALEATORIO;
		CachedRowSet rs = this.executeQuery();
		int i = 1;
		while(i<=3 && idObtenido == false ){
			if (rs.next()) {
				 idObtenido = true;
				 vo.setIdTipoPropietario(rs.getInt("ID_TIPO_PROPIETARIO"));
				 vo.setIdPropietario( rs.getLong("ID_PROPIETARIO"));
				 vo.setDescripcion(rs.getString("DESCRIPCION"));
			}
			if (idObtenido == false) {
				rs = this.executeQuery();
			}
			i++;
		}
		if (idObtenido == false){
			QUERY = QUERY_ULTIMO_TESTIMONIO;
			rs = this.executeQuery();
			if (rs.next()) {
				vo.setIdTipoPropietario(rs.getInt("ID_TIPO_PROPIETARIO"));
				vo.setIdPropietario( rs.getLong("ID_PROPIETARIO"));
				vo.setDescripcion(rs.getString("DESCRIPCION"));
			}
		}
		return vo;
	}
	
	/**
	 * Consulta el testimonio y el nombre a quien pertenece dicho testimonio
	 * @param vo Trae el id del propietario y el id del tipo de propietario
	 * @return el mismo objeto VO con la descripcion del testimonio y el nombre del 
	 * propietario del testimonio
	 * @throws Exception
	 */
	public TestimonioVO datosTestimonioIndex(TestimonioVO vo) throws SQLException {
		VALOR_TIPO_PROPIETARIO = vo.getIdTipoPropietario();
		QUERY = QUERY_DATOS_TESTIMONIO;

		CachedRowSet rs = this.executeQuery(new Object[]{vo.getIdPropietario()});

		if(rs.next()) {
			if (vo.getIdTipoPropietario() == TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario()){
				vo.setNombre(rs.getString("nombre"));
			}else if (vo.getIdTipoPropietario() == TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario()) {
				vo.setNombre(rs.getString("empresa"));
			}
		}
		
		rs.close();

		return vo;
	}

	@Override
	protected String getQuery() {
		StringBuffer query = new StringBuffer();
		if (QUERY == QUERY_DATOS_TESTIMONIO){
			if (VALOR_TIPO_PROPIETARIO == TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario()){
				/*query.append(" SELECT C.NOMBRE||' '||C.APELLIDO1||' '||C.APELLIDO2 NOMBRE, ");
				query.append("		CASE WHEN E.ID_TIPO_EMPRESA = ");
				query.append( 		TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()); 
				query.append(" 		THEN E.NOMBRE||' '||E.APELLIDO1||' '||E.APELLIDO2 WHEN E.ID_TIPO_EMPRESA = ");
				query.append(		TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona());
				query.append(" 		THEN E.RAZON_SOCIAL END EMPRESA ");
				query.append(" FROM CANDIDATO C, OFERTA_CANDIDATO OC, OFERTA_EMPLEO OE, EMPRESA E ");
				query.append(" WHERE C.ID_CANDIDATO = OC.ID_CANDIDATO(+) ");
				query.append("		AND OC.ID_OFERTA_EMPLEO = OE.ID_OFERTA_EMPLEO(+) ");
				query.append(" 		AND OE.ID_EMPRESA = E.ID_EMPRESA(+) AND C.ID_CANDIDATO = ?");*/

				// TODO TEMPORAL - NO SE CONSIDERA QUE EL CANDIDATO ESTE ASOCIADO A UNA OFERTA
				query.append(" SELECT C.NOMBRE||' '||C.APELLIDO1||' '||C.APELLIDO2 NOMBRE, '' as EMPRESA ");
				query.append(" FROM SOLICITANTE C ");
				query.append(" WHERE C.ID_CANDIDATO = ? ");
				
			} else if (VALOR_TIPO_PROPIETARIO == TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario()) {
				query.append(" SELECT E.CONTACTO_EMPRESA, ");
				query.append(" CASE WHEN E.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()); 
				query.append(" 		THEN E.NOMBRE||' '||E.APELLIDO1||' '||E.APELLIDO2 ");
				query.append("      WHEN E.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona());
				query.append(" 		THEN E.RAZON_SOCIAL END EMPRESA ");
				query.append(" FROM EMPRESA E ");
				query.append(" WHERE E.ID_EMPRESA = ?");
			}
		} else if (QUERY == QUERY_TESTIMONIO_ALEATORIO){
			query.append(" SELECT TES.ID_TIPO_PROPIETARIO, TES.ID_PROPIETARIO, TES.DESCRIPCION ");
			query.append(" FROM (SELECT ID_TESTIMONIO FROM TESTIMONIO WHERE ESTATUS = " + 
					ESTATUS.ACTIVO.getIdOpcion() + " ORDER BY DBMS_RANDOM.VALUE) IDS,");
			query.append("	TESTIMONIO TES");
			query.append(" WHERE TES.ID_TESTIMONIO = IDS.ID_TESTIMONIO AND TES.ESTATUS = " + 
					ESTATUS.ACTIVO.getIdOpcion());
			query.append(" AND ROWNUM = 1");
			
		} else if (QUERY == QUERY_ULTIMO_TESTIMONIO){
			query.append(" SELECT TES.ID_TIPO_PROPIETARIO, TES.ID_PROPIETARIO, TES.DESCRIPCION ");
			query.append(" FROM TESTIMONIO TES WHERE TES.ESTATUS = ");
			query.append(  	ESTATUS.ACTIVO.getIdOpcion());
			query.append(" 	AND ROWNUM = 1 ");
			query.append(" ORDER BY TES.ID_TESTIMONIO DESC");
		}
		return query.toString();
	}
	
}
