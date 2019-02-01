import java.util.List;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;


public class ParseMemes {
    public static void parseMemeFromvkcom(){

        DriversProperties.FirefoxProperty();
        DinamicProxy dinamicproxy=new DinamicProxy();
        //System.out.println(dinamicproxy.getAdress());
        String httpproxy=dinamicproxy.getAdress();
        Proxy proxy=new Proxy();
        proxy.setHttpProxy(httpproxy).setFtpProxy(httpproxy).setSslProxy(httpproxy).setSslProxy(httpproxy);
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability("proxy",proxy);
        FirefoxOptions firefoxOptions=new FirefoxOptions(capabilities);
        //firefoxOptions.setLogLevel(Level.ALL);
        firefoxOptions.setProxy(proxy);

        WebDriver driver=new FirefoxDriver(firefoxOptions);
        driver.get("https://vk.com");
        driver.get("http://2ip.ru");//succses



    }
}


