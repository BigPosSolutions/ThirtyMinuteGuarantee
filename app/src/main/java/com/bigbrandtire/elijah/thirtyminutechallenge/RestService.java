package com.bigbrandtire.elijah.thirtyminutechallenge;

/**
 * Created by Elija on 3/16/2018.
 */

public class RestService {
    private static final String URL = "http://bbt.bigbrandtire.com/webApiTransfers/api/";
    private retrofit.RestAdapter restAdapter;
    private DataInitiater apiService;
    private DataGetOffers apiOffersData;
    private InvoiceDetails apiGetDetails;
    public RestService()
    {
        restAdapter = new retrofit.RestAdapter.Builder()
                .setEndpoint(URL)
                .setLogLevel(retrofit.RestAdapter.LogLevel.FULL)
                .build();

        apiService = restAdapter.create(DataInitiater.class);
        apiOffersData=restAdapter.create(DataGetOffers.class);
        apiGetDetails = restAdapter.create(InvoiceDetails.class);
    }

    public DataInitiater getService()
    {
        return apiService;
    }

    public DataGetOffers getOffersServices(){
        return apiOffersData;
    }
    public InvoiceDetails getDetailsServices(){
        return apiGetDetails;
    }
}
