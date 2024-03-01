/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package imagetomatrixconverter;

/**
 *
 * @author julio
 */
public class Converter {
    
    private static String pathImage;
    private static String pathMatriz;
    
    static {
        pathImage = "";
        pathMatriz = "";
    }
    
    public static void setPathIamge(String path) {
        Converter.pathImage = path;
    }
    
    public static void setPathMatriz(String path) {
        Converter.pathMatriz = path;
    }
    
    public static void convert() {
        System.out.println(String.format("image: %s\nmatriz: %s", Converter.pathImage, Converter.pathMatriz));
    }
    
}
