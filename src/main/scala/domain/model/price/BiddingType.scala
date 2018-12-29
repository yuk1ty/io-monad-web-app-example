package domain.model.price

sealed trait BiddingType

object BiddingType {
  case object Cpc extends BiddingType
  case object Cpm extends BiddingType
}
