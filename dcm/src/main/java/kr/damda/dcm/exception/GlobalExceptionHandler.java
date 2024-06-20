package kr.damda.dcm.exception;

import java.util.HashMap;
import java.util.Map;
import kr.damda.dcm.dto.response.component.ResultResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, ResultResponseDto>> handleNotFoundException(
        NotFoundException ex) {
        ResultResponseDto errorResponse = new ResultResponseDto();
        errorResponse.setCode(603);
        errorResponse.setDescription("already registed device");

        Map<String, ResultResponseDto> response = new HashMap<>();
        response.put("result", errorResponse);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
