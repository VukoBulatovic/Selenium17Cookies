package Tests;

import Base.BaseTest;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLOutput;

public class BookStoreTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        driver.manage().window().maximize();
        driver.navigate().to("https://demoqa.com/");
    }

    @Test
    public void test() throws InterruptedException {
        homepage.clickOnBookstore();
        logIn();
        Thread.sleep(3000);
        Assert.assertEquals(profilePage.books.size(),0);
        setCookie(driver.manage().getCookieNamed("token"));
        profilePage.clickOnLogOut();
        Thread.sleep(3000);
        injectCookies();
        sidebarPage.clickOnSidebarButton("Book Store");
        bookstorePage.addBook(0);
        bookPage.clickOnAddToCollection();
        bookPage.clickOnBackToBookstore();
        bookstorePage.addBook(1);
        bookPage.clickOnAddToCollection();
        removeCookies();
        logIn();
        Assert.assertEquals(profilePage.books.size(),2);
        profilePage.removeBooks();
    }

    public void logIn(){
        sidebarPage.clickOnSidebarButton("Login");
        loginPage.inputUsername("Korisnik365");
        loginPage.inputPassword("Blabla!@#123");
        loginPage.clickOnLoginButton();
    }




}
