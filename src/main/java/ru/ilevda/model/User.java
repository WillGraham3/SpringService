package ru.ilevda.model;

import java.util.Objects;

public class User {
    private boolean getFee;
    private String phoneNumber;
    private String balance;
    private String dailyFee;
    private String dayWhenServiceIsOver;

    public User() {
    }

    public boolean isGetFee() {
        return getFee;
    }

    public void setGetFee(boolean getFee) {
        this.getFee = getFee;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getDailyFee() {
        return dailyFee;
    }

    public void setDailyFee(String dailyFee) {
        this.dailyFee = dailyFee;
    }

    public String getDayWhenServiceIsOver() {
        return dayWhenServiceIsOver;
    }

    public void setDayWhenServiceIsOver(String dayWhenServiceIsOver) {
        this.dayWhenServiceIsOver = dayWhenServiceIsOver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getFee == user.getFee &&
                phoneNumber.equals(user.phoneNumber) &&
                balance.equals(user.balance) &&
                dailyFee.equals(user.dailyFee) &&
                dayWhenServiceIsOver.equals(user.dayWhenServiceIsOver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFee, phoneNumber, balance, dailyFee, dayWhenServiceIsOver);
    }

    @Override
    public String toString() {
        return "User{" +
                "getFee=" + getFee +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", balance='" + balance + '\'' +
                ", dailyFee='" + dailyFee + '\'' +
                ", dayWhenServiceIsOver='" + dayWhenServiceIsOver + '\'' +
                '}';
    }
}
