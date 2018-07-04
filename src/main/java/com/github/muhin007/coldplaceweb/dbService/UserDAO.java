package com.github.muhin007.coldplaceweb.dbService;

import com.github.muhin007.coldplaceweb.dbService.executor.Executor;

import java.sql.Connection;

class UsersDAO {

    private Executor executor;

    public UsersDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public String getCreateQuery() {
        return "INSERT INTO coldplace.citytemp (id, city, temp) \n" +
                "VALUES (?, ?, ?);";
    }

    public String getUpdateQuery() {
        return "UPDATE coldplace.citytemp SET city= ?, temp = ? WHERE id= ?;";
    }
}

