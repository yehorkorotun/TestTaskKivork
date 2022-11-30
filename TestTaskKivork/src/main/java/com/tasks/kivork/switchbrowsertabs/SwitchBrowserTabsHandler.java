package com.tasks.kivork.switchbrowsertabs;

import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import java.util.Set;

/**
 * Class represents Task 3 (Switch between browser tabs)
 */
public class SwitchBrowserTabsHandler {
    private WebDriver driver;

    // TASK 3

    /**
     * Performs switch to the new tab
     * Method may be extended in case of some specific behaviour on the page
     */
    public void switchToTab() {
        // Gets the current window
        String mainWindow = driver.getWindowHandle();
        // Gets the set of current opened windows
        Set<String> windowHandles = driver.getWindowHandles();
        // For each loop will iterate through window tabs
        for (String handle : windowHandles) {
            // If the main window is not equal to window tab it will switch to that tab
            // In case of exception, it will cover NoSuchWindowException
            if (!mainWindow.equals(handle)) {
                try {
                    // Switches to new tab
                    driver.switchTo().window(handle);
                    // Switches to the main tab (if there is a need to do it)
                    driver.switchTo().window(mainWindow);
                } catch (NoSuchWindowException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}