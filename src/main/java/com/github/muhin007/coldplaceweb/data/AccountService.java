package com.github.muhin007.coldplaceweb.data;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private final Map<String, User> loginToProfile;
    private final Map<String, User> sessionIdToProfile;

    public AccountService() {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
    }

    public void addNewUser(User user) {
        loginToProfile.put(user.getLogin(), user);
    }

    public User getUserByLogin(String login) {
        return loginToProfile.get(login);
    }

    public User getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, User user) {
        sessionIdToProfile.put(sessionId, user);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
