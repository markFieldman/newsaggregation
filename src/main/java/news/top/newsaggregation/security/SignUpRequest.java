package news.top.newsaggregation.security;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SignUpRequest {

    public SignUpRequest(){}

    @NotBlank
    @NonNull
    private String name;

    @NotBlank
    @Email
    @NonNull
    private String email;

    @NotBlank
    @NonNull
    private String password;

    public SignUpRequest(@NotBlank String name, @NotBlank @Email String email, @NotBlank String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}