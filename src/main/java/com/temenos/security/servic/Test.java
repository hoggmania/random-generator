package com.temenos.security.servic;

public class Test {

    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder("Test printing");
        for (String s : args) {
            sb.append(" ");
            sb.append(s);
        }
        //System.out.println("Test = "+sb.toString());
     }



}