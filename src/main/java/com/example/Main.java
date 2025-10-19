package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {  
        System.out.println();
        List<Parameter> parameters = new LinkedList<>();

        try{
            FileReader fr = new FileReader("db.txt");
            BufferedReader bf = new BufferedReader(fr);
            LinkedList <String> paramsList = new LinkedList<>();
            String line;

            while((line = bf.readLine()) != null){
                if(line.startsWith("//") || line.strip().equals("")){
                    continue;
                }
                paramsList.add(line);
            }

            System.out.println("Available parameters in the database text file:\n");
            for(int i=0;i<paramsList.size();i++){
                parameters.add(Parameter.parseParameter(paramsList.get(i)));
            }
            System.out.println("===================================\nType commands here:");

            bf.close();
            fr.close();
            
        }catch(Exception e){
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println();
        Scanner in = new Scanner(System.in);
        String command = "";

        
        Environment[] envsArr = new Environment[3];
        envsArr[0] = new Environment("DEV");
        envsArr[1] = new Environment("QA");
        envsArr[2] = new Environment("PROD");

        List<Parameter> devParameters = new LinkedList<>();
        List<Parameter> qaParameters = new LinkedList<>();
        List<Parameter>  prodParameters = new LinkedList<>();

        for(int i=0;i<parameters.size();i++){
            if(parameters.get(i).getEnvironment().equals(envsArr[0].getVersion()))devParameters.add(parameters.get(i));
            if(parameters.get(i).getEnvironment().equals(envsArr[1].getVersion()))prodParameters.add(parameters.get(i));
            if(parameters.get(i).getEnvironment().equals(envsArr[2].getVersion()))qaParameters.add(parameters.get(i));
        }

        for(int i=0;i<parameters.size();i++){
            if(parameters.get(i).getEnvironment().equalsIgnoreCase(envsArr[0].getVersion()))envsArr[0].setParameters(devParameters);
            if(parameters.get(i).getEnvironment().equalsIgnoreCase(envsArr[1].getVersion()))envsArr[1].setParameters(qaParameters);
            if(parameters.get(i).getEnvironment().equalsIgnoreCase(envsArr[2].getVersion()))envsArr[2].setParameters(prodParameters);
        }
        
        do{
            command = in.nextLine().strip();
            System.out.println();
            MainMethods.validateCommand(command,in);

            String operation[]={};
            String params[]={};
            
            try {
                operation = command.strip().split("\\(");
                params = operation[1].split(",");
            } catch (Exception e) {
                System.out.println("Error occurred: " +e.getMessage());
            }
            

            
            for(int i=0;i<params.length;i++){
                params[i]=params[i].strip();
            }

            MainMethods.interpretCommand(operation[0],params,envsArr);
        }while(!command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("exit") || command.equals("q"));
        in.close();

    }
}