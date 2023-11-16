package com.moreno.obituaries.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moreno.Notification;
import com.moreno.Notify;
import com.moreno.NotifyLocation;
import com.moreno.NotifyType;
import com.moreno.obituaries.data.models.User;
import com.moreno.obituaries.utilities.Utilities;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.Set;

public class Hibernate {
    public static boolean first = true;
    public static AuthInterceptor interceptor = null;
    public static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    private static String token = null;
    public static User user = null;
    public static Session session;
    protected static CriteriaBuilder builder;
    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator programValidator = factory.getValidator();

    public static void setToken(String token) {
        Hibernate.token = token;
        verifyStatus();
    }

    public static void verifyStatus() {
        if (Utilities.lblStatus != null) {
            if (Hibernate.token != null) {
                Utilities.lblStatus.setText("ONLINE");
                Utilities.lblStatus.setBackground(Color.green);
            } else {
                Utilities.lblStatus.setText("OFFLINE");
                Utilities.lblStatus.setBackground(Color.red);
            }
        }
    }

    public static String getToken() {
        return token;
    }

    private static void buildSessionFactory() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        session = sessionFactory.openSession();
        builder = session.getCriteriaBuilder();
    }

    public static void initialize() {
        buildSessionFactory();
        interceptor = new AuthInterceptor();
    }

    public void refresh() {
        session.beginTransaction();
        session.refresh(this);
        session.getTransaction().commit();
    }

    public Boolean save() {
        Set<ConstraintViolation<Hibernate>> errors = programValidator.validate(this);
        if (errors.isEmpty()) {
            session.beginTransaction();
            session.persist(this);
            session.getTransaction().commit();
            return true;
        } else {
            StringBuilder errorsString = new StringBuilder();
            for (ConstraintViolation<Hibernate> constraintViolation : errors) {
                errorsString.append(constraintViolation.getMessage()).append(", ");
            }
            errorsString = new StringBuilder(errorsString.substring(0, errorsString.toString().length() - 2));
            String error = "Verifique los campos: " + errorsString;
            Notify.sendNotify(new Notification(NotifyType.WARNING, NotifyLocation.TOP_RIGHT, "Advertencia", error));
            return false;
        }
    }

    public <T> void dump(T t) {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (!field.getName().equals("id") && field.get(t) != null) {
                    field.set(this, field.get(t));
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void close() {
        session.close();
    }
}
