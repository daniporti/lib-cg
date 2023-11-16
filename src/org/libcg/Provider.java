package org.libcg;

import org.libcg.controllers.LivroController;
import org.libcg.controllers.MenuController;
import org.libcg.core.App;
import org.libcg.core.AppProvider;

public class Provider implements AppProvider {
    
    @Override
    public void register(App app) {
        app.singleton(MenuController.class, () -> {
            return new MenuController();
        });
        app.singleton(LivroController.class, () -> {
            return new LivroController();
        });
    }
}
