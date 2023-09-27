# BMI Web Service and Client

## Description

This project consists of a web service (`MyBMIServer`) and a command-line client (`MyBMIClient`) that allow users to calculate the Body Mass Index (BMI) and determine the BMI classification. The web service provides three main functionalities:

1. Calculate the BMI and its classification based on weight and height.
2. List the known BMI ranges.
3. Provide the ideal range of weights for a given height.

The project is developed as a part of an assignment for SENG 4500 Networks and Distributed Computing at the University of Newcastle in 2022.

==========================================================================

To run the program:

-Open a terminal in the directory: "/bmi-web-service/src"
Then start the web server in this terminal by running the following command: java -cp . com/webservice/bmi/MyBMIServerImpl. (THE WEB SERVER MUST BE STARTED FIRST, obviously)



-Open another terminal in the same directory: "/bmi-web-service/src"
Then run the following command to calculate a bmi, where the weight=103.2kg and height=187cm: "java -cp . com/webservice/bmi/MyBMIClient calcBMI 103.2 187"



-To see the list of functions you can perform, run the command: "java -cp . com/webservice/bmi/MyBMIClient"




------- Please use java8 


				  	-== Thank You ==-
