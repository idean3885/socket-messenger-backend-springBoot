package com.socket.messenger.repository;

import com.socket.messenger.dto.ChatRoomDto;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ChatRoomRepository {

    private final Map<String, ChatRoomDto> chatRoomDTOMap;

    public ChatRoomRepository() {
        this.chatRoomDTOMap = new LinkedHashMap<>();
    }
    public List<ChatRoomDto> findAllRooms(){
        //채팅방 생성 순서 최근 순으로 반환
        List<ChatRoomDto> result = new ArrayList<>(chatRoomDTOMap.values());
        return result;
    }

//    public ChatRoomDto findRoomById(String id){
//        return chatRoomDTOMap.get(id);
//    }

    public void createChatRoomDTO(String name){
        ChatRoomDto room = ChatRoomDto.create(name);
        chatRoomDTOMap.put(room.getRoomId(), room);
    }

}
