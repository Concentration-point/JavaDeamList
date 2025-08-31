package com.concentration.proxyobject;

public class Tenant implements Rental{
    @Override
    public void renting() {
        System.out.println("看房 -> 签订合同 -> 交定金 -> 入住");
    }
}
