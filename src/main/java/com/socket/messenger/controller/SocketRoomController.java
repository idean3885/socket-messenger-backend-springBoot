package com.socket.messenger.controller;

import com.socket.messenger.dto.ChatRoomDto;
import com.socket.messenger.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/socket/messenger")
@RequiredArgsConstructor
public class SocketRoomController {
    @Autowired
    ChatRoomService chatRoomService;

    //채팅방 목록 조회
    @GetMapping(value = "/rooms")
    public List<ChatRoomDto> rooms(){
        return chatRoomService.findAllRooms();
    }

    //채팅방 개설
    @GetMapping(value = "/room")
    public void create(@RequestParam String name){
        log.info("# Create Chat Room , name: " + name);
        chatRoomService.createChatRoomDTO(name);
    }

//    //채팅방 조회
//    @GetMapping("/room")
//    public void getRoom(String roomId, Model model){
//
//        log.info("# get Chat Room, roomID : " + roomId);
//
//        model.addAttribute("room", chatRoomService.findRoomById(roomId));
//    }
}
