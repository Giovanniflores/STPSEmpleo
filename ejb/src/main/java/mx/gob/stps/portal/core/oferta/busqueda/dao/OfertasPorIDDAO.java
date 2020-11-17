package mx.gob.stps.portal.core.oferta.busqueda.dao;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_OCUPACION;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PERSONA;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.core.oferta.detalle.helper.Utils;

public class OfertasPorIDDAO extends TemplateDAO {

	public OfertasPorIDDAO(){}
	
	public OfertasPorIDDAO(Connection connectionGlobal){
		super(connectionGlobal);
	}
	
	public OfertaPorPerfilVO obtenerOfertasPorID(long idOfertaEmpleo)throws SQLException {

		Object[] parametros = {idOfertaEmpleo};
		CachedRowSet cachedRowSet = executeQuery(parametros);

		while (cachedRowSet.next()) {
			StringBuilder contacto = new StringBuilder();
			OfertaPorPerfilVO oferta = new OfertaPorPerfilVO();
			oferta.setIdEmpresa(cachedRowSet.getLong(1));
			oferta.setIdOfertaEmpleo(cachedRowSet.getLong(2));
			oferta.setTituloOferta(cachedRowSet.getString(3));
			oferta.setUbicacion(cachedRowSet.getString(4));
			oferta.setEmpresa(cachedRowSet.getString(5));
			oferta.setSalario(cachedRowSet.getDouble(6));
			oferta.setFuente(cachedRowSet.getInt(7));
			oferta.setFunciones(cachedRowSet.getString(8));
			if (cachedRowSet.getInt(9) == Constantes.EDAD_REQUISITO.SI.getIdOpcion())
				oferta.setEdad("De " + cachedRowSet.getInt(10) + " a " + cachedRowSet.getInt(11));
			oferta.setHorario(Utils.getTipoEmpleo(cachedRowSet.getInt(12)));
			oferta.setNumeroPlazas(cachedRowSet.getInt(13));
			if (cachedRowSet.getInt(14) == Constantes.CONTACTO_TELEFONO.SI.getIdContactoTelefono())
				contacto.append("Teléfono");
			if (cachedRowSet.getInt(15) == Constantes.CONTACTO_CORREO.SI.getIdContactoCorreo()) {
				if (contacto.length() > 0) contacto.append(" y Correo Eletrónico");
				else contacto.append("Correo Eletrónico");
			}
			oferta.setMedioContacto(contacto.toString());
			oferta.setGradoEstudio(cachedRowSet.getString(16));
			oferta.setCarrera(cachedRowSet.getString(17));
			oferta.setOcupacion(cachedRowSet.getString(18));
			
			return oferta;
		}

		return new OfertaPorPerfilVO();
	}

	@Override
	protected String getQuery() {
		return this.execQueryBusquedaPorId();
	}

	/**
	private String execQueryBusquedaPorId() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT H.ID_EMPRESA, H.ID_OFERTA_EMPLEO, H.TITULO_OFERTA, G.OPCION || ', ' || F.MUNICIPIO AS UBICACION,");
		//query.append(" CASE WHEN I.ID_TIPO_PERSONA = " + TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() + " THEN I.NOMBRE || ' ' || I.APELLIDO1 || ' ' || I.APELLIDO2");
		//query.append(" WHEN I.ID_TIPO_PERSONA = " + TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() + " THEN I.RAZON_SOCIAL");
		//query.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) != 0) AND (I.ID_TIPO_PERSONA = " + TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() + ")) THEN J.NOMBRE || ' ' || J.APELLIDO1 || ' ' || J.APELLIDO2");
		//query.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) != 0 ) AND (I.ID_TIPO_PERSONA = " + TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() + ")) THEN J.RAZON_SOCIAL ");
		query.append(" H.NOMBRE_EMPRESA AS EMPRESA,");
		query.append(" H.SALARIO, H.FUENTE, H.FUNCIONES, H.EDAD_REQUISITO, H.EDAD_MINIMA, H.EDAD_MAXIMA, H.ID_TIPO_EMPLEO, H.NUMERO_PLAZAS, H.CONTACTO_TEL, H.CONTACTO_CORREO, DESCCATALOGO(1, " + CATALOGO_OPCION_GRADO_ESTUDIOS + ", H.ID_NIVEL_ESTUDIO) AS GRADO_ESTUDIO, DESCCATALOGO(2, " + CATALOGO_OPCION_GRADO_ESTUDIOS + ", H.ID_NIVEL_ESTUDIO,");
		query.append(" K.ID_CARRERA_ESPECIALIDAD), DESCCATALOGO(1, " + CATALOGO_OPCION_OCUPACION + ", H.ID_OCUPACION)");
		query.append(" FROM EMPRESA I, OFERTA_EMPLEO H");
		//query.append(" LEFT JOIN TERCERA_EMPRESA J ON H.ID_TERCERA_EMPRESA = J.ID_TERCERA_EMPRESA");
		query.append(" LEFT JOIN OFERTA_CARRERA_ESPEC K ON H.ID_OFERTA_EMPLEO = K.ID_OFERTA_EMPLEO");
		query.append(" AND K.PRINCIPAL = " + MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		query.append(" LEFT JOIN OFERTA_UBICACION D ON H.ID_OFERTA_EMPLEO = D.ID_OFERTA_EMPLEO ");
		query.append(" LEFT JOIN MUNICIPIO F ON D.ID_ENTIDAD = F.ID_ENTIDAD AND D.ID_MUNICIPIO = F.ID_MUNICIPIO");
		query.append(" LEFT JOIN CATALOGO_OPCION G ON D.ID_ENTIDAD = G.ID_CATALOGO_OPCION AND G.ID_CATALOGO = " + CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
		//query.append(" WHERE H.ID_OFERTA_EMPLEO = ? AND H.ESTATUS = " + ESTATUS.ACTIVO.getIdOpcion() + " AND H.ID_EMPRESA = I.ID_EMPRESA");
		query.append(" WHERE H.ID_OFERTA_EMPLEO = ? AND H.ID_EMPRESA = I.ID_EMPRESA");
		return query.toString();		
	}**/

	private String execQueryBusquedaPorId() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT H.ID_EMPRESA, H.ID_OFERTA_EMPLEO, H.TITULO_OFERTA, G.OPCION || ', ' || F.MUNICIPIO AS UBICACION,");
		query.append(" H.NOMBRE_EMPRESA AS EMPRESA,");
		query.append(" H.SALARIO, H.FUENTE, H.FUNCIONES, H.EDAD_REQUISITO, H.EDAD_MINIMA, H.EDAD_MAXIMA, H.ID_TIPO_EMPLEO, H.NUMERO_PLAZAS, H.CONTACTO_TEL, H.CONTACTO_CORREO, DESCCATALOGO(1, " + CATALOGO_OPCION_GRADO_ESTUDIOS + ", H.ID_NIVEL_ESTUDIO) AS GRADO_ESTUDIO, DESCCATALOGO(2, " + CATALOGO_OPCION_GRADO_ESTUDIOS + ", H.ID_NIVEL_ESTUDIO,");
		query.append(" K.ID_CARRERA_ESPECIALIDAD), A.DESCRIPCION");
		
		query.append(" FROM EMPRESA I, OFERTA_EMPLEO H");

		query.append(" LEFT JOIN OFERTA_CARRERA_ESPEC K ON H.ID_OFERTA_EMPLEO = K.ID_OFERTA_EMPLEO");
		query.append(" AND K.PRINCIPAL = " + MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		query.append(" LEFT JOIN OFERTA_UBICACION D ON H.ID_OFERTA_EMPLEO = D.ID_OFERTA_EMPLEO ");
		query.append(" LEFT JOIN MUNICIPIO F ON D.ID_ENTIDAD = F.ID_ENTIDAD AND D.ID_MUNICIPIO = F.ID_MUNICIPIO");
		query.append(" LEFT JOIN CATALOGO_OPCION G ON D.ID_ENTIDAD = G.ID_CATALOGO_OPCION AND G.ID_CATALOGO = " + CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
		query.append(" LEFT JOIN CAT_AREA A ON H.ID_AREA = A.ID_AREA");
		//query.append(" WHERE H.ID_OFERTA_EMPLEO = ? AND H.ESTATUS = " + ESTATUS.ACTIVO.getIdOpcion() + " AND H.ID_EMPRESA = I.ID_EMPRESA");
		query.append(" WHERE H.ID_OFERTA_EMPLEO = ? AND H.ID_EMPRESA = I.ID_EMPRESA");
		return query.toString();		
	}
}
