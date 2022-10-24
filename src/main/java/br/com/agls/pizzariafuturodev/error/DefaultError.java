package br.com.agls.pizzariafuturodev.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DefaultError {

    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;
}
