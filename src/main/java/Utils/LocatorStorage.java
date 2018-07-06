package Utils;

import TestObjects.World;
import org.openqa.selenium.By;

public class LocatorStorage {
    private By iOSLocator;
    private By androidLocator;

    public LocatorStorage(By iOSLocatorVal, By androidLocatorVal)
    {
        iOSLocator = iOSLocatorVal;
        androidLocator = androidLocatorVal;
    }

    public LocatorStorage(By commonLocator)
    {
        iOSLocator = commonLocator;
        androidLocator = commonLocator;
    }

   public By getLocator()
   {
       if (World.isAndroid)
        return this.androidLocator;
       else
           return this.iOSLocator;
   }
}


