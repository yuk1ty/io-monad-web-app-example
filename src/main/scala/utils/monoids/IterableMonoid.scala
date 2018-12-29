package utils.monoids

import cats.Monoid

object IterableMonoid {
  implicit def seqMonoid[A]: Monoid[Seq[A]] = new Monoid[Seq[A]] {
    override def empty: Seq[A] = Seq.empty[A]
    override def combine(x: Seq[A], y: Seq[A]): Seq[A] = x ++ y
  }

  implicit def setMonoid[A]: Monoid[Set[A]] = new Monoid[Set[A]] {
    override def empty: Set[A] = Set.empty[A]
    override def combine(x: Set[A], y: Set[A]): Set[A] = x ++ y
  }
}
