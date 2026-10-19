package pl.pwr.orders;

import java.nio.charset.StandardCharsets; //zestaw standardowych kodowań tekstów
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat; //bajty na szesnastkowy

public class OrderNumberGenerator {

    public static String generate(OrderRequest order) {
        Customer c = order.customer(); //zamawiajacy

        StringBuilder data = new StringBuilder(); //sklejamy
        data.append(c.firstName()).append('|') //separator!
                .append(c.lastName()).append('|')
                .append(c.email()).append('|')
                .append(c.phone()).append('|');

        for (Product p : order.products()) {
            data.append(p.code()).append(':')
                    .append(p.quantity()).append(':')
                    .append(p.unit()).append(';');
        }

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256"); //liczymy skrot algorytmem
            byte[] hash = digest.digest(data.toString().getBytes(StandardCharsets.UTF_8));
            return "ORD-" + HexFormat.of().withUpperCase().formatHex(hash).substring(0, 12);
        } catch (NoSuchAlgorithmException e) { //np literowka
            throw new IllegalStateException(e);
        }
    }
}

//.getBytes(UTF-8) - zamiana tekstu na bajty
// digest.digest() - skrot (32bajty) byte[] hash
//HexFormat.of().withUpperCase() - zapis szesnastkowy wielkimi literami.
//.formatHex(hash) zamiana 32 bajty na tekst o długości 64 znaków
