package efub.twitter.tweet.domain;

import efub.twitter.global.entity.BaseTimeEntity;
import efub.twitter.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tweet extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tweet_id", updatable = false)
    private Long tweetId;

    @ManyToOne
    @JoinColumn(name = "member_id", updatable = false)
    private Member member;

    @Column(nullable = false, length = 140)
    private String content;

    @Builder
    public Tweet(Member member, String content){
        this.member = member;
        this.content = content;
    }
}
