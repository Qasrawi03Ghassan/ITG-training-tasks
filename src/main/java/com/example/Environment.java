package com.example;

import java.util.LinkedList;
import java.util.List;

public class Environment {
    private List<Parameter> parameters;
    private String version;

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

    public String toString(){
        return String.format("{environment: \"%s\",\"parameters\": %s }",version,parameters);
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

  
}
