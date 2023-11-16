package com.moreno.obituaries.utilities;

import com.moreno.obituaries.App;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Objects;

public class Properties {
    private java.util.Properties properties;
    private static Properties instance;
    private File fileProperties;

    public static Properties getInstance() {
        if (instance == null) {
            instance = new Properties();
        }
        return instance;
    }

    private Properties() {
        initialize();
        loadPropertiesTheme();
    }

    public void initialize() {
        File file = new File(Constants.getUrlProject());
        try {
            if (file.exists() || file.mkdirs()) {
                fileProperties = new File(file.getAbsolutePath() + "/config.properties");
                if (!fileProperties.exists()) {
                    if (fileProperties.createNewFile()) {
                        properties = new java.util.Properties();
                        setKey("2QXDJJUCSSUW2GC");
                        save();
                        setUser("");
                        setPassword("");
                        setFontSize("13");
                        setBackground("#2B2D30");
                        setPanelBackground("#FFFFFF");
                        setAccentColor("#3574F0");
                        setForeground("#000000");
                        setButtonArc("25");
                        setTextComponentArc("25");
                        setComponentHideMnemonics("false");
                        properties.put("background", "@background");
                        properties.put("accentColor", "@accentColor");
                        properties.put("foreground", "@foreground");
                        properties.put("RootPane.titleBarShowIcon", "false");
                        save();
                        Utilities.createLogo(new FileInputStream(Objects.requireNonNull(App.class.getResource("images/logo.png")).getFile()));
                    }
                }
                properties = new java.util.Properties();
                FileInputStream inputStream = new FileInputStream(fileProperties.getAbsolutePath());
                properties.load(inputStream);
                inputStream.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save() {
        try {
            FileOutputStream outputStream = new FileOutputStream(fileProperties);
            properties.store(outputStream, null);
            outputStream.flush();
            outputStream.close();
            loadPropertiesTheme();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadPropertiesTheme() {
        try {
            File fileFlatLaf = new File(Objects.requireNonNull(App.class.getResource("themes/FlatLaf.properties")).toURI());
            FileOutputStream outputStream = new FileOutputStream(fileFlatLaf);
            properties.store(outputStream, null);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //properties

    public String getKey() {
        return properties.getProperty("@key");
    }

    public void setKey(String key) {
        properties.put("@key", key);
    }

    public void setFontSize(String fontSize) {
        properties.put("@fontSize", fontSize);
    }

    public Font getFont() {
        Font font = new JTextField().getFont();
        InputStream inputStream = App.class.getResourceAsStream("fonts/Montserrat/static/Montserrat-Regular.ttf");
        if (inputStream != null) {
            try {
                font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            } catch (FontFormatException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        font = font.deriveFont(Float.parseFloat(properties.getProperty("@fontSize")));
        return font;
    }

    public void setUser(String user) {
        properties.put("@user", user);
    }

    public String getUser() {
        return properties.getProperty("@user");
    }

    public void setPassword(String password) {
        properties.put("@password", password.isBlank() ? "" : Auth.encrypt(password));
    }

    public String getPassword() {
        String psw = properties.getProperty("@password");
        return psw.isBlank() ? "" : Auth.decrypt(psw);
    }

    public void setNameCompany(String nameCompany) {
        properties.put("@nameCompany", nameCompany);
    }

    public void setDirectorialResolutionNumber(String directorialResolutionNumber) {
        properties.put("@directorialResolutionNumber", directorialResolutionNumber);
    }

    public void setAddress(String address) {
        properties.put("@address", address);
    }

    public void setPhone(String phone) {
        properties.put("@phone", phone);
    }

    public void setEmail(String email) {
        properties.put("@email", email);
    }

    public String getNameCompany() {
        return properties.getProperty("@nameCompany");
    }

    public String getDirectorialResolutionNumber() {
        return properties.getProperty("@directorialResolutionNumber");
    }

    public String getAddress() {
        return properties.getProperty("@address");
    }

    public String getPhone() {
        return properties.getProperty("@phone");
    }

    public String getEmail() {
        return properties.getProperty("@email");
    }

    //PropertiesFlatLaf

    public void setForeground(String foreground) {
        properties.put("@foreground", foreground);
    }

    public void setAccentColor(String accentColor) {
        properties.put("@accentColor", accentColor);
    }

    public void setPanelBackground(String anelBackground) {
        properties.put("Panel.background", anelBackground);
    }

    public void setBackground(String background) {
        properties.put("@background", background);
    }

    public void setComponentHideMnemonics(String hideMnemonics) {
        properties.put("Component.hideMnemonics", hideMnemonics);
    }

    public void setTextComponentArc(String textComponentArc) {
        properties.put("TextComponent.arc", textComponentArc);
    }

    public void setButtonArc(String ButtonArc) {
        properties.put("Button.arc", ButtonArc);
    }

}
