package Parsing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Database.MemeDatabaseFromvkcom;
import DriverProperties.DriversProperties;
import ProxyVPN.VPN;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ParseMemes {
    public static void ParseMemeFromvkcom(){
        DriversProperties.FirefoxProperty();
        MemeDatabaseFromvkcom vk=new MemeDatabaseFromvkcom("jdbc:mysql://localhost:3306/memes?useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false","root","1234");
        Map<Integer,String> publics=vk.getPublics();
        if(publics.isEmpty())
            return;
        VPN vpn=new VPN("RU");
        WebDriver driver=null;
        for(int key:publics.keySet()){
            Pattern patternPublicName=Pattern.compile("vk.com\\/(.*)");
            Matcher matcherPublicName=patternPublicName.matcher(publics.get(key));
            matcherPublicName.find();
            String publicName=matcherPublicName.group(1);
            boolean flag=true;
            while (flag){
                System.out.println(vpn.getAdress());
                String httpProxy=vpn.getAdress();
                Proxy proxy=new Proxy();
                proxy.setHttpProxy(httpProxy).setFtpProxy(httpProxy).setSslProxy(httpProxy).setSslProxy(httpProxy);
                DesiredCapabilities capabilities=new DesiredCapabilities();
                capabilities.setCapability("proxy",proxy);
                FirefoxOptions firefoxOptions=new FirefoxOptions(capabilities);
                firefoxOptions.setProxy(proxy);
                firefoxOptions.setHeadless(true);
                driver=new FirefoxDriver(firefoxOptions);
                WebDriverWait waitwall=new WebDriverWait(driver,30);
                try {
                    driver.get(publics.get(key));
                    waitwall.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'wall_module') and contains(@id,'public_wall')]")));
                }
                catch (WebDriverException webdriverexception) {
                    vpn.getAnotherAdress("RU");
                    driver.quit();
                    continue;
                }
                List<WebElement> webElements=driver.findElements(By.xpath("//div[@class='wall_text']//div[@class='page_post_sized_thumbs  clear_fix']//a[contains(@class,'page_post_thumb_wrap')]"));
                if(webElements.size()==0){
                    vpn.getAnotherAdress("RU");
                    driver.quit();
                    continue;
                }
                else {
                    List<String> links = new ArrayList<String>();
                    System.out.println(webElements.size());
                    for (WebElement webElement : webElements)
                        links.add(webElement.getAttribute("style"));

                    Pattern patternMemeLink = Pattern.compile("url\\(\\\"(.*)\\\"\\);");
                    for (String link : links) {
                        Matcher matcherMemeLink = patternMemeLink.matcher(link);
                        matcherMemeLink.find();
                        System.out.println(matcherMemeLink.group(1));
                        vk.addMeme(key,publicName,matcherMemeLink.group(1));
                        //links.set(links.indexOf(link), matcherMemeLink.group(1));
                    }
                    links.clear();
                    flag = false;
                    }
            }
            driver.quit();
        }
        vk.closeConnection();
    }
}


