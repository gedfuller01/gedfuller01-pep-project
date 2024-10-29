package Service;

import Model.Account;
import DAO.AccountDAO;

import java.util.List;
import java.util.ArrayList;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public List<Account> getAllAccounts(){
        return accountDAO.getAllAccounts();
    }

    public Account addAccount(Account account){
        List<Account> accounts = this.getAllAccounts();
        if(account.getUsername() != "" && account.getPassword().length() >=4){
            for (int i = 0; i < accounts.size(); i++){
                if(accounts.get(i).getUsername() != account.getUsername()){
                    return accountDAO.userRegistration(account);
                }
            }
        }
        return null;
        
    }

    public Account login(Account account){
        Account loggedIn = accountDAO.login(account);
        return loggedIn;
    }
}
