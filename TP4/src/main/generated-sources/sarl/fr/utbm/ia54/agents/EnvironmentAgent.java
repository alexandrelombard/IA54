package fr.utbm.ia54.agents;

import com.google.common.base.Objects;
import fr.utbm.ia54.BoidBody;
import fr.utbm.ia54.Environment;
import fr.utbm.ia54.EnvironmentUpdatedEvent;
import fr.utbm.ia54.IntentionEvent;
import fr.utbm.ia54.Perception;
import fr.utbm.ia54.PerceptionEvent;
import fr.utbm.ia54.SimulationStepEvent;
import fr.utbm.ia54.Vector2d;
import fr.utbm.ia54.agents.BoidAgent;
import io.sarl.core.AgentKilled;
import io.sarl.core.AgentSpawned;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Destroy;
import io.sarl.core.Initialize;
import io.sarl.core.Lifecycle;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.inject.Inject;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Alexandre Lombard
 */
@SarlSpecification("0.8")
@SarlElementType(18)
@SuppressWarnings("all")
public class EnvironmentAgent extends Agent {
  private final Environment environment = new Environment();
  
  private final Map<UUID, Vector2d> intentions = new ConcurrentHashMap<UUID, Vector2d>();
  
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    IntegerRange _upTo = new IntegerRange(0, 10);
    for (final Integer i : _upTo) {
      Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$castSkill(Lifecycle.class, (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = this.$getSkill(Lifecycle.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE);
      _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.spawn(BoidAgent.class);
    }
  }
  
  private void $behaviorUnit$Destroy$1(final Destroy occurrence) {
  }
  
  private void $behaviorUnit$AgentSpawned$2(final AgentSpawned occurrence) {
    boolean _equals = Objects.equal(occurrence.agentType, "fr.utbm.ia54.agents.BoidAgent");
    if (_equals) {
      double _random = Math.random();
      double _multiply = (400d * _random);
      double _plus = (_multiply + 200d);
      double _random_1 = Math.random();
      double _multiply_1 = (300d * _random_1);
      double _plus_1 = (_multiply_1 + 150d);
      Vector2d _vector2d = new Vector2d(_plus, _plus_1);
      BoidBody body = new BoidBody(_vector2d);
      this.environment.getBodies().put(occurrence.agentID, body);
    }
  }
  
  private void $behaviorUnit$AgentKilled$3(final AgentKilled occurrence) {
    boolean _equals = Objects.equal(occurrence.agentType, "fr.utbm.ia54.agents.BoidAgent");
    if (_equals) {
      this.environment.getBodies().remove(occurrence.agentID);
    }
  }
  
  private void $behaviorUnit$SimulationStepEvent$4(final SimulationStepEvent occurrence) {
    final HashMap<UUID, Vector2d> currentIntentions = new HashMap<UUID, Vector2d>(this.intentions);
    Set<Map.Entry<UUID, Vector2d>> _entrySet = currentIntentions.entrySet();
    for (final Map.Entry<UUID, Vector2d> intentionEntry : _entrySet) {
      {
        final BoidBody body = this.environment.getBodies().get(intentionEntry.getKey());
        if ((body != null)) {
          body.move(intentionEntry.getValue(), occurrence.deltaTime);
        }
      }
    }
    Set<Map.Entry<UUID, BoidBody>> _entrySet_1 = this.environment.getBodies().entrySet();
    for (final Map.Entry<UUID, BoidBody> bodyEntry : _entrySet_1) {
      {
        final BoidBody body = bodyEntry.getValue();
        final ArrayList<Perception> perceivedBodies = CollectionLiterals.<Perception>newArrayList();
        Collection<BoidBody> _values = this.environment.getBodies().values();
        for (final BoidBody otherBody : _values) {
          if (((!Objects.equal(otherBody, bodyEntry.getValue())) && 
            (otherBody.getPosition().distance(body.getPosition()) < body.perceptionRadius))) {
            Vector2d _position = otherBody.getPosition();
            Vector2d _position_1 = body.getPosition();
            final Vector2d relativePosition = _position.operator_minus(_position_1);
            Vector2d _velocity = otherBody.getVelocity();
            Perception _perception = new Perception(relativePosition, _velocity);
            perceivedBodies.add(_perception);
          }
        }
        DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
        Vector2d _velocity_1 = body.getVelocity();
        Vector2d _vector2d = new Vector2d(_velocity_1);
        final Scope<Address> _function = (Address it) -> {
          UUID _uUID = it.getUUID();
          UUID _key = bodyEntry.getKey();
          return Objects.equal(_uUID, _key);
        };
        _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(new PerceptionEvent(_vector2d, perceivedBodies), _function);
      }
    }
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(new EnvironmentUpdatedEvent(this.environment));
  }
  
  private void $behaviorUnit$IntentionEvent$5(final IntentionEvent occurrence) {
    this.intentions.put(occurrence.getSource().getUUID(), occurrence.force);
  }
  
  @Extension
  @ImportedCapacityFeature(Lifecycle.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_LIFECYCLE;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(Lifecycle.class, ($0$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || $0$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) ? ($0$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = $0$getSkill(Lifecycle.class)) : $0$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE)", imported = Lifecycle.class)
  private Lifecycle $CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = $getSkill(Lifecycle.class);
    }
    return $castSkill(Lifecycle.class, this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE);
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
  private void $guardEvaluator$AgentSpawned(final AgentSpawned occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$AgentSpawned$2(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$SimulationStepEvent(final SimulationStepEvent occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$SimulationStepEvent$4(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$IntentionEvent(final IntentionEvent occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$IntentionEvent$5(occurrence));
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
  private void $guardEvaluator$AgentKilled(final AgentKilled occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$AgentKilled$3(occurrence));
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
  
  @SyntheticMember
  public EnvironmentAgent(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  @Deprecated
  public EnvironmentAgent(final BuiltinCapacitiesProvider provider, final UUID parentID, final UUID agentID) {
    super(provider, parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  public EnvironmentAgent(final UUID parentID, final UUID agentID, final DynamicSkillProvider skillProvider) {
    super(parentID, agentID, skillProvider);
  }
}
