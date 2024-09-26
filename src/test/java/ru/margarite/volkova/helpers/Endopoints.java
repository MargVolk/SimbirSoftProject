package ru.margarite.volkova.helpers;

public final class Endopoints {
    private static final String path = "api/";

    public static String CREATE = path + "create";
    public static String DELETE = path + "delete/{id}";
    public static String GET = path + "get/{id}";
    public static String GET_ALL = path + "getAll";
    public static String PATCH = path + "patch/{id}";
}
