package ru.margarite.volkova;

public abstract class AbstractPage<T extends AbstractPage>{
    protected abstract T checkPageLoaded();
}
