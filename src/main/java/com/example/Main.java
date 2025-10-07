package com.example;

import java.util.Scanner;

public class Main {

    static Environment devEnv = new Environment("DEV");
    static Environment qaEnv = new Environment("QA");
    static Environment prodEnv = new Environment("PROD");

    public static void main(String[] args) {      
        Scanner in = new Scanner(System.in);


        devEnv.addParameter(new Parameter("URLs.limit","NUMBER",600,devEnv.getVersion()));
        qaEnv.addParameter(new Parameter("HTTPS.reqs","NUMBER",5000,qaEnv.getVersion()));
        prodEnv.addParameter(new Parameter("Secure.state","BOOLEAN",true,prodEnv.getVersion()));

        //Testing purposes only
        /*System.out.println();
        System.out.println(devEnv);
        System.out.println(qaEnv);
        System.out.println(prodEnv);
        System.out.println(); */



        String command = in.nextLine().replaceAll("\\s+", ""); // Spaces are not needed in the commands

        MainMethods.validateCommand(command,in);
        
        String operation[] = command.strip().split("\\(");
        String params[] = operation[1].split(",");
        
        MainMethods.interpretCommand(operation[0],params);
        System.out.println();

        //Testing purposes only
        /*System.out.println();
        System.out.println(devEnv);
        System.out.println(qaEnv);
        System.out.println(prodEnv);
        System.out.println();*/
    }
}