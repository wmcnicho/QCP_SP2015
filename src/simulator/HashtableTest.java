package simulator;

import java.util.Enumeration;
import java.util.Hashtable;

public class HashtableTest {

    public static void main(String[] args) {
        Hashtable<Integer, String> ht = new Hashtable<Integer, String>();
        String s;

        ht.put(2, "Hund");
        ht.put(1, "Katze");
        ht.put(3, "Regenwurm");
        System.out.println("Durchlauf 1:");
        for (Integer elem : ht.keySet()) {
            s = ht.get(elem);
            System.out.println(elem + " - " + s);
        }
        // Wert unter Key "1" wird ersetzt
        ht.put(1, "Pferd");
        System.out.println("\nDurchlauf 2:");
        Enumeration<Integer> keys = ht.keys();
        while (keys.hasMoreElements()) {
            Integer i = keys.nextElement();
            s = ht.get(i);
            System.out.println(i + " - " + s);
        }

        System.out.println("\nKatze vorhanden? " + ht.containsValue("Katze"));

        ht.put(4, "Hund");
        System.out.println("\nAnzahl Elemente nach einf\u00FCgen von '4': "
                + ht.size());
        System.out.println("\nDurchlauf 3:");
        Enumeration<String> vals = ht.elements();
        while (vals.hasMoreElements()) {
            System.out.println(vals.nextElement());
        }

        ht.remove(4);
        System.out.println("\nAnzahl Elemente nach entfernen von '4': "
                + ht.size());

        ht.clear();
        System.out.println("\nAnzahl Elemente nach clear(): " + ht.size());
    }
} 