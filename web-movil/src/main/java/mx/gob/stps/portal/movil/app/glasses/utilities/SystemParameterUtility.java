package mx.gob.stps.portal.movil.app.glasses.utilities;

import javax.ejb.Local;

import org.apache.log4j.Logger;


@Local
public class SystemParameterUtility {

    private static Logger logger = Logger.getLogger(SystemParameterUtility.class);

    /**
     * Helper method for getting data from DB through a service
     * @return - an integer
     */
    public int getMaxAge() {
        int MAX_AGE = 60 * 15; // Default value
        return MAX_AGE;
    }

    /**
     * Helper method for getting data from DB through a service
     * @return - an String
     */
    public String getSecretKey() {
        String SECRET_KEY = ""; // Default value
        return SECRET_KEY;
    }


}
