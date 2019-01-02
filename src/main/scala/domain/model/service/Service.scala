package domain.model.service

import domain.model.advertiser.AdvertiserId
import domain.model.campaign.Campaign

case class Service(serviceId: ServiceId,
                   name: ServiceName,
                   deliveryPeriod: ServiceDeliveryPeriod,
                   deliverySwitch: ServiceDeliverySwitch,
                   advertiserId: AdvertiserId,
                   campaigns: Seq[Campaign]) {

  def start(): Service = this.copy(deliverySwitch = ServiceDeliverySwitch.On)

  def terminate(): Service =
    this.copy(deliverySwitch = ServiceDeliverySwitch.Off)
}

