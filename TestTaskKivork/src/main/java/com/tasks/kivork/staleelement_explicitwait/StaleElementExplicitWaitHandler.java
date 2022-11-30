package com.tasks.kivork.staleelement_explicitwait;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Class represents Task 1 & 4 (StaleElementReferenceException & Explicit Wait)
 */
public class StaleElementExplicitWaitHandler {
    private WebDriver driver;
    private final static long TIME_TO_WAIT = 20;

    // TASK 1

    /**
     * EXAMPLE 1
     * Wait until element exists on the page.
     * Handles StaleElementReferenceException.
     *
     * @param by       the element
     * @param duration the duration to wait (e.g. in seconds)
     * @return the web element
     */
    public WebElement waitUntilElementExistsOne(By by, Duration duration) {
        var wait = createDriverWait(driver, duration.withSeconds(TIME_TO_WAIT));
        return wait
                .ignoring(StaleElementReferenceException.class)
                .until(e -> e.findElement(by));
    }

    /**
     * EXAMPLE 2
     * Wait until element exists on the page.
     * Handles StaleElementReferenceException.
     *
     * @param by       the element
     * @param duration the duration to wait (e.g. in seconds)
     * @return the web element
     */
    public WebElement waitUntilElementExistsTwo(By by, Duration duration) {
        var wait = createDriverWait(driver, duration.withSeconds(TIME_TO_WAIT));
        return wait
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(by)));
    }

    // TASK 4
    // Maybe I didn't get that task properly, the solution from Task 1 have to handle POM with explicit wait.
    // I did the click with JS executor in order to avoid problems with click, it may help.

    /**
     * Handles click with JS executor in case of common click is not working.
     *
     * @param by       the element
     * @param duration the duration to wait (e.g. in seconds)
     */
    public void waitAndClick(By by, Duration duration) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        var wait = createDriverWait(driver, duration.withSeconds(TIME_TO_WAIT));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(by)).click();
        } catch (Exception e) {
            try {
                executor.executeScript("arguments[0].click();", driver.findElement(by));
            } catch (Exception ex) {
                throw new Error(ex);
            }
        }
    }

    // Not necessary, JFYI

    /**
     * Waits for POM to load.
     *
     * @param duration the duration to wait (e.g. in seconds)
     */
    public void waitForDocumentLoad(Duration duration) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        var wait = createDriverWait(driver, duration.withSeconds(TIME_TO_WAIT));
        wait.until(webDriver ->
                (js).executeScript("return document.readyState == 'complete'").equals(true));
    }

    /**
     * Creates web driver wait instance.
     *
     * @param driver  the web driver
     * @param timeout the timeout(e.g. in seconds)
     * @return the web driver wait instanse
     */
    private WebDriverWait createDriverWait(WebDriver driver, Duration timeout) {
        return new WebDriverWait(driver, timeout);
    }
}