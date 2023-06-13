package com.socket.messenger.util;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ImageUtil {

    public static BufferedImage base64Img(String imgSrc) {
        byte[] bytes = imgSrc.substring(imgSrc.indexOf(",") + 1).getBytes(StandardCharsets.UTF_8);
        byte[] image = Base64.getDecoder().decode(bytes);
        try {
            return ImageIO.read(new ByteArrayInputStream(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String imgBase64(BufferedImage image, String type) {
        String imageString = "";
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();
            imageString = Base64.getEncoder().encodeToString(imageBytes);
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }
}
