package domain.model.campaign

sealed trait CampaignDeliverySwitch

object CampaignDeliverySwitch {
  case object On extends CampaignDeliverySwitch
  case object Off extends CampaignDeliverySwitch
}
