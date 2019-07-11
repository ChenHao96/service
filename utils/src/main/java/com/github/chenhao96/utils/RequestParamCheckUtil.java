package com.github.chenhao96.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RequestParamCheckUtil {

    private static final Logger logger = LoggerFactory.getLogger(RequestParamCheckUtil.class);
    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    /**
     * 参数校验
     *
     * @param objective       被校验的对象
     * @param validatorGroups 校验字段的分组
     * @return null | size()==0:校验成功  其他情况返回校验的信息
     */
    public static Map<String, String> paramsValidator(Object objective, Class<?>... validatorGroups) {

        logger.info("objective:{}, validatorGroups:[{}]", objective, validatorGroups);

        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> errors = validator.validate(objective, validatorGroups);
        if (CollectionUtils.isNotEmpty(errors)) {
            Map<String, String> result = new HashMap<>(errors.size());

            int index = 0;
            for (ConstraintViolation<Object> err : errors) {
                String message = err.getMessage();
                index = getIndex(index, result, message);
            }

            return result;
        }

        return null;
    }

    /**
     * 校验请求参数工具
     *
     * @param errors 错误信息
     * @return null | size()==0:校验成功  其他情况返回校验的信息
     */
    public static Map<String, String> checkRequertParam(BindingResult errors) {

        if (errors != null && errors.hasErrors()) {

            List<ObjectError> errs = errors.getAllErrors();
            if (CollectionUtils.isNotEmpty(errs)) {

                int index = 0;
                Map<String, String> result = new HashMap<>(errs.size());
                for (ObjectError err : errs) {

                    String message = err.getDefaultMessage();
                    index = getIndex(index, result, message);
                }

                return result;
            }
        }

        return null;
    }

    private static int getIndex(int index, Map<String, String> result, String message) {

        if (StringUtils.isNotBlank(message)) {

            String key = index + "";
            String value = message;
            if (message.contains(":")) {
                key = message.split(":")[0];
                value = message.split(":")[1];
            }
            result.put(key, value);
            index++;
        }

        return index;
    }
}
