package tests.data;

public enum Language {
    English("Install Steam"), Deutsch("Steam installieren");

    public final String description;

    Language(String description) {
        this.description = description;
    }
}
