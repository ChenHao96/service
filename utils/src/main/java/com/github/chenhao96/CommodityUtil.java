package com.github.chenhao96;

import com.github.chenhao96.utils.JsonUtils;
import com.github.chenhao96.utils.Utils;
import com.github.chenhao96.utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public final class CommodityUtil {

    private long last_Update_Time = -1;
    private Map<Integer, CommodityVo> waresMoney;
    private static final CommodityUtil self = new CommodityUtil();

    private CommodityUtil() {
    }

    private BigDecimal getGoldById(Integer waresId) {
        if (waresMoney == null || waresMoney.size() == 0) return null;
        CommodityVo vo = waresMoney.get(waresId);
        if (vo != null) {
            return vo.getGoldCount();
        }
        return null;
    }

    private BigDecimal getMoneyById(Integer waresId) {

        if (waresMoney == null || waresMoney.size() == 0) return null;
        CommodityVo vo = waresMoney.get(waresId);
        if (vo != null) {
            return vo.getMoney();
        }
        return null;
    }

    private void queryWares() {
        String u = CommodityUtil.class.getResource("/").getPath();
        File merchandise = new File(u, "merchandise.json");
        long modified = merchandise.lastModified();
        if (last_Update_Time != modified) {
            last_Update_Time = modified;
            try {
                String json = Utils.file2String(merchandise);
                if (StringUtils.isNotEmpty(json)) {
                    CommodityVo[] vos = JsonUtils.jsonStr2Object(json, CommodityVo[].class);
                    if (vos != null && vos.length > 0) {
                        if (waresMoney == null) {
                            waresMoney = new HashMap<>(vos.length);
                        }
                        for (CommodityVo vo : vos) {
                            if (vo != null) {
                                waresMoney.put(vo.getWaresId(), vo);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Long wares2Score(Integer wares) {

        self.queryWares();
        BigDecimal score = BigDecimal.ZERO;
        BigDecimal value = self.getGoldById(wares);
        if (value != null) {
            score = score.add(value);
        }

        return score.longValue();
    }

    public static BigDecimal wares2Money(Integer wares) {

        self.queryWares();
        BigDecimal score = BigDecimal.ZERO;
        BigDecimal value = self.getMoneyById(wares);
        if (value != null) {
            score = score.add(value);
        }

        return score;
    }
}
