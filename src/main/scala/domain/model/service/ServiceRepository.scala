package domain.model.service

import java.time.LocalDateTime

import cats.effect.IO
import domain.model.context.DomainRepositoryOnMemory
import cats.implicits._

trait ServiceRepository[F[_]]
    extends DomainRepositoryOnMemory[F, ServiceId, Service] {
  def findAll: F[Seq[Service]]
  def findServiceById(serviceId: ServiceId): F[Option[Service]]
  def findByDeliveryPeriod(start: Option[LocalDateTime] = None,
                           end: Option[LocalDateTime] = None): F[Seq[Service]]
}

object ServiceRepository {

  import utils.monoids.IterableMonoid._

  implicit def serviceRepository = new ServiceRepository[IO] {

    override val memory: IO[Map[ServiceId, Service]] = IO(
      Map.empty[ServiceId, Service])

    override def findAll: IO[Seq[Service]] = memory.map(_.values.toSeq)

    override def findServiceById(serviceId: ServiceId): IO[Option[Service]] =
      memory.map(_.get(serviceId))

    override def findByDeliveryPeriod(
        start: Option[LocalDateTime] = None,
        end: Option[LocalDateTime] = None): IO[Seq[Service]] =
      memory
        .map(_.values.toSeq)
        .map(services => {
          (for {
            start <- start.orElse(Some(LocalDateTime.now()))
            end <- end.orElse(Some(LocalDateTime.now()))
          } yield {
            services.filter(_.deliveryPeriod.between(start, end))
          }).orEmpty
        })
  }
}

trait UsesServiceRepository[F[_]] {
  val serviceRepository: ServiceRepository[F]
}

trait MixInServiceRepository {
  val serviceRepository: ServiceRepository[IO] =
    implicitly[ServiceRepository[IO]]
}
