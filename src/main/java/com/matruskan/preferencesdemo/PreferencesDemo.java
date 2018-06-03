package com.matruskan.preferencesdemo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PreferencesDemo extends Application {
    private static final String USERNAME_PROPERTY = "username";
    private static final String FONT_FAMILY_PROPERTY = "fontFamily";
    private static final String FONT_SIZE_PROPERTY = "fontSize";
    private static final String SAVE_DATE_PROPERTY = "saveDate";
    private static final String DEFAULT_FONT_FAMILY = "System";
    private static final Double DEFAULT_FONT_SIZE = 14.0;
    private static final String DEFAULT_SAVE_DATE = "2000-01-01";

    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ISO_DATE;

    private String username = "Matruskan";
    private Font font = Font.getDefault();
    private LocalDate saveDate = LocalDate.now();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        PreferencesDemo preferencesDemo = new PreferencesDemo();
        preferencesDemo.load();
        preferencesDemo.print();
        preferencesDemo.setUsername("Matruskan");
        preferencesDemo.setFont(Font.font("Arial", 12));
        preferencesDemo.save();
        Platform.exit();
    }

    public void load() {
        Preferences userPreferences = Preferences.userRoot();
        username = userPreferences.get(USERNAME_PROPERTY, "");
        String fontFamily = userPreferences.get(FONT_FAMILY_PROPERTY, DEFAULT_FONT_FAMILY);
        Double fontSize = userPreferences.getDouble(FONT_SIZE_PROPERTY, DEFAULT_FONT_SIZE);
        font = Font.font(fontFamily, FontWeight.NORMAL, fontSize);
        String date = userPreferences.get(SAVE_DATE_PROPERTY, DEFAULT_SAVE_DATE);
        saveDate = LocalDate.parse(date, DATETIME_FORMATTER);
    }

    public void print() {
        System.out.println("Username:    " + username);
        System.out.println("Chosen Font: " + font.getFamily() + " " + font.getSize());
        System.out.println("Saved at:    " + saveDate.format(DATETIME_FORMATTER));
    }

    public void save() {
        saveDate = LocalDate.now();
        Preferences userPreferences = Preferences.userRoot();
        userPreferences.put(USERNAME_PROPERTY, username);
        userPreferences.put(FONT_FAMILY_PROPERTY, font.getFamily());
        userPreferences.putDouble(FONT_SIZE_PROPERTY, font.getSize());
        userPreferences.put(SAVE_DATE_PROPERTY, saveDate.format(DATETIME_FORMATTER));
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFont(Font font) {
        this.font = font;
    }
}
