package mx.gob.stps.portal.core.kiosco.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.candidate.service.CandidatoAppServiceRemote;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.kiosco.service.KioscoAppServiceRemote;
import mx.gob.stps.portal.core.kiosco.vo.KioscoRegistroVO;
import mx.gob.stps.portal.core.kiosco.vo.KioscoVO;
import mx.gob.stps.portal.core.oferta.detalle.bo.PerfilBO;
import mx.gob.stps.portal.core.persistencia.facade.KioscoFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.KioscoRegistroFacadeLocal;

/**
 * Session Bean implementation class KioscoAppService
 */
@Stateless(mappedName = "KioscoAppService")
public class KioscoAppService implements KioscoAppServiceRemote {

	@EJB private KioscoFacadeLocal kioscoFacade;
	@EJB private CandidatoAppServiceRemote candidatoAppServiceRemote;
	@EJB private KioscoRegistroFacadeLocal kioscoRegistroFacade;
	
    /**
     * Default constructor. 
     */
    public KioscoAppService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public KioscoVO consultaKiosco(String username) {
		if("".equals(username)||username==null) throw new IllegalArgumentException("Identificador de usuario invalido");
		
		KioscoVO kioscoVo = kioscoFacade.find(username);
		return kioscoVo;
		
	}

	
	public void registraCandidatoKiosco(KioscoRegistroVO vo, long idCandidato) {
		
		try {
			PerfilBO perfilBO = candidatoAppServiceRemote.loadPerfil(idCandidato);
		
			vo.setEmpleadoActualmente(perfilBO.getPerfilLaboral().getEmpleadoActualmente());
			vo.setGenero(perfilBO.getIdGenero());
			vo.setIdExperienciaTotal(perfilBO.getPerfilLaboral().getIdExperienciaTotal());
			vo.setIdNivelEstudios(perfilBO.getGradoPrincipal().getIdNivelEstudio());
			vo.setIdRangoEdad(getRangoEdad(perfilBO.getEdad()));
			
		
			kioscoRegistroFacade.save(vo);
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void registraUsuarioKiosco(KioscoRegistroVO vo) throws PersistenceException{
	
		kioscoRegistroFacade.save(vo);
	}

	private long getRangoEdad(int edad) {
		int idRangoEdad = 0;
		
		if((edad>=15)&&(edad<=19)) idRangoEdad = Constantes.RANGO_EDAD.DE_15_A_19.getIdRangoEdad();
		if((edad>=20)&&(edad<=29)) idRangoEdad = Constantes.RANGO_EDAD.DE_20_A_29.getIdRangoEdad();
		if((edad>=30)&&(edad<=39)) idRangoEdad = Constantes.RANGO_EDAD.DE_30_A_39.getIdRangoEdad();
		if((edad>=40)&&(edad<=49)) idRangoEdad = Constantes.RANGO_EDAD.DE_40_A_49.getIdRangoEdad();
		if((edad>=50)&&(edad<=59)) idRangoEdad = Constantes.RANGO_EDAD.DE_50_A_59.getIdRangoEdad();
		if(edad>=60) idRangoEdad = Constantes.RANGO_EDAD.DE_60_0_MAS.getIdRangoEdad();
		return idRangoEdad;
	}

	

}
