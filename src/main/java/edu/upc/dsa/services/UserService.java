package edu.upc.dsa.services;


import edu.upc.dsa.models.*;
import edu.upc.dsa.mysql.ItemManagerDAO;
import edu.upc.dsa.mysql.ItemManagerDAOImpl;
import edu.upc.dsa.mysql.UserManagerDAO;
import edu.upc.dsa.mysql.UserManagerDAOImpl;
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

@Api(value = "/users", description = "Endpoint to user Service")
@Path("/users")
public class UserService {
    final static Logger logger = Logger.getLogger(UserService.class);

    private UserManagerDAO umd;
    private ItemManagerDAO imd;

    public UserService() {
        this.umd = UserManagerDAOImpl.getInstance();
        this.imd = ItemManagerDAOImpl.getInstance();
    }


    @POST
    @ApiOperation(value = "create a new user", notes = "xd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= User.class),
            @ApiResponse(code = 404, message = "Username is already taken")

    })

    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(User user) {

        if (user.getUsername().equals("") || user.getEmail().equals("") || user.getPassword().equals(""))
        {
            return Response.status(404).entity(user).build();
        }

        //User checking = this.um.addUser(user);
        int checking = this.umd.addUser(user);
        if (checking == 1)
        {
            return Response.status(201).entity(user).build();
        }
        else
        {
            return Response.status(404).entity(user).build();
        }
    }

    @GET
    @ApiOperation(value = "get all Objects in Catalogo", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class, responseContainer="List"),
    })
    @Path("/catalogo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObjects() {

        List<Item> items = this.imd.getAll();

        GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(items) {};
        return Response.status(201).entity(entity).build();

    }
    @POST
    @ApiOperation(value="Retrieve issues", notes = "minim2")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 401, message = "Insert error")
    })

    @Path("/{username}/issue")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response issues(@PathParam("username")String username,Issue issue){
        User u = umd.getUser(username);
        issue.setUser_id(u.getId());
        if(umd.addIssue(issue) == 1)
            return Response.status(201).build();
        return Response.status(401).build();
    }


    @POST
    @ApiOperation(value = "log in user", notes = "xd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= User.class),
            @ApiResponse(code = 401, message = "Incorrect username or password")

    })

    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logIn(LogInParams loginpar) {

        //System.out.println("PARAMETROS "+loginpar.getUsername()+" ===> "+loginpar.getPassword());
            User u = this.umd.login(loginpar);
            //User u2 = this.um.login(loginpar.getUsername(),loginpar.getPassword());
            if (u!= null) {
                return Response.status(201).entity(u).build();
            }
            else {

                //System.out.println("Usuario o contrase??a incorrectos");
                return Response.status(401).build();

            }
    }

    @PUT
    @ApiOperation(value = "update a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })

    @Path("/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(String username) {

       // User user=umd.getUser(username);
        //User u = this.umd.updateUser(user.getUsername(),user.getEmail(), user.getPassword());

       // if (u == null) return Response.status(404).build();

        return Response.status(201).build();
    }
    @PUT
    @ApiOperation(value = "update a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })

    @Path("/{username}/updatelanguage")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUserLanguage(@PathParam("username")String username,String language) {
        logger.info(username+language);
        umd.updateUserLanguage(username,language);
        User user= umd.getUser(username);

        if (user==null)
        {
            return Response.status(404).build();
        }
        else {
            return Response.status(201).build();
        }

    }
    @PUT
    @ApiOperation(value = "update a User's username", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })

    @Path("/{username}/updateusername")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUserUsername(@PathParam("username")String username,String new_username) {
        String result = new_username.replaceAll("\"", "");
        logger.info("I'm updating the username:"+username+" to: "+result);

        umd.updateUserUsername(username,result);
        User user= umd.getUser(username);
        if (user==null)
        {
            return Response.status(404).build();
        }
        else {
            return Response.status(201).build();
        }

    }
    @PUT
    @ApiOperation(value = "update a User's password", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })

    @Path("/{username}/updatepassword")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUserPassword(@PathParam("username")String username,String new_password) {
        String result = new_password.replaceAll("\"", "");
        logger.info("I'm updating the "+username+" password to: "+result);
        umd.updateUserPassword(username,result);
        User user= umd.getUser(username);
        if (user==null)
        {
            return Response.status(404).build();
        }
        else {
            return Response.status(201).build();
        }

    }
    @PUT
    @ApiOperation(value = "update a User's email", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })

    @Path("/{username}/updateemail")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUserEmail(@PathParam("username")String username,String new_email) {
        String result = new_email.replaceAll("\"", "");
        logger.info("I'm updating the "+username+" email to: "+result);
        umd.updateUserEmail(username,result);
        User user= umd.getUser(username);
        if (user==null)
        {
            return Response.status(404).build();
        }
        else {
            return Response.status(201).build();
        }

    }
    @PUT
    @ApiOperation(value = "buy Item from shop", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=User.class),
            @ApiResponse(code = 404, message = "Something went wrong"),

    })
    @Path("/buyItem")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buyItem(Inventory inventory) {

        User user=umd.getUser(inventory.getUsername());
        //System.out.println("Consigo al player: "+ user.getCoins());
        Item i =imd.getItem(inventory.getName());
        System.out.println("Consigo al item: "+ i.getCoins());
        User u= this.umd.buyItem(inventory.getName(), inventory.getUsername());

        if (u == null) return Response.status(404).build();

        return Response.status(201).entity(u).build();
    }

    @GET
    @ApiOperation(value = "get a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("username")String username) {

        User user=umd.getUser(username);
        if (user == null) return Response.status(404).build();
        return Response.status(201).entity(user).build();

    }
    @DELETE
    @ApiOperation(value = "delete a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("username")String username) {

        int i = umd.deleteUser(username);
        if (i == 0) return Response.status(404).build();
        else {
            return Response.status(201).build();
        }
    }


    @GET
    @ApiOperation(value = "get Inventory from username", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Inventory.class, responseContainer="List"),

    })
    @Path("{username}/inventory")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInventory(@PathParam("username")String username) {

        List<Inventory> itemsInventory=umd.getInventory(username);
        logger.info(itemsInventory);
//        List<Item> itemsInventory = new ArrayList<>();
//        int i=0;
//        while (i<itemsInventoryAux.size())
//        {
//            itemsInventory.add(umd.getItem(itemsInventoryAux.get(i).getName()));
//            i++;
//        }
        GenericEntity<List<Inventory>> entity = new GenericEntity<List<Inventory>>(itemsInventory) {};
        if (itemsInventory.size() == 0) return Response.status(404).build();
        return Response.status(201).entity(entity).build();
    }

    @PUT
    @ApiOperation(value = "update Inventory when used in game", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),

    })
    @Path("/{username}/useItemInGame")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateInventory(Inventory inventory) {
        umd.useItemInGame(inventory.getName(), inventory.getUsername());
        return Response.status(201).build();
    }

}