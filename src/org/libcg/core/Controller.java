package org.libcg.core;

public abstract class Controller {
    protected final App app;
    
    public Controller() {
        app = new App();
    }
    
    public abstract void main();
}
