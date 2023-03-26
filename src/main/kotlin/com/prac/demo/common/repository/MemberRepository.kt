package com.prac.demo.common.repository

import com.prac.demo.common.entity.Member
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Repository

@Repository
class MemberRepository {

    @PersistenceContext
    private lateinit var em: EntityManager

    fun findMemberById(id: Long) = em.find(Member::class.java, id)

    fun saveMember(member: Member) = em.persist(member)

    fun findAllMembers() = em.createQuery("select m from Member m", Member::class.java)
        .resultList

    fun findByName(name: String?) = em.createQuery("select m from Member m where m.name = :name", Member::class.java)
        .setParameter("name", name)
        .resultList
}