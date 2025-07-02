package com.example.ms_kajita.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class AuthUser {

    private Integer id;

    private String password;

    private String estado;

    private String cargo;

    @JsonProperty("userName")
    private String user;

    @Override
    public String toString() {
        return "UsuarioDto{" +
                "idUsuario=" + id +
                ", clave='" + password + '\'' +
                ", estado='" + estado + '\'' +
                ", cargo='" + cargo + '\'' +
                ", user='" + user + '\'' +
                '}';
    }

    public AuthUser() {
    }

    public AuthUser(Integer id, String clave, String estado, String user, String cargo) {
        this.id = id;
        this.password = clave;
        this.estado = estado;
        this.user = user;
        this.cargo = cargo;
    }

    public Integer getIdUsuario() {
        return id;
    }

    public void setIdUsuario(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}