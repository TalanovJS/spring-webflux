package ru.vdcom.springwebflux.service;

public class SleepService {
    public static void sleep(Integer ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
