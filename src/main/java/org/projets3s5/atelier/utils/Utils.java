package org.projets3s5.atelier.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.util.regex.Pattern;
import java.util.*;

public class Utils {

    // === Gestion des Dates ===

    /**
     * Convertit une chaîne au format yyyy-MM-dd en un objet java.util.Date.
     */
    public static Date stringToUtilDate(String dateStr) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return (Date) formatter.parse(dateStr);
    }
    public static Date convertToDate(String dateString) {
        if (dateString == null || dateString.isEmpty()) {
            return null;  // Retourner null si la date est vide
        }

        // Définir le format de la date attendu dans le String
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Format 'yyyy-MM-dd'

        try {
            // Convertir le String en java.util.Date
            java.util.Date utilDate = sdf.parse(dateString);

            // Convertir en java.sql.Date
            return new Date(utilDate.getTime());
        } catch (ParseException e) {
            // Gérer l'exception si le format de la date est incorrect
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Convertit une chaîne au format yyyy-MM-dd en un objet java.sql.Date.
     */
    public static java.sql.Date stringToSqlDate(String dateStr) throws ParseException {
        Date utilDate = stringToUtilDate(dateStr);
        return new java.sql.Date(utilDate.getTime());
    }

    /**
     * Convertit une java.util.Date en une chaîne au format yyyy-MM-dd.
     */
    public static String utilDateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    /**
     * Convertit une java.sql.Date en une chaîne au format yyyy-MM-dd.
     */
    public static String sqlDateToString(java.sql.Date date) {
        return date != null ? date.toString() : null;
    }

    /**
     * Convertit une java.util.Date en java.sql.Date.
     */
    public static java.sql.Date utilDateToSqlDate(Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * Convertit une java.sql.Date en java.util.Date.
     */
    public static Date sqlDateToUtilDate(java.sql.Date date) {
        return new Date(date.getTime());
    }

    /**
     * Retourne la date actuelle sous forme de java.sql.Date.
     */
    public static java.sql.Date getCurrentSqlDate() {
        return new java.sql.Date(System.currentTimeMillis());
    }

    // === Manipulation des Chaînes ===

    /**
     * Vérifie si une chaîne est vide ou null.
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Capitalise la première lettre d'une chaîne.
     */
    public static String capitalize(String str) {
        if (isNullOrEmpty(str)) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    /**
     * Supprime les espaces inutiles d'une chaîne.
     */
    public static String trimString(String str) {
        return str != null ? str.trim() : null;
    }

    // === Conversions ===

    /**
     * Convertit une chaîne en entier, retourne une valeur par défaut en cas d'erreur.
     */
    public static int stringToInt(String str, int defaultValue) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Convertit une chaîne en double, retourne une valeur par défaut en cas d'erreur.
     */
    public static double stringToDouble(String str, double defaultValue) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    // === Validations ===

    /**
     * Vérifie si une chaîne est un email valide.
     */
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && Pattern.matches(emailRegex, email);
    }

    /**
     * Vérifie si une chaîne contient uniquement des chiffres.
     */
    public static boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }

    // === Gestion des Objets ===

    /**
     * Vérifie si deux objets sont égaux (inclut le cas où les deux sont null).
     */
    public static boolean areEqual(Object obj1, Object obj2) {
        return (obj1 == null && obj2 == null) || (obj1 != null && obj1.equals(obj2));
    }

    /**
     * Retourne une chaîne par défaut si l'objet est null.
     */
    public static String defaultIfNull(Object obj, String defaultValue) {
        return obj != null ? obj.toString() : defaultValue;
    }
}

