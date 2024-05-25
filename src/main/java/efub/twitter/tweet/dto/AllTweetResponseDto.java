package efub.twitter.tweet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AllTweetResponseDto {
    private List<TweetResponseDto> tweets;
    private long count;
}
