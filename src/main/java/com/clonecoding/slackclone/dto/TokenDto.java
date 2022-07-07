package com.clonecoding.slackclone.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDto {

    private String useremail;
    private String nickname;
    private Long memberId;  // 2022-07-06 추가. 편도랑.
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiresIn;
}