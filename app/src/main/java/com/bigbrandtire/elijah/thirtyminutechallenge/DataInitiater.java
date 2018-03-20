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
 * Created by Elija on 3/16/2018.
 */

public interface DataInitiater {

    @GET("/BigBrandPushNotifications")
    public void getCustomers(Callback<List<CustomerInfo>> callback);

    //i.e. http://localhost/api/institute/Students/1
    //Get student record base on ID
    @GET("/BigBrandPushNotifications/{id}")
    public void getCustomersById(@Path("id") String id,Callback<CustomerInfo> callback);

    //i.e. http://localhost/api/institute/Students/1
    //Delete student record base on ID
    @DELETE("/BigBrandPushNotifications/{id}")
    public void deleteCustomersById(@Path("id") Integer id,Callback<CustomerInfo> callback);

    //i.e. http://localhost/api/institute/Students/1
    //PUT student record and post content in HTTP request BODY
    @PUT("/BigBrandPushNotifications/{id}")
    public void updateCustomersById(@Path("id") Integer id, @Body CustomerInfo customer, Callback<CustomerInfo> callback);

    //i.e. http://localhost/api/institute/Students
    //Add student record and post content in HTTP request BODY
    @POST("/BigBrandPushNotifications")
    public void addCustomers(@Body CustomerInfo student, Callback<CustomerInfo> callback);
}
