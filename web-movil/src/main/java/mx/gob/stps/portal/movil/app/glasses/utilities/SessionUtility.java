package mx.gob.stps.portal.movil.app.glasses.utilities;



import mx.gob.stps.portal.movil.app.glasses.cookies.CookieSession;
import mx.gob.stps.portal.movil.app.glasses.cookies.CookieUtils;
import mx.gob.stps.portal.movil.app.model.Session;
import mx.gob.stps.portal.movil.app.session.SessionService;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class contains helper methods intended to be used by Sessions
 */
public class SessionUtility {

    private transient final static String SECRET_WORD = "NASTYLY EX RAT REDO WELL";

    private static Logger logger = Logger.getLogger(SessionUtility.class);

    /**
     * A new token string generated with the same hash method, salt and username
     * should always generate the provided token.
     */
    public static boolean tokenMatch(String token, String salt, String username, String role) throws Exception {
        String tmpToken = makeToken(salt, username, role);
        return tmpToken.equals(token);
    }

    /**
     * Generates a hash salt
     * @param secretKey - a String obj. containing a secret key
     * @param username - a String obj. containing a username
     * @return - a hash salt string
     */
    public static String makeSalt(String secretKey, String username) throws Exception {
        String salt;
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(secretKey).append('+');
            sb.append(SECRET_WORD).append('+');
            sb.append(new DateTime()).append('+');
            sb.append(username);
            salt = Codec.hexMD5(String.format("Use %s to make salt", sb.toString()));
        } finally {
            lock.unlock();
        }
        return salt;
    }

    /**
     * Generates a hash token
     * @param salt - a String obj. containing a salt
     * @param username a a String obj. containing username
     * @return - a hash token string
     */
    public static String makeToken(String salt, String username, String deviceId) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(salt).append('+').append(username).append('+').append(deviceId);
        return Codec.hexSHA1(String.format("Put %s on the %s", salt, sb));
    }

    /**
     * Validates if two dates has a x-offset time
     * @param lastRequestDate - Date of last request expressed in milliseconds
     * @param currentRequestDate - Date of current request expressed in milliseconds
     * @param deltaTime - Delta time expressed in milliseconds
     * @return - a boolean value (true if it hasn't expired otherwise false)
     */
    public static boolean hasExpired(long lastRequestDate, long currentRequestDate, int deltaTime) {
        DateTime lastReqDate = new DateTime(lastRequestDate);
        DateTime currentReqDate = new DateTime(currentRequestDate);
        return currentReqDate.minusMillis(deltaTime).getMillis() > lastReqDate.getMillis();
    }

    public static long currentRequestDateMinusDelta(int deltaTime) {
        DateTime currentDate = new DateTime(new Date().getTime());
        long curDateMinusDelta = currentDate.minusMillis(deltaTime).getMillis();
        logger.info(String.format("currenteDate: %s", currentDate));
        logger.info(String.format("currenteDate minus deltaTime: %s", new Date(curDateMinusDelta)));
        return curDateMinusDelta;
    }

    public static boolean isAuthenticated(SessionService sessionService, HttpServletRequest request, HttpServletResponse response) {

        // JUST_LOGGED_IN???

        // 1. There is a session cookie
        Cookie cookie = CookieUtils.getCookieByName(request, CookieSession.NAME);
        if (cookie != null) {
            // 2. Split values
            Map.Entry<String,String> usernameAndRole = getUsernameAndRoleFromCookieSession(cookie);
            String username;
            String role;
            if (usernameAndRole != null) {
                username = usernameAndRole.getKey();
                role = usernameAndRole.getValue();
            } else {
                return false;
            }

            // 3. There is a session data in DB
            Session session;
            try {
                session = sessionService.findByUsername(username);
            } catch (Exception e) {
                // There's no session in DB
                CookieUtils.deleteCookie(request, response, CookieSession.NAME);
                return false;
            } /*catch (SQLException e) {
                logger.error(e.getMessage());
                return false;
            }*/

            // 3. Recreate token
            assert session != null;
            String salt = session.getSalt();
            String token = session.getToken();
            boolean tokenMatched = false;
            try {
                tokenMatched = SessionUtility.tokenMatch(token,salt,username,role);
            } catch (Exception e) {
                // Do Nothing...
            }
            if (!tokenMatched) {
                CookieUtils.deleteCookie(request, response, CookieSession.NAME);
                /*try {*/
                    sessionService.delete(session);
                /*} catch (SQLException e) {
                    logger.error(e.getMessage());
                }*/
                return false;
            }

            return true;
        }

        return false;
    }

    public static Map.Entry<String,String> getUsernameAndRoleFromCookieSession(Cookie cookie) {
        // Catch the NullPointerException when session has expired from DB and user invokes logout
        String value = cookie.getValue();
        if (value == null) {
            return null;
        }

        String username = value.substring(0, value.indexOf(CookieSession.DELIMITER_TWO));
        String role = value.substring(value.indexOf(CookieSession.DELIMITER_TWO) + 1, value.indexOf(CookieSession.DELIMITER_ONE));

        return new HashMap.SimpleEntry<String,String>(username, role);
    }

    //    public static void renewCookieSession(HttpServletRequest request, HttpServletResponse response, int maxAge) {
    public static void renewCookieSession(HttpServletRequest request, HttpServletResponse response) {
        int untilBrowserIsAlive = -1;
        Cookie cookie = CookieUtils.getCookieByName(request, CookieSession.NAME);
        if (cookie != null) {
            String cookieValue = cookie.getValue();
            if (cookieValue != null) {
//                CookieUtils.writeCookie(request, response, CookieSession.NAME, cookieValue, CookieSession.MAX_AGE);
//                CookieUtils.writeCookie(request, response, CookieSession.NAME, cookieValue, maxAge);
                CookieUtils.writeCookie(request, response, CookieSession.NAME, cookieValue, untilBrowserIsAlive);
            }
        }
    }

//    /**
//     * Gets from DB a BinData obj. standing for a serialized MenuStructure obj. and deserializes it.
//     * @return - a MenuStructure obj.
//     * @throws UnfoundException -
//     */
//    private SessionData modifySessionData(SessionData sessionData) throws UnfoundException {
//        MenuStructure menuStructure = null;
//        if (binData != null) {
//            byte[] menuStructureBytes = binData.getBin();    // Get binary data from DB
//            menuStructure = MenuUtility.deserializeMenuStructure(menuStructureBytes);    // Deserialize menuStructure
//        }
//        return menuStructure;
//    }

//    public static void putCsrfTokenIntoSessionData(SessionData sessionData, String key, Object value) {
//        sessionData.put(key, value);
//    }

    public static Session retrieveSessionFromDB(HttpServletRequest request, SessionService sessionService) {

        // 0. Get username from cookie
        Cookie cookie = CookieUtils.getCookieByName(request, CookieSession.NAME);
        Map.Entry<String,String> usernameAndRole = SessionUtility.getUsernameAndRoleFromCookieSession(cookie);
        String username = null;
        if (usernameAndRole != null) {
            username = usernameAndRole.getKey();
            if (username == null) {
                return null;   // something went wrong...
            }
        }

        // 1. Get session from DB
        Session session;
        try {
            session = sessionService.findByUsername(username); // Find session data in DB
        } catch (Exception e) {
            return null; // session is no longer in db
        } /*catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }*/

        return session;
    }
}
