package endpoints.api

import org.http4s.Response

trait Endpoint[F[_]] {
  def endpoint: F[Response[F]]
}
