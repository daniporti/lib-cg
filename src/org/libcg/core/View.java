package org.libcg.core;

import java.util.Scanner;

public abstract class View {
    protected final App app;
    protected final Scanner scanner;
    
    public View() {
        app = new App();
        scanner = new Scanner(System.in);
    }
}
