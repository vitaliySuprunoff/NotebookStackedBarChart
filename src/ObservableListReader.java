import javafx.collections.ObservableList;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class ObservableListReader {
        static public void readFileToObservableList(File file, ObservableList data) {
            String s = getStringFromFile(file);
            s = s.substring(1, s.length() - 3);

            String[] lines = s.split(",");
            for (int i = 0; i < lines.length; i++) {
                if (lines[i].charAt(0) == ' ') {
                    lines[i] = lines[i].substring(1, lines[i].length());
                }
                Notebook n = fromString(lines[i]);
                if (n != null) {
                    data.add(n);
                }
            }
        }

        static private Notebook fromString(String s) {
            String[] elements = new String[4];
            for (int i = 0; i < 4; i++) elements[i] = "";
            int pos = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '&') {
                    pos++;
                    i++;
                }
                if (pos == 4) return null;
                elements[pos] += s.charAt(i);
            }
            for (int i = 0; i < 4; i++)
                elements[i] = elements[i].replaceAll("[\\x00-\\x1F]", "");//очистка от управляющих символов для корректного парсинга

                return new Notebook(elements[0], elements[1], elements[2], elements[3]);
        }

        static private String getStringFromFile(File file) {
            String s = "";
            try {
                Scanner in = new Scanner(file);
                while (in.hasNext())
                    s += in.nextLine() + "\r\n";
                in.close();
            } catch (FileNotFoundException e) {
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            return s;
        }

    }