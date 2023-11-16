package com.moreno.obituaries.utilities;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.moreno.Notification;
import com.moreno.Notify;
import com.moreno.NotifyLocation;
import com.moreno.NotifyType;
import com.moreno.obituaries.ui.custom.TabbedPane;
import org.apache.commons.io.IOUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class Utilities {
    public static String formatDateString = "dd-MM-yyyy";
    public static DateFormat formatDate = new SimpleDateFormat(formatDateString);
    public static DateFormat formatDate3 = new SimpleDateFormat("yyyy-MM-dd");
    public static DateFormat formatDate4 = new SimpleDateFormat("dd-MM");
    public static DateFormat formatDate2 = new SimpleDateFormat("EEEE dd-MM-yyyy");
    public static DateFormat formatDateTime = new SimpleDateFormat("dd/MM/yyyy: h:mm a");
    public static DateFormat formatTime = new SimpleDateFormat("h:mm");
    public static DateFormat formatYear = new SimpleDateFormat("yyyy");
    public static NumberFormat currency = NumberFormat.getCurrencyInstance();
    public static NumberFormat numberFormat = new DecimalFormat("###,###,###.##");
    public static JFrame jFrame;
    public static TabbedPane tabbedPane;
    public static SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
    public static JLabel lblLeft = new JLabel();
    public static JLabel lblRight = new JLabel();
    public static DecimalFormat df = new DecimalFormat("#.##");
    //    public static UiUpdate uiUpdateDialog;
    public static JTextField lblStatus;

    public static String getMessageError(String message) {
        String messageError = "Error verifique el campo: ";
        return switch (message) {
            case "name" -> messageError + "Nombre";
            case "price" -> messageError + "Precio";
            default -> messageError + message;
        };
    }

    public static void updateUI(boolean start) {
        if (start) {
            FlatAnimatedLafChange.showSnapshot();
        } else {
            FlatAnimatedLafChange.hideSnapshotWithAnimation();
        }
    }

    public static Icon resizeIcon(Image image, Double maxWidth, Double maxHeight) {
        if (image == null) {
            return null;
        } else {
            int width = image.getWidth(null);
            int height = image.getHeight(null);

            if (width > maxWidth || height > maxHeight) {
                double percen = Math.min((maxWidth / width), (maxHeight / height));
                width = (int) (percen * width);
                height = (int) (percen * height);
            }

            if (width % 2 != 0) {
                width++;
            }
            if (height % 2 != 0) {
                height++;
            }
            return new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
        }
    }

    public static JSpinner.NumberEditor getEditorPrice(JSpinner spinner) {
        JSpinner.NumberEditor js = new JSpinner.NumberEditor(spinner, "###,###,###.##");
        js.getTextField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                SwingUtilities.invokeLater(() -> {
                    JTextField textField = (JTextField) e.getSource();
                    textField.selectAll();
                });
            }
        });
        return js;
    }

    public static void loadTheme() {
        UIManager.getDefaults().put("defaultFont", Properties.getInstance().getFont());
        FlatLaf.registerCustomDefaultsSource("com.moreno.obituaries.themes");
        FlatMacLightLaf.setup();
    }

    public static void updateComponents(JComponent parent) {
        Font font = parent.getFont();
        parent.updateUI();
        font = ((Font) UIManager.getDefaults().get("defaultFont")).deriveFont(font.getAttributes());
        parent.setFont(font);
        if (parent instanceof JMenu) {
            for (Component component : ((JMenu) parent).getMenuComponents()) {
                updateComponents((JComponent) component);
            }
        } else {
            for (Component component : parent.getComponents()) {
                if (component instanceof JComponent) {
                    updateComponents((JComponent) component);
                }
            }
        }
    }

    public static Date getDateGreaterThan(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.SECOND, -1);
        return calendar.getTime();
    }

    public static Date getDateLessThan(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static boolean priceValid(KeyEvent e, String price) {
        int character = e.getKeyChar();
        if (character == 46) {
            if (price.lastIndexOf('.') == -1) {
                return !price.isEmpty();
            }
            return false;
        } else {
            return character >= 48 && character <= 57;
        }
    }

    public static Date convertLocalTimeToDate(LocalTime time) {
        Instant instant = time.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant();
        return toDate(instant);
    }

    private static Date toDate(Instant instant) {
        BigInteger millis = BigInteger.valueOf(instant.getEpochSecond()).multiply(
                BigInteger.valueOf(1000));
        millis = millis.add(BigInteger.valueOf(instant.getNano()).divide(
                BigInteger.valueOf(1_000_000)));
        return new Date(millis.longValue());
    }

    public static Date localDateToDate(LocalDate dateToConvert) {
        return Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static LocalDate dateToLocalDate(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static String getCodeOfName(String nameProduct) {
        nameProduct = nameProduct.trim();
        boolean stade = true;
        StringBuilder code = new StringBuilder();
        if (!nameProduct.isEmpty()) {
            int aux;
            do {
                code.append(nameProduct.charAt(0));
                aux = nameProduct.indexOf(" ");
                if (aux != -1) {
                    nameProduct = nameProduct.substring(aux + 1);
                } else {
                    stade = false;
                }
            } while (stade);
        }
        return code.toString();
    }

    public static Vector<Class<?>> invertirVector(Vector<Class<?>> vector) {
        Class<?> ventaAUX;
        for (int i = 0; i < vector.size() / 2; i++) {
            ventaAUX = vector.get(i);
            vector.set(i, vector.get(vector.size() - i - 1));
            vector.set((vector.size() - i - 1), ventaAUX);
        }
        return vector;
    }

    public static Integer calcularaños(Date fecha) {
        Calendar hoy = Calendar.getInstance();
        Calendar nacimiento = Calendar.getInstance();
        nacimiento.setTime(fecha);
        int años = hoy.get(Calendar.YEAR) - nacimiento.get(Calendar.YEAR);
        int meses = hoy.get(Calendar.MONTH) - nacimiento.get(Calendar.MONTH);
        int dias = hoy.get(Calendar.DAY_OF_MONTH) - nacimiento.get(Calendar.DAY_OF_MONTH);
        if (meses == 0) {
            if (dias < 0) {
                años -= 1;
            }
        } else {
            if (meses < 0) {
                años -= 1;
            }
        }
        return Math.max(años, 0);
    }


    public static void createLogo(InputStream image) {
        File file = new File(Constants.getUrlProject()+"/logo.png");
        try {
            OutputStream outputStream = new FileOutputStream(file);
            IOUtils.copy(image, outputStream);
        } catch (IOException exception) {
            exception.printStackTrace();
            Notify.sendNotify(new Notification(NotifyType.WARNING, NotifyLocation.TOP_RIGHT, "ERROR", "Ocurrió un error inesperado"));
        }
    }

    public static Image getLogo() {
        File file = new File(Constants.getUrlProject()+"/logo.png");
        return new ImageIcon(file.getAbsolutePath()).getImage();
    }
}
