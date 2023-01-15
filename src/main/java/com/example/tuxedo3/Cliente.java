package com.example.tuxedo3;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Cliente extends Application{

    public static void main(String[] args) {
        try {
            //Las salidas a System.out.println() se muestran en el archivo Universidad.ud
            System.setOut(new PrintStream(new File("Universidad.ud")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        launch(args);
    }

    public void start(Stage primaryStage) {

        TabPane tabPane = new TabPane();

        //Obtener los servicios
        tabPane.getTabs().add(Insertar());
        tabPane.getTabs().add(Consultar());
        tabPane.getTabs().add(VerTabla());
        tabPane.getTabs().add(Eliminar());
        tabPane.getTabs().add(Actualizar());

        //Crear la escena
        GridPane grid = new GridPane();
        grid.add(tabPane, 0, 0);

        primaryStage.setScene(new Scene(grid));
        primaryStage.setTitle("Cliente");

        //Mostrar el escenario
        primaryStage.show();
    }

    public Tab Insertar(){
        //Operacion Insertar
        Tab tab1 = new Tab("Insertar");
        tab1.setClosable(false);
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.BASELINE_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        //Formulario Insertar
        Text scenetitle = new Text("Codigo: ");
        grid.add(scenetitle, 0, 0, 2, 1);
        TextField codigo = new TextField();
        grid.add(codigo, 0, 1);
        Text scenetitle2 = new Text("Grupo: ");
        grid.add(scenetitle2, 0, 2, 2, 1);
        TextField grupo = new TextField();
        grid.add(grupo, 0, 3);
        Text scenetitle3 = new Text("Materia: ");
        grid.add(scenetitle3, 0, 4, 2, 1);
        TextField materia = new TextField();
        grid.add(materia, 0, 5);
        Text scenetitle4 = new Text("Creditos: ");
        grid.add(scenetitle4, 0, 6, 2, 1);
        TextField creditos = new TextField();
        grid.add(creditos, 0, 7);
        Text scenetitle5 = new Text("Trimestre: ");
        grid.add(scenetitle5, 0, 8, 2, 1);
        TextField trimestre = new TextField();
        grid.add(trimestre, 0, 9);
        Text scenetitle6 = new Text("Nombre Profesor: ");
        grid.add(scenetitle6, 0, 10, 2, 1);
        TextField nombreProfesor = new TextField();
        grid.add(nombreProfesor, 0, 11);

        //Boton Insertar
        Button btn = new Button("Insertar");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 0, 12);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String codigo1 = codigo.getText();
                String grupo1 = grupo.getText();
                String materia1 = materia.getText();
                String creditos1 = creditos.getText();
                String trimestre1 = trimestre.getText();
                String nombreProfesor1 = nombreProfesor.getText();
                UD32.Crear_UD32(new Universidad(Long.parseLong(codigo1), "", "", -1, -1, ""), new File("Universidad.ud"), "seleccionar");
                UD32.ejecutarUD32();
                ArrayList<Universidad> universidades = UD32.Leer_UD32(new File("Universidad.ud"));
                if(universidades != null && !universidades.isEmpty()){
                    //Si regresa algo es porque existe el codigo
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("El codigo ya existe");
                    alert.showAndWait();
                }else{
                    UD32.Crear_UD32(new Universidad(Long.parseLong(codigo1), grupo1, materia1, Long.parseLong(creditos1), Long.parseLong(trimestre1), nombreProfesor1), new File("Universidad.ud"), "insertaLista");
                    UD32.ejecutarUD32();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informacion");
                    alert.setHeaderText("Informacion");
                    alert.setContentText("Se ha insertado correctamente");
                    alert.showAndWait();
                }
            }
        });
        tab1.setContent(grid);
        return tab1;
    }

    public Tab Consultar(){
        //Operacion Consultar
        Tab tab2 = new Tab("Buscar");
        tab2.setClosable(false);
        GridPane grid2 = new GridPane();
        grid2.setAlignment(Pos.BASELINE_LEFT);
        grid2.setHgap(10);
        grid2.setVgap(10);
        grid2.setPadding(new Insets(25, 25, 25, 25));
        //Formulario Consultar por codigo
        Text scenetitle7 = new Text("Codigo: ");
        grid2.add(scenetitle7, 0, 0, 2, 1);
        TextField codigo2 = new TextField();
        grid2.add(codigo2, 0, 1);
        //Boton Consultar
        Button btn2 = new Button("Buscar");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.TOP_RIGHT);
        hbBtn2.getChildren().add(btn2);
        grid2.add(hbBtn2, 0, 2);
        GridPane finalGrid = grid2;
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String codigo3 = codigo2.getText();
                //Generar consulta
                UD32.Crear_UD32(new Universidad(Long.parseLong(codigo3), "", "", -1, -1, ""), new File("Universidad.ud"), "seleccionar");
                //Ejecutar ud
                UD32.ejecutarUD32();
                //Leer del buffer
                ArrayList<Universidad> universidad = UD32.Leer_UD32(new File("Universidad.ud"));
                //Mostrar resultado en tabla
                TableView<Universidad> table = new TableView<Universidad>();
                table.setEditable(true);
                table.setItems(FXCollections.observableArrayList(universidad));
                TableColumn<Universidad, Long> codigoColumn = new TableColumn<Universidad, Long>("Codigo");
                codigoColumn.setCellValueFactory(new PropertyValueFactory<Universidad, Long>("codigo"));
                TableColumn<Universidad, String> grupoColumn = new TableColumn<Universidad, String>("Grupo");
                grupoColumn.setCellValueFactory(new PropertyValueFactory<Universidad, String>("grupo"));
                TableColumn<Universidad, String> materiaColumn = new TableColumn<Universidad, String>("Materia");
                materiaColumn.setCellValueFactory(new PropertyValueFactory<Universidad, String>("materia"));
                TableColumn<Universidad, Long> creditosColumn = new TableColumn<Universidad, Long>("Creditos");
                creditosColumn.setCellValueFactory(new PropertyValueFactory<Universidad, Long>("creditos"));
                TableColumn<Universidad, Long> trimestreColumn = new TableColumn<Universidad, Long>("Trimestre");
                trimestreColumn.setCellValueFactory(new PropertyValueFactory<Universidad, Long>("trimestre"));
                TableColumn<Universidad, String> nombreProfesorColumn = new TableColumn<Universidad, String>("Nombre Profesor");
                nombreProfesorColumn.setCellValueFactory(new PropertyValueFactory<Universidad, String>("nombreProfesor"));
                table.getColumns().addAll(codigoColumn, grupoColumn, materiaColumn, creditosColumn, trimestreColumn, nombreProfesorColumn);
                finalGrid.add(table, 0, 3);
            }
        });
        tab2.setContent(grid2);
        return tab2;
    }

    public Tab VerTabla(){
        //Ver Tabla
        Tab tab3 = new Tab("Ver Todo");
        tab3.setClosable(false);
        GridPane grid3 = new GridPane();
        grid3.setAlignment(Pos.BASELINE_LEFT);
        grid3.setHgap(10);
        grid3.setVgap(10);
        grid3.setPadding(new Insets(25, 25, 25, 25));
        //Boton Ver
        Button btn3 = new Button("Ver");
        HBox hbBtn3 = new HBox(10);
        hbBtn3.setAlignment(Pos.TOP_RIGHT);
        hbBtn3.getChildren().add(btn3);
        grid3.add(hbBtn3, 0, 0);
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Generar consulta
                UD32.Crear_UD32(new Universidad(), new File("Universidad.ud"), "imprimeLista");
                //Ejecutar ud
                UD32.ejecutarUD32();
                ArrayList<Universidad> universidad = UD32.Leer_UD32(new File("Universidad.ud"));
                //Mostrar resultado en tabla
                TableView<Universidad> table = new TableView<Universidad>();
                table.setEditable(true);
                table.setItems(FXCollections.observableArrayList(universidad));
                TableColumn<Universidad, Long> codigoColumn = new TableColumn<Universidad, Long>("Codigo");
                codigoColumn.setCellValueFactory(new PropertyValueFactory<Universidad, Long>("codigo"));
                TableColumn<Universidad, String> grupoColumn = new TableColumn<Universidad, String>("Grupo");
                grupoColumn.setCellValueFactory(new PropertyValueFactory<Universidad, String>("grupo"));
                TableColumn<Universidad, String> materiaColumn = new TableColumn<Universidad, String>("Materia");
                materiaColumn.setCellValueFactory(new PropertyValueFactory<Universidad, String>("materia"));
                TableColumn<Universidad, Long> creditosColumn = new TableColumn<Universidad, Long>("Creditos");
                creditosColumn.setCellValueFactory(new PropertyValueFactory<Universidad, Long>("creditos"));
                TableColumn<Universidad, Long> trimestreColumn = new TableColumn<Universidad, Long>("Trimestre");
                trimestreColumn.setCellValueFactory(new PropertyValueFactory<Universidad, Long>("trimestre"));
                TableColumn<Universidad, String> nombreProfesorColumn = new TableColumn<Universidad, String>("Nombre Profesor");
                nombreProfesorColumn.setCellValueFactory(new PropertyValueFactory<Universidad, String>("nombreProfesor"));
                table.getColumns().addAll(codigoColumn, grupoColumn, materiaColumn, creditosColumn, trimestreColumn, nombreProfesorColumn);
                grid3.add(table, 0, 1);
            }
        });
        tab3.setContent(grid3);
        return tab3;
    }

    public Tab Eliminar(){
        //Eliminar
        Tab tab4 = new Tab("Eliminar");
        tab4.setClosable(false);
        GridPane grid4 = new GridPane();
        grid4.setAlignment(Pos.BASELINE_LEFT);
        grid4.setHgap(10);
        grid4.setVgap(10);
        grid4.setPadding(new Insets(25, 25, 25, 25));
        //Codigo
        Label codigo4 = new Label("Codigo:");
        grid4.add(codigo4, 0, 0);
        TextField codigoField4 = new TextField();
        grid4.add(codigoField4, 1, 0);
        //Boton Eliminar
        Button btn4 = new Button("Eliminar");
        HBox hbBtn4 = new HBox(10);
        hbBtn4.setAlignment(Pos.TOP_RIGHT);
        hbBtn4.getChildren().add(btn4);
        grid4.add(hbBtn4, 0, 1);
        btn4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String codigo4 = codigoField4.getText();
                //Generar consulta
                UD32.Crear_UD32(new Universidad(Long.parseLong(codigo4), "", "", -1, -1, ""), new File("Universidad.ud"), "eliminar");
                //Ejecutar ud
                UD32.ejecutarUD32();
                //Alerta resultado
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacion");
                alert.setHeaderText(null);
                alert.setContentText("Se ha eliminado el registro");
                alert.showAndWait();
            }
        });
        tab4.setContent(grid4);
        return tab4;
    }

    public Tab Actualizar(){
        //Actualizar
        Tab tab5 = new Tab("Actualizar");
        tab5.setClosable(false);
        GridPane grid5 = new GridPane();
        grid5.setAlignment(Pos.BASELINE_LEFT);
        grid5.setHgap(10);
        grid5.setVgap(10);
        grid5.setPadding(new Insets(25, 25, 25, 25));

        //Codigo
        Label codigo5 = new Label("Codigo:");
        grid5.add(codigo5, 0, 0);
        TextField codigoField5 = new TextField();
        grid5.add(codigoField5, 1, 0);

        //Seleccion
        ChoiceBox<String> choiceBox = new ChoiceBox<String>();
        choiceBox.getItems().addAll("Grupo", "Materia", "Creditos", "Trimestre", "Nombre Profesor");
        choiceBox.setValue("Grupo");
        grid5.add(choiceBox, 0, 1);
        TextField seleccionField = new TextField();
        grid5.add(seleccionField, 1, 1);

        //Boton Actualizar
        Button btn5 = new Button("Actualizar");
        HBox hbBtn5 = new HBox(10);
        hbBtn5.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn5.getChildren().add(btn5);
        grid5.add(hbBtn5, 0, 3);
        btn5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String codigo5 = codigoField5.getText();
                String seleccion = (String) choiceBox.getValue();
                //Obtener valores actuales
                UD32.Crear_UD32(new Universidad(Long.parseLong(codigo5), "", "", -1, -1, ""), new File("Universidad.ud"), "seleccionar");
                //Ejecutar ud
                UD32.ejecutarUD32();
                //Leer del buffer
                ArrayList<Universidad> universidad = UD32.Leer_UD32(new File("Universidad.ud"));
                String grupo5 = universidad.get(0).getGrupo();
                String materia5 = universidad.get(0).getMateria();
                long creditos5 = universidad.get(0).getCreditos();
                long trimestre5 = universidad.get(0).getTrimestre();
                String nombreProfesor5 = universidad.get(0).getNombreProfesor();
                //Actualizar valores
                switch (seleccion) {
                    case "Grupo":
                        grupo5 = seleccionField.getText();
                        break;
                    case "Materia":
                        materia5 = seleccionField.getText();
                        break;
                    case "Creditos":
                        creditos5 = Long.parseLong(seleccionField.getText());
                        break;
                    case "Trimestre":
                        trimestre5 = Long.parseLong(seleccionField.getText());
                        break;
                    case "Nombre Profesor":
                        nombreProfesor5 = seleccionField.getText();
                        break;
                }
                //Generar consulta
                String finalGrupo = grupo5;
                String finalMateria = materia5;
                long finalCreditos = creditos5;
                long finalTrimestre = trimestre5;
                String finalNombreProfesor = nombreProfesor5;
                UD32.Crear_UD32(new Universidad(Long.parseLong(codigo5), finalGrupo, finalMateria, finalCreditos, finalTrimestre, finalNombreProfesor), new File("Universidad.ud"), "insertaLista");
                //Ejecutar ud
                UD32.ejecutarUD32();

                //Alerta resultado
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacion");
                alert.setHeaderText(null);
                alert.setContentText("Se ha actualizado el registro");
                alert.showAndWait();
            }
        });
        tab5.setContent(grid5);
        return tab5;
    }
}