import cats.effect.IO
import endpoints.MixInEndpointProvider
import fs2.StreamApp
import org.http4s.server.blaze._

import scala.concurrent.ExecutionContext.Implicits.global

object Bootstrap extends StreamApp[IO] with MixInEndpointProvider {
  override def stream(
      args: List[String],
      requestShutdown: IO[Unit]): fs2.Stream[IO, StreamApp.ExitCode] = {
    BlazeBuilder[IO]
      .bindHttp(8080, "localhost")
      .mountService(endpointProvider.endpoints)
      .serve
  }
}
