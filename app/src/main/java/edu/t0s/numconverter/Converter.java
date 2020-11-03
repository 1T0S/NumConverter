package edu.t0s.numconverter;
import android.widget.EditText;

import java.util.Random;

public class Converter {
    private static Random rnd = new Random();

    public static String getDec(){
        return Integer.toString(rnd.nextInt(512));
    }

    public static String getBin(){
        return Integer.toBinaryString(rnd.nextInt(512));
    }

    public static String getHex(){
        return Integer.toHexString(rnd.nextInt(512));
    }

    public static int hexToDec(String hex){
        try{
            return Integer.parseInt(hex, 16);
        } catch(Exception e){
            return -1;
        }
    }

    public static int binToDec(String bin){
        try{
            return Integer.parseInt(bin, 2);
        } catch(Exception e){
            return -1;
        }
    }

    public static int getInt(String s){
        try{
            return Integer.parseInt(s);
        } catch(Exception e){
            return -1;
        }
    }
}
