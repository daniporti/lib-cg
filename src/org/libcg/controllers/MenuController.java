package org.libcg.controllers;

import org.libcg.core.Controller;
import org.libcg.views.MenuView;

public class MenuController extends Controller {

    @Override
    public void main() {
        this.app.call(MenuView.class);
    }
}
