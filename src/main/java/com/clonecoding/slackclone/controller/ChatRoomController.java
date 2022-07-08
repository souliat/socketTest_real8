package com.clonecoding.slackclone.controller;

import com.clonecoding.slackclone.dto.ChatRoomListDto;
import com.clonecoding.slackclone.dto.ChatRoomRequestDto;
import com.clonecoding.slackclone.dto.ChatRoomResponseDto;
import com.clonecoding.slackclone.model.ChatMessage;
import com.clonecoding.slackclone.model.ChatRoom;
import com.clonecoding.slackclone.model.Member;
import com.clonecoding.slackclone.service.AuthService;
import com.clonecoding.slackclone.service.ChatMessageService;
import com.clonecoding.slackclone.service.ChatRoomService;
import com.clonecoding.slackclone.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class ChatRoomController {

    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;
    private final AuthService authService;

//    // 프론트엔드 테스트용
//    @GetMapping("/channel")
//    public String rooms() {
//        return "/chat/room";
//    }
//
//    // 프론트엔드 테스트용
//    @GetMapping("/room/enter/{roomId}")
//    public String roomDetail(Model model, @PathVariable String roomId) {
//        model.addAttribute("roomId", roomId);
//        return "/chat/roomdetail";
//    }


    // 채팅방 생성
    @PostMapping("/channel")
    @ResponseBody
    public ChatRoomResponseDto createChatRoom(@RequestBody ChatRoomRequestDto requestDto) {
        log.info("채팅방 생성 requestDto = {}", requestDto);
//        requestDto.setMemberId(SecurityUtil.getCurrentMemberId());
        String useremail = SecurityUtil.getCurrentUsername();
        log.info("현재 유저의 이메일 = {}", useremail);

        ChatRoomResponseDto chatRoom = chatRoomService.createChatRoom(requestDto);

        return chatRoom;
    }


    // 전체 채팅방 목록 조회
    @GetMapping("/channels")
    @ResponseBody
    public List<ChatRoomListDto> getAllChatRooms() {
        Member member = authService.getMemberInfo();

        return chatRoomService.getAllChatRooms(member);
    }


    // 특정 채팅방 조회
    @GetMapping("/channel/{channelId}")
    @ResponseBody
    public ChatRoomResponseDto showChatRoom(@PathVariable Long channelId) {
        Member member = authService.getMemberInfo();
        return chatRoomService.showChatRoom(channelId, member);
    }

    //특정 채팅방 삭제
    @DeleteMapping("/channel/{channelId}")
    @ResponseBody
    public boolean deleteChatRoom(@PathVariable Long channelId){
        return chatRoomService.deleteChatRoom(channelId);
    }


    //채팅방 내 메시지 전체 조회
    @GetMapping("/channel/{channelId}/messages")
    @ResponseBody
    public List<ChatMessage> getRoomMessages(@PathVariable Long channelId) {
        return chatMessageService.getMessages(channelId);
    }

    // 채팅방 내 메시지 전체 조회(페이지, 무한스크롤 적용)
//    @GetMapping("/rooms/{roomId}/messages")
//    public Page<ChatMessage> getEachChatRoomMessages(@PathVariable String roomId, @PageableDefault Pageable pageable) {
//        return chatMessageService.getChatMessageByRoomId(roomId, pageable);
//    }



}
