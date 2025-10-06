package com.example;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Environment {
    private List<Parameter> parameters;
    private String version;

    public Environment(){
        parameters = new LinkedList<>();
        version = "UNKNOWN";
    }

    public Environment(String version, List<Parameter> pList){
        this.version = version;
        this.parameters = pList;
    }

    public String toString(){
        return String.format("{environment: \"%s\",\"parameters\": %s }",version,parameters);
    }

    public String getParameters(Environment env){
        for(int i=0;i<env.parameters.size();i++){

        }
        return "";
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

  
}
