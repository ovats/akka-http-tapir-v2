package repository

import scala.concurrent.Future

trait Repository[K, T] {

  // Classic CRUD operations
  def create(entity: T): Future[T]
  def read(key: K): Future[Option[T]]
  def update(entity: T): Future[T]
  def delete(key: K): Future[Option[T]]

  def findAll(): Future[Seq[T]]

}
