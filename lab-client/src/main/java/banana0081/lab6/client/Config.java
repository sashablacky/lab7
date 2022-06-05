package banana0081.lab6.client;

import banana0081.lab6.collection.CollectionManager;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.commands.CommandManager;

public final class Config {

    private static final String SYS_ENVIRONMENT = "COLLECTION_PATH";
    private static final HumanBeingCollectionManager COLLECTION_MANAGER = new HumanBeingCollectionManager(SYS_ENVIRONMENT);
    private static final CommandManager COMMAND_MANAGER = new CommandManager();

    private Config() {

    }

    public static HumanBeingCollectionManager getCollectionManager() {
        return COLLECTION_MANAGER;
    }

    public static CommandManager getCommandManager() {
        return COMMAND_MANAGER;
    }

    public static String getSystemEnvironment() {
        return SYS_ENVIRONMENT;
    }

    public static String getFilePath() {
        return System.getenv(getSystemEnvironment());
    }
}