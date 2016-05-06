package com.mycompany.xyz.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringEscapeUtils;
import static org.apache.commons.lang.StringUtils.isBlank;

/**
 * Provides string manipulation functions
 */
public class StringUtils {

    private final static String DEFAULT_ENCODING = "UTF-8";

    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public StringUtils() {
    }

    public String removeAllButChars(String input) {
        String reply = "";

        for (int i = 0; i < input.length(); i++) {
            if (Character.isLetter(input.charAt(i))) {
                reply = reply + input.substring(i, i + 1);
            }
        }
        return reply;
    }

    public String removeAllButCharsAndNums(String input) {
        String reply = "";

        for (int i = 0; i < input.length(); i++) {
            if (Character.isLetterOrDigit(input.charAt(i)) || input.charAt(i) == ' ') {
                reply = reply + input.substring(i, i + 1);
            }
        }
        return reply;
    }

    public boolean isNumeric(String input) {

        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean isDecimal(String input) {

        for (int i = 0; i < input.length(); i++) {
            if (!(Character.isDigit(input.charAt(i)) || input.charAt(i) == '-' || input.charAt(i) == '.')) {
                return false;
            }
        }
        return true;
    }

    public String checkOneSpace(String input) {
        if (input == null || input.equals("")) {
            return " ";
        }
        return input;
    }

    public String processAmpsAndGtLt(String input) {
        return processGtLt(processAmps(input));
    }

    public String RemoveAmpsAndGtLt(String input) {
        /*
         *      Removes all instances of &lt and &gt and replaces with < and >
         */
        int p;
        String work = input;
        StringBuffer out = new StringBuffer();
        p = work.indexOf("&lt;");
        while (p >= 0) {
            out.append(work.substring(0, p)).append("<");
            work = work.substring(p + 4);
            p = work.indexOf("&lt;");
        }
        out.append(work);

        work = out.toString();
        out = new StringBuffer();
        p = work.indexOf("&gt;");
        while (p >= 0) {
            out.append(work.substring(0, p)).append(">");
            work = work.substring(p + 4);
            p = work.indexOf("&gt;");
        }
        out.append(work);

        return out.toString();
    }

    public String processAmps(String input) {
        int p;
        String work = input;
        StringBuffer out = new StringBuffer();

        p = work.indexOf("&");
        while (p >= 0) {
            out.append(work.substring(0, p)).append("&amp;");
            work = work.substring(p + 1);
            p = work.indexOf("&");
        }
        out.append(work);

        work = out.toString();
        out = new StringBuffer();
        p = work.indexOf("'");
        while (p >= 0) {
            out.append(work.substring(0, p)).append("&#39;");
            work = work.substring(p + 1);
            p = work.indexOf("'");
        }
        out.append(work);

        work = out.toString();
        out = new StringBuffer();
        p = work.indexOf('"');
        while (p >= 0) {
            out.append(work.substring(0, p)).append("&quot;");
            work = work.substring(p + 1);
            p = work.indexOf('"');
        }
        out.append(work);

        return out.toString();
    }

    public String processGtLt(String input) {
        int p;
        String work = input;
        StringBuffer out = new StringBuffer();

        p = work.indexOf("<");
        while (p >= 0) {
            out.append(work.substring(0, p)).append("&lt;");
            work = work.substring(p + 1);
            p = work.indexOf("<");
        }
        out.append(work);

        work = out.toString();
        out = new StringBuffer();
        p = work.indexOf(">");
        while (p >= 0) {
            out.append(work.substring(0, p)).append("&gt;");
            work = work.substring(p + 1);
            p = work.indexOf(">");
        }
        out.append(work);

        return out.toString();
    }

    public String processJSSafe(String input) {
        int p;
        String work = input;
        StringBuffer out = new StringBuffer();

        p = work.indexOf("&");
        while (p >= 0) {
            out.append(work.substring(0, p)).append("\\&");
            work = work.substring(p + 1);
            p = work.indexOf("&");
        }
        out.append(work);

        work = out.toString();
        out = new StringBuffer();
        p = work.indexOf("'");
        while (p >= 0) {
            out.append(work.substring(0, p)).append("\\'");
            work = work.substring(p + 1);
            p = work.indexOf("'");
        }
        out.append(work);

        work = out.toString();
        out = new StringBuffer();
        p = work.indexOf('"');
        while (p >= 0) {
            out.append(work.substring(0, p));
            work = work.substring(p + 1);
            p = work.indexOf('"');
        }
        out.append(work);

        return out.toString();
    }

    public String formatSecondsToHex(int seconds, int desired_length) {
        String hex = Integer.toHexString(seconds);
        for (int i = hex.length(); i < desired_length; i++) {
            hex = "0" + hex;
        }
        return hex;
    }

    public String makeCharSafe(String input) {
        if (input == null) {
            return null;
        }
        // removes anything other than alphanumeric or the following chars -+@.&()
        return input.replaceAll("[^a-zA-Z0-9-+@.&()]", " ");
    }

    public String makeEmailAddressSafe(String input) {
        // like makeCharSafe but for email addresses
        return input.replaceAll("[^a-zA-Z0-9-+@.&(),_:;<>!$*]", "");
    }

    public static int countMatches(String str, String sub) {
        if (isEmpty(str) || isEmpty(sub)) {
            return 0;
        }
        int count = 0;
        int idx = 0;
        while ((idx = str.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length();
        }
        return count;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * utility method to validate email addresses.
     *
     */
    public static boolean isValidEmail(String inputEmail) {

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(inputEmail);
        return matcher.matches();
    }

    /**
     * <p>
     * Escapes the characters in a <code>String</code> using JavaScript String
     * rules.</p>
     * <p>
     * Escapes any values it finds into their JavaScript String form. Deals
     * correctly with quotes and control-chars (tab, backslash, cr, ff, etc.)
     * </p>
     *
     * <p>
     * So a tab becomes the characters <code>'\\'</code> and
     * <code>'t'</code>.</p>
     *
     * <p>
     * The only difference between Java strings and JavaScript strings is that
     * in JavaScript, a single quote must be escaped.</p>
     *
     * <p>
     * Example:
     * <pre>
     * input string: He didn't say, "Stop!"
     * output string: He didn\'t say, \"Stop!\"
     * </pre>
     * </p>
     *
     * @param str String to escape values in, may be null
     * @return String with escaped values, otherwise returns empty string if
     * given value is null
     */
    public static String escapeJS(String value) {
        if (value == null) {
            return "";
        }
        return StringEscapeUtils.escapeJavaScript(value);
    }

    /**
     * Normalise given string of special characters and white spaces.
     *
     * In case if is given value null, will be returned empty string. Never
     * null. The method escape all non-alphanumeric characters and replace them
     * with "-";
     *
     * Example:
     *
     * {@code
     * 	from "I have to pay $50.50" to "i-have-to-pay-50-50"
     * }
     * @
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     * param string
     * @return normalised value
     */
    public static String normalize(final String string) {
        return normalize(string, "-");
    }

    public static String normalize(final String string, final String dividingSymbol) {
        if (isBlank(string)) {
            return "";
        }
        return Normalizer.normalize(string.toLowerCase().trim(), Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replaceAll("[^\\p{Alnum}]+", dividingSymbol);
    }

    /**
     * Encode given filename to valid URL format
     *
     *
     * @param filename
     * @return
     */
    public static String filenameEncode(final String filename) {
        return filenameEncodeDecode(filename, true);
    }

    /**
     * Decode given filename to valid URL format
     *
     * @param filename
     * @return
     */
    public static String filenameDecode(final String filename) {
        return filenameEncodeDecode(filename, false);
    }

    private static String filenameEncodeDecode(final String filename, boolean isEncoding) {
        if (isBlank(filename)) {
            return "";
        }
        String path = FilenameUtils.getPathNoEndSeparator(filename);
        String fileNameWithoutExt = FilenameUtils.getBaseName(filename);
        String ext = FilenameUtils.getExtension(filename);
        String result = (isBlank(path) ? "" : "/" + path + "/");
        try {
            if (isEncoding) {
                result += URLEncoder.encode(fileNameWithoutExt, DEFAULT_ENCODING);
            } else {
                result += URLDecoder.decode(fileNameWithoutExt, DEFAULT_ENCODING);
            }
            return result + (isBlank(ext) ? "" : "." + ext);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String firstLetterDown(final String text) {
        if (text != null && text.length() > 0) {
            return text.substring(0, 1).toLowerCase() + text.substring(1);
        }
        return null;
    }

    /**
     * Converts list of Double lists to the format [data, data] | [data,data]
     * example : [51.54659484072629, -0.13475418090820312]|[51.54659484072629,
     * -0.13475418090820312]
     *
     * @param lists
     * @return String
     */
    public static String toString(List<List<Double>> lists) {
        if (null == lists) {
            return null;
        }
        StringBuilder str = new StringBuilder();
        boolean isFirst = true;
        for (List<Double> list1 : lists) {
            if (!isFirst) {
                str.append("|");
            } else {
                isFirst = false;
            }
            str.append(list1.toString());
        }

        return str.toString();
    }

    /**
     * converts string with format format [data, data] | [data,data] example :
     * [51.54659484072629, -0.13475418090820312]|[51.54659484072629,
     * -0.13475418090820312] to list of double lists
     *
     * @param listStr
     * @return List<List<Double>>
     */
    public static List<List<Double>> toList(String listStr) {
        List<List<Double>> list1 = new ArrayList();
        if (StringUtils.isEmpty(listStr)) {
            return list1;
        }
        String[] strAry1 = listStr.split("\\|");
        for (String str1 : strAry1) {
            String[] strAry2 = str1.replaceAll("\\[|\\]", "").split("\\,");
            List<Double> list2 = Arrays.asList(strAry2).stream().map(Double::valueOf).collect(Collectors.toList());
            list1.add(list2);
        }
        return list1;
    }

}
