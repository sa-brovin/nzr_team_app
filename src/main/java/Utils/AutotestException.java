package Utils;

import TestObjects.Helpers.MobileActionsHelper;

public class AutotestException extends Exception {
    public AutotestException(String errorMessage) {
        super(errorMessage);
        MobileActionsHelper.getScr();
    }
}
