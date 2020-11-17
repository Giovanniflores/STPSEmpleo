package mx.gob.stps.portal.web.crm.delegate;

import mx.gob.stps.portal.core.infra.exception.EncodingException;
import mx.gob.stps.portal.persistencia.vo.CmrBannerVO;
import mx.gob.stps.portal.web.crm.form.*;
import mx.gob.stps.portal.web.crm.pojo.EtiquetaVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Benjamin Vander Stichelen
 *
 **/
public interface CrmBusDelegate {

	public void updateArticulo(CreateArticuloForm createArticuloForm) throws Exception;

	public void deleteEtiquetasArticulo(Long idArticulo) throws Exception;

	public CreateArticuloForm selectArticulo(Long idArticulo,CreateArticuloForm createArticuloForm ) throws Exception;

    public String[] getEtiquetasArticulosEnString(Long idArticulo) throws Exception;

    public Long insertArticulo(CreateArticuloForm articuloForm) throws Exception;

    List<EtiquetaVO> getEtiquetasAlfabeticoVO() throws  Exception;

    public List<EtiquetaForm> getEtiquetasAlfabetico() throws Exception ;

    public void createEtiqueta(String etiqueta) throws Exception;

    Long createEtiquetaArticulo(Long idArticulo, Long idEtiqueta) throws Exception;

    public ArticulosForm getTodoArticulos() throws SQLException, ServiceLocatorException, IOException;

    public void deleteArticulo(long id) throws ServiceLocatorException, SQLException;

    public boolean existUsuario(LoginForm loginForm) throws ServiceLocatorException, SQLException, EncodingException;

    public List<CreateArticuloForm> getEtiquetasArticulosDesdeEtiqueta(long i) throws ServiceLocatorException, IOException, SQLException;

    public List<CreateArticuloForm> selectArticulos(List<Integer> idArticulos, Object o, Object o1, Object o2);

    public void createEtiquetaForm(EtiquetaForm etiquetaForm) throws Exception;

    public int countArticulos();

    public List<CreateArticuloForm> getRandomArticulos(int homelimit);


    public void updateUsuario(UserForm userForm) throws EncodingException, ServiceLocatorException;

    public void selectUsuario(Long id, UserForm userForm) throws ServiceLocatorException;

    public Long insertUsuario(UserForm userForm) throws EncodingException, ServiceLocatorException;

    public List<CreateArticuloForm> getArticulosHome() throws ServiceLocatorException, IOException, SQLException;

    public Long getIdEtiqueta(String temasEmpleo) throws ServiceLocatorException, SQLException;

    public Boolean deleteEtiqueta(Long id) throws ServiceLocatorException, SQLException;

    public void actualizarEtiqueta(EtiquetaForm etiquetaForm) throws ServiceLocatorException;

    public void getEtiqueta(EtiquetaForm etiquetaForm) throws ServiceLocatorException;

    public void crearBanner(BannerForm bannerForm) throws IOException, ServiceLocatorException, SQLException;

    public void actualizarBanner(BannerForm bannerForm) throws IOException, ServiceLocatorException, SQLException;

    public void getBanner(BannerForm bannerForm) throws ServiceLocatorException, SQLException;

    public CmrBannerVO getBannerImage(Long idBanner) throws ServiceLocatorException, SQLException;

    List<BannerForm> getBanners() throws ServiceLocatorException, SQLException;

    public void deleteBanner(Long id) throws ServiceLocatorException;

    public List<BannerForm> getBannersHome() throws ServiceLocatorException, SQLException;

}