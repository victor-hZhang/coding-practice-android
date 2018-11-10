package com.codingtest.deeplinktest.codingtest.apiService.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Event {
    @SerializedName("name")
    public String name;

    @SerializedName("date")
    private Date dateTime;

    @SerializedName("available_seats")
    public int availableSeats;

    @SerializedName("price")
    public BigDecimal price;

    @SerializedName("venue")
    public String venue;

    @SerializedName("labels")
    public List<String> labels;

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date datetime) {
        this.dateTime = datetime;
    }
}
