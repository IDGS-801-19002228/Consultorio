package com.example.consultorio.interfaces;

import com.example.consultorio.models.Cita;
import com.example.consultorio.models.Cliente;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ClientesSB {

  @GET("rest/clientes/getAll")
  Call<List<Cliente>> getAllClieentes();

  @GET("rest/citas/getAll")
  Call<List<Cita>> getAllCita();

  @FormUrlEncoded
  @POST("rest/clientes/save")
  Call<Cliente> getClientes(
          @Field("nombre") String nombre,
          @Field("apellidoPaterno") String apellidoPaterno,
          @Field("apellidoMaterno") String apellidoMaterno,
          @Field("fechaNacimiento") String fechaNacimiento,
          @Field("edad") int edad,
          @Field("genero") String genero,
          @Field("correo") String correo,
          @Field("contrasenia") String contrasenia
  );


  @FormUrlEncoded
  @POST("rest/citas/save2")
  Call<Cita> saveCita(
          @Field("idCliente_fk") int idCliente_fk,
          @Field("especialidad") String especialidad,
          @Field("tratamiento")String tratamiento,
          @Field("alergias")String alergias,
          @Field("numeroContacto") int numeroContacto,
          @Field("fechaCita")String fechaCita,
          @Field("horario")int horario

  );

}
