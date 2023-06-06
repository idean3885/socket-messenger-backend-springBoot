package com.socket.messenger.dto;

import com.socket.messenger.enums.MessageType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "MessageDto", description = "소켓통신용 메시지 Dto")
@ToString
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MessageDto {

    @Schema(description = "메시지 종류")
    private MessageType messageType;

    @Schema(description = "메시지 내용")
    private String content;

    @Schema(description = "전송자")
    private String sender;

}
