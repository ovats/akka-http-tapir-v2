package com.demo.repository

import com.demo.domain.Contact

import scala.collection.concurrent.TrieMap
import scala.concurrent.{ExecutionContext, Future}

class InMemoryContactsRepository()(implicit ec: ExecutionContext) extends Repository[String, Contact] {

  private val repo: TrieMap[String, Contact] = TrieMap.empty

  override def create(entity: Contact): Future[Contact] =
    Future {
      repo += (entity.email -> entity)
      entity
    }

  override def read(key: String): Future[Option[Contact]] =
    Future {
      repo.get(key)
    }

  override def update(entity: Contact): Future[Contact] =
    Future {
      repo += (entity.email -> entity)
      entity
    }

  override def delete(key: String): Future[Option[Contact]] =
    Future {
      repo.remove(key)
    }

  override def findAll(): Future[Seq[Contact]] =
    Future {
      repo.toList.map(_._2)
    }
}
