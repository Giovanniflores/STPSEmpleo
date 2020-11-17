/**
 * 
 */
package mx.gob.stps.portal.web.crm.delegate;

import mx.gob.stps.portal.core.crm.service.CrmAppServiceRemote;
import mx.gob.stps.portal.core.infra.exception.EncodingException;
import mx.gob.stps.portal.core.infra.utils.Password;
import mx.gob.stps.portal.persistencia.vo.*;
import mx.gob.stps.portal.web.crm.form.*;
import mx.gob.stps.portal.web.crm.pojo.EtiquetaVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;
import mx.gob.stps.portal.web.infra.utils.Constantes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Benjamin Vander Stichelen
 *
 **/
public final class CrmBusDelegateImpl implements CrmBusDelegate {

	private static CrmBusDelegate instance = new CrmBusDelegateImpl();

	private CrmBusDelegateImpl() {
	}

	public static CrmBusDelegate getInstance() {
		return instance;
	}


    @Override
    public void updateArticulo(CreateArticuloForm createArticuloForm) throws Exception {
        CmrArticulosQueTeIntVO cmrArticulosQueTeIntVO = mapear(createArticuloForm);
        cmrArticulosQueTeIntVO.setId(createArticuloForm.getId());
        getCrmAppService().updateArticulo(cmrArticulosQueTeIntVO);
    }

    @Override
    public void deleteEtiquetasArticulo(Long idArticulo) throws Exception {
        getCrmAppService().deleteEtiquetasArticulo(idArticulo);

    }

    @Override
    public CreateArticuloForm selectArticulo(Long idArticulo, CreateArticuloForm createArticuloForm) throws Exception {
        CmrArticulosQueTeIntVO cmrArticulosQueTeIntVO = getCrmAppService().selectArticulo(idArticulo);
        mapear(cmrArticulosQueTeIntVO, createArticuloForm);
        //createArticuloForm.setSelectedEtiquestas(getCrmAppService().getEtiquetasArticulosEnString(idArticulo));
        return createArticuloForm;
    }

    private void mapear(CmrArticulosQueTeIntVO cmrArticulosQueTeIntVO, CreateArticuloForm createArticuloForm) {
        createArticuloForm.setActivo(cmrArticulosQueTeIntVO.getActivo());
        createArticuloForm.setArticulo(cmrArticulosQueTeIntVO.getArticulo());
        createArticuloForm.setDescripcion(cmrArticulosQueTeIntVO.getDescripcion());
        String[] strEtiquetas = new String[cmrArticulosQueTeIntVO.getCmrPalabrasClavesArticulos().size()];
        for(int i = 0; i< cmrArticulosQueTeIntVO.getCmrPalabrasClavesArticulos().size();i++){
            strEtiquetas[i] = String.valueOf(cmrArticulosQueTeIntVO.getCmrPalabrasClavesArticulos().get(i).getIdEtiqueta());
        }

        createArticuloForm.setSelectedEtiquestas(strEtiquetas);
        for(CmrPalabrasClavesArticuloVO cmrPalabrasClavesArticuloVO: cmrArticulosQueTeIntVO.getCmrPalabrasClavesArticulos()){
            EtiquetaForm etiquetaForm = new EtiquetaForm();
            etiquetaForm.setEtiqueta(cmrPalabrasClavesArticuloVO.getEtiqueta());
            etiquetaForm.setId(cmrPalabrasClavesArticuloVO.getIdEtiqueta());
            createArticuloForm.getEtiquetaForms().add(etiquetaForm);
        }
        createArticuloForm.setFecha(cmrArticulosQueTeIntVO.getFecha());
        createArticuloForm.setFuente(cmrArticulosQueTeIntVO.getFuente());
        createArticuloForm.setId(cmrArticulosQueTeIntVO.getId());
        createArticuloForm.setIndexable(cmrArticulosQueTeIntVO.getIndexable());
        createArticuloForm.setFuente(cmrArticulosQueTeIntVO.getFuente());
        createArticuloForm.setMostrarEnHome(cmrArticulosQueTeIntVO.getMostrarEnHome());
        createArticuloForm.setTitulo(cmrArticulosQueTeIntVO.getTitulo());
    }

    @Override
    public String[] getEtiquetasArticulosEnString(Long idArticulo) throws Exception {
        return getCrmAppService().getEtiquetasArticulosEnString(idArticulo);
    }

    @Override
    public Long insertArticulo(CreateArticuloForm articuloForm) throws Exception {
        return getCrmAppService().insertArticulo(mapear(articuloForm));

    }

    private CmrArticulosQueTeIntVO mapear(CreateArticuloForm articuloForm) {
        CmrArticulosQueTeIntVO articulosQueTeIntVO = new CmrArticulosQueTeIntVO();
        articulosQueTeIntVO.setActivo(articuloForm.getActivo());
        articulosQueTeIntVO.setArticulo(articuloForm.getArticulo());
        articulosQueTeIntVO.setDescripcion(articuloForm.getDescripcion());
        articulosQueTeIntVO.setFecha(articuloForm.getFecha());
        articulosQueTeIntVO.setFuente(articuloForm.getFuente());
        articulosQueTeIntVO.setIndexable(articuloForm.getIndexable());
        articulosQueTeIntVO.setMostrarEnHome(articuloForm.getMostrarEnHome());
        articulosQueTeIntVO.setOcculto(articuloForm.getOcculto());
        articulosQueTeIntVO.setTitulo(articuloForm.getTitulo());
        articulosQueTeIntVO.setUrl("url");
        return articulosQueTeIntVO;
    }

    @Override
    public List<EtiquetaVO> getEtiquetasAlfabeticoVO() throws  Exception{
        return mapearEtiquetasToEtiquetaVO(getEtiquetasAlfabetico());
    }

    private List<EtiquetaVO> mapearEtiquetasToEtiquetaVO(List<EtiquetaForm> etiquetasAlfabetico) {
        List<EtiquetaVO> etiquetaVOs = new ArrayList<EtiquetaVO>();
        for(EtiquetaForm etiqueta : etiquetasAlfabetico){
            EtiquetaVO item = new EtiquetaVO();
            item.setEtiqueta(etiqueta.getEtiqueta());
            item.setIdEtiqueta(etiqueta.getId());
            etiquetaVOs.add(item);
        }
        return etiquetaVOs;
    }

    @Override
    public List<EtiquetaForm> getEtiquetasAlfabetico() throws Exception {
        List<CmrPalabrasClavesVO> cmrPalabrasClavesVOs = getCrmAppService().getEtiquetasAlfabetico();
        List<EtiquetaForm> etiquetaForms = new ArrayList<EtiquetaForm>();
        for(CmrPalabrasClavesVO cmrPalabrasClavesVO : cmrPalabrasClavesVOs){
            EtiquetaForm item = new EtiquetaForm();
            item.setEtiqueta(cmrPalabrasClavesVO.getEtiqueta());
            item.setId(cmrPalabrasClavesVO.getIdEtiqueta());
            etiquetaForms.add(item);
        }
        return etiquetaForms;
    }

    @Override
    public void createEtiqueta(String etiqueta) throws Exception {
        getCrmAppService().createEtiqueta(etiqueta);
    }

    @Override
    public Long createEtiquetaArticulo(Long idArticulo, Long idEtiqueta) throws Exception {
        return getCrmAppService().createEtiquetaArticulo(idArticulo,idEtiqueta);
    }

    @Override
    public ArticulosForm getTodoArticulos() throws SQLException, ServiceLocatorException, IOException {
        List<CmrArticulosQueTeIntVO> cmrArticulosQueTeIntVOs = getCrmAppService().getTodoArticulos();
        ArticulosForm articulosForm = new ArticulosForm();
        List<CreateArticuloForm> createArticuloForms = new ArrayList<CreateArticuloForm>() ;

        for(CmrArticulosQueTeIntVO cmrArticulosQueTeIntVO : cmrArticulosQueTeIntVOs){
            createArticuloForms.add(mapearACreateForm(cmrArticulosQueTeIntVO));
        }
        articulosForm.setArticulosFormList(createArticuloForms);
        return articulosForm;
    }

    @Override
    public void deleteArticulo(long id) throws ServiceLocatorException, SQLException {
        getCrmAppService().deleteArticulo(id);
    }

    @Override
    public boolean existUsuario(LoginForm loginForm) throws ServiceLocatorException, SQLException, EncodingException {
        CmrUsuarioVO cmrUsuarioVO = new CmrUsuarioVO();
        cmrUsuarioVO.setClave(Password.codificaPassword(loginForm.getClave()));
        cmrUsuarioVO.setUsuario(loginForm.getUsuario());

        return getCrmAppService().existeUsuario(cmrUsuarioVO);
    }

    @Override
    public List<CreateArticuloForm> getEtiquetasArticulosDesdeEtiqueta(long idEtiqueta) throws ServiceLocatorException, IOException, SQLException {
        List<CmrArticulosQueTeIntVO> cmrArticulosQueTeIntVOs = getCrmAppService().getEtiquetasArticulosDesdeEtiqueta(idEtiqueta);
        List<CreateArticuloForm> createArticuloForms = new ArrayList<CreateArticuloForm>();
        for(CmrArticulosQueTeIntVO cmrArticulosQueTeIntVO : cmrArticulosQueTeIntVOs){
            CreateArticuloForm createArticuloForm = mapearACreateForm(cmrArticulosQueTeIntVO);
            createArticuloForms.add(createArticuloForm);
        }
        return createArticuloForms;
    }

    @Override
    public List<CreateArticuloForm> selectArticulos(List<Integer> idArticulos, Object o, Object o1, Object o2) {
        return null;
    }

    @Override
    public void createEtiquetaForm(EtiquetaForm etiquetaForm) throws Exception {
        this.createEtiqueta(etiquetaForm.getEtiqueta());
    }

    @Override
    public int countArticulos() {
        return 0;
    }

    @Override
    public List<CreateArticuloForm> getRandomArticulos(int homelimit) {
        return null;
    }

    @Override
    public void updateUsuario(UserForm userForm) throws EncodingException, ServiceLocatorException {
        CmrUsuarioVO usuario = mapearUsuario(userForm);
        getCrmAppService().updateUsuario(usuario);
    }

    private CmrUsuarioVO mapearUsuario(UserForm userForm) throws EncodingException {
        CmrUsuarioVO cmrUsuarioVO = new CmrUsuarioVO();
        if(userForm.getId() != null){
            cmrUsuarioVO.setIdCmtUsuario(userForm.getId());
        }
        cmrUsuarioVO.setClave(Password.codificaPassword(userForm.getClave()));
        cmrUsuarioVO.setCorreo(userForm.getCorreo());
        cmrUsuarioVO.setUsuario(userForm.getUsuario());
        return cmrUsuarioVO;
    }

    @Override
    public void selectUsuario(Long id, UserForm userForm) throws ServiceLocatorException {
        CmrUsuarioVO vo = getCrmAppService().getUsusario(id);
        setUsuario(userForm, vo);

    }

    private void setUsuario(UserForm userForm, CmrUsuarioVO vo) {
        userForm.setId(vo.getIdCmtUsuario());
        userForm.setUsuario(vo.getUsuario());
        userForm.setClave(vo.getClave());
        userForm.setCorreo(vo.getCorreo());
    }

    @Override
    public Long insertUsuario(UserForm userForm) throws EncodingException, ServiceLocatorException {
        CmrUsuarioVO cmrUsuarioVO = mapearUsuario(userForm);

        Long id = getCrmAppService().insertUsusario(cmrUsuarioVO);
        userForm.setId(id);
        return id;
    }

    @Override
    public List<CreateArticuloForm> getArticulosHome() throws ServiceLocatorException, IOException, SQLException {
        List<CreateArticuloForm> createArticuloForms = new ArrayList<CreateArticuloForm>();
        for(CmrArticulosQueTeIntVO articuloVo :getCrmAppService().getArticulosHome()){
            CreateArticuloForm articuloForm = mapearACreateForm(articuloVo);
            createArticuloForms.add(articuloForm);
        }
        return createArticuloForms;
    }

    @Override
    public Long getIdEtiqueta(String temasEmpleo) throws ServiceLocatorException, SQLException {
        return getCrmAppService().getIdEtiqueta(temasEmpleo);
    }

    @Override
    public Boolean deleteEtiqueta(Long id) throws ServiceLocatorException, SQLException {

        return getCrmAppService().deleteEtiqueta(id);
    }

    @Override
    public void actualizarEtiqueta(EtiquetaForm etiquetaForm) throws ServiceLocatorException {

        getCrmAppService().actualizarEtiqueta(mapearEtiquetaFormToVo(etiquetaForm));
    }



    private CmrPalabrasClavesVO mapearEtiquetaFormToVo(EtiquetaForm etiquetaForm) {
        CmrPalabrasClavesVO cmrPalabrasClavesVO = new CmrPalabrasClavesVO();
        cmrPalabrasClavesVO.setEtiqueta(etiquetaForm.getEtiqueta());
        cmrPalabrasClavesVO.setIdEtiqueta(etiquetaForm.getId());
        return cmrPalabrasClavesVO;
    }


    private CreateArticuloForm mapearACreateForm(CmrArticulosQueTeIntVO cmrArticulosQueTeIntVO) {
        CreateArticuloForm createArticuloForm = new CreateArticuloForm();
        createArticuloForm.setActivo(cmrArticulosQueTeIntVO.getActivo());
        createArticuloForm.setArticulo(cmrArticulosQueTeIntVO.getArticulo());
        createArticuloForm.setDescripcion(cmrArticulosQueTeIntVO.getDescripcion());
        createArticuloForm.setEtiquetas(mapearEtiquetas(cmrArticulosQueTeIntVO.getCmrPalabrasClavesArticulos()));
        createArticuloForm.setFecha(cmrArticulosQueTeIntVO.getFecha());
        createArticuloForm.setFuente(cmrArticulosQueTeIntVO.getFuente());
        createArticuloForm.setId(cmrArticulosQueTeIntVO.getId());
        createArticuloForm.setIndexable(cmrArticulosQueTeIntVO.getIndexable());
        createArticuloForm.setMostrarEnHome(cmrArticulosQueTeIntVO.getMostrarEnHome());
        createArticuloForm.setTitulo(cmrArticulosQueTeIntVO.getTitulo());


        return createArticuloForm;
    }

    private List<EtiquetaSelectedForm> mapearEtiquetas(List<CmrPalabrasClavesArticuloVO> cmrPalabrasClavesArticulos) {
        List<EtiquetaSelectedForm> etiquetaSelectedForms = new ArrayList<EtiquetaSelectedForm>();
        for(CmrPalabrasClavesArticuloVO cmrPalabrasClavesArticuloVO:cmrPalabrasClavesArticulos){
            EtiquetaSelectedForm etiquetaSelectedForm = new EtiquetaSelectedForm();
            etiquetaSelectedForm.setEtiquetaSelected(cmrPalabrasClavesArticuloVO.getEtiqueta());
            etiquetaSelectedForm.setId(cmrPalabrasClavesArticuloVO.getIdEtiqueta());
        }
        return etiquetaSelectedForms;
    }

    @Override
    public void getEtiqueta(EtiquetaForm etiquetaForm) throws ServiceLocatorException {
        etiquetaForm.setEtiqueta(getCrmAppService().getEtiqueta(etiquetaForm.getId()));
    }

    @Override
    public void crearBanner(BannerForm bannerForm) throws IOException, ServiceLocatorException, SQLException {
        CmrBannerVO vo = mapearVoBannerForm(bannerForm);

        getCrmAppService().guardarBanner(vo);
    }

    private CmrBannerVO mapearVoBannerForm(BannerForm bannerForm) throws IOException {
        CmrBannerVO cmrBannerVO = new CmrBannerVO();
        cmrBannerVO.setActiva(bannerForm.getActivo());
        cmrBannerVO.setCaption(bannerForm.getTitulo());
        cmrBannerVO.setFechaFin(bannerForm.getFechaFin());
        cmrBannerVO.setFechaInicio(bannerForm.getFechaInicio());
        if(bannerForm.getFile() != null && bannerForm.getFile().getFileSize() > 0) {
            cmrBannerVO.setImagen(bannerForm.getFile().getFileData());
            cmrBannerVO.setTipoImagen(bannerForm.getFile().getContentType());
        }
        cmrBannerVO.setAltura(bannerForm.getAltura());
        cmrBannerVO.setAncho(bannerForm.getAncho());
        cmrBannerVO.setDescripcion(bannerForm.getDescripcion());
        cmrBannerVO.setDescripcionLarga(bannerForm.getDescripcionLarga());
        cmrBannerVO.setIndice(bannerForm.getIndice());
        cmrBannerVO.setLink(bannerForm.getLink());
        cmrBannerVO.setNuevaVentana(bannerForm.getNuevaVentana());


        return cmrBannerVO;
    }

    @Override
    public void actualizarBanner(BannerForm bannerForm) throws IOException, ServiceLocatorException, SQLException {
        CmrBannerVO vo = mapearVoBannerForm(bannerForm);
        vo.setIdBanner(bannerForm.getId());

        getCrmAppService().actualizarBanner(vo);
    }

    @Override
    public void getBanner(BannerForm bannerForm) throws ServiceLocatorException, SQLException {
        CmrBannerVO vo = new CmrBannerVO();
        vo.setIdBanner(bannerForm.getId());
        vo = getCrmAppService().buscarBanner(vo);
        mapearBannerFormVo(bannerForm,vo);
    }

    @Override
    public CmrBannerVO getBannerImage(Long idBanner) throws ServiceLocatorException, SQLException {
        CmrBannerVO vo = new CmrBannerVO();
        vo.setIdBanner(idBanner);
        return getCrmAppService().buscarBanner(vo);
    }

    @Override
    public List<BannerForm> getBanners() throws ServiceLocatorException, SQLException {
        return mapearBanners(getCrmAppService().getBanners());
    }

    public List<BannerForm> mapearBanners(List<CmrBannerVO> vos){
        List<BannerForm> bannerForms = new ArrayList<BannerForm>();
        for(CmrBannerVO vo : vos){
            BannerForm banner = new BannerForm();
            mapearBannerFormVo(banner,vo);
            bannerForms.add(banner);
        }
        return bannerForms;
    }

    @Override
    public void deleteBanner(Long id) throws ServiceLocatorException {
        getCrmAppService().deleteBanner(id);
    }

    @Override
    public List<BannerForm> getBannersHome() throws ServiceLocatorException, SQLException {
        return mapearBanners(getCrmAppService().getBannersInicio());
    }

    private void mapearBannerFormVo(BannerForm bannerForm, CmrBannerVO vo) {
        bannerForm.setActivo(vo.isActiva());
        bannerForm.setTitulo(vo.getCaption());
        bannerForm.setFechaFin(vo.getFechaFin());
        bannerForm.setFechaInicio(vo.getFechaInicio());
        bannerForm.setId(vo.getIdBanner());
        bannerForm.setAltura(vo.getAltura());
        bannerForm.setAncho(vo.getAncho());
        bannerForm.setDescripcion(vo.getDescripcion());
        bannerForm.setDescripcionLarga(vo.getDescripcionLarga());
        bannerForm.setIndice(vo.getIndice());
        bannerForm.setLink(vo.getLink());
        bannerForm.setNuevaVentana(vo.getNuevaVentana());
        bannerForm.setTipoImagen(vo.getTipoImagen());



        bannerForm.setFile(null);


    }


    public CrmAppServiceRemote getCrmAppService()throws ServiceLocatorException {
        CrmAppServiceRemote ejb = (CrmAppServiceRemote) ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_CRM);
		return ejb;
	}
}