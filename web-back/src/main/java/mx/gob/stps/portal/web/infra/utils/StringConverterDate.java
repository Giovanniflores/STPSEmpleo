/**
 * 
 */
package mx.gob.stps.portal.web.infra.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.StringConverter;

/**
 * @author Felipe
 *
 */
public class StringConverterDate implements Converter {
	
	private static final StringConverter stringConverter =
        new StringConverter();
    private String formatPattern = null;

    public void setFormatPattern(String formatPattern) {
        this.formatPattern = formatPattern;
    }
    
    /* (non-Javadoc)
	 * @see org.apache.commons.beanutils.Converter#convert(java.lang.Class, java.lang.Object)
	 */
    @SuppressWarnings("rawtypes")
	@Override
    public Object convert(Class type, Object value) {
    	//logger.info("StringConverter.convert");
        Object returnValue = null;

        if (value != null) {
            if (type == String.class && (value instanceof Date)) {
                SimpleDateFormat formatter =
                    new SimpleDateFormat(formatPattern);
                String dateString = formatter.format(value);
                returnValue = dateString;
            } else {
                returnValue = stringConverter.convert(type, value);
            }
        }
        return returnValue;
    }

}
