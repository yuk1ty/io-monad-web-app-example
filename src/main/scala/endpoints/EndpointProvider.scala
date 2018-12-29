package endpoints

import cats.effect.IO
import endpoints.api.MixInHealthCheckEndpoint
import org.http4s.Method.GET
import org.http4s._
import org.http4s.dsl.impl.Root
import org.http4s.dsl.io.{->, /}
import org.http4s.server.middleware.CORS

trait EndpointProvider[F[_]] {
  def endpoints: HttpService[F]
}

object EndpointProvider {
  implicit val endpointProviderIO: EndpointProvider[IO] = new EndpointProvider[IO] with MixInHealthCheckEndpoint {
    override def endpoints: HttpService[IO] =  CORS(HttpService[IO] {
      case GET -> Root / "api" => healthCheckEndpoint.endpoint
    })
  }
}

trait UsesEndpointProvider {
  val endpointProvider: EndpointProvider[IO]
}

trait MixInEndpointProvider {
  val endpointProvider: EndpointProvider[IO] = implicitly[EndpointProvider[IO]]
}
