package com.example;

import java.util.Scanner;

public class Main {

    public static Environment env;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        env = new Environment();
        env.setVersion("DEV");

        env.addParameter(new Parameter("URLs.limit","NUMBER",600,env.getVersion()));

        System.out.println();
        System.out.println(env);
        System.out.println();

        String command = in.nextLine().replaceAll("\\s+", "");

        MainMethods.validateCommand(command,in);
        
        String operation[] = command.strip().split("\\(");
        String params[] = operation[1].split(",");
        
        MainMethods.interpretCommand(operation[0],params);
        System.out.println();

        //System.out.println(env);
    }
}