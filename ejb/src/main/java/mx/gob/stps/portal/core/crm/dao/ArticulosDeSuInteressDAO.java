package mx.gob.stps.portal.core.crm.dao;

import mx.gob.stps.portal.core.crm.constantes.Queries;
import mx.gob.stps.portal.core.crm.persistencia.facade.EtiquetaFacade;
import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.persistencia.vo.CmrArticulosQueTeIntVO;
import mx.gob.stps.portal.persistencia.vo.CmrBannerVO;
import mx.gob.stps.portal.persistencia.vo.CmrPalabrasClavesArticuloVO;
import mx.gob.stps.portal.persistencia.vo.CmrPalabrasClavesVO;
import mx.gob.stps.portal.utils.Utils;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.rowset.CachedRowSet;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjamin.vander on 02/12/2015.
 */
public class ArticulosDeSuInteressDAO extends TemplateDAO {


    private static Logger logger = Logger.getLogger(ArticulosDeSuInteressDAO.class);

    private String CONSULTA_ARTICULOS_DE_SU_INTERESS;

    private String TABLEARTICULOS = "CMR_ARTICULOS_QUE_TE_INT";

    private String ID_ARTICULOS_INTERESS = "ID_ARTICULOS_INTERESS";
    private String TITULO = "TITULO";
    private String DESCRIPCION = "DESCRIPCION";
    private String FUENTE = "FUENTE";
    private String FECHA = "FECHA";
    private String MOSTRARENHOME = "MOSTRARENHOME";
    private String ESTATUS = "ESTATUS";
    private String ARTICULO = "ARTICULO";
    private String ACTIVO = "ACTIVO";
    private String INDEXABLE = "INDEXABLE";
    private String READABLE_URL = "READABLE_URL";

    private String TABLEETIQUETAARTICULOS = "CMR_PALABRASCLAVEARTICULO";
    private String ID_ARTICULO_ETIQUETA = "ID_ARTICULO_ETIQUETA";
    private String ID_ETIQUETA = "ID_ETIQUETA";

    private String TABLEETIQUETA = "CMR_PALABRASCLAVE";
    private String ETIQUETA = "ETIQUETA";

    private String TABLEUSUARIO ="CMR_USUARIO";
    private String USUARIO ="USUARIO";
    private String ID_USARIO ="ID_CMT_USUARIO";
    private String CLAVE ="CLAVE";
    private String CORREO ="CORREO";

    private String TABLEBANNER = "CMR_BANNER";
    private String ID_BANNER = "ID_BANNER";
    private String IMAGEN = "IMAGEN";
    private String CAPTION = "CAPTION";
    private String FECHAINICIO = "FECHAINICIO";
    private String FECHAFIN = "FECHAFIN";
    private String ACTIVA = "ACTIVA";
    private String ANCHO = "ANCHO";
    private String ALTURA = "ALTURA";
    private String DESCRIPCIONLARGA = "DESCRIPICONLARGA";
    private String NUEVAVENTANA = "NUEVAVENTANA";
    private String LINK = "LINK";
    private String TIPOIMAGEN = "TIPOIMAGEN";
    private String INDICE = "INDICE";


    public ArticulosDeSuInteressDAO(){
    }

    public ArticulosDeSuInteressDAO(Connection globalConnection){
        super(globalConnection);
    }


    @PersistenceContext
    private EntityManager entityManager;


    public List<CmrArticulosQueTeIntVO> getArticulosCompleto() throws SQLException, IOException {
        List<CmrArticulosQueTeIntVO> cmrArticulosQueTeIntVOs = null;

        cmrArticulosQueTeIntVOs = getArticulosDeSuInteress();


        for(CmrArticulosQueTeIntVO cmrArticulosQueTeIntVO : cmrArticulosQueTeIntVOs){
            List<CmrPalabrasClavesArticuloVO> cmrPalabrasClavesArticuloVOs = new ArrayList<CmrPalabrasClavesArticuloVO>();
            cmrPalabrasClavesArticuloVOs = getEtiquetasArticulo(cmrArticulosQueTeIntVO.getId());
            cmrArticulosQueTeIntVO.setCmrPalabrasClavesArticulos(cmrPalabrasClavesArticuloVOs);
        }

        return cmrArticulosQueTeIntVOs;
    }

    public List<CmrPalabrasClavesArticuloVO> getEtiquetasArticulo(Long id) throws SQLException {
        List<CmrPalabrasClavesArticuloVO> cmrPalabrasClavesArticuloVOs = new ArrayList<CmrPalabrasClavesArticuloVO>();
        CONSULTA_ARTICULOS_DE_SU_INTERESS = Queries.BUSCARETIQUETAARTICULOS;
        EtiquetaFacade facade = new EtiquetaFacade();
        CachedRowSet cachedRowSet = executeQuery(new Object[]{id});
        while(cachedRowSet.next()){
            CmrPalabrasClavesArticuloVO item = new CmrPalabrasClavesArticuloVO();
            item.setIdArticulo(cachedRowSet.getLong(ID_ARTICULOS_INTERESS));
            item.setIdEtiqueta(cachedRowSet.getLong(ID_ETIQUETA));
            item.setIdArticuloEtiqueta(cachedRowSet.getLong(ID_ARTICULO_ETIQUETA));
            item.setEtiqueta(cachedRowSet.getString(ETIQUETA));
            cmrPalabrasClavesArticuloVOs.add(item);
        }




        return cmrPalabrasClavesArticuloVOs;
    }

    public String[] getEtiquetasArticuloString(long idArticulo) throws SQLException {
        List<CmrPalabrasClavesArticuloVO> cmrPalabrasClavesArticuloVOs = this.getEtiquetasArticulo(idArticulo);
        String[] listaEtiquetas = new String[cmrPalabrasClavesArticuloVOs.size()];
        for(int i=0;i<cmrPalabrasClavesArticuloVOs.size();i++){
            listaEtiquetas[i] = cmrPalabrasClavesArticuloVOs.get(i).getEtiqueta();
        }
        return listaEtiquetas;
    }


    public List<CmrArticulosQueTeIntVO> getArticulosDesdeEtiqueta(long idEtiqueta) throws SQLException, IOException {
        List<CmrArticulosQueTeIntVO> cmrArticulosQueTeIntVOs = new ArrayList<CmrArticulosQueTeIntVO>();
        CONSULTA_ARTICULOS_DE_SU_INTERESS = Queries.BUSCAR_ARTICULOS_POR_ETIQUETA;
        CachedRowSet cachedRowSet = executeQuery(new Object[]{idEtiqueta});
        while (cachedRowSet.next()) {
            CmrArticulosQueTeIntVO item = new CmrArticulosQueTeIntVO();
            mapearArticulosDeSuInteress(cachedRowSet, item);
            cmrArticulosQueTeIntVOs.add(item);
        }

        return cmrArticulosQueTeIntVOs;
    }
    public List<CmrArticulosQueTeIntVO> getArticulosDeSuInteress() throws SQLException, IOException {
        CONSULTA_ARTICULOS_DE_SU_INTERESS = Queries.BUSCAR_ARTICULOS;
        List<CmrArticulosQueTeIntVO> cmrArticulosQueTeIntVOs = new ArrayList<CmrArticulosQueTeIntVO>();

        CachedRowSet cachedRowSet = executeQuery();
        while (cachedRowSet.next()) {
            CmrArticulosQueTeIntVO item = new CmrArticulosQueTeIntVO();
            mapearArticulosDeSuInteress(cachedRowSet, item);

            cmrArticulosQueTeIntVOs.add(item);
        }


        return cmrArticulosQueTeIntVOs;
    }

    private void mapearArticulosDeSuInteress(CachedRowSet cachedRowSet, CmrArticulosQueTeIntVO item) throws SQLException, IOException {
        item.setActivo(Utils.getBooleanConfidencialidad(cachedRowSet.getInt(ACTIVO)));

        item.setArticulo(getBlobString(ARTICULO,cachedRowSet));
        item.setDescripcion(cachedRowSet.getString(DESCRIPCION));
        item.setFecha(Utils.getFechaFormato(cachedRowSet.getDate(FECHA)));
        item.setFuente(cachedRowSet.getString(FUENTE));
        item.setId(cachedRowSet.getLong(ID_ARTICULOS_INTERESS));
        item.setIndexable(Utils.getBooleanConfidencialidad(cachedRowSet.getInt(INDEXABLE)));
        item.setMostrarEnHome(Utils.getBooleanConfidencialidad(cachedRowSet.getInt(MOSTRARENHOME)));

        item.setTitulo(cachedRowSet.getString(TITULO));
        item.setUrl(cachedRowSet.getString(READABLE_URL));
    }


    public boolean existeUsuario(String usuario, String clave) throws SQLException {
        CONSULTA_ARTICULOS_DE_SU_INTERESS = Queries.EXISTE_USUARIO;
        CachedRowSet cachedRowset = executeQuery(new Object[]{usuario, clave});
        while (cachedRowset.next()){
            return true;
        }
        return false;

    }
    public void deleteArticulo(long idArticulo) throws SQLException {
        this.deleteEtiquetasArticulo(idArticulo);
        CONSULTA_ARTICULOS_DE_SU_INTERESS = Queries.DELETE_ARTICULO;
        executeUpdate(new Object[]{idArticulo});
    }

    public List<CmrArticulosQueTeIntVO> getArticulosHome() throws SQLException, IOException {
        List<CmrArticulosQueTeIntVO> cmrArticulosQueTeIntVOs = new ArrayList<CmrArticulosQueTeIntVO>();
        CONSULTA_ARTICULOS_DE_SU_INTERESS = Queries.GET_ARTIUCLOS_HOME;
        CachedRowSet cachedRowSet = executeQuery();
        while(cachedRowSet.next()){
            CmrArticulosQueTeIntVO cmrArticulosQueTeIntVo = new CmrArticulosQueTeIntVO();
            mapearArticulosDeSuInteress(cachedRowSet, cmrArticulosQueTeIntVo);
            cmrArticulosQueTeIntVOs.add(cmrArticulosQueTeIntVo);
        }
        return cmrArticulosQueTeIntVOs;

    }
    public void deleteEtiquetasArticulo(Long idArticulo) throws SQLException {
        CONSULTA_ARTICULOS_DE_SU_INTERESS = Queries.DELETE_ETIQUETA_ARTICULOS;
        executeUpdate(new Object[]{idArticulo});

    }



    public void deleteEtiquetaDeLosArticulos(Long id) throws SQLException {
        CONSULTA_ARTICULOS_DE_SU_INTERESS = Queries.DELETE_ETIQUETA_DE_ARTICULO_POR_ETIQUETA;
        executeUpdate(new Object[]{id});

    }

    public Long getIdEtiqueta(String temasEmpleo) throws SQLException {
        CONSULTA_ARTICULOS_DE_SU_INTERESS = Queries.BUSCAR_IDETIQUETA_DESDE_ETIQUETA;
        CachedRowSet cachedRowSet = executeQuery(new Object[]{temasEmpleo});
        Long idEtiqueta = null;
        while (cachedRowSet.next()) {

            idEtiqueta = cachedRowSet.getLong(ID_ETIQUETA);

        }
        return idEtiqueta;

    }

    public List<CmrPalabrasClavesVO> getEtiquetasAlfabetico() throws SQLException {
        List<CmrPalabrasClavesVO> cmrPalabrasClaveVOs = new ArrayList<CmrPalabrasClavesVO>();
        CONSULTA_ARTICULOS_DE_SU_INTERESS = Queries.BUSCAR_ETIQUETAS_ALFABETICO;
        CachedRowSet cachedRowSet = executeQuery();
        while (cachedRowSet.next()) {
            CmrPalabrasClavesVO item = new CmrPalabrasClavesVO();
            item.setEtiqueta(cachedRowSet.getString(ETIQUETA));
            item.setIdEtiqueta(cachedRowSet.getLong(ID_ETIQUETA));
            cmrPalabrasClaveVOs.add(item);

        }
        return cmrPalabrasClaveVOs;
    }

    public List<CmrBannerVO> getBanners() throws SQLException {
        List<CmrBannerVO> items = new ArrayList<CmrBannerVO>();
        CONSULTA_ARTICULOS_DE_SU_INTERESS = Queries.BUSCAR_BANNERS;
        CachedRowSet cachedRowSet = executeQuery();
        while (cachedRowSet.next()) {
            CmrBannerVO item = mapearItemToCmrBannerVO(cachedRowSet);
            items.add(item);
        }
        return items;
    }


    public List<CmrBannerVO> getBannersInicio() throws SQLException {
        List<CmrBannerVO> items = new ArrayList<CmrBannerVO>();
        CONSULTA_ARTICULOS_DE_SU_INTERESS = Queries.BUSCAR_BANNERS_INICIO;
        CachedRowSet cachedRowSet = executeQuery();
        while (cachedRowSet.next()) {
            CmrBannerVO item = mapearItemToCmrBannerVO(cachedRowSet);
            items.add(item);
        }
        return items;
    }


    private CmrBannerVO mapearItemToCmrBannerVO(CachedRowSet cr) throws SQLException {
        CmrBannerVO vo = new CmrBannerVO();
        vo.setActiva(Utils.getBooleanConfidencialidad(cr.getInt(ACTIVA)));
        vo.setAltura(cr.getLong(ALTURA));
        vo.setAncho(cr.getLong(ANCHO));
        vo.setCaption(cr.getString(CAPTION));
        vo.setDescripcion(cr.getString(DESCRIPCION));
        vo.setDescripcionLarga(cr.getString(DESCRIPCIONLARGA));
        vo.setFechaFin(Utils.getFechaFormato(cr.getDate(FECHAFIN)));
        vo.setFechaInicio(Utils.getFechaFormato(cr.getDate(FECHAINICIO)));
        vo.setIdBanner(cr.getLong(ID_BANNER));
        //No se lee el imagen porque es pesado y se occupa otra methodo para mostrarlo
        //vo.setImagen(getBlobByteArray(IMAGEN,cr));
        vo.setIndice(cr.getLong(INDICE));
        vo.setLink(cr.getString(LINK));
        vo.setNuevaVentana(Utils.getBooleanConfidencialidad(cr.getInt(NUEVAVENTANA)));
        vo.setTipoImagen(cr.getString(TIPOIMAGEN));

        return vo;
    }

    private String getBlobString(String campo,CachedRowSet cachedRowSet) throws SQLException, IOException {

        Blob myBlob = cachedRowSet.getBlob(campo);
        java.io.InputStream myInputStream = myBlob.getBinaryStream();

        BufferedReader in = new BufferedReader(new java.io.InputStreamReader(myInputStream));
        String total="";
        String str;
        while ((str = in.readLine()) != null) {
            total += str;
        }
        myInputStream.close();
        return total;
    }

    private byte[] getBlobByteArray(String campo, CachedRowSet cachedRowSet) throws SQLException {
        Blob myBlob = cachedRowSet.getBlob(campo);
        return myBlob.getBytes(0,(int)myBlob.length());
    }


    private String getQueryBuscarArticulos() {

        StringBuffer sqlString = new StringBuffer();

        sqlString.append("SELECT " + ID_ARTICULOS_INTERESS +", " + TITULO +", " + DESCRIPCION +", " + FUENTE +", " + FECHA );
        sqlString.append(", " + MOSTRARENHOME +", " +ESTATUS + ", " + ARTICULO + ", " + ACTIVO + ", " + INDEXABLE);
        sqlString.append(", " + READABLE_URL );
        sqlString.append("  FROM " + TABLEARTICULOS);

        sqlString.append(" ORDER BY "+FECHA+" DESC ");
        return sqlString.toString();

    }


    private String getQueryArticulosHome() {
        StringBuffer sqlString = new StringBuffer();

/*
        sqlString.append("SELECT " + ID_ARTICULOS_INTERESS +", " + TITULO +", " + DESCRIPCION +", " + FUENTE +", " + FECHA );
        sqlString.append(", " + MOSTRARENHOME +", " +ESTATUS + ", " + ARTICULO + ", " + ACTIVO + ", " + INDEXABLE);
        sqlString.append(", " + READABLE_URL );
        sqlString.append("  FROM " + TABLEARTICULOS);
        sqlString.append(" WHERE "+ MOSTRARENHOME + " = " + Utils.getNumeroConfidencialidad(true));
        sqlString.append(" ORDER BY "+FECHA+" DESC ");*/
//SOLO los ultimos 3 publicados

        sqlString.append("SELECT " + ID_ARTICULOS_INTERESS +", " + TITULO +", " + DESCRIPCION +", " + FUENTE +", " + FECHA );
        sqlString.append(", " + MOSTRARENHOME +", " +ESTATUS + ", " + ARTICULO + ", " + ACTIVO + ", " + INDEXABLE);
        sqlString.append(", " + READABLE_URL );
        sqlString.append(" FROM (SELECT " + ID_ARTICULOS_INTERESS +", " + TITULO +", " + DESCRIPCION +", " + FUENTE +", " + FECHA );
        sqlString.append(", " + MOSTRARENHOME +", " +ESTATUS + ", " + ARTICULO + ", " + ACTIVO + ", " + INDEXABLE);
        sqlString.append(", " + READABLE_URL );
        sqlString.append("  FROM " + TABLEARTICULOS);
        sqlString.append(" ORDER BY "+FECHA+" DESC ");
        sqlString.append(") where rownum <= 3 ");
        return sqlString.toString();

    }


    private String getQueryArticulosPorEtiqueta() {
        StringBuffer sqlString = new StringBuffer();

        sqlString.append("SELECT a." + ID_ARTICULOS_INTERESS +" as " + ID_ARTICULOS_INTERESS + ", " + TITULO +", " + DESCRIPCION +", " + FUENTE +", " + FECHA );
        sqlString.append(", " + MOSTRARENHOME +", " +ESTATUS + ", " + ARTICULO + ", " + ACTIVO + ", " + INDEXABLE);
        sqlString.append(", " + READABLE_URL );
        sqlString.append("  FROM " + TABLEARTICULOS + " a ");
        sqlString.append(" INNER JOIN " + TABLEETIQUETAARTICULOS + " b ");
        sqlString.append(" ON a."+ID_ARTICULOS_INTERESS + " = b."+ID_ARTICULOS_INTERESS);
        sqlString.append(" WHERE b."+ ID_ETIQUETA + " = ? ");
        sqlString.append(" ORDER BY "+FECHA+" DESC ");
        return sqlString.toString();

    }



    private String deleteQueryArticulo() {
        StringBuffer sqlString = new StringBuffer();
        sqlString.append("DELETE FROM " + TABLEARTICULOS +" WHERE " + ID_ARTICULOS_INTERESS +"= ?");
        return sqlString.toString();
    }
    private String getQueryBuscarEtiquetasArticulo(){
        StringBuffer sqlString = new StringBuffer();
        sqlString.append("SELECT a." + ID_ARTICULO_ETIQUETA + " as "+ID_ARTICULO_ETIQUETA);
        sqlString.append(" , a." + ID_ETIQUETA + " as " + ID_ETIQUETA +", " + ID_ARTICULOS_INTERESS);
        sqlString.append(" , " + ETIQUETA);
        sqlString.append(" FROM " + TABLEETIQUETAARTICULOS + " a ");
        sqlString.append(" INNER JOIN " + TABLEETIQUETA +" b ");
        sqlString.append(" on a."+ ID_ETIQUETA + " = b." + ID_ETIQUETA);
        sqlString.append(" WHERE " + ID_ARTICULOS_INTERESS + " = ? ");
        return sqlString.toString();
    }


    private String deleteQueryEtiquetasArticulo(){
        StringBuffer sqlString = new StringBuffer();
        sqlString.append("DELETE ");
        sqlString.append(" FROM " + TABLEETIQUETAARTICULOS );
        sqlString.append(" WHERE " + ID_ARTICULOS_INTERESS + " = ? ");
        return sqlString.toString();
    }
    private String getQueryDeleteEtiquetaArticuloPorEtiqueta() {
        StringBuffer sqlString = new StringBuffer();
        sqlString.append("DELETE ");
        sqlString.append(" FROM " + TABLEETIQUETAARTICULOS );
        sqlString.append(" WHERE " + ID_ETIQUETA + " = ? ");
        return sqlString.toString();
    }


    private String getQueryEtiquetasAlfabetico() {
        StringBuffer sqlString = new StringBuffer();
        sqlString.append("SELECT " + ID_ETIQUETA + ", " + ETIQUETA);
        sqlString.append(" FROM " + TABLEETIQUETA );
        sqlString.append(" ORDER BY " + ETIQUETA );
        return sqlString.toString();
    }


    private String getQueryIdEtiquetaFromEtiqueta() {
        StringBuffer sqlString = new StringBuffer();
        sqlString.append("SELECT " + ID_ETIQUETA );
        sqlString.append(" FROM " + TABLEETIQUETA );
        sqlString.append(" WHERE " + ETIQUETA + "=?");
        return sqlString.toString();

    }




    private String getUsuarioClave() {
        StringBuffer sqlString = new StringBuffer();
        sqlString.append("SELECT " + ID_USARIO + ", " + USUARIO +", " + CLAVE);
        sqlString.append(", " + CORREO + " FROM " + TABLEUSUARIO);
        sqlString.append(" WHERE " + USUARIO + "=? and " + CLAVE + "=?");
        return sqlString.toString();
    }

    private String getQueryBaseBanners(){
        StringBuffer sqlString = new StringBuffer();
        sqlString.append("SELECT " + ID_BANNER + ", " + IMAGEN + ", " + CAPTION  );
        sqlString.append( ", " + FECHAINICIO  + ", " + FECHAFIN + ", " + ACTIVA);
        sqlString.append( ", " + ACTIVA + ", " + ANCHO + ", " + ALTURA);
        sqlString.append( ", " + DESCRIPCIONLARGA + ", " + NUEVAVENTANA);
        sqlString.append( ", " + LINK + ", " + DESCRIPCION + ", " + TIPOIMAGEN);
        sqlString.append( ", " + INDICE + " FROM " + TABLEBANNER + " ");
        return sqlString.toString();
    }

    private String getQueryBuscarBanners() {
        StringBuffer sqlString = new StringBuffer();
        sqlString.append(getQueryBaseBanners());
        sqlString.append(" ORDER BY " +INDICE );
        return sqlString.toString();
    }


    private String getQueryBuscarBannersInicio() {
        StringBuffer sqlString = new StringBuffer();
        sqlString.append(getQueryBaseBanners());
        sqlString.append(" WHERE " + ACTIVA + " = " + Utils.getNumeroConfidencialidad(true));
        sqlString.append(" OR ("+FECHAINICIO+ " <= CURRENT_DATE AND "+FECHAFIN+" >= CURRENT_DATE)");
        sqlString.append(" ORDER BY  " + INDICE);
        return sqlString.toString();

    }

    @Override
    protected String getQuery() {
        if(CONSULTA_ARTICULOS_DE_SU_INTERESS.equals(Queries.BUSCAR_ARTICULOS))
        {
            return this.getQueryBuscarArticulos();
        }

        if(CONSULTA_ARTICULOS_DE_SU_INTERESS.equals(Queries.BUSCARETIQUETAARTICULOS)){
            return this.getQueryBuscarEtiquetasArticulo();
        }
        if(CONSULTA_ARTICULOS_DE_SU_INTERESS.equals(Queries.DELETE_ETIQUETA_ARTICULOS)){
            return this.deleteQueryEtiquetasArticulo();
        }
        if(CONSULTA_ARTICULOS_DE_SU_INTERESS.equals(Queries.DELETE_ARTICULO)){
            return this.deleteQueryArticulo();
        }
        if(CONSULTA_ARTICULOS_DE_SU_INTERESS.equals(Queries.BUSCAR_ETIQUETAS_ALFABETICO)){
            return this.getQueryEtiquetasAlfabetico();
        }
        if(CONSULTA_ARTICULOS_DE_SU_INTERESS.equals(Queries.EXISTE_USUARIO)){
            return this.getUsuarioClave();
        }
        if(CONSULTA_ARTICULOS_DE_SU_INTERESS.equals( Queries.GET_ARTIUCLOS_HOME)){
            return this.getQueryArticulosHome();
        }
        if(CONSULTA_ARTICULOS_DE_SU_INTERESS.equals(Queries.BUSCAR_ARTICULOS_POR_ETIQUETA)){
            return this.getQueryArticulosPorEtiqueta();
        }
        if(CONSULTA_ARTICULOS_DE_SU_INTERESS.equals(Queries.BUSCAR_IDETIQUETA_DESDE_ETIQUETA)){
            return this.getQueryIdEtiquetaFromEtiqueta();
        }
        if(CONSULTA_ARTICULOS_DE_SU_INTERESS.equals(Queries.DELETE_ETIQUETA_DE_ARTICULO_POR_ETIQUETA)){
            return this.getQueryDeleteEtiquetaArticuloPorEtiqueta();
        }
        if(CONSULTA_ARTICULOS_DE_SU_INTERESS.equals(Queries.BUSCAR_BANNERS)){
            return this.getQueryBuscarBanners();
        }
        if(CONSULTA_ARTICULOS_DE_SU_INTERESS.equals(Queries.BUSCAR_BANNERS_INICIO)){
            return this.getQueryBuscarBannersInicio();
        }
        return null;
    }



}
