package com.tapsilat.model.common;
import com.tapsilat.enums.*;


import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckoutDesign {
    @JsonProperty("input_background_color")
    private String inputBackgroundColor;

    @JsonProperty("input_text_color")
    private String inputTextColor;

    @JsonProperty("label_text_color")
    private String labelTextColor;

    @JsonProperty("left_background_color")
    private String leftBackgroundColor;

    @JsonProperty("logo")
    private String logo;

    @JsonProperty("order_detail_html")
    private String orderDetailHtml;

    @JsonProperty("pay_button_color")
    private String payButtonColor;

    @JsonProperty("redirect_url")
    private String redirectUrl;

    @JsonProperty("right_background_color")
    private String rightBackgroundColor;

    @JsonProperty("text_color")
    private String textColor;

    public String getInputBackgroundColor() {
        return inputBackgroundColor;
    }

    public void setInputBackgroundColor(String inputBackgroundColor) {
        this.inputBackgroundColor = inputBackgroundColor;
    }

    public String getInputTextColor() {
        return inputTextColor;
    }

    public void setInputTextColor(String inputTextColor) {
        this.inputTextColor = inputTextColor;
    }

    public String getLabelTextColor() {
        return labelTextColor;
    }

    public void setLabelTextColor(String labelTextColor) {
        this.labelTextColor = labelTextColor;
    }

    public String getLeftBackgroundColor() {
        return leftBackgroundColor;
    }

    public void setLeftBackgroundColor(String leftBackgroundColor) {
        this.leftBackgroundColor = leftBackgroundColor;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getOrderDetailHtml() {
        return orderDetailHtml;
    }

    public void setOrderDetailHtml(String orderDetailHtml) {
        this.orderDetailHtml = orderDetailHtml;
    }

    public String getPayButtonColor() {
        return payButtonColor;
    }

    public void setPayButtonColor(String payButtonColor) {
        this.payButtonColor = payButtonColor;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getRightBackgroundColor() {
        return rightBackgroundColor;
    }

    public void setRightBackgroundColor(String rightBackgroundColor) {
        this.rightBackgroundColor = rightBackgroundColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }
}
