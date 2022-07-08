package com.clonecoding.slackclone.model;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column
    private String useremail;
    @Column
    private String password;
    @Column
    private String nickname;
    @Column
    @Enumerated(EnumType.STRING)
    private UserRole authority;

    @Builder
    public Member(String useremail, String password, UserRole authority, String nickname) {
        this.useremail = useremail;
        this.password = password;
        this.authority = authority;
        this.nickname = nickname;
    }
}

