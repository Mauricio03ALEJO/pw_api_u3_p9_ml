package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.application.AsignaturaService;
import uce.edu.web.api.matricula.domain.Asignatura;

@Path("/asignaturas")
public class AsignaturaResource {

    @Inject
    private AsignaturaService asignaturaService;

    @GET
    @Path("")
    @jakarta.ws.rs.Produces(MediaType.APPLICATION_JSON)
    public List<Asignatura> listarTodas() {
        return this.asignaturaService.listarTodas();
    }

    @GET
    @Path("/{id}")
    @jakarta.ws.rs.Produces(MediaType.APPLICATION_XML)
    public Asignatura consultarPorId(@PathParam("id") Integer id) {
        return this.asignaturaService.consultarPorId(id);
    }

    @GET
    @Path("/codigo/{codigo}")
    public Asignatura consultarPorCodigo(@PathParam("codigo") String codigo) {
        return this.asignaturaService.consultarPorCodigo(codigo);
    }

    @GET
    @Path("/nombre")
    public List<Asignatura> consultarPorNombre(@QueryParam("nombre") String nombre) {
        return this.asignaturaService.consultarPorNombre(nombre);
    }

    @GET
    @Path("/nivel/{nivel}")
    public List<Asignatura> consultarPorNivel(@PathParam("nivel") Integer nivel) {
        return this.asignaturaService.consultarPorNivel(nivel);
    }

    @POST
    @Path("")
    public Response guardar(Asignatura asignatura) {
        this.asignaturaService.crear(asignatura);
        return Response.status(Response.Status.CREATED).entity(asignatura).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Integer id, Asignatura asignatura) {
        this.asignaturaService.actualizar(id, asignatura);
        return Response.status(209).entity(null).build();
    }

    @PATCH
    @Path("/{id}")
    public void actualizarParcial(@PathParam("id") Integer id, Asignatura asignatura) {
        this.asignaturaService.actualizarParcial(id, asignatura);
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Integer id) {
        this.asignaturaService.eliminar(id);
    }

    @DELETE
    @Path("/codigo/{codigo}")
    public Long eliminarPorCodigo(@PathParam("codigo") String codigo) {
        return this.asignaturaService.eliminarPorCodigo(codigo);
    }
}
