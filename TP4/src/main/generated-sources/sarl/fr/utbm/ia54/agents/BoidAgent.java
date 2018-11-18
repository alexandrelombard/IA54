package fr.utbm.ia54.agents;

import com.google.common.base.Objects;
import fr.utbm.ia54.IntentionEvent;
import fr.utbm.ia54.Perception;
import fr.utbm.ia54.PerceptionEvent;
import fr.utbm.ia54.Vector2d;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Destroy;
import io.sarl.core.Initialize;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import io.sarl.lang.core.DynamicSkillProvider;
import io.sarl.lang.core.Scope;
import io.sarl.lang.core.Skill;
import io.sarl.lang.util.ClearableReference;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Alexandre Lombard
 */
@SarlSpecification("0.8")
@SarlElementType(18)
@SuppressWarnings("all")
public class BoidAgent extends Agent {
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
  }
  
  private void $behaviorUnit$Destroy$1(final Destroy occurrence) {
  }
  
  private void $behaviorUnit$PerceptionEvent$2(final PerceptionEvent occurrence) {
    Vector2d force = new Vector2d();
    Vector2d _cohesion = this.cohesion(occurrence.velocity, occurrence.perceivedBodies);
    Vector2d _plus = force.operator_plus(_cohesion);
    force = _plus;
    Vector2d _alignment = this.alignment(occurrence.velocity, occurrence.perceivedBodies);
    Vector2d _plus_1 = force.operator_plus(_alignment);
    force = _plus_1;
    Vector2d _separation = this.separation(occurrence.perceivedBodies);
    Vector2d _plus_2 = force.operator_plus(_separation);
    force = _plus_2;
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
    final Scope<Address> _function = (Address it) -> {
      UUID _uUID = it.getUUID();
      UUID _uUID_1 = occurrence.getSource().getUUID();
      return Objects.equal(_uUID, _uUID_1);
    };
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(new IntentionEvent(force), _function);
  }
  
  @Pure
  protected Vector2d cohesion(final Vector2d velocity, final List<Perception> perceptions) {
    Vector2d averageLocation = new Vector2d();
    for (final Perception p : perceptions) {
      Vector2d _plus = averageLocation.operator_plus(p.position);
      averageLocation = _plus;
    }
    int _size = perceptions.size();
    double _divide = (1d / _size);
    Vector2d _multiply = averageLocation.operator_multiply(Double.valueOf(_divide));
    averageLocation = _multiply;
    return averageLocation.operator_minus(velocity);
  }
  
  @Pure
  protected Vector2d alignment(final Vector2d velocity, final List<Perception> perceptions) {
    Vector2d averageVelocity = new Vector2d();
    for (final Perception p : perceptions) {
      Vector2d _plus = averageVelocity.operator_plus(p.velocity);
      averageVelocity = _plus;
    }
    int _size = perceptions.size();
    double _divide = (1d / _size);
    Vector2d _multiply = averageVelocity.operator_multiply(Double.valueOf(_divide));
    averageVelocity = _multiply;
    return averageVelocity.operator_minus(velocity);
  }
  
  protected Vector2d separation(final List<Perception> perceptions) {
    Vector2d force = new Vector2d();
    int count = 0;
    for (final Perception p : perceptions) {
      double _length = p.position.length();
      boolean _greaterThan = (_length > 0);
      if (_greaterThan) {
        double _length_1 = p.position.length();
        double _divide = (1d / _length_1);
        Vector2d _multiply = p.position.operator_multiply(Double.valueOf(_divide));
        Vector2d _plus = force.operator_plus(_multiply);
        force = _plus;
        count++;
      }
    }
    Vector2d _multiply_1 = force.operator_multiply(Double.valueOf((1d / count)));
    force = _multiply_1;
    return force;
  }
  
  @Extension
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(DefaultContextInteractions.class, ($0$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || $0$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? ($0$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = $0$getSkill(DefaultContextInteractions.class)) : $0$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS)", imported = DefaultContextInteractions.class)
  private DefaultContextInteractions $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = $getSkill(DefaultContextInteractions.class);
    }
    return $castSkill(DefaultContextInteractions.class, this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Initialize(final Initialize occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Initialize$0(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Destroy(final Destroy occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Destroy$1(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$PerceptionEvent(final PerceptionEvent occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$PerceptionEvent$2(occurrence));
  }
  
  @SyntheticMember
  public BoidAgent(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  @Deprecated
  public BoidAgent(final BuiltinCapacitiesProvider provider, final UUID parentID, final UUID agentID) {
    super(provider, parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  public BoidAgent(final UUID parentID, final UUID agentID, final DynamicSkillProvider skillProvider) {
    super(parentID, agentID, skillProvider);
  }
}
