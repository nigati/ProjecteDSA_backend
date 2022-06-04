package edu.upc.dsa.services;
import edu.upc.dsa.*;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.Stats;
import edu.upc.dsa.mysql.ItemManagerDAOImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/stats", description = "Endpoint to item Service")
@Path("/stats")
public class StatsService {
    private StatsManager sm;

    public StatsService() {
        this.sm = StatsManagerImpl.getInstance();

    }

    @GET
    @ApiOperation(value = "get ranking from best to worst", notes = "minim2 Claudia")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Stats.class, responseContainer="List"),
    })
    @Path("/ranking")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRanking() {

        List<Stats> stats = this.sm.getRanking();

        GenericEntity<List<Stats>> entity = new GenericEntity<List<Stats>>(stats) {};
        return Response.status(201).entity(entity).build()  ;

    }
}
