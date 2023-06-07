package com.socket.messenger.controller;

import com.socket.messenger.dto.SocketVo;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessengerController {

    @MessageMapping("/receive")
    @SendTo("/send")
    public SocketVo SocketHandler(SocketVo socketVo) {
        return socketVo;
    }

}
