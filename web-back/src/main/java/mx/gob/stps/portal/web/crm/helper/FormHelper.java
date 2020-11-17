package mx.gob.stps.portal.web.crm.helper;

import mx.gob.stps.portal.web.crm.form.BaseMenuForm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by benjamin.vander on 05/01/2016.
 */
public class FormHelper {

    public static void addMessage(BaseMenuForm form, String message){
        if(!form.getErrorMessage().isEmpty()){
            form.setErrorMessage(form.getErrorMessage() + "\\n" + message);
        }
        else {
            form.setErrorMessage(message);
        }
    }

    public static int getLength(String str) {
        if(str != null){
            return str.length();
        }
        return 0;
    }

    public static void validString(int min, int max, String campo, String nombreCampo,BaseMenuForm form) {
        int length = getLength(campo);
        if (length < min || max > 100) {
            FormHelper.addMessage(form, nombreCampo + "debe ser entre " + min + " y " + max + " caracteres.");
        }
    }

    public static boolean isThisDateValid(String dateToValidate, String dateFromat){

        if(dateToValidate == null){
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
        sdf.setLenient(false);

        try {

            //if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);
            System.out.println(date);

        } catch (ParseException e) {

            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static void validDate(String fecha, BaseMenuForm form, String dateFormat) {

        if(fecha.length() != dateFormat.length() || !isThisDateValid(fecha,dateFormat)){
            addMessage(form,"Se debe capturar una fecha valida.");
        }
    }
}
