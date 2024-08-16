package com.spring.dto;

import com.spring.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDTO {
    private String message;
    private String status;
    private Integer id;
    private User user;
    public ResponseDTO(String message) {
        this.message = message;
    }

    public ResponseDTO(String message, String status, Integer id) {
        this.message = message;
        this.status = status;
        this.id = id;
    }
}
