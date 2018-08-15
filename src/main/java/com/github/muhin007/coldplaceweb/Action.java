package com.github.muhin007.coldplaceweb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
    void action(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
