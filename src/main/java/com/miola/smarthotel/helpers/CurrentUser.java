package com.miola.smarthotel.helpers;

import com.miola.smarthotel.model.User;

/**
 * Code created by Andrius on 2020-10-09
 */
public class CurrentUser {

    private static User user;

    private CurrentUser() {
    }

    public static User getCurrentUser() {
        return user;
    }

    public static void setCurrentUser(User currentUser) {
        user = currentUser;
    }
}
