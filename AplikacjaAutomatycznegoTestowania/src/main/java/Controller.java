
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField modifyRecord;

    @FXML
    private CheckBox manufacturerCheckbox;

    @FXML
    private TextField manufacturerTextField;

    @FXML
    private CheckBox screenSizeCheckbox;

    @FXML
    private TextField screenSizeTextField;

    @FXML
    private CheckBox resolutionCheckbox;

    @FXML
    private TextField resolutionTextField;

    @FXML
    private CheckBox matrixTypeCheckbox;

    @FXML
    private TextField matrixTypeTextField;

    @FXML
    private CheckBox processorNameCheckbox;

    @FXML
    private TextField processorNameTextField;

    @FXML
    private CheckBox physicalCoresNameCheckbox;

    @FXML
    private TextField physicalCoresTextField;

    @FXML
    private CheckBox clockSpeedCheckbox;

    @FXML
    private TextField clockSpeedTextField;

    @FXML
    private CheckBox ramCheckbox;

    @FXML
    private TextField ramTextField;

    @FXML
    private CheckBox discSizeCheckbox;

    @FXML
    private TextField discSizeTextField;

    @FXML
    private CheckBox discTypeCheckbox;

    @FXML
    private TextField discTypeTextField;

    @FXML
    private CheckBox graphicCardNameCheckbox;

    @FXML
    private TextField graphicCardNameTextField;

    @FXML
    private CheckBox graphicCardMemoryCheckbox;

    @FXML
    private TextField graphicCardMemoryTextField;

    @FXML
    private CheckBox osCheckbox;

    @FXML
    private TextField osTextField;

    @FXML
    private CheckBox discReaderCheckbox;

    @FXML
    private TextField discReaderTextField;

    @FXML
    private CheckBox additionalSaveToXMLCheckbox;

    @FXML
    private Button wykonajButton;


    static String nazwy_kolumn[] ={"nazwa producenta", "przekątna ekranu", "rozdzielczość ekranu", "rodzaj powierzchni ekranu", "czy ekran jest dotykowy", "nazwa procesora",  "liczba rdzeni fizycznych", "prędkość taktowania MHz", "wielkość pamięci RAM", "pojemność dysku", "rodzaj dysku", "nazwa układu graficznego", "pamięć układu graficznego", "nazwa systemu operacyjnego", "rodzaj napędu fizycznego w komputerze"};

    ClassReader classReader;
    private final List<CheckBox> checkboxesList = new ArrayList<>();
    private final List<TextField> textFieldsList = new ArrayList<>();

    public void initialize(URL location, ResourceBundle resources) {
        classReader = new ClassReader(Data.class);
        classReader.readClassMethods(); //metoda przechowuje metody klasy Data


        wykonajButton.setOnAction(event -> {
            fillListCheckboxes();
            fillListTextFields();
            Robot bot = null;
            try {
                bot = new Robot();
                bot.mouseMove(20, 440);
                bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                bot.delay(10);
                int record = Integer.parseInt(modifyRecord.getText());
                int i=0;
                for (CheckBox checkbox : checkboxesList){
                    if(checkbox.isSelected()){
                        moveToTableRecord(bot, record, i);
                        sendKeys(bot, textFieldsList.get(i).getText());
                    }
                    i++;
                }
//                if(manufacturerCheckbox.isSelected()){
//                    moveToTableRecord(bot, record, 0);
//                    sendKeys(bot, manufacturerTextField.getText());
//                }
//                if(screenSizeCheckbox.isSelected()){
//                    moveToTableRecord(bot, record, 1);
//                    sendKeys(bot, screenSizeTextField.getText());
//                }
//                if(resolutionCheckbox.isSelected()){
//                    moveToTableRecord(bot, record, 2);
//                    sendKeys(bot, resolutionTextField.getText());
//                }
//                if(matrixTypeCheckbox.isSelected()){
//                    moveToTableRecord(bot, record, 3);
//                    sendKeys(bot, manufacturerTextField.getText());
//                }


            } catch (AWTException e) {
                e.printStackTrace();
            }
        });
    }

    private void fillListCheckboxes() {
        checkboxesList.add(manufacturerCheckbox);
        checkboxesList.add(screenSizeCheckbox);
        checkboxesList.add(resolutionCheckbox);
        checkboxesList.add(matrixTypeCheckbox);
        checkboxesList.add(processorNameCheckbox);
        checkboxesList.add(physicalCoresNameCheckbox);
        checkboxesList.add(clockSpeedCheckbox);
        checkboxesList.add(ramCheckbox);
        checkboxesList.add(discSizeCheckbox);
        checkboxesList.add(discTypeCheckbox);
        checkboxesList.add(graphicCardNameCheckbox);
        checkboxesList.add(graphicCardMemoryCheckbox);
        checkboxesList.add(osCheckbox);
        checkboxesList.add(discReaderCheckbox);

    }

    private void fillListTextFields() {

        textFieldsList.add(manufacturerTextField);
        textFieldsList.add(screenSizeTextField);
        textFieldsList.add(resolutionTextField);
        textFieldsList.add(matrixTypeTextField);
        textFieldsList.add(processorNameTextField);
        textFieldsList.add(physicalCoresTextField);
        textFieldsList.add(clockSpeedTextField);
        textFieldsList.add(ramTextField);
        textFieldsList.add(discSizeTextField);
        textFieldsList.add(discTypeTextField);
        textFieldsList.add(graphicCardNameTextField);
        textFieldsList.add(graphicCardMemoryTextField);
        textFieldsList.add(osTextField);
        textFieldsList.add(discReaderTextField);
    }

    private void moveToTableRecord(Robot bot, int record, int kolumna) {
        bot.mouseMove(20+100* kolumna, 495+25* record);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    void sendKeys(Robot robot, String keys) {
        for (char c : keys.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                throw new RuntimeException(
                        "Key code not found for character '" + c + "'");
            }
//            if (needsShift(c)) {
//                robot.keyPress(KeyEvent.VK_SHIFT);
//                robot.delay(100);
//            }
            robot.keyPress(keyCode);
            robot.delay(100);
            robot.keyRelease(keyCode);
            robot.delay(100);
        }
        robot.keyPress(KeyEvent.VK_ENTER);
    }

    private boolean needsShift(Character c) {
        return Character.isUpperCase(c);
    }




    /**
     * ------------------------------------------------------------------------------------------------
     */



}
