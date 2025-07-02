package com.acad.auth.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserDto {
    private int id;
    private String userName;
    private String password;
    private String estado;
    private String cargo;
}
