package com.example.ms_kajita.dto;


public class UsuarioEstadoStatsDto {
    private Long usuarioId;
    private String usuarioNombre;
    private long cantidad;     // número de asistencias con ese estado

    public UsuarioEstadoStatsDto() {}

    public UsuarioEstadoStatsDto(Long usuarioId, String usuarioNombre, long cantidad) {
        this.usuarioId = usuarioId;
        this.usuarioNombre = usuarioNombre;
        this.cantidad = cantidad;
    }

    // getters y setters
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public String getUsuarioNombre() { return usuarioNombre; }
    public void setUsuarioNombre(String usuarioNombre) { this.usuarioNombre = usuarioNombre; }

    public long getCantidad() { return cantidad; }
    public void setCantidad(long cantidad) { this.cantidad = cantidad; }
}
