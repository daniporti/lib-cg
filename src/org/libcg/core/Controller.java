package org.libcg.core;

public abstract class Controller {
    protected final App app;
    
    public Controller() {
        app = App.getInstance();
    }
    
    public abstract void principal();
}
