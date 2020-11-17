package mx.gob.stps.portal.core.crm.service.impl;

import mx.gob.stps.portal.core.crm.dao.ArticulosDeSuInteressDAO;
import mx.gob.stps.portal.core.crm.persistencia.facade.*;
import mx.gob.stps.portal.core.crm.service.CrmAppServiceLocal;
import mx.gob.stps.portal.core.crm.service.CrmAppServiceRemote;
import mx.gob.stps.portal.persistencia.vo.CmrArticulosQueTeIntVO;
import mx.gob.stps.portal.persistencia.vo.CmrBannerVO;
import mx.gob.stps.portal.persistencia.vo.CmrPalabrasClavesVO;
import mx.gob.stps.portal.persistencia.vo.CmrUsuarioVO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by benjamin.vander on 02/12/2015.
 */
@Stateless(name = "CrmAppService", mappedName = "CrmAppService")
public class CrmAppService implements CrmAppServiceLocal,CrmAppServiceRemote {

    @EJB
    private ArticulosQueTeIntFacadeLocal articulosQueTeIntFacadeLocal;
    @EJB
    private EtiquetaArticuloFacadeLocal etiquetaArticuloFacadeLocal;
    @EJB
    private EtiquetaFacadeLocal etiquetaFacadeLocal;
    @EJB
    private UsuarioCrmFacadeLocal usuarioFacadeLocal;
    @EJB
    private BannerFacadeLocal bannerFacadeLocal;

    private ArticulosDeSuInteressDAO articulosDeSuInteressDAO = new ArticulosDeSuInteressDAO();

    @Override
    public void updateArticulo(CmrArticulosQueTeIntVO vo) throws Exception {
        articulosQueTeIntFacadeLocal.update(vo);

    }

    @Override
    public void deleteEtiquetasArticulo(long idArticulo) throws Exception {
        articulosDeSuInteressDAO.deleteEtiquetasArticulo(idArticulo);
    }

    @Override
    public CmrArticulosQueTeIntVO selectArticulo(long idArticulo) throws Exception {
        CmrArticulosQueTeIntVO cmrArticulosQueTeIntVO = articulosQueTeIntFacadeLocal.get(idArticulo);
        cmrArticulosQueTeIntVO.setCmrPalabrasClavesArticulos(articulosDeSuInteressDAO.getEtiquetasArticulo(cmrArticulosQueTeIntVO.getId()));
        return cmrArticulosQueTeIntVO;
    }

    @Override
    public String[] getEtiquetasArticulosEnString(long idArticulo) throws Exception {
        return articulosDeSuInteressDAO.getEtiquetasArticuloString(idArticulo);

    }

    @Override
    public Long insertArticulo(CmrArticulosQueTeIntVO vo) throws Exception {
        return articulosQueTeIntFacadeLocal.save(vo);
    }

    @Override
    public List<CmrPalabrasClavesVO> getEtiquetasAlfabetico() throws Exception {
        return articulosDeSuInteressDAO.getEtiquetasAlfabetico();
    }

    @Override
    public Long createEtiqueta(String etiqueta) throws Exception {
        return etiquetaFacadeLocal.save(etiqueta);
    }

    @Override
    public Long createEtiquetaArticulo(long idArticulo, long idEtiqueta) throws Exception {
        return etiquetaArticuloFacadeLocal.save(idArticulo,idEtiqueta);
    }

    @Override
    public List<CmrArticulosQueTeIntVO> getTodoArticulos() throws SQLException, IOException {
       return articulosDeSuInteressDAO.getArticulosCompleto();
    }

    @Override
    public void deleteArticulo(long id) throws SQLException {
        articulosDeSuInteressDAO.deleteArticulo(id);
    }

    @Override
    public void updateUsuario(CmrUsuarioVO usuario) {
        usuarioFacadeLocal.update(usuario);
    }

    @Override
    public CmrUsuarioVO getUsusario(Long id) {
        return usuarioFacadeLocal.get(id);
    }

    @Override
    public Long insertUsusario(CmrUsuarioVO cmrUsuarioVO) {
        return usuarioFacadeLocal.save(cmrUsuarioVO);
    }

    @Override
    public void deleteUsuario(CmrUsuarioVO cmrUsuarioVO){
        usuarioFacadeLocal.delete(cmrUsuarioVO);
    }

    @Override
    public boolean existeUsuario(CmrUsuarioVO cmrUsuarioVO) throws SQLException {
        return articulosDeSuInteressDAO.existeUsuario(cmrUsuarioVO.getUsuario(),cmrUsuarioVO.getClave());
    }

    @Override
    public List<CmrArticulosQueTeIntVO> getArticulosHome() throws SQLException, IOException {
        return articulosDeSuInteressDAO.getArticulosHome();
    }

    @Override
    public List<CmrArticulosQueTeIntVO> getEtiquetasArticulosDesdeEtiqueta(long idEtiqueta) throws IOException, SQLException {
        return articulosDeSuInteressDAO.getArticulosDesdeEtiqueta(idEtiqueta);

    }

    @Override
    public Long getIdEtiqueta(String temasEmpleo) throws SQLException {
        return articulosDeSuInteressDAO.getIdEtiqueta(temasEmpleo);
    }

    @Override
    public Boolean deleteEtiqueta(Long id) throws SQLException {
        articulosDeSuInteressDAO.deleteEtiquetaDeLosArticulos(id);
        CmrPalabrasClavesVO cmrPalabrasClavesVO = new CmrPalabrasClavesVO();
        cmrPalabrasClavesVO.setIdEtiqueta(id);
        etiquetaFacadeLocal.delete(cmrPalabrasClavesVO);
        return true;

    }

    @Override
    public String getEtiqueta(Long id) {
        return etiquetaFacadeLocal.get(id).getEtiqueta();

    }

    @Override
    public void actualizarEtiqueta(CmrPalabrasClavesVO cmrPalabrasClavesVO) {
        etiquetaFacadeLocal.update(cmrPalabrasClavesVO);
    }

    @Override
    public void actualizarBanner(CmrBannerVO cmrBannerVO){
        bannerFacadeLocal.update(cmrBannerVO);
    }

    @Override
    public long guardarBanner(CmrBannerVO cmrBannerVO) throws SQLException {
        return bannerFacadeLocal.save(cmrBannerVO);
    }

    @Override
    public void borrarBanner(CmrBannerVO cmrBannerVO){
        bannerFacadeLocal.delete(cmrBannerVO);
    }

    @Override
    public CmrBannerVO buscarBanner(CmrBannerVO cmrBannerVO) throws SQLException {
        return bannerFacadeLocal.get(cmrBannerVO.getIdBanner());
    }

    @Override
    public void deleteBanner(Long id) {
        if(id!=null) {
            CmrBannerVO cmrBannerVO = new CmrBannerVO();
            cmrBannerVO.setIdBanner(id);
            bannerFacadeLocal.delete(cmrBannerVO);
        }
    }

    @Override
    public List<CmrBannerVO> getBanners() throws SQLException {

        return articulosDeSuInteressDAO.getBanners();
    }

    @Override
    public List<CmrBannerVO> getBannersInicio() throws SQLException {
        return articulosDeSuInteressDAO.getBannersInicio();
    }


}
