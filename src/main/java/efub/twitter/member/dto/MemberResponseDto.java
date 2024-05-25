package efub.twitter.member.dto;

import efub.twitter.member.domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberResponseDto {
    private Long memberId;
    private String nickname;
    private String id;
    private String email;
    private String bio;
    private LocalDateTime createdDate;

//    public MemberResponseDto(Long memberId, String nickname, String id, String email, String bio) {
//        this.memberId = memberId;
//        this.nickname = nickname;
//        this.id = id;
//        this.email = email;
//        this.bio = bio;
//    }

    public static MemberResponseDto from(Member member){
        return new MemberResponseDto(member.getMemberId(),
            member.getNickname(),
            member.getId(),
            member.getEmail(),
            member.getBio(),
            member.getCreatedDate());
    }

}
