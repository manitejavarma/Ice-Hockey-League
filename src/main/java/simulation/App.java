package simulation;

import db.data.IUserFactory;
import org.json.simple.JSONObject;
import presentation.ReadUserInput;
import simulation.factory.UserConcrete;
import simulation.factory.ValidationConcrete;
import simulation.model.User;
import simulation.state.HockeyContext;
import validator.IValidation;

import java.io.FileNotFoundException;


public class App {
    public static void main(String[] args) {

        String filePath = "";
        JSONObject jsonFromInput = null;

        ReadUserInput readUserInput = ReadUserInput.getInstance();

        String userName = readUserInput.getInput("Please enter username");

        ValidationConcrete validationConcrete = new ValidationConcrete();
        IValidation validation = validationConcrete.newValidation();

        try {
            if (validation.isNotEmpty(userName)) {
                UserConcrete userConcrete = new UserConcrete();
                IUserFactory factory = userConcrete.newUserFactory();
                User user = userConcrete.newUserByName(userName, factory);

                user.setName(userName);
                if (user.getId() == 0) {
                    String password = readUserInput.getInput("Please enter password to register yourself");

                    user.setPassword(password);
                    addUser(user);
                }
                filePath = readUserInput.getInput("Please provide location of JSON file. If not please press ENTER");

                if (validation.isNotNull(filePath) && filePath.length() > 0) {

                    if (JSONController.invalidJSON(filePath)) {
                        System.out.println("Invalid JSON file Provided.Exiting the app!");
                        System.exit(1);
                    }
                    jsonFromInput = JSONController.readJSON(filePath);
                }
                HockeyContext context = new HockeyContext(user);
                context.startAction(jsonFromInput);

            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not found. " + e);
        } catch (Exception e) {
            System.out.println("System faced unexpected exception. Please contact team. " + e);
        }
    }

    private static void addUser(User user) throws Exception {
        UserConcrete userConcrete = new UserConcrete();
        IUserFactory addUserFactory = userConcrete.newUserFactory();
        user.addUser(addUserFactory);
    }


}