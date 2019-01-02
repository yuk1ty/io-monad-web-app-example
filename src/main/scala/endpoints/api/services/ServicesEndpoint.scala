package endpoints.api.services

import application.services.MixInServicesUsecase
import cats.effect.IO
import endpoints.api.Endpoint
import org.http4s.{Response, Status}

trait ServicesEndpoint[F[_]] extends Endpoint[F]

object ServicesEndpoint {
  implicit def servicesEndpoint =
    new ServicesEndpoint[IO] with MixInServicesUsecase {
      import io.circe.generic.auto._
      import io.circe.syntax._
      import org.http4s.dsl.io._
      import org.http4s.circe._

      override def endpoint: IO[Response[IO]] = for {
        services <- servicesUsecase.services
        res <- Ok(services.asJson)
      } yield {
        res
      }
    }
}

trait UsesServicesEndpoint[F[_]] {
  val servicesEndpoint: ServicesEndpoint[F]
}

trait MixInServicesEndpoint {
  val servicesEndpoint: ServicesEndpoint[IO] = implicitly[ServicesEndpoint[IO]]
}
