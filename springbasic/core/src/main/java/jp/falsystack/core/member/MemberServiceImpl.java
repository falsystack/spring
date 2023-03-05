package jp.falsystack.core.member;

public class MemberServiceImpl implements MemberService{

  private final MemberRepository memberRepository;

  // 생성자 주입
  public MemberServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Override
  public void join(Member member) {
    memberRepository.save(member);
  }

  @Override
  public Member findMember(Long memberId) {
    return memberRepository.findById(memberId);
  }
}
