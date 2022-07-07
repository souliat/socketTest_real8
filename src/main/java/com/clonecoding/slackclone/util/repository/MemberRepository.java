package com.clonecoding.slackclone.util.repository;

import com.clonecoding.slackclone.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUseremail(String useremail);
    boolean existsByUseremail(String useremail);

}
