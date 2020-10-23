package io.github.gaelrenoux.tranzactio

import java.sql.{Connection => JdbcConnection}

import zio.{Has, Tag, ZIO}


/** Template implementing a default transactional mechanism, based on a ConnectionSource. */
abstract class DatabaseServiceBase[Connection <: Has[_] : Tag](connectionSource: ConnectionSource.Service)
  extends DatabaseOps.ServiceOps[Connection] {

  import connectionSource._

  def connectionFromJdbc(connection: JdbcConnection): ZIO[Any, Nothing, Connection]

  private[tranzactio] override def transactionRFull[R <: Has[_], E, A](zio: ZIO[R with Connection, E, A], commitOnFailure: Boolean = false)
    (implicit errorStrategies: ErrorStrategiesRef): ZIO[R, Either[DbException, E], A] =
    ZIO.accessM[R] { r =>
      runTransaction({ c: JdbcConnection =>
        val t: ZIO[Any, E, A] = connectionFromJdbc(c).map(r ++ _).flatMap(zio.provide(_))
        t
      }, commitOnFailure)
    }

  private[tranzactio] override def autoCommitRFull[R <: Has[_], E, A](zio: ZIO[R with Connection, E, A])
    (implicit errorStrategies: ErrorStrategiesRef): ZIO[R, Either[DbException, E], A] = {
    ZIO.accessM[R] { r =>
      runAutoCommit { c: JdbcConnection =>
        val t: ZIO[Any, E, A] = connectionFromJdbc(c).map(r ++ _).flatMap(zio.provide(_))
        t
      }
    }
  }

}

