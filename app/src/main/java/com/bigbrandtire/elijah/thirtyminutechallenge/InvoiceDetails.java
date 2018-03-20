package com.bigbrandtire.elijah.thirtyminutechallenge;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by Elija on 3/19/2018.
 */

public interface InvoiceDetails {
    @GET("/BigBrandPushDetails")
    public void getOffers(Callback<List<BBTInvoiceDetails>> callback);

    @GET("/BigBrandPushDetails/{id}")
    public void getOffersById(@Path("id") String id, Callback<BBTInvoiceDetails> callback);

    @DELETE("/BigBrandPushDetails/{id}")
    public void deleteOffersById(@Path("id") Integer id,Callback<BBTInvoiceDetails> callback);

    @PUT("/BigBrandPushDetails/{id}")
    public void updateOffersById(@Path("id") String id, Callback<BBTInvoiceDetails> callback);

    @POST("/BigBrandPushDetails")
    public void addOffers(@Body BBTInvoiceDetails student, Callback<BBTInvoiceDetails> callback);
}
