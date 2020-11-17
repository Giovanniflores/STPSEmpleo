package mx.gob.stps.portal.core.search.service;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.utils.Constantes.OCUPATE_CAMPO_ORDEN;
import mx.gob.stps.portal.core.infra.utils.Constantes.OCUPATE_ORDEN_DIRECCION;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.core.search.Query;
import mx.gob.stps.portal.core.search.ResultInfoBO;
import mx.gob.stps.portal.core.search.SearchMessage;

@Remote
public interface PortalEmpleoBuscadorServiceRemote {

    List<CandidatoVo> buscarCandidatos(String cadena) throws TechnicalException, SQLException;
    List<CandidatoVo> buscarCandidatos(Query query, long idOferta, int records) throws TechnicalException, SQLException;
    List<CandidatoVo> buscarCandidatos(String cadena, int indiceInicial, int indiceFinal) throws TechnicalException, SQLException;	
    @SuppressWarnings("rawtypes")
    List buscarOfertasOcupate(int page, List<ResultInfoBO> results) throws TechnicalException;
    List<OfertaPorCanalVO> buscarOfertasOcupateWS(int page, List<Long> index) throws TechnicalException, SQLException;
    @SuppressWarnings("rawtypes")
    List buscarOfertasEspecificas(int page, List<Long> index, String pattern) throws TechnicalException, SQLException;
    List<Long> buscarOfertasEmpleo(String search, Integer estado, String discapacidad, Integer fuente, Integer edad); //common
    List<ResultInfoBO> buscarOfertasEmpleo(String search, Integer estado) throws TechnicalException, SQLException;
    List<ResultInfoBO> buscarOfertasEmpleo(String search, Integer estado,
        OCUPATE_CAMPO_ORDEN ocupateCampo, OCUPATE_ORDEN_DIRECCION direccion) throws TechnicalException, SQLException;
    List<ResultInfoBO> buscarOfertasEmpleo(String search, Integer estado, Integer municipio, String discapacidad, Integer fuente, Integer edad,
        OCUPATE_CAMPO_ORDEN ocupateCampo, OCUPATE_ORDEN_DIRECCION direccion) throws TechnicalException, SQLException;
    List<Long> buscarCandidatosEmpleo(String cadena) throws TechnicalException, SQLException;
    List<Long> buscarCandidatosEmpleo(String search, Integer idEntidad) throws TechnicalException, SQLException;    
    @SuppressWarnings("rawtypes")
    List buscadorCandidatos(int page, List<?> index) throws TechnicalException, SQLException;
    List<OfertaPorPerfilVO> buscarOfertas(Query query, int rows) throws SQLException;
    List<OfertaPorPerfilVO> buscarOfertas(Query query, int rows, Integer fuente) throws SQLException;
    List<Long> busquedaEspecifica(int entidad, int area, int escolaridad, int salario, int idMunicipio, int ocupacion,
        int carrera, int edad, int region, String tipoOrden, int columna, int fuente) throws TechnicalException, SQLException;
    List<Long> busquedaEspecificaMultiple(int entidad, int area, int escolaridad, int salario, int idMunicipio, List<Integer> idsOcupaciones,
    List<Integer> idsCarreras, int edad, int region) throws TechnicalException, SQLException;
    String busquedaEspecificaLbl(int entidad, int area, int escolaridad, int salario, int idMunicipio, int ocupacion, int carrera, int edad,
        int region, String descripcionCarreraUOcupacionOLA, int fuente) throws TechnicalException, SQLException;
    String busquedaEspecificaMultipleLbl(int entidad, int area, int escolaridad, int salario, int idMunicipio, List<Integer> idsOcupaciones, 
        List<Integer> idsCarreras, int edad, int region, String descripcionCarreraUOcupacionOLA) throws TechnicalException, SQLException;
    List<CandidatoVo> buscadorCandidatos(List<Long> index) throws TechnicalException, SQLException;
    List<CandidatoVo> busquedaCandidatos(long idOfertaEmpleo) throws Exception;
    List<OfertaPorPerfilVO> buscaOfertasRecomendadas(long idCandidato);
    List<OfertaPorPerfilVO> buscarOfertasRecomendadas(long idCandidato, int compatibilidadLimite);
    
    //---------------METODOS CON LA FUNCIONALIDAD DE MENSAJES------------------------------------------------------------------------------
    SearchMessage MsgBuscarOfertasEmpleo(String search, Integer estado,OCUPATE_CAMPO_ORDEN ocupateCampo, OCUPATE_ORDEN_DIRECCION direccion) 
    		throws TechnicalException, SQLException;
    
    SearchMessage MsgBuscarOfertasEmpleo(String search, Integer estado) throws TechnicalException, SQLException;
    
    SearchMessage MsgBuscarOfertasEmpleo(String search, Integer estado, Integer municipio, String discapacidad, Integer fuente, Integer edad,
            OCUPATE_CAMPO_ORDEN ocupateCampo, OCUPATE_ORDEN_DIRECCION direccion) throws TechnicalException, SQLException;
    
    SearchMessage MsgBuscarCandidatosEmpleo(String cadena) throws TechnicalException, SQLException;
    SearchMessage MsgBuscarCandidatosEmpleo(String cadena, Integer idEntidad) throws TechnicalException, SQLException;
    
}