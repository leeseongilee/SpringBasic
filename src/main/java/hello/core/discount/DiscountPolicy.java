package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {

    /**
     * @param memeber
     * @param price
     * @return 할인 대상 금액
     */
    int discount(Member memeber, int price);

}
