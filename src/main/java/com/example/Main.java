package com.example;

public class Main {
    public static void main(String[] args) {
       for(int i=0;i<Parameter.parametersList.size();i++){
        System.out.println(Parameter.parametersList.get(i));
       }

       Parameter x = new Parameter();
       Parameter.addParameter(x);
       
        for(int i=0;i<Parameter.parametersList.size();i++){
        System.out.println(Parameter.parametersList.get(i));
       }
    }
}