package fr.utbm.ia54;

import fr.utbm.ia54.Perception;
import fr.utbm.ia54.Vector2d;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

/**
 * @author Alexandre Lombard
 */
@SarlSpecification("0.8")
@SarlElementType(15)
@SuppressWarnings("all")
public class PerceptionEvent extends Event {
  public final Vector2d velocity;
  
  public final List<Perception> perceivedBodies;
  
  public PerceptionEvent(final Vector2d velocity, final List<Perception> perceivedBodies) {
    this.velocity = velocity;
    this.perceivedBodies = perceivedBodies;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    return result;
  }
  
  /**
   * Returns a String representation of the PerceptionEvent event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected void toString(final ToStringBuilder builder) {
    super.toString(builder);
    builder.add("velocity", this.velocity);
    builder.add("perceivedBodies", this.perceivedBodies);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 4757002463L;
}
