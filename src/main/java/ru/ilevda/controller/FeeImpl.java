package ru.ilevda.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import ru.ilevda.interfaces.Fee;
import ru.ilevda.model.User;

@Controller
class FeeImpl implements Fee {

    public List<User> paymentFee(List<User> list) {
        return list.stream().map(user -> {
            final LocalDate today = LocalDate.now();
            today.format(DateTimeFormatter.ofPattern("d-MM-uuuu"));
            double balance = Double.parseDouble(user.getBalance());
            double dailyFee = Double.parseDouble(user.getDailyFee());
            int day = (int) (balance / dailyFee);
            String whenServiceOver;
            if (day == 0)
                whenServiceOver = today.minusDays(1).toString();
            else whenServiceOver = today.plusDays(day).toString();

            user.setDayWhenServiceIsOver(whenServiceOver);
            return user;
        }).collect(Collectors.toList());
    }
}
