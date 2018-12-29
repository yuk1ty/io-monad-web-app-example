package domain.model.material.banner

import domain.model.material.BannerSize

case class Banner(bannerId: BannerId, contentUrl: String, size: BannerSize)
