package net.cnam.fleetview.model.nominatim;

import com.google.gson.*;
import net.cnam.fleetview.App;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
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
    private final Map<NominatimAddress.Identifier, NominatimAddress> cache = new java.util.HashMap<>();

    /**
     * Recherche d'adresses
     *
     * @param searchQuery
     * @return Liste d'adresses possibles
     */
    public List<NominatimAddress> search(String searchQuery) {
        LinkedList<NominatimAddress> addresses = new LinkedList<>();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            String encodedQuery = URLEncoder.encode(searchQuery, "UTF-8");
            String apiUrl = NOMINATIM_URL + "search?q=" + encodedQuery + "&format=json&addressdetails=1&countrycodes=fr";

            HttpGet request = new HttpGet(apiUrl);
            // Ajout de l'entête
            request.addHeader("User-Agent", USER_AGENT);
            request.addHeader("accept", "application/json");

            // Exécution de la requête
            CloseableHttpResponse response = httpClient.execute(request);
            // Récupération du résultat
            String responseBody = EntityUtils.toString(response.getEntity());
            // Traitement du résultat
            JsonArray jsonArray = JsonParser.parseString(responseBody).getAsJsonArray();
            // Parcours des résultats
            for (JsonElement jsonElements : jsonArray) {
                // Création d'un objet NominatimAddress
                NominatimAddress address = new NominatimAddress();

                // Remplissage des données
                this.fill(address, jsonElements.getAsJsonObject());

                // Ajout de l'adresse à la liste
                addresses.add(address);
            }

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        try {
            httpClient.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return addresses;
    }

    /**
     * Récupération d'une adresse à partir de son type et de son identifiant
     *
     * @param osmType Type de l'adresse
     * @param osmId   Identifiant de l'adresse
     * @return Adresse
     */
    public NominatimAddress lookup(String osmType, long osmId) {
        NominatimAddress.Identifier identifier = new NominatimAddress.Identifier(osmType, osmId);
        if (this.cache.containsKey(identifier)) {
            return this.cache.get(identifier);
        }

        // Création de l'adresse
        NominatimAddress address = new NominatimAddress();
        // Création du client HTTP
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // Création de la requête
        HttpGet request = new HttpGet("https://nominatim.openstreetmap.org/lookup?format=json&osm_ids=" + identifier.getOsmType() + identifier.getOsmId() + "&addressdetails=1&countrycodes=fr");
        // Ajout de l'entête
        request.addHeader("User-Agent", USER_AGENT);
        request.addHeader("accept", "application/json");
        try {
            // Exécution de la requête
            CloseableHttpResponse response = httpClient.execute(request);
            // Récupération du résultat
            String responseBody = EntityUtils.toString(response.getEntity());
            // Traitement du résultat
            JsonArray jsonArray = JsonParser.parseString(responseBody).getAsJsonArray();
            // Parcours des résultats
            for (JsonElement jsonElements : jsonArray) {
                // Récupération des données
                this.fill(address, jsonElements.getAsJsonObject());
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        try {
            httpClient.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return address;
    }

    private void fill(NominatimAddress address, JsonObject jsonObject) {
        // Ajout des données à l'adresse
        NominatimAddress.Identifier identifier = new NominatimAddress.Identifier();
        // OSM_TYPE
        identifier.setOsmType(jsonObject.get("osm_type").getAsString());
        // OSM_ID
        identifier.setOsmId(jsonObject.get("osm_id").getAsLong());
        address.setNominatimId(identifier);
        // LAT
        address.setLat(jsonObject.get("lat").getAsDouble());
        // LON
        address.setLon(jsonObject.get("lon").getAsDouble());
        // ADDRESS
        JsonObject addressObject = jsonObject.get("address").getAsJsonObject();
        // Pays
        if (addressObject.get("country") != null) {
            address.setPays(addressObject.get("country").getAsString());
        }
        // Code postal
        if (addressObject.get("postcode") != null) {
            address.setCodePostal(addressObject.get("postcode").getAsString());
        }
        // Commune
        if (addressObject.get("city") != null) {
            address.setCommune(addressObject.get("city").getAsString());
        } else if (addressObject.get("town") != null) {
            address.setCommune(addressObject.get("town").getAsString());
        } else if (addressObject.get("village") != null) {
            address.setCommune(addressObject.get("village").getAsString());
        } else if (addressObject.get("hamlet") != null) {
            address.setCommune(addressObject.get("hamlet").getAsString());
        }
        // Rue
        if (addressObject.get("road") != null) {
            address.setRue(addressObject.get("road").getAsString());
        } else if (addressObject.get("pedestrian") != null) {
            address.setRue(addressObject.get("pedestrian").getAsString());
        } else if (addressObject.get("footway") != null) {
            address.setRue(addressObject.get("footway").getAsString());
        } else if (addressObject.get("cycleway") != null) {
            address.setRue(addressObject.get("cycleway").getAsString());
        } else if (addressObject.get("path") != null) {
            address.setRue(addressObject.get("path").getAsString());
        } else if (addressObject.get("track") != null) {
            address.setRue(addressObject.get("track").getAsString());
        } else if (addressObject.get("service") != null) {
            address.setRue(addressObject.get("service").getAsString());
        } else if (addressObject.get("residential") != null) {
            address.setRue(addressObject.get("residential").getAsString());
        } else if (addressObject.get("unclassified") != null) {
            address.setRue(addressObject.get("unclassified").getAsString());
        } else if (addressObject.get("tertiary") != null) {
            address.setRue(addressObject.get("tertiary").getAsString());
        } else if (addressObject.get("secondary") != null) {
            address.setRue(addressObject.get("secondary").getAsString());
        } else if (addressObject.get("primary") != null) {
            address.setRue(addressObject.get("primary").getAsString());
        } else if (addressObject.get("trunk") != null) {
            address.setRue(addressObject.get("trunk").getAsString());
        } else if (addressObject.get("motorway") != null) {
            address.setRue(addressObject.get("motorway").getAsString());
        }
        // Numéro de rue
        if (addressObject.get("house_number") != null) {
            address.setNumeroDeRue(addressObject.get("house_number").getAsString());
        }
        // Complément d'adresse
        if (addressObject.get("suburb") != null) {
            address.setComplement(addressObject.get("suburb").getAsString());
        } else if (addressObject.get("quarter") != null) {
            address.setComplement(addressObject.get("quarter").getAsString());
        } else if (addressObject.get("neighbourhood") != null) {
            address.setComplement(addressObject.get("neighbourhood").getAsString());
        } else if (addressObject.get("city_district") != null) {
            address.setComplement(addressObject.get("city_district").getAsString());
        } else if (addressObject.get("county") != null) {
            address.setComplement(addressObject.get("county").getAsString());
        } else if (addressObject.get("state_district") != null) {
            address.setComplement(addressObject.get("state_district").getAsString());
        } else if (addressObject.get("state") != null) {
            address.setComplement(addressObject.get("state").getAsString());
        }

        // Gestion du cache
        this.cache.put(identifier, address);
    }
}
