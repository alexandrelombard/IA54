package fr.utbm.ia54;

import fr.utbm.ia54.Vector2d;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.eclipse.xtend.lib.annotations.AccessorType;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Alexandre Lombard
 */
@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public class BoidBody {
  /**
   * Perception radius of the body
   */
  public final double perceptionRadius = 100.0;
  
  public final double maxAcceleration = 0.001d;
  
  public final double minVelocity = 0.00005d;
  
  /**
   * Max velocity length
   */
  public final double maxVelocity = 0.003d;
  
  /**
   * Max angular velocity
   */
  public final double maxAngularVelocity = 0.01d;
  
  @Accessors(AccessorType.PUBLIC_GETTER)
  private Vector2d position = new Vector2d();
  
  @Accessors(AccessorType.PUBLIC_GETTER)
  private Vector2d velocity = new Vector2d(this.minVelocity, 0.0d);
  
  public BoidBody() {
  }
  
  public BoidBody(final Vector2d position) {
    Vector2d _vector2d = new Vector2d(position);
    this.position = _vector2d;
  }
  
  public Vector2d move(final Vector2d acceleration, final double deltaTime) {
    Vector2d _xblockexpression = null;
    {
      Vector2d _xifexpression = null;
      double _length = acceleration.length();
      boolean _greaterThan = (_length > this.maxAcceleration);
      if (_greaterThan) {
        double _length_1 = acceleration.length();
        Vector2d _divide = acceleration.operator_divide(Double.valueOf(_length_1));
        _xifexpression = _divide.operator_multiply(Double.valueOf(this.maxAcceleration));
      } else {
        _xifexpression = acceleration;
      }
      Vector2d effectiveAcceleration = _xifexpression;
      while ((Math.abs(this.velocity.angle(this.velocity.operator_plus(effectiveAcceleration.operator_multiply(Double.valueOf(deltaTime))))) > this.maxAngularVelocity)) {
        Vector2d _multiply = effectiveAcceleration.operator_multiply(Double.valueOf(0.5));
        effectiveAcceleration = _multiply;
      }
      Vector2d _multiply = effectiveAcceleration.operator_multiply(Double.valueOf(deltaTime));
      Vector2d newVelocity = this.velocity.operator_plus(_multiply);
      double _length_2 = newVelocity.length();
      boolean _greaterThan_1 = (_length_2 > this.maxVelocity);
      if (_greaterThan_1) {
        double _length_3 = newVelocity.length();
        Vector2d _divide_1 = newVelocity.operator_divide(Double.valueOf(_length_3));
        Vector2d _multiply_1 = _divide_1.operator_multiply(Double.valueOf(this.maxVelocity));
        newVelocity = _multiply_1;
      }
      double _length_4 = newVelocity.length();
      boolean _lessThan = (_length_4 < this.minVelocity);
      if (_lessThan) {
        double _length_5 = newVelocity.length();
        Vector2d _divide_2 = newVelocity.operator_divide(Double.valueOf(_length_5));
        Vector2d _multiply_2 = _divide_2.operator_multiply(Double.valueOf(this.minVelocity));
        newVelocity = _multiply_2;
      }
      this.velocity = newVelocity;
      Vector2d _multiply_3 = this.velocity.operator_multiply(Double.valueOf(deltaTime));
      Vector2d _plus = this.position.operator_plus(_multiply_3);
      _xblockexpression = this.position = _plus;
    }
    return _xblockexpression;
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
    BoidBody other = (BoidBody) obj;
    if (Double.doubleToLongBits(other.perceptionRadius) != Double.doubleToLongBits(this.perceptionRadius))
      return false;
    if (Double.doubleToLongBits(other.maxAcceleration) != Double.doubleToLongBits(this.maxAcceleration))
      return false;
    if (Double.doubleToLongBits(other.minVelocity) != Double.doubleToLongBits(this.minVelocity))
      return false;
    if (Double.doubleToLongBits(other.maxVelocity) != Double.doubleToLongBits(this.maxVelocity))
      return false;
    if (Double.doubleToLongBits(other.maxAngularVelocity) != Double.doubleToLongBits(this.maxAngularVelocity))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + (int) (Double.doubleToLongBits(this.perceptionRadius) ^ (Double.doubleToLongBits(this.perceptionRadius) >>> 32));
    result = prime * result + (int) (Double.doubleToLongBits(this.maxAcceleration) ^ (Double.doubleToLongBits(this.maxAcceleration) >>> 32));
    result = prime * result + (int) (Double.doubleToLongBits(this.minVelocity) ^ (Double.doubleToLongBits(this.minVelocity) >>> 32));
    result = prime * result + (int) (Double.doubleToLongBits(this.maxVelocity) ^ (Double.doubleToLongBits(this.maxVelocity) >>> 32));
    result = prime * result + (int) (Double.doubleToLongBits(this.maxAngularVelocity) ^ (Double.doubleToLongBits(this.maxAngularVelocity) >>> 32));
    return result;
  }
  
  @Pure
  public Vector2d getPosition() {
    return this.position;
  }
  
  @Pure
  public Vector2d getVelocity() {
    return this.velocity;
  }
}
