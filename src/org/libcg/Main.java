package org.libcg;

import org.libcg.controllers.MenuController;
import org.libcg.core.App;

public class Main {
    public static void main(String[] args) {
      App.useProvider(new Provider());
      App.run(MenuController.class);
    }
}
