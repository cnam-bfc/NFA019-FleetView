package net.cnam.fleetview.model.secteurdelimitation;

import java.util.Comparator;

public class SecteurDelimitationOrdreComparator implements Comparator<SecteurDelimitation> {
    @Override
    public int compare(SecteurDelimitation o1, SecteurDelimitation o2) {
        return o1.getOrdre() - o2.getOrdre();
    }
}
