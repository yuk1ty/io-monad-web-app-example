package endpoints.api.services

import application.services.MixInServicesUsecase
import cats.effect.IO
import endpoints.api.Endpoint
import org.http4s.{Response, Status}

trait ServicesEndpoint[F[_]] extends Endpoint[F]

object ServicesEndpoint {
  implicit def servicesEndpoint =
    new ServicesEndpoint[IO] with MixInServicesUsecase {
      override def endpoint: IO[Response[IO]] = IO {
        // TODO for debug
        val tmp = servicesUsecase.services
        Response(Status.Ok)
      }
    }
}

trait UsesServicesEndpoint[F[_]] {
  val servicesEndpoint: ServicesEndpoint[F]
}

trait MixInServicesEndpoint {
  val servicesEndpoint: ServicesEndpoint[IO] = implicitly[ServicesEndpoint[IO]]
}
