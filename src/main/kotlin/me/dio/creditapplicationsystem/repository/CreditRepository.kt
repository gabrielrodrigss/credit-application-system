package me.dio.creditapplicationsystem

import me.dio.creditapplicationsystem.entity.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

// Essa interface dá acesso para o front-end ao banco de dados, através do JpaRepository que referencia a Tabela e P. Key
// O JpaRepository oferece um CRUD.
@Repository
interface CreditRepository: JpaRepository<Credit, Long> {
}