package edu.upc.dsa.services;
import edu.upc.dsa.models.*;
import edu.upc.dsa.mysql.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
@Api(value = "/forum", description = "Endpoint to forum Service")
@Path("/forum")
public class ForumService {
    final static Logger logger = Logger.getLogger(ForumService.class);
    private ForumManagerDAO forumManagerDAO;
    public ForumService() {
        this.forumManagerDAO = ForumManagerDAOImpl.getInstance();
    }


    @POST
    @ApiOperation(value = "User", notes = "message", response = ForumMessage.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create")

    })

    @Path("/addMessage")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEntry (ForumMessage message) {
        this.forumManagerDAO.addEntry(message.getUsername(), message.getMessage());
        return Response.status(201).build();
    }


    //---------------------------


    @GET
    @ApiOperation(value = "Get all messages of the forum in the system.", notes = "asdasd", response = ForumMessage.class, responseContainer="List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "No messages in the forum.")
    })
    @Path("/GetAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEntries() {
        List<ForumMessage> listMessages = this.forumManagerDAO.getAllEntries();
        GenericEntity<List<ForumMessage>> entity = new GenericEntity<List<ForumMessage>>(listMessages){};
        if (listMessages.size() == 0){
            return Response.status(404).build();
        }
        else{
            return Response.status(200).entity(entity).build();
        }
    }

}
