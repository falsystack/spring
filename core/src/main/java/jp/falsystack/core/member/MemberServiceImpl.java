package jp.falsystack.core.member;

public class MemberServiceImpl implements MemberService{

    // OCP, DIP違反
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
