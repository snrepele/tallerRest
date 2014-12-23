/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.utpl.entidades.service;

import ec.edu.utpl.entidades.Curso;
import ec.edu.utpl.entidades.Docente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Santiago
 */
@Stateless
@Path("cursos")
public class CursoFacadeREST extends AbstractFacade<Curso> {
    @PersistenceContext(unitName = "tRestPU")
    private EntityManager em;

    public CursoFacadeREST() {
        super(Curso.class);
    }
    

    

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    
    
    
    
    

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Curso find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    //METODO QUE OBTIENE LOS DOCENTES QUE DICTAN UNA MATERIA POR SU NUMERO DE ID
    @GET
    @Path("/{id}/docentes")
    @Produces({"application/xml", "application/json"})
    public List<Docente> findDocenteByCurso(@PathParam("id") Integer id) {
        return super.findDocenteByCurso(id);
    }
    

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Curso> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Curso> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    
  

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
