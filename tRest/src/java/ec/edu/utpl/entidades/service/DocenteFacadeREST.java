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
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Santiago
 */
@Stateless
@Path("docentes")
public class DocenteFacadeREST extends AbstractFacade<Docente> {
    @PersistenceContext(unitName = "tRestPU")
    private EntityManager em;

    public DocenteFacadeREST() {
        super(Docente.class);
    }
    
    @PUT
    @Path("{json}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("json") String s)
    {
        JSONParser parser=new JSONParser();
        try{
         int  ced;
         Object obj = parser.parse(s);
         JSONObject jsonObject = (JSONObject) obj;  
         
         ced=Integer.parseInt(jsonObject.get("cedula").toString());         
         
         Docente d;
         d = new Docente(ced, jsonObject.get("nombre").toString());
         super.edit(d);
             
      }catch(ParseException pe){      
      }    
    }
    
    
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }


    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Docente find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    //METODO GET PARA OBTENER LAS MATERIAS QUE DICTA UN DOCENTE POR MEDIO DE SU NUMERO DE CEDULA
    @GET
    @Path("/{cedula}/cursos")
    @Produces({"application/xml", "application/json"})
    public List<Curso> findCursoByDocente(@PathParam("cedula") Integer cedula) {
     return super.findCursoByDocente(cedula);
    }

    
    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Docente> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Docente> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
