package com.bytetime.Factory_modle;

public abstract class Factory {

    public abstract <T extends Product> T createProduct(Class<T> clz);
}