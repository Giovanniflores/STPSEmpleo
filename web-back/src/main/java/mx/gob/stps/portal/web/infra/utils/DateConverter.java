package mx.gob.stps.portal.web.infra.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

public class DateConverter implements Converter {
	
	private String formatPattern = null;

    public void setFormatPattern(String formatPattern) {
        this.formatPattern = formatPattern;
    }

    @SuppressWarnings("rawtypes")
	public Object convert(Class type, Object value) {
        Date date = null;
        if (value != null
            && (value instanceof String)
            && (type == Date.class)) {
            try {

                String s = value.toString();
                if (!s.trim().equals("")) {//Valida cadena vacía
	                SimpleDateFormat formatter =
	                    new SimpleDateFormat(formatPattern);
	                date = formatter.parse(s);
                }
            } catch (Exception e) {
            	e.printStackTrace();
            	throw new ConversionException("La fecha no tiene el formato " 
            			+ Constantes.formatDate);
            }
        }
        return date;
    }
    
	/*@Override
	public Object convert(Class type, Object value) throws ConversionException {
		logger.info("Converter Date");
		if (value == null) {
			return (value);
        }
		
        if (value instanceof Date) {
        	logger.info("Objeto fecha");
        	Date date = (Date) value;
        	SimpleDateFormat sdf = new SimpleDateFormat(Constantes.formatDate);
        	return sdf.format(date);
        }
		
		if (value instanceof String) {
			logger.info("Objeto String");
			String string = (String) value;
			if (!string.trim().equals("")) {
				SimpleDateFormat sdf = new SimpleDateFormat(Constantes.formatDateForma);
				try {
					return sdf.parse(string);
				} catch (ParseException e) {
					e.printStackTrace();
					throw new ConversionException("La fecha no tiene formato " + Constantes.formatDateForma);
				}
			} else {
				Date date = null;
				return date;
			}
		}
		return value;
	}*/

}
