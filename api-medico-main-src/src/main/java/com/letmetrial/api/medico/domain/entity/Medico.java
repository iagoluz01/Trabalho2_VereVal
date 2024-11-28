package com.letmetrial.api.medico.domain.entity;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Medico {

    private UUID id;

    private String crm;

    private Area area;

}
