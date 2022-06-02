
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
                if(!modifyRecord.getText().equals("")){
                    int record = Integer.parseInt(modifyRecord.getText());
                    int i=0;
                    for (CheckBox checkbox : checkboxesList){
                        if(checkbox.isSelected()){
                            moveToTableRecord(bot, record, i);
                            sendKeys(bot, textFieldsList.get(i).getText());
                        }
                        i++;
                    }
                }

                if(additionalSaveToXMLCheckbox.isSelected()){
                    moveToExportToXML(bot);
                }
                moveToExportToDB(bot);

                szukanieListaLaptopowZOkreslonaMatryca(bot);

                wyborProduceta(bot);

            } catch (AWTException e) {
                e.printStackTrace();
            }
        });
    }

    private void szukanieListaLaptopowZOkreslonaMatryca(Robot bot) {
            if(matrixTypeTextField.getText().equals("blyszczaca")){
                //najechanie i wcisniecie comboboxa
                klikniecieNaPrzycisk(bot, 150, 40, 10);

                //przestawienie comboboxa
                klikniecieNaPrzycisk(bot, 150, 95, 1000);

            }
            else {

                if(matrixTypeTextField.getText().equals("matowa")){
                    //najechanie i wcisniecie comboboxa
                    klikniecieNaPrzycisk(bot, 150, 40, 10);

                    //przestawienie comboboxa
                    klikniecieNaPrzycisk(bot, 150, 80, 1000);

                }

            }

            //klikniecie przycisku "lista laptopow z określoną matyrycą"
            klikniecieNaPrzycisk(bot, 300, 40, 10);

    }

    private void wyborProduceta(Robot bot){
        if(manufacturerTextField.getText().equals("Samsung")){
            //najechanie i wcisniecie comboboxa
            klikniecieNaPrzycisk(bot, 800, 40, 1000);

            //przestawienie comboboxa
            klikniecieNaPrzycisk(bot, 800, 80, 10);

        }

        if(manufacturerTextField.getText().equals("Dell")){
                //najechanie i wcisniecie comboboxa
                klikniecieNaPrzycisk(bot, 800, 40, 1000);

                //przestawienie comboboxa
                klikniecieNaPrzycisk(bot, 800, 95, 10);

        }

        if(manufacturerTextField.getText().equals("Asus")){
            //najechanie i wcisniecie comboboxa
            klikniecieNaPrzycisk(bot, 800, 40, 1000);

            //przestawienie comboboxa
            klikniecieNaPrzycisk(bot, 800, 115, 10);

        }

        if(manufacturerTextField.getText().equals("Fujitsu")){
            //najechanie i wcisniecie comboboxa
            klikniecieNaPrzycisk(bot, 800, 40, 1000);

            //przestawienie comboboxa
            klikniecieNaPrzycisk(bot, 800, 145, 10);

        }

        if(manufacturerTextField.getText().equals("Huawei")){
            //najechanie i wcisniecie comboboxa
            klikniecieNaPrzycisk(bot, 800, 40, 1000);

            //przestawienie comboboxa
            klikniecieNaPrzycisk(bot, 800, 175, 10);

        }

        if(manufacturerTextField.getText().equals("MSI")){
            //najechanie i wcisniecie comboboxa
            klikniecieNaPrzycisk(bot, 800, 40, 1000);

            //przestawienie comboboxa
            klikniecieNaPrzycisk(bot, 800, 205, 10);

        }

        if(manufacturerTextField.getText().equals("Sony")){
            //najechanie i wcisniecie comboboxa
            klikniecieNaPrzycisk(bot, 800, 40, 1000);

            //przestawienie comboboxa
            klikniecieNaPrzycisk(bot, 800, 235, 10);

        }
        bot.delay(10000);
        //przycisniecie liczba laptopow producenta
        klikniecieNaPrzycisk(bot, 1000, 40, 10);
    }

    private void klikniecieNaPrzycisk(Robot bot, int i2, int i3, int i4) {
        bot.mouseMove(i2, i3);
        bot.delay(i4);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        bot.delay(100);
    }

    private void moveToExportToDB(Robot bot) {
        klikniecieNaPrzycisk(bot, 1000, 440, 100);
    }

    private void moveToExportToXML(Robot bot) {
        klikniecieNaPrzycisk(bot, 600, 440, 100);
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
        bot.mouseMove(20+110* kolumna, 494+25* record);
        bot.delay(100);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        bot.delay(100);
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
            robot.keyRelease(keyCode);
            robot.delay(10);
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
