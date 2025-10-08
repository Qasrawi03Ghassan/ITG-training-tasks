package com.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {  
        System.out.println();
        Scanner in = new Scanner(System.in);
        String command = "";

        
        Environment[] envsArr = new Environment[3];
        envsArr[0] = new Environment("DEV");
        envsArr[1] = new Environment("QA");
        envsArr[2] = new Environment("PROD");

        //Example parameters
        envsArr[0].addParameter(new Parameter("URLs.limit","NUMBER",600,envsArr[0].getVersion()));
        envsArr[1].addParameter(new Parameter("HTTPS.reqs","NUMBER",5000,envsArr[1].getVersion()));
        envsArr[2].addParameter(new Parameter("Secure.state","BOOLEAN",true,envsArr[2].getVersion()));

        
        do{
            
            command = in.nextLine().strip();
            System.out.println();

            MainMethods.validateCommand(command,in);
            
            
            String operation[] = command.strip().split("\\(");
            String params[] = operation[1].split(",");

            
            for(int i=0;i<params.length;i++){
                params[i]=params[i].strip();
            }

            MainMethods.interpretCommand(operation[0],params,envsArr);
        }while(!command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("exit") || command.equals("q"));
        in.close();

    }
}