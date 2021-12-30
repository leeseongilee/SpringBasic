package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository(); //DIP에 위배됨 "클라이언트가 구현(FixDiscountPolicy)에 의존(안다)하고있음"
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); //DIP에 위배됨 "클라이언트가 구현(FixDiscountPolicy)에 의존(안다)하고있음"
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);//discountPolicy 객체가 fixDiscoutPolicy의 구현객체인지, rate의 구현객체인지 알 수 없음(IoC 제어의 역전)

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}

