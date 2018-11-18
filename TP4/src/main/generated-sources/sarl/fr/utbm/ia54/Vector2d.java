package fr.utbm.ia54;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.eclipse.xtext.xbase.lib.DoubleExtensions;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Alexandre Lombard
 */
@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public class Vector2d {
  public Double x;
  
  public Double y;
  
  public Vector2d() {
    this.x = Double.valueOf(0d);
    this.y = Double.valueOf(0d);
  }
  
  public Vector2d(final double x, final double y) {
    this.x = Double.valueOf(x);
    this.y = Double.valueOf(y);
  }
  
  public Vector2d(final Vector2d v) {
    this.x = v.x;
    this.y = v.y;
  }
  
  @Pure
  public double distance(final Vector2d v) {
    return Math.sqrt(this.distanceSquared(v));
  }
  
  @Pure
  public double distanceSquared(final Vector2d v) {
    double _minus = DoubleExtensions.operator_minus(this.x, v.x);
    double _pow = Math.pow(_minus, 2d);
    double _minus_1 = DoubleExtensions.operator_minus(this.y, v.y);
    double _pow_1 = Math.pow(_minus_1, 2d);
    return (_pow + _pow_1);
  }
  
  @Pure
  public Vector2d operator_plus(final Vector2d v1) {
    double _plus = DoubleExtensions.operator_plus(v1.x, this.x);
    double _plus_1 = DoubleExtensions.operator_plus(v1.y, this.y);
    return new Vector2d(_plus, _plus_1);
  }
  
  @Pure
  public Vector2d operator_minus(final Vector2d v1) {
    double _minus = DoubleExtensions.operator_minus(this.x, v1.x);
    double _minus_1 = DoubleExtensions.operator_minus(this.y, v1.y);
    return new Vector2d(_minus, _minus_1);
  }
  
  @Pure
  public Vector2d operator_multiply(final Double l) {
    double _multiply = DoubleExtensions.operator_multiply(this.x, l);
    double _multiply_1 = DoubleExtensions.operator_multiply(this.y, l);
    return new Vector2d(_multiply, _multiply_1);
  }
  
  @Pure
  public double length() {
    double _multiply = DoubleExtensions.operator_multiply(this.x, this.x);
    double _multiply_1 = DoubleExtensions.operator_multiply(this.y, this.y);
    return (_multiply + _multiply_1);
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
    Vector2d other = (Vector2d) obj;
    if (Double.doubleToLongBits(other.x) != Double.doubleToLongBits(this.x))
      return false;
    if (Double.doubleToLongBits(other.y) != Double.doubleToLongBits(this.y))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
    result = prime * result + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
    return result;
  }
}
