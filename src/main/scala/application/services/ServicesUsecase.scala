package application.services

import application.ApplicationUsecase
import domain.model.service.{
  MixInServiceRepository,
  Service,
  ServiceId,
  UsesServiceRepository
}
import cats.effect.IO

trait ServicesUsecase[F[_]]
    extends ApplicationUsecase[F]
    with UsesServiceRepository[F] {
  def services: F[List[Service]]
  def startServiceById(serviceId: ServiceId): F[Option[Service]]
  def terminateServiceById(serviceId: ServiceId): F[Option[Service]]
}

object ServicesUsecase {
  implicit def serviceUsecase =
    new ServicesUsecase[IO] with MixInServiceRepository {

      def services: IO[List[Service]] = serviceRepository.findAll

      def startServiceById(serviceId: ServiceId): IO[Option[Service]] = {
        for {
          service <- serviceRepository.findServiceById(serviceId)
          started <- IO(service.map(_.start()))
          _ <- started.fold(IO.unit)(serviceRepository.put)
        } yield {
          started
        }
      }

      def terminateServiceById(serviceId: ServiceId): IO[Option[Service]] = {
        for {
          service <- serviceRepository.findServiceById(serviceId)
          terminated <- IO(service.map(_.terminate()))
          _ <- terminated.fold(IO.unit)(serviceRepository.put)
        } yield {
          terminated
        }
      }
    }
}

trait UsesServicesUsecase[F[_]] {
  val servicesUsecase: ServicesUsecase[F]
}

trait MixInServicesUsecase {
  val servicesUsecase: ServicesUsecase[IO] = implicitly[ServicesUsecase[IO]]
}
