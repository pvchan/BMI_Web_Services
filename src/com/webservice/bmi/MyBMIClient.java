package com.webservice.bmi;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class MyBMIClient {
    public static void main(String[] args) throws Exception {
        // Check for the required arguments
        if (args.length == 0) {
            System.out.println("Usage: java MyBMIClient <function> <arguments>");
            System.out.println("Available functions: calcBMI, listRanges, listWeights");
            return;
        }

        // Create a URL object pointing to the server's WSDL file
        URL url = new URL("http://localhost:8888/bmi?wsdl");

        // Create a QName object to specify the service's namespace and name
        QName qname = new QName("http://bmi.webservice.com/", "MyBMIServerImplService");

        // Create a Service object using the URL and QName objects
        Service service = Service.create(url, qname);

        // Get a reference to the server's implementation of the BMI Calculator
        MyBMIServer bmiServer = service.getPort(MyBMIServer.class);

        // Call the appropriate function based on the user's input
        String function = args[0];
        switch (function) {
            case "calcBMI":
                if (args.length != 3) {
                    System.out.println("Usage: java MyBMIClient calcBMI <weight> <height>");
                    return;
                }
                String weight = args[1];
                String height = args[2];
                String result = bmiServer.calcBMI(weight, height);
                System.out.println(result);
                break;
            case "listRanges":
                String ranges = bmiServer.listRanges();
                System.out.println(ranges);
                break;
            case "listWeights":
                if (args.length != 2) {
                    System.out.println("Usage: java MyBMIClient listWeights <height>");
                    return;
                }
                String desiredWeights = bmiServer.listWeights(args[1]);
                System.out.println(desiredWeights);
                break;
            default:
                System.out.println("Invalid function: " + function);
                System.out.println("Available functions: calcBMI, listRanges, listWeights");
        }
    }
}
