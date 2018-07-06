package Utils;

import java.util.HashMap;
import java.util.Map;

public class TestSettings {
    public static final int timeOut = 30000;
    public static final int veryShortTimeOut = 30000 / 6;
    public static final int shortTimeOut = timeOut / 2;
    public static final int longTimeOut = timeOut * 2;

   // Driver type
   public static final String testEnv="SauceLabs"; // (Local, LocalWeb,SauceLabs,)
  // public static final String testEnv="Local";
   
   public static String siteAddress ="https://www.guerrillamail.com/inbox";
   
   
    //region Android settings

    //public static final String androidDeviceName = "4203a3b6d0cb8100";
    public static final String appPackageAndroid = "nz.rugby.nzrteamapp";
    //public static final String appPackageMac = "NZ Rugby";
    public static final String appPackageMac = "nz.rugby.nzrteamapp";
    public static  String appPackage = "";
    public static final String appActivity = "host.exp.exponent.experience.ShellAppActivity";
    //public static final String apkPath = "D:/Projects/bitbucket/nzr_team_app/apk/NZRugbyTeamApp.apk";

    //endregion
    
    //Local
    public static String PathChromeDriver="/Users/emily/Automation/nzr_tv_ui_tests/Drivers/chromedriver";
/*
    public static final String sauceLabName = "sa.brovin";
    public static final String sauceLabAccessKey = "2487c4cc-24c9-4970-b13e-3c881d6cd065";
    public static final String sauceLabURL = "https://" + sauceLabName + ":" + sauceLabAccessKey + "@ondemand.saucelabs.com:443/wd/hub";
    public static final String sauceLabIOSBuildPath = "https://s3-eu-central-1.amazonaws.com/teamapp-build-ios/build//NZ_Rugby.zip";
    public static final String sauceLabAndroidBuildPath = "https://s3-eu-central-1.amazonaws.com/teamapp-build-android/build//NZ_Rugby.apk";
*/
    
//    public static final String sauceLabName = "EmilyJiang";
//    public static final String sauceLabAccessKey = "f34efa79-82be-4c06-bf16-15d54c5f0dad";
    public static final String sauceLabName = "Emily.jiang";
    public static final String sauceLabAccessKey = "88cd8416-78cb-47cf-ac43-ab279b3aa9e8";
    public static final String sauceLabURL = "https://" + sauceLabName + ":" + sauceLabAccessKey + "@ondemand.saucelabs.com:443/wd/hub";
    public static final String sauceLabIOSBuildPath = "https://s3-ap-southeast-2.amazonaws.com/nzr-team-app-automate/bitrise_automated_testing_build/NZ_Rugby.zip";
    public static final String sauceLabAndroidBuildPath = "https://s3-ap-southeast-2.amazonaws.com/nzr-team-app-automate/bitrise_automated_testing_build/NZ_Rugby.apk";
	

    
    //BrowserStack Settings
//    public static final String browserStackName = "sab45";
//    public static final String browserStackAccessKey = "98p16Qqz4Ep1ZDRvtk5Z";
    public static final String browserStackName = "mattgibbons1";
    public static final String browserStackAccessKey = "cjx0xR2cayIysBybCiCy";
    public static final String browserStackURL = "https://"+browserStackName+":"+browserStackAccessKey+"@hub-cloud.browserstack.com/wd/hub";

    public static class Account {
        public String email;
        public String userName;
        public String password;

        public Account(String email, String userName, String pass) {
            this.email = email;
            this.userName = userName;
            this.password = pass;
        }
    }

    public static Map<String, Account> accounts;
    static {
        accounts = new HashMap<>();
       // accounts.put("sabrovin@mailinator.com", new Account("sabrovin@mailinator.com", "", "Test123"));
        accounts.put("jianghongbo515@gmail.com", new Account("jianghongbo515@gmail.com", "", "Test123"));
    }
}
