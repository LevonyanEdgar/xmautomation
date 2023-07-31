package com.xm.pages;

public abstract class ComponentBase<T extends PageBase<T>> extends PageBase<T> {

    public ComponentBase() {
        initPage();
    }

    @Override
    public String getUrl() {
        return "";
    }

    @Override
    public T open() {
        return initPage();
    }

    @Override
    public T init() {
        return initPage();
    }


}