package kr.damda.dcm.exception;

import java.util.HashMap;
import java.util.Map;
import kr.damda.dcm.dto.response.svc.component.ResultResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyRegistedDeviceException.class)
    public ResponseEntity<Map<String, ResultResponseDto>> handleNotFoundException(
        NotFoundException ex) {
        ResultResponseDto errorResponse = new ResultResponseDto();
        errorResponse.setCode(603);
        errorResponse.setDescription("이미 등록된 디바이스");

        Map<String, ResultResponseDto> response = new HashMap<>();
        response.put("result", errorResponse);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
