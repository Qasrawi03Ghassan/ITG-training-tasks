package com.example;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Parameter {
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
        name = "UNKNOWN";
        type = "UNKNOWN";
        environment = "Undefined";
        defaultValue = new Object();
        overriddenValue = null;
        lastUpdated = LocalDateTime.now();

    }

    public Parameter(Long id,String name,String type, Object value, Object defaultValue){
        this.id = id;
        this.name = name;
        this.type = type;
        this.overriddenValue = value;
        this.defaultValue = defaultValue;
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
        return String.format("[{\"name\": \"%s\",\"type\": \"%s\",\"value: %s\",\"defaultValue: %s\"}]",name,type,overriddenValue,defaultValue);
    }    
}
