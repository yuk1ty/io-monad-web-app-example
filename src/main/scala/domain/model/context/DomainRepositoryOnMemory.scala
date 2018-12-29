package domain.model.context

import cats.effect.IO

trait DomainRepositoryOnMemory[F[_], K, V] {
  val memory: IO[Map[K, V]]
}
