package Service;

import Model.Message;
import DAO.MessageDAO;
import DAO.AccountDAO;
import Model.Account;

import java.util.List;
import java.util.ArrayList;
public class MessageService {
    private MessageDAO messageDAO;
    private AccountDAO accountDAO;
    public MessageService(){
        messageDAO = new MessageDAO();
        accountDAO = new AccountDAO();
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
        this.accountDAO = new AccountDAO();
    }

    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int message_id){
        Message message = messageDAO.getMessageById(message_id);
        if (message == null){
            return null;
        }
        return message;
    }

    public Message createMessage(Message message){
        List<Account> accounts = accountDAO.getAllAccounts();
        if (message.getMessage_text() != ""){
            if(message.getMessage_text().length() < 255){
                for (int i = 0; i < accounts.size(); i++){
                    if(accounts.get(i).getAccount_id() == message.getPosted_by()){
                        return messageDAO.createMessage(message);
                    }
                }
            }
        }
        return null;

    }
}
