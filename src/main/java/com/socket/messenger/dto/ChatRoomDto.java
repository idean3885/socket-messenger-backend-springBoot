package com.socket.messenger.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Schema(name = "chatRoomDto", description = "채팅방 Dto")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChatRoomDto {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    public static ChatRoomDto create(String name){
        ChatRoomDto room = new ChatRoomDto();

        room.roomId = UUID.randomUUID().toString();
        room.name = name;
        return room;
    }
}
