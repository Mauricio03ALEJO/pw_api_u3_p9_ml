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
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.matricula.application.AsignaturaService;
import uce.edu.web.api.matricula.application.representation.AsignaturaRepresentation;
import uce.edu.web.api.matricula.application.representation.LinkDto;

@Path("/asignaturas")
public class AsignaturaResource {

    @Inject
    private AsignaturaService asignaturaService;

    @Context
    private UriInfo uriInfo;

    @GET
    @Path("")
    @jakarta.ws.rs.Produces(MediaType.APPLICATION_JSON)
    public List<AsignaturaRepresentation> listarTodas() {
        List<AsignaturaRepresentation> lista = this.asignaturaService.listarTodas();
        for (int i = 0; i < lista.size(); i++) {
            lista.set(i, construirLinks(lista.get(i)));
        }
        return lista;
    }

    @GET
    @Path("/{id}")
    @jakarta.ws.rs.Produces(MediaType.APPLICATION_JSON)
    public AsignaturaRepresentation consultarPorId(@PathParam("id") Integer id) {
        return construirLinks(this.asignaturaService.consultarPorId(id));
    }

    @GET
    @Path("/codigo/{codigo}")
    @jakarta.ws.rs.Produces(MediaType.APPLICATION_JSON)
    public AsignaturaRepresentation consultarPorCodigo(@PathParam("codigo") String codigo) {
        return construirLinks(this.asignaturaService.consultarPorCodigo(codigo));
    }

    @GET
    @Path("/nombre")
    @jakarta.ws.rs.Produces(MediaType.APPLICATION_JSON)
    public List<AsignaturaRepresentation> consultarPorNombre(@QueryParam("nombre") String nombre) {
        List<AsignaturaRepresentation> lista = this.asignaturaService.consultarPorNombre(nombre);
        for (int i = 0; i < lista.size(); i++) {
            lista.set(i, construirLinks(lista.get(i)));
        }
        return lista;
    }

    @GET
    @Path("/nivel/{nivel}")
    @jakarta.ws.rs.Produces(MediaType.APPLICATION_JSON)
    public List<AsignaturaRepresentation> consultarPorNivel(@PathParam("nivel") Integer nivel) {
        List<AsignaturaRepresentation> lista = this.asignaturaService.consultarPorNivel(nivel);
        for (int i = 0; i < lista.size(); i++) {
            lista.set(i, construirLinks(lista.get(i)));
        }
        return lista;
    }

    @POST
    @Path("")
    @jakarta.ws.rs.Consumes(MediaType.APPLICATION_JSON)
    @jakarta.ws.rs.Produces(MediaType.APPLICATION_JSON)
    public Response guardar(AsignaturaRepresentation asignatura) {
        this.asignaturaService.crear(asignatura);
        return Response.status(Response.Status.CREATED).entity(asignatura).build();
    }

    @PUT
    @Path("/{id}")
    @jakarta.ws.rs.Consumes(MediaType.APPLICATION_JSON)
    @jakarta.ws.rs.Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(@PathParam("id") Integer id, AsignaturaRepresentation asignatura) {
        this.asignaturaService.actualizar(id, asignatura);
        return Response.status(Response.Status.OK).entity(asignatura).build();
    }

    @PATCH
    @Path("/{id}")
    @jakarta.ws.rs.Consumes(MediaType.APPLICATION_JSON)
    @jakarta.ws.rs.Produces(MediaType.APPLICATION_JSON)
    public Response actualizarParcial(@PathParam("id") Integer id, AsignaturaRepresentation asignatura) {
        this.asignaturaService.actualizarParcial(id, asignatura);
        return Response.status(Response.Status.OK).entity(asignatura).build();
    }

    @DELETE
    @Path("/{id}")
    @jakarta.ws.rs.Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") Integer id) {
        this.asignaturaService.eliminar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/codigo/{codigo}")
    @jakarta.ws.rs.Produces(MediaType.APPLICATION_JSON)
    public Response eliminarPorCodigo(@PathParam("codigo") String codigo) {
        Long result = this.asignaturaService.eliminarPorCodigo(codigo);
        return Response.status(Response.Status.NO_CONTENT).entity(result).build();
    }

    private AsignaturaRepresentation construirLinks(AsignaturaRepresentation ar) {
        if (ar == null) return null;
        String self = this.uriInfo.getBaseUriBuilder().path(AsignaturaResource.class).path(String.valueOf(ar.id))
            .build().toString();
        ar.links = List.of(new LinkDto(self, "self"));
        return ar;
    }
}
