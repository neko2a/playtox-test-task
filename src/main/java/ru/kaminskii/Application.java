package ru.kaminskii;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.tinylog.Logger;

import java.util.ArrayList;

public class Application {
    private static final int ID_LENGTH = 10;
    private static final int START_ACCOUNT_MONEY = 10000;
    private static final int TRANSFERS_AMOUNT = 15;
    private static final int MIN_MONEY_TRANSFER = 50;
    private static final int MAX_MONEY_TRANSFER = 1000;

    public static void main(String[] args) {
        ArrayList<Account> accounts = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            accounts.add(new Account(RandomStringUtils.randomAlphanumeric(ID_LENGTH), START_ACCOUNT_MONEY));
        }

        generateTransferThread(accounts.get(0), accounts.get(1)).start();
        generateTransferThread(accounts.get(2), accounts.get(3)).start();
    }

    private static Thread generateTransferThread(Account from, Account to) {
        return new Thread(() -> {
            for (int i = 0; i < TRANSFERS_AMOUNT; i++) {
                try {
                    Thread.sleep(RandomUtils.nextInt(1000, 2000));
                } catch (InterruptedException e) {
                    Logger.error(e.getMessage());
                }
                new Transfer(from, to, RandomUtils.nextInt(MIN_MONEY_TRANSFER, MAX_MONEY_TRANSFER)).run();
            }
        });
    }
}
