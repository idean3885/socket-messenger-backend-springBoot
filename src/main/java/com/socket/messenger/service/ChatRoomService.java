package com.socket.messenger.service;

import com.socket.messenger.dto.ChatRoomDto;
import com.socket.messenger.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class ChatRoomService {
    @Autowired
    ChatRoomRepository chatRoomRepository;

    public List<ChatRoomDto> findAllRooms(){
        //채팅방 생성 순서 최근 순으로 반환
        List<ChatRoomDto> result = chatRoomRepository.findAllRooms();
        Collections.reverse(result);

        return result;
    }

//    public ChatRoomDto findRoomById(String id){
//        return chatRoomDTOMap.get(id);
//    }

    public void createChatRoomDTO(String name){
        chatRoomRepository.createChatRoomDTO(name);
    }
}
