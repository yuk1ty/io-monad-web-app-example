package domain.model.advertiser

import domain.model.service.Service

case class Advertiser(advertiserid: AdvertiserId,
                      advertiserName: AdvertiserName,
                      services: Seq[Service])
