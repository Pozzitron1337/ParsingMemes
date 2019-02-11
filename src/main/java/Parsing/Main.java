package Parsing;

import Database.MemeDatabaseFromvkcom;

import java.util.Date;


public class Main {

    public static void main(String[] args) {

        Date t1=new Date();
        ParseMemes.ParseMemeFromvkcom();
        Date t2=new Date();
        System.out.println(t2.getTime()-t1.getTime());
    }
}
