package com.teamluffy.tripproject.friends.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class FriendTypeConverter implements AttributeConverter<FriendType, String> {
  @Override
  public String convertToDatabaseColumn(FriendType attribute) {
    return attribute.name();
  }

  @Override
  public FriendType convertToEntityAttribute(String dbData) {
    return FriendType.valueOf(dbData);
  }
}