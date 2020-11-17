package mx.gob.stps.portal.movil.app.service.util;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import mx.gob.stps.portal.exception.BusinessException;
import  mx.gob.stps.portal.ws.captcha.CaptchaPreguntasWSServiceStub;
import mx.gob.stps.portal.persistencia.vo.CaptchaPreguntasVO;
import mx.gob.stps.portal.persistencia.vo.CaptchaRespuestasVO;
import java.util.ArrayList;
import org.apache.log4j.Logger;

@Stateless
@LocalBean
public class CaptchaPreguntasWSService implements CaptchaPreguntasWSServiceLocal{

	private static final Logger logger = Logger.getLogger(CaptchaPreguntasWSService.class);

	/*
	 * (non-Javadoc)
	 * @see mx.gob.stps.portal.movil.app.service.util.CaptchaPreguntasWSServiceLocal#obtieneDesafioCaptcha()
	 * obtiene el captcha para el móvil desde el servicio de captcha
	 */
	public CaptchaPreguntasVO obtieneDesafioCaptcha() throws BusinessException{

		try{			
			//trazaSrojas("obtieneDesafioCaptcha1");			
			CaptchaPreguntasWSServiceStub.ObtienePregunta args = new CaptchaPreguntasWSServiceStub.ObtienePregunta();
			
			//trazaSrojas("obtieneDesafioCaptcha2");			
			CaptchaPreguntasWSServiceStub.ObtienePreguntaE params = new CaptchaPreguntasWSServiceStub.ObtienePreguntaE();
			params.setObtienePregunta(args);
			
			//trazaSrojas("obtieneDesafioCaptcha3");			
			CaptchaPreguntasWSServiceStub stub = new CaptchaPreguntasWSServiceStub();
			CaptchaPreguntasWSServiceStub.ObtienePreguntaResponseE response = stub.obtienePregunta(params);
			
			//trazaSrojas("obtieneDesafioCaptcha4");			
			CaptchaPreguntasWSServiceStub.CaptchaPreguntasVO pregunta = response.getObtienePreguntaResponse().get_return();			
			
			//trazaSrojas("obtieneDesafioCaptcha5");
			CaptchaPreguntasVO result = null;		
			if (pregunta != null){
				
				result = new CaptchaPreguntasVO();
				
				result.setIdPregunta(pregunta.getIdPregunta());
				result.setPregunta(pregunta.getPregunta());
				
				result.setCaptchaRespuestas(new ArrayList<CaptchaRespuestasVO>());
				for (int i =0; i < pregunta.getCaptchaRespuestas().length; i++){
					CaptchaRespuestasVO r = new CaptchaRespuestasVO();
					r.setIdRespuesta(pregunta.getCaptchaRespuestas()[i].getIdRespuesta());
					r.setRespuesta(pregunta.getCaptchaRespuestas()[i].getRespuesta());
					result.getCaptchaRespuestas().add(r);
				}
			}
			
			//trazaSrojas("obtieneDesafioCaptcha6");
			return result;
		} catch (Exception e){
			logger.error("RegistroCandidatoCurp.obtieneDesafioCaptcha: ha ocurrido un error");
			//e.printStackTrace();
			throw new BusinessException(e);
		}		
		
	}

	private void trazaSrojas(String message){
		if (message != null && !message.isEmpty())
			System.out.println("<srojas> "+this.getClass().getName()+": "+message);
	}
	
	
}


