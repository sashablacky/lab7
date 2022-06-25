package banana0081.lab6.abstraction;

import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HttpMethod;

public interface CommandInterface {
    HTTPRequest execute(String nameCommand, HTTPRequest request);
}
