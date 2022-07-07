package com.clonecoding.slackclone.dto;

import com.clonecoding.slackclone.model.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    private String useremail;

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getUseremail());
    }
}
