package ru.pearx.purmag.common.expressions.operations;

import java.util.Random;
import java.util.function.Function;

public class OperationBuilder<T extends Operation>
{
    private final T base;

    @SuppressWarnings("unchecked")
    public OperationBuilder(T base) {
        this.base = (T) base.clone();
    }

    public OperationBuilder<T> setFormat(String format) {
        base.format = format;
        return this;
    }

    public OperationBuilder<T> setGenerator(Function<Random, Integer> generator) {
        base.generator = generator;
        return this;
    }

    public T getOp() {
        return base;
    }

}
