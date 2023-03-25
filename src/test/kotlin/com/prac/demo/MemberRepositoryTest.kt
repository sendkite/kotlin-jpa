package com.prac.demo

import com.prac.demo.common.entity.Member
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private val memberRepository = MemberRepository()

    @Test
    @Transactional
    fun save() {
        val member = Member()
        member.name = "member1"
        val savedId = memberRepository.save(member)
        val findMember = memberRepository.find(savedId!!)
        assertEquals(member.name, findMember?.name)
    }
}