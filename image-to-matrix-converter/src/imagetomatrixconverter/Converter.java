/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package imagetomatrixconverter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author julio
 */
public class Converter {
    
    private static String pathImage;
    private static String folderName;
    private static String fileName;
    
    private static List<String> lines;
    
    static {
        pathImage = "";
        folderName = "";
        fileName = "";
        lines = new ArrayList<>();
    }
    
    public static void setPathIamge(String path) {
        Converter.pathImage = path;
    }
    
    public static void setPathMatriz(String folderName, String fileName) {
        Converter.folderName = folderName;
        Converter.fileName = fileName;
    }
    
    public static void convert() throws Exception {
        String fileFullName = Converter.folderName + "/" + Converter.fileName;
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileFullName))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
    
}
