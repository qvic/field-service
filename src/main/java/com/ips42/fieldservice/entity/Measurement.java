package com.ips42.fieldservice.entity;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.time.Instant;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@TypeDefs({
        @TypeDef(
                name = "int-array",
                typeClass = IntArrayType.class
        )
})
public class Measurement {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String tenantId;

    @Column
    private String fieldId;

    @Column
    private String measureId;

    @Column
    private String deviceId;

    @Type(type = "int-array")
    @Column(columnDefinition = "integer[]")
    private Integer[] measures;

    @Column
    private Double longitude;

    @Column
    private Double latitude;

    @Column
    private Instant date;
}