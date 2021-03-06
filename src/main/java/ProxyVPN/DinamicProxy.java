package ProxyVPN;
import DriverProperties.DriversProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DinamicProxy {
    private String ip;
    private String port;
    private int numberOfRaw;


    public DinamicProxy(String region){
        numberOfRaw=1;
        getProxyFromfreeproxylistnet(region);
    }


    public void getProxyFromfreeproxylistnet(String region){
        DriversProperties.FirefoxProperty();

        FirefoxOptions firefoxOptions=new FirefoxOptions();
        firefoxOptions.setHeadless(true);
        WebDriver driver =new FirefoxDriver(firefoxOptions);
        WebDriverWait wait=new WebDriverWait(driver,5);

        driver.get("https://free-proxy-list.net/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='proxylisttable']")));

        WebElement selectElementByRegion=driver.findElement(By.xpath("//table[@id='proxylisttable']//th[3]//select"));
        Select selectByRegion=new Select(selectElementByRegion);
        selectByRegion.selectByValue(region);

        this.ip=driver.findElement(By.xpath("//tbody/tr["+this.numberOfRaw+"]/td[1]")).getText();
        this.port=driver.findElement(By.xpath("//tbody/tr["+this.numberOfRaw+"]/td[2]")).getText();
        driver.quit();
        this.numberOfRaw++;
        }


    public String getAdress(){
            return ip+":"+port;
        }
}
