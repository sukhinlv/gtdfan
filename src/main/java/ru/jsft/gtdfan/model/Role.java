package ru.jsft.gtdfan.model;

public enum Role {
//public enum Role implements GrantedAuthority {
    USER,
    ADMIN;

    // https://stackoverflow.com/a/19542316/548473
//    @Override
//    public String getAuthority() {
//        return "ROLE_" + name();
//    }
}
