package com.greglturnquist.hackingspringboot.reactive.model;

public class Dish {
    // Dish 설명
    private String description;
    // Dish 배달 완료되었는지
    private boolean delivered = false;

    // 음식 배달하는 함수
    public static Dish deliver(Dish dish) {
        Dish deliveredDish = new Dish(dish.description);
        deliveredDish.delivered = true;
        return deliveredDish;
    }

    public Dish(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDelivered() {
        return delivered;
    }

    @Override
    public String toString() {
        return "Dish{" +
            "description='" + description + '\'' +
            ", delivered=" + delivered +
            '}';
    }
}
