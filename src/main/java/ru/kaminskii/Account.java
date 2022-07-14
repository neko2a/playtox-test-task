package ru.kaminskii;

import lombok.Getter;
import org.tinylog.Logger;

@Getter
public class Account {
    private final String id;
    private int money;

    public Account(String id, int money) {
        if (id.isBlank()) {
            throw new IllegalArgumentException("Account id must not be empty.");
        }
        if (money < 0) {
            throw new IllegalArgumentException("Money must be greater than 0.");
        }
        this.id = id;
        this.money = money;
        Logger.info("Created account " + this.id + " with " + this.money + " money");
    }

    public synchronized void debit(int amount) {
        if (amount < 0) {
            String message = "The amount of money to be deducted must be greater than 0.";
            throw new IllegalArgumentException(message);
        }
        if (money < amount) {
            String message = "There are not enough funds on account " + this.id;
            throw new IllegalStateException(message);
        } else {
            money -= amount;
        }
    }

    public synchronized void replenishment(int amount) {
        if (amount < 0) {
            String message = "The amount of money to replenish must be greater than 0.";
            Logger.error(message);
            throw new IllegalArgumentException(message);
        }
        money += amount;
    }
}
