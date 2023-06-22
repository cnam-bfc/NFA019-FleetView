package net.cnam.fleetview.model.nominatim;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class NominatimAddressDAOTest {
    private static NominatimAddressDAO nominatimAddressDAO;

    @BeforeClass
    public static void beforeClass() {
        nominatimAddressDAO = new NominatimAddressDAO();
    }

    /**
     * Vérifier que le format de l'adresse retournée par l'API n'a pas changé par rapport à l'interprétation de l'application
     */
    @Test
    public void verifFormatReturnedByAPI() {
        String osmType = "N";
        long osmId = 2911629812L;

        NominatimAddress address = nominatimAddressDAO.lookup(osmType, osmId);
        // Vérification du format de l'adresse
        Assert.assertEquals("N", address.getNominatimId().getOsmType());
        Assert.assertEquals(2911629812L, address.getNominatimId().getOsmId());
        Assert.assertEquals("France", address.getPays());
        Assert.assertEquals("71100", address.getCodePostal());
        Assert.assertEquals("Chalon-sur-Saône", address.getCommune());
        Assert.assertEquals("Place de l'Hôtel de Ville", address.getRue());
        Assert.assertEquals("3", address.getNumeroDeRue());
    }
}