package com.tms.api.users.util;

public class Constant {
    public static final String BASE = "";
    //ADMIN role
    public static final String PATH_USERS = Constant.BASE + "/users";
    public static final String PATH_VARIABLE_ID = "/{id}";
    //USER role
    public static final String PATH_LOGIN = "/login";
    public static final String PATH_REGISTER = "/register";
    public static final String PATH_USER_DETAILS = "/me";
    //TESTS Client
    public static final String TEST_SERVICE_NAME = "tests";
    public static final String TESTS_PATH = "/features/scenarios/user";


}
