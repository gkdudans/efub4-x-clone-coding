package efub.twitter.member.controller;

import efub.twitter.member.domain.Member;
import efub.twitter.member.dto.MemberResponseDto;
import efub.twitter.member.dto.MemberTweetResponseDto;
import efub.twitter.member.dto.SignUpRequestDto;
import efub.twitter.member.service.MemberService;
import efub.twitter.tweet.domain.Tweet;
import efub.twitter.tweet.service.TweetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final TweetService tweetService;

    /* 회원가입 기능 */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public MemberResponseDto signUp(@RequestBody @Valid final SignUpRequestDto requestDto){
        Long id = memberService.signUp(requestDto);
        Member findMember = memberService.findMemberById(id);
        return MemberResponseDto.from(findMember);
    }

    /* 사용자 정보 불러오기  */
    @GetMapping("/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public MemberResponseDto getMember(@PathVariable Long memberId){
        Member findMember = memberService.findMemberById(memberId);
        return MemberResponseDto.from(findMember);
    }

    /* 사용자 트윗 목록 조회하기  */
    @GetMapping("/{memberId}/tweets")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<MemberTweetResponseDto> getMemberTweetList(@PathVariable Long memberId){
        Member writer = memberService.findMemberById(memberId);
        List<Tweet> tweetList = tweetService.findMemberTweetList(writer);

        return ResponseEntity.status(HttpStatus.OK)
                .body(MemberTweetResponseDto.of(writer, tweetList));
    }
}
