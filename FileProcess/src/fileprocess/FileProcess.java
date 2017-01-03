/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileprocess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TienDV
 */
public class FileProcess {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String path="D:\\net\\dl380g7a\\export\\ddn11a2\\ledduy\\trecvid-avs\\keyframe-5\\tv2016\\test.iacc.3\\TRECVID2016_35345";
        listFile(path);
        // TODO code application logic here
    }
    public static void listFile(String pathname)   {
    File f = new File(pathname);
    File[] listfiles = f.listFiles();
    for (int i = 0; i < listfiles.length; i++) {
        if (listfiles[i].isDirectory()) {
            File[] internalFile = listfiles[i].listFiles();
            for (int j = 0; j < internalFile.length; j++) {
                System.out.println(internalFile[j]);
                if (internalFile[j].isDirectory()) {
                    String name = internalFile[j].getAbsolutePath();
                    listFile(name);
                }

            }
        } else {
            System.out.println(listfiles[i]);
            
        }

    }

    PrintWriter pr = null;    
        try {
            pr = new PrintWriter("D:\\out.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileProcess.class.getName()).log(Level.SEVERE, null, ex);
        }

    for (int i=0; i<listfiles.length ; i++)
    {
        pr.println(listfiles[i]);
    }
    pr.close();
   
    

}
    
}
