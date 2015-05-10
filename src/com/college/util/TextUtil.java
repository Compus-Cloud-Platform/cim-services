/*
 * Copyright (c) 2015 NeuLion, Inc. All Rights Reserved.
 */
package com.college.util;

import java.util.Random;

public class TextUtil
{
    static char[] m_hex = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F' };

    static char[] m_alphanumeric = { '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
            'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z' };

    private static Random random = new Random();

    /**
     * get the alphanumeric string with the specific length.
     * @param length the length which is 8 by default.
     */
    public static String getRandomString(int length)
    {
        if (length <= 0)
            length = 8;
        byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        StringBuffer sb = new StringBuffer(length);
        for (int i = 0; i < length; i++)
        {
            sb.append(m_alphanumeric[Math.abs(bytes[i] % 36)]);
        }
        return sb.toString();
    }

    /**
     * get empty if string is null
     * @param string
     * @return empty string if string is null, otherwise keep old value
     */
    public static String nullToEmpty(String string)
    {
        return (string == null) ? "" : string;
    }

    /**
     * get null if string is empty or null
     * @param string
     * @return null if string is null or empty, otherwise keep old value
     */
    public static String emptyToNull(String string)
    {
        return TextUtil.isEmpty(string) ? null : string;
    }

    /**
     * Tests to see if a string is empty.
     * @param s the string to test.
     * @return true if string is null or blank.
     */
    public static boolean isEmpty(String s)
    {
        return (s == null || s.length() == 0 || s.trim().length() == 0);
    }
    
    /**
     * Pads a string to the desired length.
     * @param s the string.
     * @param len the desired length.
     * @param ch the character used for padding
     * @return the padded string.
     */
    public static String lpad(String s, int len, char ch)
    {
        if (s == null || s.length() >= len)
            return s;
        
        StringBuffer buf = new StringBuffer(len);
        for (len -= s.length(); len > 0; len--)
            buf.append(ch);
        
        buf.append(s);
        return buf.toString();
    }
    
    /**
     * Pads a string to the desired length.
     * @param s the string.
     * @param len the desired length.
     * @param ch the character used for padding
     * @return the padded string.
     */
    public static String rpad(String s, int len, char ch)
    {
        if (s == null || s.length() >= len)
            return s;
        
        StringBuffer buf = new StringBuffer(len);
        buf.append(s);
        for (len -= s.length(); len > 0; len--)
            buf.append(ch);
        
        return buf.toString();
    }
    
    /**
     * Left trims a string.
     * @param s the string to ltrim.
     * @return a trimmed string.
     */
    public static String ltrim(String s)
    {
        if (s == null)
            return null;
        
        for (int k = 0; k < s.length(); k++)
        {
            if (!Character.isWhitespace(s.charAt(k)))
                return (k == 0 ? s : s.substring(k));
        }
        return "";
    }

    /**
     * Right trims a string.
     * @param s the string to rtrim.
     * @return a trimmed string.
     */
    public static String rtrim(String s)
    {
        if (s == null)
            return null;
        
        for (int k = s.length()-1; k >= 0; k--)
        {
            if (!Character.isWhitespace(s.charAt(k)))
                return (k == s.length() - 1 ? s : s.substring(0, k + 1));
        }
        return "";
    }
    
    /**
     * Trims a string.
     * This is equivalent (s == null ? null : s.trim()).
     * @param s the string to ltrim.
     * @return a trimmed string.
     */
    public static String trim(String s)
    {
        return (s == null ? null : s.trim());
    }
    
    /**
     * Trims the string array.
     * @param ss the strings
     * @return the trimmed string array
     */
    public static String[] trim(String[] ss)
    {
        if (ss == null)
            return ss;
        
        for (int i = 0; i < ss.length; i++)
        {
            ss[i] = TextUtil.trim(ss[i]);
        }
        return ss;
    }

    /**
     * Gets the byte length of string
     * @param s
     * @return length for the s.
     */
    public static int getWordCount(String s)
    {
        int length = 0;
        for (int i = 0; i < s.length(); i++)
        {
            int ascii = Character.codePointAt(s, i);
            if (ascii >= 0 && ascii <= 255)
                length++;
            else
                length += 2;
        }
        return length;
    }

    public static int getWordCountWithReg(String s)
    {
        s = s.replaceAll("[^//x00-//xff]", " ** ");
        int length = s.length();
        return length;
    }

    public static void main(String[] args)
    {
        System.out.println(getWordCount("dai��"));
    }
}
