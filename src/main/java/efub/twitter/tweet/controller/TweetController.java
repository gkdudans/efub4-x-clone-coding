package efub.twitter.tweet.controller;

import efub.twitter.tweet.domain.Tweet;
import efub.twitter.tweet.dto.AllTweetResponseDto;
import efub.twitter.tweet.dto.TweetRequestDto;
import efub.twitter.tweet.dto.TweetResponseDto;
import efub.twitter.tweet.service.TweetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tweets")
public class TweetController {
    private final TweetService tweetService;

    /* 트윗 작성 */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public TweetResponseDto createNewTweet(@RequestBody @Valid final TweetRequestDto dto){
        Tweet savedTweet = tweetService.createNewTweet(dto);
        return TweetResponseDto.of(savedTweet);
//        return TweetResponseDto.from(savedTweet,
//                savedTweet.getMember().getMemberId(),
//                savedTweet.getMember().getNickname(),
//                savedTweet.getMember().getId());
    }

    /* 전체 트윗 목록 보기 */
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public AllTweetResponseDto getAllTweets(){
        List<TweetResponseDto> list = new ArrayList<>();
        List<Tweet> tweets = tweetService.findAllPosts();
        for(Tweet tweet : tweets){
            TweetResponseDto dto = TweetResponseDto.of(tweet);
//            TweetResponseDto dto = TweetResponseDto.from(tweet,
//                    tweet.getMember().getMemberId(),
//                    tweet.getMember().getNickname(),
//                    tweet.getMember().getId());
            list.add(dto);
        }
        long count = tweetService.countAllPosts();
        return new AllTweetResponseDto(list, count);
    }

    /* 개별 트윗 보기 */
    @GetMapping("/{tweetId}")
    @ResponseStatus(value = HttpStatus.OK)
    public TweetResponseDto getTweet(@PathVariable Long tweetId){
        Tweet tweet = tweetService.findTweetById(tweetId);
        return TweetResponseDto.of(tweet);
//        return TweetResponseDto.from(tweet,
//                tweet.getMember().getMemberId(),
//                tweet.getMember().getNickname(),
//                tweet.getMember().getId());
    }

    /* 트윗 삭제 */
    @DeleteMapping("/{tweetId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteTweet(@PathVariable Long tweetId,
                              @RequestParam(name = "memberId") Long memberId){
        tweetService.deleteTweet(tweetId, memberId);
        return "트윗을 삭제했습니다.";
    }
}
