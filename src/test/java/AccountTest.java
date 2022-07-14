import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.kaminskii.Account;

public class AccountTest {
    @Test
    public void emptyIdTest() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Account("", 100));
        Assertions.assertEquals("Account id must not be empty.", exception.getMessage());
    }

    @Test
    public void spaceIdTest() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Account(" ", 100));
        Assertions.assertEquals("Account id must not be empty.", exception.getMessage());
    }

    @Test
    public void negativeStartBalanceTest() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Account("testString", -10));
        Assertions.assertEquals("Money must be greater than 0.", exception.getMessage());
    }

    @Test
    public void notEnoughMoneyTest() {
        Exception exception = Assertions.assertThrows(IllegalStateException.class,
                () -> new Account("testString", 100).debit(150));
        Assertions.assertEquals("There are not enough funds on account testString", exception.getMessage());
    }

    @Test
    public void negativeDebitAmountTest() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Account("testString", 100).debit(-150));
        Assertions.assertEquals("The amount of money to be deducted must be greater than 0.", exception.getMessage());
    }

    @Test
    public void negativeReplenishmentAmountTest() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Account("testString", 100).replenishment(-150));
        Assertions.assertEquals("The amount of money to replenish must be greater than 0.", exception.getMessage());
    }

    @Test
    public void replenishmentTest() {
        int startMoney = 1000;
        int addMoney = 120;
        Account account = new Account("testString", startMoney);
        Assertions.assertEquals(startMoney, account.getMoney());
        account.replenishment(addMoney);
        Assertions.assertEquals(startMoney + addMoney, account.getMoney());
    }

    @Test
    public void debitTest() {
        int startMoney = 1000;
        int addMoney = 120;
        Account account = new Account("testString", startMoney);
        Assertions.assertEquals(startMoney, account.getMoney());
        account.debit(addMoney);
        Assertions.assertEquals(startMoney - addMoney, account.getMoney());
    }

    @Test
    public void createAccountTest() {
        int startMoney = 1000;
        String id = "testString";
        Account account = new Account(id, startMoney);
        Assertions.assertEquals(id, account.getId());
        Assertions.assertEquals(startMoney, account.getMoney());
    }
}
