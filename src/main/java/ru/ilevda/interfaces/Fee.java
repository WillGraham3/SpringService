package ru.ilevda.interfaces;

import ru.ilevda.model.User;

import java.util.List;

public interface Fee {
    List<User> paymentFee(List<User> list);
}
