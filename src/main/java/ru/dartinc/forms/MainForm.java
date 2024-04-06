package ru.dartinc.forms;

import ru.dartinc.dto.BookOutDTO;
import ru.dartinc.service.*;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {
    private static MainForm instance;

    private final JPanel mainPanel = new JPanel();
    private final JLabel bookTitleJLabel = new JLabel("Введите название книги : ");
    private final JTextField bookTitleText = new JTextField(120);
    private final JLabel bookOriginalTitleJLabel = new JLabel("Введите оригинальное название книги :");
    private final JTextField bookOriginalTitleText = new JTextField(120);
    private final JLabel bookYearOfEditionJLabel = new JLabel("Введите год издания книги :");
    private final JTextField bookYearOfEditionText = new JTextField(120);
    private final JLabel bookYearOfEditionTranslateJLabel = new JLabel("Введите год издания перевода книги :");
    private final JTextField bookYearOfEditionTranslateText = new JTextField(120);
    private final JLabel bookIsbnOriginalJLabel = new JLabel("Введите ISBN оригинала книги :");
    private final JTextField bookIsbnOriginalText = new JTextField(120);
    private final JLabel bookIsbnTranslateJLabel = new JLabel("Введите ISBN перевода книги :");
    private final JTextField bookIsbnTranslateText = new JTextField(120);
    private final JLabel bookGenreJLabel = new JLabel("Введите жанр :");
    private final JTextField bookGenreText = new JTextField(120);

    private JLabel bookPubHouseJLabel = new JLabel("Введите название издательства :");
    private JTextField bookPubHouseText = new JTextField(120);

    private JLabel bookPubHouseTranslateJLabel = new JLabel("Введите название издательства перевода :");
    private JTextField bookPubHouseTranslateText = new JTextField(120);
    private JLabel bookTagsJLabel = new JLabel("Введите таги через \";\" :");
    private JTextField bookTagsText = new JTextField(120);

    private JLabel bookAuthorsJLabel = new JLabel("Введите авторов через \";\" :");
    private JTextField bookAuthorsText = new JTextField(120);

    private JLabel bookSeriaJLabel = new JLabel("Введите название серии :");
    private JTextField bookSeriaText = new JTextField(120);

    private JLabel bookPathToFileJLabel = new JLabel("Выберите путь до файла книги :");
    private JTextField bookPathToFileText = new JTextField(120);
    private JButton bookOpenFileButton = new JButton(" Выбрать файл книги ");

    private JLabel bookPathToPicJLabel = new JLabel("Выберите путь до обложки книги :");
    private JTextField bookPathToPicText = new JTextField(120);
    private JButton bookOpenPicButton = new JButton(" Выбрать файл обложки ");

    private JButton bookAddButton = new JButton(" Добавить книгу ");

    private JFileChooser bookFileChooser = new JFileChooser();
    private JFileChooser bookPicFileChooser = new JFileChooser();

    private String authorizationToken;
    private MainForm() {
        this.setTitle("Admin UI for Home Library Server");
        this.setSize(1080, 800);
        this.setLayout(new GridLayout(0, 1, 10, 10));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        createMainPanel();
        this.add(mainPanel);
        setAuthorization();
        this.setVisible(true);

    }

    public String getToken(){
        return authorizationToken;
    }
    private void setAuthorization(){
        this.authorizationToken = AppAthorization.getAuthorizationToken();
    }

    public static MainForm getInstance() {
        if (instance == null) {
            instance = new MainForm();
        }
        return instance;
    }

    private void createMainPanel() {
        mainPanel.setLayout(new GridLayout(28, 0, 10, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(bookTitleJLabel);
        mainPanel.add(bookTitleText);

        mainPanel.add(bookOriginalTitleJLabel);
        mainPanel.add(bookOriginalTitleText);

        mainPanel.add(bookYearOfEditionJLabel);
        mainPanel.add(bookYearOfEditionText);

        mainPanel.add(bookYearOfEditionTranslateJLabel);
        mainPanel.add(bookYearOfEditionTranslateText);

        mainPanel.add(bookIsbnOriginalJLabel);
        mainPanel.add(bookIsbnOriginalText);

        mainPanel.add(bookIsbnTranslateJLabel);
        mainPanel.add(bookIsbnTranslateText);

        mainPanel.add(bookGenreJLabel);
        mainPanel.add(bookGenreText);

        mainPanel.add(bookPubHouseJLabel);
        mainPanel.add(bookPubHouseText);

        mainPanel.add(bookPubHouseTranslateJLabel);
        mainPanel.add(bookPubHouseTranslateText);

        mainPanel.add(bookTagsJLabel);
        mainPanel.add(bookTagsText);

        mainPanel.add(bookAuthorsJLabel);
        mainPanel.add(bookAuthorsText);

        mainPanel.add(bookSeriaJLabel);
        mainPanel.add(bookSeriaText);

        var panelAddBookFile = new JPanel(new GridLayout(1, 3, 10, 10));
        panelAddBookFile.add(bookPathToFileJLabel);
        panelAddBookFile.add(bookPathToFileText);
        panelAddBookFile.add(bookOpenFileButton);
        bookOpenFileButton.addActionListener(e -> {
            AppRussifier.runRussifier(bookFileChooser);
            bookFileChooser.setDialogTitle("Выберите файл книги");
            // Определение режима - только файл
            bookFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = bookFileChooser.showOpenDialog(null);
            // Если файл выбран, то представим его в сообщении
            if (result == JFileChooser.APPROVE_OPTION)
                bookPathToFileText.setText(bookFileChooser.getSelectedFile().getAbsolutePath());
        });
        mainPanel.add(panelAddBookFile);

        var panelAddBookPic = new JPanel(new GridLayout(1, 3, 10, 10));
        panelAddBookPic.add(bookPathToPicJLabel);
        panelAddBookPic.add(bookPathToPicText);
        panelAddBookPic.add(bookOpenPicButton);

        bookOpenPicButton.addActionListener(e -> {
            AppRussifier.runRussifier(bookPicFileChooser);
            bookPicFileChooser.setDialogTitle("Выберите файл картинки книги");
            // Определение режима - только файл
            bookPicFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = bookPicFileChooser.showOpenDialog(null);
            // Если файл выбран, то представим его в сообщении
            if (result == JFileChooser.APPROVE_OPTION)
                bookPathToPicText.setText(bookPicFileChooser.getSelectedFile().getAbsolutePath());
        });
        mainPanel.add(panelAddBookPic);

        bookAddButton.addActionListener(actionEvent ->

                sendDTO());
        mainPanel.add(bookAddButton);
    }

    private boolean sendDTO() {
        if (bookTitleText.getText().isEmpty() || bookTitleText.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Название книги не должно быть пустым!!!", "Не задано название книги", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (bookYearOfEditionText.getText().isEmpty() || bookYearOfEditionText.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Год издания книги не должен быть пустым!!!", "Не задан год издания книги", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (bookGenreText.getText().isEmpty() || bookGenreText.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Жанр книги не должен быть пустым!!!", "Не задан жанр книги", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (bookAuthorsText.getText().isEmpty() || bookAuthorsText.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Автор книги не должен быть пустым!!!", "Не задан автор книги", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (bookPathToFileText.getText().isEmpty() || bookPathToFileText.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Путь до файла книги не должен быть пустым!!!", "Не задан путь до файла книги", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (bookPathToPicText.getText().isEmpty() || bookPathToPicText.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Путь до файла обложки не должен быть пустым!!!", "Не задан путь до файла обложки книги", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(HttpUtils.sendBookOutDtoToServer(createDTO())) {
            JOptionPane.showMessageDialog(null, "Книга добавлена", "Книга добавлена", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Ошибка отправки книги", "Ошибка отправки книги", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    private void clearFields() {
        bookTitleText.setText("");
        bookOriginalTitleText.setText("");
        bookYearOfEditionText.setText("");
        bookYearOfEditionTranslateText.setText("");
        bookIsbnOriginalText.setText("");
        bookIsbnTranslateText.setText("");
        bookGenreText.setText("");
        bookPubHouseText.setText("");
        bookPubHouseTranslateText.setText("");
        bookTagsText.setText("");
        bookAuthorsText.setText("");
        bookPathToFileText.setText("");
        bookPathToPicText.setText("");
    }

    public BookOutDTO createDTO() {
        var result = new BookOutDTO();
        try {
            result.setTitle(bookTitleText.getText());
            if(!bookOriginalTitleText.getText().isEmpty() || !bookOriginalTitleText.getText().isBlank()) {
                result.setOriginalTitle(bookOriginalTitleText.getText());
            } else {
                result.setOriginalTitle(bookTitleText.getText());
            }
            if(!bookIsbnOriginalText.getText().isEmpty() || !bookIsbnOriginalText.getText().isBlank()) {
                result.setIsbnOriginal(bookIsbnOriginalText.getText());
            } else {
                result.setIsbnOriginal(null);
            }
            if(!bookIsbnTranslateText.getText().isEmpty() || !bookIsbnTranslateText.getText().isBlank()) {
                result.setIsbnTranslate(bookIsbnTranslateText.getText());
            } else {
                result.setIsbnTranslate(null);
            }
            result.setGenre(bookGenreText.getText());

            if(!bookYearOfEditionText.getText().isEmpty() || !bookYearOfEditionText.getText().isBlank()) {
                result.setYearEdition(bookYearOfEditionText.getText());
            } else {
                result.setYearEdition(null);
            }

            if(!bookYearOfEditionTranslateText.getText().isEmpty() || !bookYearOfEditionTranslateText.getText().isBlank()) {
                result.setYearOfEditionTranslate(bookYearOfEditionTranslateText.getText());
            } else {
                result.setYearOfEditionTranslate(null);
            }
            if(!bookPubHouseText.getText().isEmpty() || !bookPubHouseText.getText().isBlank()) {
                result.setPubHouse(bookPubHouseText.getText());
            } else {
                result.setPubHouse(null);
            }
            if(!bookPubHouseTranslateText.getText().isEmpty() || !bookPubHouseTranslateText.getText().isBlank()) {
                result.setPubHouseTranslate(bookPubHouseTranslateText.getText());
            } else {
                result.setPubHouseTranslate(null);
            }
            result.setTags(StringUtils.fromString(bookTagsText.getText()));
            result.setAuthors(StringUtils.fromString(bookAuthorsText.getText()));

            result.setFileFormatBook(bookPathToFileText.getText().substring(bookPathToFileText.getText().lastIndexOf('.') + 1).toUpperCase());
            result.setFile(Base64Utils.fileToBase64(bookPathToFileText.getText()));
            result.setPicture(Base64Utils.fileToBase64(bookPathToPicText.getText()));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Что-то пошло не так");
            return null;
        }
        return result;
    }


}
