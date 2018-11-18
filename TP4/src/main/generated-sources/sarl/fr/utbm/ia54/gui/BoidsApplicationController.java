package fr.utbm.ia54.gui;

import fr.utbm.ia54.BoidBody;
import fr.utbm.ia54.Environment;
import fr.utbm.ia54.gui.BoidsApplicationAgent;
import fr.utbm.ia54.gui.components.BoidView;
import io.sarl.javafx.FxViewerController;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import org.eclipse.xtend.lib.annotations.AccessorType;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure0;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Alexandre Lombard
 */
@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public class BoidsApplicationController extends FxViewerController {
  private final AtomicBoolean launched = new AtomicBoolean(false);
  
  private UUID launchedAgent;
  
  @Accessors(AccessorType.PUBLIC_SETTER)
  private Pane content;
  
  /**
   * UI initialization. The agent framework is started.
   */
  @FXML
  public UUID initialize() {
    UUID _xifexpression = null;
    boolean _andSet = this.launched.getAndSet(true);
    boolean _not = (!_andSet);
    if (_not) {
      final Procedure0 _function = () -> {
      };
      _xifexpression = this.launchedAgent = this.startAgentApplication(BoidsApplicationAgent.class, _function);
    }
    return _xifexpression;
  }
  
  /**
   * UI exit. Kill everything.
   */
  @FXML
  @Pure
  public void exit() {
    System.exit(0);
  }
  
  @Pure
  protected void refresh(final Environment environment) {
    final Runnable _function = () -> {
      this.content.getChildren().clear();
      Collection<BoidBody> _values = environment.getBodies().values();
      for (final BoidBody body : _values) {
        {
          final BoidView boidView = new BoidView();
          boidView.setTranslateX((body.getPosition().x).doubleValue());
          boidView.setTranslateY((body.getPosition().y).doubleValue());
          this.content.getChildren().add(boidView);
        }
      }
    };
    Platform.runLater(_function);
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
    BoidsApplicationController other = (BoidsApplicationController) obj;
    if (!Objects.equals(this.launchedAgent, other.launchedAgent)) {
      return false;
    }
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Objects.hashCode(this.launchedAgent);
    return result;
  }
  
  @SyntheticMember
  public BoidsApplicationController() {
    super();
  }
  
  public void setContent(final Pane content) {
    this.content = content;
  }
}
