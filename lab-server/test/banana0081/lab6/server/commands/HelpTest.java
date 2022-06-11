package banana0081.lab6.server.commands;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelpTest {
    String expected = String.join("\n", "help : вывести справку по доступным командам",
            "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)",
            "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении",
            "add {element} : добавить новый элемент в коллекцию",
            "update id {element} : обновить значение элемента коллекции, id которого равен заданному",
            "remove_by_id id : удалить элемент из коллекции по его id",
            "clear : очистить коллекцию",
            "save : сохранить коллекцию в файл",
            "load : прочитать коллекцию из файла",
            "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.",
            "exit : завершить программу (без сохранения в файл)",
            "remove_first : удалить первый элемент из коллекции",
            "remove_last : удалить последний элемент из коллекции",
            "shuffle : перемешать элементы коллекции в случайном порядке",
            "sum_of_minutes_of_waiting : вывести сумму значений поля minutesOfWaiting для всех элементов коллекции",
            "min_by_minutes_of_waiting : вывести любой объект из коллекции, значение поля minutesOfWaiting которого является минимальным",
            "print_unique_impact_speed : вывести уникальные значения поля impactSpeed всех элементов в коллекции)");

}