//Thank you for posting your cake pattern dependency injection technique. I've started using it in some of my prototypes.

//I think I've made some improvements. I don't wrap the service interfaces in the namespace traits that declare dependencies on those interfaces. Instead I create separate DependsOn traits. This way the service interfaces, and implementations, can be used outside of this pattern. Also, I don't use self-typing because it imposes extra inheritance requirements on the service consumer (i.e. ComponentRegistry). Instead, I just extend the DependsOn traits for the same result. These changes might mean it's not really the cake pattern any more. Combined with my DependsOn... and ...Dependencies naming conventions, I've been calling it the declared dependence pattern.

//See sample code below:

// =======================
// Service interfaces. They're not wrapped in namespace traits, because
// there's no reason they shouldn't be used as normal interfaces if the
// situation calls for it.
trait OnOffDevice {
  def on: Unit
  def off: Unit
}
trait SensorDevice {
  def isCoffeePresent: Boolean
}
trait BeeperDevice {
  def beep(beepSound:String)
  }
trait TriggeredDevice {
  protected val name : String
  def trigger
}

// =======================
// DependsOn interfaces for declaring dependencies. The vals
// are protected so that the registry is not required to make them available
trait DependsOnOnOffDevice {
  protected val onOff: OnOffDevice
}
trait DependsOnSensorDevice {
  protected val sensor: SensorDevice
}
trait DependsOnBeeper {
  protected val beeper: BeeperDevice
}

// =======================
// Service implementations. They don't need to be wrapped in namespace traits
// since the interfaces they implement weren't wrapped. Thus, they can be
// used normally (outside of the declared dependence pattern) if you choose
class Heater extends OnOffDevice {
  def on = println("heater.on")
    def off = println("heater.off")
  }

class PotSensor extends SensorDevice {
  def isCoffeePresent = true
}

class Beeper extends BeeperDevice {
  def beep(beepSound:String) = println("beep: " + beepSound)
  }

class EmergencyShutoffSwitch extends TriggeredDevice {
  protected val name = "emergency switch"
  def trigger = {
    println("EMERGENCY SHUTOFF")
  }
}

// =======================
// Service declaring two dependencies that it wants injected. It just extends
// and mixes in the DependsOn interfaces instead of using self-typing, so that
// users of WarmerDependencies aren't required to explicitly mix in the
// DependsOn interfaces
trait WarmerDependencies extends DependsOnOnOffDevice with DependsOnSensorDevice {
  class Warmer extends TriggeredDevice {
    protected val name = "warmer"
    def trigger = {
      if (sensor.isCoffeePresent) onOff.on
      else onOff.off
    }
  }
}

// =======================
// An aspect that can be mixed in with any TriggeredDevice. It also just
// extends the DependsOn interface instead of using self-typing.
trait BeepWhenTriggeredAspectDependencies extends DependsOnBeeper {
  trait BeepWhenTriggeredAspect extends TriggeredDevice {
    abstract override def trigger = {
      beeper.beep(name + " triggered")
      super.trigger
    }
  }
}

// =======================
// instantiate the services in a module
object ServiceRegistry extends
WarmerDependencies with
BeepWhenTriggeredAspectDependencies {

  // onOff and sensor are required by the compiler because ServiceRegistry
  // extends WarmerDependencies. They are injected into warmer. They are
  // protected, because, in this example, ServiceRegistry consumers don't
  // need to know about them
  protected val onOff = new Heater
  protected val sensor = new PotSensor

  // beeper is required by the compiler because ServiceRegistry mixes in
  // BeepWhenTriggeredAspectDependencies. It is injected into warmer
  // and emergencySwitch. It is public so ComponentyRegistry consumers can
  // us it
  val beeper = new Beeper

  // A warmer that beeps when it's triggered
  val warmer = new Warmer with BeepWhenTriggeredAspect

  // An emergency shutdown switch that beeps when it's triggered
  val emergencySwitch = new EmergencyShutoffSwitch with BeepWhenTriggeredAspect
}
