package efub.twitter.member.dto;

import efub.twitter.member.domain.Member;
import efub.twitter.member.domain.MemberStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequestDto {

    @NotBlank(message = "닉네임은 필수입니다. ") // 해당 값이 null이 아니고, 공백(""과 " " 모두 포함)이 아닌지 검증
    private String nickname;

    @NotBlank(message = "아이디는 필수입니다. ") // 해당 값이 null이 아니고, 공백(""과 " " 모두 포함)이 아닌지 검증
    private String id;

    @NotBlank(message = "이메일은 필수입니다.")  // 해당 값이 null이 아니고, 공백(""과 " " 모두 포함)이 아닌지 검증
    @Email(message = "유효하지 않은 이메일 형식입니다.",
            regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.") // 해당 값이 null이 아니고, 공백(""과 " " 모두 포함)이 아닌지 검증
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!.?,])[A-Za-z\\d!.?,]{2,16}$",
            message = "16자 이내의 영문자 및 숫자와 ?,!,., , 특수문자로 입력해주세요.")
    private String password;

    private String bio;


    public Member toEntity() {
        return Member.builder()
                .nickname(this.nickname)
                .id(this.id)
                .email(this.email)
                .password(this.password)
                .bio(this.bio)
                .build();
    }
}
