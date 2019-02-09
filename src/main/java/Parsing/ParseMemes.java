package Parsing;

import java.util.ArrayList;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import DriverProperties.DriversProperties;
import ProxyVPN.VPN;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ParseMemes {




    public static void parseMemeFromvkcom(){
        DriversProperties.FirefoxProperty();
        VPN vpn=new VPN();
        boolean flag=true;
        while (flag){
            System.out.println(vpn.getAdress());
            String httpproxy=vpn.getAdress();
            Proxy proxy=new Proxy();
            proxy.setHttpProxy(httpproxy).setFtpProxy(httpproxy).setSslProxy(httpproxy).setSslProxy(httpproxy);
            DesiredCapabilities capabilities=new DesiredCapabilities();
            capabilities.setCapability("proxy",proxy);
            FirefoxOptions firefoxOptions=new FirefoxOptions(capabilities);
            firefoxOptions.setProxy(proxy);
            firefoxOptions.setHeadless(true);
            WebDriver driver=new FirefoxDriver(firefoxOptions);
            WebDriverWait waitwall=new WebDriverWait(driver,30);
            try {
                driver.get("http://vk.com/4ch");
                waitwall.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'wall_module') and contains(@id,'public_wall')]")));
            }
            catch (WebDriverException webdriverexception) {
                vpn.getAnotherAdress();
                continue;
            }

            List<WebElement> webElements=driver.findElements(By.xpath("//div[@class='wall_text']//div[@class='page_post_sized_thumbs  clear_fix']//a[contains(@class,'page_post_thumb_wrap')]"));
            if(webElements.size()==0){
                vpn.getAnotherAdress();
                continue;
            }else{
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
                    links.set(links.indexOf(link),matcher.group(1));
                }

                System.out.println();

                for (String s:links){
                    System.out.println(s);
                }
                flag=false;
                driver.quit();
            }
        }
    }
}


