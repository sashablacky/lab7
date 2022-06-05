package banana0081.lab6.commands;

public interface Commandable {
    /**
     * adds command
     * @param key command name
     * @param cmd command callback
     */
    void addCommand(String key, Command cmd);
    
    /**
     * executes command with argument
     * @param key command name
     * @param arg
     */
    void runCommand(String key, String arg);

    /**
     * executes command without argument
     * @param key
     */
    void runCommand(String key);

    boolean hasCommand(String s);

    /**
     * runs in command interpreter in console
     */
    void consoleMode();

    /**
     * executes script from file
     * @param path
     */
    void fileMode(String path);
}
