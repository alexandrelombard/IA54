package fr.utbm.ia54;

import fr.utbm.ia54.BoidBody;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.eclipse.xtend.lib.annotations.AccessorType;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Alexandre Lombard
 */
@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public class Environment {
  @Accessors(AccessorType.PUBLIC_GETTER)
  private final Map<UUID, BoidBody> bodies = new ConcurrentHashMap<UUID, BoidBody>();
  
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
  
  @SyntheticMember
  public Environment() {
    super();
  }
  
  @Pure
  public Map<UUID, BoidBody> getBodies() {
    return this.bodies;
  }
}
