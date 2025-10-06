package com.example;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println();

        List<Parameter> pList = new LinkedList<>();
        Environment env1 = new Environment("PROD",pList);
       
        env1.addParameter(new Parameter(1L,"sms.limit","NUMBER",200,100));
        env1.addParameter(new Parameter(2L,"NEW","NUMBER",200,500));

        System.out.println(env1);
        
        env1.removeParameter(2L);

        System.out.println(env1);


    }
}