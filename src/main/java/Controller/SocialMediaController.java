package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.Javalin;
import io.javalin.http.Context;

import Model.Account;
import Model.Message;
import Service.MessageService;
import Service.AccountService;
import DAO.MessageDAO;
import DAO.AccountDAO;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController(){
        accountService = new AccountService();
        messageService = new MessageService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);
        app.get("/messages", this::getAllMessages);
        app.get("/messages/{message_id}", this::getMessageById);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByUser);

        app.post("/register", this::userRegistration);
        app.post("/login", this::userLogin);
        app.post("/messages", this::createNewMessage);

        app.delete("/messages/{message_id}", this::deleteMessage);

        app.patch("/messages/{message_id}", this::updateMessage);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    private void getAllMessages(Context context){
        context.json("filler");
    }

    private void getMessageById(Context context){
        context.json("filler");
    }

    private void getAllMessagesByUser(Context context){
        context.json("filler");
    }

    private void userRegistration(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account addedAccount = accountService.addAccount(account);
        if(addedAccount != null){
            context.json(mapper.writeValueAsString(addedAccount));
        }
        else{
            context.status(400);
        }
    }

    private void userLogin(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account loggedIn = accountService.login(account);
        if(loggedIn != null){
            context.json(mapper.writeValueAsString(loggedIn));
        }
        else{
            context.status(401);
        }
        
    }

    private void createNewMessage(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        Message newMessage = messageService.createMessage(message);
        if(newMessage != null){
            context.json(mapper.writeValueAsString(newMessage));
        }
        else{
            context.status(400);
        }
    }

    private void deleteMessage(Context context){
        context.json("filler");
    }

    private void updateMessage(Context context){
        context.json("filler");
    }


}