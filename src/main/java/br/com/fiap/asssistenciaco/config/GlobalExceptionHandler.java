package br.com.fiap.asssistenciaco.config;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private record ErrorResponse(String error, LocalDateTime dateTime){};

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse handleRuntimeException(RuntimeException ex) {
        return new ErrorResponse(ex.getMessage(), LocalDateTime.now());
    }

    @ResponseStatus
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> resultado = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(e->{
            var nomeCampo = ((FieldError)e).getField();
            var mensagem = e.getDefaultMessage();
            resultado.put(nomeCampo, mensagem);
        });
        return resultado;
    }
}
