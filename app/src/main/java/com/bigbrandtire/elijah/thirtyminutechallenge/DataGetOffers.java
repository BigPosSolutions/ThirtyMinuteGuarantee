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
 * Created by Elijah on 3/19/2018.
 */

public interface DataGetOffers {

    @GET("/BigBrandPushOffers")
    public void getOffers(Callback<List<Offers>> callback);

    @GET("/BigBrandPushOffers/{id}")
    public void getOffersById(@Path("id") Integer id, Callback<Offers> callback);

    @DELETE("/BigBrandPushOffers/{id}")
    public void deleteOffersById(@Path("id") Integer id,Callback<Offers> callback);

    @PUT("/BigBrandPushOffers/{id}")
    public void updateOffersById(@Path("id") Integer id, @Body Offers customer, Callback<Offers> callback);

    @POST("/BigBrandPushOffers")
    public void addOffers(@Body Offers student, Callback<Offers> callback);
}
