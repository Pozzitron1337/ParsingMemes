package Parsing;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ProxyVPN.DinamicProxy;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;


public class ParseMemes {




    public static void parseMemeFromvkcom(){

        DriversProperties.FirefoxProperty();
        DinamicProxy dinamicproxy=new DinamicProxy();
        System.out.println(dinamicproxy.getAdress());
        String httpproxy=dinamicproxy.getAdress();
        Proxy proxy=new Proxy();
        proxy.setHttpProxy(httpproxy).setFtpProxy(httpproxy).setSslProxy(httpproxy).setSslProxy(httpproxy);

        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability("proxy",proxy);

        FirefoxOptions firefoxOptions=new FirefoxOptions(capabilities);
        firefoxOptions.setLogLevel(Level.ALL);
        firefoxOptions.setProxy(proxy);
        firefoxOptions.setHeadless(true);

        WebDriver driver=new FirefoxDriver(firefoxOptions);
        driver.get("http://vk.com/4ch");
        List<WebElement> webElements=driver.findElements(By.xpath("//div[@class='wall_text']//div[@class='page_post_sized_thumbs  clear_fix']//a[contains(@class,'page_post_thumb_wrap')]"));










        List<String> links=new ArrayList<String>();
        System.out.println(webElements.size());
        for(WebElement webElement:webElements){
            System.out.println(webElement.getAttribute("style"));
            links.add(webElement.getAttribute("style"));
        }
        Pattern pattern = Pattern.compile("url\\(\\\"(.*)\\\"\\);");
        for(String link:links) {
            Matcher matcher=pattern.matcher(link);
            matcher.find();
            System.out.println(matcher.group(1));
        }


    }
}


