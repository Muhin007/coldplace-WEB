package com.github.muhin007.coldplaceweb.data;

import com.github.muhin007.coldplaceweb.dbservice.DBService;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;
    private DBService dbService;

    public AccountService(DBService dbService) {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
        this.dbService = dbService;
    }

    public AccountService(Map<String, UserProfile> loginToProfile, Map<String, UserProfile> sessionIdToProfile) {

        this.loginToProfile = loginToProfile;
        this.sessionIdToProfile = sessionIdToProfile;
    }

    public void addNewUser(UserProfile userProfile) {
        loginToProfile.put(userProfile.getLogin(), userProfile);
        dbService.addUser(userProfile.getLogin(), userProfile.getPass(), userProfile.getEmail());
    }

    public UserProfile getUserByLogin(String login) {
        UsersDataSet dataSet = dbService.getUserByLogin(login);
        if (dataSet != null) {
            return new UserProfile(dataSet.getLogin(), dataSet.getPassword());
        } else {
            return null;
        }
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
