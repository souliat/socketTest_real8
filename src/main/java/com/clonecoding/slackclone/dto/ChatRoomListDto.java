package com.clonecoding.slackclone.dto;

import com.clonecoding.slackclone.model.ChatRoom;
import com.clonecoding.slackclone.model.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ChatRoomListDto {

    private Long id;
    private String channel;
    private List<Member> memberList;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ChatRoomListDto(ChatRoom chatRoom, Member member) {
        this.id = chatRoom.getId();
        this.channel = chatRoom.getChannel();
        this.memberList = chatRoom.getMemberList();
        this.nickname = member.getNickname();
        this.createdAt = chatRoom.getCreatedAt();
        this.modifiedAt = chatRoom.getModifiedAt();
    }

}
