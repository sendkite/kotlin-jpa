package com.prac.demo.common.service

import com.prac.demo.common.entity.Member
import com.prac.demo.common.repository.MemberRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class MemberServiceTest {

    @Autowired
    private lateinit var memberService: MemberService

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Test
    fun `회원가입 테스트`() {
        val member = Member()
        member.name = "memberTest"

        val savedId = memberService.join(member)
        val findMember = memberRepository.findMemberById(savedId!!)
        assertEquals(member.name, findMember?.name)
    }

    @Test
    fun `중복 회원 예외 테스트`() {
        val member1 = Member()
        member1.name = "memberTest"

        val member2 = Member()
        member2.name = "memberTest"

        memberService.join(member1)
        assertThrows(IllegalStateException::class.java) {
            memberService.join(member2)
        }
    }
}