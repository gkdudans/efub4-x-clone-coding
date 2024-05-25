package efub.twitter.member.domain;

import efub.twitter.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import static efub.twitter.member.domain.MemberStatus.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", updatable = false)
    private Long memberId;

    @Column(nullable = false, length = 50)
    private String nickname;

    @Column(nullable = false, length = 50)
    private String id;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false)
    private String password;

    private String bio;

    @Enumerated(EnumType.STRING) // enum 타입
    private MemberStatus status;

    @Builder
    public Member(String nickname, String id, String email, String password, String bio){
        this.nickname = nickname;
        this.id = id;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.status = MemberStatus.REGISTERED;
    }

    public void withdrawAccount(){
        this.status= UNREGISTERED;
    }

}
