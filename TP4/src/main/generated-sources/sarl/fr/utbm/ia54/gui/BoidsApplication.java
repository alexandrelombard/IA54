package fr.utbm.ia54.gui;

import fr.utbm.ia54.gui.BoidsApplicationController;
import io.sarl.javafx.FxApplication;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Alexandre Lombard
 */
@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public class BoidsApplication extends FxApplication {
  protected FXMLLoader doApplicationStart(final Stage stage) {
    final VBox root = new VBox();
    final Scene scene = new Scene(root, 800, 600);
    final BoidsApplicationController controller = new BoidsApplicationController();
    final Pane content = new Pane();
    VBox.setVgrow(content, Priority.ALWAYS);
    final MenuBar menuBar = new MenuBar();
    final Menu mainMenu = new Menu("Menu");
    final MenuItem initializeMenuItem = new MenuItem("Initialize");
    final MenuItem exitMenuItem = new MenuItem("Exit");
    final EventHandler<ActionEvent> _function = (ActionEvent it) -> {
      controller.initialize();
    };
    initializeMenuItem.setOnAction(_function);
    final EventHandler<ActionEvent> _function_1 = (ActionEvent it) -> {
      controller.exit();
    };
    exitMenuItem.setOnAction(_function_1);
    mainMenu.getItems().add(initializeMenuItem);
    mainMenu.getItems().add(exitMenuItem);
    menuBar.getMenus().add(mainMenu);
    root.getChildren().add(menuBar);
    root.getChildren().add(content);
    content.setStyle("-fx-background-color: lightgreen");
    controller.setContent(content);
    FXMLLoader loader = new FXMLLoader();
    loader.setRoot(root);
    loader.setController(controller);
    stage.setTitle("Boids");
    stage.setScene(scene);
    return loader;
  }
  
  @SyntheticMember
  public BoidsApplication() {
    super();
  }
}
