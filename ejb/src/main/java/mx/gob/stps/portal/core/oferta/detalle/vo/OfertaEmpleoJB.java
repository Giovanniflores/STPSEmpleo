package mx.gob.stps.portal.core.oferta.detalle.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import oracle.spatial.geometry.JGeometry;
import mx.gob.stps.portal.core.candidate.vo.ConocimientoComputacionVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.CAMBIO_RESIDENCIA;
import mx.gob.stps.portal.core.infra.utils.Constantes.DISPONIBILIDAD_VIAJAR;
import mx.gob.stps.portal.core.infra.utils.Constantes.IDIOMAS;
import mx.gob.stps.portal.core.infra.utils.Constantes.ROLAR_TURNO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.core.oferta.detalle.bo.OfertaIdiomaBO;
import mx.gob.stps.portal.core.oferta.detalle.bo.OfertaRequisitoBO;
import mx.gob.stps.portal.core.oferta.detalle.helper.Utils;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.utils.Catalogos;

public class OfertaEmpleoJB implements Serializable {
	
	private static final long serialVersionUID = 6262133366331559499L;
	
	private String idOfertaEmpleo;

	private String idEmpresa;

	private String tituloOferta;
	
	private String idAreaLaboral;

	private String idOcupacion;

	private String funciones;

	private String diasLaborales;

	private String horaEntrada;

	private String horaSalida;

	private String rolarTurno;

	private String empresaOfrece;

	private String salario;

	private String idTipoContrato;

	private String idJerarquia;

	private String numeroPlazas;

	private String limitePostulantes;

	private String idDiscapacidad;

	private String idCausaVacante;

	private String fechaInicio;

	private String fechaFin;
    private Date fechaVigencia;

	private String disponibilidadViajar;

	private String disponibilidadRadicar;

	private String idNivelEstudio;

	private String idSituacionAcademica;

	private String habilidadGeneral;
	private List<CatalogoOpcionVO> habilidades;
    private String habilidadesConcatenadas;
	
	private String experienciaAnios;

	private int edadRequisito;

	private String edadMinima;

	private String edadMaxima;

	private String genero;

	private String mapaUbicacion;
	
	private JGeometry geoReferencia;

	private String observaciones;

	private long idTerceraEmpresa;

	private String idContacto;

	private String idHorarioDe;

	private String idHorarioA;

	private String idDuracionAproximada;

	private String diasEntrevista;

	private String fuente;
	
	private int fuenteId;

	private String contactoTel;
	
	private String contactoCorreo;
	
	private String contactoDomicilio;
	
	private String fechaAlta;

	private String fechaModificacion;

	private String idTipoEmpleo;
	
	private String estatusOferta;
	
	private String estatus;
	private Catalogos.ESTATUS estatusEnum;
	
    private int estatusOffer;	
	
	private String empresaNombre;
	
	private String gradoEstudios;
	
	private String situacionAcademica;
	
	private String tipoEmpleo;
	
	private String tipoContrato;
	
	private String ubicacion;
	
	private String estatusOfertaCandidato;
	
	private String idiomas;
	
	private String medioContacto;
	
	private List<String> especialidades;
	
	private String carrera;
	
	private String requisitos;
	
	private String idiomasCert;
	
	private String areaLaboral;
	
	private String ocupacion;
	
	private String sector;
	
	private String horario;
	
	private String prestaciones;
	
	private String jerarquia;

	private String horarioLaboral;
	
	private String tipoDiscapacidad;
	
	private String causaOriginaOferta;
	
	private EmpresaVO empresa;
	
	private OfertaEmpleoVO oferta;

	//private TerceraEmpresaVO tercera;
	
	private String idOfertaCandidato;
	
	private OfertaCandidatoVO ofertaCandidatoVO;
	
	private List<OfertaRequisitoVO> requisitosList;
	
	private List<OfertaIdiomaVO> idiomasCertList;
	
	private HashMap<String, String> ubicaciones;
	
	private HashMap<String, String> idiomasMap;
	
	private List<String> sectores;
	
	private List<String> prestacionesList;	
	
	private int IdContactoCorreo;
	private int IdContactoDomicilio;
	private int IdContactoTel;
	private List<TelefonoVO> telefonos;
	private String descripcionesDiscapacidades;
	
	private ConocimientoComputacionVO conocimientoComputacion;
	
	private String correoElectronicoContacto;
	private String telefonoOferta;
	
	@SuppressWarnings("unused")
	private String conocimientoComputacionDesc;
	
	private String cargoContacto;
	
	private DomicilioVO domicilio;

	public OfertaEmpleoJB(){}
	
	public OfertaEmpleoJB(HashMap<String, String> ubicaciones){
		this.ubicaciones = ubicaciones;
	}

	public OfertaEmpleoJB(OfertaEmpleoVO ofertaEmpleoVO, EmpresaVO empresaVO,
            List<OfertaRequisitoVO> requisitosList, List<OfertaIdiomaVO> idiomasCertList, 
            HashMap<String, String> ubicaciones, HashMap<String, String> idiomasMap, List<String> especialidades,
            List<String> sectores, List<String> prestacionesList, String areaLaboral, String ocupacion) {
		
		if (null == ofertaEmpleoVO) ofertaEmpleoVO = new OfertaEmpleoVO();

		//this.ofertaCandidatoVO = ofertaCandidatoVO;
		this.empresa = empresaVO;
		this.requisitosList = requisitosList;
		this.idiomasCertList = idiomasCertList;
		//this.tercera = tercera;
		this.ubicaciones = ubicaciones;
		this.idiomasMap = idiomasMap;
		this.especialidades = especialidades;
		this.sectores = sectores;
		this.prestacionesList = prestacionesList;
		this.areaLaboral = areaLaboral;
		this.ocupacion = ocupacion;
		setOfferEmployVO(ofertaEmpleoVO);
	}
	
	public OfertaEmpleoJB(OfertaCandidatoVO ofertaCandidatoVO, OfertaEmpleoVO ofertaEmpleoVO, EmpresaVO empresaVO,
			              List<OfertaRequisitoVO> requisitosList, List<OfertaIdiomaVO> idiomasCertList, 
			              HashMap<String, String> ubicaciones, HashMap<String, String> idiomasMap, List<String> especialidades,
			              List<String> sectores, List<String> prestacionesList, String areaLaboral, String ocupacion) {

		//OfertaEmpleoVO vo = new OfertaEmpleoVO();
		this.ofertaCandidatoVO = ofertaCandidatoVO;
		this.empresa = empresaVO;
		this.requisitosList = requisitosList;
		this.idiomasCertList = idiomasCertList;
		//this.tercera = tercera;
		this.ubicaciones = ubicaciones;
		this.idiomasMap = idiomasMap;
		this.especialidades = especialidades;
		this.sectores = sectores;
		this.prestacionesList = prestacionesList;
		this.areaLaboral = areaLaboral;
		this.ocupacion = ocupacion;
		
		if (this.ofertaCandidatoVO.getIdOfertaEmpleo() > -1) {
			setIdOfertaCandidato(ofertaCandidatoVO.getIdOfertaCandidato());
		}
		
		setOfferEmployVO(ofertaEmpleoVO);
	}

	private void setOfferEmployVO(OfertaEmpleoVO vo) {
		if (vo != null) {
			this.oferta = vo;
			setEmpresa(vo.getIdEmpresa());
			setUbicacion(vo.getIdOfertaEmpleo());
			setEstatusOfertaCandidato();
			setIdiomas(vo.getIdOfertaEmpleo());
			setSector(vo.getIdOfertaEmpleo());
			setHorario(vo.getHoraEntrada(), vo.getHoraSalida());
			setPrestaciones(vo.getIdOfertaEmpleo());
			setJerarquia(vo.getIdJerarquia());
			setEspecialidades(vo.getIdOfertaEmpleo(), vo.getIdNivelEstudio());
			setRequisitos(vo.getIdOfertaEmpleo());
			setIdiomasCert(vo.getIdOfertaEmpleo());
			//RBM1 TK1000 TK1001 se asigno en el constructor			
			//setAreaLaboral(vo.getIdAreaLaboral());
			//setOcupacion(vo.getIdOcupacion());

			setTituloOferta(vo.getTituloOferta());
			this.IdContactoCorreo = oferta.getContactoCorreo();
			this.IdContactoDomicilio = oferta.getContactoDomicilio();
			this.IdContactoTel = oferta.getContactoTel();	
			this.telefonos = oferta.getTelefonos();
			setContactoCorreo(vo.getContactoCorreo());
			setCorreoElectronicoContacto(vo.getCorreoElectronicoContacto());
			setContactoTel(vo.getContactoTel());
			setContactoDomicilio(vo.getContactoDomicilio());
			setDiasEntrevista(vo.getDiasEntrevista());
			setDiasLaborales(vo.getDiasLaborales());
			setDisponibilidadRadicar(vo.getDisponibilidadRadicar());
			setDisponibilidadViajar(vo.getDisponibilidadViajar());
			setEdadMaxima(vo.getEdadMaxima());
			setEdadMinima(vo.getEdadMinima());
			setEdadRequisito(vo.getEdadRequisito());
			setEmpresaOfrece(vo.getEmpresaOfrece());
			setFuenteId(vo.getFuente());
//			this.estatus = Utils.setEstatusLbl(vo.getEstatus());
			this.estatus = String.valueOf(Catalogos.ESTATUS.getEstatus(vo.getEstatus()).getOpcion());
			this.estatusEnum = Catalogos.ESTATUS.getEstatus(vo.getEstatus());
			setEstatusOffer(vo.getEstatus());
			setExperienciaAnios(vo.getExperienciaAnios());
			setFechaAlta(vo.getFechaAlta());
			setFechaFin(vo.getFechaFin());
			setFechaInicio(vo.getFechaInicio());
			setFechaModificacion(vo.getFechaModificacion());
			setFuente(vo.getFuente());
			setFunciones(vo.getFunciones());
			setGenero(vo.getGenero());
			setHabilidadGeneral(vo.getHabilidadGeneral());
			setHoraEntrada(vo.getHoraEntrada());
			setHoraSalida(vo.getHoraSalida());
			setIdAreaLaboral(vo.getIdAreaLaboral());
			setIdCausaVacante(vo.getIdCausaVacante());
			setIdContacto(vo.getIdContacto());
			setIdDiscapacidad(vo.getIdDiscapacidad());
			setIdDuracionAproximada(vo.getIdDuracionAproximada());
			setIdEmpresa(vo.getIdEmpresa());
			//setEmpresaNombre();
			this.empresaNombre = oferta.getNombreEmpresa();
			setIdHorarioA(vo.getIdHorarioA());
			setIdHorarioDe(vo.getIdHorarioDe());
			setIdJerarquia(vo.getIdJerarquia());
			setIdNivelEstudio(vo.getIdNivelEstudio());
			setGradoEstudios((int)vo.getIdNivelEstudio());
			setIdOcupacion(vo.getIdOcupacion());
			setIdOfertaEmpleo(vo.getIdOfertaEmpleo());
			setIdSituacionAcademica(vo.getIdSituacionAcademica());
			setSituacionAcademica((int)vo.getIdSituacionAcademica());
			setIdTerceraEmpresa(vo.getIdTerceraEmpresa());
			setIdTipoContrato(vo.getIdTipoContrato());
			setIdTipoEmpleo(vo.getIdTipoEmpleo());
			setTipoEmpleo((int)vo.getIdTipoEmpleo());
			setTipoContrato((int)vo.getIdTipoContrato());
			setLimitePostulantes(vo.getLimitePostulantes());
			setMapaUbicacion(vo.getMapaUbicacion());
			setGeoReferencia(vo.getGeoReferencia());
			setNumeroPlazas(vo.getNumeroPlazas());
			setObservaciones(vo.getObservaciones());
			setRolarTurno(vo.getRolarTurno());
			setSalario(vo.getSalario());
			setMedioContacto();
			setTipoDiscapacidad((int)vo.getIdDiscapacidad());
			setCausaOriginaOferta((int)vo.getIdDiscapacidad());
			setFuenteId(vo.getFuente());
			setCargoContacto(vo.getCargoContacto());
			setConocimientoComputacion(vo.getConocimientoComputacion());
		}
	}
	
	public void setIdOfertaCandidato(long idOfertaCandidato) {
		if (idOfertaCandidato > 0)
			this.idOfertaCandidato = String.valueOf(idOfertaCandidato);
		else
			this.idOfertaCandidato = "";
	}
	
	public String getIdOfertaCandidato() {
		return this.idOfertaCandidato;
	}
	
	/**
	 * Method 'getFuente'
	 * 
	 * @return int
	 */
	public String getFuente() {
		return fuente;
	}

	/**
	 * Method 'setFuente'
	 * 
	 * @param fuente
	 */
	public void setFuente(int fuente) {
		//Fuente de la oferta de empleo del catalogo
		this.fuente = "Portal del empleo";
	}
	
	public byte[] getEmpresaLogo() {
		if (null != this.empresa && null != this.empresa.getLogotipo())
			return empresa.getLogotipo();
		else
			return new byte[] {};
	}
	
	private void setJerarquia(long idJerarquia) {
		if (idJerarquia > -1)
			this.jerarquia = Utils.getJerarquia((int) idJerarquia);
		else
			this.jerarquia = "";
	}
	
	public String getJerarquia() {
		return this.jerarquia;
	}
	
	private void setGradoEstudios(int degree) {
		if (degree > -1) 
			this.gradoEstudios = Utils.getGradoEstudios(degree);
		else
			this.gradoEstudios = "";
	}

	public void setGradoEstudios(String gradoEstudios) { 
		this.gradoEstudios = gradoEstudios;
	}

	public String getGradoEstudios() {
		return this.gradoEstudios;
	}
	
	
	private void setTipoContrato(int type) {
		if (type > 0)
			this.tipoContrato = Utils.getTipoContrato(type);
		else
			this.tipoContrato = "";
	}
	
	public String getTipoContrato() {
		return this.tipoContrato;
	}
	
	private void setTipoEmpleo(int type) {
		if (type > -1)
			this.tipoEmpleo = Utils.getTipoEmpleo(type);
		else
			this.tipoEmpleo = "";
	}

	public void setTipoEmpleo(String tipoEmpleo) {
		this.tipoEmpleo = tipoEmpleo;
	}

	public String getTipoEmpleo() {
		return this.tipoEmpleo;
	}
	
	private void setSituacionAcademica(int status) {
		if (status > 0)
			this.situacionAcademica = Utils.getSituacionAcademica(status);
		else
			this.situacionAcademica = "";
	}
	
	public String getSituacionAcademica() {
		return this.situacionAcademica;
	}
	
	private void setTipoDiscapacidad(int type) {
		if (type > -1)
			this.tipoDiscapacidad = Utils.getTipoDiscapacidad(type);
		else
			this.tipoDiscapacidad = "";
	}
	
	public String getTipoDiscapacidad() {
		return this.tipoDiscapacidad;
	}
	
	private void setCausaOriginaOferta(int type) {
		if (type > -1)
			this.causaOriginaOferta = Utils.getCausaOferta(type);
		else
			this.causaOriginaOferta = "";
	}
	
	public String getCausaOriginaOferta() {
		return this.causaOriginaOferta;
	}
	
	public String getEmpresaNombre() {
		return this.empresaNombre;
	}
	
//	private void setEmpresaNombre() {
//		if (getEmpresa()!=null){
//			if (getEmpresa().getIdTipoPersona() == TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona())
//				empresaNombre = getEmpresa().getNombre() + " " + getEmpresa().getApellido1() + " " + (null != getEmpresa().getApellido2() ? getEmpresa().getApellido2() : "");
//			else
//				empresaNombre = (null != getEmpresa().getRazonSocial() ? getEmpresa().getRazonSocial() : "");
//		}
//	}

	public void setEmpresaNombre(String empresaNombre) {
		this.empresaNombre = empresaNombre;
	}

	private void setMedioContacto() {
		this.medioContacto = "";		
		/*
		if(this.contactoCorreo.equals(Constantes.CONTACTO_CORREO.NO.getIdContactoCorreo())) {
			if(this.idTerceraEmpresa > 0) {
				TerceraEmpresaVO vo = getTerceraEmpresaVO(this.idTerceraEmpresa);
				this.tercera = vo;
				if (null != vo.getTelefonos()) {
					Iterator<TelefonoVO> itels = vo.getTelefonos().iterator();
					while (itels.hasNext()) {
						TelefonoVO tel = itels.next();
						if (tel.getPrincipal() == 2)
							this.medioContacto = "Contactar al telï¿½fono: " + tel.getTelefono();
					}
				}else
					this.medioContacto = "";
			}else {
				Iterator<TelefonoVO> itels = this.empresa.getTelefonos().iterator();
				while (itels.hasNext()) {
					TelefonoVO tel = itels.next();
					if (tel.getPrincipal() == 2)
						this.medioContacto = "Contactar al telï¿½fono: " + tel.getTelefono();
				}
			}
		}else {
			if(this.idTerceraEmpresa > 0) {
				TerceraEmpresaVO vo = getTerceraEmpresaVO(this.idTerceraEmpresa);
				this.tercera = vo;
				this.medioContacto = "Enviar CV al correo electrï¿½nico: " + vo.getCorreoElectronico();
			}else {
				this.medioContacto = "Enviar CV al correo electrï¿½nico: " + this.empresa.getCorreoElectronico();
			}
		}
		*/
	}
	
	public String getMedioContacto() {
		return this.medioContacto;
	}
	
	private void setEmpresa(long companyID) {
		EmpresaVO vo = new EmpresaVO();
		if (companyID > 0)
			vo = getEmpresaVO(companyID);
		if (null != vo)
			this.empresa = vo;
	}

	private EmpresaVO getEmpresaVO(long companyID) {
		return this.empresa;
	}
	
	public EmpresaVO getEmpresa() {
		return this.empresa;
	}
	
	private void setRequisitos(long idOfertaEmpleo) {
		this.requisitos = "";
		StringBuilder req = new StringBuilder();
		StringBuilder hab = new StringBuilder();
		StringBuilder com = new StringBuilder();
		List<OfertaRequisitoVO> list = new ArrayList<OfertaRequisitoVO>();
		

			list = requisitosList;
			Iterator<OfertaRequisitoVO> it = list.iterator();
			while (it.hasNext()) {
				OfertaRequisitoBO bo = new OfertaRequisitoBO(it.next());
				if (bo.tipoRequisito() == 1)
					req.append(", " + bo.toString());
				else if (bo.tipoRequisito() == 2)
					hab.append(", " + bo.toString());
				else if (bo.tipoRequisito() == 3)
					com.append(", " + bo.toString());
			}
			if (req.length() < 3) hab = hab.replace(0, 2, "");
			this.requisitos = req.toString().replaceFirst(", ","") + hab.toString() + com.toString();

	}
	
	public String getRequisitos() {
		return this.requisitos;
	}
	
	private void setIdiomasCert(long idOfertaEmpleo) {
		this.idiomasCert = "";
		StringBuilder req = new StringBuilder();
		if (idiomasCertList!=null){
			for (OfertaIdiomaVO idiomaVO : idiomasCertList){				
				OfertaIdiomaBO bo = new OfertaIdiomaBO(idiomaVO, idiomaVO.getCertificacion());
				 if (!IDIOMAS.NO_REQUISITO.getOpcion().equals(bo.getIdioma()) && !IDIOMAS.NINGUNO.getOpcion().equals(bo.getIdioma()))				
					 req.append(", " + bo.toString());			
			}
		}
		this.idiomasCert = (req.length() == 0 ? IDIOMAS.NO_REQUISITO.getOpcion() : req.toString().replaceFirst(", ",""));		
	}
	
	public String getIdiomasCert() {
		return this.idiomasCert;
	}

	private void setUbicacion(long idOfertaEmpleo) {
		this.ubicacion = "";
		
		if (this.ubicaciones!=null && null != this.ubicaciones.get("entidad"))
			this.ubicacion = this.ubicaciones.get("entidad");
				
		if (this.ubicaciones!=null && null != this.ubicaciones.get("municipio"))
			this.ubicacion += ", " + this.ubicaciones.get("municipio");
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	private void setIdiomas(long idOfertaEmpleo) {
		this.idiomas = "";

		if (this.idiomasMap!=null){
			Iterator<String> it =  this.idiomasMap.keySet().iterator();
			while (it.hasNext()) {
				String idioma = it.next();
				String dominio = this.idiomasMap.get(idioma);
				this.idiomas += idioma + ": " + dominio + ", ";
			}
		}
		
		this.idiomas = this.idiomas.toString().replaceFirst(", ","");
	}

	public void setIdiomas(String idiomas) {
		this.idiomas = idiomas;
	}

	private void setEspecialidades(long idOfertaEmpleo, long idNivelEstudio) {
		//this.especialidades = list;
	}
	
	private void setSector(long idOfertaEmpleo) {
		this.sector = "";
		StringBuilder ret = new StringBuilder();

		if (this.sectores!=null){
			for (String aux : this.sectores){
				ret.append(", " + aux);
			}
		}

		this.sector = ret.toString().replaceFirst(", ","");
	}
	
	private void setPrestaciones(long idOfertaEmpleo) {
		this.prestaciones = "";
		StringBuilder ret = new StringBuilder();

		if (prestacionesList!=null){
			for (String aux : prestacionesList){
				ret.append(", " + aux);	
			}
		}

		this.prestaciones = ret.toString().replaceFirst(", ","");
	}
	
	//RBM1 TK1000 TK1001
	public void setAreaLaboral(String areaLAboral) {
		this.areaLaboral = areaLAboral;
	}
	
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}
	
	public String getPrestaciones() {
		return this.prestaciones;
	}
	
	public String getIdiomas() {
		return this.idiomas;
	}
	
	public String getSector() {
		return this.sector;
	}
	
	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public String getEspecialidades() {
		if (especialidades==null) return getCarrera();
		
		StringBuilder ret = new StringBuilder();
		if (!this.especialidades.isEmpty()) {
			Iterator<String> it = this.especialidades.iterator();
			while (it.hasNext()) {
				ret.append(", " + it.next());
			}
			return ret.toString().replaceFirst(", ","");
		}else
			return "";
	}
	
	public String getAreaLaboral() {
		return this.areaLaboral;
	}
	
	public String getOcupacion() {
		return this.ocupacion;
	}
	
	private void setEstatusOfertaCandidato() {
		if (null != this.ofertaCandidatoVO)
			this.estatusOfertaCandidato = Utils.setEstatusLbl(this.ofertaCandidatoVO.getEstatus());
		else
			this.estatusOfertaCandidato = "";
	}

	public void setEstatusOfertaCandidato(String estatusOfertaCandidato) {
		this.estatusOfertaCandidato = estatusOfertaCandidato;
	}
	
	public String getUbicacion() {
		return this.ubicacion;
	}
	
	/**
	 * Method 'getEstatus'
	 * 
	 * @return String
	 */
	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	
	public Catalogos.ESTATUS getEstatusEnum() {
		return estatusEnum;
	}

    public void setEstatusEnum(Catalogos.ESTATUS estatusEnum) {
    	this.estatusEnum = estatusEnum;
	}
	
	public int getEstatusOffer() {
		return estatusOffer;
	}

	public void setEstatusOffer(int estatusOffer) {
		this.estatusOffer = estatusOffer;
	}	

	public String getEstatusOferta() {
		return estatusOferta;
	}

	public void setEstatusOferta(String estatusOferta) {
		this.estatusOferta = estatusOferta;
	}

	/**
	 * Method 'getEstatusOfertaCandidato'
	 * 
	 * @return String
	 */
	public String getEstatusOfertaCandidato() {
		return this.estatusOfertaCandidato;
	}
	
	/**
	 * Method 'getIdOfertaEmpleo'
	 * 
	 * @return int
	 */
	public String getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}

	/**
	 * Method 'setIdOfertaEmpleo'
	 * 
	 * @param idOfertaEmpleo
	 */
	public void setIdOfertaEmpleo(long idOfertaEmpleo) {
		this.idOfertaEmpleo = "" + idOfertaEmpleo;
	}

	/**
	 * Method 'getIdEmpresa'
	 * 
	 * @return int
	 */
	public String getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * Method 'setIdEmpresa'
	 * 
	 * @param idEmpresa
	 */
	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = "" + idEmpresa;
	}

	/**
	 * Method 'getTituloOferta'
	 * 
	 * @return String
	 */
	public String getTituloOferta() {
		return tituloOferta;
	}

	/**
	 * Method 'setTituloOferta'
	 * 
	 * @param tituloOferta
	 */
	public void setTituloOferta(String tituloOferta) {
		this.tituloOferta = Utils.capitalize(tituloOferta);
	}

	/**
	 * Method 'getIdAreaLaboral'
	 * 
	 * @return int
	 */
	public String getIdAreaLaboral() {
		return idAreaLaboral;
	}

	/**
	 * Method 'setIdAreaLaboral'
	 * 
	 * @param idAreaLaboral
	 */
	public void setIdAreaLaboral(long idAreaLaboral) {
		this.idAreaLaboral = "" + idAreaLaboral;
	}

	/**
	 * Method 'getIdOcupacion'
	 * 
	 * @return int
	 */
	public String getIdOcupacion() {
		return idOcupacion;
	}

	/**
	 * Method 'setIdOcupacion'
	 * 
	 * @param idOcupacion
	 */
	public void setIdOcupacion(long idOcupacion) {
		this.idOcupacion = "" + idOcupacion;
	}

	/**
	 * Method 'getFunciones'
	 * 
	 * @return String
	 */
	public String getFunciones() {
		return funciones;
	}

	/**
	 * Method 'setFunciones'
	 * 
	 * @param funciones
	 */
	public void setFunciones(String funciones) {
		this.funciones = Utils.capitalize(funciones);
	}

	/**
	 * Method 'getDiasLaborales'
	 * 
	 * @return String
	 */
	public String getDiasLaborales() {
		return this.diasLaborales;
	}

	/**
	 * Method 'setDiasLaborales'
	 * 
	 * @param diasLaborales
	 */
	public void setDiasLaborales(String diasLaborales) {
		if (null != diasLaborales && !"0000000".equals(diasLaborales))
			this.diasLaborales = Utils.getLblDiasLaborales(diasLaborales);
		else
			this.diasLaborales = "";
	}

	/**
	 * Method 'getHoraEntrada'
	 * 
	 * @return String
	 */
	public String getHoraEntrada() {
		return horaEntrada;
	}

	/**
	 * Method 'setHoraEntrada'
	 * 
	 * @param horaEntrada
	 */
	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	/**
	 * Method 'getHoraSalida'
	 * 
	 * @return String
	 */
	public String getHoraSalida() {
		return horaSalida;
	}

	/**
	 * Method 'setHoraSalida'
	 * 
	 * @param horaSalida
	 */
	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	/**
	 * Method 'getRolarTurno'
	 * 
	 * @return int
	 */
	public String getRolarTurno() {
		return rolarTurno;
	}

	/**
	 * Method 'setRolarTurno'
	 * 
	 * @param rolarTurno
	 */
	public void setRolarTurno(int rolarTurno) {
		if (rolarTurno == ROLAR_TURNO.SI.getIdOpcion())
			this.rolarTurno = "Disponibilidad para rolar turno"; 
		else
			this.rolarTurno = "No";
	}

	/**
	 * Method 'getEmpresaOfrece'
	 * 
	 * @return String
	 */
	public String getEmpresaOfrece() {
		return empresaOfrece;
	}

	/**
	 * Method 'setEmpresaOfrece'
	 * 
	 * @param empresaOfrece
	 */
	public void setEmpresaOfrece(String empresaOfrece) {
		this.empresaOfrece = empresaOfrece;
	}

	/**
	 * Method 'getSalario'
	 * 
	 * @return double
	 */
	public String getSalario() {
		return salario;
	}

	/**
	 * Method 'setSalario'
	 * 
	 * @param salario
	 */
	public void setSalario(double salario) {
		if (salario > 0) {
			String salary = String.valueOf(salario);
			int index = salary.indexOf(".");
			if (index != -1)
				this.salario = salary.substring(0, index);
			else
				this.salario = salary;
		}else
			this.salario = "";
	}

	/**
	 * Method 'getIdTipoContrato'
	 * 
	 * @return int
	 */
	public String getIdTipoContrato() {
		return idTipoContrato;
	}

	/**
	 * Method 'setIdTipoContrato'
	 * 
	 * @param idTipoContrato
	 */
	public void setIdTipoContrato(long idTipoContrato) {
		this.idTipoContrato = "" + idTipoContrato;
	}

	/**
	 * Method 'getIdJerarquia'
	 * 
	 * @return int
	 */
	public String getIdJerarquia() {
		return idJerarquia;
	}

	/**
	 * Method 'setIdJerarquia'
	 * 
	 * @param idJerarquia
	 */
	public void setIdJerarquia(long idJerarquia) {
		this.idJerarquia = "" + idJerarquia;
	}

	/**
	 * Method 'getNumeroPlazas'
	 * 
	 * @return int
	 */
	public String getNumeroPlazas() {
		return numeroPlazas;
	}

	/**
	 * Method 'setNumeroPlazas'
	 * 
	 * @param numeroPlazas
	 */
	public void setNumeroPlazas(int numeroPlazas) {
		this.numeroPlazas = "" + numeroPlazas;
	}

	/**
	 * Method 'getLimitePostulantes'
	 * 
	 * @return int
	 */
	public String getLimitePostulantes() {
		return limitePostulantes;
	}

	/**
	 * Method 'setLimitePostulantes'
	 * 
	 * @param limitePostulantes
	 */
	public void setLimitePostulantes(int limitePostulantes) {
		this.limitePostulantes = "" + limitePostulantes;
	}

	/**
	 * Method 'getIdDiscapacidad'
	 * 
	 * @return int
	 */
	public String getIdDiscapacidad() {
		return idDiscapacidad;
	}

	/**
	 * Method 'setIdDiscapacidad'
	 * 
	 * @param idDiscapacidad
	 */
	public void setIdDiscapacidad(long idDiscapacidad) {
		this.idDiscapacidad = "" + idDiscapacidad;
	}

	/**
	 * Method 'getIdCausaVacante'
	 * 
	 * @return int
	 */
	public String getIdCausaVacante() {
		return idCausaVacante;
	}

	/**
	 * Method 'setIdCausaVacante'
	 * 
	 * @param idCausaVacante
	 */
	public void setIdCausaVacante(long idCausaVacante) {
		this.idCausaVacante = "" + idCausaVacante;
	}

	/**
	 * Method 'getFechaInicio'
	 * 
	 * @return Date
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Method 'setFechaInicio'
	 * 
	 * @param fechaInicio
	 */
	public void setFechaInicio(Date fechaInicio) {
		String date = Utils.formatDate(fechaInicio);
		if (null != date)
			this.fechaInicio = date.toString();
		else
			this.fechaInicio = "";
	}

	/**
	 * Method 'getFechaFin'
	 * 
	 * @return Date
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * Method 'setFechaFin'
	 * 
	 * @param fechaFin
	 */
	public void setFechaFin(Date fechaFin) {
		String date = Utils.formatDate(fechaFin);
		if (null != date)
			this.fechaFin = date;
		else
			this.fechaFin = "";
	}

    public Date getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(Date fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    /**
	 * Method 'getDisponibilidadViajar'
	 * 
	 * @return int
	 */
	public String getDisponibilidadViajar() {
		return this.disponibilidadViajar;
	}

	/**
	 * Method 'setDisponibilidadViajar'
	 * 
	 * @param disponibilidadViajar
	 */
	private void setDisponibilidadViajar(int travelViability) {
		if (travelViability == DISPONIBILIDAD_VIAJAR.SI.getIdOpcion())
			this.disponibilidadViajar = "Disponibilidad para viajar";
		else
			this.disponibilidadViajar = "";
	}

	/**
	 * Method 'getDisponibilidadRadicar'
	 * 
	 * @return int
	 */
	public String getDisponibilidadRadicar() {
		return this.disponibilidadRadicar;
	}

	/**
	 * Method 'setDisponibilidadRadicar'
	 * 
	 * @param disponibilidadRadicar
	 */
	public void setDisponibilidadRadicar(int disponibilidadRadicar) {
		if (disponibilidadRadicar == CAMBIO_RESIDENCIA.SI.getIdOpcion())
			this.disponibilidadRadicar = "Disponibilidad para cambio de residencia";
		else
			this.disponibilidadRadicar = "";
	}

	/**
	 * Method 'getIdNivelEstudio'
	 * 
	 * @return int
	 */
	public String getIdNivelEstudio() {
		return idNivelEstudio;
	}

	/**
	 * Method 'setIdNivelEstudio'
	 * 
	 * @param idNivelEstudio
	 */
	public void setIdNivelEstudio(long idNivelEstudio) {
		this.idNivelEstudio = "" + idNivelEstudio;
	}

	/**
	 * Method 'getIdSituacionAcademica'
	 * 
	 * @return int
	 */
	public String getIdSituacionAcademica() {
		return idSituacionAcademica;
	}

	/**
	 * Method 'setIdSituacionAcademica'
	 * 
	 * @param idSituacionAcademica
	 */
	public void setIdSituacionAcademica(long idSituacionAcademica) {
		this.idSituacionAcademica = "" + idSituacionAcademica;
	}

	/**
	 * Method 'getHabilidadGeneral'
	 * 
	 * @return String
	 */
	public String getHabilidadGeneral() {
		return habilidadGeneral;
	}

	/**
	 * Method 'setHabilidadGeneral'
	 * 
	 * @param habilidadGeneral
	 */
	public void setHabilidadGeneral(String habilidadGeneral) {
		this.habilidadGeneral = (null != habilidadGeneral ? Utils.capitalize(habilidadGeneral) : "");
	}

	public List<CatalogoOpcionVO> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<CatalogoOpcionVO> habilidades) {
		this.habilidades = habilidades;
	}

    public String getHabilidadesConcatenadas() {
        return habilidadesConcatenadas;
    }

    public void setHabilidadesConcatenadas(String habilidadesConcatenadas) {
        this.habilidadesConcatenadas = habilidadesConcatenadas;
    }

	/**
	 * Method 'getExperienciaAnios'
	 * 
	 * @return int
	 */
	public String getExperienciaAnios() {
		return experienciaAnios;
	}

	/**
	 * Method 'setExperienciaAnios'
	 * 
	 * @param experienciaAnios
	 */
	public void setExperienciaAnios(int experienciaAnios) {
		this.experienciaAnios = Utils.getExperiencia(experienciaAnios);
	}

	/**
	 * Method 'getEdadRequisito'
	 * 
	 * @return int
	 */
	public int getEdadRequisito() {
		return edadRequisito;
	}

	/**
	 * Method 'setEdadRequisito'
	 * 
	 * @param edadRequisito
	 */
	public void setEdadRequisito(int edadRequisito) {
		this.edadRequisito = edadRequisito;
	}

	/**
	 * Method 'getEdadMinima'
	 * 
	 * @return int
	 */
	public String getEdadMinima() {
		return edadMinima;
	}

	/**
	 * Method 'setEdadMinima'
	 * 
	 * @param edadMinima
	 */
	public void setEdadMinima(int edadMinima) {
		this.edadMinima = "" + edadMinima;
	}

	/**
	 * Method 'getEdadMaxima'
	 * 
	 * @return int
	 */
	public String getEdadMaxima() {
		return edadMaxima;
	}

	/**
	 * Method 'setEdadMaxima'
	 * 
	 * @param edadMaxima
	 */
	public void setEdadMaxima(int edadMaxima) {
		this.edadMaxima = "" + edadMaxima;
	}

	/**
	 * Method 'getGenero'
	 * 
	 * @return int
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * Method 'setGenero'
	 * 
	 * @param genero
	 */
	public void setGenero(int genero) {
		this.genero = "" + genero;
	}

	/**
	 * Method 'getMapaUbicacion'
	 * 
	 * @return String
	 */
	public String getMapaUbicacion() {
		return mapaUbicacion;
	}

	/**
	 * Method 'setMapaUbicacion'
	 * 
	 * @param mapaUbicacion
	 */
	public void setMapaUbicacion(String mapaUbicacion) {
		if (null != mapaUbicacion)
			this.mapaUbicacion = mapaUbicacion;
		else
			this.mapaUbicacion = "";
	}

	public JGeometry getGeoReferencia() {
		return geoReferencia;
	}

	public void setGeoReferencia(JGeometry geoReferencia) {
		this.geoReferencia = geoReferencia;
	}

	/**
	 * Method 'getObservaciones'
	 * 
	 * @return String
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * Method 'setObservaciones'
	 * 
	 * @param observaciones
	 */
	public void setObservaciones(String observaciones) {
		if (null != observaciones)
			this.observaciones = observaciones;
		else
			this.observaciones = "Ninguna";
	}

	/**
	 * Method 'getIdTerceraEmpresa'
	 * 
	 * @return int
	 */
	public long getIdTerceraEmpresa() {
		return idTerceraEmpresa;
	}

	/**
	 * Method 'setIdTerceraEmpresa'
	 * 
	 * @param idTerceraEmpresa
	 */
	public void setIdTerceraEmpresa(long idTerceraEmpresa) {
		this.idTerceraEmpresa = idTerceraEmpresa;
	}

	/**
	 * Method 'getIdContacto'
	 * 
	 * @return int
	 */
	public String getIdContacto() {
		return idContacto;
	}

	/**
	 * Method 'setIdContacto'
	 * 
	 * @param idContacto
	 */
	public void setIdContacto(long idContacto) {
		this.idContacto = "" + idContacto;
	}


	/**
	 * Method 'getIdHorarioDe'
	 * 
	 * @return int
	 */
	public String getIdHorarioDe() {
		return idHorarioDe;
	}

	/**
	 * Method 'setIdHorarioDe'
	 * 
	 * @param idHorarioDe
	 */
	public void setIdHorarioDe(long idHorarioDe) {
		this.idHorarioDe = "" + idHorarioDe;
	}

	/**
	 * Method 'getIdHorarioA'
	 * 
	 * @return int
	 */
	public String getIdHorarioA() {
		return idHorarioA;
	}

	/**
	 * Method 'setIdHorarioA'
	 * 
	 * @param idHorarioA
	 */
	public void setIdHorarioA(long idHorarioA) {
		this.idHorarioA = "" + idHorarioA;
	}

	private void setHorario(String idHorarioDe, String idHorarioA) {
		this.horario = "";
		if (null != idHorarioDe)
			this.horario = Utils.getHorario(idHorarioDe);
		if (null != idHorarioA)
			this.horario += " a " + Utils.getHorario(idHorarioA);
	}
	
	public String getHorario() {
		return this.horario;
	}
	
	public String getHorarioLaboral() {
		this.horarioLaboral = "";
		if (null != this.horaEntrada) 
			this.horarioLaboral = this.horaEntrada+":00";
		if (null != this.horaSalida)
			this.horarioLaboral += " a " + this.horaSalida+":00";
		return this.horarioLaboral;
	}
	
	/**
	 * Method 'getIdDuracionAproximada'
	 * 
	 * @return int
	 */
	public String getIdDuracionAproximada() {
		return idDuracionAproximada;
	}

	/**
	 * Method 'setIdDuracionAproximada'
	 * 
	 * @param idDuracionAproximada
	 */
	public void setIdDuracionAproximada(long idDuracionAproximada) {
		this.idDuracionAproximada = "" + idDuracionAproximada;
	}

	/**
	 * Method 'getDiasEntrevista'
	 * 
	 * @return String
	 */
	public String getDiasEntrevista() {
		return diasEntrevista;
	}

	/**
	 * Method 'setDiasEntrevista'
	 * 
	 * @param diasEntrevista
	 */
	public void setDiasEntrevista(String diasEntrevista) {
		this.diasEntrevista = diasEntrevista;
	}

	/**
	 * Method 'getFechaAlta'
	 * 
	 * @return Date
	 */
	public String getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * Method 'setfechaModificacion'
	 * 
	 * @param fechaModificacion
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		if (null != fechaModificacion)
			this.fechaModificacion = fechaModificacion.toString();
		else
			this.fechaModificacion = "";
	}
	
	
	/**
	 * Method 'getfechaModificacion'
	 * 
	 * @return Date
	 */
	public String getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * Method 'setFechaAlta'
	 * 
	 * @param fechaAlta
	 */
	public void setFechaAlta(Date fechaAlta) {
		if (null != fechaAlta)
			this.fechaAlta = fechaAlta.toString();
		else
			this.fechaAlta = "";
	}
	
	public String getContactoTel() {
		return contactoTel;
	}

	public void setContactoTel(int contactoTel) {
		this.contactoTel = "" + contactoTel;
	}

	public String getContactoCorreo() {
		return contactoCorreo;
	}

	public void setContactoCorreo(int contactoCorreo) {
		this.contactoCorreo = "" + contactoCorreo;
	}
	
	public String getIdTipoEmpleo() {
		return idTipoEmpleo;
	}

	public void setIdTipoEmpleo(long idTipoEmpleo) {
		this.idTipoEmpleo = "" + idTipoEmpleo;
	}

	/**
	 * @param fuenteId the fuenteId to set
	 */
	public void setFuenteId(int fuenteId) {
		this.fuenteId = fuenteId;
	}

	/**
	 * @return the fuenteId
	 */
	public int getFuenteId() {
		return fuenteId;
	}

	public OfertaEmpleoVO getOferta() {
		return oferta;
	}

    public void setOferta(OfertaEmpleoVO oferta) {
        this.oferta = oferta;
    }

	public void setMedioContacto(String medioContacto) {
		this.medioContacto = medioContacto;
	}

	/**
	 * @return the idContactoCorreo
	 */
	public int getIdContactoCorreo() {
		return IdContactoCorreo;
	}

	/**
	 * @param idContactoCorreo the idContactoCorreo to set
	 */
	public void setIdContactoCorreo(int idContactoCorreo) {
		IdContactoCorreo = idContactoCorreo;
	}

	/**
	 * @return the idContactoDomicilio
	 */
	public int getIdContactoDomicilio() {
		return IdContactoDomicilio;
	}

	/**
	 * @param idContactoDomicilio the idContactoDomicilio to set
	 */
	public void setIdContactoDomicilio(int idContactoDomicilio) {
		IdContactoDomicilio = idContactoDomicilio;
	}

	/**
	 * @return the idContactoTel
	 */
	public int getIdContactoTel() {
		return IdContactoTel;
	}

	/**
	 * @param idContactoTel the idContactoTel to set
	 */
	public void setIdContactoTel(int idContactoTel) {
		IdContactoTel = idContactoTel;
	}

	/**
	 * @return the telefonos
	 */
	public List<TelefonoVO> getTelefonos() {
		return telefonos;
	}

	/**
	 * @param telefonos the telefonos to set
	 */
	public void setTelefonos(List<TelefonoVO> telefonos) {
		this.telefonos = telefonos;
	}

	public String getDescripcionesDiscapacidades() {
		return descripcionesDiscapacidades;
	}

	public void setDescripcionesDiscapacidades(String descripcionesDiscapacidades) {
		this.descripcionesDiscapacidades = descripcionesDiscapacidades;
	}
	
	
	
	
	private int differenciaFecha; //dato par saber si tiene mas o menos de los diasDifferencia enviado al query


	public int getDifferenciaFecha() {
		return differenciaFecha;
	}

	public void setDifferenciaFecha(int differenciaFecha) {
		this.differenciaFecha = differenciaFecha;
	}
	
	
	//Fin Cambio Movil
	
	public ConocimientoComputacionVO getConocimientoComputacion() {
		return conocimientoComputacion;
	}

	public void setConocimientoComputacion(
			ConocimientoComputacionVO conocimientoComputacion) {
		this.conocimientoComputacion = conocimientoComputacion;
	}

	public String getConocimientoComputacionDesc() {
		StringBuilder description = new StringBuilder();
		if (null == conocimientoComputacion) return "No es requisito";
		if (conocimientoComputacion.getProcesadorTxt() > 0) description.append(", Procesador de textos");
		if (conocimientoComputacion.getHojaCalculo() > 0) description.append(", Hojas de cálculo");
		if (conocimientoComputacion.getInternet() > 0) description.append(", Internet y correo electrónico");
		if (conocimientoComputacion.getRedesSociales() > 0) description.append(", Redes sociales");
		if (description.length() > 2) description.delete(0, 2).append(".");
		if (null != conocimientoComputacion.getOtros() && !conocimientoComputacion.getOtros().isEmpty()) description.append("<br>Otros: " + conocimientoComputacion.getOtros());
		return description.toString();
	}

	public void setConocimientoComputacionDesc(String conocimientoComputacionDesc) {
		this.conocimientoComputacionDesc = conocimientoComputacionDesc;
	}

	public String getCargoContacto() {
		return cargoContacto;
	}

	public void setCargoContacto(String cargoContacto) {
		this.cargoContacto = cargoContacto;
	}

	public DomicilioVO getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(DomicilioVO domicilio) {
		this.domicilio = domicilio;
	}

	public String getCorreoElectronicoContacto() {
		return correoElectronicoContacto;
	}

	public void setCorreoElectronicoContacto(String correoElectronicoContacto) {
		this.correoElectronicoContacto = correoElectronicoContacto;
	}

	public String getTelefonoOferta() {
		return telefonoOferta;
	}

	public void setTelefonoOferta(String telefonoOferta) {
		this.telefonoOferta = telefonoOferta;
	}

	public String getContactoDomicilio() {
		return contactoDomicilio;
	}

	public void setContactoDomicilio(int contactoDomicilio) {
		this.contactoDomicilio = "" + contactoDomicilio;
	}

	public HashMap<String, String> getUbicaciones() {
		return ubicaciones;
	}

	public void setUbicaciones(HashMap<String, String> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}
}
