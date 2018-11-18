package fr.utbm.ia54.gui.components;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import javafx.scene.Group;
import javafx.scene.shape.Circle;

/**
 * @author Alexandre Lombard
 */
@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public class BoidView extends Group {
  public BoidView() {
    Circle _circle = new Circle(0, 0, 5);
    this.getChildren().add(_circle);
  }
  
  public BoidView(final double x, final double y) {
    Circle _circle = new Circle(x, y, 5);
    this.getChildren().add(_circle);
  }
}
