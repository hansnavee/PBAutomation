package Utils;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class XMLFileUtility {

    private static final Logger logger = LogManager.getLogger(XMLFileUtility.class);

    private static final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    public static Map<String, String> getUsers(String role) {
        Map<String, String> users = new HashMap<>();
        String prefix = envPrefix(role);

        try {
            File file = configFile();
            if (!file.exists()) {
                logger.error("Config file not found at path: {}", file.getAbsolutePath());
                Allure.step("Config file not found at: " + file.getAbsolutePath());
                return usersFromEnvOnly(prefix);
            }

            Document document = parse(file);
            Element environment = selectedEnvironment(document);
            String environmentName = environment == null ? "default" : environmentName(environment);
            Element user = findUser(environment == null ? document.getDocumentElement() : environment, role);

            String rawUsername = user == null ? "" : text(user, "Username").trim();
            String username = resolve(rawUsername);
            if (username.isEmpty() && (rawUsername.isEmpty() || !rawUsername.startsWith("${"))) {
                username = getenv(prefix + "_USERNAME");
            }
            String password = firstNonEmpty(
                    getenv(prefix + "_PASSWORD"),
                    user == null ? "" : resolve(text(user, "Password")),
                    sharedPassword(document)
            );

            if (!username.isEmpty()) {
                users.put("Username", username);
            }
            if (!password.isEmpty()) {
                users.put("Password", password);
            }

            if (username.isEmpty() || password.isEmpty()) {
                logger.warn("Incomplete credentials for role '{}' in environment '{}'", role, environmentName);
            } else {
                logger.info("Using credentials for role '{}' in environment '{}'", role, environmentName);
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            logger.error("Error while reading user credentials: {}", e.getMessage(), e);
            Allure.step("Error while reading user credentials: " + e.getMessage());
            return usersFromEnvOnly(prefix);
        }

        return users;
    }

    public static String getURL() {
        String envUrl = getenv("PB_URL");
        if (!envUrl.isEmpty()) {
            logger.info("Using application URL from PB_URL");
            return envUrl;
        }

        String url = "";
        try {
            File file = configFile();
            if (!file.exists()) {
                logger.error("Config file not found at path: {}", file.getAbsolutePath());
                Allure.step("Config file not found at: " + file.getAbsolutePath());
                return url;
            }
            Document document = parse(file);
            Element environment = selectedEnvironment(document);
            if (environment != null) {
                url = resolve(text(environment, "URL"));
            }
            if (url.isEmpty()) {
                url = directChildText(document.getDocumentElement(), "URL");
            }
            if (url.isEmpty()) {
                logger.warn("URL is empty. Set PB_URL or src/test/resources/config/environment.local.xml");
            } else {
                logger.info("Application URL retrieved successfully");
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            logger.error("Error while reading URL: {}", e.getMessage(), e);
            Allure.step("Error while reading URL: " + e.getMessage());
        }
        return url;
    }

    public static String getBrowserName() {
        String property = System.getProperty("browser");
        if (property != null && !property.isBlank()) {
            return property.trim();
        }
        String envBrowser = getenv("PB_BROWSER");
        if (!envBrowser.isEmpty()) {
            return envBrowser;
        }
        try {
            File file = configFile();
            if (!file.exists()) {
                return "chrome";
            }
            Document document = parse(file);
            NodeList nodes = document.getElementsByTagName("BrowserName");
            if (nodes.getLength() > 0) {
                String name = resolve(nodes.item(0).getTextContent());
                if (!name.isEmpty()) {
                    return name;
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            logger.error("Error while reading browser name: {}", e.getMessage(), e);
        }
        return "chrome";
    }

    private static Map<String, String> usersFromEnvOnly(String prefix) {
        Map<String, String> users = new HashMap<>();
        String username = getenv(prefix + "_USERNAME");
        String password = firstNonEmpty(getenv(prefix + "_PASSWORD"), getenv("PB_PASSWORD"));
        if (!username.isEmpty()) {
            users.put("Username", username);
        }
        if (!password.isEmpty()) {
            users.put("Password", password);
        }
        return users;
    }

    private static Element selectedEnvironment(Document document) {
        NodeList environments = document.getElementsByTagName("Environment");
        if (environments.getLength() == 0) {
            return null;
        }

        String requestedName = getenv("PB_ENV");
        String requestedUrl = normalizeUrl(getenv("PB_URL"));
        Element firstUsable = null;
        Element urlMatch = null;

        for (int i = 0; i < environments.getLength(); i++) {
            Node node = environments.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Element environment = (Element) node;
            if (firstUsable == null && !resolve(text(environment, "URL")).isEmpty()) {
                firstUsable = environment;
            }
            if (!requestedName.isEmpty() && requestedName.equalsIgnoreCase(environmentName(environment))) {
                logger.info("Selected environment '{}'", environmentName(environment));
                return environment;
            }
            if (urlMatch == null && !requestedUrl.isEmpty()
                    && requestedUrl.equals(normalizeUrl(resolve(text(environment, "URL"))))) {
                urlMatch = environment;
            }
        }

        if (urlMatch != null) {
            logger.info("Selected environment '{}' from PB_URL", environmentName(urlMatch));
            return urlMatch;
        }

        if (!requestedName.isEmpty() || !requestedUrl.isEmpty()) {
            logger.warn("No environment matched PB_ENV='{}' or PB_URL. Using the first configured environment.", requestedName);
        }
        return firstUsable == null ? (Element) environments.item(0) : firstUsable;
    }

    private static Element findUser(Element scope, String role) {
        NodeList users = scope.getElementsByTagName("User");
        for (int i = 0; i < users.getLength(); i++) {
            Node node = users.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Element user = (Element) node;
            if (text(user, "Role").equalsIgnoreCase(role)) {
                return user;
            }
        }
        return null;
    }

    private static String sharedPassword(Document document) {
        return firstNonEmpty(getenv("PB_PASSWORD"), directChildText(document.getDocumentElement(), "Password"));
    }

    private static String environmentName(Element environment) {
        String name = environment.getAttribute("name");
        return name == null ? "" : name.trim();
    }

    private static String directChildText(Element parent, String tag) {
        NodeList children = parent.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE && tag.equals(node.getNodeName())) {
                return resolve(node.getTextContent());
            }
        }
        return "";
    }

    private static String firstNonEmpty(String... values) {
        for (String value : values) {
            if (value != null && !value.isBlank()) {
                return value.trim();
            }
        }
        return "";
    }

    private static String normalizeUrl(String url) {
        if (url == null) {
            return "";
        }
        String trimmed = url.trim();
        while (trimmed.endsWith("/")) {
            trimmed = trimmed.substring(0, trimmed.length() - 1);
        }
        return trimmed;
    }

    private static File configFile() {
        File local = Paths.get(
                System.getProperty("user.dir"),
                "src", "test", "resources", "config", "environment.local.xml"
        ).toFile();
        if (local.exists()) {
            return local;
        }
        return Paths.get(
                System.getProperty("user.dir"),
                "src", "test", "resources", "config", "environment.xml"
        ).toFile();
    }

    private static Document parse(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(file);
        document.getDocumentElement().normalize();
        return document;
    }

    private static String text(Element element, String tag) {
        NodeList nodes = element.getElementsByTagName(tag);
        if (nodes.getLength() == 0 || nodes.item(0) == null) {
            return "";
        }
        return nodes.item(0).getTextContent();
    }

    static String resolve(String value) {
        if (value == null) {
            return "";
        }
        String trimmed = value.trim();
        if (trimmed.startsWith("${") && trimmed.endsWith("}") && trimmed.length() > 3) {
            return getenv(trimmed.substring(2, trimmed.length() - 1));
        }
        return trimmed;
    }

    private static String envPrefix(String role) {
        if (role == null) {
            return "PB_USER";
        }
        return switch (role.trim().toLowerCase()) {
            case "operator user" -> "PB_OPERATOR";
            case "guest user" -> "PB_GUEST";
            case "admin user" -> "PB_ADMIN";
            case "invalid user" -> "PB_INVALID";
            default -> "PB_" + role.trim().toUpperCase().replace(' ', '_');
        };
    }

    private static String getenv(String name) {
        String value = System.getenv(name);
        return value == null ? "" : value.trim();
    }
}
