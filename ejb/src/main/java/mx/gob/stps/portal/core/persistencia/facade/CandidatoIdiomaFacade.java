package mx.gob.stps.portal.core.persistencia.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.DOMINIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.IDIOMAS;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.persistencia.entity.CandidatoIdioma;
import mx.gob.stps.portal.utils.Catalogos.CERTIFICACION;

@Stateless
public class CandidatoIdiomaFacade implements CandidatoIdiomaFacadeLocal {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<IdiomaVO> candidatoIdiomasList(long idCandidato) throws PersistenceException {
		String findIdiomasByCandidato = "SELECT c FROM CandidatoIdioma c WHERE c.idCandidato=:idCandidato";
		List<IdiomaVO> list = new ArrayList<IdiomaVO>();
		Query query = entityManager.createQuery(findIdiomasByCandidato);
		query.setParameter("idCandidato", idCandidato);
		try {
			List<Object> result = query.getResultList();
			for(Object resultElement : result) {
				IdiomaVO vo = getCandidatoIdiomaVO((CandidatoIdioma)resultElement);
				list.add(vo);
			}
		}catch (Exception re) {
			re.printStackTrace();
		}
		return list;
	}
	
	@Override
	public long persist(IdiomaVO idiomaVO) throws PersistenceException {
		if (null == idiomaVO || idiomaVO.getIdCandidato() <= 0) return -1;
		if (idiomaVO.getIdIdioma() == IDIOMAS.NINGUNO.getIdOpcion()){
			idiomaVO.setIdCertificacion(Constantes.CATALOGO_OPCION_NINGUNA);
			idiomaVO.setIdDominio(DOMINIO.NINGUNO.getIdOpcion());
			idiomaVO.setIdDominioComprension((int)DOMINIO.NINGUNO.getIdOpcion());
			idiomaVO.setIdDominioEscrito((int)DOMINIO.NINGUNO.getIdOpcion());
			idiomaVO.setIdDominioHabla((int)DOMINIO.NINGUNO.getIdOpcion());
			idiomaVO.setIdDominioLectura((int)DOMINIO.NINGUNO.getIdOpcion());
		}
		try {
			if (idiomaVO.getIdCandidatoIdioma() > 0) {
				CandidatoIdioma idioma = entityManager.find(CandidatoIdioma.class, idiomaVO.getIdCandidatoIdioma());
				if (idioma != null) {
					setCandidatoIdioma(idioma, idiomaVO);
					entityManager.merge(idioma);
				}
			}else {
				CandidatoIdioma idioma = new CandidatoIdioma();
				idioma.setIdCandidato(idiomaVO.getIdCandidato());
				setCandidatoIdioma(idioma, idiomaVO);
				idioma.setFechaAlta(new Date());
				entityManager.persist(idioma);
				idiomaVO.setIdCandidatoIdioma(idioma.getIdCandidatoIdioma());
			}
		} catch (NoResultException re) {
			// No se localiza el idima para su edicion
		}
		return idiomaVO.getIdCandidatoIdioma();
	}
	
	private void setCandidatoIdioma(CandidatoIdioma idioma, IdiomaVO idiomaVO) {
		idioma.setIdIdioma(idiomaVO.getIdIdioma());
		idioma.setIdCertificacion(idiomaVO.getIdCertificacion());
		idioma.setIdDominio(idiomaVO.getIdDominio());
		idioma.setPrincipal(Utils.toLong(idiomaVO.getPrincipal()));
		idioma.setExamenId(Utils.toLong(idiomaVO.getExamenId()));
		if (idiomaVO.getExamenPresentado() == 1 || (idiomaVO.getExamenPresentado() == 2 && idiomaVO.getExamenId() > 0))
			idioma.setExamenPresentado(Utils.toLong(idiomaVO.getExamenPresentado()));
		idioma.setExamenPuntos(Utils.toLong(idiomaVO.getExamenPuntos()));
		idioma.setIdDominioEscrito(Utils.toLong(idiomaVO.getIdDominioEscrito()));
		idioma.setIdDominioHabla(Utils.toLong(idiomaVO.getIdDominioHabla()));
		idioma.setIdDominioLectura(Utils.toLong(idiomaVO.getIdDominioLectura()));
		idioma.setExamenFecha(idiomaVO.getExamenFecha());
	}

	private IdiomaVO getCandidatoIdiomaVO(CandidatoIdioma entity) {
		IdiomaVO vo = new IdiomaVO();
		vo.setIdCandidatoIdioma(entity.getIdCandidatoIdioma());
		vo.setIdCertificacion(entity.getIdCertificacion());
		vo.setIdDominio(entity.getIdDominio());
		vo.setIdIdioma(entity.getIdIdioma());
		vo.setPrincipal(Utils.toInt(entity.getPrincipal()));
		vo.setExamenFecha(entity.getExamenFecha());
		vo.setIdCandidato(entity.getIdCandidato());

		CERTIFICACION cert = CERTIFICACION.getCertificacion((int)vo.getIdIdioma());
		vo.setCertificacion	(cert!=null?cert.getOpcion():"");

		vo.setDominio		(DOMINIO.getDescripcion			((int)vo.getIdDominio()));
		vo.setIdioma		(IDIOMAS.getDescripcion			((int)vo.getIdIdioma()));		
		
		if (null != entity.getExamenId())
			vo.setExamenId(entity.getExamenId().intValue());
		if (null != entity.getExamenPresentado())
			vo.setExamenPresentado(entity.getExamenPresentado().intValue());
		if (null != entity.getExamenPuntos())
			vo.setExamenPuntos(entity.getExamenPuntos().intValue());
		if (null != entity.getIdDominioEscrito())
			vo.setIdDominioEscrito(entity.getIdDominioEscrito().intValue());
		if (null != entity.getIdDominioHabla())
			vo.setIdDominioHabla(entity.getIdDominioHabla().intValue());
		if (null != entity.getIdDominioLectura())
			vo.setIdDominioLectura(entity.getIdDominioLectura().intValue());
		return vo;
	}
}
