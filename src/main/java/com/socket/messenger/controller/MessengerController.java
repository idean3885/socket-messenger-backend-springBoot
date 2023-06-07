package com.socket.messenger.controller;

import com.socket.messenger.dto.SocketVo;
import com.socket.messenger.util.ImageUtil;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class MessengerController {

    @MessageMapping("/receive")
    @SendTo("/send")
    public SocketVo SocketHandler(SocketVo socketVo) throws IOException {
        String recvImgSrc = "";
        if (!"".equals(socketVo.getSendImgSrc())) {
            recvImgSrc = imgSrc(socketVo.getSendImgSrc());
        }
        return SocketVo.builder().sendImgSrc(socketVo.getSendImgSrc()).build();
    }

    public String imgSrc(String imgSrc) throws IOException {
        BufferedImage bufferedImage = ImageUtil.base64Img(imgSrc);
        String stringImage = ImageUtil.imgBase64(bufferedImage, "png");
        return stringImage;
    }
}
