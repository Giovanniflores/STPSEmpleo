package mx.gob.stps.portal.core.crm.service;

import mx.gob.stps.portal.persistencia.vo.CmrArticulosQueTeIntVO;
import mx.gob.stps.portal.persistencia.vo.CmrBannerVO;
import mx.gob.stps.portal.persistencia.vo.CmrPalabrasClavesVO;
import mx.gob.stps.portal.persistencia.vo.CmrUsuarioVO;

import javax.ejb.Remote;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by benjamin.vander on 02/12/2015.
 */
@Remote
public interface CrmAppServiceRemote {

    public void updateArticulo(CmrArticulosQueTeIntVO createArticuloForm) throws Exception;

    public void deleteEtiquetasArticulo(long idArticulo) throws Exception;

    public CmrArticulosQueTeIntVO selectArticulo(long idArticulo ) throws Exception;

    public String[] getEtiquetasArticulosEnString(long idArticulo) throws Exception;

    public Long insertArticulo(CmrArticulosQueTeIntVO articuloForm) throws Exception;

    public List<CmrPalabrasClavesVO> getEtiquetasAlfabetico() throws Exception ;

    public Long createEtiqueta(String etiqueta) throws Exception;

    public Long createEtiquetaArticulo(long idArticulo, long idEtiqueta) throws Exception;

    public List<CmrArticulosQueTeIntVO> getTodoArticulos() throws SQLException, IOException;

    public void deleteArticulo(long id) throws SQLException;

    public void updateUsuario(CmrUsuarioVO usuario);

    public CmrUsuarioVO getUsusario(Long id);

    public Long insertUsusario(CmrUsuarioVO cmrUsuarioVO);

    public void deleteUsuario(CmrUsuarioVO cmrUsuarioVO);

    public boolean existeUsuario(CmrUsuarioVO cmrUsuarioVO) throws SQLException;

    public List<CmrArticulosQueTeIntVO> getArticulosHome() throws IOException,SQLException;

    public List<CmrArticulosQueTeIntVO> getEtiquetasArticulosDesdeEtiqueta(long idEtiqueta) throws IOException, SQLException;

    public Long getIdEtiqueta(String temasEmpleo) throws SQLException;

    public Boolean deleteEtiqueta(Long id) throws SQLException;

    public String getEtiqueta(Long id);

    public void actualizarEtiqueta(CmrPalabrasClavesVO cmrPalabrasClavesVO);

    public void actualizarBanner(CmrBannerVO cmrBannerVO);

    public long guardarBanner(CmrBannerVO cmrBannerVO) throws SQLException;

    public void borrarBanner(CmrBannerVO cmrBannerVO);

    public CmrBannerVO buscarBanner(CmrBannerVO cmrBannerVO) throws SQLException;

    public void deleteBanner(Long id);

    public List<CmrBannerVO> getBanners() throws SQLException;

    public List<CmrBannerVO> getBannersInicio() throws SQLException;
}
