package com.miola.smarthotel.helpers;

/**
 * Code created by Andrius on 2020-09-28
 */
public enum SceneName {
    DASHBOARD("DASHBOARD"),
    CLIENTS("CLIENT"),
    EMPLOYES("EMPLOYES"),
    CHAMBRES("CHAMBRES"),
    SEARCH ("SEARCH RESULTS")
    ;

    private final String name;

    private SceneName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
