import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class DinamicProxy {
    private String ip;
    private String port;


    public DinamicProxy(){
        getProxyFromfreeproxylistnet();
    }


    public void getProxyFromfreeproxylistnet(){
        DriversProperties.FirefoxProperty();

        WebDriver driver =new FirefoxDriver();
        WebDriverWait wait=new WebDriverWait(driver,5);

        driver.get("https://free-proxy-list.net/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='proxylisttable']")));

        WebElement selectelementbyregion=driver.findElement(By.xpath("//table[@id='proxylisttable']//th[3]//select"));
        Select selectbyregion=new Select(selectelementbyregion);
        selectbyregion.selectByValue("RU");

        WebElement SelectElementMaxNumberOfListSize =driver.findElement(By.xpath("//select[@name='proxylisttable_length']"));
        Select selectMaxNumberOfListSize=new Select(SelectElementMaxNumberOfListSize);

        /*int maxNumberOfListSize=Integer.parseInt(selectMaxNumberOfListSize.getFirstSelectedOption().getText());
        Random random=new Random();
        int numberOfRaw=(Math.abs(random.nextInt())%maxNumberOfListSize)+1;*/

        int numberOfRaw=1;
        this.ip=driver.findElement(By.xpath("//tbody/tr["+numberOfRaw+"]/td[1]")).getText();
        this.port=driver.findElement(By.xpath("//tbody/tr["+numberOfRaw+"]/td[2]")).getText();
        driver.quit();
        }







    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    public String getAdress(){
            return ip+":"+port;
        }
}
