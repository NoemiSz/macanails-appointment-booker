package com.macanails.macanailsappointmentbooker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class Nail {
    private NailDecoration decor;
    private NailType type;

    public void setDecorEnum(String decor) {
        this.decor = NailDecoration.valueOf(decor);
    }

    public void setTypeEnum(String type) {
        this.type = NailType.valueOf(type);
    }

    @Override
    public String toString() {
        return getType().getDescription()+"\n"
                +getDecor().getDescription();
    }

}
