package config;

public final class AppConfig {

    private AppConfig() {}

    /* =====================
       ENVIRONMENT
       ===================== */
    public enum Env {
        QA("https://manaerp-qa.netlify.app/"),
        UAT("https://manaerp-uat.netlify.app/"),
        PROD("https://manaerp.com/");

        private final String url;

        Env(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }

    /* =====================
       BROWSER
       ===================== */
    public enum Browser {
        CHROME,
        EDGE,
        FIREFOX
    }

    /* =====================
       GLOBAL SETTINGS
       ===================== */
    public static final Env ENV = Env.QA;
    public static final Browser BROWSER = Browser.CHROME;
    public static final int DEFAULT_TIMEOUT = 20;
}
