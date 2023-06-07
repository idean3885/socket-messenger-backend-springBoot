package com.socket.messenger.util;


import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ImageUtil {

    public static BufferedImage base64Img(String imgSrc) throws IOException {
        byte[] bytes = Base64.getDecoder().decode(imgSrc);
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes));
        return image;
    }

    public static String imgBase64(BufferedImage image, String type) throws IOException {
        String imageString = "";
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        ImageIO.write(image, type, bos);
        byte[] imageBytes = bos.toByteArray();

        imageString = Base64.getEncoder().encodeToString(imageBytes);

        bos.close();
        return imageString;
    }
}
