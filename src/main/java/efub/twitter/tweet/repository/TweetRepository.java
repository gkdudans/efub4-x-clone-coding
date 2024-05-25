package efub.twitter.tweet.repository;

import efub.twitter.member.domain.Member;
import efub.twitter.tweet.domain.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
    boolean existsByTweetIdAndMember_MemberId(Long tweetId, Long memberId);
    List<Tweet> findAllByMember(Member writer);
}
