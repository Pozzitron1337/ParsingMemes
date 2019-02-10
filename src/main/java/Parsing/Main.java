package Parsing;

import Database.MemeDatabaseFromvkcom;


public class Main {

    public static void main(String[] args) {


       ParseMemes.ParseMemeFromvkcom();
        /*MemeDatabaseFromvkcom vk=new MemeDatabaseFromvkcom("jdbc:mysql://localhost:3306/memes?useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false","root","1234");
        vk.addMeme(3,"sda","dasd");*/
    }
}
