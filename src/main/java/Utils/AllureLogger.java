package Utils;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public class AllureLogger {

    // ✅ Logs a failed step with stack trace (red in report)
    public static void failStep(String message, Exception e) {
        Allure.step("❌ " + message + " → " + e.getMessage(), Status.FAILED);
        Allure.addAttachment("Error Details", "text/plain",
                getStackTrace(e), StandardCharsets.UTF_8.name());
    }

    // ✅ Logs a warning step (yellow in report)
    public static void warnStep(String message) {
        Allure.step("⚠️ " + message, Status.BROKEN);
    }

    // ✅ Logs a successful step
    public static void passStep(String message) {
        Allure.step("✅ " + message, Status.PASSED);
    }

    private static String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
