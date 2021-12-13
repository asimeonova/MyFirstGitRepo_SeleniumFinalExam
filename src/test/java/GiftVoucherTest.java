import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class GiftVoucherTest extends BaseClass{

   @BeforeMethod
    public void goToPage(){
       driver.get("http://shop.pragmatic.bg/admin/");
   }

   @Test
   public  void giftVoucher(){
       // log into the system
       WebElement username=driver.findElement(By.cssSelector("#input-username"));
       username.sendKeys("admin15");
       driver.findElement(By.cssSelector("#input-password")).sendKeys("parola123!");
       driver.findElement(By.cssSelector(".btn")).click();
       Assert.assertEquals(driver.getTitle(), "Dashboard" ,"Message on failure");

       //go to gift voucher section
       driver.findElement(By.cssSelector("#menu-sale>a.parent.collapsed")).click();
       WebDriverWait wait= new WebDriverWait(driver, 10);
       wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#collapse4 li:nth-of-type(4)")));
       driver.findElement(By.cssSelector("#collapse4 li:nth-of-type(4)")).click();
       driver.findElement(By.cssSelector("#collapse4-3 li:nth-of-type(1)")).click();

       // checking if the gift voucher exists
       String pageSource=driver.getPageSource();
        if ( pageSource.contains("a123q")){
            driver.findElement(By.xpath("//td[text()='a123q']//..//input")).click();
            driver.findElement(By.cssSelector(".btn-danger")).click();
            driver.switchTo().alert().accept();
       }


       // create gift voucher
       driver.findElement(By.cssSelector(".pull-right a.btn")).click();
       driver.findElement(By.cssSelector("#input-code")).sendKeys("a123q");
       driver.findElement(By.cssSelector("#input-from-name")).sendKeys("Petar Petrov");
       driver.findElement(By.cssSelector("#input-from-email")).sendKeys("petar@abv.bg");
       driver.findElement(By.cssSelector("#input-to-name")).sendKeys("Ivan Petrov");
       driver.findElement(By.cssSelector("#input-to-email")).sendKeys("ivan@gmail.com");
       driver.findElement(By.cssSelector("#input-amount")).sendKeys("50$");
       driver.findElement(By.cssSelector(".pull-right button.btn")).click();

       //verify successfully created  gift voucher
      String successfullMessage= driver.findElement(By.cssSelector(".alert")).getText();
      Assert.assertTrue(successfullMessage.contains("Success"));

   }


}
