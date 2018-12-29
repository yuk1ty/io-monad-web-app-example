package domain.model.campaign

import domain.model.ad.Ad
import domain.model.service.ServiceId

case class Campaign(campaignId: CampaignId,
                    campaignName: CampaignName,
                    deliveryPeriod: CampaignDeliveryPeriod,
                    deliverySwitch: CampaignDeliverySwitch,
                    ads: Seq[Ad],
                    parentServiceId: ServiceId)
