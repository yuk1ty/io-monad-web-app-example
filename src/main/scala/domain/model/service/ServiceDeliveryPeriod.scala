package domain.model.service

import java.time.LocalDateTime

case class ServiceDeliveryPeriod(start: LocalDateTime, end: LocalDateTime) {
  def between(_start: LocalDateTime, _end: LocalDateTime): Boolean =
    start.isBefore(_start) || end.isAfter(_end)
}
