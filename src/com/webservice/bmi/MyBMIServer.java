package com.webservice.bmi;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface MyBMIServer {

    @WebMethod
    String calcBMI(String weight, String height);

    @WebMethod
    String listRanges();

    @WebMethod
    String listWeights(String height);
}