package ru.ilevda.interfaces;

import ru.ilevda.model.User;

import java.util.List;
import java.util.Map;

public interface Operations {
    List<User> parseToUser(List<String[]> list);

    List<String[]> parseToSuccess(List<User> list);

    List<String[]> parseToFailure(List<User> list);

    Map<Boolean, List<User>> spreadingPerson(List<User> list);

}
