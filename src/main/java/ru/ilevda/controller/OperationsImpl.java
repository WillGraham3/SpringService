package ru.ilevda.controller;

import org.springframework.stereotype.Controller;
import ru.ilevda.interfaces.Operations;
import ru.ilevda.model.User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
class OperationsImpl implements Operations {

    public List<User> parseToUser(List<String[]> list) {
        return list.stream().map(array -> {
            User user = new User();
            user.setPhoneNumber(array[0]);
            user.setBalance(array[1]);
            return user;
        }).collect(Collectors.toList());

    }

    public List<String[]> parseToSuccess(List<User> list) {
        return list.stream().map(user -> new String[]{
                user.getPhoneNumber(),
                user.getDayWhenServiceIsOver()}).collect(Collectors.toList());

    }

    public List<String[]> parseToFailure(List<User> list) {
        return list.stream().map(user -> new String[]{
                user.getPhoneNumber()
        }).collect(Collectors.toList());

    }

    public Map<Boolean, List<User>> spreadingPerson(List<User> list) {
        return list.stream().collect(Collectors.groupingBy(User::isGetFee));

    }
}
