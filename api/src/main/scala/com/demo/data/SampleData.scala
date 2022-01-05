package com.demo.data

import com.demo.domain.Contact
import repository.Repository

import scala.concurrent.Future

object SampleData {

  // Sample data for tests
  def addSampleData(repo: Repository[String, Contact]): Future[Contact] = {
    repo.create(Contact("Carl Sagan", "555-5551", "carl.sagan@mail.com", "US"))
    repo.create(Contact("Albert Einstein", "555-5552", "albert.einstein@mail.com", "DE"))
    repo.create(Contact("Isaac Newton", "555-5553", "isaac.newton@mail.com", "EN"))
    repo.create(Contact("Marie Curie", "555-5554", "marie.curien@mail.com", "PL"))
    repo.create(Contact("Stephen Hawking", "555-5555", "stephen.hawking@mail.com", "EN"))
  }

}
