package edu.upc.dsa.services;

import edu.upc.dsa.StatsManager;
import edu.upc.dsa.StatsManagerImpl;
import edu.upc.dsa.UserManager;
import edu.upc.dsa.UserManagerImpl;
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
@Api(value = "/game", description = "Endpoint to game Service")
@Path("/game")
public class GameService {
    final static Logger logger = Logger.getLogger(ForumService.class);
    private GameManagerDAO gameManagerDAO;
    public GameService() {
        this.gameManagerDAO = GameManagerDAOImpl.getInstance();
    }


    @POST
    @ApiOperation(value = "Game", notes = "message", response = Game.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create"),
            @ApiResponse(code = 404, message = "Error saving the game.")

    })

    @Path("/saveGame")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveGame (Game game) {
        int i = this.gameManagerDAO.saveGame(game);
        if (i==1){
            return Response.status(201).build();
        }
        else{
            return Response.status(404).build();
        }
    }


    //---------------------------


    @GET
    @ApiOperation(value = "Get the last game of the player.", notes = "asdasd", response = ForumMessage.class, responseContainer="List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "No messages in the forum.")
    })
    @Path("/getGame/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLastGameOfUser(@PathParam("username") String username) {
        Game lastGame= this.gameManagerDAO.getLastGameOfUser(username);
        if (lastGame == null) return Response.status(404).build();
        return Response.status(201).entity(lastGame).build();
    }

}
