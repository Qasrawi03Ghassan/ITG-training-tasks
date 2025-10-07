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
            if(command.equals("quit") || command.equals("exit") || command.equals("q"))System.exit(0);
        }
    }

    public static void checkValueType(String type, Object value){
        try{
            if(type.equals("NUMBER")){
                value = Integer.parseInt((String)value);
            }else if(type.equals("BOOLEAN")){
                value = Boolean.parseBoolean((String)value);
            }else if(type.equals("STRING")){
                value = String.valueOf(value);
            }else{
                System.out.println("Invalid type, supported types are: \"BOOLEAN\",\"STRING\",\"NUMBER\"");
            }
        }catch(Exception e){
            System.out.println("Invalid parameters, value of \"" + value + "\" is not of type \"" + type + "\".");
        }
    }

    public static void interpretCommand(String op, String params[]){
        op=op.toLowerCase();

        if(params.length == 0){
            System.err.println("Invalid command: no parameters were given.");
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
                    System.err.println("Invalid parameters count.");
                    break;
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
                    //System.out.println("Update an existing parameter"); COMPLETED

                    name = params[0];
                    type = params[1];
                    value=params[2];
                    environment = params[3];

                    checkValueType(type, value);

                    switch (environment) {
                        case "DEV":
                            handleUpdateParameter(Main.devEnv, name, type, value, environment);
                            break;

                        case "QA":
                            handleUpdateParameter(Main.qaEnv, name, type, value, environment);
                            break;

                        case "PROD":
                            handleUpdateParameter(Main.prodEnv, name, type, value, environment);
                            break;
                    
                        default:
                            break;
                    }

                
                break;

            case "overridevalue":
                //System.out.println("Override an existing value"); COMPLETED

                name = params[0];
                environment = params[1];
                value = params[2];

                switch (environment) {
                    case "DEV":
                        handleOverrideValue(Main.devEnv, name, value);
                        break;

                    case "QA":
                        handleOverrideValue(Main.qaEnv, name, value);
                        break;

                    case "PROD":
                        handleOverrideValue(Main.prodEnv, name, value);
                        break;
                    
                    default:
                        break;
                    }


                
                break;

            case "search":
                System.out.println("Search for a parameter - case insensitive");

                environment = params[0];


                switch (environment.toUpperCase()) {
                        case "DEV":
                            handleSearch(Main.devEnv);
                            break;

                        case "QA":
                            handleSearch(Main.qaEnv);
                            break;

                        case "PROD":
                            handleSearch(Main.prodEnv);
                            break;

                        case "BOOLEAN","STRING","NUMBER":
                            handleSearchByType(Main.devEnv, environment);
                            handleSearchByType(Main.prodEnv, environment);
                            handleSearchByType(Main.qaEnv, environment);

                            break;
                        
                        default:

                            handleSearch(Main.devEnv,environment);
                            handleSearch(Main.qaEnv,environment);
                            handleSearch(Main.prodEnv,environment);

                            break;
                    }
                




                break;
        

            case "export":
                //System.out.println("Export environment to JSON"); COMPLETED

                if(params.length != 1){
                    System.err.println("Invalid parameters count, system will shutdown.");
                    System.exit(1);
                }
                String targetToExport = params[0];
                handleExportByEnv(targetToExport);

                break;

             case "deleteparameter","removeparameter":
                //System.out.println("Delete a specific parameter"); COMPLETED

                if(params.length != 2){
                     System.err.println("Invalid parameters count.");
                }

                String targetToDelete = params[0];
                environment = params[1];

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
                    if(environment.equals("DEV"))handleRemoveParameter(Main.devEnv, targetToDelete);
                    else if(environment.equals("QA"))handleRemoveParameter(Main.qaEnv, targetToDelete);
                    else if(environment.equals("PROD"))handleRemoveParameter(Main.prodEnv, targetToDelete);
                    else{
                        System.out.println("Environment \"" + environment + "\" does not exist.");
                    }
                }

                break;

            default:
                System.out.println("Invalid command.");
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

    public static void logOldParameter(Environment envObj, String name,String type, Object value, String env){
        //TODO: Implement file logging 
        System.out.println("Must log old data with time stamp before updating.");



    }

    public static void handleUpdateParameter(Environment envObj,String name,String type, Object value, String env){
        logOldParameter(envObj, name, type, value, env);
        envObj.findAndUpdateParameter(name, type, value, env);
    }

    public static void handleOverrideValue(Environment envObj, String name,Object value){
        //TODO: Implement file logging for overriding
        //logOldParameter(envObj, name, name, value, name);
        envObj.findAndOverrideValue(name, value);
    }

    public static void handleRemoveParameter(Environment envObj, String name){
        int foundName = 0;
        for(int i=0;i<envObj.getParameters().size();i++){
            if(name.equals(envObj.getParameters().get(i).getName())){
                foundName = 1;
                envObj.removeParameter(name);
            }else{
                foundName = 0;
            }
        }
        if(foundName == 0){
            System.out.println("Could not find parameter with name \"" + name + "\" in \"" + envObj.getVersion() + "\" environment.");
        }
    }
    public static void handleRemoveParameter(Environment envObj, Long id){
        for(int i=0;i<envObj.getParameters().size();i++){
            if(id.equals(envObj.getParameters().get(i).getId())){
                envObj.removeParameter(id);
            }
        }
    }

    public static void handleSearch(Environment envObj){
        envObj.searchByEnv();
    }

    public static void handleSearch(Environment envObj,String subName){
        envObj.searchBySubName(subName);
    }

    public static void handleSearchByType(Environment envObj,String type){
        envObj.searchByType(type);
    }
    
    public static void handleExportByEnv(String t){
        if(t.equals(Main.devEnv.getVersion())){
            System.out.println(Main.devEnv);
        }else if(t.equals(Main.prodEnv.getVersion())){
            System.out.println(Main.prodEnv);
        }else if(t.equals(Main.qaEnv.getVersion())){
            System.out.println(Main.qaEnv);
        }else{
            System.out.println("\"" + t + "\" environment was not found.");
        }
        System.out.println();
    }
}
