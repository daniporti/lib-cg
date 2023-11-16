package org.libcg.controllers;

import org.libcg.core.Controller;
import org.libcg.views.menu.MenuPrincipalScreen;

public class MenuController extends Controller {

    @Override
    public void principal() {
        MenuPrincipalScreen view = new MenuPrincipalScreen();
        
        view.render();
    }
}
