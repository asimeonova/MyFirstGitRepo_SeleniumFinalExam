import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClass {
  WebDriver driver;

  @BeforeMethod
    public  void setUp(){
    System.setProperty("webdriver.chrome.driver","C:\\Users\\00. BrowserDrivers\\chromedriver.exe" );
    driver= new ChromeDriver();
    driver.manage().window().maximize();
  }

/*   @AfterMethod
    public void quitBrowser(){
        driver.quit();
    }*/

}


