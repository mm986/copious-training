package com.copious.training.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:label.properties")
@Data
public class Label {
    @Value("${repo.owner.name}")
    public String owner;

    @Value("${repo.owner.designation}")
    public String desig;

    @Value("${repo.owner.experience}")
    public String exp;

    @Value("${repo.owner.org}")
    public String org;
}