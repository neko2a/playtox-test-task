import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.kaminskii.Account;
import ru.kaminskii.Transfer;

public class TransferTest {
    @Test
    public void transferTest() {
        Account accountFrom = new Account("ACC1", 1000);
        Account accountTo = new Account("ACC2", 700);
        new Transfer(accountFrom,accountTo,500).run();
        Assertions.assertEquals(500, accountFrom.getMoney());
        Assertions.assertEquals(1200, accountTo.getMoney());
    }
}
