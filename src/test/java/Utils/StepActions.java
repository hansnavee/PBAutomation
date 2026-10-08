package Utils;

import io.qameta.allure.Allure;
import org.junit.Assert;

import java.nio.charset.StandardCharsets;

public final class StepActions {

    private StepActions() {
    }

    public static void run(String name, Runnable action) {
        Allure.step(name, () -> {
            try {
                action.run();
            } catch (AssertionError | Exception e) {
                String message = "FAILED: " + name + "\nReason: " + e.getMessage();
                Allure.addAttachment(
                        "Failure Details",
                        "text/plain",
                        message,
                        StandardCharsets.UTF_8.name()
                );
                Assert.fail(message);
            }
        });
    }
}
