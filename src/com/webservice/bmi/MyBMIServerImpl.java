package com.webservice.bmi;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService(endpointInterface = "com.webservice.bmi.MyBMIServer", portName = "8888")
public class MyBMIServerImpl implements MyBMIServer {

    private int numRequests = 0;
    private double[] bmiRanges;
    private double[] bmiRanges2;
    private String[] bmiLabels;

    public MyBMIServerImpl() {
        bmiRanges = new double[]{18.50, 25, 30, 100.00};
        bmiRanges2 = new double[]{0,18.50, 25, 30, 100.00};
        bmiLabels = new String[]{"UNDERWEIGHT", "NORMAL RANGE", "OVERWEIGHT", "OBESE"};
    }

    @Override
    public String calcBMI(String weight, String height) {
        numRequests++;
        double w = Double.parseDouble(weight);
        double h = Double.parseDouble(height) / 100;
        double bmi = w / (h * h);
        String classification = getClassification(bmi);
        String result = String.format("%.2f %s", bmi, classification);
        return result;
    }

    private String getClassification(double bmi) {
        if (bmi < bmiRanges[0]) {
            return "UNDERWEIGHT";
        } else if (bmi <= bmiRanges[1]) {
            return "NORMAL RANGE";
        } else if (bmi <= bmiRanges[2]) {
            return "OVERWEIGHT";
        } else if (bmi < bmiRanges[3]) {
            return "OBESE";
        } else {
            return "UNDEFINED";
        }
    }

    @Override
    public String listRanges() {
        numRequests++;
        if (bmiRanges2.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bmiRanges2.length - 1; i++) {
                sb.append(String.format("%.2f - %.2f : %s\n", bmiRanges2[i], bmiRanges2[i + 1] - 0.01, bmiLabels[i]));
            }
            return sb.toString();
        } else {
            return "UNDEFINED";
        }
    }

    @Override
    public String listWeights(String height) {
        numRequests++;
        double h = Double.parseDouble(height) / 100;
        double idealWeightLow = bmiRanges[0] * (h * h);
        double idealWeightHigh = bmiRanges[1] * (h * h);
        return String.format("%.2f - %.2f", idealWeightLow, idealWeightHigh);
    }

    public static void main(String[] args) {
        // Set the endpoint URL and create the server instance
        String url = "http://localhost:8888/bmi";
        Endpoint endpoint = Endpoint.publish(url, new MyBMIServerImpl());

        // Check if the server was successfully started
        if (endpoint.isPublished()) {
            System.out.println("BMI server started at " + url);
        } else {
            System.out.println("Failed to start BMI server");
        }
    }

}
