package com.socket.messenger.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "SocketVo", description = "소켓통신용 메시지 Dto")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SocketVo {

    @Schema(description = "닉네임")
    private String nickname;

    @Schema(description = "전달할 내용")
    private String content;

}
