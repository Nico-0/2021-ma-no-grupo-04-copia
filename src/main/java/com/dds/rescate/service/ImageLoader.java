package com.dds.rescate.service;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageLoader {

    static int anchoGlobal = 200;
    static int altoGlobal = 150;
    static String PATH = "src\\main\\resources\\";

    public static void resizeImage(String nombre) throws IOException{
        new File(PATH + "Imagenes").mkdirs();

        File originalImage = new File(PATH + "ImgTemp\\" + nombre);
        File resizedImage = new File(PATH + "Imagenes\\" + nombre);

            BufferedImage original = ImageIO.read(originalImage);
            BufferedImage resized = new BufferedImage(anchoGlobal, altoGlobal, original.getType());
            Graphics2D g2 = resized.createGraphics();
            g2.drawImage(original, 0, 0, anchoGlobal, altoGlobal, null);
            g2.dispose();
            ImageIO.write(resized, "jpg", resizedImage);

    }
}
