import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class Script3{
    public static void main(String[] args) {



        WebDriver driver = getDriver();
        driver.manage().window().maximize();
        driver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/index.php?controller=AdminLogin&token=d251f363cceb5a849cb7330938c64dea");

        WebElement searchInputEmail = driver.findElement(By.id("email"));
        searchInputEmail.sendKeys("webinar.test@gmail.com");

        WebElement searchInputPassword = driver.findElement(By.id("passwd"));
        searchInputPassword.sendKeys("Xcg7299bnSmMuRLp9ITw");

        WebElement searchButtonIn = driver.findElement(By.name("submitLogin"));
        searchButtonIn.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.withMessage("Element is not found")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("subtab-AdminCatalog")));

        WebElement catalog = driver.findElement(By.id("subtab-AdminCatalog"));
        WebElement categor = driver.findElement(By.id("subtab-AdminCategories"));

        Actions actions = new Actions(driver);
        actions.moveToElement(catalog).pause(Duration.ofSeconds(5)).click(categor)
                .build().perform();

        wait.withMessage("Element is not found")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("page-header-desc-category-new_category")));

        WebElement categorNew = driver.findElement(By.id("page-header-desc-category-new_category"));
        categorNew.click();

        WebElement inputNew = driver.findElement(By.cssSelector("input[name=name_1]"));
        inputNew.sendKeys("1categ");

        WebElement save = driver.findElement(By.id("category_form_submit_btn"));
        save.click();

        wait.withMessage("Element is not found")
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name=categoryFilter_name]")));

        WebElement inputFilter = driver.findElement(By.cssSelector("input[name=categoryFilter_name]"));
        inputFilter.sendKeys("1categ");

        WebElement search = driver.findElement(By.id("submitFilterButtoncategory"));
        search.click();

        driver.close();
    }





    public static WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "//resourses//chromedriver.exe" );
        return new ChromeDriver();
    }


    public static EventFiringWebDriver getConfiguredDriver () {
        EventFiringWebDriver driver = new EventFiringWebDriver(getDriver());
        driver.register(new EventHandler());

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;

    }
}
