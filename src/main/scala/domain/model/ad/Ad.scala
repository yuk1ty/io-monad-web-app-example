package domain.model.ad

import domain.model.campaign.CampaignId
import domain.model.material.Material
import domain.model.price.Price

case class Ad(adId: AdId,
              adName: AdName,
              price: Price,
              material: Material,
              parentCampaignId: Seq[CampaignId])
