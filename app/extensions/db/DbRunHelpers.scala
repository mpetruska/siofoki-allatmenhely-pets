package extensions.db

import play.api.db.slick.HasDatabaseConfig
import slick.basic.BasicProfile
import slick.dbio.DBIO

import scala.concurrent.Future

trait DbRunHelpers[P <: BasicProfile] { _: HasDatabaseConfig[P] =>

  def dbRun[A, R](f: A => DBIO[R])(a: A): Future[R] = db.run(f(a))
  def dbRun[A, B, R](f: (A, B) => DBIO[R])(a: A, b: B): Future[R] = db.run(f(a, b))
  def dbRun[A, B, C, R](f: (A, B, C) => DBIO[R])(a: A, b: B, c: C): Future[R] = db.run(f(a, b, c))
  def dbRun[A, B, C, D, R](f: (A, B, C, D) => DBIO[R])(a: A, b: B, c: C, d: D): Future[R] = db.run(f(a, b, c, d))
  def dbRun[A, B, C, D, E, R](f: (A, B, C, D, E) => DBIO[R])(a: A, b: B, c: C, d: D, e: E): Future[R] = db.run(f(a, b, c, d, e))
  def dbRun[A, B, C, D, E, G, R](f: (A, B, C, D, E, G) => DBIO[R])(a: A, b: B, c: C, d: D, e: E, g: G): Future[R] = db.run(f(a, b, c, d, e, g))
  def dbRun[A, B, C, D, E, G, H, R](f: (A, B, C, D, E, G, H) => DBIO[R])(a: A, b: B, c: C, d: D, e: E, g: G, h: H): Future[R] = db.run(f(a, b, c, d, e, g, h))
  def dbRun[A, B, C, D, E, G, H, I, R](f: (A, B, C, D, E, G, H, I) => DBIO[R])(a: A, b: B, c: C, d: D, e: E, g: G, h: H, i: I): Future[R] = db.run(f(a, b, c, d, e, g, h, i))
  def dbRun[A, B, C, D, E, G, H, I, J, R](f: (A, B, C, D, E, G, H, I, J) => DBIO[R])(a: A, b: B, c: C, d: D, e: E, g: G, h: H, i: I, j: J): Future[R] = db.run(f(a, b, c, d, e, g, h, i, j))
  def dbRun[A, B, C, D, E, G, H, I, J, K, R](f: (A, B, C, D, E, G, H, I, J, K) => DBIO[R])(a: A, b: B, c: C, d: D, e: E, g: G, h: H, i: I, j: J, k: K): Future[R] = db.run(f(a, b, c, d, e, g, h, i, j, k))
  def dbRun[A, B, C, D, E, G, H, I, J, K, L, R](f: (A, B, C, D, E, G, H, I, J, K, L) => DBIO[R])(a: A, b: B, c: C, d: D, e: E, g: G, h: H, i: I, j: J, k: K, l: L): Future[R] = db.run(f(a, b, c, d, e, g, h, i, j, k, l))
  def dbRun[A, B, C, D, E, G, H, I, J, K, L, M, R](f: (A, B, C, D, E, G, H, I, J, K, L, M) => DBIO[R])(a: A, b: B, c: C, d: D, e: E, g: G, h: H, i: I, j: J, k: K, l: L, m: M): Future[R] = db.run(f(a, b, c, d, e, g, h, i, j, k, l, m))
  def dbRun[A, B, C, D, E, G, H, I, J, K, L, M, N, R](f: (A, B, C, D, E, G, H, I, J, K, L, M, N) => DBIO[R])(a: A, b: B, c: C, d: D, e: E, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N): Future[R] = db.run(f(a, b, c, d, e, g, h, i, j, k, l, m, n))
  def dbRun[A, B, C, D, E, G, H, I, J, K, L, M, N, O, R](f: (A, B, C, D, E, G, H, I, J, K, L, M, N, O) => DBIO[R])(a: A, b: B, c: C, d: D, e: E, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O): Future[R] = db.run(f(a, b, c, d, e, g, h, i, j, k, l, m, n, o))
  def dbRun[A, B, C, D, E, G, H, I, J, K, L, M, N, O, Q, R](f: (A, B, C, D, E, G, H, I, J, K, L, M, N, O, Q) => DBIO[R])(a: A, b: B, c: C, d: D, e: E, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, q: Q): Future[R] = db.run(f(a, b, c, d, e, g, h, i, j, k, l, m, n, o, q))
  def dbRun[A, B, C, D, E, G, H, I, J, K, L, M, N, O, Q, S, R](f: (A, B, C, D, E, G, H, I, J, K, L, M, N, O, Q, S) => DBIO[R])(a: A, b: B, c: C, d: D, e: E, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O,q: Q, s: S): Future[R] = db.run(f(a, b, c, d, e, g, h, i, j, k, l, m, n, o, q, s))

}
