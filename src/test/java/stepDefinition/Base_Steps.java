package stepDefinition;

import Utils.DriverManager;
import Utils.PageObjectManager;

public class Base_Steps {

    protected PageObjectManager pages;

    public Base_Steps() {
        this.pages = DriverManager.getPages();
    }
}
