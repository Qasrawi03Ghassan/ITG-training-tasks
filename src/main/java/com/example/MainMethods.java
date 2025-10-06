package com.example;

import java.util.Scanner;

public  class MainMethods {
    static Long newId = 0L;
    public static void validateCommand(String command,Scanner in){
        while(!command.contains("(") || !command.endsWith(")")){
            System.out.println("Invalid command, please try again: ");
            command = in.nextLine().replaceAll("\\s+", "");
        }
        in.close();
    }

    public static void interpretCommand(String op, String params[]){
        op=op.toLowerCase();
        int flag = 0;

        switch (op) {
            case "addparameter":
                System.out.println("Add a new parameter using these params: " ); 
                flag = 1;     


                break;

            case "updateparameter":
                System.out.println("Update an existing parameter");
                flag = 1;

                
                break;

            case "overridevalue":
                System.out.println("Override an existing value");
                
                break;

            case "search":
                System.out.println("Search for a parameter - case insensitive");


                break;
        

            case "export":
                System.out.println("Export environment to JSON");


                break;

             case "deleteparameter":
                System.out.println("Delete a specific parameter");



                break;

            default:
                System.out.println("Invalid command");
                break;
        }

        Object value = null;
        for(int i=0;i<params.length;i++){
            if(params[i].endsWith(")")){
                params[i] = params[i].replace(")", "");
            }
            if(params[i].startsWith("\"") && params[i].endsWith("\"")){
                params[i]=params[i].replace("\"", "");
            }
        }   

        System.out.println();

        if(flag == 1){
            String name = params[0];
            String type = params[1];
            value = params[2];
            String environment = params[3];

            if(type.equals("NUMBER")){
                value = Integer.parseInt((String)value);
            }else if(type.equals("BOOLEAN")){
                value = Boolean.parseBoolean((String)value);
            }

            handleAddParam(Main.env, name, type, value);
            //These are just for debugging purposes
            /*System.out.println("Name: " + name);
            System.out.println("Type: " + type);
            System.out.println("Value: " + value);
            System.out.println("Environment: " + environment);

            System.out.println();
            System.out.println("Value variable value: " + value);
            System.out.println("value variable type: " + value.getClass().getName());*/
        }
    }

    public static void handleAddParam(Environment env,String name, String type, Object value){
        newId++;
        Parameter newParam = new Parameter(newId,name,type,value,value,env.getVersion());
        env.addParameter(newParam);
    } 

}
