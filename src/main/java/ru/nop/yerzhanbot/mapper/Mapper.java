package ru.nop.yerzhanbot.mapper;

public interface Mapper<SOURCE, TARGET> {
    TARGET map(SOURCE source);
}
