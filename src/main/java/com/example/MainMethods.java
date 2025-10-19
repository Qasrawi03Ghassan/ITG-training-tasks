package com.example;

import java.io.FileWriter;
import java.util.Scanner;

public  class MainMethods {
    static Long newId = Parameter.x;
    public static void validateCommand(String command,Scanner in){
        if(command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("exit") || command.equals("q")){
            System.exit(0);
        }

        while(!command.contains("(") || !command.endsWith(")")){
            System.out.println("Invalid command, please try again: ");
            command = in.nextLine().replaceAll("\\s+", "");
            if(command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("exit") || command.equals("q"))System.exit(0);
        }
    }

    public static boolean checkValueType(String type, Object value){
        boolean flag = false;
        try{
            if(type.equals("NUMBER")){
                value = Integer.parseInt((String)value);
                flag =  true;
            }else if(type.equals("BOOLEAN")){
                value = Boolean.parseBoolean((String)value);
                flag =  true;
            }else if(type.equals("STRING")){
                value = String.valueOf(value);
                flag =  true;
            }else{
                System.out.println("Invalid type, supported types are: \"BOOLEAN\",\"STRING\",\"NUMBER\"");
                flag = false;
            }
        }catch(Exception e){
            System.out.println("Invalid parameters, value of \"" + value + "\" is not of type \"" + type + "\".");
            flag =  false;
        }
        return flag;
    }

    public static void interpretCommand(String op, String params[],Environment[] envsArr){
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
                if(params.length != 4){
                    System.err.println("Invalid parameters count.");
                    break;
                }

                name = params[0];
                type = params[1];
                value = params[2];
                environment = params[3];

                if(!checkValueType(type, value)){
                    break;
                }
                
                switch (environment) {
                case "DEV":
                    handleAddParam(envsArr[0], name, type, value, environment);
                    break;

                case "QA":
                    handleAddParam(envsArr[1], name, type, value, environment);
                    break;

                case "PROD":
                    handleAddParam(envsArr[2], name, type, value, environment);
                    break;
            
                default:
                    break;
            }


                break;

            case "updateparameter":

                    name = params[0];
                    type = params[1];
                    value=params[2];
                    environment = params[3];

                    if(!checkValueType(type, value)){
                        break;
                    }

                    switch (environment) {
                        case "DEV":
                            handleUpdateParameter(envsArr[0], name, type, value, environment);
                            break;

                        case "QA":
                            handleUpdateParameter(envsArr[1], name, type, value, environment);
                            break;

                        case "PROD":
                            handleUpdateParameter(envsArr[2], name, type, value, environment);
                            break;
                    
                        default:
                            break;
                    }

                
                break;

            case "overridevalue":

                name = params[0];
                environment = params[1];
                value = params[2];

                switch (environment) {
                    case "DEV":
                        handleOverrideValue(envsArr[0], name, value);
                        break;

                    case "QA":
                        handleOverrideValue(envsArr[1], name, value);
                        break;

                    case "PROD":
                        handleOverrideValue(envsArr[2], name, value);
                        break;
                    
                    default:
                        break;
                    }
                
                break;

            case "search":
                environment = params[0];

                switch (environment) {
                        case "DEV":
                            handleSearch(envsArr[0]);
                            break;

                        case "QA":
                            handleSearch(envsArr[1]);
                            break;

                        case "PROD":
                            handleSearch(envsArr[2]);
                            break;

                        case "BOOLEAN","STRING","NUMBER":

                            handleSearchByType(envsArr[0], environment);
                            handleSearchByType(envsArr[2], environment);
                            handleSearchByType(envsArr[1], environment);

                            break;
                        
                        default:

                            handleSearch(envsArr[0],environment);
                            handleSearch(envsArr[1],environment);
                            handleSearch(envsArr[2],environment);

                            break;
                    }
                
                break;
        
            case "export":

                if(params.length != 1){
                    System.err.println("Invalid parameters count, system will shutdown.");
                    System.exit(1);
                }
                String targetToExport = params[0];
                handleExportByEnv(envsArr,targetToExport);

                break;

             case "deleteparameter","removeparameter":

                if(params.length != 2){
                     System.err.println("Invalid parameters count.");
                }

                String targetToDelete = params[0];
                environment = params[1];

                try{
                    Long targetId = Long.parseLong(targetToDelete);
                    
                    switch (environment) {
                        case "DEV":
                            handleRemoveParameter(envsArr[0], targetId);
                            break;

                        case "QA":
                            handleRemoveParameter(envsArr[1], targetId);
                            break;

                        case "PROD":
                            handleRemoveParameter(envsArr[2], targetId);
                            break;
                    
                        default:
                            break;
                    }

                }catch(NumberFormatException e){
                    if(environment.equals("DEV"))handleRemoveParameter(envsArr[0], targetToDelete);
                    else if(environment.equals("QA"))handleRemoveParameter(envsArr[1], targetToDelete);
                    else if(environment.equals("PROD"))handleRemoveParameter(envsArr[2], targetToDelete);
                    else{
                        System.out.println("Environment \"" + environment + "\" does not exist.");
                    }
                }

                break;

            case "savetodb","savedb":
                try {
                    FileWriter fr = new FileWriter("db.txt");
                    
                    for(int i=0;i<envsArr.length;i++){
                        for(int j=0;j<envsArr[i].getParameters().size();j++){
                            fr.write(envsArr[i].getParameters().get(j).toString() + ", Environment: " + envsArr[i].getVersion() + ", parameter ID: " + envsArr[i].getParameters().get(j).getId() + "\n");
                        }
                    }
                    fr.close();
                } catch (Exception e) {
                    System.err.println("ERROR: " + e.getMessage());
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
        if(env.equals(envObj.getVersion()) && !envObj.doesExist(name,env)){
            newParam = new Parameter(newId,name,type,value,value,envObj.getVersion());
            envObj.addParameter(newParam);
        }
    }

    public static void handleUpdateParameter(Environment envObj,String name,String type, Object value, String env){
        envObj.findAndUpdateParameter(name, type, value, env);
    }

    public static void handleOverrideValue(Environment envObj, String name,Object value){
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
        System.out.println("\"" + envObj.getVersion() + "\" environment:");
        envObj.searchBySubName(subName);
        System.out.println("\n------------------------------------");
    }

    public static void handleSearchByType(Environment envObj,String type){
        System.out.println("\"" + envObj.getVersion() + "\" environment:");
        envObj.searchByType(type);
        System.out.println("\n------------------------------------");
    }
    
    public static void handleExportByEnv(Environment[] envsArr,String t){
        if(t.equals(envsArr[0].getVersion())){
            System.out.println(envsArr[0]);
        }else if(t.equals(envsArr[2].getVersion())){
            System.out.println(envsArr[2]);
        }else if(t.equals(envsArr[1].getVersion())){
            System.out.println(envsArr[1]);
        }else{
            System.out.println("\"" + t + "\" environment was not found.");
        }
        System.out.println();
    }
}
