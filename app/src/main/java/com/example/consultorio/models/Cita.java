package com.example.consultorio.models;

public class Cita {

    int idCita;
    int idCliente_fk;
    String especialidad;
    String tratamiento;
    String alergias;
    int numeroContacto;
    String fechaCita;
    int horario;
    int estatus;
    //Cliente cliente;


    public Cita() {

    }

    public Cita(String especialidad, String tratamiento, String alergias, int numeroContacto, String fechaCita, int horario) {
        this.especialidad = especialidad;
        this.tratamiento = tratamiento;
        this.alergias = alergias;
        this.numeroContacto = numeroContacto;
        this.fechaCita = fechaCita;
        this.horario = horario;
    }


    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }


    public int getIdCliente_fk() {
        return idCliente_fk;
    }

    public void setIdCliente_fk(int idCliente_fk) {
        this.idCliente_fk = idCliente_fk;
    }


    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }


    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }


    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }


    public int getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(int numeroContacto) {
        this.numeroContacto = numeroContacto;
    }


    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }


    public int getHorario() {
        return horario;
    }

    public void setHorario(int horario) {
        this.horario = horario;
    }


    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }


   /* public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }*/

}
