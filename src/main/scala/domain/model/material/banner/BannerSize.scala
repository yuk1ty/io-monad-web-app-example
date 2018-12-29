package domain.model.material.banner

sealed abstract class BannerSize(w: Int, h: Int)

object BannerSize {
  case object Rectangle extends BannerSize(300, 250)
  case object Square300 extends BannerSize(300, 300)
  case object Square250 extends BannerSize(250, 250)
  case object Banner extends BannerSize(728, 90)
  case object SkyClever extends BannerSize(120, 600)
}
