package mx.gob.stps.portal.web.social.action;

/**
 * Created by benjamin.vander on 12/01/2016.
 */

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuthService;
import mx.gob.stps.portal.web.social.form.AuthForm;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Contact;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.BirthDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class SocialAuthSuccessAction2 extends Action {

    final Log LOG = LogFactory.getLog(SocialAuthSuccessAction.class);

    String baseUrl = "https://graph.facebook.com/";
    String fbUserToken;
    String pageToken;
    ObjectMapper objectMapper = new ObjectMapper();

    private String getFacebookUserId(Response response)
            throws IOException {
        String responseBody = response.getBody();
        JsonNode jsonNode;
        jsonNode = objectMapper.readTree(responseBody);
        JsonNode idNode = jsonNode.get("id");
        return idNode.asText();
    }

    private Profile getFacebookProfile(Response response) throws IOException {
        Profile profile = new Profile();
        String responseBody = response.getBody();
        JsonNode jsonNode;
        jsonNode = objectMapper.readTree(responseBody);
        JsonNode idNode = jsonNode.get("id"); //birthday,name,email,first_name,last_name,gender,location
        idNode = jsonNode.get("first_name");
        profile.setFirstName(idNode.asText());
        idNode = jsonNode.get("last_name");
        profile.setLastName(idNode.asText());
        idNode = jsonNode.get("name");
        profile.setFullName(idNode.asText());
        profile.setDob(new BirthDate());
        idNode = jsonNode.get("birthday");
        String birthday = idNode.asText();
        Date formattedDate = null;
        Calendar myCal = new GregorianCalendar();
        try {
            if(birthday.length() == 10){
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                formattedDate = formatter.parse(birthday);
                myCal.setTime(formattedDate);

                profile.getDob().setDay(myCal.get(Calendar.DAY_OF_MONTH));
                profile.getDob().setMonth(myCal.get(Calendar.MONTH) + 1);
                profile.getDob().setYear(myCal.get(Calendar.YEAR));
            }else {
                if(birthday.length() == 5){
                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd");
                    formattedDate = formatter.parse(birthday);
                    myCal.setTime(formattedDate);
                    profile.getDob().setDay(myCal.get(Calendar.DAY_OF_MONTH));
                    profile.getDob().setMonth(myCal.get(Calendar.MONTH)+1);
                }else {
                    if (birthday.length() == 4) {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
                        formattedDate = formatter.parse(birthday);
                        profile.getDob().setYear(myCal.get(Calendar.YEAR));

                    }
                }
            }
        } catch (ParseException e) {
            System.out.println(e.getStackTrace());
        }

        idNode =jsonNode.get("gender");
        profile.setGender(idNode.asText());
        idNode = jsonNode.get("location");
        idNode = idNode.get("name");
        profile.setLocation(idNode.asText());
        return profile;
    }

    private Token getAccessToken(OAuthService oAuthService,String code) {
        Verifier verifier = new Verifier(code);
        return oAuthService.getAccessToken(Token.empty(), verifier);
    }

    private Response getResponseForProfile(OAuthService oAuthService,Token accessToken) {
        OAuthRequest oauthRequest =
                new OAuthRequest(Verb.GET,
                        "https://graph.facebook.com/me?fields=id,birthday,name,email,first_name,last_name,gender,hometown,location",oAuthService);
        oAuthService.signRequest(accessToken, oauthRequest);
        return oauthRequest.send();
    }


        @Override
    public ActionForward execute(final ActionMapping mapping,
                                 final ActionForm form, final HttpServletRequest request,
                                 final HttpServletResponse response) throws Exception {

        AuthForm authForm = (AuthForm) form;

        OAuthService service = null;
        Profile profile = null;
        if(authForm.getService() != null){
            service = authForm.getService();
        }
        if(service != null){
            Verifier v = new Verifier(authForm.getCode());
            Token accessToken = service.getAccessToken(null, v); // returns short term token for FB User
            // Exchange the code for an AccessToken and retrieve the profile

            Response resp = getResponseForProfile(service, accessToken);
            if (resp.isSuccessful()) {
                try {
                    String facebookUserId = getFacebookUserId(resp);
                    profile = getFacebookProfile(resp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            authForm.setService(null);
            request.setAttribute("profile", profile);


            return mapping.findForward("success");


        }
        SocialAuthManager manager = null;
        if (authForm.getSocialAuthManager() != null) {
            manager = authForm.getSocialAuthManager();
        }
        if (manager != null) {
            List<Contact> contactsList = new ArrayList<Contact>();
            try {
                Map<String, String> paramsMap = new HashMap<String, String>();
                for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
                    String key = entry.getKey();
                    String values[] = entry.getValue();
                    paramsMap.put(key, values[0].toString()); // Only 1 value is
                }
                AuthProvider provider = manager.connect(paramsMap);

                profile = provider.getUserProfile();
                contactsList = provider.getContactList();
                if (contactsList != null && contactsList.size() > 0) {
                    for (Contact p : contactsList) {
                        if (StringUtils.isEmpty(p.getFirstName())
                                && StringUtils.isEmpty(p.getLastName())) {
                            p.setFirstName(p.getDisplayName());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.setAttribute("profile", profile);
            request.setAttribute("contacts", contactsList);

            return mapping.findForward("success");
        }
        // if provider null
        return mapping.findForward("failure");
    }
}

