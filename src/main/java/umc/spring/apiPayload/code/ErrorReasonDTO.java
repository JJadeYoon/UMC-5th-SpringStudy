package umc.spring.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@AllArgsConstructor
public class ErrorReasonDTO {

    private final String message;
    private final String code;
    private final Boolean isSuccess;
    private final HttpStatus httpStatus;
}
