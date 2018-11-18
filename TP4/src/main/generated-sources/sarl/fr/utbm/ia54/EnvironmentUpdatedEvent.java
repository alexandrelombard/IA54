package fr.utbm.ia54;

import fr.utbm.ia54.Environment;
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
public class EnvironmentUpdatedEvent extends Event {
  public final Environment environment;
  
  public EnvironmentUpdatedEvent(final Environment environment) {
    this.environment = environment;
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
   * Returns a String representation of the EnvironmentUpdatedEvent event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected void toString(final ToStringBuilder builder) {
    super.toString(builder);
    builder.add("environment", this.environment);
  }
}
