/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlparser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author tiendv
 */
public class XMLParser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException   {
        // TODO code application logic here
        
         File inputFile = new File("D:\\out4.xml");
         String outPutdirName = "D:\\text"; 
         DocumentBuilderFactory dbFactory 
            = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(inputFile);
         doc.getDocumentElement().normalize();
         NodeList nList = doc.getElementsByTagName("img");
         System.out.println("Total no of image : " + nList.getLength());
         for (int temp = 0; temp < nList.getLength(); temp++) {
            String text = "";
            Node nNode = nList.item(temp);
            Element eElement = (Element) nNode;
            String fileName = eElement.getAttribute("name");
           // fileName=fileName.replaceAll("/dataset/Datasets/vbs2016/net/dl380g7a/export/ddn11a2/ledduy/trecvid-avs/keyframe-5/tv2016/test.iacc.3/", "").replaceAll(".jpg", ".txt");
            fileName=fileName.replaceAll("/home/mmlabgpu3/vsd2016/net/dl380g7a/export/ddn11a2/ledduy/trecvid-avs/keyframe-5/tv2016/test.iacc.3/", "").replaceAll(".jpg", ".txt");
            String [] tempString = fileName.split("/");
            fileName=tempString[1];
            System.out.println(fileName);
            File dir = new File (outPutdirName);
            File file = new File(dir,fileName);
            NodeList nimageList = eElement.getElementsByTagName("txt");
            if (nimageList != null && nimageList.getLength() > 0)
            {
                System.out.println("number of text "+ nimageList.getLength());
                List<String> lscontent =  new ArrayList<String>();
                for(int i=0; i<nimageList.getLength();i++)
                {
                     Node tempN = nimageList.item(i);
                     Element eElementImage = (Element) tempN; 
                     lscontent.add(eElementImage.getAttribute("lexRecog"));
                     
                }
                StringBuilder sb = new StringBuilder();
                for (String str : lscontent)
                    sb.append(str).append(" ");  
                    text = sb.toString();
                
                System.out.println(text);
            }
            
            BufferedWriter output = null;
            try {
                output = new BufferedWriter(new FileWriter(file));
                output.write(text);
            } catch ( IOException e ) {
                e.printStackTrace();
            } finally {
              if ( output != null ) {
                output.close();
              }
            }
                
         }
    }

    
}


