/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileprocess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TienDV
 */
public class NewClass {
    
     public static void main(String[] args) {
        //
        // Create an instance of File for data.txt file.
        //
        File file = new File("D:\\name.txt");

         List<String> list = new ArrayList<>();
        try {
            //
            // Create a new Scanner object which will read the data from the 
            // file passed in. To check if there are more line to read from it
            // we check by calling the scanner.hasNextLine() method. We then
            // read line one by one till all line is read.
            //
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
                String[] splited = line.split("\\s+");
                list.add(splited[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
          PrintWriter pr = null;    
        try {
            pr = new PrintWriter("D:\\out.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileProcess.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i=0; i<list.size() ; i++)
        {
            pr.println(list.get(i));
        }
        pr.close();

    }
    
}
