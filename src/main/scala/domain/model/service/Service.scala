package domain.model.service

import domain.model.campaign.Campaign

case class Service(serviceId: ServiceId,
                   name: ServiceName,
                   deliveryPeriod: ServiceDeliveryPeriod,
                   deliverySwitch: ServiceDeliverySwitch,
                   campaigns: Seq[Campaign])
