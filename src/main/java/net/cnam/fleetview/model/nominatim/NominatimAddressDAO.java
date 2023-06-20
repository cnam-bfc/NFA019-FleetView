package net.cnam.fleetview.model.nominatim;

import com.google.gson.Gson;
import net.cnam.fleetview.App;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NominatimAddressDAO {
    private final String NOMINATIM_URL = "https://nominatim.openstreetmap.org/";
    private final String USER_AGENT = App.APP_NAME + "/" + App.APP_VERSION;
    private final Gson gson = new Gson();

    /**
     * Recherche d'adresses
     *
     * @param searchQuery
     * @return Liste d'adresses possibles
     */
    public List<NominatimAddress> search(String searchQuery) {
        LinkedList<NominatimAddress> addresses = new LinkedList<>();

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            String encodedQuery = URLEncoder.encode(searchQuery, "UTF-8");
            String apiUrl = NOMINATIM_URL + "search?q=" + encodedQuery + "&format=json&addressdetails=1&countrycodes=fr";

            HttpGet request = new HttpGet(apiUrl);
            request.setHeader("User-Agent", USER_AGENT);

            // Exécution de la requête
            HttpClientResponseHandler<String> responseHandler = new HttpClientResponseHandler<String>() {
                @Override
                public String handleResponse(final ClassicHttpResponse response) throws IOException, ParseException {
                    int status = response.getCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new RuntimeException("Unexpected response status: " + status);
                    }
                }
            };
            String responseBody = httpClient.execute(request, responseHandler);

            // Traitement du résultat
            // Transformation du JSON en objets Java primitifs
            Map<String, Object>[] results = gson.fromJson(responseBody, Map[].class);
            // Parcours des résultats
            for (Map<String, Object> result : results) {
                // Création d'un objet NominatimAddress
                NominatimAddress address = new NominatimAddress();
                // Récupération des données
                // OSM_TYPE
                address.setOsmType((String) result.get("osm_type"));
                // OSM_ID
                address.setOsmId(Long.parseLong((String) result.get("osm_id")));
                // ADDRESS
                Map<String, Object> addressDetails = (Map<String, Object>) result.get("address");
                // Pays
                address.setPays((String) addressDetails.get("country"));
                // Code postal
                address.setCodePostal((String) addressDetails.get("postcode"));
                // Commune
                if (addressDetails.get("city") != null) {
                    address.setCommune((String) addressDetails.get("city"));
                } else if (addressDetails.get("town") != null) {
                    address.setCommune((String) addressDetails.get("town"));
                } else if (addressDetails.get("village") != null) {
                    address.setCommune((String) addressDetails.get("village"));
                } else if (addressDetails.get("hamlet") != null) {
                    address.setCommune((String) addressDetails.get("hamlet"));
                }
                // Rue
                if (addressDetails.get("road") != null) {
                    address.setRue((String) addressDetails.get("road"));
                } else if (addressDetails.get("pedestrian") != null) {
                    address.setRue((String) addressDetails.get("pedestrian"));
                } else if (addressDetails.get("footway") != null) {
                    address.setRue((String) addressDetails.get("footway"));
                } else if (addressDetails.get("cycleway") != null) {
                    address.setRue((String) addressDetails.get("cycleway"));
                } else if (addressDetails.get("path") != null) {
                    address.setRue((String) addressDetails.get("path"));
                } else if (addressDetails.get("track") != null) {
                    address.setRue((String) addressDetails.get("track"));
                } else if (addressDetails.get("service") != null) {
                    address.setRue((String) addressDetails.get("service"));
                } else if (addressDetails.get("residential") != null) {
                    address.setRue((String) addressDetails.get("residential"));
                } else if (addressDetails.get("unclassified") != null) {
                    address.setRue((String) addressDetails.get("unclassified"));
                } else if (addressDetails.get("tertiary") != null) {
                    address.setRue((String) addressDetails.get("tertiary"));
                } else if (addressDetails.get("secondary") != null) {
                    address.setRue((String) addressDetails.get("secondary"));
                } else if (addressDetails.get("primary") != null) {
                    address.setRue((String) addressDetails.get("primary"));
                } else if (addressDetails.get("trunk") != null) {
                    address.setRue((String) addressDetails.get("trunk"));
                } else if (addressDetails.get("motorway") != null) {
                    address.setRue((String) addressDetails.get("motorway"));
                }
                // Numéro de rue
                if (addressDetails.get("house_number") != null) {
                    address.setNumeroDeRue((String) addressDetails.get("house_number"));
                }
                // Complément d'adresse
                if (addressDetails.get("suburb") != null) {
                    address.setComplement((String) addressDetails.get("suburb"));
                } else if (addressDetails.get("quarter") != null) {
                    address.setComplement((String) addressDetails.get("quarter"));
                } else if (addressDetails.get("neighbourhood") != null) {
                    address.setComplement((String) addressDetails.get("neighbourhood"));
                } else if (addressDetails.get("city_district") != null) {
                    address.setComplement((String) addressDetails.get("city_district"));
                } else if (addressDetails.get("county") != null) {
                    address.setComplement((String) addressDetails.get("county"));
                } else if (addressDetails.get("state_district") != null) {
                    address.setComplement((String) addressDetails.get("state_district"));
                } else if (addressDetails.get("state") != null) {
                    address.setComplement((String) addressDetails.get("state"));
                }
                // Ajout de l'adresse à la liste
                addresses.add(address);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            httpClient.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return addresses;
    }
}
