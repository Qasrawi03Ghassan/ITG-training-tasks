package com.example;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Parameter {
    static final  String UK = "UNKNOWN"; 

    static LinkedList<Parameter> parametersList = new LinkedList<>();

    private Long id;
    private String name;
    private String type;
    private Object defaultValue;
    private String environment;
    private Object overriddenValue;
    private LocalDateTime lastUpdated;

    public Parameter(){
        id = 0L;
        name = UK;
        type = UK;
        environment = UK;
        defaultValue = new Object();
        overriddenValue = null;
        lastUpdated = LocalDateTime.now();

    }

    public Parameter(String name, String type, Object defaultValue, String environment){
        this.name = name;
        this.type = type;
        this.environment = environment;

        if(!(defaultValue instanceof Environment)){
            this.defaultValue = defaultValue;
            // if(overriddenValue == null){
            //     overriddenValue = defaultValue;
            // }
            overriddenValue = null;
        }else{
            defaultValue = UK;
        }
    }

    public Parameter(Long id,String name,String type, Object value, Object defaultValue, String env){
        this.id = id;
        this.name = name;
        this.type = type;
        this.environment = env;

        if(!(value instanceof Environment) && !(defaultValue instanceof Environment)){
            this.defaultValue = defaultValue;
            if(overriddenValue == null){
                overriddenValue = defaultValue;
            }else{
                overriddenValue = value;
            }
        }      
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public Object getOverriddenValue() {
        return overriddenValue;
    }

    public void setOverriddenValue(Object overriddenValue) {
        this.overriddenValue = overriddenValue;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public static void addParameter(Parameter p){
        parametersList.add(p);
    }

    @Override
    public String toString(){
        if(type.equals("STRING")){
            return String.format("{\"name\": \"%s\",\"type\": \"%s\",\"value\": \"%s\",\"defaultValue\": \"%s\"}",name,type,(overriddenValue == null?defaultValue:overriddenValue),defaultValue);
        }
        return String.format("{\"name\": \"%s\",\"type\": \"%s\",\"value\": %s,\"defaultValue\": %s}",name,type,(overriddenValue == null?defaultValue:overriddenValue),defaultValue);
    }    
}
