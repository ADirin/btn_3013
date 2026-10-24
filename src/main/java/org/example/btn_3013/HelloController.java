package org.example.btn_3013;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.text.NumberFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML private Label timeLabel;
    @FXML private Button btn1;
    @FXML private Button btn2;
    @FXML private Button btn3;
    @FXML private Button btn4;
    @FXML private Label currencyLabel;

    private ResourceBundle rs;


    @FXML
    public void initialize() {
        System.out.println("Initializing Controller...");
        if (btn1 == null || btn2 == null || btn3 == null || btn4 == null) {
            System.err.println("FXML components are NOT properly injected!");
        } else {
            loadLanguage("en", "US"); // Default language
        }
    }


    private void loadLanguage(String language, String country) {
        Locale locale = Locale.forLanguageTag(language+ "-" + country);
        rs = ResourceBundle.getBundle("org.example.btn_3013.Messages_bundle", locale);

        if(btn1 != null && btn2 != null && btn3 != null && btn4 != null && timeLabel !=null && currencyLabel != null) {
            btn1.setText(rs.getString("btn1.text"));
            btn2.setText(rs.getString("btn2.text"));
            btn3.setText(rs.getString("btn3.text"));
            btn4.setText(rs.getString("btn4.text"));
            welcomeText.setText(rs.getString("lbl.text"));
            updateTimeAndCurrencyForLocale(language, country, locale);
        } else {
            System.err.println("FXML injection issue detected!");
        }
    }

    private void updateTimeAndCurrencyForLocale(String language, String country, Locale locale) {
        String zoneId = switch (country) {
            case "FI" -> "Europe/Helsinki";
            case "SE" -> "Europe/Stockholm";
            case "IR" -> "Asia/Tehran";
            case "US" -> "America/New_York";
            default   -> "UTC";
        };


        String dateTimePattern = rs.containsKey("datetime.format") ? rs.getString("datetime.format") : "yyyy-MM-dd HH:mm:ss";

        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of(zoneId));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimePattern, locale);

        String timePrefix = rs.containsKey("time.label") ? rs.getString("time.prefix") : "Current Time: ";

        timeLabel.setText(timePrefix + "("+ zoneId+ ")" + formatter.format(zonedDateTime));

        double sampleAmount = 1234.56;
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        String formattedCurrency = currencyFormatter.format(sampleAmount);

        if (currencyLabel != null) {
            String currencyPrefix = rs.containsKey("currency.label")
                    ? rs.getString("currency.label")
                    : "Currency";
            currencyLabel.setText(currencyPrefix + ": " + formattedCurrency);
        }



    }


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void btnEn(ActionEvent actionEvent) {
        loadLanguage("en", "US");

    }

    public void btnSv(ActionEvent actionEvent) {
        loadLanguage("sv", "SE");
    }

    public void btnFi(ActionEvent actionEvent) {
        loadLanguage("fi", "FI");
    }

    public void btnFa(ActionEvent actionEvent) {
        loadLanguage("fa", "IR");
    }
}
