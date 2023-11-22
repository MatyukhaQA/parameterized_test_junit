package tests.data;

public enum Language {
    ENGLISH("Install Steam"), DEUTSCH("Steam installieren");

    public final String description;

    Language(String description) {
        this.description = description;
    }
}
