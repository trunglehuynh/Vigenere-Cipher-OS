/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.assignment2.trunghuynh;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TrungHuynh
 */
public class OSAssignment2TrungHuynh {

    /**
     * @param args the command line arguments
     */
//    static char[] arrayChar;
//    static String key;
//    static char[] arrayKey;
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        int isEncode = 0;
        String filePath;
        String key;
        String data;
        String result;

        while (true) {
            try {
                System.out.println("Enter file's location (Ex: test.txt): ");
                filePath = reader.next();
                System.out.println("Enter key (Ex: fgh): ");
                key = reader.next();
                System.out.println("Enter 0 to encode or 1 to decode");
                isEncode = reader.nextInt();
                String[] arraySt = filePath.split("\\.");
                if (isEncode == 0) {
                    data = reader(filePath);
                    result = encodeOrDecode(true, data, key);
      
                    
                } else {

                    data = reader(filePath);
                    result = encodeOrDecode(false, data, key);


                }
                
                 writer(arraySt[0]+isEncode+"."+arraySt[1],result);
            } catch (Exception ex) {
                System.out.println("wrong input--------");
            }

            System.out.println("---------------------new-----------------------");
        }

    }

    private static String encodeOrDecode(boolean isEncode, String data, String key) {
        char[] arrayChar = data.toCharArray();
        char[] arrayKey = key.toCharArray();
        char a;
        String result = "";

        for (int i = 0; i < arrayChar.length; i++) {

            if (isEncode) {
                a = encodeChar(arrayChar[i], arrayKey[i % arrayKey.length]);
            } else {
                a = decodeChar(arrayChar[i], arrayKey[i % arrayKey.length]);
            }

            result = result + a;

        }
          System.out.println("result:");
        System.out.println(result);
        return result;
    }

    private static char decodeChar(char letter, char key) {

        if (!Character.isLetter(letter)) {
            return letter;
        }

        int starNum;
        int endNum;

        if (Character.isUpperCase(letter)) {

            starNum = 65;// A
            endNum = 90; //Z
            key = Character.toUpperCase(key);
        } else {
            starNum = 97;// a
            endNum = 122; //z
            key = Character.toLowerCase(key);
        }

        int numKey = (int) key;
        int shift = numKey - starNum;

        int newLetter = (int) letter - shift;
        if (newLetter < starNum) {
            newLetter = endNum - (starNum - newLetter);
        }
        return (char) newLetter;

    }

    private static char encodeChar(char letter, char key) {

        if (!Character.isLetter(letter)) {
            return letter;
        }

        int starNum;
        int endNum;

        if (Character.isUpperCase(letter)) {

            starNum = 65;// A
            endNum = 90; //Z
            key = Character.toUpperCase(key);
        } else {
            starNum = 97;// a
            endNum = 122; //z
            key = Character.toLowerCase(key);
        }

        int numKey = (int) key;
        int shift = numKey - starNum;

        int newLetter = (int) letter + shift;
        if (newLetter > endNum) {
            newLetter = newLetter - endNum + starNum;
        }
        return (char) newLetter;

    }

    private static String reader(String link) {

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(link));
            StringBuilder sb = new StringBuilder();
            String line;

            line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String result = sb.toString();
            br.close();
            return result;
        } catch (Exception e) {
            return null;

        }

    }

    private static void writer(String fileName, String data) {

        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileName), "utf-8"));
            writer.write(data);
        } catch (IOException ex) {
            // report
        } finally {
            System.out.println("results in new file: "+fileName);
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(OSAssignment2TrungHuynh.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
