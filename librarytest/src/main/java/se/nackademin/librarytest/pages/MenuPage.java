package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class MenuPage extends PageBase {
    
    @FindBy(css = "#side-menu-link-add-user")
    private SelenideElement addUser;
    @FindBy(css = "#side-menu-link-sign-in")
    private SelenideElement signIn;
    @FindBy(css = "#side-menu-link-my-profile")
    private SelenideElement myProfile;
    @FindBy(css = "#side-menu-link-browse-books")
    private SelenideElement browseBooks;
    @FindBy(css = "#side-menu-link-add-book")
    private SelenideElement addBook;
    @FindBy(css = "#side-menu-link-add-author")
    private SelenideElement addAuthor;
    @FindBy(css = "#side-menu-link-browse-authors")
    private SelenideElement browseAuthors;
    @FindBy(css = "#side-menu-link-sign-out")
    private SelenideElement signOut;
    @FindBy(css = "#banner-link")
    private SelenideElement homePage;
    
    public void navigateToAddUser(){
        clickButton("add user page button", addUser);
    }
    
    public void navigateToSignIn(){
        clickButton("sign in page button", signIn);
    }
    
    public void navigateToMyProfile(){
        clickButton("my profile page button", myProfile);
    }
    
    public void navigateToAddBook(){
        clickButton("add book page button", addBook);
    }
    
    public void navigateToBrowseBooks(){
        clickButton("browse books page button", browseBooks);
    }
    
    public void navigateToAddAuthor(){
        clickButton("add author page button", addAuthor);
    }
    
    public void navigateToBrowseAuthors(){
        clickButton("browse authors page button", browseAuthors);
    }
    
    public void navigateToSignOut(){
        clickButton("sign out button", signOut);
    }
    
    public void navigateToHomePage(){
        clickButton("home page button", homePage);
    }
}
