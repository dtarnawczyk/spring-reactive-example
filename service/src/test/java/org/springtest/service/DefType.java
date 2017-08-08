package org.springtest.service;

public enum DefType {

    ANNOTATION(){
        @Override
        public String getPath() {
            return "/users";
        }
    },
    FUNCTIONAL(){
        @Override
        public String getPath() {
            return "/usrs";
        }
    };

    public abstract String getPath();
}
