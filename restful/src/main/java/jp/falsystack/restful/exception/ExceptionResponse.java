package jp.falsystack.restful.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor(staticName = "of")
public class ExceptionResponse {
    private LocalDate timestamp;
    private String message;
    private String details;
}
