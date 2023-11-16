package org.libcg.core;

import java.util.Scanner;

public abstract class View {
    protected final App app;
    protected final Scanner scanner;
    
    public View() {
        app = App.getInstance();
        scanner = new Scanner(System.in);
    }
    
    public abstract void render();
}
