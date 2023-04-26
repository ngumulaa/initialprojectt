package edu.guilford.atm;

import java.util.ArrayList;
import java.util.Random;
import static javax.swing.text.html.HTML.Attribute.ID;

public class Bank {

    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;

    // create a new Bank object with empty lists of users and accounts 
    // name the name of the bank 
    public Bank(String name) {
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }
public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }
    /**
     * Generate a new uuid for a user
     *
     * @return the uuid
     */
    public String getNewUserUUID() {
        //we will be using a random class to generate the uuid 
        // and verify that it's not already in our user and account lists
        //let's intialize
        String uuid;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique;
        //continue looping the generator until you get a new uuid 
        //that does not exist already in our user list
        do {//generate the number
            uuid = "";
            for (int c = 0; c < len; c++) {
                uuid += ((Integer) rng.nextInt(10)).toString();
            }
            //check to make sure it's unique
            nonUnique = false;
            for (User u : this.users) {
                if (uuid.compareTo(u.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);
        return uuid;
    }

    public String getNewAccountUUID() {
        /**
         * generate a new uuid for an account
         */
        //let's intialize
        String uuid;
        Random rng = new Random();
        int len = 10;
        boolean nonUnique;
        //continue looping the generator until you get a new uuid 
        //that does not exist already in our user list
        do {//generate the number
            uuid = "";
            for (int c = 0; c < len; c++) {
                uuid += ((Integer) rng.nextInt(10)).toString();
            }
            //check to make sure it's unique
            nonUnique = false;
            for (Account a : this.accounts) {
                if (uuid.compareTo(a.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);
        return uuid;
    }

    /**
     * Create a new user of the bank
     *
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param pin the user's pin
     * @return the new User object
     */

    public User addUser(String firstName, String lastName, String pin) {
        // create a new User object and add it to our list 
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);

        // create a savings account for the user and add to User and Bank
        //accounts lists
        Account newAccount = new Account("Savings", newUser, this);
        
        newUser.addAccount(newAccount);
        this.accounts.add(newAccount); 
        return newUser;
    }

    /**
     * Get the User object associated with a particular userID and pin, if they
     * are valid
     *
     * @param userID the UUID of the user to log in
     * @param pin the pin of the user
     * @return the User object, if the login is successful, or null, if it is
     * not
     */
    public User userLogin(String userID, String pin) {

        // search through list of users
        for (User u : this.users){ 
         
         // check user ID is correct
         if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
                return u;
            }
        }
        // if we haven't found the user or have an incorrect pin 
        return null;
    }
/**
 * Get the name of the bank
 * @return the name of the bank
 */
    public String getName() {
        return this.name;
    }
}
