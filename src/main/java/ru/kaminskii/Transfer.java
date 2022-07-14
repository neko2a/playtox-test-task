package ru.kaminskii;

import lombok.AllArgsConstructor;
import org.tinylog.Logger;

@AllArgsConstructor
public class Transfer implements Runnable {
    private final Account from;
    private final Account to;
    private final int money;

    @Override
    public void run() {
        String message = String.format("%s(%d) -> %s(%d). Transfer %d coins.",
                from.getId(), from.getMoney(), to.getId(), to.getMoney(), money);
        Logger.info(message);
        boolean success = false;

        try {
            from.debit(money);
            success = true;
        } catch (IllegalStateException | IllegalArgumentException e) {
            Logger.info(message + " FAILED!");
            Logger.error(e.getMessage());
        }

        if (success) {
            to.replenishment(money);
        }
    }
}
