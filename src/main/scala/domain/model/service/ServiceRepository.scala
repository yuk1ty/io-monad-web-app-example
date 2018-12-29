package domain.model.service

import java.time.LocalDateTime

import cats.effect.IO
import domain.model.context.DomainRepositoryOnMemory

trait ServiceRepository[F[_]]
    extends DomainRepositoryOnMemory[F, ServiceId, Service] {
  def findAll: IO[List[Service]]
  def findServiceById(serviceId: ServiceId): F[Option[Service]]
  def findActiveServices(
      start: LocalDateTime = LocalDateTime.now(),
      end: LocalDateTime = LocalDateTime.now()): F[List[Service]]
}

object ServiceRepository {

  implicit def serviceRepository = new ServiceRepository[IO] {

    override val memory: IO[Map[ServiceId, Service]] = IO(
      Map.empty[ServiceId, Service])

    override def findAll: IO[List[Service]] = memory.map(_.values.toList)

    override def findServiceById(serviceId: ServiceId): IO[Option[Service]] =
      memory.map(_.get(serviceId))

    override def findActiveServices(
        start: LocalDateTime = LocalDateTime.now(),
        end: LocalDateTime = LocalDateTime.now()): IO[List[Service]] = {
      memory.map(_.values.filter(_.deliveryPeriod.between(start, end)).toList)
    }
  }
}

trait UsesServiceRepository[F[_]] {
  val serviceRepository: ServiceRepository[F]
}

trait MixInServiceRepository {
  val serviceRepository: ServiceRepository[IO] =
    implicitly[ServiceRepository[IO]]
}
