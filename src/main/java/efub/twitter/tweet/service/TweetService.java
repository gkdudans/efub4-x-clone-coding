package efub.twitter.tweet.service;

import efub.twitter.exception.CustomDeleteException;
import efub.twitter.member.domain.Member;
import efub.twitter.member.service.MemberService;
import efub.twitter.tweet.domain.Tweet;
import efub.twitter.tweet.dto.TweetRequestDto;
import efub.twitter.tweet.repository.TweetRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static efub.twitter.exception.ErrorCode.PERMISSION_REJECTED_USER;

@Service
@Transactional
@RequiredArgsConstructor
public class TweetService {
    private final TweetRepository tweetRepository;
    private final MemberService memberService;

    public Tweet createNewTweet(TweetRequestDto dto) {
        Member member = memberService.findMemberById(Long.valueOf(dto.getMemberId()));
        Tweet tweet = dto.toEntity(member);
        Tweet savedTweet = tweetRepository.save(tweet);
        return savedTweet;
    }

    @Transactional(readOnly = true)
    public List<Tweet> findAllPosts() {
        List<Tweet> tweets = tweetRepository.findAll();
        return tweets;
    }
    @Transactional(readOnly = true)
    public long countAllPosts() {
        return tweetRepository.count();
    }

    @Transactional(readOnly = true)
    public Tweet findTweetById(Long tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(()->new EntityNotFoundException("해당 id를 가진 Tweet를 찾을 수 없습니다. id="+tweetId));
        return tweet;
    }

    public void deleteTweet(Long tweetId, Long memberId) {
        Tweet tweet = findTweetById(tweetId);
        if(!tweetRepository.existsByTweetIdAndMember_MemberId(tweetId, memberId)){
            throw new CustomDeleteException(PERMISSION_REJECTED_USER);
        }
        tweetRepository.delete(tweet);
    }

    public List<Tweet> findMemberTweetList(Member writer) {
        return tweetRepository.findAllByMember(writer);
    }
}
