import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class Main {

    public static void main(String[] args) {
        DriversProperties.FirefoxProperty();

        /*WebDriver driver =new FirefoxDriver();
        driver.get("http:\\google.com");
        System.out.println(driver.getCurrentUrl());
        WebElement search=driver.findElement(By.name("q"));
        System.out.println(search);*/
        ParseMemes.parseMemeFromvkcom();

    }
}
