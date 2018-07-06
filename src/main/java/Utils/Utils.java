package Utils;

import org.openqa.selenium.WebElement;

import java.util.List;

public class Utils {
    public static String normalizeString(String str) {
        return str.replace("\n", "").replace(" ", "").toLowerCase();
    }

    public static boolean isMac()
    {
        if (System.getProperty("os.name").contains("Mac"))
          return true;
        return false;
    }

    public static WebElement getLast(List<WebElement> list)
    {
        int size = list.size();
        return list.get(size - 1);
    }
}
