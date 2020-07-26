package eseascrapper;

import org.openqa.selenium.By; 
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.Scanner;

public class EseaScrapper {
  
  private final static String[] mapsList = {"de_overpass", "de_mirage", "de_dust2", "de_inferno", "de_nuke", "de_vertigo", "de_train"}; 
  private static final Scanner myScanner = new Scanner(System.in);
  private static int ovpNum = 0; 
  private static int mirNum = 0; 
  private static int dustNum = 0; 
  private static int infNum = 0; 
  private static int traNum = 0; 
  private static int vertNum = 0; 
  private static int nukeNum = 0; 
  private static String teamName = ""; 
  
  
  public static String parseTeamName(String title) {
    String[] titleParse = title.split(" "); 
    for(int i = 4; i<titleParse.length;i++) {
      teamName += titleParse[i] + " "; 
    }
    return teamName; 
  }
  
  
  //TODO- MAKE THIS PRINT IN ORDER OF MOST PLAYED TO LEAST PLAYED
  public static void MapsPlayed() {
    if(ovpNum != 0 ) {
      System.out.println(teamName +  "has played overpass " + ovpNum + " times");
    }
    if(mirNum != 0 ) {
      System.out.println(teamName +  "has played mirage " + mirNum + " times");
    }
    if(dustNum != 0 ) {
      System.out.println(teamName +  "has played dust 2 " + dustNum + " times");
    }
    if(infNum != 0 ) {
      System.out.println(teamName +  "has played inferno " + infNum + " times");
    }
    if(traNum != 0 ) {
      System.out.println(teamName +  "has played train " + traNum + " times");
    }
    if(vertNum != 0 ) {
      System.out.println(teamName +  "has played vertigo " + vertNum + " times");
    }
    if(nukeNum != 0 ) {
      System.out.println(teamName +  "has played nuke " + nukeNum + " times");
    }
    
  }
  
  public static void connectAndTakeData(String url) {
    System.setProperty("webdriver.gecko.driver", "C:\\WebDriver\\bin\\geckodriver.exe");
    //Be able to change options of browser
    FirefoxOptions firefoxOptions = new FirefoxOptions(); 
    
  
   //Set of csgo maps 
    Set<String> a = new HashSet<String>(); 
    a.addAll(Arrays.asList(mapsList)); 
    
    
    try {
     
      //Starts the WebDriver with the url inputted
      WebDriver driver = new FirefoxDriver();
        
        driver.get(url);
        //Not sure why this works but these two lines are necessary idk really why
        
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);  //(100)
        TimeUnit.SECONDS.sleep(2); //(10)
        System.out.println(driver.getCurrentUrl());
        
        //Prints the name and the maps this team plays
       
        String teamName = parseTeamName(driver.getTitle()); 
        System.out.println("Team name " + teamName);
        List<WebElement> elements = driver.findElements(By.tagName("td"));
        ArrayList<String> tds = new ArrayList<String>(); 
        for(int i = 0;i<elements.size();i++) {
          tds.add(elements.get(i).getText()); 
        }
        ArrayList<String> maps = new ArrayList<String>(); 
        for (String text : tds) {
           if(a.contains(text)) {
             if(text.equals(mapsList[0])) {
               ovpNum++; 
             }
             if(text.equals(mapsList[1])) {
               mirNum++; 
             }
             if(text.equals(mapsList[2])) {
               dustNum++; 
             }
             if(text.equals(mapsList[3])) {
               infNum++; 
             }
             if(text.equals(mapsList[4])) {
               nukeNum++; 
             }
             if(text.equals(mapsList[5])) {
               vertNum++; 
             }
             if(text.equals(mapsList[6])) {
               traNum++; 
             }
             maps.add(text); 
           }
        }
        driver.quit();
        driver.close();
    }catch(Exception e) {
    System.out.println(e.getMessage());
  }
  finally {
    
  }
  }
  
  
  
    public static void main(String[] args) throws InterruptedException {
      System.out.println("Please input an esea team url");
      while(!myScanner.hasNext()) {
        myScanner.hasNext(); 
      }
      String url = myScanner.next(); 
      while(true) {
           connectAndTakeData(url); 
           MapsPlayed();
           TimeUnit.SECONDS.sleep(10);
           ovpNum = 0; 
           mirNum = 0; 
           dustNum = 0; 
           infNum = 0; 
           traNum = 0; 
           vertNum = 0; 
           nukeNum = 0; 
      }
     
        }
      
    }

  