/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package imagetomatrixconverter;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author julio
 */
public class Converter {
    
    private static String pathImage;
    private static String folderName;
    private static String fileName;
    
    private static final List<String> lines;
    
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
        if (Converter.pathImage.isBlank() || Converter.folderName.isBlank() ||Converter.fileName.isBlank()) {
            throw new Exception("Image and folder cannot be empty");
        }
        
        String fileFullName = Converter.folderName + "/" + Converter.fileName;
        
        Converter.auxConvert();
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileFullName))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
        
        Converter.pathImage = "";
        Converter.folderName = "";
        Converter.fileName = "";
        
        Converter.lines.clear();
    }
    
    private static void auxConvert() throws Exception {
        BufferedImage image = ImageIO.read(new FileInputStream(Converter.pathImage));
        
        int[] pixels = new int[image.getWidth() * image.getHeight()];
        
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
        
        for (int i = 0; i < image.getWidth(); i++) {
            String line = "{";

            for (int j = 0; j < image.getHeight(); j++) {
                int currentPixel = pixels[i + (j * image.getWidth())];
                
                line += "'" + currentPixel + "',";
            }
            
            line = line.substring(0, line.length() - 1);
            line += "},";
            
            Converter.lines.add(line);
        }
    }
    
}
