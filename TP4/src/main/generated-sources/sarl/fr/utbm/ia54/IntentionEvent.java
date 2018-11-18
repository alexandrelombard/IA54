package fr.utbm.ia54;

import fr.utbm.ia54.Vector2d;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

/**
 * @author Alexandre Lombard
 */
@SarlSpecification("0.8")
@SarlElementType(15)
@SuppressWarnings("all")
public class IntentionEvent extends Event {
  public Vector2d force;
  
  public IntentionEvent(final Vector2d f) {
    this.force = f;
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
   * Returns a String representation of the IntentionEvent event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected void toString(final ToStringBuilder builder) {
    super.toString(builder);
    builder.add("force", this.force);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = -384654640L;
}
