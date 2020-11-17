package mx.gob.stps.portal.web.infra.utils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoOutVO;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.infra.vo.ItemVO;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.util.*;

public class HispavistaRestClient {

    private final static String API_KEY = "5548066f707b99a027118489a8e943aa";
    public final static int ID_COUNTRY_MEXICO = 160;

    private static Logger logger = Logger.getLogger(HispavistaRestClient.class);

    public static void main(String... args) {

//        requestGetCountries();
//        requestGetCountryStates(160);
//        requestGetAreas();
//        requestGetAreaProfessions(2);

//        String input = "{\"search\": \"secretaria\", \"idcountry\": \"160\", \"idstate\": \"1745\", \"idarea\" : \"1\", \"idprofession\" : \"470\"}";
        String search = StringEscapeUtils.escapeHtml("Programador Web");
        requestPostSearchJobOffers(search, ID_COUNTRY_MEXICO, 1745, 1, 10);
//        requestGetJobOffer(1194239366);

//        String command = "curl -H \"apikey:5548066f707b99a027118489a8e943aa\" -H \"Content-Type:application/json\" -X GET \"https://api.trabajos.com/1.0/countries/160/states\" --insecure";
//        StringBuffer output = new StringBuffer();
//        Process p;
//        try {
//            p = Runtime.getRuntime().exec(command);
//            p.waitFor();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
//
//            String line = "";
//            while ((line = reader.readLine())!= null) {
//                output.append(line + "\n");
//            }
//
//            System.out.println(output.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        Allocates a Date object and initializes it to represent the specified number of milliseconds since
//        the standard base time known as "the epoch", namely January 1, 1970, 00:00:00 GMT.
        long unixTimestamp = Long.parseLong("1446584334");
        Date date = new Date((long)1446584334 * 1000); // Multiply by 1000, since java is expecting milliseconds:
        System.out.println(date);
    }

    public static CatalogoVO requestGetCountries() {
        logger.info("****** Countries ********");

//        try {
//            doTrustToCertificates();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        String fURL = "https://api.trabajos.com/1.0/countries";

        try {

//            URL url = new URL("https://api.trabajos.com/1.0/countries");
//            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            // NOTE: Invocations in order to work with WeyLogic
//            URL url = new URL(null, fURL, new sun.net.www.protocol.https.Handler());
//            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Content-Type", "application/json");
//            conn.setRequestProperty("apikey", API_KEY);
//            int status = conn.getResponseCode();

            Client c = Client.create();
            WebResource resource = c.resource(fURL);
            WebResource.Builder builder = resource.header("apikey", API_KEY).header("Content-Type", "application/json");
            ClientResponse clientResponse = builder.get(ClientResponse.class);
            int status = clientResponse.getStatus();
            String response = clientResponse.getEntity(String.class);

            if (status != HttpURLConnection.HTTP_OK ) {
                throw new RuntimeException("Failed : HTTP error code : " + status);
            }

            logger.info(response);
            JSONObject jsonObj = new JSONObject(response);
//            logger.info(jsonObj.toString(4));
            CatalogoVO catalogoVO = buildCatalogoVO(jsonObj, "countries", "country");

//            conn.disconnect();
            clientResponse.close();

            return catalogoVO;

//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static CatalogoVO requestGetCountryStates(final int idCountry) {
        logger.info(String.format("****** Country[%d]/States ********", idCountry));

//        try {
//            doTrustToCertificates();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        String fURL = String.format("https://api.trabajos.com/1.0/countries/%d/states", idCountry);

        try {

//            URL url = new URL(String.format("https://api.trabajos.com/1.0/countries/%d/states", idCountry));
//            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            // NOTE: Invocations in order to work with WeyLogic
//            String fURL = String.format("https://208.51.7.77/1.0/countries/%d/states", idCountry);
//            URL url = new URL(null, fURL, new sun.net.www.protocol.https.Handler());
//            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Content-Type", "application/json");
//            conn.setRequestProperty("apikey", API_KEY);
//            int status = conn.getResponseCode();
//            String response = readResponse(conn);

            Client client = Client.create();
            WebResource resource = client.resource(fURL);
            WebResource.Builder builder = resource.header("apikey", API_KEY).header("Content-Type", "application/json");
            ClientResponse clientResponse = builder.get(ClientResponse.class);
            int status = clientResponse.getStatus();
            String response = clientResponse.getEntity(String.class);

            if (status != HttpURLConnection.HTTP_OK ) {
                throw new RuntimeException("Failed : HTTP error code : " + status);
            }

//            String command = "curl -H \"apikey:5548066f707b99a027118489a8e943aa\" -H \"Content-Type:application/json\" -X GET \"https://api.trabajos.com/1.0/countries/160/states\" --insecure";
//            StringBuffer output = new StringBuffer();
//            Process p;
//            try {
//                p = Runtime.getRuntime().exec(command);
//                p.waitFor();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
//
//                String line = "";
//                while ((line = reader.readLine())!= null) {
//                    output.append(line + "\n");
//                }
////                System.out.println(output.toString());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            String response = output.toString();

            logger.info(response);
            JSONObject jsonObj = new JSONObject(response);
//            logger.info(jsonObj.toString(4));
            CatalogoVO catalogoVO = buildCatalogoVO(jsonObj, "states", "state");

//            conn.disconnect();
//            clientResponse.close();

            return catalogoVO;

//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
        } catch (JSONException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println("Error in building and sending get requests: " + e);
        }

        return null;
    }

    public static CatalogoVO requestGetAreas() {
        logger.info("****** Areas ********");

//        try {
//            doTrustToCertificates();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        String fURL = "https://api.trabajos.com/1.0/areas";

        try {

//            URL url = new URL("https://api.trabajos.com/1.0/areas");
//            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            // NOTE: Invocations in order to work with WeyLogic
//            URL url = new URL(null, fURL, new sun.net.www.protocol.https.Handler());
//            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Content-Type", "application/json");
//            conn.setRequestProperty("apikey", API_KEY);
//            int status = conn.getResponseCode();
//            String response = readResponse(conn);

            Client c = Client.create();
            WebResource resource = c.resource(fURL);
            WebResource.Builder builder = resource.header("apikey", API_KEY).header("Content-Type", "application/json");
            ClientResponse clientResponse = builder.get(ClientResponse.class);
            int status = clientResponse.getStatus();
            String response = clientResponse.getEntity(String.class);

            if (status != HttpURLConnection.HTTP_OK ) {
                throw new RuntimeException("Failed : HTTP error code : " + status);
            }

            logger.info(response);
            JSONObject jsonObj = new JSONObject(response);
//            logger.info(jsonObj.toString(4));
            CatalogoVO catalogoVO = buildCatalogoVO(jsonObj, "areas", "area");

//            conn.disconnect();
            clientResponse.close();

            return catalogoVO;

//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
        } catch (JSONException e) {
            logger.info(e);
        } catch (Exception e) {
            logger.info(e);
        }

        return null;
    }

    public static CatalogoVO requestGetAreaProfessions(final int idArea) {
        logger.info(String.format("****** Area[%d]/Professions ********", idArea));

//        try {
//            doTrustToCertificates();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        String fURL = String.format("https://api.trabajos.com/1.0/areas/%d/professions", idArea);

        try {

//            URL url = new URL(String.format("https://api.trabajos.com/1.0/areas/%d/professions", idArea));
//            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            // NOTE: Invocations in order to work with WeyLogic
//            URL url = new URL(null, fURL, new sun.net.www.protocol.https.Handler());
//            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Content-Type", "application/json");
//            conn.setRequestProperty("apikey", API_KEY);
//            int status = conn.getResponseCode();
//            String response = readResponse(conn);

            Client c = Client.create();
            WebResource resource = c.resource(fURL);
            WebResource.Builder builder = resource.header("apikey", API_KEY).header("Content-Type", "application/json");
            ClientResponse clientResponse = builder.get(ClientResponse.class);
            int status = clientResponse.getStatus();
            String response = clientResponse.getEntity(String.class);

            if (status != HttpURLConnection.HTTP_OK ) {
                throw new RuntimeException("Failed : HTTP error code : " + status);
            }

            logger.info(response);
            JSONObject jsonObj = new JSONObject(response);
//            logger.info(jsonObj.toString(4));
            CatalogoVO catalogoVO = buildCatalogoVO(jsonObj, "professions", "profession");

//            conn.disconnect();
            clientResponse.close();

            return catalogoVO;

//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
        } catch (JSONException e) {
            logger.info(e);
        } catch (Exception e) {
            logger.info(e);
        }

        return null;
    }

    // TODO: DELETE THIS METHOD
    public static List<OfertaEmpleoOutVO> requestPostSearchJobOffers(final String search, final int idCountry, final int idState, final int idArea, final int idProfession, final int offset, final int num) {
        logger.info("****** Search JobOffer ********");

//        try {
//            doTrustToCertificates();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        String fURL = String.format("https://api.trabajos.com/1.0/joboffers/search?offset=%d&num=%d", offset, num);
        String fURL = String.format("https://api.trabajos.com/1.0/joboffers/search");

        try {

//            URL url = new URL(String.format("https://api.trabajos.com/1.0/joboffers/search?%d=1&%d=10", offset, num));
//            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            // NOTE: Invocations in order to work with WayLogic
//            URL url = new URL(null, fURL, new sun.net.www.protocol.https.Handler());
//            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
//            conn.setDoOutput(true);
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Type", "application/json");
//            conn.setRequestProperty("apikey", API_KEY);
//            int status = conn.getResponseCode();

//            String input = "{\"search\": \"secretaria\", \"idcountry\": \"160\", \"idstate\": \"1745\", \"idarea\" : \"1\", \"idprofession\" : \"470\"}";
            StringBuilder input = new StringBuilder();
            input.append("{");
            input.append("\"search\"").append(":").append("\"").append(StringEscapeUtils.escapeHtml(search)).append("\"").append(", ");
            input.append("\"idcountry\"").append(":").append("\"").append(idCountry).append("\"").append(", ");
            input.append("\"idstate\"").append(":").append("\"").append(idState).append("\"").append(", ");
            input.append("\"idarea\"").append(":").append("\"").append(idArea).append("\"").append(", ");
            input.append("\"idprofession\"").append(":").append("\"").append(idProfession).append("\"");
            input.append("}");

//            OutputStream os = conn.getOutputStream();
//            os.write(input.toString().getBytes());
//            os.flush();

//            String response = readResponse(conn);

            Client c = Client.create();
            WebResource resource = c.resource(fURL);
            WebResource.Builder builder = resource.header("apikey", API_KEY).header("Content-Type", "application/json").entity(input.toString(), MediaType.APPLICATION_JSON_TYPE);
            ClientResponse clientResponse = builder.post(ClientResponse.class);
            int status = clientResponse.getStatus();
            String response = clientResponse.getEntity(String.class);

//            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED ) {
            if (status != HttpURLConnection.HTTP_OK ) {
                throw new RuntimeException("Failed : HTTP error code : " + status);
            }

            //dump all the content
//            print_content(conn);

//            logger.info(response);
            JSONObject jsonObj = new JSONObject(response);
            logger.info(jsonObj.toString(4));
            List<OfertaEmpleoOutVO> listOfertaEmpleoOutVO = buildListOfertaEmpleoOutVO(jsonObj);

//            conn.disconnect();
            clientResponse.close();

            return listOfertaEmpleoOutVO;

//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
        } catch (JSONException e) {
            logger.info(e);
        } catch (Error e) {
            logger.info(e);
        }

        return null;
    }

    public static List<OfertaEmpleoOutVO> requestPostSearchJobOffers(final String search, final int idCountry, final int idState, final int offset, final int num) {
        logger.info("****** Search JobOffer ********");

        String fURL = String.format("https://api.trabajos.com/1.0/joboffers/search");

        try {
            StringBuilder input = new StringBuilder();
            input.append("{");
            input.append("\"search\"").append(":").append("\"").append(StringEscapeUtils.escapeHtml(search)).append("\"").append(", ");
            input.append("\"idcountry\"").append(":").append("\"").append(idCountry).append("\"").append(", ");
            input.append("\"idstate\"").append(":").append("\"").append(idState).append("\"");
            input.append("}");

            Client c = Client.create();
            WebResource resource = c.resource(fURL);
            WebResource.Builder builder = resource.header("apikey", API_KEY).header("Content-Type", "application/json").entity(input.toString(), MediaType.APPLICATION_JSON_TYPE);
            ClientResponse clientResponse = builder.post(ClientResponse.class);
            int status = clientResponse.getStatus();
            String response = clientResponse.getEntity(String.class);

            if (status != HttpURLConnection.HTTP_OK ) {
                throw new RuntimeException("Failed : HTTP error code : " + status);
            }

//            logger.info(response);
            JSONObject jsonObj = new JSONObject(response);
            logger.info(jsonObj.toString(4));
            List<OfertaEmpleoOutVO> listOfertaEmpleoOutVO = buildListOfertaEmpleoOutVO(jsonObj);

            clientResponse.close();

            return listOfertaEmpleoOutVO;

        } catch (JSONException e) {
            logger.info(e);
        } catch (Error e) {
            logger.info(e);
        }

        return null;
    }

    public static void requestGetJobOffer(final int idJobOffer) {
        logger.info("****** JobOffer ********");

        try {

//            URL url = new URL(String.format("https://api.trabajos.com/1.0/joboffers/%d", idJobOffer));
//            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            // NOTE: Invocations in order to work with WeyLogic
            String fURL = String.format("https://api.trabajos.com/1.0/joboffers/%d", idJobOffer);
            URL url = new URL(null, fURL, new sun.net.www.protocol.https.Handler());
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("apikey", API_KEY);

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK ) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }



            //dump all the content
//            print_content(conn);

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public static void print_https_cert(HttpsURLConnection con){

        if(con!=null){

            try {

                System.out.println("Response Code : " + con.getResponseCode());
                System.out.println("Cipher Suite : " + con.getCipherSuite());
                System.out.println("\n");

                Certificate[] certs = con.getServerCertificates();
                for(Certificate cert : certs){
                    System.out.println("Cert Type : " + cert.getType());
                    System.out.println("Cert Hash Code : " + cert.hashCode());
                    System.out.println("Cert Public Key Algorithm : "
                            + cert.getPublicKey().getAlgorithm());
                    System.out.println("Cert Public Key Format : "
                            + cert.getPublicKey().getFormat());
                    System.out.println("\n");
                }

            } catch (SSLPeerUnverifiedException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }

        }

    }

    public static void print_content(HttpsURLConnection con){
        if(con!=null){

            try {

//                System.out.println("****** Content of the URL ********");
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String input;

                while ((input = br.readLine()) != null){
//                    System.out.println(input);
                    JSONObject jsonObj = new JSONObject(input);
                    System.out.println(jsonObj.toString(4));
                }
                br.close();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public static String readResponse(HttpURLConnection con){
        String input = null;

        if (con != null) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                input = br.readLine();
//                while ((String in = br.readLine()) != null){
//                    System.out.println(input);
//                    JSONObject jsonObj = new JSONObject(input);
//                    System.out.println(jsonObj.toString(4));
//                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return input;
    }

    public static String readResponse(HttpsURLConnection con){
        String input = null;

        if (con != null) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                input = br.readLine();
//                while ((String in = br.readLine()) != null){
//                    System.out.println(input);
//                    JSONObject jsonObj = new JSONObject(input);
//                    System.out.println(jsonObj.toString(4));
//                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return input;
    }

//    public static Map<Integer, String> buildJsonMap(final JSONObject jsonObject, final String arrayName, final String itemName) {
//        Map<Integer, String> jsonMap = new LinkedHashMap<Integer, String>();
//
//        JSONArray professions = jsonObject.optJSONArray(arrayName);
//        for (int i = 0; i < professions.length(); i++) {
//            JSONObject json;
//            try {
//                json = professions.getJSONObject(i);
//                String key = String.format("id%s", itemName);
//                String value = String.format("name%s", itemName);
//                jsonMap.put(json.getInt(key), json.getString(value));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return jsonMap;
//    }

    public static CatalogoVO buildCatalogoVO(final JSONObject jsonObject, final String arrayName, final String itemName) {
        List<ItemVO> items = new LinkedList<ItemVO>();
        JSONArray array = jsonObject.optJSONArray(arrayName);
        for (int i = 0; i < array.length(); i++) {
            JSONObject json;
            try {
                json = array.getJSONObject(i);
                String key = String.format("id%s", itemName);   // label & value
                String value = String.format("name%s", itemName); // name
                ItemVO itemVO = new ItemVO(json.getString(value), json.getString(key), json.getString(key));    // name, label, value
                items.add(itemVO);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        CatalogoVO catalogoVO = new CatalogoVO("value", "label");
        catalogoVO.setItems(items);

        return catalogoVO;
    }

    public static List<OfertaEmpleoOutVO> buildListOfertaEmpleoOutVO(final JSONObject jsonObject) {
        List<OfertaEmpleoOutVO> list = new LinkedList<OfertaEmpleoOutVO>();

        JSONArray array = jsonObject.optJSONArray("joboffers");
        for (int i = 0; i < array.length(); i++) {
            JSONObject json;
            try {
                json = array.getJSONObject(i);

                String timestamp = json.getString("date");

                // Allocates a Date object and initializes it to represent the specified number of milliseconds since
                // the standard base time known as "the epoch", namely January 1, 1970, 00:00:00 GMT.
                long unixTimestamp = Long.parseLong(timestamp);
                Date date = new Date(unixTimestamp * 1000); // Multiply by 1000, since java is expecting milliseconds:

                String fecha = Utils.formatDDMMYYYY(date);
                String puesto = json.getString("jobtitle");
                String estado = (String) json.getJSONObject("state").get("namestate");
                String empresa = json.getString("companyname");
                long idOferta = json.getLong("idjoboffer");
                String url = String.format("http://www.trabajos.mx/bolsa-trabajo/%d", idOferta);
                String descripcion = json.getString("mainrequirements");
                String ciudad = json.getString("town");
                String[] imagen = null;
                String idDiscapacidad = null;
                String discapacidad = null;
                String idEmpresa = null;
                idOferta = 0;   // Changed to zero so that view can display jobOffer detail URL

                OfertaEmpleoOutVO ofertaEmpleoOutVO = new OfertaEmpleoOutVO();
                ofertaEmpleoOutVO.setFecha(fecha);
                ofertaEmpleoOutVO.setPuesto(puesto);
                ofertaEmpleoOutVO.setEstado(estado);
                ofertaEmpleoOutVO.setEmpresa(empresa);
                ofertaEmpleoOutVO.setUrl(url);
                ofertaEmpleoOutVO.setIdOferta(idOferta);
                ofertaEmpleoOutVO.setDescripcion(descripcion);
                ofertaEmpleoOutVO.setCiudad(ciudad);
                ofertaEmpleoOutVO.setImagen(imagen);
                ofertaEmpleoOutVO.setIdDiscapacidad(idDiscapacidad);
                ofertaEmpleoOutVO.setDiscapacidad(discapacidad);
                ofertaEmpleoOutVO.setIdEmpresa(idEmpresa);

                list.add(ofertaEmpleoOutVO);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    // trusting all certificate
//    private static void doTrustToCertificates() throws Exception {
//        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
//        TrustManager[] trustAllCerts = new TrustManager[]{
//                new X509TrustManager() {
//                    public X509Certificate[] getAcceptedIssuers() {
//                        return null;
//                    }
//
//                    public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
//                        return;
//                    }
//
//                    public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
//                        return;
//                    }
//                }
//        };
//
//        SSLContext sc = SSLContext.getInstance("SSL");
//        sc.init(null, trustAllCerts, new SecureRandom());
//        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//        HostnameVerifier hv = new HostnameVerifier() {
//            public boolean verify(String urlHostName, SSLSession session) {
//                if (!urlHostName.equalsIgnoreCase(session.getPeerHost())) {
//                    System.out.println("Warning: URL host '" + urlHostName + "' is different to SSLSession host '" + session.getPeerHost() + "'.");
//                }
//                return true;
//            }
//        };
//        HttpsURLConnection.setDefaultHostnameVerifier(hv);
//    }
}

