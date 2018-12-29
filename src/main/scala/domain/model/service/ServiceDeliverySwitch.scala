package domain.model.service

sealed trait ServiceDeliverySwitch

object ServiceDeliverySwitch {
  case object On extends ServiceDeliverySwitch
  case object Off extends ServiceDeliverySwitch
}
