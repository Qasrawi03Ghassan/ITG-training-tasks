package com.example;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Environment {
    private List<Parameter> parameters;
    private String version;

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Environment(){
        parameters = new LinkedList<>();
        version = "UNKNOWN";
    }

    public Environment(String version){
        parameters = new LinkedList<>();
        this.version = version;
    }

    public Environment(String version, List<Parameter> pList){
        this.version = version;
        this.parameters = pList;
    }

    @Override
    public String toString(){
        return String.format("{\"environment\": \"%s\",\"parameters\": %s }",version,parameters);
    }

    public void addParameter(Parameter p){
        parameters.add(p);
    }

    public void removeParameter(Long id){
       for(int i=0;i<parameters.size();i++){
            if(parameters.get(i).getId().equals(id)){
                parameters.remove(parameters.get(i));
            }
       }       
    }

    public void removeParameter(String name){
       for(int i=0;i<parameters.size();i++){
            if(parameters.get(i).getName().equals(name)){
                parameters.remove(parameters.get(i));
            }
       }       
    }

    public void findAndUpdateParameter(String name,String type, Object value, String env){
        try {
            int found = 0;
            for(Parameter p : parameters){
                if(p.getName().equals(name)){
                    found = 1;

                    FileWriter fr = new FileWriter("changesLogger.txt",true);
                    p.setLastUpdated(LocalDateTime.now());
                    fr.write(p + ",environment:" + p.getEnvironment() + " => " + p.getLastUpdated() + "\n");
                    fr.close();

                    p.setType(type);
                    p.setEnvironment(env);
                    p.setOverriddenValue(value);
                    p.setDefaultValue(value);
                }else{
                    found = 0;
                }
            }
            if(found == 0){
                System.out.println("\"" + name + "\" does not exist in \"" + getVersion() + "\" environment.");
            }   
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void findAndOverrideValue(String name,Object value){
        try {
            int found = 0;
            for(Parameter p : parameters){
                if(p.getName().equals(name)){
                    found = 1;

                    if(!MainMethods.checkValueType(p.getType(), value)){
                        return;
                    }

                    FileWriter fr = new FileWriter("changesLogger.txt",true);
                    p.setLastUpdated(LocalDateTime.now());
                    fr.write(p + ",environment:" + p.getEnvironment() + " => " + p.getLastUpdated() + "\n");
                    fr.close();


                    p.setOverriddenValue(value);
                }else{
                    found = 0;
                }
            }
            if(found == 0){
                System.out.println("\"" + name + "\" does not exist in \"" + getVersion() + "\" environment.");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void searchByEnv(){
        int found = 0;
        for(Parameter p:parameters){
            if(p.getEnvironment().equals(this.getVersion())){
                found = 1;
                System.out.print(p + " ");
            }else{
                found = 0;
            }
        }
        if(found == 0){
            System.out.println("No parameters found in \"" + getVersion() + "\" environment.");
        }
        System.out.println();
    }
    public void searchBySubName(String subName){
        int found = 0;
        for(Parameter p : parameters){
            if(p.getName().toLowerCase().contains(subName.toLowerCase())){
                found = 1;
                System.out.print(p + " ");
            }else{
                found = 0;
            }
        }
        if(found == 0){
            System.out.println("Could not find any parameters with subname \"" + subName + "\" in \"" + getVersion() + "\" environment.");
        }else{
            System.out.println();
        }
    }
    public void searchByType(String type){
        type = type.toUpperCase();
        int found = 0;
        for(Parameter p : parameters){
            if(p.getType().equalsIgnoreCase(type)){
                found = 1;
                System.out.print(p + " ");
            }else{
                found = 0;
            }
        }
        if(found == 0){
            System.out.println("Could not find any parameters with type \"" + type + "\" in \"" + getVersion() + "\" environment.\n");
        }else{
            System.out.println();
        }
    }

  
}
