package com.clonecoding.slackclone.model;

import com.clonecoding.slackclone.dto.ChatMessageRequestDto;
import com.clonecoding.slackclone.service.AuthService;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChatMessage {
    public enum MessageType {
        ENTER, TALK, QUIT
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column
    private MessageType type;
    @Column
    private Long roomId;
    // 이게 String roomId 였던 이유는 코드 처음 작성한 블로거(아빠개발자)가 DB 없이 해당 채팅방 구현 예시 만들다보니 Auto Increment 되는 고유한 구분 값이 없어서
    // roomId를 UUID 랜덤 문자열 값으로 지정했던 것이 그 이유인듯 하다. 어쨌든 해당 ChatMessage 중에 특정한 ChatRoom 에 해당되는 Messages들을
    // 가지고 오려면 Long 형이 더 적합할 듯하여 바꿈. 2022-06-23 10:01 AM
    // StompHandler, ChatMessageRequestDto, ChatMessage, ChatMessageRepository, ChatMessageService 쪽에서 바꿈.

    @Column
    private Long memberId;
    @Column
    private String sender;
    // 내용
    @Column
    private String message;
    @Column
    private String createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id_joined")
    private Member member;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "chatRoom_id_joined")
//    private ChatRoom chatRoom;

    @Builder
    public ChatMessage(MessageType type, Long roomId, Long memberId, String sender, String senderEmail, String senderImg, String message, String createdAt) {
        this.type = type;
        this.roomId = roomId;
        this.member = null;
        this.memberId = memberId;
        this.sender = sender;
        this.message = message;
        this.createdAt = createdAt;
    }

    @Builder
    public ChatMessage(ChatMessageRequestDto chatMessageRequestDto, AuthService authService) {
        this.type = chatMessageRequestDto.getType();
        this.roomId = chatMessageRequestDto.getRoomId();
//        this.user = authService.getMyInfo(chatMessageRequestDto.getMemberId());
//        this.member = authService.getMemberInfo();
        // authservice 확인좀....해봐야겠다... 편도랑 이야기함.
        this.memberId = chatMessageRequestDto.getMemberId();
        this.sender = chatMessageRequestDto.getSender();
        this.message = chatMessageRequestDto.getMessage();
        this.createdAt = chatMessageRequestDto.getCreatedAt();
    }

    @Builder
    public ChatMessage(ChatMessageRequestDto chatMessageRequestDto) {
        this.type = chatMessageRequestDto.getType();
        this.roomId = chatMessageRequestDto.getRoomId();
        this.member = null;
        this.memberId = chatMessageRequestDto.getMemberId();
        this.sender = chatMessageRequestDto.getSender();
        this.message = chatMessageRequestDto.getMessage();
        this.createdAt = chatMessageRequestDto.getCreatedAt();
    }

}
