package com.github.chenhao96;

import java.math.BigDecimal;

public class CommodityVo {

    private Integer waresId;

    private BigDecimal money;

    private BigDecimal goldCount;

    public Integer getWaresId() {
        return waresId;
    }

    public void setWaresId(Integer waresId) {
        this.waresId = waresId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getGoldCount() {
        return goldCount;
    }

    public void setGoldCount(BigDecimal goldCount) {
        this.goldCount = goldCount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CommodityVo{");
        sb.append("waresId=").append(waresId);
        sb.append(", money=").append(money);
        sb.append(", goldCount=").append(goldCount);
        sb.append('}');
        return sb.toString();
    }
}
