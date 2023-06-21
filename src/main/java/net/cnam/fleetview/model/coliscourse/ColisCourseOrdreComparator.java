package net.cnam.fleetview.model.coliscourse;

import java.util.Comparator;

public class ColisCourseOrdreComparator implements Comparator<ColisCourse> {
    @Override
    public int compare(ColisCourse o1, ColisCourse o2) {
        // Compare les ordres
        return o1.getOrdre().compareTo(o2.getOrdre());
    }
}
