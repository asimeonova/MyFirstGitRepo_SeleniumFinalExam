import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ShopPragmaticReviewTest extends BaseClass {
    @BeforeMethod
    public void goToStartPage() {
        driver.get("http://shop.pragmatic.bg/");
    }

    @Test
    public void reviewTest() {
        // go to product review section
        driver.findElement(By.cssSelector(".collapse .dropdown:nth-of-type(1)")).click();
        driver.findElement(By.cssSelector(".navbar-nav li.dropdown.open ul li:nth-of-type(2)")).click();
        driver.findElement(By.cssSelector(".caption a")).click();
        driver.findElement(By.cssSelector(".nav-tabs li:nth-of-type(2)")).click();

        //Write a review
        WebElement name = driver.findElement(By.cssSelector("#input-name"));
        name.sendKeys("Antoniya");


        WebElement review = driver.findElement(By.cssSelector("#input-review"));
        review.sendKeys("Fast shipping and quality product");

        driver.findElement(By.cssSelector("input[value='4']")).click();
        driver.findElement(By.cssSelector("#button-review")).click();

        // verify successfully written review
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert")));
        String actualReview = driver.findElement(By.cssSelector(".alert")).getText();
        Assert.assertTrue(actualReview.contains("Thank you for your review"));


        // go to admin area
        driver.get("http://shop.pragmatic.bg/admin/");
        WebElement username = driver.findElement(By.cssSelector("#input-username"));
        username.sendKeys("admin15");
        driver.findElement(By.cssSelector("#input-password")).sendKeys("parola123!");
        driver.findElement(By.cssSelector(".btn")).click();
        Assert.assertEquals(driver.getTitle(), "Dashboard" ,"Message on failure");

        // go to review section
        driver.findElement(By.cssSelector("#menu-catalog")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#collapse1 li:nth-of-type(9)")));
        driver.findElement(By.cssSelector("#collapse1 li:nth-of-type(9)")).click();

        //approve the review
        driver.findElement(By.xpath("//td[text()='Antoniya']//..//input")).click();
        driver.findElement(By.xpath("//td[text()='Antoniya']/following-sibling::td[4]/a")).click();
        Select status = new Select(driver.findElement(By.name("status")));
        status.selectByVisibleText("Enabled");
        driver.findElement(By.cssSelector(".btn-primary")).click();

        // verify the review exists
        goToStartPage();
        // go to product review section
        driver.findElement(By.cssSelector(".collapse .dropdown:nth-of-type(1)")).click();
        driver.findElement(By.cssSelector(".navbar-nav li.dropdown.open ul li:nth-of-type(2)")).click();
        driver.findElement(By.cssSelector(".caption a")).click();
        driver.findElement(By.cssSelector(".nav-tabs li:nth-of-type(2)")).click();
        // verify the review
       String actualReviewText=driver.findElement(By.xpath("//p[text()='Fast shipping and quality product']")).getText();
       Assert.assertTrue(actualReviewText.contains("Fast shipping and quality product"));
    }
}