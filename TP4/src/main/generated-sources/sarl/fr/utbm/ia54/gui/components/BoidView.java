package fr.utbm.ia54.gui.components;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Alexandre Lombard
 */
@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public class BoidView extends Group {
  private final double DEFAULT_RADIUS = 5d;
  
  private final double DEFAULT_LINE_LENGTH = 10d;
  
  public BoidView() {
    Circle _circle = new Circle(0, 0, this.DEFAULT_RADIUS);
    this.getChildren().add(_circle);
    Line _line = new Line(0, 0, this.DEFAULT_LINE_LENGTH, 0);
    this.getChildren().add(_line);
  }
  
  public BoidView(final double x, final double y, final double theta) {
    Circle _circle = new Circle(x, y, this.DEFAULT_RADIUS);
    this.getChildren().add(_circle);
    double _cos = Math.cos(theta);
    double _multiply = (this.DEFAULT_LINE_LENGTH * _cos);
    double _plus = (x + _multiply);
    double _sin = Math.sin(theta);
    double _multiply_1 = (this.DEFAULT_LINE_LENGTH * _sin);
    double _plus_1 = (y + _multiply_1);
    Line _line = new Line(x, y, _plus, _plus_1);
    this.getChildren().add(_line);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    BoidView other = (BoidView) obj;
    if (Double.doubleToLongBits(other.DEFAULT_RADIUS) != Double.doubleToLongBits(this.DEFAULT_RADIUS))
      return false;
    if (Double.doubleToLongBits(other.DEFAULT_LINE_LENGTH) != Double.doubleToLongBits(this.DEFAULT_LINE_LENGTH))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + (int) (Double.doubleToLongBits(this.DEFAULT_RADIUS) ^ (Double.doubleToLongBits(this.DEFAULT_RADIUS) >>> 32));
    result = prime * result + (int) (Double.doubleToLongBits(this.DEFAULT_LINE_LENGTH) ^ (Double.doubleToLongBits(this.DEFAULT_LINE_LENGTH) >>> 32));
    return result;
  }
}
