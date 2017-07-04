import com.sun.org.apache.xpath.internal.operations.Number;
import javafx.application.Application;
import java.io.File;
import java.io.FileWriter;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.stage.FileChooser;


public class BuildJavaFX extends Application  {
    private TableView<Notebook> table = new TableView<Notebook>();
    private final ObservableList<Notebook> data =
            FXCollections.observableArrayList(
                    new Notebook("001", "Asus","3.8 Ггц","6 Гб"),
                    new Notebook("002", "Lenovo","3.2 Ггц", "8 Гб"),
                    new Notebook("003", "Asus","3.8 Ггц","6 Гб"),
                    new Notebook("004", "Apple","3.2 Ггц", "8 Гб")
                    );
    final HBox hb = new HBox();

    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    StackedBarChart<String, java.lang.Number> sbc;
    TilePane tpForSbc;

    public void UpdateSbc(TilePane tp){
        xAxis.setLabel("Производитель");
        yAxis.setLabel("Количество моделей");
        tp.getChildren().remove(sbc);
        sbc = new StackedBarChart<String, java.lang.Number>(xAxis, yAxis);
        sbc.setTitle("Частота процессора / Производитель ноутбука");
        sbc.setPadding(new Insets(10, 0, 0, 10));
        sbc.setMaxWidth(400);
        ChartWork.reDate(data);
        sbc.getData().addAll(ChartWork.series);
        tp.getChildren().add(sbc);
    }

    public static void main (String[] args) {
        try{
            launch(args);
        }
        catch (Exception e){}
    }

    @Override
    public void start(Stage stage){
        Scene scene = new Scene(new Group());
        stage.setTitle("Модели ноутбуков");
        stage.setWidth(950);
        stage.setHeight(550);
        final Label label = new Label("Модели ноутбуков");
        label.setFont(new Font("Arial", 16));
        label.setMaxWidth(400);
        table.setEditable(true);
        TilePane tilePane = new TilePane();
        data.addListener(new ListChangeListener<Notebook>() {
            @Override
            public void onChanged(
                    javafx.collections.ListChangeListener.Change<? extends Notebook> arg0) {
                // TODO Auto-generated method stub
                UpdateSbc(tilePane);
            }
        });

        TableColumn NoteIDCol = new TableColumn("ID");
        NoteIDCol.setMinWidth(100);
        NoteIDCol.setMaxWidth(125);
        NoteIDCol.setCellValueFactory(
                new PropertyValueFactory<Notebook, String>("ID"));
        NoteIDCol.setCellFactory(TextFieldTableCell.forTableColumn());
        NoteIDCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Notebook, String>>() {
                    @Override
                    public void handle(CellEditEvent<Notebook, String> t) {
                        ((Notebook) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setID(t.getNewValue());
                    }
                }
        );

        TableColumn NoteCreatorCol = new TableColumn("Производитель");
        NoteCreatorCol.setMinWidth(100);
        NoteCreatorCol.setMaxWidth(125);
        NoteCreatorCol.setCellValueFactory(
                new PropertyValueFactory<Notebook, String>("Creator"));
        NoteCreatorCol.setCellFactory(TextFieldTableCell.forTableColumn());
        NoteCreatorCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Notebook, String>>() {
                    @Override
                    public void handle(CellEditEvent<Notebook, String> t) {
                        ((Notebook) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setCreator(t.getNewValue());
                        UpdateSbc(tilePane);
                    }
                }
        );

        TableColumn NoteFrequencyCol = new TableColumn("Частота");
        NoteFrequencyCol.setMinWidth(100);
        NoteFrequencyCol.setMaxWidth(125);
        NoteFrequencyCol.setCellValueFactory(
                new PropertyValueFactory<Notebook, Number>("Frequency"));
        NoteFrequencyCol.setCellFactory(TextFieldTableCell.forTableColumn());
        NoteFrequencyCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Notebook, String>>() {
                    @Override
                    public void handle(CellEditEvent<Notebook, String> t) {
                        ((Notebook) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setFrequency(t.getNewValue());
                        UpdateSbc(tilePane);
                    }
                }
        );

        TableColumn NoteRamCol = new TableColumn("RAM");
        NoteRamCol.setMinWidth(100);
        NoteRamCol.setMaxWidth(125);
        NoteRamCol.setCellValueFactory(
                new PropertyValueFactory<Notebook, String>("RAM"));
        NoteRamCol.setCellFactory(TextFieldTableCell.forTableColumn());
        NoteRamCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Notebook, String>>() {
                    @Override
                    public void handle(CellEditEvent<Notebook, String> t) {
                        ((Notebook) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setRAM(t.getNewValue());
                    }
                }
        );

        table.setItems(data);
        table.getColumns().addAll(NoteIDCol,NoteCreatorCol,NoteFrequencyCol,NoteRamCol);
        table.setMaxWidth(400);
        table.setMinWidth(400);

        final TextField addID = new TextField();
        addID.setPromptText("ID");
        addID.setMaxWidth(NoteIDCol.getWidth());

        final TextField addCreator = new TextField();
        addCreator.setPromptText("Производитель");
        addCreator.setMaxWidth(NoteCreatorCol.getWidth());

        final TextField addFrequency = new TextField();
        addFrequency.setPromptText("Частота");
        addFrequency.setMaxWidth(NoteFrequencyCol.getWidth());

        final TextField addRAM = new TextField();
        addRAM.setPromptText("RAM");
        addRAM.setMaxWidth(NoteRamCol.getWidth());

        final Button addButton = new Button("Добавить");
        addButton.setMinWidth(98);
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try{
                    if (addID.getText().equals("")||
                            addCreator.getText().equals("")||
                            addFrequency.getText().equals("")||
                            addRAM.getText().equals(""))
                        throw new Exception();
                data.add(new Notebook(
                        addID.getText(),
                        addCreator.getText(),
                        addFrequency.getText(),
                        addRAM.getText()));
                addID.clear();
                addCreator.clear();
                addFrequency.clear();
                addRAM.clear();}
                catch (Exception ex) {}
            }
        });

        final Button deleteButton = new Button ("Удалить");
        deleteButton.setMinWidth(98);
        deleteButton.setOnAction (new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try{
                    data.remove(table.getSelectionModel().getSelectedItem());
                    UpdateSbc(tilePane);
                }
                catch (Exception exc) {}
            }
        });

        final Button saveButton = new Button("Сохранить в ...");
        saveButton.setMinWidth(98);
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(stage);
                if (file != null) {
                    try {
                        FileWriter fr = new FileWriter(file);
                        fr.write(data.toString());
                        fr.close();
                    }
                    catch (Exception ex){}
                }
                }
            });

        final Button loadButton = new Button("Загрузить из ...");
        loadButton.setMinWidth(98);
        loadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(stage);
                if (file != null) {
                    try {
                        ObservableListReader.readFileToObservableList(file,data);
                    }
                    catch (Exception ex){}
                }
            }
        });

        hb.getChildren().addAll(addID, addCreator,addFrequency,addRAM);
        hb.setSpacing(3);
        hb.setMinWidth(400);
        hb.setMaxWidth(500);

        final HBox hb2 = new HBox();

        hb2.getChildren().addAll(addButton,deleteButton,saveButton, loadButton);
        hb2.setSpacing(3);
        hb2.setMinWidth(400);
        hb2.setMaxWidth(500);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb, hb2);
        vbox.setMaxWidth(400);
        tilePane.getChildren().addAll(vbox);
        UpdateSbc(tilePane);
        ((Group) scene.getRoot()).getChildren().add(tilePane);
        stage.setScene(scene);
        stage.show();
    }
}