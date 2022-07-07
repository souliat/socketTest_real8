package com.clonecoding.slackclone.dto;

import com.clonecoding.slackclone.model.ChatRoom;
import com.clonecoding.slackclone.model.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@ToString
@Getter
@Setter
public class ChatRoomResponseDto {

    private Long id;
    private String channel;
    private Member member;


    public ChatRoomResponseDto(ChatRoom chatRoom, Member member) {
        this.id = chatRoom.getId();
        this.channel = chatRoom.getChannel();
        this.member = member;
    }
}