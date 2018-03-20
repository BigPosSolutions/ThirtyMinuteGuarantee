package com.bigbrandtire.elijah.thirtyminutechallenge;

import android.text.Editable;

/**
 * Created by Elija on 3/16/2018.
 */

public class CustomerInfo {

    public int id;
    public String PushKey;
    public String PhoneNumber;
    public String Name;
    public int CustomerNumber;
    public String AppId;
    public CustomerDetail pushdetail;

    public CustomerInfo(){
        this.pushdetail = new CustomerDetail();
    }
}
