package com.tapsilat.model.common;
import com.tapsilat.enums.*;


import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderCard {
    @JsonProperty("card_id")
    private String cardId;

    @JsonProperty("card_sequence")
    private Integer cardSequence;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Integer getCardSequence() {
        return cardSequence;
    }

    public void setCardSequence(Integer cardSequence) {
        this.cardSequence = cardSequence;
    }
}
