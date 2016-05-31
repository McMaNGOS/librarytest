package se.nackademin.librarytest.helpers;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import se.nackademin.librarytest.pages.AddUserPage;
import se.nackademin.librarytest.pages.MenuPage;
import se.nackademin.librarytest.pages.SignInPage;

public class UserHelper {
    public static void createNewUser(String username, String password, String email, boolean librarian){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToAddUser();
        
        AddUserPage addUserPage = page(AddUserPage.class);
        addUserPage.setUsername(username);
        addUserPage.setPassword(password);
        addUserPage.setEmail(email);
        
        if (librarian == true){
            addUserPage.clickLibrarianButton();
            addUserPage.clickAddUserButton();
        } else {
            addUserPage.clickAddUserButton();
        }
    }
    
    public static void logInAsUser(String username, String password){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToSignIn();
        
        SignInPage signInPage = page(SignInPage.class);
        signInPage.setUsername(username);
        signInPage.setPassword(password);
        signInPage.clickLogIn();
    }
    
    public static void signOutUserAndNavigateHome(){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToSignOut();
        menuPage.navigateToHomePage();
    }
}
