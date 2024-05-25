package efub.twitter.tweet.dto;

import efub.twitter.member.domain.Member;
import efub.twitter.tweet.domain.Tweet;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TweetRequestDto {
    @NotBlank(message = "계정 id는 필수입니다.")
    private String memberId;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    public Tweet toEntity(Member member){
        return Tweet.builder()
               .member(member)
               .content(this.content)
               .build();
    }
}
