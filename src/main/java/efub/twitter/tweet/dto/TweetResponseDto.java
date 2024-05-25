package efub.twitter.tweet.dto;

import efub.twitter.tweet.domain.Tweet;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class TweetResponseDto {
    private Long tweetId;
    private Long memberId;
    private String nickname;
    private String id;
    private String content;
    private LocalDateTime createdDate;

    public static TweetResponseDto of(Tweet tweet) {
        return TweetResponseDto.builder()
                .tweetId(tweet.getTweetId())
                .memberId(tweet.getMember().getMemberId())
                .nickname(tweet.getMember().getNickname())
                .id(tweet.getMember().getId())
                .content(tweet.getContent())
                .createdDate(tweet.getCreatedDate())
                .build();
    }
}
