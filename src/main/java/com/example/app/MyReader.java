package com.example.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyReader {
    /** created for help for testing. Reads the line from console. */
    public String getLine()  {
        System.out.println("\n*** Please list the tags you are interested in(divided by the comma without spaces):");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = reader.readLine();
            return line;
        } catch (IOException e) { System.out.println("WARNING! IOException!"); }
        return "";
    }
}
