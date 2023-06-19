package com.example.c3s1pcCustomer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Products {
  @Id
  private String productId;
  private String productName;
  private String productSmallPrice;
  private String productMediumPrice;
  private String productLargePrice;
  private String imageUrl;
}
