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
        String envUsername = getenv(prefix + "_USERNAME");
        String envPassword = getenv(prefix + "_PASSWORD");

        if (!envUsername.isEmpty() || !envPassword.isEmpty()) {
            users.put("Username", envUsername);
            users.put("Password", envPassword);
            logger.info("Using environment credentials for role '{}'", role);
            return users;
        }

        try {
            File file = configFile();
            logger.info("Reading user credentials for role: {}", role);
            if (!file.exists()) {
                logger.error("Config file not found at path: {}", file.getAbsolutePath());
                Allure.step("Config file not found at: " + file.getAbsolutePath());
                return users;
            }

            Document document = parse(file);
            NodeList nodeList = document.getElementsByTagName("User");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                Element element = (Element) node;
                String xmlRole = text(element, "Role");
                if (!xmlRole.equalsIgnoreCase(role)) {
                    continue;
                }
                users.put("Username", resolve(text(element, "Username")));
                users.put("Password", resolve(text(element, "Password")));
                logger.info("Found credentials for role '{}'", role);
                break;
            }

            if (users.isEmpty()) {
                logger.warn("No matching user found for role: {}", role);
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            logger.error("Error while reading user credentials: {}", e.getMessage(), e);
            Allure.step("Error while reading user credentials: " + e.getMessage());
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
            NodeList nodes = document.getElementsByTagName("URL");
            if (nodes.getLength() > 0) {
                url = resolve(nodes.item(0).getTextContent());
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
