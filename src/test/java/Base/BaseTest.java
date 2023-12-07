package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    //Ulogujte se na demoqa(https://demoqa.com/ -> Book Store Application) preko cookies-a ,
    //dodati dve knjige na svoj nalog,
    //zatim se izlogovati brisanjem cookies-a.
    //Ulogovati se ponovo preko log-in forme
    //i potvrditi da se knjige i dalje nalaze na nalogu.


    public static WebDriver driver;
    public WebDriverWait wait;
    public Homepage homepage;
    public BookstorePage bookstorePage;
    public BookPage bookPage;
    public SidebarPage sidebarPage;
    public LoginPage loginPage;
    public ProfilePage profilePage;
    private Cookie cookie;
    public ExcelReader excelReader;




    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver,Duration.ofSeconds(15));

        homepage = new Homepage();
        bookstorePage = new BookstorePage();
        bookPage = new BookPage();
        sidebarPage = new SidebarPage();
        loginPage = new LoginPage();
        profilePage = new ProfilePage();
        //excelReader = new ExcelReader("D:\\Users\\Bulatovic Vuko\\Desktop\\TestData.xlsx");
        excelReader = new ExcelReader("src/test/java/TestData.xlsx");


    }

    public void scrollToElement(WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void injectCookies(){
        Cookie cookie1 = new Cookie("userName","Korisnik365");
        Cookie cookie2 = new Cookie("userID","1c38b857-d579-431a-83eb-be30ad77a004");
        //Cookie cookie3 = new Cookie("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6IktvcmlzbmlrMzY1IiwicGFzc3dvcmQiOiJCbGFibGEhQCMxMjMiLCJpYXQiOjE3MDE5NjAyNDN9.aOFR3sLvBHlagJKzEMbGY6pY_uMm2RoM1jDkD5aR_Uw");
        Cookie cookie3 = getCookie();
        Cookie cookie4 = new Cookie("expires","2023-12-14T14%3A44%3A03.885Z");
        driver.manage().addCookie(cookie1);
        driver.manage().addCookie(cookie2);
        driver.manage().addCookie(cookie3);
        driver.manage().addCookie(cookie4);
        driver.navigate().refresh();
    }

    public void removeCookies(){
        Cookie cookie1 = new Cookie("userName","Korisnik365");
        Cookie cookie2 = new Cookie("userID","1c38b857-d579-431a-83eb-be30ad77a004");
        //Cookie cookie3 = new Cookie("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6IktvcmlzbmlrMzY1IiwicGFzc3dvcmQiOiJCbGFibGEhQCMxMjMiLCJpYXQiOjE3MDE5NjAyNDN9.aOFR3sLvBHlagJKzEMbGY6pY_uMm2RoM1jDkD5aR_Uw");
        Cookie cookie3 = getCookie();
        Cookie cookie4 = new Cookie("expires","2023-12-14T14%3A44%3A03.885Z");

        driver.manage().deleteCookie(cookie1);
        driver.manage().deleteCookie(cookie2);
        driver.manage().deleteCookie(cookie3);
        driver.manage().deleteCookie(cookie4);
        driver.navigate().refresh();


    }





    public Cookie getCookie() {
        return cookie;
    }

    public void setCookie(Cookie cookie){
        this.cookie = driver.manage().getCookieNamed("token");
    }




    @AfterClass
    public void tearDown(){
        //driver.manage().deleteAllCookies();
        //driver.quit();
    }


}
