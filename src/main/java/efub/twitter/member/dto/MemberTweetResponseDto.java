package efub.twitter.member.dto;

import efub.twitter.member.domain.Member;
import efub.twitter.tweet.domain.Tweet;
import efub.twitter.tweet.dto.TweetResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberTweetResponseDto {
    private String writerNickname;
    private List<TweetResponseDto> memberTweetList;
    private Long count;

    public static MemberTweetResponseDto of(Member member, List<Tweet> tweetList) {
        return MemberTweetResponseDto.builder()
                .writerNickname(member.getNickname())
                .memberTweetList(tweetList.stream().map(TweetResponseDto::of).collect(Collectors.toList()))
                .count((long) tweetList.size())
                .build();
    }
}
