package com.example;

import java.util.Scanner;

public  class MainMethods {
    static Long newId = 0L;
    public static void validateCommand(String command,Scanner in){
        if(command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("exit") || command.equals("q")){
            System.exit(0);
        }

        while(!command.contains("(") || !command.endsWith(")")){
            System.out.println("Invalid command, please try again: ");
            command = in.nextLine().replaceAll("\\s+", "");
        }
    }

    public static void checkValueType(String type, Object value){
        if(type.equals("NUMBER")){
            value = Integer.parseInt((String)value);
        }else if(type.equals("BOOLEAN")){
            value = Boolean.parseBoolean((String)value);
        }
    }

    public static void interpretCommand(String op, String params[]){
        op=op.toLowerCase();

        if(params.length == 0){
            System.err.println("Invalid command: no parameters were given.\nSystem terminates.");
            System.exit(0);
        }

        for(int i=0;i<params.length;i++){
            if(params[i].endsWith(")")){
                params[i] = params[i].replace(")", "");
            }
            if(params[i].startsWith("\"") && params[i].endsWith("\"")){
                params[i]=params[i].replace("\"", "");
            }
        }

        String name="";
        String type="";
        Object value=null;
        String environment="";

        switch (op) {
            case "addparameter":
                //System.out.println("Add a new parameter using these params: " ); COMPLETED

                if(params.length != 4){
                    System.err.println("Invalid parameters count, system will shutdown.");
                    System.exit(1);
                }

                name = params[0];
                type = params[1];
                value=params[2];
                environment = params[3];

                checkValueType(type, value);
                
                switch (environment) {
                case "DEV":
                    handleAddParam(Main.devEnv, name, type, value, environment);
                    break;

                case "QA":
                    handleAddParam(Main.qaEnv, name, type, value, environment);
                    break;

                case "PROD":
                    handleAddParam(Main.prodEnv, name, type, value, environment);
                    break;
            
                default:
                    break;
            }


                break;

            case "updateparameter":
                System.out.println("Update an existing parameter");
                

                
                break;

            case "overridevalue":
                System.out.println("Override an existing value");
                
                break;

            case "search":
                System.out.println("Search for a parameter - case insensitive");


                break;
        

            case "export":
                //System.out.println("Export environment to JSON");
                if(params.length != 1){
                    System.err.println("Invalid parameters count, system will shutdown.");
                    System.exit(1);
                }
                String targetToExport = params[0];
                handleExportByEnv(targetToExport);

                break;

             case "deleteparameter":
                //System.out.println("Delete a specific parameter"); COMPLETED

                if(params.length != 2){
                     System.err.println("Invalid parameters count, system will shutdown.");
                    System.exit(1);
                }
                environment = params[0];
                String targetToDelete = params[1];
                try{
                    Long targetId = Long.parseLong(targetToDelete);
                    
                    switch (environment) {
                        case "DEV":
                            handleRemoveParameter(Main.devEnv, targetId);
                            break;

                        case "QA":
                            handleRemoveParameter(Main.qaEnv, targetId);
                            break;

                        case "PROD":
                            handleRemoveParameter(Main.prodEnv, targetId);
                            break;
                    
                        default:
                            break;
                    }

                }catch(NumberFormatException e){
                    handleRemoveParameter(Main.devEnv, targetToDelete);
                }

                break;

            default:
                System.out.println("Invalid command, system will shut down");
                System.exit(1);
                break;
        }
    }

    public static void handleAddParam(Environment envObj,String name, String type, Object value, String env){
        newId++;
        Parameter newParam = null;
        if(env.equals(envObj.getVersion())){
            newParam = new Parameter(newId,name,type,value,value,envObj.getVersion());
        }
        envObj.addParameter(newParam);
    }

    public static void handleRemoveParameter(Environment envObj, String name){
        for(int i=0;i<envObj.getParameters().size();i++){
            if(name.equals(envObj.getParameters().get(i).getName())){
                envObj.removeParameter(name);
            }
        }
    }
    public static void handleRemoveParameter(Environment envObj, Long id){
        for(int i=0;i<envObj.getParameters().size();i++){
            if(id.equals(envObj.getParameters().get(i).getId())){
                envObj.removeParameter(id);
            }
        }
    }
    
    public static void handleExportByEnv(String t){
        if(t.equals(Main.devEnv.getVersion())){
            System.out.println(Main.devEnv);
        }else if(t.equals(Main.prodEnv.getVersion())){
            System.out.println(Main.prodEnv);
        }else if(t.equals(Main.qaEnv.getVersion())){
            System.out.println(Main.qaEnv);
        }else{
            System.out.println(t + " environment was not found.");
        }
        System.out.println();
    }
}
