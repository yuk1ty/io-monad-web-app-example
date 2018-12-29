package domain.model.service

import cats.effect.IO
import domain.model.context.DomainRepositoryOnMemory

trait ServiceRepository[F[_]]
    extends DomainRepositoryOnMemory[F, ServiceId, Service] {
  def findAll: F[Seq[Service]]
  def findServiceById(serviceId: ServiceId): F[Option[Service]]
}

object ServiceRepository {
  implicit def serviceRepository = new ServiceRepository[IO] {

    override val memory: IO[Map[ServiceId, Service]] = IO(
      Map.empty[ServiceId, Service])

    override def findAll: IO[Seq[Service]] = memory.map(_.values.toSeq)

    override def findServiceById(serviceId: ServiceId): IO[Option[Service]] =
      memory.map(_.get(serviceId))
  }
}

trait UsesServiceRepository[F[_]] {
  val serviceRepository: ServiceRepository[F]
}

trait MixInServiceRepository {
  val serviceRepository: ServiceRepository[IO] =
    implicitly[ServiceRepository[IO]]
}
