package com.bigbrandtire.elijah.thirtyminutechallenge;

import java.util.Date;

/**
 * Created by Elija on 3/19/2018.
 */

public class Offers {

    public int id;
    public String Offers;
    public String Expiration;
    public String Begining;

    public String getOffers() {
        return Offers;
    }

    public void setOffers(String offers) {
        Offers = offers;
    }

    public String getExpiration() {
        return Expiration;
    }

    public void setExpiration(String expiration) {
        Expiration = expiration;
    }

    public String getBegining() {
        return Begining;
    }

    public void setBegining(String begining) {
        Begining = begining;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
