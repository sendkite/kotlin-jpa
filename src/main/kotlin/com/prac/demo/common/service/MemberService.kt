package com.prac.demo.common.service

import com.prac.demo.common.entity.Member
import com.prac.demo.common.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository
) {
    @Transactional
    fun join(member: Member): Long? {
        validateDuplicateMember(member)
        memberRepository.saveMember(member)
        return member.id
    }

    private fun validateDuplicateMember(member: Member) {
        val findMembers = memberRepository.findByName(member.name)
            if (findMembers.isNotEmpty()) {
                throw IllegalStateException("이미 존재하는 회원입니다.")
            }
    }

    fun findAllMembers(): List<Member> {
        return memberRepository.findAllMembers()
    }

    fun findOne(memberId: Long): Member {
        return memberRepository.findMemberById(memberId)
    }
}