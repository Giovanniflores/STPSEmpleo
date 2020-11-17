package mx.gob.stps.portal.core.ws.ofertas.superchamba;

import mx.gob.stps.portal.utils.PropertiesLoader;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class SuperChamba {

    private static final PropertiesLoader properties = PropertiesLoader.getInstance();
    private static final Map<Integer, Integer> catalogoFechas = new HashMap<Integer, Integer>();

    static {
        catalogoFechas.put(1, 1); // Hoy
        catalogoFechas.put(3, 2); // Ultimos 3 dias
        catalogoFechas.put(7, 3); // Ultimos 7 dias
        catalogoFechas.put(30, 4); // Ultimos 30 dias
        catalogoFechas.put(60, 5); // Ultimos 60 dias
    }

    public static void main(String... args) throws Exception {
        String result = SuperChamba.findVacancies("Contador", 9, 60);
        System.out.println(result);
    }

    public static String findVacancies(String keyword, int federalEntityId, int publishedId) throws Exception {

        String trustStore = properties.getProperty("curpServices.keyStore");
        String trustStorePassword = properties.getProperty("curpServices.keyStore.password");
        System.setProperty("javax.net.ssl.trustStore", trustStore);
        System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);

        BigInteger federalEntityIdBI = BigInteger.valueOf(federalEntityId);
        BigInteger publishedIdBI = BigInteger.valueOf(SuperChamba.catalogoFechas.get(publishedId));

        VacanciesServiceLocator locator = new VacanciesServiceLocator();
        VacanciesServicePortType service = locator.getvacanciesServicePort();
        return service.vacanciesFindVacancies(keyword, federalEntityIdBI, publishedIdBI);
    }
}
