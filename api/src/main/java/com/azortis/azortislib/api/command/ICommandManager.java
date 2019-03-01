package com.azortis.azortislib.api.command;

public interface ICommandManager {

    void register(String name, IBaseCommand baseCommand);

    void register(String name, ICommandBuilder commandBuilder);

    void unregister(String name);

}
