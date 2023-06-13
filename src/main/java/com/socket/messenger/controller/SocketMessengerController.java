package com.socket.messenger.controller;

import com.socket.messenger.dto.ReqReceiveDto;
import com.socket.messenger.dto.ResReceiveDto;
import com.socket.messenger.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.joda.time.LocalDateTime;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class SocketMessengerController {

    private static final Map<String, String> idColorMap = new HashMap<>();

    /**
     * <h3>메시지 전송</h3>
     *
     * @param reqReceiveDto {@link ReqReceiveDto 메시지 요청 Dto}
     * @param headerAccessor {@link SimpMessageHeaderAccessor Stomp 메시지 헤더}
     * @return {@link ResReceiveDto 메시지 응답 Dto}
     */
    @MessageMapping("/socket/messenger/receive")
    @SendTo("/socket/messenger/send")
    public ResReceiveDto messageReceiveSend(@Payload ReqReceiveDto reqReceiveDto, SimpMessageHeaderAccessor headerAccessor) {
        var simpSessionId = String.valueOf(headerAccessor.getHeader("simpSessionId"));
        return ResReceiveDto.builder()
                .responseTime(LocalDateTime.now().toString("yy.MM.dd HH:mm:ss.SSS"))
                .simpSessionId(simpSessionId)
                .nickname(reqReceiveDto.getNickname())
                .nicknameColor(getColorById(simpSessionId))
                .contents(reqReceiveDto.getContents())
                .recvImgSrcList(getResizeImgSrcList(reqReceiveDto.getSendImgSrcList()))
                .build();
    }

    private String getColorById(String simpSessionId) {
        if (idColorMap.containsKey(simpSessionId)) {
            return idColorMap.get(simpSessionId);
        }
        // A값 0.7 로 임의 고정(끝자리 b3)
        var colorCode = String.format("#%06xb3", new Random(System.currentTimeMillis()).nextInt(0xffffff + 1));
        idColorMap.put(simpSessionId, colorCode);
        return colorCode;
    }

    private List<String> getResizeImgSrcList(List<String> imgSrcList) {
        return imgSrcList.stream().map(imgSrc -> {
            var bufferedImage = ImageUtil.base64Img(imgSrc);
            var reWidth = 400;
            var reHeight = 0;
            if (bufferedImage != null && bufferedImage.getWidth() > reWidth) {
                reHeight = (int) (400.0 / bufferedImage.getWidth() * bufferedImage.getHeight());
            } else {
                reWidth = bufferedImage.getWidth();
                reHeight = bufferedImage.getHeight();
            }
            var type = imgSrc.substring(imgSrc.indexOf("/") + 1, imgSrc.indexOf(";"));
            // TODO: 리사이징 로직
            return ImageUtil.imgBase64(bufferedImage, type);
        }).collect(Collectors.toList());
    }

}
