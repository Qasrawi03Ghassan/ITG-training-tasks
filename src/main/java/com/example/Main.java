package com.example;

import java.util.Scanner;

public class Main {

    //Will deal with this later but keep it like this for now
    static Environment devEnv = new Environment("DEV");
    static Environment qaEnv = new Environment("QA");
    static Environment prodEnv = new Environment("PROD");

    public static void main(String[] args) {  
        System.out.println();

        Scanner in = new Scanner(System.in);

        //Testing only
        /*devEnv.addParameter(new Parameter("URLs.limit","NUMBER",600,devEnv.getVersion()));
        qaEnv.addParameter(new Parameter("HTTPS.reqs","NUMBER",5000,qaEnv.getVersion()));
        prodEnv.addParameter(new Parameter("Secure.state","BOOLEAN",true,prodEnv.getVersion()));*/

        String command = "";

        do{
            command = in.nextLine().strip();
            System.out.println();

            MainMethods.validateCommand(command,in);
            
            String operation[] = command.strip().split("\\(");
            String params[] = operation[1].split(",");

            //This allows users to enter white spaces between parameters
            for(int i=0;i<params.length;i++){
                params[i]=params[i].strip();
            }

            MainMethods.interpretCommand(operation[0],params);
        }while(!command.equalsIgnoreCase("quit"));
        in.close();

    }
}