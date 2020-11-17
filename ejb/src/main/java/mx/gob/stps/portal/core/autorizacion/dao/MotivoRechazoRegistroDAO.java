package mx.gob.stps.portal.core.autorizacion.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;


public class MotivoRechazoRegistroDAO extends TemplateDAO  {

	private final static int QUERY_MOTIVO_OFERTAS   	= 1;
	private final static int QUERY_MOTIVO_EMPRESAS  	= 2;
	private final static int QUERY_MOTIVO_VIDEOC    	= 3;
	private final static int QUERY_MOTIVO_TESTIMONIO	= 4;	
	private final static int QUERY_REQUIERE_DETALLE	    = 5;	
	
	private int QUERY = 0;

	public MotivoRechazoRegistroDAO(){
	}
	
	public MotivoRechazoRegistroDAO(Connection globalConnection){
		super(globalConnection);
	}

	@Override
	protected String getQuery() {
		StringBuilder query = new StringBuilder();
		
		switch (QUERY){
			case QUERY_MOTIVO_OFERTAS:
				query = getQueryMotivoOfertas(); break;
			
			case QUERY_MOTIVO_EMPRESAS:
				query = getQueryMotivoEmpresas(); break;
				
			case QUERY_MOTIVO_VIDEOC:
				query = getQueryMotivoVideoCurriculum(); break;

			case QUERY_MOTIVO_TESTIMONIO:
				query = getQueryMotivoTestimonio(); break;
				
			case QUERY_REQUIERE_DETALLE:
				query = getQueryRequiereDetalle(); break;				

		}
		
		return query.toString();
	}	
	
	/*  */
	public List<CatalogoOpcionVO> consultaMotivos(int idTipoRegistro) throws SQLException {
		if(Constantes.TIPO_REGISTRO.OFERTA.getIdTipoRegistro()==idTipoRegistro){
			QUERY = QUERY_MOTIVO_OFERTAS;
		} else if(Constantes.TIPO_REGISTRO.EMPRESA.getIdTipoRegistro()==idTipoRegistro){
			QUERY = QUERY_MOTIVO_EMPRESAS;
		} else if(Constantes.TIPO_REGISTRO.VIDEO_CURRICULO.getIdTipoRegistro()==idTipoRegistro){
			QUERY = QUERY_MOTIVO_VIDEOC;
		} else if(Constantes.TIPO_REGISTRO.TESTIMONIO.getIdTipoRegistro()==idTipoRegistro){
			QUERY = QUERY_MOTIVO_TESTIMONIO;
		}
	
		Object[] params = {Constantes.ESTATUS.ACTIVO.getIdOpcion(), idTipoRegistro};			
		
		CachedRowSet rowSet = executeQuery(params);
		
		List<CatalogoOpcionVO> motivos = new ArrayList<CatalogoOpcionVO>();
		CatalogoOpcionVO motivo = null;
		
		while (rowSet.next()){
			motivo = new CatalogoOpcionVO();
			motivo.setIdCatalogoOpcion(rowSet.getLong("ID_VALIDACION_MOTIVO"));
			motivo.setOpcion(rowSet.getString("DESCRIPCION"));
			motivos.add(motivo);			
		}
		
		return motivos;
		
	}	
		
	
	public long[] consultaRequiereDetalle() throws SQLException{
		long[] reqDetalle = new long[50];
		
		int count = 0;
		QUERY = QUERY_REQUIERE_DETALLE;
		Object[] params = {Constantes.ESTATUS.ACTIVO.getIdOpcion(), Constantes.MOTIVO_DETALLE_REQUERIDO};
		
		
		
		CachedRowSet rowSet = executeQuery(params);
		
		while (rowSet.next()){
			reqDetalle[count] = rowSet.getLong("ID_VALIDACION_MOTIVO");
			count++;
		}
		
		reqDetalle = (long[])resizeArray(reqDetalle,count+1);
		
		return reqDetalle;
	}
	
	
	
	
	private StringBuilder getQueryRequiereDetalle(){
		StringBuilder query = new StringBuilder();

		query.append("SELECT ID_VALIDACION_MOTIVO ");
		query.append(" FROM VALIDACION_MOTIVO ");
		query.append(" WHERE ESTATUS = ? AND REQUIERE_DETALLE = ? ORDER BY ID_VALIDACION_MOTIVO ");
		return query;
	}	
	
	
	private StringBuilder getQueryMotivoOfertas(){
		StringBuilder query = new StringBuilder();

		query.append("SELECT ID_VALIDACION_MOTIVO, DESCRIPCION, ID_TIPO_REGISTRO, REQUIERE_DETALLE ");
		query.append(" FROM VALIDACION_MOTIVO ");
		query.append(" WHERE ESTATUS = ? AND ID_TIPO_REGISTRO = ? ORDER BY DESCRIPCION ");
		return query;
	}	

	
	private StringBuilder getQueryMotivoEmpresas(){
		StringBuilder query = new StringBuilder();

		query.append("SELECT ID_VALIDACION_MOTIVO, DESCRIPCION, ID_TIPO_REGISTRO, REQUIERE_DETALLE ");
		query.append(" FROM VALIDACION_MOTIVO ");
		query.append(" WHERE ESTATUS = ? AND ID_TIPO_REGISTRO = ? ORDER BY DESCRIPCION ");
		return query;
	}		
	
	private StringBuilder getQueryMotivoVideoCurriculum(){
		StringBuilder query = new StringBuilder();

		query.append("SELECT ID_VALIDACION_MOTIVO, DESCRIPCION, ID_TIPO_REGISTRO, REQUIERE_DETALLE ");
		query.append(" FROM VALIDACION_MOTIVO ");
		query.append(" WHERE ESTATUS = ? AND ID_TIPO_REGISTRO = ? ORDER BY DESCRIPCION ");
		return query;
	}	
	
	
	private StringBuilder getQueryMotivoTestimonio(){
		StringBuilder query = new StringBuilder();

		query.append("SELECT ID_VALIDACION_MOTIVO, DESCRIPCION, ID_TIPO_REGISTRO, REQUIERE_DETALLE ");
		query.append(" FROM VALIDACION_MOTIVO ");
		query.append(" WHERE ESTATUS = ? AND ID_TIPO_REGISTRO = ? ORDER BY DESCRIPCION ");
		return query;
	}		
	
	private static Object resizeArray (Object oldArray, int newSize) {
		   int oldSize = java.lang.reflect.Array.getLength(oldArray);
		   Class elementType = oldArray.getClass().getComponentType();
		   Object newArray = java.lang.reflect.Array.newInstance(
		         elementType,newSize);
		   int preserveLength = Math.min(oldSize,newSize);
		   if (preserveLength > 0)
		      System.arraycopy (oldArray,0,newArray,0,preserveLength);
		   return newArray; 
	}	

	
}
