package com.github.chenhao96.service;

public class ServiceResult<T> {

    private T returnData;

    private boolean result;

    private String message;

    private int responseCode;

    public ServiceResult() {
    }

    public ServiceResult(boolean result) {
        this.result = result;
    }

    /**
     * 获取错误编码
     *
     * @return 错误编码
     */
    public int getResponseCode() {
        return responseCode;
    }

    /**
     * 设置错误编码
     *
     * @param responseCode 错误编码
     */
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * 获取正常与否
     *
     * @return 正常与否
     */
    public boolean isResult() {
        return result;
    }

    /**
     * 设置正常与否
     *
     * @param result 正常与否
     */
    public void setResult(boolean result) {
        this.result = result;
    }

    /**
     * 获取错误信息
     *
     * @return 错误信息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置错误信息
     *
     * @param message 错误信息
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取返回值
     *
     * @return 返回值
     */
    public T getReturnData() {
        return returnData;
    }

    /**
     * 设置返回值
     *
     * @param returnData 返回值
     */
    public void setReturnData(T returnData) {
        this.returnData = returnData;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ServiceResult{");
        sb.append("returnData=").append(returnData);
        sb.append(", result=").append(result);
        sb.append(", message='").append(message).append('\'');
        sb.append(", responseCode=").append(responseCode);
        sb.append('}');
        return sb.toString();
    }
}
