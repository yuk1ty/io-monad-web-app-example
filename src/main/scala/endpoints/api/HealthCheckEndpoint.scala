package endpoints.api

import cats.effect.IO
import org.http4s.{Response, Status}

trait HealthCheckEndpoint[F[_]] extends Endpoint[F]

object HealthCheckEndpoint {
  implicit def healthCheckEndpoint = new HealthCheckEndpoint[IO] {
    override def endpoint: IO[Response[IO]] = IO(Response(Status.Ok))
  }
}

trait UsesHealthCheckEndpoint {
  val healthCheckEndpoint: HealthCheckEndpoint[IO]
}

trait MixInHealthCheckEndpoint {
  val healthCheckEndpoint: HealthCheckEndpoint[IO] =
    implicitly[HealthCheckEndpoint[IO]]
}
